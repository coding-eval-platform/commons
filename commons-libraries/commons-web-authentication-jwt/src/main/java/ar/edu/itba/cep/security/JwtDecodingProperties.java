package ar.edu.itba.cep.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties for configuring encoding/decoding of JWT tokens.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "authentication.jwt.api-gateway")
/* package */ class JwtDecodingProperties {

    /**
     * The public key used to verify external tokens.
     */
    private String publicKey;
}
