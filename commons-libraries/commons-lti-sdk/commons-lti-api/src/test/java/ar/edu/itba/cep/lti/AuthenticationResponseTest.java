package ar.edu.itba.cep.lti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test class for {@link AuthenticationResponse}.
 */
class AuthenticationResponseTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of an {@link AuthenticationResponse} instance.
     */
    @Test
    void testCreation() {
        final var idToken = idToken();
        final var state = state();

        final var response = new AuthenticationResponse(idToken, state);

        Assertions.assertAll(
                "Creating an LtiAuthenticationResponse is not working as expected",
                () -> Assertions.assertEquals(idToken, response.getIdToken(), "The ID token does not match"),
                () -> Assertions.assertEquals(state, response.getState(), "State does not match")
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null ID token is not allowed when creating an {@link AuthenticationResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullIdToken() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationResponse(null, state()),
                "Creating an LtiAuthenticationResponse with a null ID token is being allowed"
        );
    }

    /**
     * Tests that a null client id is not allowed when creating an {@link AuthenticationResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullState() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationResponse(idToken(), null),
                "Creating an LtiAuthenticationResponse with a null state is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid deployment id.
     */
    private static String idToken() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid client id.
     */
    private static String state() {
        return UUID.randomUUID().toString();
    }
}
