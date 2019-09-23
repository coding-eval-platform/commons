package ar.edu.itba.cep.executor.api;

import ar.edu.itba.cep.executor.models.ExecutionRequest;

/**
 * Defines behaviour for an object that can send command messages to the executor service.
 */
public interface ExecutionRequestSender<I extends ExecutionResponseIdData> {

    /**
     * Requests an execution to the executor service.
     *
     * @param executionRequest The {@link ExecutionRequest} to be sent to the executor service.
     * @param idData           Data indicating how the reply message must be identified.
     */
    void requestExecution(final ExecutionRequest executionRequest, final I idData);
}
