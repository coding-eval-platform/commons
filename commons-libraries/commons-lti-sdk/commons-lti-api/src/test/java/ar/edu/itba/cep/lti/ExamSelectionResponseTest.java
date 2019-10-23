package ar.edu.itba.cep.lti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test class for {@link ExamSelectionResponse}.
 */
class ExamSelectionResponseTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of an {@link ExamSelectionResponse} instance.
     */
    @Test
    void testCreation() {
        final var state = state();

        final var response = new ExamSelectionResponse(state);

        Assertions.assertAll(
                "Creating an ExamSelectionResponse is not working as expected",
                () -> Assertions.assertEquals(state, response.getState(), "State does not match")
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null client id is not allowed when creating an {@link ExamSelectionResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullState() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamSelectionResponse(null),
                "Creating an ExamSelectionResponse with a null state is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid client id.
     */
    private static String state() {
        return UUID.randomUUID().toString();
    }
}
