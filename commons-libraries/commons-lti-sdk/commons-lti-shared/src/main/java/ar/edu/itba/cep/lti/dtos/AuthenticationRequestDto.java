package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.AuthenticationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link AuthenticationRequest}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class AuthenticationRequestDto {

    @JsonProperty
    private final String endpoint;
    @JsonProperty
    private final String clientId;
    @JsonProperty
    private final String loginHint;
    @JsonProperty
    private final String redirectUri;
    @JsonProperty
    private final String nonce;
    @JsonProperty
    private final String ltiMessageHint;
    @JsonProperty
    private final String state;


    /**
     * Transform {@code this} instance into a {@link AuthenticationRequest}.
     *
     * @return The created {@link AuthenticationRequest}.
     */
    public AuthenticationRequest toModel() {
        return new AuthenticationRequest(endpoint, clientId, loginHint, redirectUri, nonce, ltiMessageHint, state);
    }

    /**
     * Maps the given {@code model} to a {@link AuthenticationRequestDto}.
     *
     * @param model The {@link AuthenticationRequest} from where data is taken.
     * @return The created {@link AuthenticationRequestDto}.
     */
    public static AuthenticationRequestDto fromModel(final AuthenticationRequest model) {
        return new AuthenticationRequestDto(
                model.getEndpoint(),
                model.getClientId(),
                model.getLoginHint(),
                model.getRedirectUri(),
                model.getNonce(),
                model.getLtiMessageHint(),
                model.getState()
        );
    }
}
