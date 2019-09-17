package ar.edu.itba.cep.executor.models;

import lombok.*;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Represents an execution response.
 * In includes a no-args constructor with protected visibility
 * to allow persistence of objects of this type (or subclasses) using JPA.
 */
@Getter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ExecutionResponse {

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
     * @param result   The result of the execution (e.g finished, timed-out, or with compilation errors).
     * @param exitCode The execution's exit code.
     * @param stdout   A {@link List} of {@link String}s
     *                 that were sent to standard output by the program being executed.
     *                 Each {@link String} in the {@link List} is a line that was printed in standard output.
     * @param stderr   A {@link List} of {@link String}s
     *                 that were sent to standard error output by the program being executed.
     *                 Each {@link String} in the {@link List} is a line
     *                 that was printed in standard error output.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    public ExecutionResponse(
            final ExecutionResult result,
            final int exitCode,
            final List<String> stdout,
            final List<String> stderr) throws IllegalArgumentException {
        assertExecutionResult(result);
        assertStdOutList(stdout);
        assertStdErrList(stderr);
        this.result = result;
        this.exitCode = exitCode;
        this.stdout = Optional.ofNullable(stdout).map(Collections::unmodifiableList).orElseGet(LinkedList::new);
        this.stderr = Optional.ofNullable(stderr).map(Collections::unmodifiableList).orElseGet(LinkedList::new);
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
        Assert.isTrue(
                Objects.isNull(stdout) || stdout.stream().noneMatch(Objects::isNull),
                "If present, the stdout list must not contain nulls."
        );
    }

    /**
     * Asserts that the given {@code stderr} {@link List} is valid.
     *
     * @param stderr The {@link List} with standard error output to be validated.
     * @throws IllegalArgumentException If the {@link List} is not valid.
     */
    private static void assertStdErrList(final List<String> stderr) throws IllegalArgumentException {
        Assert.isTrue(
                Objects.isNull(stderr) || stderr.stream().noneMatch(Objects::isNull),
                "If present, the stderr list must not contain nulls."
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
