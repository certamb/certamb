/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.sistcoop.certamb.services.messages;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class Messages {

    public static final String ACCOUNT_DISABLED = "accountDisabled";
    public static final String ACCOUNT_TEMPORARILY_DISABLED = "accountTemporarilyDisabled";

    public static final String INVALID_PASSWORD = "invalidPassword";

    public static final String INVALID_PASSWORD_EXISTING = "invalidPasswordExisting";

    public static final String INVALID_PASSWORD_CONFIRM = "invalidPasswordConfirm";

    public static final String INVALID_EMAIL = "invalidEmail";

    public static final String INVALID_USER = "invalidUser";

    public static final String EXPIRED_CODE = "expiredCode";

    public static final String READ_ONLY_USER = "readOnlyUser";

    public static final String READ_ONLY_PASSWORD = "readOnlyPassword";

    public static final String MISSING_EMAIL = "missingEmail";

    public static final String MISSING_FIRST_NAME = "missingFirstName";

    public static final String MISSING_LAST_NAME = "missingLastName";

    public static final String MISSING_PASSWORD = "missingPassword";

    public static final String NOTMATCH_PASSWORD = "notMatchPassword";

    public static final String MISSING_USERNAME = "missingUsername";

    public static final String MISSING_TOTP = "missingTotp";

    public static final String INVALID_TOTP = "invalidTotp";

    public static final String USERNAME_EXISTS = "usernameExists";

    public static final String EMAIL_EXISTS = "emailExists";

    public static final String ACTION_WARN_TOTP = "actionTotpWarning";

    public static final String ACTION_WARN_PROFILE = "actionProfileWarning";

    public static final String ACTION_WARN_PASSWD = "actionPasswordWarning";

    public static final String ACTION_WARN_EMAIL = "actionEmailWarning";

    public static final String MISSING_IDENTITY_PROVIDER = "missingIdentityProvider";

    public static final String INVALID_FEDERATED_IDENTITY_ACTION = "invalidFederatedIdentityAction";

    public static final String IDENTITY_PROVIDER_NOT_FOUND = "identityProviderNotFound";

    public static final String FEDERATED_IDENTITY_NOT_ACTIVE = "federatedIdentityLinkNotActive";

    public static final String FEDERATED_IDENTITY_REMOVING_LAST_PROVIDER = "federatedIdentityRemovingLastProvider";

    public static final String IDENTITY_PROVIDER_REDIRECT_ERROR = "identityProviderRedirectError";

    public static final String IDENTITY_PROVIDER_REMOVED = "identityProviderRemoved";

    public static final String ERROR = "error";

}
