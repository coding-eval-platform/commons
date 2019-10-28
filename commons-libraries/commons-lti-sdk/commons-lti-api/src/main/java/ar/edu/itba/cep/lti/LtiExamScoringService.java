package ar.edu.itba.cep.lti;

/**
 * Defines behaviour for objects that can perform the exam taking process,
 * which is performed by the LTI authentication response handling.
 */
public interface LtiExamScoringService {

    /**
     * Handles the exam scoring requests.
     *
     * @param examScoringRequest The received {@link ExamScoringRequest}.
     */
    void scoreExam(final ExamScoringRequest examScoringRequest);
}
