package org.sistcoop.certamb.models;

/**
 * @author <a href="mailto:mposolda@redhat.com">Marek Posolda</a>
 */
public class ModelReadOnlyException extends ModelException {

    private static final long serialVersionUID = 1L;

    public ModelReadOnlyException() {
    }

    public ModelReadOnlyException(String message) {
        super(message);
    }

    public ModelReadOnlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ModelReadOnlyException(Throwable cause) {
        super(cause);
    }
}
