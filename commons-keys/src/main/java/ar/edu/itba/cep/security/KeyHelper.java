package ar.edu.itba.cep.security;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.function.Function;

/**
 * A helper class for {@link Key} tasks,
 * such as generation of {@link java.security.PrivateKey}s or {@link java.security.PublicKey}s.
 */
public class KeyHelper {


    /**
     * Generates a {@link Key} of type {@code K} from the given {@link KeySpec} of type {@code S},
     * using the given {@link KeyFactory}. Generates a {@link Key} of type {@code K} from the given
     * {@code encodedKey}.
     *
     * @param keyFactory       The {@link KeyFactory} used to generate the {@link Key}.
     * @param encodedKey       The encoded key.
     * @param keySpecGenerator A {@link Function} that given a {@code byte[]}, returns a {@link
     *                         KeySpec} of type {@code S}. Will be called with the decoded version of the given {@code
     *                         encodedKey}.
     * @param keyGenerator     A {@link KeyGenerator}
     * @param <S>              The concrete type of {@link KeySpec}.
     * @param <K>              The concrete type of {@link Key}.
     * @return The generated {@link Key}.
     * @throws InvalidKeyException If the key is invalid for the {@link KeySpec}
     *                             generated by the given {@code keySpecGenerator}.
     * @implNote Will use {@link Base64.Decoder#decode(String)} to decode the given {@code encodedKey},
     * and its result will be used to call the given {@code keySpecGenerator}.
     */
    public static <S extends KeySpec, K extends Key> K generateKey(
            final KeyFactory keyFactory,
            final String encodedKey,
            final Function<byte[], S> keySpecGenerator,
            final KeyGenerator<S, K> keyGenerator) throws InvalidKeyException {

        final var decodedKeyString = Base64.getDecoder().decode(encodedKey);
        final var keySpec = keySpecGenerator.apply(decodedKeyString);
        try {
            return keyGenerator.generateKey(keyFactory, keySpec);
        } catch (final InvalidKeySpecException invalidKeySpecException) {
            throw new InvalidKeyException(invalidKeySpecException);
        }
    }


    /**
     * Defines behaviour for an object that can generate {@link Key}s from a {@link KeyFactory} and
     * an {@link KeySpec}
     *
     * @param <S> The concrete type of {@link KeySpec}.
     * @param <K> The concrete type of {@link Key}.
     */
    @FunctionalInterface
    public interface KeyGenerator<S extends KeySpec, K extends Key> {

        /**
         * Generates a {@link Key} of type {@code K} using the given {@code keyFactory} and {@code
         * keySpec}.
         *
         * @param keyFactory The {@link KeyFactory} used to generate the {@link Key}.
         * @param keySpec    The {@link KeySpec} from where the {@link Key} will be created.
         * @return The generated {@link Key}.
         * @throws InvalidKeySpecException If the implementation might throw the said exception.
         */
        K generateKey(final KeyFactory keyFactory, final S keySpec) throws InvalidKeySpecException;
    }


    /**
     * A {@link RuntimeException} to be thrown in exchange of an {@link InvalidKeySpecException}.
     */
    public static final class InvalidKeyException extends RuntimeException {

        /**
         * Private constructor.
         *
         * @param cause The {@link InvalidKeySpecException} that causes this exception to be thrown.
         */
        private InvalidKeyException(final InvalidKeySpecException cause) {
            super("Invalid key", cause);
        }
    }


    /**
     * Private constructor to avoid instantiation.
     */
    private KeyHelper() {
    }
}
