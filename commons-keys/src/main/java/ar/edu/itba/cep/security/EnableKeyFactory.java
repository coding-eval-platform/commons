package ar.edu.itba.cep.security;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Imports the {@link KeyFactoryConfigurer}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import(KeyFactoryConfigurer.class)
public @interface EnableKeyFactory {

    /**
     * The name of the requested key algorithm.
     *
     * @return The name of the requested key algorithm.
     * @see java.security.KeyFactory#getInstance(String)
     */
    String algorithm() default "RSA";

}
