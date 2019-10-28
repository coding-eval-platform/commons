package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExamTakingResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.UUID;

/**
 * Data transfer object to send/receive {@link ExamTakingResponse}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExamTakingResponseDto {

    @JsonProperty
    private final long examId;
    @JsonProperty
    private final UUID tokenId;
    @JsonProperty
    private final String accessToken;
    @JsonProperty
    private final String refreshToken;
    @JsonProperty
    private final String returnUrl;


    /**
     * Transform {@code this} instance into a {@link ExamTakingResponse}.
     *
     * @return The created {@link ExamTakingResponse}.
     */
    public ExamTakingResponse toModel() {
        return new ExamTakingResponse(examId, tokenId, accessToken, refreshToken, returnUrl);
    }

    /**
     * Maps the given {@code model} to a {@link ExamTakingResponseDto}.
     *
     * @param model The {@link ExamTakingResponse} from where data is taken.
     * @return The created {@link ExamTakingResponseDto}.
     */
    public static ExamTakingResponseDto fromModel(final ExamTakingResponse model) {
        return new ExamTakingResponseDto(
                model.getExamId(),
                model.getTokenId(),
                model.getAccessToken(),
                model.getRefreshToken(),
                model.getReturnUrl()
        );
    }
}
