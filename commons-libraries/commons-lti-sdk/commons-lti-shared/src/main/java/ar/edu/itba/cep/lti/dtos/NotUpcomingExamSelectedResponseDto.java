package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.NotUpcomingExamSelectedResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Data transfer object to send/receive {@link NotUpcomingExamSelectedResponse}s.
 */
@Value
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class NotUpcomingExamSelectedResponseDto implements ExamSelectedResponseDto<NotUpcomingExamSelectedResponse> {

    /**
     * The singleton instance.
     */
    private static final NotUpcomingExamSelectedResponseDto INSTANCE = new NotUpcomingExamSelectedResponseDto();

    /**
     * Transform {@code this} instance into a {@link NotUpcomingExamSelectedResponse}.
     *
     * @return The created {@link NotUpcomingExamSelectedResponse}.
     */
    public NotUpcomingExamSelectedResponse toModel() {
        return NotUpcomingExamSelectedResponse.getInstance();
    }


    /**
     * Retrieves the unique instance of this class.
     *
     * @return The unique {@link NotUpcomingExamSelectedResponseDto} instance.
     */
    public static NotUpcomingExamSelectedResponseDto getInstance() {
        return INSTANCE;
    }
}
