package ar.edu.itba.cep.security.bearer;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

/**
 * A {@link PreAuthenticatedAuthenticationToken} that stores a raw bearer token.
 */
class PreAuthenticatedBearerToken extends PreAuthenticatedAuthenticationToken {

    /**
     * Constructor.
     *
     * @param rawToken The raw bearer token.
     */
    /* package */ PreAuthenticatedBearerToken(final String rawToken) {
        super(rawToken, "");
        super.setAuthenticated(false); // Make sure this Authentication instance is not authenticated.
    }

    /**
     * @return The Raw bearer token.
     */
    /* package */String getRawToken() {
        return (String) getPrincipal();
    }


    /**
     * {@inheritDoc}.
     *
     * @throws UnsupportedOperationException This extension of {@link org.springframework.security.core.Authentication}
     *                                       cannot be authenticated.
     */
    @Override
    public final void setAuthenticated(final boolean authenticated) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This token cannot be authenticated");
    }

    /**
     * {@inheritDoc}.
     *
     * @throws UnsupportedOperationException This extension of {@link org.springframework.security.core.Authentication}
     *                                       cannot store details.
     */
    @Override
    public final void setDetails(final Object details) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This tokens cannot store details");
    }
}
