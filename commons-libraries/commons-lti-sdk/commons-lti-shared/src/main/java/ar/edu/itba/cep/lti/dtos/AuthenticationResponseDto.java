package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.AuthenticationResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link AuthenticationResponse}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class AuthenticationResponseDto {

    @JsonProperty
    private final String idToken;
    @JsonProperty
    private final String state;


    /**
     * Transform {@code this} instance into a {@link AuthenticationResponse}.
     *
     * @return The created {@link AuthenticationResponse}.
     */
    public AuthenticationResponse toModel() {
        return new AuthenticationResponse(idToken, state);
    }

    /**
     * Maps the given {@code model} to a {@link AuthenticationResponseDto}.
     *
     * @param model The {@link AuthenticationResponse} from where data is taken.
     * @return The created {@link AuthenticationResponseDto}.
     */
    public static AuthenticationResponseDto fromModel(final AuthenticationResponse model) {
        return new AuthenticationResponseDto(model.getIdToken(), model.getState());
    }
}
