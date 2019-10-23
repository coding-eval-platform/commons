package ar.edu.itba.cep.lti;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.util.UUID;

/**
 * Test class for {@link AuthenticationRequest}.
 */
class AuthenticationRequestTest {

    /**
     * The "fixed" part of the authentication request {@link URI}. This part of the {@link URI} is always the same
     * (it contains fixed values).
     * This is copied here to make sure that this is really included (if the reference is used, any change on it
     * won't make the test fail).
     */
    private static final String FIXED_PART = "&prompt=none&scope=openid&response_type=id_token&response_mode=form_post";

    // ================================================================================================================
    // The following constants are copied to make sure that they are really included
    // (if the reference is used, any change on it won't make the test fail).
    // ================================================================================================================

    private static final String PROMPT_VALUE = "none";
    private static final String SCOPE_VALUE = "openid";
    private static final String RESPONSE_TYPE_VALUE = "id_token";
    private static final String RESPONSE_MODE_VALUE = "form_post";


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of a {@link AuthenticationRequest} instance.
     */
    @Test
    void testCreation() {
        final var endpoint = endpoint();
        final var clientId = clientId();
        final var loginHint = loginHint();
        final var ltiMessageHint = ltiMessageHint();
        final var state = state();
        final var nonce = nonce();
        final var redirectUri = redirectUri();

        final var request = new AuthenticationRequest(
                endpoint, clientId, loginHint, redirectUri, nonce, ltiMessageHint, state
        );

        Assertions.assertAll(
                "The creation of an LtiAuthenticationRequest is not working as expected",
                () -> Assertions.assertEquals(endpoint, request.getEndpoint(), "The endpoint does not match"),
                () -> Assertions.assertEquals(clientId, request.getClientId(), "The client id does not match"),
                () -> Assertions.assertEquals(loginHint, request.getLoginHint(), "The login hint does not match"),
                () -> Assertions.assertEquals(ltiMessageHint, request.getLtiMessageHint(), "The lti message hint does not match"),
                () -> Assertions.assertEquals(state, request.getState(), "The state does not match"),
                () -> Assertions.assertEquals(nonce, request.getNonce(), "The nonce does not match"),
                () -> Assertions.assertEquals(redirectUri, request.getRedirectUri(), "The redirect uri does not match"),

                () -> Assertions.assertEquals(PROMPT_VALUE, request.getPrompt(), "The prompt is not the expected by LTI"),
                () -> Assertions.assertEquals(SCOPE_VALUE, request.getScope(), "The scope is not the expected by LTI"),
                () -> Assertions.assertEquals(RESPONSE_TYPE_VALUE, request.getResponseType(), "The response type is not the expected by LTI"),
                () -> Assertions.assertEquals(RESPONSE_MODE_VALUE, request.getResponseMode(), "The response mode is not the expected by LTI")
        );
    }

    /**
     * Tests that optional parameters of the {@link AuthenticationRequest} constructor are really optional
     * (i.e {@code null} can be used).
     */
    @Test
    void testOptionals() {
        final var endpoint = endpoint();
        final var clientId = clientId();
        final var loginHint = loginHint();
        final var redirectUrl = redirectUri();

        Assertions.assertDoesNotThrow(
                () -> new AuthenticationRequest(endpoint, clientId, loginHint, redirectUrl, nonce(), null, null),
                "Passing nulls to optional params is not being allowed"
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null endpoint is not allowed when creating an {@link AuthenticationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullEndpoint() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationRequest(
                        null, clientId(), loginHint(), redirectUri(), nonce(), ltiMessageHint(), state()
                ),
                "Creating an LtiAuthenticationRequest with a null \"endpoint\" is being allowed"
        );
    }

    /**
     * Tests that a null client id is not allowed when creating an {@link AuthenticationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullClientId() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationRequest(
                        endpoint(), null, loginHint(), redirectUri(), nonce(), ltiMessageHint(), state()
                ),
                "Creating an LtiAuthenticationRequest with a null \"client id\" is being allowed"
        );
    }

    /**
     * Tests that a null login hint is not allowed when creating an {@link AuthenticationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullLoginHint() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationRequest(
                        endpoint(), clientId(), null, redirectUri(), nonce(), ltiMessageHint(), state()
                ),
                "Creating an LtiAuthenticationRequest with a null \"login hint\" is being allowed"
        );
    }

    /**
     * Tests that a null redirect url is not allowed when creating an {@link AuthenticationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullRedirectUrl() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationRequest(
                        endpoint(), clientId(), loginHint(), null, nonce(), ltiMessageHint(), state()
                ),
                "Creating an LtiAuthenticationRequest with a null \"redirect uri\" is being allowed"
        );
    }

    /**
     * Tests that a null nonce is not allowed when creating an {@link AuthenticationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullNonce() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new AuthenticationRequest(
                        endpoint(), clientId(), loginHint(), redirectUri(), null, ltiMessageHint(), state()
                ),
                "Creating an LtiAuthenticationRequest with a null \"nonce\" is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * Validates that the given {@code uri} has the correct endpoint.
     *
     * @param uri      The {@link URI} to be validated.
     * @param endpoint The endpoint.
     */
    private static void validateEndpoint(final URI uri, final String endpoint) {
        Assertions.assertEquals(
                endpoint,
                uri.getScheme() + "://" + uri.getAuthority() + uri.getPath(),
                "The endpoint of the URI created by the LtiAuthenticationRequest is not the expected"
        );
    }

    /**
     * Validates that the required params needed by LTI in the given {@code uri} are present.
     *
     * @param uri         The {@link URI} to be validated.
     * @param clientId    The client id.
     * @param loginHint   The login hint.
     * @param redirectUri The redirect url.
     */
    private static void validateRequiredParams(
            final URI uri,
            final String clientId,
            final String loginHint,
            final String redirectUri) {
        final var queryString = uri.getQuery();
        Assertions.assertAll(
                "There are missing required params in the URI created by the LtiAuthenticationRequest",
                () -> Assertions.assertTrue(
                        queryString.contains("client_id=" + clientId),
                        "The client id is missing in the query string"
                ),
                () -> Assertions.assertTrue(
                        queryString.contains("login_hint=" + loginHint),
                        "The login hint is missing in the query string"
                ),
                () -> Assertions.assertTrue(
                        queryString.contains("redirect_uri=" + redirectUri),
                        "The redirect url is missing in the query string"
                ),
                () -> Assertions.assertTrue(
                        queryString.matches(".*nonce=.+.*"),
                        "The nonce is missing in the query string"
                ),
                () -> Assertions.assertTrue(
                        uri.getQuery().contains(FIXED_PART),
                        "The query string does not contain the fixed part required by LTI (\"" + FIXED_PART + "\")"
                )
        );
    }


    /**
     * @return A valid endpoint.
     */
    private static String endpoint() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid client id.
     */
    private static String clientId() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid login hint.
     */
    private static String loginHint() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid LTI message hint.
     */
    private static String ltiMessageHint() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid state.
     */
    private static String state() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid nonce.
     */
    private static String nonce() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid redirect uri.
     */
    private static String redirectUri() {
        return UUID.randomUUID().toString();
    }
}
