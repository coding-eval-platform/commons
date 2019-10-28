package ar.edu.itba.cep.lti;

/**
 * Defines behaviour for objects that can perform the exam selection process,
 * starting by the LTI authentication response handling.
 */
public interface LtiExamSelectionService {

    /**
     * Performs the exam selection initiation step of the Deep Linking flow.
     * This is done through the authentication response step of the LTI Open-ID Connect Launch flow.
     *
     * @param authenticationResponse The received {@link AuthenticationResponse}.
     * @return The {@link ExamSelectionResponse} with the needed data by the user in order to select the exam
     * to be registered.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
     * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-3-authentication-response>
     * IMS Security Framework, section 5.1.1.3: Step 3: Authentication Response</a>
     * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-4-resource-is-displayed>
     * IMS Security Framework, section 5.1.1.4: Step 4: Resource is displayed</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#workflow>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2: Workflow</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#redirection-from-platform-to-tool>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2.1:
     * Redirection from platform to tool</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#tool-user-experience-for-deep-linking>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2.2:
     * Tool user experience for deep linking</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#lti-deep-linking-interaction>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 3: LTI Deep Linking interaction</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#deep-linking-request-message>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 3.4: Deep linking request message</a>
     */
    ExamSelectionResponse examSelection(final AuthenticationResponse authenticationResponse);

    /**
     * Handles the exam selected step of the Deep Linking flow.
     *
     * @param examSelectedRequest The received {@link ExamSelectedRequest}.
     * @return The {@link ExistingExamSelectedResponse} with the needed data by the user to submit the content to the LMS.
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#workflow>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2: Workflow</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#tool-user-experience-for-deep-linking>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2.2:
     * Tool user experience for deep linking</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#tool-user-experience-for-deep-linking>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2.2:
     * Tool user experience for deep linking</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#redirection-back-to-the-platform>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 1.2.3:
     * Redirection back to the platform</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#lti-deep-linking-interaction>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 3: LTI Deep Linking interaction</a>
     * @see <a href=https://www.imsglobal.org/spec/lti-dl/v2p0#deep-linking-response-message>
     * Learning Tools Interoperability (LTI) Deep Linking Specification, section 3.5: Deep linking response message</a>
     */
    ExamSelectedResponse examSelected(final ExamSelectedRequest examSelectedRequest);
}
