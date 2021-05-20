package exceptions;

/**
 * An exception class used to notify that an error has occurred while trying to
 * access JDBC data.
 *
 * @see java.sql.SQLException
 */
public class DataAccessException extends Exception {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
