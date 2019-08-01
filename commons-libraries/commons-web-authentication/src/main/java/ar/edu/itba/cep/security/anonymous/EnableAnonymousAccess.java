package ar.edu.itba.cep.security.anonymous;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link AnonymousAccessConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(AnonymousAccessConfigurer.class)
public @interface EnableAnonymousAccess {
}
