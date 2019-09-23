package ar.edu.itba.cep.executor.client;

import ar.edu.itba.cep.executor.api.ExecutionRequestSender;
import ar.edu.itba.cep.executor.api.ExecutionResponseHandler;
import ar.edu.itba.cep.executor.api.ExecutionResponseIdData;
import ar.edu.itba.cep.executor.dtos.ExecutionRequestDto;
import ar.edu.itba.cep.executor.dtos.ExecutionResponseDto;
import com.bellotapps.the_messenger.commons.payload.PayloadDeserializer;
import com.bellotapps.the_messenger.commons.payload.PayloadSerializer;
import com.bellotapps.the_messenger.json.JacksonJsonPayloadDeserializer;
import com.bellotapps.the_messenger.json.JacksonJsonPayloadSerializer;
import com.bellotapps.the_messenger.producer.MessageBuilderFactory;
import com.bellotapps.the_messenger.producer.MessageProducer;
import com.bellotapps.the_messenger.transport.json.jackson.JacksonMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Configuration class for the executor service command messages client.
 *
 * @param <I> The concrete type of {@link ExecutionResponseIdData} used by the execution request operations.
 */
@Configuration
@EnableConfigurationProperties(ExecutorServiceCommandMessagesProperties.class)
public class ExecutorServiceCommandMessagesConfigurer<I extends ExecutionResponseIdData> {

    /**
     * A {@link Logger} to indicate if there are issues with the configuration.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorServiceCommandMessagesConfigurer.class);


    /**
     * Creates a bean of {@link JacksonJsonPayloadSerializer} of {@link ExecutionRequestDto}.
     *
     * @return A bean of {@link PayloadSerializer} of {@link ExecutionResponseDto}.
     */
    @Bean
    public PayloadSerializer<ExecutionRequestDto> executionRequestDtoPayloadSerializer() {
        return new JacksonJsonPayloadSerializer<>(new ObjectMapper(), ExecutionRequestDto.class);
    }

    /**
     * Creates a bean of {@link JacksonJsonPayloadDeserializer} of {@link ExecutionResponseDto}.
     *
     * @return A bean of {@link PayloadDeserializer} of {@link ExecutionResponseDto}.
     */
    @Bean
    public PayloadDeserializer<ExecutionResponseDto> executionResponseDtoPayloadDeserializer() {
        return new JacksonJsonPayloadDeserializer<>(new ObjectMapper(), ExecutionResponseDto.class);
    }

    /**
     * Creates a bean of {@link WithReplyChannelMessageBuilderFactory}.
     *
     * @param executionRequestDtoPayloadSerializer A {@link PayloadSerializer} of {@link ExecutionRequestDto}.
     * @param properties                           An {@link ExecutorServiceCommandMessagesProperties} instance
     *                                             with configuration stuff.
     * @return A bean of {@link MessageBuilderFactory} of {@link ExecutionRequestDto}.
     */
    @Bean
    public MessageBuilderFactory<ExecutionRequestDto> executionRequestMessageBuilderFactory(
            final PayloadSerializer<ExecutionRequestDto> executionRequestDtoPayloadSerializer,
            final ExecutorServiceCommandMessagesProperties properties) {

        return new WithReplyChannelMessageBuilderFactory(
                properties.getSender(),
                executionRequestDtoPayloadSerializer,
                JacksonMessage::new,
                properties.getRequestExecution().getReplyChannel()
        );
    }

