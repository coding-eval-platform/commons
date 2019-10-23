package ar.edu.itba.cep.lti;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Represents an "exam selected" response of a not upcoming exam (already started/finished),
 * which is sent back to the user once the corresponding {@link ExamSelectedRequest} has been validated and processed.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NotUpcomingExamSelectedResponse implements ExamSelectedResponse {

    /**
     * The singleton instance.
     */
    private static final NotUpcomingExamSelectedResponse INSTANCE = new NotUpcomingExamSelectedResponse();

    /**
     * Retrieves the unique instance of this class.
     *
     * @return The unique {@link NotUpcomingExamSelectedResponse} instance.
     */
    public static NotUpcomingExamSelectedResponse getInstance() {
        return INSTANCE;
    }
}
