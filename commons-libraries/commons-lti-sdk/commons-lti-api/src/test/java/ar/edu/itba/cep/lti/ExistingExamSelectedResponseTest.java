package ar.edu.itba.cep.lti;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

/**
 * Test class for {@link ExistingExamSelectedResponse}.
 */
@ExtendWith(MockitoExtension.class)
class ExistingExamSelectedResponseTest {


    // ================================================================================================================
    // Acceptable arguments
    // ================================================================================================================

    /**
     * Tests the creation of an {@link ExistingExamSelectedResponse} instance.
     */
    @Test
    void testCreation(@Mock(name = "examData") final ExamData examData) {
        final var endpoint = endpoint();
        final var jwt = jwt();

        final var response = new ExistingExamSelectedResponse(endpoint, jwt, examData);

        Assertions.assertAll(
                "Creating an ExamSelectedResponse is not working as expected",
                () -> Assertions.assertEquals(endpoint, response.getEndpoint(), "The endpoint does not match"),
                () -> Assertions.assertEquals(jwt, response.getJwt(), "The JWT does not match"),
                () -> Assertions.assertEquals(examData, response.getExamData(), "The Exam Data does not match")
        );
    }


    // ================================================================================================================
    // Constraint testing
    // ================================================================================================================

    /**
     * Tests that a null endpoint is not allowed when creating an {@link ExistingExamSelectedResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullEndpoint(@Mock(name = "examData") final ExamData examData) {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExistingExamSelectedResponse(null, jwt(), examData),
                "Creating an ExamSelectedResponse with a null state is being allowed"
        );
    }

    /**
     * Tests that a null jwt is not allowed when creating an {@link ExistingExamSelectedResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullState(@Mock(name = "examData") final ExamData examData) {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExistingExamSelectedResponse(endpoint(), null, examData),
                "Creating an ExamSelectedResponse with a null state is being allowed"
        );
    }

    /**
     * Tests that a null jwt is not allowed when creating an {@link ExistingExamSelectedResponse}
     * (i.e throws an {@link NullPointerException}).
     */
    @Test
    void testNullExamData() {
        Assertions.assertThrows(
                NullPointerException.class,
                () -> new ExistingExamSelectedResponse(endpoint(), jwt(), null),
                "Creating an ExamSelectedResponse with a null state is being allowed"
        );
    }


    // ================================================================================================================
    // Helpers
    // ================================================================================================================

    /**
     * @return A valid endpoint.
     */
    private static String endpoint() {
        return "https://" + Faker.instance().internet().domainName();
    }

    /**
     * @return A valid client id.
     */
    private static String jwt() {
        return UUID.randomUUID().toString();
    }
}
