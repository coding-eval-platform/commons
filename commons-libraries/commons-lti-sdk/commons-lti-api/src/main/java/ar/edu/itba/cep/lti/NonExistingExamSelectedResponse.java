package ar.edu.itba.cep.lti;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Represents an "exam selected" response of a non existing exam,
 * which is sent back to the user once the corresponding {@link ExamSelectedRequest} has been validated and processed.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NonExistingExamSelectedResponse implements ExamSelectedResponse {

    /**
     * The singleton instance.
     */
    private static final NonExistingExamSelectedResponse INSTANCE = new NonExistingExamSelectedResponse();

    /**
     * Retrieves the unique instance of this class.
     *
     * @return The unique {@link NonExistingExamSelectedResponse} instance.
     */
    public static NonExistingExamSelectedResponse getInstance() {
        return INSTANCE;
    }
}
