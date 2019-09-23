package ar.edu.itba.cep.executor.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * Properties for the executor service's command messages configurer.
 */
@Data
@ConfigurationProperties(prefix = "executor-service.command-messages")
/* package */ class ExecutorServiceCommandMessagesProperties {

    /**
     * Indicates the sender of the messages.
     */
    private String sender;

    /**
     * The properties for the execution request command message.
     */
    @NestedConfigurationProperty
    private RequestExecutionCommandMessageProperties requestExecution;


    /**
     * Wraps properties for the execution request command message.
     */
    @Data
    /* package */ static final class RequestExecutionCommandMessageProperties {
        /**
         * The channel to which the executor service must send the response of an execution request.
         */
        private String replyChannel;
    }
}
