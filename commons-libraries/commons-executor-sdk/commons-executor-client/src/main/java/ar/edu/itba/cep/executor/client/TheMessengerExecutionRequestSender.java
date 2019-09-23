package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.Constants;
import ar.edu.itba.cep.executor.api.ExecutionRequestSender;
import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import ar.edu.itba.cep.executor.dtos.ExecutionRequestDto;
import ar.edu.itba.cep.executor.models.ExecutionRequest;
import com.bellotapps.the_messenger.commons.Message;
import com.bellotapps.the_messenger.producer.MessageBuilderFactory;
import com.bellotapps.the_messenger.producer.MessageProducer;
import lombok.AllArgsConstructor;

/**
 * Concrete implementation of {@link ExecutionRequestSender}, using The Messenger.
 */
@AllArgsConstructor
public class TheMessengerExecutionRequestSender<I extends ExecutionResponseIdData>
        implements ExecutionRequestSender<I> {

    /**
     * The {@link MessageProducer} in charge of sending the {@link Message}.
     */
    private final MessageProducer messageProducer;
    /**
     * A {@link MessageBuilderFactory} of {@link ExecutionRequestDto} that creates the
     * {@link com.bellotapps.the_messenger.producer.MessageBuilder} that can create the request {@link Message}s.
     */
    private final MessageBuilderFactory<ExecutionRequestDto> messageBuilderFactory;
    /**
     * The {@link ExecutorServiceCommandMessagesConfigurer} used to setup the
     * {@link com.bellotapps.the_messenger.producer.MessageBuilder}s used to create the {@link Message}s
     * being sent with data used to identify response messages.
     */
    private final ExecutionResponseIdDataMessageBuilderConfigurer<I> idDataMessageBuilderConfigurer;


    @Override
    public void requestExecution(
            final ExecutionRequest executionRequest,
            final I idData) {
        final var builder = messageBuilderFactory.commandMessage("requestExecution")
                .withPayload(ExecutionRequestDto.buildFromRequest(executionRequest));
        idDataMessageBuilderConfigurer.configureMessageBuilder(builder, idData);
        messageProducer.send(builder.build(), Constants.EXECUTOR_SERVICE_COMMANDS_CHANNEL);
    }
}
