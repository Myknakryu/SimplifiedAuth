package pl.myku.simplifiedAuth;

import net.fabricmc.api.ModInitializer;
import pl.myku.simplifiedAuth.db.JsonDbManager;
import pl.myku.simplifiedAuth.db.IDbManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.util.ConfigHandler;

import java.util.Properties;


public class SimplifiedAuth implements ModInitializer {
    public static final String MOD_ID = "simplifiedauth";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final ConfigHandler config;
    static {
        Properties props = new Properties();
        props.setProperty("DatabaseManager","json");
        props.setProperty("Schema", "users");
        props.setProperty("Address", "./db/");
        props.setProperty("Port", "");
        props.setProperty("Username", "");
        props.setProperty("Password", "");
        config = new ConfigHandler(MOD_ID, props);
    }

    public static IDbManager dbManager;
    public static PlayerManager playerManager = new PlayerManager();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialization of Simplified auth mod.");
        switch (config.getString("DatabaseManager")){
            case "json":
            default:
            {
                dbManager = new JsonDbManager(config.getString("Address"),config.getString("Schema"));
                break;
            }
        }
        LOGGER.info("Simplified Auth initialized.");
    }
}
