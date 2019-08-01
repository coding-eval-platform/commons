package ar.edu.itba.cep.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.Assert;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 * Configuration class to setup a {@link KeyFactory}.
 */
@Configuration
class KeyFactoryConfigurer implements ImportAware, InitializingBean {

    /**
     * The {@link AnnotationMetadata}.
     */
    private AnnotationMetadata importMetadata = null;


    @Override
    public void setImportMetadata(final AnnotationMetadata importMetadata) {
        this.importMetadata = importMetadata;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(importMetadata != null, "The importMetadata must not be null");
    }

    /**
     * Creates a bean of a {@link KeyFactory}.
     *
     * @return A {@link KeyFactory} bean.
     * @throws NoSuchAlgorithmException Never.
     */
    @Bean
    public KeyFactory keyFactory() throws NoSuchAlgorithmException {
        final var algorithm = getKeyFactoryAlgorithm();
        return KeyFactory.getInstance(algorithm);
    }

    /**
     * Retrieves the algorithm to be used by the {@link KeyFactory} to be created from the {@link EnableKeyFactory}
     * annotation that imported this configurator class.
     *
     * @return The {@link KeyFactory} algorithm.
     */
    private String getKeyFactoryAlgorithm() {
        Assert.state(importMetadata != null, "The importMetadata must not be null");
        return Optional.of(importMetadata)
                .map(AnnotationMetadata::getClassName)
                .map(className -> {
                    try {
                        return Class.forName(className);
                    } catch (final ClassNotFoundException e) {
                        throw new RuntimeException("Class " + className + " was not found");
                    }
                })
                .map(importingClass -> AnnotationUtils.findAnnotation(importingClass, EnableKeyFactory.class))
                .map(EnableKeyFactory::algorithm)
                .orElseThrow(IllegalArgumentException::new)
                ;
    }
}
