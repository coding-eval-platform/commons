package ar.edu.itba.cep.lti.dtos;

import ar.edu.itba.cep.lti.AuthenticationRequest;
import ar.edu.itba.cep.lti.ExamSelectedRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Optional;

/**
 * Data transfer object to send/receive {@link ExamSelectedRequest}s.
 */
@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ExamSelectedRequestDto {

    @JsonProperty
    private final long examId; // TODO: long or Long
    @JsonProperty
    private final String state;
    @JsonProperty
    private final String url;
    @JsonProperty
    private final ImageDto icon;
    @JsonProperty
    private final ImageDto thumbnail;


    /**
     * Transform {@code this} instance into a {@link ExamSelectedRequest}.
     *
     * @return The created {@link ExamSelectedRequest}.
     */
    public ExamSelectedRequest toModel() {
        return new ExamSelectedRequest(
                examId,
                state,
                url,
                Optional.ofNullable(icon).map(ImageDto::toModel).orElse(null),
                Optional.ofNullable(thumbnail).map(ImageDto::toModel).orElse(null)
        );
    }

    /**
     * Maps the given {@code model} to a {@link ExamSelectedRequestDto}.
     *
     * @param model The {@link ExamSelectedRequest} from where data is taken.
     * @return The created {@link ExamSelectedRequestDto}.
     */
    public static ExamSelectedRequestDto fromModel(final ExamSelectedRequest model) {
        return new ExamSelectedRequestDto(
                model.getExamId(),
                model.getState(),
                model.getUrl(),
                Optional.ofNullable(model.getIcon()).map(ImageDto::fromModel).orElse(null),
                Optional.ofNullable(model.getThumbnail()).map(ImageDto::fromModel).orElse(null)
        );
    }


    /**
     * Represents an image, which can be sent in the {@link ExamSelectedRequest} as an icon or as a thumbnail.
     */
    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
    public static final class ImageDto {
        @JsonProperty
        private final String url;
        @JsonProperty
        private final int width;
        @JsonProperty
        private final int height;


        /**
         * Transform {@code this} instance into a {@link ExamSelectedRequest.Image}.
         *
         * @return The created {@link ExamSelectedRequest.Image}.
         */
        public ExamSelectedRequest.Image toModel() {
            return new ExamSelectedRequest.Image(url, width, height);
        }

        /**
         * Maps the given {@code model} to a {@link ImageDto}.
         *
         * @param model The {@link ExamSelectedRequest.Image} from where data is taken.
         * @return The created {@link ImageDto}.
         */
        public static ImageDto fromModel(final ExamSelectedRequest.Image model) {
            return new ImageDto(model.getUrl(), model.getWidth(), model.getHeight());
        }
    }
}
