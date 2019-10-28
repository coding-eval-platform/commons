package ar.edu.itba.cep.lti;

/**
 * Defines behaviour for objects that can perform an LTI login initiation process.
 */
public interface LtiLoginService {

    /**
     * Creates an {@link AuthenticationRequest} from the given {@link LoginInitiationRequest}.
     *
     * @param loginInitiationRequest The {@link LoginInitiationRequest} from where the data needed to create
     *                               the {@link AuthenticationRequest} is taken.
     * @return The created {@link AuthenticationRequest}.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
     * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-1-third-party-initiated-login>
     * IMS Security Framework, section 5.1.1.1: Step 1: Third-party Initiated Login</a>.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-2-authentication-request>
     * IMS Security Framework, section 5.1.1.2: Step 2: Authentication Request</a>
     */
    AuthenticationRequest loginInitiation(final LoginInitiationRequest loginInitiationRequest);
}
