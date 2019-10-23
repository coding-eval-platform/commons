package ar.edu.itba.cep.lti;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;
import java.util.UUID;

/**
 * Test class for {@link ExamSelectedRequest}.
 */
@ExtendWith(MockitoExtension.class)
class ExamSelectedRequestTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of an {@link ExamSelectedRequest} instance.
     */
    @Test
    void testCreation(
            @Mock(name = "icon") final ExamSelectedRequest.Image icon,
            @Mock(name = "thumbnail") final ExamSelectedRequest.Image thumbnail) {
        final var examId = examId();
        final var state = state();
        final var url = url();

        final var request = new ExamSelectedRequest(examId, state, url, icon, thumbnail);

        Assertions.assertAll(
                "Creating an ExamSelectedRequest is not working as expected",
                () -> Assertions.assertEquals(examId, request.getExamId(), "The exam id does not match"),
                () -> Assertions.assertEquals(state, request.getState(), "The state does not match"),
                () -> Assertions.assertEquals(url, request.getUrl(), "The url does not match"),
                () -> Assertions.assertEquals(icon, request.getIcon(), "The icon does not match"),
                () -> Assertions.assertEquals(thumbnail, request.getThumbnail(), "The thumbnail does not match")
        );
    }

    /**
     * Tests the creation of an {@link ExamSelectedRequest} instance when using null icon and thumbnail
     * (they are optional).
     */
    @Test
    void testCreationOptionals() {
        Assertions.assertDoesNotThrow(
                () -> new ExamSelectedRequest(examId(), state(), url(), null, null),
                "Creating an ExamSelectedRequest with null icon and thumbnail is not being allowed"
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null state is not allowed when creating an {@link ExamSelectedRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullState(
            @Mock(name = "icon") final ExamSelectedRequest.Image icon,
            @Mock(name = "thumbnail") final ExamSelectedRequest.Image thumbnail) {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamSelectedRequest(examId(), null, url(), icon, thumbnail),
                "Creating an ExamSelectedRequest with a null state is being allowed"
        );
    }

    /**
     * Tests that a null url is not allowed when creating an {@link ExamSelectedRequest}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullUrl(
            @Mock(name = "icon") final ExamSelectedRequest.Image icon,
            @Mock(name = "thumbnail") final ExamSelectedRequest.Image thumbnail) {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExamSelectedRequest(examId(), state(), null, icon, thumbnail),
                "Creating an ExamSelectedRequest with a null state is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid exam id.
     */
    private static long examId() {
        return new Random().nextLong();
    }

    /**
     * @return A valid client id.
     */
    private static String state() {
        return UUID.randomUUID().toString();
    }

    /**
     * @return A valid url.
     */
    private static String url() {
        return "https://" + Faker.instance().internet().domainName();
    }
}
