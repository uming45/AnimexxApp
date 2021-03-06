package de.meisterfuu.animexx.services;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

public class AccountService extends Service {

    private static AccountAuthenticatorImpl sAccountAuthenticator = null;

    public AccountService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        IBinder ret = null;
        if (intent.getAction().equals(android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT))
            ret = getAuthenticator().getIBinder();
        return ret;
    }

    private AccountAuthenticatorImpl getAuthenticator() {
        if (sAccountAuthenticator == null)
            sAccountAuthenticator = new AccountAuthenticatorImpl(this);
        return sAccountAuthenticator;
    }

    private static class AccountAuthenticatorImpl extends AbstractAccountAuthenticator {
        private Context mContext;

        public AccountAuthenticatorImpl(Context context) {
            super(context);
            mContext = context;
        }

        @Override
        public Bundle addAccount(AccountAuthenticatorResponse response,
                                 String accountType, String authTokenType,
                                 String[] requiredFeatures, Bundle options)
                throws NetworkErrorException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Bundle confirmCredentials(AccountAuthenticatorResponse response,
                                         Account account, Bundle options) throws NetworkErrorException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Bundle editProperties(AccountAuthenticatorResponse response,
                                     String accountType) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Bundle getAuthToken(AccountAuthenticatorResponse response,
                                   Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public String getAuthTokenLabel(String authTokenType) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Bundle hasFeatures(AccountAuthenticatorResponse response,
                                  Account account, String[] features)
                throws NetworkErrorException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Bundle updateCredentials(AccountAuthenticatorResponse response,
                                        Account account, String authTokenType, Bundle options)
                throws NetworkErrorException {
            // TODO Auto-generated method stub
            return null;
        }


    }
}