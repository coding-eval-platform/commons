package ar.edu.itba.cep.lti.constants;

import lombok.Value;

/**
 * Class containing the LTI Service paths.
 */
@Value
public class Paths {

    public static final String LOGIN_INITIATION_PATH = "login-init";
    public static final String EXAM_SELECTION_PATH = "exam-selection";
    public static final String EXAM_SELECTED_PATH = "exam-selected";
}
