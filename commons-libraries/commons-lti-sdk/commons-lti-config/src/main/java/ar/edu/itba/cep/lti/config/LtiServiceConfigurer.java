package ar.edu.itba.cep.lti.config;

import ar.edu.itba.cep.lti.LtiService;
import ar.edu.itba.cep.lti.LtiServiceImpl;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class that enables the LTI service.
 */
@Configuration
@EnableConfigurationProperties(LtiServiceConfigurer.LtiServiceProperties.class)
public class LtiServiceConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(LtiServiceConfigurer.class);

    /**
     * Creates an {@link LtiService} bean.
     *
     * @param restTemplate A {@link RestTemplate} to be injected to the {@link LtiService}.
     * @param properties   The {@link LtiServiceProperties} needed to configure the {@link LtiService} being returned.
     * @return The created {@link LtiService} bean.
     */
    @Bean
    public LtiService ltiService(final RestTemplate restTemplate, final LtiServiceProperties properties) {
        return new LtiServiceImpl(restTemplate, properties.getBaseUrl());
    }

    /**
     * A {@link RestTemplate} bean creation method.
     *
     * @param restTemplateBuilder A {@link RestTemplateBuilder} needed to create the {@link RestTemplate}.
     * @return The created {@link RestTemplateBuilder}.
     */
    @Bean
    @Lazy
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public RestTemplate dummyRestTemplate(final RestTemplateBuilder restTemplateBuilder) {
        LOGGER.warn("No RestTemplate bean found. Creating one");
        return restTemplateBuilder.build();
    }

    /**
     * A {@link RestTemplateBuilder} bean creation method.
     * This method should not be executed as a {@link RestTemplateBuilder} always exist in a Spring Boot application.
     *
     * @return The created {@link RestTemplateBuilder}.
     */
    @Bean
    @Lazy
    @ConditionalOnMissingBean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public RestTemplateBuilder dummyRestTemplateBuilder() {
        LOGGER.warn("No RestTemplateBuilder bean found. Creating one");
        return new RestTemplateBuilder();
    }

    /**
     * Configuration properties for the {@link LtiService}.
     */
    @Data
    @ConfigurationProperties("lti-service")
    public static final class LtiServiceProperties {
        /**
         * The base url where the LTI is serving.
         */
        private String baseUrl = "http://lti-service/lti/app/";
    }
}
