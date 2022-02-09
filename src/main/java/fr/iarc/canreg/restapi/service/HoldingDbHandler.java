package fr.iarc.canreg.restapi.service;

import canreg.common.database.HoldingDbCommon;
import canreg.server.CanRegServerImpl;
import canreg.server.database.CanRegDAO;
import canreg.server.management.SystemDescription;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handler for the holding databases.
 */
@RequiredArgsConstructor
public class HoldingDbHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(HoldingDbHandler.class);

    public final CanRegDAO canRegDAO;
    public final Properties dbProperties;
    public final SystemDescription mainSystemDescription;

    /**
     * Returns a CanRegDAO for the holding database of the API user
     * @param apiUserName the userName of the api user
     * @return CanRegDAO
     */
    public CanRegDAO getDaoForApiUser(String apiUserName) {
        // Additional variables for holding db
        SystemDescription systemDescriptionForHoldingDB 
                = HoldingDbCommon.buildSystemDescriptionForHoldingDB(mainSystemDescription);

        String registryCode = CanRegServerImpl.getRegistryCodeForApiHolding(
                mainSystemDescription.getRegistryCode(), apiUserName, false);
        return new CanRegDAO(registryCode, systemDescriptionForHoldingDB.getSystemDescriptionDocument(), dbProperties);
    }

}
