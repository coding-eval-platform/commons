package ar.edu.itba.cep.executor.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

/**
 * Represents an execution response.
 */
@Getter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true, of = "executionRequest")
public class ExecutionResponse {

    /**
     * The {@link ExecutionRequest} to which this execution response belongs to.
     */
    private final ExecutionRequest executionRequest;
    /**
     * The result of the execution (e.g finished, timed-out, or with compilation errors).
     */
    private final ExecutionResult result;
    /**
     * The execution's exit code.
     */
    private final int exitCode;
    /**
     * A {@link List} of {@link String}s that were sent to standard output by the program being executed.
     * Each {@link String} in the {@link List} is a line that was printed in standard output.
     */
    private final List<String> stdout;
    /**
     * A {@link List} of {@link String}s that were sent to standard error output by the program being executed.
     * Each {@link String} in the {@link List} is a line that was printed in standard error output.
     */
    private final List<String> stderr;


    /**
     * Constructor.
     *
     * @param executionRequest The {@link ExecutionRequest} to which this execution response belongs to.
     * @param result           The result of the execution (e.g finished, timed-out, or with compilation errors).
     * @param exitCode         The execution's exit code.
     * @param stdout           A {@link List} of {@link String}s
     *                         that were sent to standard output by the program being executed.
     *                         Each {@link String} in the {@link List} is a line that was printed in standard output.
     * @param stderr           A {@link List} of {@link String}s
     *                         that were sent to standard error output by the program being executed.
     *                         Each {@link String} in the {@link List} is a line
     *                         that was printed in standard error output.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    public ExecutionResponse(
            final ExecutionRequest executionRequest,
            final ExecutionResult result,
            final int exitCode,
            final List<String> stdout,
            final List<String> stderr) throws IllegalArgumentException {
        assertExecutionRequest(executionRequest);
        assertExecutionResult(result);
        assertStdOutList(stdout);
        assertStdErrList(stderr);
        assertRequestAndResult(executionRequest, result);
        this.executionRequest = executionRequest;
        this.result = result;
        this.exitCode = exitCode;
        this.stdout = stdout;
        this.stderr = stderr;
    }


    // ================================
    // Assertions
    // ================================

    /**
     * Asserts that the given {@code executionRequest} is valid.
     *
     * @param executionRequest The {@link ExecutionRequest} to be validated.
     * @throws IllegalArgumentException If the {@link ExecutionRequest} is not valid.
     */
    private static void assertExecutionRequest(final ExecutionRequest executionRequest)
            throws IllegalArgumentException {
        Assert.notNull(executionRequest, "The execution request must not be null");
    }

    /**
     * Asserts that the given {@code result} is valid.
     *
     * @param result The {@link ExecutionResult} to be validated.
     * @throws IllegalArgumentException If the {@link ExecutionResult} is not valid.
     */
    private static void assertExecutionResult(final ExecutionResult result) throws IllegalArgumentException {
        Assert.notNull(result, "The result must not be null");
    }

    /**
     * Asserts that the given {@code stdout} {@link List} is valid.
     *
     * @param stdout The {@link List} with standard output to be validated.
     * @throws IllegalArgumentException If the {@link List} is not valid.
     */
    private static void assertStdOutList(final List<String> stdout) throws IllegalArgumentException {
        Assert.notNull(stdout, "The stdout list must not be null");
        Assert.isTrue(stdout.stream().noneMatch(Objects::isNull), "The stdout list must not contain nulls.");
    }

    /**
     * Asserts that the given {@code stderr} {@link List} is valid.
     *
     * @param stderr The {@link List} with standard error output to be validated.
     * @throws IllegalArgumentException If the {@link List} is not valid.
     */
    private static void assertStdErrList(final List<String> stderr) throws IllegalArgumentException {
        Assert.notNull(stderr, "The stderr list must not be null");
        Assert.isTrue(stderr.stream().noneMatch(Objects::isNull), "The stderr list must not contain nulls.");
    }

    /**
     * Asserts that the given {@code request} and {@code result} are consistent.
     *
     * @param request The {@link ExecutionRequest} to be checked.
     * @param result  The {@link ExecutionResult} to be checked.
     * @throws IllegalArgumentException If both are value are not consistent together.
     */
    private static void assertRequestAndResult(final ExecutionRequest request, final ExecutionResult result)
            throws IllegalArgumentException {
        Assert.isTrue(
                result != ExecutionResult.COMPILE_ERROR || request.getLanguage().isCompiled(),
                "If the result is a compile error, the request's language must be compiled!"
        );
    }

    // ================================
    // Type
    // ================================

    public enum ExecutionResult {
        /**
         * Indicates that the execution process completed as expected.
         */
        COMPLETED,
        /**
         * Indicates that the execution timed-out (i.e the running phase).
         */
        TIMEOUT,
        /**
         * Indicates that the compilation phase failed (i.e the code could not be compiled).
         */
        COMPILE_ERROR,
        /**
         * Indicates that there was an error while initializing. This is an unexpected situation.
         */
        INITIALIZATION_ERROR,
        /**
         * Indicates that an unexpected error (different from the rest of this enum's error values) occurred.
         */
        UNKNOWN_ERROR,
        ;

        /**
         * Returns the {@link ExecutionResult} instance from the given {@code value}.
         *
         * @param value The {@link String} representing the {@link ExecutionResult} to be returned.
         * @return The {@link ExecutionResult} of the specified {@code value}.
         * @throws IllegalArgumentException If the given {@code value} is {@code null},
         *                                  or there is no {@link ExecutionResult}
         *                                  matching the given {@code value}.
         */
        public static ExecutionResult fromString(final String value) throws IllegalArgumentException {
            Assert.notNull(value, "The value must not be null");
            return valueOf(value.toUpperCase());
        }
    }
}
