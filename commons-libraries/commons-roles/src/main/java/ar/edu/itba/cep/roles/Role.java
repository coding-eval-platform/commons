package ar.edu.itba.cep.roles;

/**
 * Enum containing the different roles of the platform.
 */
public enum Role {

    /**
     * Indicates that a user is a teacher.
     */
    TEACHER,

    /**
     * Indicates that a user is a student.
     */
    STUDENT,

    /**
     * Indicates that a user is an admin of the platform.
     */
    ADMIN,
    ;

    /**
     * Returns the {@link Role} corresponding to the given {@code value}.
     *
     * @param value The {@link String} representation of the {@link Role} to be returned.
     * @return The {@link Role} corresponding to the given {@code value}.
     * @throws IllegalArgumentException If the given {@code value} is {@code null} or there is no {@link Role}
     *                                  corresponding to the given {@code value}.
     * @apiNote This method allows any letter case.
     */
    public static Role fromString(final String value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("The value must not be null");
        }
        return valueOf(value.toUpperCase());
    }
}
