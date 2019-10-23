package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an LTI authentication request.
 * This request is performed by the UA to an LMS
 * (through a redirect sent by this application after a login initiation request).
 * Check <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a> for more information.
 * This entity represents the following step:
 * <a href=https://www.imsglobal.org/spec/security/v1p0/#step-3-authentication-response>
 * IMS Security Framework, section 5.1.1.3: Step 3: Authentication Response</a>
 *
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-3-authentication-response>
 * IMS Security Framework, section 5.1.1.3: Step 3: Authentication Response</a>
 */
@Value
public class AuthenticationResponse {

    /**
     * The ID token that was sent by the LMS platform.
     */
    @NonNull
    private final String idToken;
    /**
     * The state that was sent in the authentication request.
     */
    @NonNull
    private final String state;
}
