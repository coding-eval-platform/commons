package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an LTI login initiation request.
 * This request is performed by the UA to this application
 * (through a redirect sent by the LMS after the user starts an LTI tool from the said LMS).
 * Check <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a> for more information.
 * This entity represents the following step:
 * <a href=https://www.imsglobal.org/spec/security/v1p0/#step-1-third-party-initiated-login>
 * IMS Security Framework, section 5.1.1.1: Step 1: Third-party Initiated Login</a>.
 *
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-1-third-party-initiated-login>
 * IMS Security Framework, section 5.1.1.1: Step 1: Third-party Initiated Login</a>.
 */
@Value
public class LoginInitiationRequest {

    /**
     * The issuing authority (identifies the LMS).
     */
    @NonNull
    private final String issuer;
    /**
     * Hint needed by the LMS.
     */
    @NonNull
    private final String loginHint;
    /**
     * The actual end-point that should be executed at the end of the authentication flow.
     */
    @NonNull
    private final String targetLinkUri;

    /**
     * An optional field used alongside the {@code loginHint} by the LMS
     * to carry information about the received LTI message.
     */
    private final String ltiMessageHint;
    /**
     * An optional field used to identify a specific LTI tool deployment.
     */
    private final String deploymentId;
    /**
     * An optional field used to identify a specific LTI tool deployment.
     */
    private final String clientId;
}
