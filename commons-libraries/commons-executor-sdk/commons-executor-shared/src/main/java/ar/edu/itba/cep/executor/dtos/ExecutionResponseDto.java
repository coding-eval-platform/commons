package ar.edu.itba.cep.executor.dtos;

import ar.edu.itba.cep.executor.models.ExecutionResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

/**
 * Data transfer object for receiving {@link ExecutionResponse}s.
 */
@Getter
@AllArgsConstructor(staticName = "buildFromResponse")
@ToString(doNotUseGetters = true, of = "executionResponse")
@EqualsAndHashCode(doNotUseGetters = true, of = "executionResponse")
public class ExecutionResponseDto {

    /**
     * The result field (stored here to avoid mistakes of field naming).
     */
    private static final String RESULT_FIELD = "result";
    /**
     * The exit code field (stored here to avoid mistakes of field naming).
     */
    private static final String EXIT_CODE_FIELD = "exitCode";
    /**
     * The stdout field (stored here to avoid mistakes of field naming).
     */
    private static final String STDOUT_FIELD = "stdout";
    /**
     * The stderr field (stored here to avoid mistakes of field naming).
     */
    private static final String STDERR_FIELD = "stderr";


    /**
     * The {@link ExecutionResponse} being wrapped by this DTO.
     */
    @JsonIgnore
    private final ExecutionResponse executionResponse;


    /**
     * @return The execution's result.
     */
    @JsonProperty(value = RESULT_FIELD, access = READ_ONLY)
    public ExecutionResponse.ExecutionResult getResult() {
        return executionResponse.getResult();
    }

    /**
     * @return The execution's exit code.
     */
    @JsonProperty(value = EXIT_CODE_FIELD, access = READ_ONLY)
    public int getExitCode() {
        return executionResponse.getExitCode();
    }

    /**
     * @return A {@link List} of {@link String}s that were sent to standard output by the program being executed.
     * Each {@link String} in the {@link List} is a line that was printed in standard output.
     */
    @JsonProperty(value = STDOUT_FIELD, access = READ_ONLY)
    public List<String> getStdout() {
        return executionResponse.getStdout();
    }

    /**
     * @return A {@link List} of {@link String}s that were sent to standard error output by the program being executed.
     * Each {@link String} in the {@link List} is a line that was printed in standard error output.
     */
    @JsonProperty(value = STDERR_FIELD, access = READ_ONLY)
    public List<String> getStderr() {
        return executionResponse.getStderr();
    }


    /**
     * Creates an {@link ExecutionResponseDto} from the given properties. This method is intended to be used
     * to create a DTO to receive data.
     *
     * @param result   The result of the execution (e.g finished, timed-out, or with compilation errors).
     * @param exitCode The execution's exit code.
     *                 * @param stdout   A {@link List} of {@link String}s
     *                 that were sent to standard output by the program being executed.
     *                 Each {@link String} in the {@link List} is a line that was printed in standard output.
     * @param stdout   A {@link List} of {@link String}s
     *                 that were sent to standard output by the program being executed.
     *                 Each {@link String} in the {@link List} is a line that was printed in standard output.
     * @param stderr   A {@link List} of {@link String}s
     *                 that were sent to standard error output by the program being executed.
     *                 Each {@link String} in the {@link List} is a line
     *                 that was printed in standard error output.
     * @return The created {@link ExecutionResponseDto}.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    @JsonCreator
    public static ExecutionResponseDto buildFromProperties(
            @JsonProperty(value = RESULT_FIELD, access = WRITE_ONLY) final ExecutionResponse.ExecutionResult result,
            @JsonProperty(value = EXIT_CODE_FIELD, access = WRITE_ONLY) final int exitCode,
            @JsonProperty(value = STDOUT_FIELD, access = WRITE_ONLY) final List<String> stdout,
            @JsonProperty(value = STDERR_FIELD, access = WRITE_ONLY) final List<String> stderr) {
        return buildFromResponse(new ExecutionResponse(result, exitCode, stdout, stderr));
    }
}
