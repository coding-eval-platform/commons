package ar.edu.itba.cep.security.bearer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * Configuration class for the bearer token authentication mechanism.
 */
@Configuration
class BearerTokenConfigurer {

    /**
     * The {@link Logger}.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenConfigurer.class);


    /**
     * Creates a {@link BearerTokenAuthenticationFilter} bean.
     *
     * @param bearerTokenAuthenticationManager The {@link BearerTokenAuthenticationManager} needed by the
     *                                         {@link BearerTokenAuthenticationFilter}.
     * @return A {@link BearerTokenAuthenticationFilter} bean.
     */
    @Bean
    @Autowired
    @ConditionalOnMissingBean
    public BearerTokenAuthenticationFilter bearerTokenAuthenticationFilter(
            final BearerTokenAuthenticationManager bearerTokenAuthenticationManager) {
        return new BearerTokenAuthenticationFilter(bearerTokenAuthenticationManager);
    }


    /**
     * Creates a {@link BearerTokenAuthenticationManager} bean.
     *
     * @param tokenDecoder The {@link TokenDecoder} needed by the BearerTokenAuthenticationManager
     * @return A {@link BearerTokenAuthenticationManager} bean.
     */
    @Bean
    @Autowired
    @ConditionalOnMissingBean
    public BearerTokenAuthenticationManager bearerTokenAuthenticationManager(final TokenDecoder tokenDecoder) {
        return new BearerTokenAuthenticationManager(tokenDecoder);
    }

    /**
     * A "placeholder" {@link TokenDecoder} bean creation method.
     *
     * @return Nothing.
     * @throws IllegalStateException Always. This method is executed only if another {@link TokenDecoder} bean
     *                               is not defined in the scope.
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ConditionalOnMissingBean
    @Lazy
    public TokenDecoder dummyTokenDecoder() throws IllegalStateException {
        LOGGER.error("Missing TokenDecoder!");
        throw new IllegalStateException("A TokenDecoder was not found");
    }
}
