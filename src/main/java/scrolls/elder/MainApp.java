package scrolls.elder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import scrolls.elder.commons.core.Config;
import scrolls.elder.commons.core.LogsCenter;
import scrolls.elder.commons.core.Version;
import scrolls.elder.commons.exceptions.DataLoadingException;
import scrolls.elder.commons.util.ConfigUtil;
import scrolls.elder.commons.util.StringUtil;
import scrolls.elder.logic.Logic;
import scrolls.elder.logic.LogicManager;
import scrolls.elder.model.Datastore;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.ReadOnlyUserPrefs;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.util.SampleDataUtil;
import scrolls.elder.storage.DatastoreStorage;
import scrolls.elder.storage.JsonDatastoreStorage;
import scrolls.elder.storage.JsonUserPrefsStorage;
import scrolls.elder.storage.Storage;
import scrolls.elder.storage.StorageManager;
import scrolls.elder.storage.UserPrefsStorage;
import scrolls.elder.ui.Ui;
import scrolls.elder.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(0, 2, 2, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Elder Scrolls ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        DatastoreStorage datastoreStorage = new JsonDatastoreStorage(userPrefs.getDatastoreFilePath());
        storage = new StorageManager(datastoreStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s datastore and {@code userPrefs}. <br>
     * The data from the sample datastore will be used instead if {@code storage}'s datastore is not found,
     * or an empty datastore will be used instead if errors occur when reading {@code storage}'s datastore.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getDatastoreFilePath());

        Optional<ReadOnlyDatastore> datastoreOptional;
        ReadOnlyDatastore initialData;
        try {
            datastoreOptional = storage.readDatastore();
            if (datastoreOptional.isEmpty()) {
                logger.info("Creating a new data file " + storage.getDatastoreFilePath()
                        + " populated with sample data.");
            }
            initialData = datastoreOptional.orElseGet(SampleDataUtil::getSampleDatastore);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getDatastoreFilePath() + " could not be loaded."
                    + " Will be starting no data.");
            initialData = new Datastore();
        }

        return new ModelManager(initialData, userPrefs);
    }

    private void initLogging(Config config) {
        LogsCenter.init(config);
    }

    /**
     * Returns a {@code Config} using the file at {@code configFilePath}. <br>
     * The default file path {@code Config#DEFAULT_CONFIG_FILE} will be used instead
     * if {@code configFilePath} is null.
     */
    protected Config initConfig(Path configFilePath) {
        Config initializedConfig;
        Path configFilePathUsed;

        configFilePathUsed = Config.DEFAULT_CONFIG_FILE;

        if (configFilePath != null) {
            logger.info("Custom Config file specified " + configFilePath);
            configFilePathUsed = configFilePath;
        }

        logger.info("Using config file : " + configFilePathUsed);

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(configFilePathUsed);
            if (!configOptional.isPresent()) {
                logger.info("Creating new config file " + configFilePathUsed);
            }
            initializedConfig = configOptional.orElse(new Config());
        } catch (DataLoadingException e) {
            logger.warning("Config file at " + configFilePathUsed + " could not be loaded."
                    + " Using default config properties.");
            initializedConfig = new Config();
        }

        //Update config file in case it was missing to begin with or there are new/unused fields
        try {
            ConfigUtil.saveConfig(initializedConfig, configFilePathUsed);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }
        return initializedConfig;
    }

    /**
     * Returns a {@code UserPrefs} using the file at {@code storage}'s user prefs file path,
     * or a new {@code UserPrefs} with default configuration if errors occur when
     * reading from the file.
     */
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        Path prefsFilePath = storage.getUserPrefsFilePath();
        logger.info("Using preference file : " + prefsFilePath);

        UserPrefs initializedPrefs;
        try {
            Optional<UserPrefs> prefsOptional = storage.readUserPrefs();
            if (!prefsOptional.isPresent()) {
                logger.info("Creating new preference file " + prefsFilePath);
            }
            initializedPrefs = prefsOptional.orElse(new UserPrefs());
        } catch (DataLoadingException e) {
            logger.warning("Preference file at " + prefsFilePath + " could not be loaded."
                    + " Using default preferences.");
            initializedPrefs = new UserPrefs();
        }

        //Update prefs file in case it was missing to begin with or there are new/unused fields
        try {
            storage.saveUserPrefs(initializedPrefs);
        } catch (IOException e) {
            logger.warning("Failed to save config file : " + StringUtil.getDetails(e));
        }

        return initializedPrefs;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Elder Scrolls " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping Elder Scrolls ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
