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
 * <a href=https://www.imsglobal.org/spec/security/v1p0/#step-2-authentication-request>
 * IMS Security Framework, section 5.1.1.2: Step 2: Authentication Request</a>
 *
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-2-authentication-request>
 * IMS Security Framework, section 5.1.1.2: Step 2: Authentication Request</a>.
 */
@Value
public class AuthenticationRequest {

    /**
     * The prompt value required by LTI.
     */
    private static final String PROMPT_VALUE = "none";
    /**
     * The scope value required by LTI.
     */
    private static final String SCOPE_VALUE = "openid";
    /**
     * The response type value required by LTI.
     */
    private static final String RESPONSE_TYPE_VALUE = "id_token";
    /**
     * The response mode value required by LTI.
     */
    private static final String RESPONSE_MODE_VALUE = "form_post";


    // ================================
    // Properties
    // ================================


    /**
     * The endpoint to which the request must be sent.
     */
    @NonNull
    private final String endpoint;

    /**
     * The client id used to perform the LTI authentication request.
     */
    @NonNull
    private final String clientId;
    /**
     * The login hint sent by the LMS platform in the login initiation request.
     */
    @NonNull
    private final String loginHint;
    /**
     * The uri to which the user must be redirected at the end of the Open-ID connect flow.
     */
    @NonNull
    private final String redirectUri;
    /**
     * A nonce required by the LTI spec to associate a Client session with an ID Token, and to mitigate replay attacks.
     */
    @NonNull
    private final String nonce;

    /**
     * The LTI message hint sent by the LMS platform in the login initiation request.
     */
    private final String ltiMessageHint;
    /**
     * An opaque value for the platform to maintain state between the LTI authentication request and the callback
     * (the access to the redirect uri), and to provide Cross-Site Request Forgery (CSRF) mitigation.
     */
    private final String state;

    /**
     * The prompt required by LTI.
     */
    private final String prompt = PROMPT_VALUE;
    /**
     * The scope required by LTI.
     */
    private final String scope = SCOPE_VALUE;
    /**
     * The response type required by LTI.
     */
    private final String responseType = RESPONSE_TYPE_VALUE;
    /**
     * The response mode required by LTI.
     */
    private final String responseMode = RESPONSE_MODE_VALUE;
}
