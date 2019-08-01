package ar.edu.itba.cep.security.bearer;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link BearerTokenConfigurer}
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(BearerTokenConfigurer.class)
public @interface EnableBearerTokenAuthentication {
}
