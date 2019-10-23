package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.NonExistingExamSelectedResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link NonExistingExamSelectedResponse}s.
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class NonExistingExamSelectedResponseDto implements ExamSelectedResponseDto<NonExistingExamSelectedResponse> {

    /**
     * The singleton instance.
     */
    private static final NonExistingExamSelectedResponseDto INSTANCE = new NonExistingExamSelectedResponseDto();

    /**
     * Transform {@code this} instance into a {@link NonExistingExamSelectedResponse}.
     *
     * @return The created {@link NonExistingExamSelectedResponse}.
     */
    public NonExistingExamSelectedResponse toModel() {
        return NonExistingExamSelectedResponse.getInstance();
    }


    /**
     * Retrieves the unique instance of this class.
     *
     * @return The unique {@link NonExistingExamSelectedResponseDto} instance.
     */
    public static NonExistingExamSelectedResponseDto getInstance() {
        return INSTANCE;
    }
}
