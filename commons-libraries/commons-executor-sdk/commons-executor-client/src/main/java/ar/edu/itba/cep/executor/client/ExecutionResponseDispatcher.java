package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import com.bellotapps.the_messenger.commons.Message;
import com.bellotapps.the_messenger.consumer.BuiltInMessageHandler;
import com.bellotapps.the_messenger.consumer.MessageHandler;

/**
 * A dispatcher of {@link Message}s using The Messenger stuff.
 *
 * @param <I> The concrete type of {@link ExecutionResponseIdData}, needed by the underlying {@link MessageHandler}.
 */
public class ExecutionResponseDispatcher<I extends ExecutionResponseIdData> {

    /**
     * The {@link MessageHandler} in charge of dispatching actions based on received messages.
     */
    private final MessageHandler messageHandler;

    /**
     * Constructor.
     *
     * @param theMessengerExecutionResponseMessageHandler A {@link TheMessengerExecutionResponseMessageHandler}
     *                                                    to be used to configure the underlying {@link MessageHandler}
     *                                                    to which the reception of a {@link Message} is delegated to.
     */
    public ExecutionResponseDispatcher(
            final TheMessengerExecutionResponseMessageHandler<I> theMessengerExecutionResponseMessageHandler) {
        this.messageHandler = BuiltInMessageHandler.Builder.create()
                .configureTypedMessageHandlers()
                .handleReplyMessageWith(theMessengerExecutionResponseMessageHandler)
                .continueWithParentBuilder()
                .build();
    }

    /**
     * Dispatches an action upon the reception of a {@link Message}.
     *
     * @param message The {@link Message} that arrived.
     */
    public void dispatch(final Message message) {
        this.messageHandler.handle(message);
    }
}
