package org.sistcoop.certamb.mappers;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class MapperConfigValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    public MapperConfigValidationException(String message) {
        super(message);
    }

    public MapperConfigValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
