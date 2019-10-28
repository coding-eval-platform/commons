package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an exam scoring request (i.e indicates that the score of an exam must be published to the LMSs).
 */
@Value
public class ExamScoringRequest {

    /**
     * The id of the exam being scored.
     */
    private final long examId;
    /**
     * The subject to which the score belongs.
     */
    @NonNull
    private final String subject;
    /**
     * The score achieved.
     */
    private final int score;
}
