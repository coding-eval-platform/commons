package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import com.bellotapps.the_messenger.producer.MessageBuilder;

/**
 * Defines behaviour for an object that can configure a {@link MessageBuilder} using {@link ExecutionResponseIdData}.
 *
 * @param <I> The concrete type of {@link ExecutionResponseIdData}.
 */
public interface ExecutionResponseIdDataMessageBuilderConfigurer<I extends ExecutionResponseIdData> {

    /**
     * Configures the given {@code builder} using the given {@code idData}.
     *
     * @param builder The {@link MessageBuilder} to be configured.
     * @param idData  The {@link ExecutionResponseIdData} used to configure the {@link MessageBuilder},
     *                in order to be able to identify a response.
     */
    void configureMessageBuilder(final MessageBuilder builder, final I idData);
}
