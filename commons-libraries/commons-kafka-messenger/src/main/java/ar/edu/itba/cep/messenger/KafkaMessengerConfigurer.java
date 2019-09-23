package ar.edu.itba.cep.messenger;

import com.bellotapps.the_messenger.commons.Message;
import com.bellotapps.the_messenger.producer.BiConsumerMessageProducer;
import com.bellotapps.the_messenger.producer.MessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * Configuration class to initialize a {@link MessageProducer} using Kafka (i.e. a {@link KafkaTemplate} instance).
 */
@Configuration
public class KafkaMessengerConfigurer {

    /**
     * A {@link Logger} to indicate if there are issues with the configuration.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessengerConfigurer.class);

    /**
     * Creates a bean of {@link BiConsumerMessageProducer} that allows sending messages.
     *
     * @param kafkaTemplate The underlying {@link KafkaTemplate} used by the {@link MessageProducer}.
     * @return A bean of {@link MessageProducer}.
     */
    @Bean
    public MessageProducer messageProducer(final KafkaTemplate<String, Message> kafkaTemplate) {
        return new BiConsumerMessageProducer((message, channel) -> kafkaTemplate.send(channel, message));
    }
}
