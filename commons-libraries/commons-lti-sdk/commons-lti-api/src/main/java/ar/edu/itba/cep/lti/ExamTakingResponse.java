package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

import java.util.UUID;

/**
 * Represents an exam taking response (i.e handling of an LTI Deep Linking request)
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
public class ExamTakingResponse {

    /**
     * The id of the exam being launched.
     */
    private final long examId;
    /**
     * The token's id.
     */
    @NonNull
    private final UUID tokenId;
    /**
     * The access token.
     */
    @NonNull
    private final String accessToken;
    /**
     * The refresh token.
     */
    @NonNull
    private final String refreshToken;
    /**
     * The url to which the user should be redirected when the exam is completed. Can be null.
     */
    private final String returnUrl;
}
