package ar.edu.itba.cep.executor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test class for {@link ExecutionResponse}
 */
class ExecutionResponseTest {

    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests that the constructor does not fail when invoking it with valid arguments.
     */
    @Test
    void testConstructorWithValidArguments() {
        final var result = TestHelper.validResult();
        final var exitCode = TestHelper.validExitCode();
        final var stdout = TestHelper.validInputOutputList();
        final var stderr = TestHelper.validInputOutputList();

        final var response = new ExecutionResponse(result, exitCode, stdout, stderr);

        Assertions.assertAll(
                "Creating an execution response is not working as expected",
                () -> Assertions.assertEquals(
                        result,
                        response.getResult(),
                        "There is a mismatch in the result"
                ),
                () -> Assertions.assertEquals(
                        exitCode,
                        response.getExitCode(),
                        "There is a mismatch in the exit code"
                ),
                () -> Assertions.assertEquals(
                        stdout,
                        response.getStdout(),
                        "There is a mismatch in the stdout list"
                ),
                () -> Assertions.assertEquals(
                        stderr,
                        response.getStderr(),
                        "There is a mismatch in the stderr list"
                )
        );
    }

    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} {@link ExecutionRequest}
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullExecutionResult() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        null,
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null execution result is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} element
     * in the {@code stdout} {@link List} throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullElementInStdout() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.inputOutputListWithNullElement(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null element in the stdout list is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} element
     * in the {@code stderr} {@link List} throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullElementInStderr() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.inputOutputListWithNullElement()
                ),
                "Creating an execution response with a null element in the stderr list is being allowed."
        );
    }
}
