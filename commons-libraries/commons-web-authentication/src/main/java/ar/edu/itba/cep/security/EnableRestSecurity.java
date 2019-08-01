package ar.edu.itba.cep.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link RestSecurityConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(RestSecurityConfigurer.class)
public @interface EnableRestSecurity {
}
