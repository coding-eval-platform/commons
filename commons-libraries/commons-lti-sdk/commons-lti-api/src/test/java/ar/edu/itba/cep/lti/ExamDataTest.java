package ar.edu.itba.cep.lti;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Test class for {@link ExamData}
 */
class ExamDataTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of a {@link ExamData} instance.
     */
    @Test
    void testCreation() {
        final var id = id();
        final var description = description();
        final var startingAt = startingAt();
        final var duration = duration();
        final var maxScore = maxScore();


        final var examData = new ExamData(
                id,
                description,
                startingAt,
                duration,
                maxScore
        );

        Assertions.assertAll(
                "Creating an ExamData instance is not working as expected",
                () -> Assertions.assertEquals(
                        id,
                        examData.getId(),
                        "The id does not match"
                ),
                () -> Assertions.assertEquals(
                        description,
                        examData.getDescription(),
                        "The description does not match"
                ),
                () -> Assertions.assertEquals(
                        startingAt,
                        examData.getStartingAt(),
                        "The starting at LocalDateTime does not match"
                ),
                () -> Assertions.assertEquals(
                        duration,
                        examData.getDuration(),
                        "The duration does not match"
                ),
                () -> Assertions.assertEquals(
                        maxScore,
                        examData.getMaxScore(),
                        "The max score does not match"
                )
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a description is not allowed when creating an {@link ExamData}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullDescription() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamData(
                        id(), null, startingAt(), duration(), maxScore()
                ),
                "Creating an ExamData instance with a null \"description\" is being allowed"
        );
    }

    /**
     * Tests that a null starting at value is not allowed when creating an {@link ExamData}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullStartingAt() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamData(
                        id(), description(), null, duration(), maxScore()
                ),
                "Creating an ExamData instance with a null \"starting at\" vaue is being allowed"
        );
    }

    /**
     * Tests that a null {@link Duration} is not allowed when creating an {@link ExamData}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullDuration() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamData(
                        id(), description(), startingAt(), null, maxScore()
                ),
                "Creating an ExamData instance with a null \"duration\" is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid exam id.
     */
    private static long id() {
        return Faker.instance().number().numberBetween(0L, Long.MAX_VALUE);
    }

    /**
     * @return A valid exam description.
     */
    private static String description() {
        return Faker.instance().lorem().sentence();
    }

    /**
     * @return A valid target link uri.
     */
    private static LocalDateTime startingAt() {
        final var offset = ZoneOffset.UTC;
        final var starting = LocalDateTime.now().plusDays(1).toEpochSecond(offset);
        final var finishing = LocalDateTime.now().plusDays(31).toEpochSecond(offset);
        final var random = Faker.instance().number().numberBetween(starting, finishing);

        return LocalDateTime.ofEpochSecond(random, 0, offset);
    }

    /**
     * @return A valid LTI message hint.
     */
    private static Duration duration() {
        final var durationMinutes = Faker.instance().number().numberBetween(30, 150);

        return Duration.ofMinutes(durationMinutes);
    }

    /**
     * @return A valid deployment id.
     */
    private static int maxScore() {
        return Faker.instance().number().numberBetween(2, 100);
    }
}
