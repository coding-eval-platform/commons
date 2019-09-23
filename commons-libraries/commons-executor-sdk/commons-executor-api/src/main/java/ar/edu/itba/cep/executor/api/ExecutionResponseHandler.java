package ar.edu.itba.cep.executor.api;

import ar.edu.itba.cep.executor.models.ExecutionResponse;

/**
 * Defines behaviour for an object that can handle the executor service's command responses.
 */
public interface ExecutionResponseHandler<I extends ExecutionResponseIdData> {

    /**
     * Process the given {@code response}.
     *
     * @param response The {@link ExecutionResponse} to be processed.
     * @param idData   Data indicating how the reply message is identified.
     */
    void processExecutionResponse(final ExecutionResponse response, final I idData);
}
