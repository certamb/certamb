package org.sistcoop.certamb.services.messages;

import java.util.Locale;

import javax.enterprise.context.Dependent;

import org.sistcoop.certamb.messages.MessagesProvider;
import org.sistcoop.certamb.messages.MessagesProviderFactory;

/**
 * @author <a href="mailto:leonardo.zanivan@gmail.com">Leonardo Zanivan</a>
 */
@Dependent
public class AdminMessagesProviderFactory implements MessagesProviderFactory {

    @Override
    public MessagesProvider create() {
        return new AdminMessagesProvider(Locale.ENGLISH);
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
    }

    @Override
    public void close() {
    }

    @Override
    public String getId() {
        return "admin";
    }

}
