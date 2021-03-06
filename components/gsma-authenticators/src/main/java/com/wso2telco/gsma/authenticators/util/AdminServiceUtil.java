package com.wso2telco.gsma.authenticators.util;

import com.wso2telco.gsma.authenticators.internal.CustomAuthenticatorServiceComponent;
import org.wso2.carbon.identity.application.authentication.framework.exception.AuthenticationFailedException;
import org.wso2.carbon.user.api.UserRealm;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.core.UserStoreManager;
import org.wso2.carbon.utils.multitenancy.MultitenantUtils;

/**
 * Created by isuru on 12/22/16.
 */
public class AdminServiceUtil {

    public static boolean isUserExists(String msisdn) throws UserStoreException, AuthenticationFailedException {
        int tenantId = -1234;
        UserRealm userRealm = CustomAuthenticatorServiceComponent.getRealmService()
                .getTenantUserRealm(tenantId);

        if (userRealm != null) {
            UserStoreManager userStoreManager = (UserStoreManager) userRealm.getUserStoreManager();
            return userStoreManager.isExistingUser(MultitenantUtils.getTenantAwareUsername(msisdn));
        } else {
            throw new AuthenticationFailedException("Cannot find the user realm for the given tenant: " + tenantId);
        }
    }
}
