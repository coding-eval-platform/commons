package ar.edu.itba.cep.executor.models;

import lombok.*;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Represents an execution request.
 * In includes a no-args constructor with protected visibility
 * to allow persistence of objects of this type (or subclasses) using JPA.
 */
@Getter
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(doNotUseGetters = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
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
     * The compiler flags. Should be null if the {@link #language} is compiled.
     */
    private final String compilerFlags;
    /**
     * The time given to execute, in milliseconds.
     */
    private final Long timeout;
    /**
     * The name of the file in which the "main" will be placed (i.e the name of the file where the code will be copied).
     */
    private final String mainFileName;
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
     * @param compilerFlags    The compiler flags. Should null if the {@code language} is compiled.
     * @param timeout          The time given to execute, in milliseconds.
     * @param mainFileName     The name of the file in which the "main" will be placed
     *                         (i.e the name of the file where the code will be copied).
     * @param language         The programming language in which the {@code code} is written.
     * @throws IllegalArgumentException If any argument is not valid.
     */
    public ExecutionRequest(
            final String code,
            final List<String> programArguments,
            final List<String> stdin,
            final String compilerFlags,
            final Long timeout,
            final String mainFileName,
            final Language language)
            throws IllegalArgumentException {
        assertCode(code);
        assertProgramArguments(programArguments);
        assertStdin(stdin);
        assertTimeout(timeout);
        assertLanguage(language);
        this.code = code;
        this.programArguments = Optional.ofNullable(programArguments)
                .map(Collections::unmodifiableList)
                .orElseGet(LinkedList::new);
        this.stdin = Optional.ofNullable(stdin)
                .map(Collections::unmodifiableList)
                .orElseGet(LinkedList::new);
        this.compilerFlags = compilerFlags;
        this.timeout = timeout;
        this.mainFileName = mainFileName;
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
        Assert.isTrue(
                Objects.isNull(programArguments) || programArguments.stream().noneMatch(Objects::isNull),
                "If present, the program arguments list must not contain nulls."
        );
    }

    /**
     * Asserts that the given {@code stdin} {@link List} is valid.
     *
     * @param stdin The stdin {@link List} to be checked.
     * @throws IllegalArgumentException If the {@code stdin} {@link List} is not valid.
     */
    private static void assertStdin(final List<String> stdin) throws IllegalArgumentException {
        Assert.isTrue(
                Objects.isNull(stdin) || stdin.stream().noneMatch(Objects::isNull),
                "If present, the stdin list must not contain nulls."
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
