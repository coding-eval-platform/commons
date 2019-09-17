package ar.edu.itba.cep.executor.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents an execution request.
 */
@Getter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
public class ExecutionRequest {

    /**
     * The code to be run.
     */
    private final String code;
    /**
     * The program arguments to be passed to the execution.
     */
    private final List<String> programArguments;
    /**
     * The elements to be passed to the standard input.
     */
    private final List<String> stdin;
    /**
     * The time given to execute, in milliseconds.
     */
    private final Long timeout;
    /**
     * The programming language in which the {@link #code} is written.
     */
    private final Language language;


    /**
     * Constructor.
     *
     * @param code             The code to be run.
     * @param programArguments The input arguments to be passed to the execution.
     * @param stdin            The elements to be passed to the standard input.
     * @param timeout          The time given to execute, in milliseconds.
     * @param language         The programming language in which the {@code code} is written.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    public ExecutionRequest(
            final String code,
            final List<String> programArguments,
            final List<String> stdin,
            final Long timeout,
            final Language language)
            throws IllegalArgumentException {
        assertCode(code);
        assertProgramArguments(programArguments);
        assertStdin(stdin);
        assertTimeout(timeout);
        assertLanguage(language);
        this.code = code;
        this.programArguments = Collections.unmodifiableList(programArguments);
        this.stdin = Collections.unmodifiableList(stdin);
        this.timeout = timeout;
        this.language = language;
    }


    // ================================
    // Assertions
    // ================================

    /**
     * Asserts that the given {@code code} is valid.
     *
     * @param code The code to be checked.
     * @throws IllegalArgumentException If the {@code code} is not valid.
     */
    private static void assertCode(final String code) throws IllegalArgumentException {
        Assert.notNull(code, "The code must not be null.");
    }

    /**
     * Asserts that the given {@code programArguments} {@link List} is valid.
     *
     * @param programArguments The programArguments {@link List} to be checked.
     * @throws IllegalArgumentException If the {@code programArguments} {@link List} is not valid.
     */
    private static void assertProgramArguments(final List<String> programArguments) throws IllegalArgumentException {
        Assert.notNull(programArguments, "The programArguments list must not be null");
        Assert.isTrue(
                programArguments.stream().noneMatch(Objects::isNull),
                "The program arguments list must not contain nulls."
        );
    }

    /**
     * Asserts that the given {@code stdin} {@link List} is valid.
     *
     * @param stdin The stdin {@link List} to be checked.
     * @throws IllegalArgumentException If the {@code stdin} {@link List} is not valid.
     */
    private static void assertStdin(final List<String> stdin) throws IllegalArgumentException {
        Assert.notNull(stdin, "The stdin list must not be null");
        Assert.isTrue(
                stdin.stream().noneMatch(Objects::isNull),
                "The stdin list must not contain nulls."
        );
    }

    /**
     * Asserts that the given {@code timeout} is valid.
     *
     * @param timeout The timeout to be checked.
     * @throws IllegalArgumentException If the {@code timeout} is not valid.
     */
    private static void assertTimeout(final Long timeout) throws IllegalArgumentException {
        Assert.isTrue(timeout == null || timeout > 0, "The timeout must be null or positive");
    }

    /**
     * Asserts that the given {@code language} is valid.
     *
     * @param language The {@link Language} to be checked.
     * @throws IllegalArgumentException If the {@code language} is not valid.
     */
    private static void assertLanguage(final Language language) throws IllegalArgumentException {
        Assert.notNull(language, "The language must not be null");
    }
}
