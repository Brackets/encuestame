package org.encuestame.core.service;

import java.io.IOException;

import org.encuestame.core.config.AdministratorProfile;
import org.encuestame.persistence.exception.EnmeFailOperation;
import org.encuestame.utils.web.UserAccountBean;


public interface SetupOperations {

    String installDatabase() throws EnmeFailOperation, IOException;

    Boolean removeTables();

    void demoInstall();

    void upgradeDatabase();

    String getSQLExecuted();

    String checkStatus();

    void validateInstall();

    UserAccountBean createUserAdministration(final AdministratorProfile administratorProfile);

    java.util.List<String> loadInstallParameters();

    Boolean checkDatabase();

}