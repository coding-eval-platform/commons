package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.api.ExecutionResponseHandler;
import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import ar.edu.itba.cep.executor.dtos.ExecutionResponseDto;
import ar.edu.itba.cep.executor.models.ExecutionResponse;
import com.bellotapps.the_messenger.commons.Message;
import com.bellotapps.the_messenger.commons.payload.PayloadDeserializer;
import com.bellotapps.the_messenger.consumer.DeserializerMessageHandler;

/**
 * A {@link com.bellotapps.the_messenger.consumer.MessageHandler} that can process {@link ExecutionResponseDto}s.
 *
 * @param <I> The concrete type of {@link ExecutionResponseIdData} needed to identify a received {@link Message}.
 */
public class TheMessengerExecutionResponseMessageHandler<I extends ExecutionResponseIdData>
        extends DeserializerMessageHandler<ExecutionResponseDto> {

    /**
     * The {@link ExecutionResponseHandler} that is in charge of processing a {@link ExecutionResponse}s.
     */
    private final ExecutionResponseHandler<I> executionResponseProcessor;
    /**
     * An {@link ExecutionResponseIdDataFactory}
     * needed to create {@link ExecutionResponseIdData} instances from the received {@link Message}s.
     */
    private final ExecutionResponseIdDataFactory<I> identificationDataFactory;


    /**
     * Constructor.
     *
     * @param payloadDeserializer        A {@link PayloadDeserializer} of {@link ExecutionResponseDto}.
     * @param executionResponseProcessor The {@link ExecutionResponseHandler}
     *                                   that is in charge of processing a {@link ExecutionResponse}s.
     * @param identificationDataFactory  An {@link ExecutionResponseIdDataFactory}
     *                                   needed to create {@link ExecutionResponseIdData}
     *                                   instances from the received {@link Message}s.
     */
    public TheMessengerExecutionResponseMessageHandler(
            final PayloadDeserializer<ExecutionResponseDto> payloadDeserializer,
            final ExecutionResponseHandler<I> executionResponseProcessor,
            final ExecutionResponseIdDataFactory<I> identificationDataFactory) {
        super(payloadDeserializer);
        this.executionResponseProcessor = executionResponseProcessor;
        this.identificationDataFactory = identificationDataFactory;
    }


    @Override
    protected void andThen(final ExecutionResponseDto executionResponseDto, final Message message) {
        executionResponseProcessor.processExecutionResponse(
                executionResponseDto.getExecutionResponse(),
                identificationDataFactory.buildFromMessage(message)
        );
    }
}
