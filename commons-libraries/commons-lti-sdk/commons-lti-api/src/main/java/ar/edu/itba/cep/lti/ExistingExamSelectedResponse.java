package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an "exam selected" response of an existing exam,
 * which is sent back to the user once the corresponding {@link ExamSelectedRequest} has been validated and processed.
 */
@Value
public class ExistingExamSelectedResponse implements ExamSelectedResponse {

    /**
     * The endpoint to which the user must be redirected.
     */
    @NonNull
    private final String endpoint;
    /**
     * The JWT to be sent to the platform.
     */
    @NonNull
    private final String jwt;
    /**
     * The details of the selected exam.
     */
    @NonNull
    private final ExamData examData;
}
