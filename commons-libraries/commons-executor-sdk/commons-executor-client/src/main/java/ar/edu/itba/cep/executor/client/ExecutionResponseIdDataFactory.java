package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import com.bellotapps.the_messenger.commons.Message;

/**
 * Defines behaviour for an object that can build an {@link ExecutionResponseIdData} of type {@code I}
 * from a {@link Message}.
 *
 * @param <I> The concrete type of {@link ExecutionResponseIdData}.
 */
public interface ExecutionResponseIdDataFactory<I extends ExecutionResponseIdData> {

    /**
     * Builds an instance of {@link ExecutionResponseIdData} from the given {@code message}.
     *
     * @param message The {@link Message} to be analyzed.
     * @return The created {@link ExecutionResponseIdData}.
     * @apiNote This method must be thread-safe.
     */
    I buildFromMessage(final Message message);
}
