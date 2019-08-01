package ar.edu.itba.cep.security.anonymous;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class that setups an {@link AnonymousAccessFilter}.
 */
@Configuration
class AnonymousAccessConfigurer {

    /**
     * Creates a bean of an {@link AnonymousAccessFilter}.
     *
     * @return An {@link AnonymousAccessFilter} bean.
     */
    @Bean
    @ConditionalOnMissingBean
    public AnonymousAccessFilter anonymousAccessFilter() {
        return new AnonymousAccessFilter();
    }
}
