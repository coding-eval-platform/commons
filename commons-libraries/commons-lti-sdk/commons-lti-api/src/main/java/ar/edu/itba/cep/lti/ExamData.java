package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Bean class holding an exam's data.
 */
@Value
public class ExamData {

    /**
     * The exam's id.
     */
    private final long id;
    /**
     * The exam's description.
     */
    @NonNull
    private final String description;
    /**
     * The {@link LocalDateTime} at which the exam should start.
     */
    @NonNull
    private final LocalDateTime startingAt;
    /**
     * The {@link Duration} that the exam should have.
     */
    @NonNull
    private final Duration duration;
    /**
     * The maximum score the exam can have.
     */
    private final int maxScore;
}
