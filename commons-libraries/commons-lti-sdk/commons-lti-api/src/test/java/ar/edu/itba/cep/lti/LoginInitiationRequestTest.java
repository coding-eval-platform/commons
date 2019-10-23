package ar.edu.itba.cep.lti;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test class for {@link LoginInitiationRequest}
 */
class LoginInitiationRequestTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of a {@link LoginInitiationRequest} instance.
     */
    @Test
    void testCreation() {
        final var issuer = issuer();
        final var loginHint = loginHint();
        final var targetLinkUri = targetLinkUri();
        final var ltiMessageHint = ltiMessageHint();
        final var deploymentId = deploymentId();
        final var clientId = clientId();


        final var ltiLoginInitiationRequest = new LoginInitiationRequest(
                issuer,
                loginHint,
                targetLinkUri,
                ltiMessageHint,
                deploymentId,
                clientId
        );

        Assertions.assertAll(
                "Creating an LtiLoginInitiationRequest is not working as expected",
                () -> Assertions.assertEquals(
                        issuer,
                        ltiLoginInitiationRequest.getIssuer(),
                        "The issuer does not match"
                ),
                () -> Assertions.assertEquals(
                        loginHint,
                        ltiLoginInitiationRequest.getLoginHint(),
                        "The login hint does not match"
                ),
                () -> Assertions.assertEquals(
                        targetLinkUri,
                        ltiLoginInitiationRequest.getTargetLinkUri(),
                        "The target link uri does not match"
                ),
                () -> Assertions.assertEquals(
                        ltiMessageHint,
                        ltiLoginInitiationRequest.getLtiMessageHint(),
                        "The LTI message hint does not match"
                ),
                () -> Assertions.assertEquals(
                        deploymentId,
                        ltiLoginInitiationRequest.getDeploymentId(),
                        "The deployment id does not match"
                ),
                () -> Assertions.assertEquals(
                        clientId,
                        ltiLoginInitiationRequest.getClientId(),
                        "The client id does not match"
                )
        );
    }

    /**
     * Tests that optional parameters of the {@link LoginInitiationRequestTest} constructor are really optional
     * (i.e {@code null} can be used).
     */
    @Test
    void testOptionals() {
        Assertions.assertDoesNotThrow(
                () -> new LoginInitiationRequest(issuer(), loginHint(), targetLinkUri(), null, null, null),
                "Creating an LtiLoginInitiationRequest passing null to optional parameters is not being allowed"
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null issuer is not allowed when creating an {@link LoginInitiationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullIssuer() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new LoginInitiationRequest(
                        null, loginHint(), targetLinkUri(), ltiMessageHint(), deploymentId(), clientId()
                ),
                "Creating an LtiLoginInitiationRequest with a null \"issuer\" is being allowed"
        );
    }

    /**
     * Tests that a null login hint is not allowed when creating an {@link LoginInitiationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullLoginHint() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new LoginInitiationRequest(
                        issuer(), null, targetLinkUri(), ltiMessageHint(), deploymentId(), clientId()
                ),
                "Creating an LtiLoginInitiationRequest with a null \"login hint\" is being allowed"
        );
    }

    /**
     * Tests that a null target link uri is not allowed when creating an {@link LoginInitiationRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullTargetLinkUri() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new LoginInitiationRequest(
                        issuer(), loginHint(), null, ltiMessageHint(), deploymentId(), clientId()
                ),
                "Creating an LtiLoginInitiationRequest with a null \"target link uri\" is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid issuer.
     */
    private static String issuer() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid login hint.
     */
    private static String loginHint() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid target link uri.
     */
    private static String targetLinkUri() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid LTI message hint.
     */
    private static String ltiMessageHint() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid deployment id.
     */
    private static String deploymentId() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid client id.
     */
    private static String clientId() {
        return UUID.randomUUID().toString();
    }
}
