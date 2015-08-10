package org.sistcoop.cooperativa.messages;

import org.sistcoop.cooperativa.provider.Provider;

/**
 * @author <a href="mailto:leonardo.zanivan@gmail.com">Leonardo Zanivan</a>
 */
public interface MessagesProvider extends Provider {

    String getMessage(String messageKey, Object... parameters);

}
