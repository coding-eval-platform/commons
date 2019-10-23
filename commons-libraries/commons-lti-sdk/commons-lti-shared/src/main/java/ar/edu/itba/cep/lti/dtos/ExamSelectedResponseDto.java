package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.ExamSelectedResponse;
import ar.edu.itba.cep.lti.ExistingExamSelectedResponse;
import ar.edu.itba.cep.lti.NonExistingExamSelectedResponse;
import ar.edu.itba.cep.lti.NotUpcomingExamSelectedResponse;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Marking interface that allows sending/receiving both
 * {@link ExistingExamSelectedResponseDto} and {@link NonExistingExamSelectedResponseDto} instances.
 *
 * @param <M> The concrete type of {@link ExamSelectedResponse} subclass the implementor of this interfaces is DTO of.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = ExistingExamSelectedResponseDto.class,
                name = ExamSelectedResponseDto.EXISTING
        ),
        @JsonSubTypes.Type(
                value = NonExistingExamSelectedResponseDto.class,
                name = ExamSelectedResponseDto.NON_EXISTING
        ),
        @JsonSubTypes.Type(
                value = NotUpcomingExamSelectedResponseDto.class,
                name = ExamSelectedResponseDto.NOT_UPCOMING
        ),
})
public interface ExamSelectedResponseDto<M extends ExamSelectedResponse> {

    /**
     * Value that marks a JSON to be deserialized into an {@link ExistingExamSelectedResponseDto}.
     */
    /* package */ String EXISTING = "EXISTING";
    /**
     * Value that marks a JSON to be deserialized into a {@link NonExistingExamSelectedResponseDto}.
     */
    /* package */ String NON_EXISTING = "NON_EXISTING";
    /**
     * Value that marks a JSON to be deserialized into a {@link NotUpcomingExamSelectedResponseDto}.
     */
    /* package */ String NOT_UPCOMING = "NOT_UPCOMING";

    /**
     * Transform {@code this} instance into its model counterpart (of type {@code M}).
     *
     * @return The created model instance.
     */
    M toModel();

    /**
     * Builder method that returns the {@link ExamSelectedResponse} subclass corresponding to the given {@code model}.
     *
     * @param model The {@link ExamSelectedResponse} whose DTO counterpart must be retrieved.
     * @return The corresponding {@link ExamSelectedResponse}.
     */
    static ExamSelectedResponseDto fromModel(final ExamSelectedResponse model) {
        if (model instanceof ExistingExamSelectedResponse) {
            return ExistingExamSelectedResponseDto.fromModel((ExistingExamSelectedResponse) model);
        }
        if (model instanceof NonExistingExamSelectedResponse) {
            return NonExistingExamSelectedResponseDto.getInstance();
        }
        if (model instanceof NotUpcomingExamSelectedResponse) {
            return NotUpcomingExamSelectedResponseDto.getInstance();
        }
        throw new IllegalArgumentException("Unknown subtype");
    }
}
