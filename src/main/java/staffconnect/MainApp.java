package staffconnect;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import staffconnect.commons.core.Config;
import staffconnect.commons.core.LogsCenter;
import staffconnect.commons.core.Version;
import staffconnect.commons.exceptions.DataLoadingException;
import staffconnect.commons.util.ConfigUtil;
import staffconnect.commons.util.StringUtil;
import staffconnect.logic.Logic;
import staffconnect.logic.LogicManager;
import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.ReadOnlyStaffBook;
import staffconnect.model.ReadOnlyUserPrefs;
import staffconnect.model.StaffBook;
import staffconnect.model.UserPrefs;
import staffconnect.model.util.SampleDataUtil;
import staffconnect.storage.JsonStaffBookStorage;
import staffconnect.storage.JsonUserPrefsStorage;
import staffconnect.storage.StaffBookStorage;
import staffconnect.storage.Storage;
import staffconnect.storage.StorageManager;
import staffconnect.storage.UserPrefsStorage;
import staffconnect.ui.Ui;
import staffconnect.ui.UiManager;

/**
 * Runs the application.
 */
public class MainApp extends Application {

    public static final Version VERSION = new Version(1, 2, 0, true);

    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    protected Ui ui;
    protected Logic logic;
    protected Storage storage;
    protected Model model;
    protected Config config;

    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing StaffConnect ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        StaffBookStorage staffBookStorage = new JsonStaffBookStorage(userPrefs.getStaffConnectFilePath());
        storage = new StorageManager(staffBookStorage, userPrefsStorage);

        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s staff book and {@code userPrefs}. <br>
     * The data from the sample staff book will be used instead if {@code storage}'s staff book is not found,
     * or an empty staff book will be used instead if errors occur when reading {@code storage}'s staff book.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getStaffBookFilePath());

        Optional<ReadOnlyStaffBook> staffBookOptional;
        ReadOnlyStaffBook initialData;
        try {
            staffBookOptional = storage.readStaffBook();
            if (!staffBookOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getStaffBookFilePath()
                        + " populated with a sample StaffBook.");
            }
            initialData = staffBookOptional.orElseGet(SampleDataUtil::getSampleStaffBook);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getStaffBookFilePath() + " could not be loaded."
                    + " Will be starting with an empty StaffBook.");
            initialData = new StaffBook();
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
        logger.info("Starting StaffConnect " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    @Override
    public void stop() {
        logger.info("============================ [ Stopping StaffConnect ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            logger.severe("Failed to save preferences " + StringUtil.getDetails(e));
        }
    }
}
