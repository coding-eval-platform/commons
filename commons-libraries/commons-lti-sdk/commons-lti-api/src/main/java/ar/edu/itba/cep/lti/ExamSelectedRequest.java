package ar.edu.itba.cep.lti;

import lombok.NonNull;
import lombok.Value;

/**
 * Represents an "exam selected" request,
 * which is sent once the user had entered an exam id to match with an LMS content.
 */
@Value
public class ExamSelectedRequest {

    /**
     * The id of the exam that was selected.
     */
    @NonNull
    private final long examId;
    /**
     * The state that must be resent after an exam is selected.
     */
    @NonNull
    private final String state;
    /**
     * The url of the resource
     * (i.e the endpoint to which the LMS must send the request to access the exam).
     * This is part of the request as the frontend has the context in which the resource must be accessed.
     */
    @NonNull
    private final String url;
    /**
     * The {@link Image} for the icon.
     * This is part of the request as the frontend is owner of the images.
     */
    private final Image icon;
    /**
     * The {@link Image} for the thumbnail.
     * This is part of the request as the frontend is owner of the images.
     */
    private final Image thumbnail;


    /**
     * Represents an image, which can be sent in the {@link ExamSelectedRequest} as an icon or as a thumbnail.
     */
    @Value
    public static final class Image {
        /**
         * Url of the image file.
         */
        @NonNull
        private final String url;
        /**
         * The width (in pixels) of the image.
         */
        private final int width;
        /**
         * The height (in pixels) of the image.
         */
        private final int height;
    }
}
