package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExamData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Data transfer object to send/receive {@link ar.edu.itba.cep.lti.ExamData}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExamDataDto {

    @JsonProperty
    private final long id;
    @JsonProperty
    private final String description;
    @JsonProperty
    private final LocalDateTime startingAt;
    @JsonProperty
    private final Duration duration;
    @JsonProperty
    private final int maxScore;

    /**
     * Transform {@code this} instance into a {@link ExamData}.
     *
     * @return The created {@link ExamData}.
     */
    public ExamData toModel() {
        return new ExamData(id, description, startingAt, duration, maxScore);
    }

    /**
     * Maps the given {@code model} to a {@link ExamDataDto}.
     *
     * @param model The {@link ExamData} from where data is taken.
     * @return The created {@link ExamDataDto}.
     */
    public static ExamDataDto fromModel(final ExamData model) {
        return new ExamDataDto(
                model.getId(),
                model.getDescription(),
                model.getStartingAt(),
                model.getDuration(),
                model.getMaxScore()
        );
    }
}
