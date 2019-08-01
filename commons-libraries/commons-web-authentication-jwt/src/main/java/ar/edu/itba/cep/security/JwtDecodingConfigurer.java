package ar.edu.itba.cep.security;

import ar.edu.itba.cep.security.bearer.TokenDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * Configuration class for JWT decoding stuff.
 */
@Configuration
@EnableKeyFactory
@EnableConfigurationProperties(JwtDecodingProperties.class)
public class JwtDecodingConfigurer {

    /**
     * The {@link Logger}.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtDecodingConfigurer.class);


    /**
     * Creates a {@link PublicKey} from the {@link KeyFactory} and the {@link JwtDecodingProperties}.
     *
     * @param keyFactory            The {@link KeyFactory} used to generate the public key.
     * @param jwtDecodingProperties The {@link JwtDecodingProperties} from where configuration values will be taken.
     * @return The {@link PublicKey}.
     */
    @Bean
    @Autowired
    @ConditionalOnMissingBean
    public PublicKey publicKey(final KeyFactory keyFactory, final JwtDecodingProperties jwtDecodingProperties) {
        try {
            return KeyHelper.generateKey(
                    keyFactory,
                    jwtDecodingProperties.getPublicKey(),
                    X509EncodedKeySpec::new,
                    KeyFactory::generatePublic
            );
        } catch (final KeyHelper.InvalidKeyException e) {
            LOGGER.error("The key that was set is not valid!", e);
            throw e;
        }
    }

    @Bean
    @Autowired
    @Primary
    public TokenDecoder jwtTokenDecoder(final PublicKey publicKey) {
        return new JwtTokenDecoder(publicKey);
    }
}
