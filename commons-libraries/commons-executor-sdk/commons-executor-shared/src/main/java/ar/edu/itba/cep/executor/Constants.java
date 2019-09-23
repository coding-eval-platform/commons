package ar.edu.itba.cep.executor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * Class with several constants to be used across the Kafka Command Senders' Executor Service proxy module.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    /**
     * The Reply-Channel header key. Asked by the Executor Service in order to receive the response from it.
     */
    public static final String REPLY_CHANNEL_HEADER = "Reply-Channel";


    // ================================================================================================================
    // Executor Service stuff
    // ================================================================================================================

    /**
     * Topic to which {@link ar.edu.itba.cep.executor.models.ExecutionRequest}s are sent
     * to the Executor Service.
     */
    public static final String EXECUTOR_SERVICE_COMMANDS_CHANNEL = "ExecutorService-Commands";
}
