package ar.edu.itba.cep.executor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test class for {@link ExecutionRequest}.
 */
class ExecutionRequestTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests that the constructor does not fail when invoking it with valid arguments.
     */
    @Test
    void testConstructorWithValidArguments() {
        final var code = TestHelper.validCode();
        final var programArguments = TestHelper.validInputOutputList();
        final var stdin = TestHelper.validInputOutputList();
        final var compilerFlags = TestHelper.validCompilerFlags();
        final var timeout = TestHelper.validTimeout();
        final var language = TestHelper.validLanguage();

        final var request = new ExecutionRequest(code, programArguments, stdin, compilerFlags, timeout, language);

        Assertions.assertAll(
                "Creating an execution request is not working as expected",
                () -> Assertions.assertEquals(
                        code,
                        request.getCode(),
                        "There is a mismatch in the code"
                ),
                () -> Assertions.assertEquals(
                        programArguments,
                        request.getProgramArguments(),
                        "There is a mismatch in the program arguments"
                ),
                () -> Assertions.assertEquals(
                        stdin,
                        request.getStdin(),
                        "There is a mismatch in the stdin list"
                ),
                () -> Assertions.assertEquals(
                        compilerFlags,
                        request.getCompilerFlags(),
                        "There is a mismatch in the compiler flags"
                ),
                () -> Assertions.assertEquals(
                        timeout,
                        request.getTimeout(),
                        "There is a mismatch in the timeout"
                ),
                () -> Assertions.assertEquals(
                        language,
                        request.getLanguage(),
                        "There is a mismatch in the language"
                )
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that creating an {@link ExecutionRequest} with a {@code null} {@code code} {@link String}
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullCode() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionRequest(
                        null,
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validCompilerFlags(),
                        TestHelper.validTimeout(),
                        TestHelper.validLanguage()
                ),
                "Creating an execution request with a null code is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionRequest} with a {@code null} element
     * in the {@code programArguments} {@link List} throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullElementInProgramArgumentsList() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionRequest(
                        TestHelper.validCode(),
                        TestHelper.inputOutputListWithNullElement(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validCompilerFlags(),
                        TestHelper.validTimeout(),
                        TestHelper.validLanguage()
                ),
                "Creating an execution request with a null element in the inputs list is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionRequest} with a {@code null} element
     * in the {@code stdin} {@link List} throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullElementInStdinList() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionRequest(
                        TestHelper.validCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.inputOutputListWithNullElement(),
                        TestHelper.validCompilerFlags(),
                        TestHelper.validTimeout(),
                        TestHelper.validLanguage()
                ),
                "Creating an execution request with a null element in the inputs list is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionRequest} with a non positive timeout
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNonPositiveTimeout() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ExecutionRequest(
                        TestHelper.validCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validCompilerFlags(),
                        TestHelper.nonPositiveTimeout(),
                        TestHelper.validLanguage()
                ),
                "Creating an execution request with a null language is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionRequest} with a {@code null} {@link Language}
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullLanguage() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new ExecutionRequest(
                        TestHelper.validCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validCompilerFlags(),
                        TestHelper.validTimeout(),
                        null
                ),
                "Creating an execution request with a null language is being allowed."
        );
    }
}
