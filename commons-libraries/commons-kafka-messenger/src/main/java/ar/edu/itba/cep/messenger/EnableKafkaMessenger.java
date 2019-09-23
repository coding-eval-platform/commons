package ar.edu.itba.cep.messenger;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link KafkaMessengerConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(KafkaMessengerConfigurer.class)
public @interface EnableKafkaMessenger {
}
