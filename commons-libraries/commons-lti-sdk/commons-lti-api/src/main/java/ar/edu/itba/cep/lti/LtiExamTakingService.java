package ar.edu.itba.cep.lti;

/**
 * Defines behaviour for objects that can perform the exam taking process,
 * which is performed by the LTI authentication response handling.
 */
public interface LtiExamTakingService {

    /**
     * Handles the exam taking request.
     * This is done through the authentication response step of the LTI Open-ID Connect Launch flow.
     *
     * @param authenticationResponse The received {@link AuthenticationResponse}.
     * @return The {@link ExamTakingResponse} with the needed data by the user to take an exam.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
     * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-3-authentication-response>
     * IMS Security Framework, section 5.1.1.3: Step 3: Authentication Response</a>
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-4-resource-is-displayed>
     * IMS Security Framework, section 5.1.1.4: Step 4: Resource is displayed</a>
     * @see <a href=https://www.imsglobal.org/spec/lti/v1p3#resource-link-launch-request-message>
     * IMS Learning Tools Interoperability® Core Specification, section 5: Resource link launch request message</a>
     */
    ExamTakingResponse takeExam(final AuthenticationResponse authenticationResponse);
}
