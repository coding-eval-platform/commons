package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an LTI content (i.e an url to the said content).
 * This is the final step in the LTI authentication flow
 * Check <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a> for more information.
 * This entity represents the following step:
 * <a href=https://www.imsglobal.org/spec/security/v1p0/#step-4-resource-is-displayed>
 * IMS Security Framework, section 5.1.1.4: Step 4: Resource is displayed</a>
 *
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#openid_connect_launch_flow>IMS Security Framework,
 * section 5.1.1: OpenID Connect Launch Flow Overview</a>.
 * @see <a href=https://www.imsglobal.org/spec/security/v1p0/#step-4-resource-is-displayed>
 * IMS Security Framework, section 5.1.1.4: Step 4: Resource is displayed</a>
 */
@Value
public class ExamSelectionResponse {

    /**
     * The state that must be resent after an exam is selected.
     */
    @NonNull
    private final String state;
}
