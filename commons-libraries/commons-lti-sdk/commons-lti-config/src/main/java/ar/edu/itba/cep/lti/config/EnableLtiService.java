package ar.edu.itba.cep.lti.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link LtiServiceConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(LtiServiceConfigurer.class)
public @interface EnableLtiService {
}
