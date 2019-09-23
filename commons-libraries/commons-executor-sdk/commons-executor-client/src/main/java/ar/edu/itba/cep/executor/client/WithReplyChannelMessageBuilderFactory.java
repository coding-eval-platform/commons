package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.Constants;
import ar.edu.itba.cep.executor.dtos.ExecutionRequestDto;
import ar.edu.itba.cep.executor.dtos.ExecutionResponseDto;
import com.bellotapps.the_messenger.commons.payload.PayloadSerializer;
import com.bellotapps.the_messenger.producer.MessageBuilder;
import com.bellotapps.the_messenger.producer.MessageBuilderFactory;
import com.bellotapps.the_messenger.producer.basic_factories.GenericMessageBuilderFactory;

/**
 * A {@link MessageBuilderFactory} of {@link ExecutionResponseDto},
 * which overrides all the created {@link MessageBuilder}s adding a reply channel header.
 */
public final class WithReplyChannelMessageBuilderFactory extends GenericMessageBuilderFactory<ExecutionRequestDto> {

    /**
     * The channel to which the executor service must send the response of an execution request.
     */
    private final String replyChannel;

    /**
     * Constructor.
     *
     * @param sender         The sender to be configured to the created {@link MessageBuilder}s.
     * @param serializer     The {@link PayloadSerializer} to be configured to the created {@link MessageBuilder}s.
     * @param messageCreator The {@link MessageBuilder.MessageCreator}
     *                       to be configured to the created {@link MessageBuilder}s.
     * @param replyChannel   The channel to which the executor service must send the response of an execution request.
     */
    public WithReplyChannelMessageBuilderFactory(
            final String sender,
            final PayloadSerializer<ExecutionRequestDto> serializer,
            final MessageBuilder.MessageCreator messageCreator,
            final String replyChannel) {
        super(sender, serializer, messageCreator);
        this.replyChannel = replyChannel;
    }

    @Override
    public MessageBuilder<ExecutionRequestDto> create() {
        return super.create().withHeader(Constants.REPLY_CHANNEL_HEADER, replyChannel);
    }
}
