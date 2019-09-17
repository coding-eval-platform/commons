package ar.edu.itba.cep.executor.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static ar.edu.itba.cep.executor.models.ExecutionResponse.ExecutionResult.COMPILE_ERROR;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link ExecutionResponse}
 */
@ExtendWith(MockitoExtension.class)
class ExecutionResponseTest {

    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests that the constructor does not fail when invoking it with valid arguments.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testConstructorWithValidArguments(@Mock(name = "request") final ExecutionRequest request) {
        final var result = TestHelper.validResult();
        final var compileError = result == COMPILE_ERROR;
        if (compileError) {
            when(request.getLanguage()).thenReturn(TestHelper.compiledLanguage());
        }
        Assertions.assertDoesNotThrow(
                () -> new ExecutionResponse(
                        request,
                        result,
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList()
                ),
                "An execution response is not being created with acceptable arguments."
        );

        if (compileError) {
            verify(request, only()).getLanguage();
        } else {
            verifyZeroInteractions(request);
        }
    }

    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} {@link ExecutionRequest}
     * throws an {@link IllegalArgumentException}.
     */
    @Test
    void testNullExecutionRequest() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        null,
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null execution request is being allowed."
        );
    }

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} {@link ExecutionRequest}
     * throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNullExecutionResult(@Mock(name = "request") final ExecutionRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        null,
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null execution result is being allowed."
        );
        verifyZeroInteractions(request);
    }

    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} {@code stdout} {@link List}
     * throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNullStdout(@Mock(name = "request") final ExecutionRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        null,
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null stdout list is being allowed."
        );
        verifyZeroInteractions(request);
    }


    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} element
     * in the {@code stdout} {@link List} throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNullElementInStdout(@Mock(name = "request") final ExecutionRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.inputOutputListWithNullElement(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null element in the stdout list is being allowed."
        );
        verifyZeroInteractions(request);
    }


    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} {@code stderr} {@link List}
     * throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNullStderr(@Mock(name = "request") final ExecutionRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        null
                ),
                "Creating an execution response with a null stderr list is being allowed."
        );
        verifyZeroInteractions(request);
    }


    /**
     * Tests that creating an {@link ExecutionResponse} with a {@code null} element
     * in the {@code stderr} {@link List} throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNullElementInStderr(@Mock(name = "request") final ExecutionRequest request) {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        TestHelper.validResult(),
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.inputOutputListWithNullElement()
                ),
                "Creating an execution response with a null element in the stderr list is being allowed."
        );
        verifyZeroInteractions(request);
    }

    /**
     * Tests that creating an {@link ExecutionResponse} with an
     * {@link ar.edu.itba.cep.executor.models.ExecutionResponse.ExecutionResult#COMPILE_ERROR},
     * when the {@link Language} of the {@link ExecutionRequest} is not compiled,
     * throws an {@link IllegalArgumentException}.
     *
     * @param request A mocked {@link ExecutionRequest} (the one owning the response).
     */
    @Test
    void testNonCompiledLanguageAndCompileError(@Mock(name = "request") final ExecutionRequest request) {
        when(request.getLanguage()).thenReturn(TestHelper.nonCompiledLanguage());
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new ExecutionResponse(
                        request,
                        COMPILE_ERROR,
                        TestHelper.validExitCode(),
                        TestHelper.validInputOutputList(),
                        TestHelper.validInputOutputList()
                ),
                "Creating an execution response with a null element in the stderr list is being allowed."
        );
        verify(request, only()).getLanguage();
    }
}
