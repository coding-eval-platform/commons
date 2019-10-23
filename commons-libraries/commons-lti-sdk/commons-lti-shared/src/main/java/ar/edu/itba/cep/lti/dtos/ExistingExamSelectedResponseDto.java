package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExistingExamSelectedResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Optional;

/**
 * Data transfer object to send/receive {@link ExistingExamSelectedResponse}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExistingExamSelectedResponseDto implements ExamSelectedResponseDto<ExistingExamSelectedResponse> {

    @JsonProperty
    private final String endpoint;
    @JsonProperty
    private final String jwt;
    @JsonProperty
    private final ExamDataDto examData;


    /**
     * Transform {@code this} instance into a {@link ExistingExamSelectedResponse}.
     *
     * @return The created {@link ExistingExamSelectedResponse}.
     */
    public ExistingExamSelectedResponse toModel() {
        final var examDataModel = Optional.ofNullable(examData).map(ExamDataDto::toModel).orElseThrow();
        return new ExistingExamSelectedResponse(endpoint, jwt, examDataModel);
    }

    /**
     * Maps the given {@code model} to a {@link ExistingExamSelectedResponseDto}.
     *
     * @param model The {@link ExistingExamSelectedResponse} from where data is taken.
     * @return The created {@link ExistingExamSelectedResponseDto}.
     */
    public static ExistingExamSelectedResponseDto fromModel(final ExistingExamSelectedResponse model) {
        return new ExistingExamSelectedResponseDto(model.getEndpoint(), model.getJwt(), ExamDataDto.fromModel(model.getExamData()));
    }
}
