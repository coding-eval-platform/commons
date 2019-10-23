package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.LoginInitiationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link LoginInitiationRequest}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class LoginInitiationRequestDto {

    @JsonProperty
    private final String issuer;
    @JsonProperty
    private final String loginHint;
    @JsonProperty
    private final String targetLinkUri;
    @JsonProperty
    private final String ltiMessageHint;
    @JsonProperty
    private final String deploymentId;
    @JsonProperty
    private final String clientId;


    /**
     * Transform {@code this} instance into a {@link LoginInitiationRequest}.
     *
     * @return The created {@link LoginInitiationRequest}.
     */
    public LoginInitiationRequest toModel() {
        return new LoginInitiationRequest(issuer, loginHint, targetLinkUri, ltiMessageHint, deploymentId, clientId);
    }

    /**
     * Maps the given {@code model} to a {@link LoginInitiationRequestDto}.
     *
     * @param model The {@link LoginInitiationRequest} from where data is taken.
     * @return The created {@link LoginInitiationRequestDto}.
     */
    public static LoginInitiationRequestDto fromModel(final LoginInitiationRequest model) {
        return new LoginInitiationRequestDto(
                model.getIssuer(),
                model.getLoginHint(),
                model.getTargetLinkUri(),
                model.getLtiMessageHint(),
                model.getDeploymentId(),
                model.getClientId()
        );
    }
}
