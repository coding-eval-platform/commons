package ar.edu.itba.cep.executor.dtos;

import ar.edu.itba.cep.executor.models.ExecutionRequest;
import ar.edu.itba.cep.executor.models.Language;
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
 * Data transfer object for sending execution requests.
 */
@Getter
@AllArgsConstructor(staticName = "buildFromRequest")
@ToString(doNotUseGetters = true, of = "executionRequest")
@EqualsAndHashCode(doNotUseGetters = true, of = "executionRequest")
public class ExecutionRequestDto {

    /**
     * The code field (stored here to avoid mistakes of field naming).
     */
    private static final String CODE_FIELD = "code";
    /**
     * The program arguments field (stored here to avoid mistakes of field naming).
     */
    private static final String PROGRAM_ARGUMENTS_FIELD = "programArguments";
    /**
     * The stdin field (stored here to avoid mistakes of field naming).
     */
    private static final String STDIN_FIELD = "stdin";
    /**
     * The compiler flags field (stored here to avoid mistakes of field naming).
     */
    private static final String COMPILER_FLAGS_FIELD = "compilerFlags";
    /**
     * The timeout field (stored here to avoid mistakes of field naming).
     */
    private static final String TIMEOUT_FIELD = "timeout";
    /**
     * The main file name field (stored here to avoid mistakes of field naming).
     */
    private static final String MAIN_FILE_NAME_FIELD = "mainFileName";
    /**
     * The language field (stored here to avoid mistakes of field naming).
     */
    private static final String LANGUAGE_FIELD = "language";


    /**
     * The {@link ExecutionRequest} being wrapped.
     */
    @JsonIgnore
    private final ExecutionRequest executionRequest;


    /**
     * @return The code to be run.
     */
    @JsonProperty(value = CODE_FIELD, access = READ_ONLY)
    public String getCode() {
        return executionRequest.getCode();
    }

    /**
     * @return The program arguments to be passed to the execution.
     */
    @JsonProperty(value = PROGRAM_ARGUMENTS_FIELD, access = READ_ONLY)
    public List<String> getProgramArguments() {
        return executionRequest.getProgramArguments();
    }

    /**
     * @return The standard input to be passed to the execution.
     */
    @JsonProperty(value = STDIN_FIELD, access = READ_ONLY)
    public List<String> getStdin() {
        return executionRequest.getStdin();
    }

    /**
     * @return The compiler flags. Should be null if the {@code language} is compiled.
     */
    @JsonProperty(value = COMPILER_FLAGS_FIELD, access = READ_ONLY)
    public String getCompilerFlags() {
        return executionRequest.getCompilerFlags();
    }

    /**
     * @return The time given to execute, in milliseconds.
     */
    @JsonProperty(value = TIMEOUT_FIELD, access = READ_ONLY)
    public Long getTimeout() {
        return executionRequest.getTimeout();
    }

    /**
     * @return The name of the file in which the "main" will be placed
     * (i.e the name of the file where the code will be copied).
     */
    @JsonProperty(value = MAIN_FILE_NAME_FIELD, access = READ_ONLY)
    public String getMainFileName() {
        return executionRequest.getMainFileName();
    }

    /**
     * @return The programming language in which the {@link #getCode()} is written.
     */
    @JsonProperty(value = LANGUAGE_FIELD, access = READ_ONLY)
    public Language getLanguage() {
        return executionRequest.getLanguage();
    }


    /**
     * Creates an {@link ExecutionRequestDto} from the given properties. This method is intended to be used
     * to create a DTO to receive data.
     *
     * @param code             The code to be run.
     * @param programArguments The program arguments to be passed to the execution.
     * @param stdin            The standard input to be passed to the execution.
     * @param compilerFlags    The compiler flags. Should be null if the {@code language} is compiled.
     * @param timeout          The time given to execute, in milliseconds.
     * @param mainFileName     The name of the file in which the "main" will be placed
     *                         (i.e the name of the file where the code will be copied).
     * @param language         The programming language in which the {@code code} is written.
     * @return The created {@link ExecutionRequestDto}.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    @JsonCreator
    public static ExecutionRequestDto buildFromProperties(
            @JsonProperty(value = CODE_FIELD, access = WRITE_ONLY) final String code,
            @JsonProperty(value = PROGRAM_ARGUMENTS_FIELD, access = WRITE_ONLY) final List<String> programArguments,
            @JsonProperty(value = STDIN_FIELD, access = WRITE_ONLY) final List<String> stdin,
            @JsonProperty(value = COMPILER_FLAGS_FIELD, access = WRITE_ONLY) final String compilerFlags,
            @JsonProperty(value = TIMEOUT_FIELD, access = WRITE_ONLY) final Long timeout,
            @JsonProperty(value = MAIN_FILE_NAME_FIELD, access = WRITE_ONLY) final String mainFileName,
            @JsonProperty(value = LANGUAGE_FIELD, access = WRITE_ONLY) final Language language) {
        return buildFromRequest(
                new ExecutionRequest(code, programArguments, stdin, compilerFlags, timeout, mainFileName, language)
        );
    }
}