    /**
     * A "placeholder" {@link MessageProducer} bean creation method.
     *
     * @return Nothing.
     * @throws IllegalStateException Always. This method is executed only if another {@link MessageProducer} bean
     *                               is not defined in the scope.
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public MessageProducer dummyMessageProducer() throws IllegalStateException {
        LOGGER.error("Missing MessageProducer!");
        throw new IllegalStateException("A MessageProducer was not found");
    }

    /**
     * A "placeholder" {@link ExecutionResponseIdDataMessageBuilderConfigurer} bean creation method.
     *
     * @return Nothing.
     * @throws IllegalStateException Always. This method is executed only if another
     *                               {@link ExecutionResponseIdDataMessageBuilderConfigurer} bean
     *                               is not defined in the scope.
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ExecutionResponseIdDataMessageBuilderConfigurer<I> dummyIdDataMessageBuilderConfigurer() {
        LOGGER.error("Missing ExecutionResponseIdDataMessageBuilderConfigurer!");
        throw new IllegalStateException("A ExecutionResponseIdDataMessageBuilderConfigurer was not found");
    }

    /**
     * Creates an {@link ExecutionRequestSender} bean of type {@link TheMessengerExecutionRequestSender}.
     *
     * @param messageProducer          The {@link MessageProducer} used to send messages.
     * @param messageBuilderFactory    A {@link MessageBuilderFactory} used to create the
     *                                 {@link com.bellotapps.the_messenger.commons.Message}s
     * @param messageBuilderConfigurer An {@link ExecutionResponseIdDataMessageBuilderConfigurer}
     *                                 needed to configure {@link com.bellotapps.the_messenger.producer.MessageBuilder}s
     *                                 with data to identify a response.
     * @return The created bean.
     */
    @Bean
    public ExecutionRequestSender<I> executionRequestSender(
            final MessageProducer messageProducer,
            final MessageBuilderFactory<ExecutionRequestDto> messageBuilderFactory,
            final ExecutionResponseIdDataMessageBuilderConfigurer<I> messageBuilderConfigurer) {

        return new TheMessengerExecutionRequestSender<>(
                messageProducer,
                messageBuilderFactory,
                messageBuilderConfigurer
        );
    }


    /**
     * A "placeholder" {@link ExecutionResponseHandler} bean creation method.
     *
     * @return Nothing.
     * @throws IllegalStateException Always. This method is executed only if another
     *                               {@link ExecutionResponseHandler} bean is not defined in the scope.
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ExecutionResponseHandler dummyExecutionResponseHandler() {
        LOGGER.error("Missing ExecutorServiceCommandResponseHandler!");
        throw new IllegalStateException("A ExecutorServiceCommandResponseHandler was not found");
    }

    /**
     * A "placeholder" {@link ExecutionResponseIdDataFactory} bean creation method.
     *
     * @return Nothing.
     * @throws IllegalStateException Always. This method is executed only if another
     *                               {@link ExecutionResponseIdDataFactory} bean is not defined in the scope.
     */
    @Lazy
    @Bean
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ExecutionResponseIdDataFactory dummyIdDataFactory() {
        LOGGER.error("Missing ExecutionResponseIdDataFactory!");
        throw new IllegalStateException("A ExecutionResponseIdDataFactory was not found");
    }

    /**
     * Creates a {@link com.bellotapps.the_messenger.consumer.MessageHandler} bean
     * of type {@link TheMessengerExecutionResponseMessageHandler}.
     *
     * @param payloadDeserializer       A {@link PayloadDeserializer} of {@link ExecutionResponseDto}.
     * @param executionResponseHandler  The {@link ExecutionResponseHandler} to which the handling is delegated.
     * @param identificationDataFactory An {@link ExecutionResponseIdDataFactory} needed to analyze
     *                                  {@link com.bellotapps.the_messenger.commons.Message}s looking for data
     *                                  to identify the response.
     * @return The created bean.
     */
    @Bean
    public TheMessengerExecutionResponseMessageHandler executionResponseMessageHandler(
            final PayloadDeserializer<ExecutionResponseDto> payloadDeserializer,
            final ExecutionResponseHandler<I> executionResponseHandler,
            final ExecutionResponseIdDataFactory<I> identificationDataFactory) {

        return new TheMessengerExecutionResponseMessageHandler<>(
                payloadDeserializer,
                executionResponseHandler,
                identificationDataFactory
        );
    }

    /**
     * Creates an {@link ExecutionResponseDispatcher} bean.
     *
     * @param theMessengerExecutionResponseMessageHandler A {@link TheMessengerExecutionResponseMessageHandler}
     *                                                    to which the handling will be delegated.
     * @return The created bean.
     */
    @Bean
    public ExecutionResponseDispatcher messagesDispatcher(
            final TheMessengerExecutionResponseMessageHandler<I> theMessengerExecutionResponseMessageHandler) {
        return new ExecutionResponseDispatcher<>(theMessengerExecutionResponseMessageHandler);
    }
}
