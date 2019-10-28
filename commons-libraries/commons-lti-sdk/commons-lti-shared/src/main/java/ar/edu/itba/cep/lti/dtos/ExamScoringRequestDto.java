package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExamScoringRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link ar.edu.itba.cep.lti.ExamScoringRequest}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExamScoringRequestDto {

    @JsonProperty
    private final long examId;
    @JsonProperty
    private final String subject;
    @JsonProperty
    private final int score;

    /**
     * Transform {@code this} instance into a {@link ExamScoringRequest}.
     *
     * @return The created {@link ExamScoringRequest}.
     */
    public ExamScoringRequest toModel() {
        return new ExamScoringRequest(examId, subject, score);
    }

    /**
     * Maps the given {@code model} to a {@link ExamScoringRequestDto}.
     *
     * @param model The {@link ExamScoringRequest} from where data is taken.
     * @return The created {@link ExamScoringRequestDto}.
     */
    public static ExamScoringRequestDto fromModel(final ExamScoringRequest model) {
        return new ExamScoringRequestDto(model.getExamId(), model.getSubject(), model.getScore());
    }
}
