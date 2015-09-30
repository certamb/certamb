package org.sistcoop.cooperativa.messages;

/**
 * @author <a href="mailto:leonardo.zanivan@gmail.com">Leonardo Zanivan</a>
 */

public interface MessagesProviderFactory {

    public MessagesProvider create();

    public void init();

    public void postInit();

    public void close();

    public String getId();

}
