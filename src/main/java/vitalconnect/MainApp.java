package vitalconnect;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import vitalconnect.commons.core.Config;
import vitalconnect.commons.core.LogsCenter;
import vitalconnect.commons.core.Version;
import vitalconnect.commons.exceptions.DataLoadingException;
import vitalconnect.commons.util.ConfigUtil;
import vitalconnect.commons.util.StringUtil;
import vitalconnect.logic.Logic;
import vitalconnect.logic.LogicManager;
import vitalconnect.model.Appointment;
import vitalconnect.model.Clinic;
import vitalconnect.model.Model;
import vitalconnect.model.ModelManager;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.ReadOnlyUserPrefs;
import vitalconnect.model.UserPrefs;
import vitalconnect.model.util.SampleDataUtil;
import vitalconnect.storage.AppointmentStorage;
import vitalconnect.storage.ClinicStorage;
import vitalconnect.storage.JsonAppointmentStorage;
import vitalconnect.storage.JsonClinicStorage;
import vitalconnect.storage.JsonUserPrefsStorage;
import vitalconnect.storage.Storage;
import vitalconnect.storage.StorageManager;
import vitalconnect.storage.UserPrefsStorage;
import vitalconnect.ui.Ui;
import vitalconnect.ui.UiManager;

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

    /**
     * Initializes the application's primary components.
     */
    @Override
    public void init() throws Exception {
        logger.info("=============================[ Initializing Clinic ]===========================");
        super.init();

        AppParameters appParameters = AppParameters.parse(getParameters());
        config = initConfig(appParameters.getConfigPath());
        initLogging(config);

        UserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(config.getUserPrefsFilePath());
        UserPrefs userPrefs = initPrefs(userPrefsStorage);
        ClinicStorage clinicStorage = new JsonClinicStorage(userPrefs.getClinicFilePath());
        AppointmentStorage appointmentStorage = new JsonAppointmentStorage(userPrefs.getAppointmentFilePath());
        storage = new StorageManager(clinicStorage, userPrefsStorage, appointmentStorage);


        model = initModelManager(storage, userPrefs);

        logic = new LogicManager(model, storage);

        ui = new UiManager(logic);
    }

    /**
     * Returns a {@code ModelManager} with the data from {@code storage}'s clinic and {@code userPrefs}. <br>
     * The data from the sample clinic will be used instead if {@code storage}'s clinic is not found,
     * or an empty clinic will be used instead if errors occur when reading {@code storage}'s clinic.
     */
    private Model initModelManager(Storage storage, ReadOnlyUserPrefs userPrefs) {
        logger.info("Using data file : " + storage.getClinicFilePath());

        Optional<ReadOnlyClinic> clinicOptional;
        ReadOnlyClinic initialData;
        Optional<List<Appointment>> appointmentsOptional;
        List<Appointment> appointments;
        try {
            clinicOptional = storage.readClinic();
            if (!clinicOptional.isPresent()) {
                logger.info("Creating a new data file " + storage.getClinicFilePath()
                        + " populated with a sample Clinic.");
            }
            initialData = clinicOptional.orElseGet(SampleDataUtil::getSampleClinic);
        } catch (DataLoadingException e) {
            logger.warning("Data file at " + storage.getClinicFilePath() + " could not be loaded."
                    + " Will be starting with an empty Clinic.");
            initialData = new Clinic();
        }

        try {
            appointmentsOptional = storage.readAppointments();
            if (!appointmentsOptional.isPresent()) {
                logger.info("Appointment data file not found. Will be starting with an empty appointment list.");
            }
            appointments = appointmentsOptional.orElseGet(Collections::emptyList);
        } catch (DataLoadingException e) {
            logger.warning("Data file for appointments could not be loaded. "
                    + "Will be starting with an empty appointment list.");
            appointments = new ArrayList<>();
        }

        return new ModelManager(initialData, userPrefs, appointments);
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

    /**
     * Starts the primary stage of the application.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting Clinic " + MainApp.VERSION);
        ui.start(primaryStage);
    }

    /**
     * Stops the application, ensuring all resources are released and necessary data is saved.
     */
    @Override
    public void stop() {
        logger.info("============================ [ Stopping Clinic ] =============================");
        try {
            storage.saveUserPrefs(model.getUserPrefs());
            // Save appointments when application stops
            storage.saveAppointments(model.getFilteredAppointmentList());
        } catch (IOException e) {
            logger.severe("Failed to save preferences and appointments: " + StringUtil.getDetails(e));
        }
    }
}
