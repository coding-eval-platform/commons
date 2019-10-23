package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExamSelectionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link ExamSelectionResponse}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExamSelectionResponseDto {

    @JsonProperty
    private final String state;


    /**
     * Transform {@code this} instance into a {@link ExamSelectionResponse}.
     *
     * @return The created {@link ExamSelectionResponse}.
     */
    public ExamSelectionResponse toModel() {
        return new ExamSelectionResponse(state);
    }

    /**
     * Maps the given {@code model} to a {@link ExamSelectionResponseDto}.
     *
     * @param model The {@link ExamSelectionResponse} from where data is taken.
     * @return The created {@link ExamSelectionResponseDto}.
     */
    public static ExamSelectionResponseDto fromModel(final ExamSelectionResponse model) {
        return new ExamSelectionResponseDto(model.getState());
    }
}
