package ar.edu.itba.cep.executor.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link ExecutorServiceCommandMessagesConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(ExecutorServiceCommandMessagesConfigurer.class)
public @interface EnableExecutorServiceCommandMessages {
}
