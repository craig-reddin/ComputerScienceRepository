package teamprojectsavecare;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.Period;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;

public class SaveCareGUI extends javax.swing.JFrame implements Runnable, ThreadFactory {

    /**
     * Creates new form SaveCareGUI
     */
    //QR Scanner Objects
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Thread t;

    //Executes the runnable tasks (camera) Orchestates the mechanics of running tasks
    private Executor executor = Executors.newSingleThreadExecutor(this);

    //Colors used for UI interaction chnanges of page components.
    public Color defaultColor, blackColor;
    public Color hoverColor;
    public Color whiteColor;
    public Color greenBlueColor;

    //User objects of the application
    private Patient patient;
    private Doctor doctor;
    private User user;

    // The bellow integer fields are used to navigate through the patients medical history.
    // The Arraylists are used to store the patients medical history.
    private ArrayList<MedicationPrescribed> medsPrescribed;
    private int medicationPrescribedNumber;
    private int prescriptionUpdateIndex;
    private ArrayList<ProceduresCompleted> proceduresCompleted;
    private int proceduresCompletedNumber;
    private int procedureCompletedUpdatIndex;
    private ArrayList<Appointment> surgeryAppointments;
    private int surgeryAppointmentNumber;
    private int surgeryAppointmentUpdateIndex;
    private ArrayList<Appointment> hospitalAppointments;
    private int hospitalAppointmentNumber;
    private int hospitalAppointmentUpdateIndex;
    private ArrayList<String> allAppointmentTimes;
    private ArrayList<Medication> medicationNameDose;
    private ArrayList<Procedures> Allprocedure;
    private ArrayList<Clinic> clinic;
    private ArrayList<Medication> medicationList;

    private ArrayList<AppointmentHospitalSetter> appointmentComboList;

    //This arraylist stores all times available to book for a doctor, later used to load into jcombobox.
    private ArrayList<String> appointmentTimes;

    //CSV Doctor data extraction object. Used to extract existing doctors from hospital to be inserted into new application.
    //Arraylist hold a list of number values representing the days in each month.
    //Used to load days of each month to be loaded into combo box when a certain month is selected
    private ArrayList<Integer> months;
    ArrayList<String> patientUpdateFields;

    //SQLite database object. Loads the database locally into the C:\\ folder 
    private LocalSQLiteDatabase db;

    public SaveCareGUI() {
        initComponents();

        //db = new LocalSQLiteDatabase();
        // db.loadDatabase();
        initializeComponents();

    }

    public void logOut() {

        //if webcam.close is user when webcam is null. a error is thrown. stops the webcam trying to be closed when already closed.
        if (this.webcam != null) {
            webcam.close();
            webcam = null;

        }

        initializeComponents();

    }

    //Below method is used to intialize all nessesarry components. Used when the user logs out but the application is not closed.
    public void initializeComponents() {
        t = new Thread();
        allAppointmentTimes = new ArrayList<>();
        appointmentTimes = new ArrayList<>();
        clinic = new ArrayList<>();
        appointmentComboList = new ArrayList<>();
        months = new ArrayList<>();
        HomeMenu.setVisible(true);
        HomePagePanel.setVisible(true);
        user = new User();
        medsPrescribed = new ArrayList<>();
        proceduresCompleted = new ArrayList<>();
        hospitalAppointments = new ArrayList<>();
        surgeryAppointments = new ArrayList<>();
        medicationNameDose = new ArrayList<>();
        greenBlueColor = new Color(14, 78, 108);
        defaultColor = new Color(15, 15, 15);
        hoverColor = new Color(52, 149, 235);
        whiteColor = new Color(255, 255, 255);
        blackColor = new Color(0, 0, 0);
        patientUpdateFields = new ArrayList<>();
        medicationList = new ArrayList<>();
        Allprocedure = new ArrayList<>();
        fillUpdateFields();
        patient = new Patient("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        doctor = new Doctor("", "", "", "", "", "", "", "", "", "", "", "");
        user.setMyConn(user.getConnection());
        patient.setMyConn(user.getMyConn());
        doctor.setMyConn(user.getMyConn());

        CreateUserOption.setVisible(false);
        MainMenuPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        BenefitsPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        MainMenuPanel.setVisible(false);
        UserLoggedInPanel.setVisible(false);
        QRScannerPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(false);
        createHospitalIdLabel.setVisible(false);
        CreateHospitalID.setVisible(false);
        createDoctorLabel.setVisible(false);
        createDoctorNumber.setVisible(false);
        PrescribMedicationPanel.setVisible(false);
        ProceduresCompletedPanel.setVisible(false);
        AppointmentAddPanel.setVisible(false);
        UserLoggedInPanel.setVisible(false);
        LifeStylePanel.setVisible(false);
        SurgeryAppointmentPanel.setVisible(false);
        medicationPrescribedNumber = 0;
        prescriptionUpdateIndex = 0;
        proceduresCompletedNumber = 0;
        procedureCompletedUpdatIndex = 0;
        surgeryAppointmentNumber = 0;
        surgeryAppointmentUpdateIndex = 0;
        hospitalAppointmentNumber = 0;
        hospitalAppointmentUpdateIndex = 0;

        // This method loads each months number of days into an arraylist
        loadData();

        //method to load times into appointment times arraylist for combo box
        loadTimes();

        //Belo
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel33 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        signInBackground = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        panel1 = new java.awt.Panel();
        MenuContainer = new java.awt.Panel();
        MainMenuPanel = new java.awt.Panel();
        mainMenuLogo = new javax.swing.JLabel();
        mainMenuPersonalInfoButton = new javax.swing.JLabel();
        mainMenuMedicalHistoryButton = new javax.swing.JLabel();
        mainUserOptions = new javax.swing.JLabel();
        mainMenuLogOutButton = new javax.swing.JLabel();
        HomeMenu = new java.awt.Panel();
        homeSignInButton = new javax.swing.JLabel();
        homeContactUsButton = new javax.swing.JLabel();
        homeAboutUsButton = new javax.swing.JLabel();
        homeBenefitsButton = new javax.swing.JLabel();
        homeMenuLogo = new javax.swing.JLabel();
        homeMenuButton = new javax.swing.JLabel();
        ApplicationInterfacePanel = new java.awt.Panel();
        HomePagePanel = new java.awt.Panel();
        HomeHeading = new javax.swing.JLabel();
        homeTransformHeading = new javax.swing.JLabel();
        hoeJoinHeading = new javax.swing.JLabel();
        homeJoinUsButton = new javax.swing.JLabel();
        homePageBackground = new javax.swing.JLabel();
        CreateAccountPanel = new java.awt.Panel();
        jLabel16 = new javax.swing.JLabel();
        createUserFieldsContainer = new java.awt.Panel();
        createLastNameLabel = new javax.swing.JLabel();
        createPatientLastName = new javax.swing.JTextField();
        createEircodeLabel = new javax.swing.JLabel();
        createRePassword = new javax.swing.JTextField();
        createPatientEmail = new javax.swing.JTextField();
        createFirstNameLabel = new javax.swing.JLabel();
        createMaritalStatuseLabel = new javax.swing.JLabel();
        createPatientEircode = new javax.swing.JTextField();
        createPatientMobileNumber = new javax.swing.JTextField();
        createSexLabel = new javax.swing.JLabel();
        createNationalityLabel = new javax.swing.JLabel();
        createPatientAddress = new javax.swing.JTextField();
        createMobileLabel = new javax.swing.JLabel();
        createRePasswordLabel = new javax.swing.JLabel();
        createDOBLabel = new javax.swing.JLabel();
        createEmailLabel = new javax.swing.JLabel();
        createRaceLabel = new javax.swing.JLabel();
        createAddressLabel = new javax.swing.JLabel();
        createPatientFirstName = new javax.swing.JTextField();
        createAccountBtn = new javax.swing.JLabel();
        createClearButton = new javax.swing.JLabel();
        createPasswordLabel = new javax.swing.JLabel();
        createPPSLabel = new javax.swing.JLabel();
        createPatientPassword = new javax.swing.JTextField();
        createPatientPPS = new javax.swing.JTextField();
        createDoctorNumber = new javax.swing.JTextField();
        createDoctorLabel = new javax.swing.JLabel();
        CreateHospitalID = new javax.swing.JTextField();
        createHospitalIdLabel = new javax.swing.JLabel();
        createPatientNationality = new javax.swing.JComboBox<>();
        createPatientRace = new javax.swing.JComboBox<>();
        createPatientMaritalStatus = new javax.swing.JComboBox<>();
        createDay = new javax.swing.JComboBox<>();
        createMonth = new javax.swing.JComboBox<>();
        createYear = new javax.swing.JComboBox<>();
        createPatientSex = new javax.swing.JComboBox<>();
        CreateUserOption = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        AboutUsPanel = new java.awt.Panel();
        panel2 = new java.awt.Panel();
        ParagraphAboutUs26 = new javax.swing.JLabel();
        ParagraphAboutUs27 = new javax.swing.JLabel();
        ParagraphAboutUs28 = new javax.swing.JLabel();
        ParagraphAboutUs29 = new javax.swing.JLabel();
        ParagraphAboutUs31 = new javax.swing.JLabel();
        ParagraphAboutUs30 = new javax.swing.JLabel();
        ParagraphAboutUs10 = new javax.swing.JLabel();
        ParagraphAboutUs8 = new javax.swing.JLabel();
        ParagraphAboutUs9 = new javax.swing.JLabel();
        ParagraphAboutUs7 = new javax.swing.JLabel();
        ParagraphAboutUs6 = new javax.swing.JLabel();
        ParagraphAboutUs16 = new javax.swing.JLabel();
        ParagraphAboutUs17 = new javax.swing.JLabel();
        ParagraphAboutUs18 = new javax.swing.JLabel();
        ParagraphAboutUs19 = new javax.swing.JLabel();
        ParagraphAboutUs20 = new javax.swing.JLabel();
        ParagraphAboutUs25 = new javax.swing.JLabel();
        ParagraphAboutUs24 = new javax.swing.JLabel();
        ParagraphAboutUs23 = new javax.swing.JLabel();
        ParagraphAboutUs22 = new javax.swing.JLabel();
        ParagraphAboutUs21 = new javax.swing.JLabel();
        aboutTimetableicon = new javax.swing.JLabel();
        ParagraphAboutUs5 = new javax.swing.JLabel();
        ParagraphAboutUs4 = new javax.swing.JLabel();
        ParagraphAboutUs3 = new javax.swing.JLabel();
        ParagraphAboutUs2 = new javax.swing.JLabel();
        ParagraphAboutUs1 = new javax.swing.JLabel();
        aboutHearIcon = new javax.swing.JLabel();
        aboutMedicalIcon = new javax.swing.JLabel();
        ParagraphAboutUs11 = new javax.swing.JLabel();
        ParagraphAboutUs12 = new javax.swing.JLabel();
        ParagraphAboutUs13 = new javax.swing.JLabel();
        ParagraphAboutUs14 = new javax.swing.JLabel();
        ParagraphAboutUs15 = new javax.swing.JLabel();
        aboutUsHeading = new javax.swing.JLabel();
        ourMissinLabel = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        ContactUsPanel = new java.awt.Panel();
        ContactUsLabel = new javax.swing.JLabel();
        contactInfoLabel = new javax.swing.JLabel();
        contactPanel1 = new java.awt.Panel();
        SaveCareAddressLbl = new javax.swing.JLabel();
        AddressLbl = new javax.swing.JLabel();
        AddressLbl2 = new javax.swing.JLabel();
        EircodeLbl = new javax.swing.JLabel();
        SaveCareEircodeLbl1 = new javax.swing.JLabel();
        contactAddressIcon = new javax.swing.JLabel();
        contactPanel2 = new java.awt.Panel();
        SaveCareEmailLbl = new javax.swing.JLabel();
        EmailLbl = new javax.swing.JLabel();
        contactEmailIcon = new javax.swing.JLabel();
        contactPanel3 = new java.awt.Panel();
        SaveCareCustomerNumber = new javax.swing.JLabel();
        CustomerNumerLbl = new javax.swing.JLabel();
        SaveCareTelephoneNumber1 = new javax.swing.JLabel();
        CustomerNumerLbl1 = new javax.swing.JLabel();
        contactPhoneIcon = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        UserLoggedInPanel = new java.awt.Panel();
        loggedInPersonalInforPanel = new java.awt.Panel();
        loggedInPersonalInformationButton = new javax.swing.JLabel();
        personalicon = new javax.swing.JLabel();
        loggedInMedicalHistoryPanel = new java.awt.Panel();
        medicalHistoryIcon = new javax.swing.JLabel();
        loggedInMedicalHistoryButton = new javax.swing.JLabel();
        logeedInMedicationPanel = new java.awt.Panel();
        MedicationTietableIcon = new javax.swing.JLabel();
        loggedInMedicationButton = new javax.swing.JLabel();
        loggedInQRPanel = new java.awt.Panel();
        QRCodeIcon = new javax.swing.JLabel();
        loggedInQRButton = new javax.swing.JLabel();
        SignedInHeading = new javax.swing.JLabel();
        loggedInChooseOneHeading = new javax.swing.JLabel();
        loggedInBackground = new javax.swing.JLabel();
        PersonalInformationPanel = new java.awt.Panel();
        personalInformationHeading = new javax.swing.JLabel();
        personalInformationContentContainer = new java.awt.Panel();
        PPSDisplay = new javax.swing.JLabel();
        patientNumber = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameDisplay = new javax.swing.JLabel();
        dateOfBirthLabel = new javax.swing.JLabel();
        lastNameDisplay = new javax.swing.JLabel();
        ppsLabel = new javax.swing.JLabel();
        dateOfBirthDisplay = new javax.swing.JLabel();
        mobileNumberLabel = new javax.swing.JLabel();
        MobileNumberDisplay = new javax.swing.JLabel();
        lastNameLabel = new javax.swing.JLabel();
        homeAddressLabel = new javax.swing.JLabel();
        patientNumberDisplay = new javax.swing.JLabel();
        homeAddressDisplay = new javax.swing.JLabel();
        eircodeLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        NationalityLabel = new javax.swing.JLabel();
        maritalStatusLabel = new javax.swing.JLabel();
        eircodeDisplay = new javax.swing.JLabel();
        emailDisplay = new javax.swing.JLabel();
        nationalityDisplay = new javax.swing.JLabel();
        maritalStatusDisplay = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();
        sexDisplay = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        raceDisplay = new javax.swing.JLabel();
        raceLabel = new javax.swing.JLabel();
        passwordDisplay = new javax.swing.JPasswordField();
        updateComboBox = new javax.swing.JComboBox<>();
        updateFieldContent = new javax.swing.JTextField();
        updateFieldLabel = new javax.swing.JLabel();
        updatePatientPersonalInfoBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        QRScannerPanel = new java.awt.Panel();
        QRCodeScannerHeading = new javax.swing.JLabel();
        QRScannerGoBackButton = new javax.swing.JLabel();
        cameraContainer = new java.awt.Panel();
        signInPatientEmail = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        signPatientIn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        GeneratedQRCodePanel = new java.awt.Panel();
        GeneratedQRCodeContainer = new javax.swing.JLabel();
        GeneratedQRLabel = new javax.swing.JLabel();
        QRGeneratorGoBackButton = new javax.swing.JLabel();
        GeneratedQRBackground = new javax.swing.JLabel();
        MedicalHistoryPanel = new java.awt.Panel();
        CurrentMedicationHeading = new javax.swing.JLabel();
        medicationNameLabel = new javax.swing.JLabel();
        medicationNameOutput = new javax.swing.JLabel();
        MedicationBrandLabel = new javax.swing.JLabel();
        medicationBrandOutput = new javax.swing.JLabel();
        MedicationDatePrescribedLabel = new javax.swing.JLabel();
        medicationPrescribedOutput = new javax.swing.JLabel();
        medicationDateEndOutput = new javax.swing.JLabel();
        medicationFinishDateOutput = new javax.swing.JLabel();
        medicationSideEffectsLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        medicationSideEffectsOutput = new javax.swing.JTextArea();
        medicationDoctorNameLabel = new javax.swing.JLabel();
        medicationDoctorNameOutput = new javax.swing.JLabel();
        medicationPreviousButton = new javax.swing.JLabel();
        medicationNextButton = new javax.swing.JLabel();
        MedicationInfoOptions = new javax.swing.JComboBox<>();
        AddInformation = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        SignInPanel = new java.awt.Panel();
        signInComponentsConainer = new java.awt.Panel();
        signInEmailLabel = new javax.swing.JLabel();
        signInPasswordLabel = new javax.swing.JLabel();
        loginEmail = new javax.swing.JTextField();
        loginPassword = new javax.swing.JPasswordField();
        signInButton = new javax.swing.JButton();
        createAccountButton = new javax.swing.JButton();
        signInHeading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        PrescribMedicationPanel = new java.awt.Panel();
        prescribeMedicationHeading = new javax.swing.JLabel();
        AddPrescriptionMedNameLabel = new javax.swing.JLabel();
        addPrescriptionDateStartLabel = new javax.swing.JLabel();
        addPrescriptionDateFinishLabel = new javax.swing.JLabel();
        medicationComboBox = new javax.swing.JComboBox<>();
        medicationPrescribedMonth = new javax.swing.JComboBox<>();
        medicationPrescribedDay = new javax.swing.JComboBox<>();
        medicationFinishMonth = new javax.swing.JComboBox<>();
        medicationFinishDay = new javax.swing.JComboBox<>();
        addPrescriptionButton = new javax.swing.JButton();
        nextMedication = new javax.swing.JLabel();
        previousMedication = new javax.swing.JLabel();
        updatePrescriptionButton = new javax.swing.JButton();
        AddPrescriptionGoBackButton = new javax.swing.JLabel();
        updatePrescriptionButtonQuery = new javax.swing.JButton();
        medicationFinishYear = new javax.swing.JComboBox<>();
        medicationPrescribedYear = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        ProceduresCompletedPanel = new java.awt.Panel();
        proceduresCompletedHeading = new javax.swing.JLabel();
        procedureDay = new javax.swing.JComboBox<>();
        procedureMonth = new javax.swing.JComboBox<>();
        procedureYear = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        procedureSummary = new javax.swing.JTextArea();
        procedureComboBox = new javax.swing.JComboBox<>();
        addProcedureButton = new javax.swing.JButton();
        showEditOptionsButton = new javax.swing.JButton();
        prevProcedureUpdate = new javax.swing.JLabel();
        nextProcedureUpdate = new javax.swing.JLabel();
        updateProcedureButton = new javax.swing.JButton();
        goBackProceduresPage = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        AppointmentAddPanel = new java.awt.Panel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        appointmentDay = new javax.swing.JComboBox<>();
        appointmentMonth = new javax.swing.JComboBox<>();
        appointmentYear = new javax.swing.JComboBox<>();
        appointmentTimeDigit = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        appointmentSummary = new javax.swing.JTextArea();
        AddNewAppointment = new javax.swing.JButton();
        dispalyUpdateComponents = new javax.swing.JButton();
        updateExistingAppointment = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        prevAppointmentButton = new javax.swing.JLabel();
        nextAppointmentButton = new javax.swing.JLabel();
        hospitalNameComboBox = new javax.swing.JComboBox<>();
        DepartmentComboBox = new javax.swing.JComboBox<>();
        clinicNameComboBox = new javax.swing.JComboBox<>();
        appointmentGoBack = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        SurgeryAppointmentPanel = new java.awt.Panel();
        surgeryAppointmentHeading = new javax.swing.JLabel();
        sAppDate = new javax.swing.JLabel();
        sAppTime = new javax.swing.JLabel();
        sAppSurgerySummary = new javax.swing.JLabel();
        prevSAppointment = new javax.swing.JLabel();
        nextSAppointment = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        sAppSummary = new javax.swing.JTextArea();
        updateSAppointmentButton = new javax.swing.JButton();
        showSAppUpdateComponents = new javax.swing.JButton();
        addSAppointmentButton = new javax.swing.JButton();
        surgeryAppDay = new javax.swing.JComboBox<>();
        surgeryAppMonth = new javax.swing.JComboBox<>();
        surgeryAppYear = new javax.swing.JComboBox<>();
        sAppTImeNumber = new javax.swing.JComboBox<>();
        suregerAppGoBack = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        LifeStylePanel = new java.awt.Panel();
        jLabel20 = new javax.swing.JLabel();
        bloodLabel = new javax.swing.JLabel();
        emergencyNumberLabel = new javax.swing.JLabel();
        occupationLabel = new javax.swing.JLabel();
        SmokerLabel = new javax.swing.JLabel();
        activityLabel = new javax.swing.JLabel();
        bloodTypeDisplay = new javax.swing.JLabel();
        emergencyNumberDisplay = new javax.swing.JLabel();
        occupationDisplay = new javax.swing.JLabel();
        smokeQuestionDisplay = new javax.swing.JLabel();
        weeklyActivityDisplay = new javax.swing.JLabel();
        updateLifestylePanel = new java.awt.Panel();
        jLabel26 = new javax.swing.JLabel();
        OptionTextUpdateTf = new javax.swing.JTextField();
        UpdateOptionBox = new javax.swing.JComboBox<>();
        UpdateActivityButton = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        BenefitsPanel = new java.awt.Panel();
        benefitsReminderHeading = new javax.swing.JLabel();
        smoothHealthHeading = new javax.swing.JLabel();
        benefitsInstantHeading = new javax.swing.JLabel();
        benefitsPrivacyHeading = new javax.swing.JLabel();
        BenefitsHeading = new javax.swing.JLabel();
        benefitsHeading2 = new javax.swing.JLabel();
        benefitsInformationHeading = new javax.swing.JLabel();
        benefitsMedicalHeading = new javax.swing.JLabel();
        BenefitBox3 = new javax.swing.JPanel();
        ParagraphBenefit3Box1 = new javax.swing.JLabel();
        ParagraphBenefit3Box2 = new javax.swing.JLabel();
        ParagraphBenefit3Box3 = new javax.swing.JLabel();
        ParagraphBenefit3Box4 = new javax.swing.JLabel();
        ParagraphBenefit3Box5 = new javax.swing.JLabel();
        ParagraphBenefit3Box6 = new javax.swing.JLabel();
        ParagraphBenefit3Box7 = new javax.swing.JLabel();
        ParagraphBenefit3Box8 = new javax.swing.JLabel();
        BenefitBox2 = new javax.swing.JPanel();
        ParagraphBenefit2Box1 = new javax.swing.JLabel();
        ParagraphBenefit2Box2 = new javax.swing.JLabel();
        ParagraphBenefit2Box3 = new javax.swing.JLabel();
        ParagraphBenefit2Box4 = new javax.swing.JLabel();
        ParagraphBenefit2Box5 = new javax.swing.JLabel();
        ParagraphBenefit2Box6 = new javax.swing.JLabel();
        ParagraphBenefit2Box7 = new javax.swing.JLabel();
        BenefitBox1 = new javax.swing.JPanel();
        ParagraphBenefit1Box1 = new javax.swing.JLabel();
        ParagraphBenefit1Box2 = new javax.swing.JLabel();
        ParagraphBenefit1Box3 = new javax.swing.JLabel();
        ParagraphBenefit1Box4 = new javax.swing.JLabel();
        ParagraphBenefit1Box5 = new javax.swing.JLabel();
        ParagraphBenefit1Box6 = new javax.swing.JLabel();
        ParagraphBenefit1Box7 = new javax.swing.JLabel();
        BenefitBox4 = new javax.swing.JPanel();
        ParagraphBenefit4Box1 = new javax.swing.JLabel();
        ParagraphBenefit4Box2 = new javax.swing.JLabel();
        ParagraphBenefit4Box3 = new javax.swing.JLabel();
        ParagraphBenefit4Box4 = new javax.swing.JLabel();
        ParagraphBenefit4Box5 = new javax.swing.JLabel();
        ParagraphBenefit4Box6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        jLabel33.setText("jLabel33");

        jPasswordField1.setText("jPasswordField1");

        signInBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N

        jFormattedTextField1.setText("jFormattedTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SaveCare Application");
        setForeground(new java.awt.Color(0, 0, 0));
        setLocation(new java.awt.Point(400, 200));
        setResizable(false);
        setSize(new java.awt.Dimension(1296, 861));
        setType(java.awt.Window.Type.UTILITY);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setLayout(null);
        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        MenuContainer.setPreferredSize(new java.awt.Dimension(1296, 181));
        MenuContainer.setLayout(new java.awt.CardLayout());

        MainMenuPanel.setBackground(new java.awt.Color(14, 78, 108));
        MainMenuPanel.setPreferredSize(new java.awt.Dimension(1298, 181));

        mainMenuLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/SaveCareLogo.png"))); // NOI18N

        mainMenuPersonalInfoButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        mainMenuPersonalInfoButton.setForeground(new java.awt.Color(255, 255, 255));
        mainMenuPersonalInfoButton.setText("Personal Info");
        mainMenuPersonalInfoButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainMenuPersonalInfoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMenuPersonalInfoButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuPersonalInfoButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuPersonalInfoButtonMouseExited(evt);
            }
        });

        mainMenuMedicalHistoryButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        mainMenuMedicalHistoryButton.setForeground(new java.awt.Color(255, 255, 255));
        mainMenuMedicalHistoryButton.setText("Medical History");
        mainMenuMedicalHistoryButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainMenuMedicalHistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMenuMedicalHistoryButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuMedicalHistoryButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuMedicalHistoryButtonMouseExited(evt);
            }
        });

        mainUserOptions.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        mainUserOptions.setForeground(new java.awt.Color(255, 255, 255));
        mainUserOptions.setText("User Options");
        mainUserOptions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainUserOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainUserOptionsMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainUserOptionsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainUserOptionsMouseExited(evt);
            }
        });

        mainMenuLogOutButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        mainMenuLogOutButton.setForeground(new java.awt.Color(255, 255, 255));
        mainMenuLogOutButton.setText("Log Out");
        mainMenuLogOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainMenuLogOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainMenuLogOutButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mainMenuLogOutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mainMenuLogOutButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MainMenuPanelLayout = new javax.swing.GroupLayout(MainMenuPanel);
        MainMenuPanel.setLayout(MainMenuPanelLayout);
        MainMenuPanelLayout.setHorizontalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainMenuLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                .addComponent(mainMenuPersonalInfoButton)
                .addGap(55, 55, 55)
                .addComponent(mainMenuMedicalHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(mainUserOptions)
                .addGap(57, 57, 57)
                .addComponent(mainMenuLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        MainMenuPanelLayout.setVerticalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MainMenuPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mainMenuPersonalInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuMedicalHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainUserOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuLogOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(mainMenuLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        MenuContainer.add(MainMenuPanel, "card3");

        HomeMenu.setBackground(new java.awt.Color(14, 78, 108));
        HomeMenu.setMaximumSize(new java.awt.Dimension(1249, 138));

        homeSignInButton.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeSignInButton.setForeground(new java.awt.Color(255, 255, 255));
        homeSignInButton.setText("Sign In");
        homeSignInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeSignInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeSignInButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeSignInButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeSignInButtonMouseExited(evt);
            }
        });

        homeContactUsButton.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeContactUsButton.setForeground(new java.awt.Color(255, 255, 255));
        homeContactUsButton.setText("Contact Us");
        homeContactUsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeContactUsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeContactUsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeContactUsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeContactUsButtonMouseExited(evt);
            }
        });

        homeAboutUsButton.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeAboutUsButton.setForeground(new java.awt.Color(255, 255, 255));
        homeAboutUsButton.setText("About Us");
        homeAboutUsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeAboutUsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeAboutUsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeAboutUsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeAboutUsButtonMouseExited(evt);
            }
        });

        homeBenefitsButton.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeBenefitsButton.setForeground(new java.awt.Color(255, 255, 255));
        homeBenefitsButton.setText("Benefits");
        homeBenefitsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeBenefitsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeBenefitsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeBenefitsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeBenefitsButtonMouseExited(evt);
            }
        });

        homeMenuLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/SaveCareLogo.png"))); // NOI18N

        homeMenuButton.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeMenuButton.setForeground(new java.awt.Color(255, 255, 255));
        homeMenuButton.setText("Home");
        homeMenuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeMenuButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMenuButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeMenuButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeMenuButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout HomeMenuLayout = new javax.swing.GroupLayout(HomeMenu);
        HomeMenu.setLayout(HomeMenuLayout);
        HomeMenuLayout.setHorizontalGroup(
            HomeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeMenuLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(homeMenuLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
                .addComponent(homeMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(homeBenefitsButton)
                .addGap(55, 55, 55)
                .addComponent(homeAboutUsButton)
                .addGap(43, 43, 43)
                .addComponent(homeContactUsButton)
                .addGap(43, 43, 43)
                .addComponent(homeSignInButton)
                .addGap(58, 58, 58))
        );
        HomeMenuLayout.setVerticalGroup(
            HomeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HomeMenuLayout.createSequentialGroup()
                .addGroup(HomeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HomeMenuLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(homeMenuLogo))
                    .addGroup(HomeMenuLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(HomeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(HomeMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(homeBenefitsButton)
                                .addComponent(homeMenuButton))
                            .addComponent(homeAboutUsButton)
                            .addComponent(homeContactUsButton)
                            .addComponent(homeSignInButton))))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        MenuContainer.add(HomeMenu, "card2");

        getContentPane().add(MenuContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1296, -1));

        ApplicationInterfacePanel.setPreferredSize(new java.awt.Dimension(1298, 700));
        ApplicationInterfacePanel.setLayout(new java.awt.CardLayout());

        HomePagePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HomeHeading.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        HomeHeading.setText("Guiding You to Optimal Health");
        HomeHeading.setToolTipText("");
        HomePagePanel.add(HomeHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 704, 55));

        homeTransformHeading.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        homeTransformHeading.setText("Transform the way you manage your health with SaveCare ");
        HomePagePanel.add(homeTransformHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, -1, -1));

        hoeJoinHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        hoeJoinHeading.setText("Join now and take charge of your well-being.");
        HomePagePanel.add(hoeJoinHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 337, -1, -1));

        homeJoinUsButton.setFont(new java.awt.Font("Arial Narrow", 3, 36)); // NOI18N
        homeJoinUsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeJoinUsButton.setText("Join Us");
        homeJoinUsButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        homeJoinUsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        homeJoinUsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeJoinUsButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeJoinUsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeJoinUsButtonMouseExited(evt);
            }
        });
        HomePagePanel.add(homeJoinUsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 184, 61));

        homePageBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        HomePagePanel.add(homePageBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 690));

        ApplicationInterfacePanel.add(HomePagePanel, "card2");

        CreateAccountPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel16.setText("Create Account");
        CreateAccountPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 281, -1));

        createUserFieldsContainer.setBackground(new java.awt.Color(14, 78, 108));
        createUserFieldsContainer.setForeground(new java.awt.Color(255, 255, 255));
        createUserFieldsContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        createLastNameLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createLastNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        createLastNameLabel.setText("Last Name");
        createUserFieldsContainer.add(createLastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, -1));

        createPatientLastName.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientLastName.setText("Reddin");
        createPatientLastName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 133, 357, -1));

        createEircodeLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createEircodeLabel.setForeground(new java.awt.Color(255, 255, 255));
        createEircodeLabel.setText("Eircode");
        createUserFieldsContainer.add(createEircodeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 287, -1, -1));

        createRePassword.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createRePassword.setText("Helllooo1!");
        createRePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createRePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 360, -1));

        createPatientEmail.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientEmail.setText("craig1@gmail.com");
        createPatientEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 350, -1));

        createFirstNameLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createFirstNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        createFirstNameLabel.setText("First Name");
        createUserFieldsContainer.add(createFirstNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        createMaritalStatuseLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createMaritalStatuseLabel.setForeground(new java.awt.Color(255, 255, 255));
        createMaritalStatuseLabel.setText("Marital Status");
        createUserFieldsContainer.add(createMaritalStatuseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        createPatientEircode.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientEircode.setText("A65F4E2");
        createPatientEircode.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientEircode, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 350, -1));

        createPatientMobileNumber.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientMobileNumber.setText("0876776624");
        createPatientMobileNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientMobileNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 209, 357, -1));

        createSexLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createSexLabel.setForeground(new java.awt.Color(255, 255, 255));
        createSexLabel.setText("Sex");
        createUserFieldsContainer.add(createSexLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 45, -1));

        createNationalityLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createNationalityLabel.setForeground(new java.awt.Color(255, 255, 255));
        createNationalityLabel.setText("Nationality");
        createUserFieldsContainer.add(createNationalityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 340, -1, -1));

        createPatientAddress.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientAddress.setText("123 fake Street");
        createPatientAddress.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 350, -1));

        createMobileLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createMobileLabel.setForeground(new java.awt.Color(255, 255, 255));
        createMobileLabel.setText("Mobile Number");
        createUserFieldsContainer.add(createMobileLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 211, -1, -1));

        createRePasswordLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createRePasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        createRePasswordLabel.setText("Re-Password");
        createUserFieldsContainer.add(createRePasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, -1, -1));

        createDOBLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createDOBLabel.setForeground(new java.awt.Color(255, 255, 255));
        createDOBLabel.setText("Date Of Birth");
        createUserFieldsContainer.add(createDOBLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, -1, -1));

        createEmailLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createEmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        createEmailLabel.setText("Email Address");
        createUserFieldsContainer.add(createEmailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 130, -1));

        createRaceLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createRaceLabel.setForeground(new java.awt.Color(255, 255, 255));
        createRaceLabel.setText("Race");
        createUserFieldsContainer.add(createRaceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        createAddressLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        createAddressLabel.setText("Home Address");
        createUserFieldsContainer.add(createAddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 249, -1, -1));

        createPatientFirstName.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientFirstName.setText("Craig");
        createPatientFirstName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 95, 357, -1));

        createAccountBtn.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        createAccountBtn.setForeground(new java.awt.Color(255, 255, 255));
        createAccountBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createAccountBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-account-32.png"))); // NOI18N
        createAccountBtn.setText("Create Account");
        createAccountBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 3, true));
        createAccountBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createAccountBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAccountBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createAccountBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createAccountBtnMouseExited(evt);
            }
        });
        createUserFieldsContainer.add(createAccountBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 350, 60));

        createClearButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        createClearButton.setForeground(new java.awt.Color(255, 255, 255));
        createClearButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createClearButton.setText("Clear");
        createClearButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 3));
        createClearButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createClearButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createClearButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                createClearButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                createClearButtonMouseExited(evt);
            }
        });
        createUserFieldsContainer.add(createClearButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, 350, 60));

        createPasswordLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        createPasswordLabel.setText("Password");
        createUserFieldsContainer.add(createPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, -1, -1));

        createPPSLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createPPSLabel.setForeground(new java.awt.Color(255, 255, 255));
        createPPSLabel.setText("PPS Number");
        createUserFieldsContainer.add(createPPSLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        createPatientPassword.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientPassword.setText("Helllooo1!");
        createPatientPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 90, 360, -1));

        createPatientPPS.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createPatientPPS.setText("8165659U");
        createPatientPPS.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createPatientPPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 357, -1));

        createDoctorNumber.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(createDoctorNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 50, 360, 27));

        createDoctorLabel.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        createDoctorLabel.setForeground(new java.awt.Color(255, 255, 255));
        createDoctorLabel.setText("Doctor Number");
        createUserFieldsContainer.add(createDoctorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, -1, 27));

        CreateHospitalID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        createUserFieldsContainer.add(CreateHospitalID, new org.netbeans.lib.awtextra.AbsoluteConstraints(168, 54, 357, 29));

        createHospitalIdLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createHospitalIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        createHospitalIdLabel.setText("Hospital ID");
        createUserFieldsContainer.add(createHospitalIdLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        createPatientNationality.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        createPatientNationality.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Afghan", "Albanian", "Algerian", "American", "Andorran", "Angolan", "Anguillan", "Citizen of Antigua and Barbuda", "Argentine", "Armenian", "Australian", "Austrian", "Azerbaijani", "Bahamian", "Bahraini", "Bangladeshi", "Barbadian", "Belarusian", "Belgian", "Belizean", "Beninese", "Bermudian", "Bhutanese", "Bolivian", "Citizen of Bosnia and Herzegovina", "Botswanan", "Brazilian", "British", "British Virgin Islander", "Bruneian", "Bulgarian", "Burkinan", "Burmese", "Burundian", "Cambodian", "Cameroonian", "Canadian", "Cape Verdean", "Cayman Islander", "Central African", "Chadian", "Chilean", "Chinese", "Colombian", "Comoran", "Congolese (Congo)", "Congolese (DRC)", "Cook Islander", "Costa Rican", "Croatian", "Cuban", "Cymraes", "Cymro", "Cypriot", "Czech", "Danish", "Djiboutian", "Dominican", "Citizen of the Dominican Republic", "Dutch", "East Timorese", "Ecuadorean", "Egyptian", "Emirati", "English", "Equatorial Guinean", "Eritrean", "Estonian", "Ethiopian", "Faroese", "Fijian", "Filipino", "Finnish", "French", "Gabonese", "Gambian", "Georgian", "German", "Ghanaian", "Gibraltarian", "Greek", "Greenlandic", "Grenadian", "Guamanian", "Guatemalan", "Citizen of Guinea-Bissau", "Guinean", "Guyanese", "Haitian", "Honduran", "Hong Konger", "Hungarian ", "Icelandic", "Indian", "Indonesian", "Iranian", "Iraqi", "Irish ", "Israeli ", "Italian ", "Ivorian ", "Jamaican ", "Japanese ", "Jordanian ", "Kazakh ", "Kenyan ", "Kittitian ", "Citizen of Kiribati ", "Kosovan ", "Kuwaiti ", "Kyrgyz ", "Laotian ", "Latvian ", "Lebanese ", "Liberian ", "Libyan ", "Liechtenstein citizen ", "Lithuanian ", "Luxembourger ", "Macanese ", "Macedonian ", "Malagasy ", "Malawian ", "Malaysian ", "Maldivian ", "Malian ", "Maltese ", "Marshallese ", "Martiniquais ", "Mauritanian ", "Mauritian ", "Mexican ", "Micronesian ", "Moldovan ", "Monegasque ", "Mongolian ", "Montenegrin ", "Montserratian ", "Moroccan ", "Mosotho ", "Mozambican ", "Namibian ", "Nauruan ", "Nepalese ", "New Zealander ", "Nicaraguan ", "Nigerian ", "Nigerien ", "Niuean ", "North Korean ", "Northern Irish ", "Norwegian ", "Omani ", "Pakistani ", "Palauan ", "Palestinian ", "Panamanian ", "Papua New Guinean ", "Paraguayan ", "Peruvian ", "Pitcairn Islander ", "Polish ", "Portuguese ", "Prydeinig ", "Puerto Rican ", "Qatari ", "Romanian ", "Russian ", "Rwandan", "Salvadorean", "Sammarinese", "Samoa", "Sao Tomean", "Saudi Arabian", "Scottish", "Senegalese", "Serbian", "Citizen of Seychelles", "Sierra Leonean", "Singaporean", "Slovak Slovenian", "Solomon Islander", "Somali", "South African", "South Korean", "South Sudanese", "Spanish", "Sri Lankan", "St Helenian", "St Lucian", "Stateless", "Sudanese", "Surinamese", "Swazi", "Swedish", "Swiss", "Syrian", "Taiwanese", "Tajik", "Tanzanian", "Thai", "Togolese", "Tongan", "Trinidadian", "Tristanian", "Tunisian", "Turkish", "Turkmen", "Turks and Caicos Island", "Ugandan", "Ukrainian", "Uruguayan", "Uzbek", "Vatican citizen", "Citizen of Vanuatu", "Venezuelan", "Vietnamese", "Vincentian", "Wallisian", "Welsh", "Yemeni", "Zambian", "Zimbabwean" }));
        createPatientNationality.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createPatientNationality, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, 360, -1));

        createPatientRace.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        createPatientRace.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Asian", "Asian Irish", "Black", "Black Irish", "Hispanic", "Irish Travleler", "Mixed Background", "White", "White Irish", "Other Ethnic Background" }));
        createPatientRace.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createPatientRace, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 290, 360, -1));

        createPatientMaritalStatus.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        createPatientMaritalStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Single", "Married", "Divorced", "Sepererated", "Widowed" }));
        createPatientMaritalStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createPatientMaritalStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 250, 360, -1));

        createDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        createDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 110, -1));

        createMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        createMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createMonthActionPerformed(evt);
            }
        });
        createUserFieldsContainer.add(createMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 110, -1));

        createYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, 100, -1));

        createPatientSex.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        createPatientSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Female", "Male", "Intersex", " " }));
        createPatientSex.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createUserFieldsContainer.add(createPatientSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 210, 360, -1));

        CreateAccountPanel.add(createUserFieldsContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 1100, 510));

        CreateUserOption.setBackground(new java.awt.Color(255, 255, 255));
        CreateUserOption.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        CreateUserOption.setForeground(new java.awt.Color(0, 0, 0));
        CreateUserOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Patient", "Doctor" }));
        CreateUserOption.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        CreateUserOption.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CreateUserOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateUserOptionActionPerformed(evt);
            }
        });
        CreateAccountPanel.add(CreateUserOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 230, 50));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        CreateAccountPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(CreateAccountPanel, "card5");

        AboutUsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setBackground(new java.awt.Color(14, 78, 108));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ParagraphAboutUs26.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs26.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs26.setText("We're constantly innovating and improving our ");
        panel2.add(ParagraphAboutUs26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 410, -1));

        ParagraphAboutUs27.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs27.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs27.setText("platform to make healthcare more personalized ");
        panel2.add(ParagraphAboutUs27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 410, -1));

        ParagraphAboutUs28.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs28.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs28.setText("and patient-centered. Our ultimate goal is to create ");
        panel2.add(ParagraphAboutUs28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        ParagraphAboutUs29.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs29.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs29.setText("a world where everyone has access to high-quality ");
        panel2.add(ParagraphAboutUs29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 400, 20));

        ParagraphAboutUs31.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs31.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs31.setText("needs and preferences.  ");
        panel2.add(ParagraphAboutUs31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 400, -1));

        ParagraphAboutUs30.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs30.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs30.setText("healthcare that's tailored to their unique ");
        panel2.add(ParagraphAboutUs30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 400, -1));

        ParagraphAboutUs10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs10.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs10.setText("smartphone or computer.");
        panel2.add(ParagraphAboutUs10, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, -1, -1));

        ParagraphAboutUs8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs8.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs8.setText("medical history, medication schedule, and ");
        panel2.add(ParagraphAboutUs8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));

        ParagraphAboutUs9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs9.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs9.setText("upcoming appointments from their");
        panel2.add(ParagraphAboutUs9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, -1, -1));

        ParagraphAboutUs7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs7.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs7.setText("technology, patients can easily access their ");
        panel2.add(ParagraphAboutUs7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, -1, -1));

        ParagraphAboutUs6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs6.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs6.setText("healthcare providers. With our advanced ");
        panel2.add(ParagraphAboutUs6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, -1));

        ParagraphAboutUs16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs16.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs16.setText("and their healthcare team. Our platform enables ");
        ParagraphAboutUs16.setToolTipText("");
        panel2.add(ParagraphAboutUs16, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 210, -1, -1));

        ParagraphAboutUs17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs17.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs17.setText("patients to view and update their personal ");
        panel2.add(ParagraphAboutUs17, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 240, -1, -1));

        ParagraphAboutUs18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs18.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs18.setText("information, including their medical history and ");
        panel2.add(ParagraphAboutUs18, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 270, -1, -1));

        ParagraphAboutUs19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs19.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs19.setText("lifestyle choices, so that providers can make more ");
        panel2.add(ParagraphAboutUs19, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, -1, -1));

        ParagraphAboutUs20.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs20.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs20.setText("informed decisions about their care.");
        panel2.add(ParagraphAboutUs20, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 330, -1, -1));

        ParagraphAboutUs25.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs25.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs25.setText("health issues before they become more serious. ");
        panel2.add(ParagraphAboutUs25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 400, -1));

        ParagraphAboutUs24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs24.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs24.setText("upcoming appointments, we hope to prevent ");
        panel2.add(ParagraphAboutUs24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 400, -1));

        ParagraphAboutUs23.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs23.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs23.setText("stay on top of their medication schedules and ");
        panel2.add(ParagraphAboutUs23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 400, -1));

        ParagraphAboutUs22.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs22.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs22.setText("better health outcomes for all. By helping patients ");
        panel2.add(ParagraphAboutUs22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 400, 20));

        ParagraphAboutUs21.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs21.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs21.setText("At SaveCare, we're also dedicated to promoting ");
        panel2.add(ParagraphAboutUs21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 400, -1));

        aboutTimetableicon.setBackground(new java.awt.Color(255, 255, 255));
        aboutTimetableicon.setForeground(new java.awt.Color(255, 255, 255));
        aboutTimetableicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icon3.png"))); // NOI18N
        panel2.add(aboutTimetableicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        ParagraphAboutUs5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs5.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs5.setText("and helping them to connect with the right ");
        panel2.add(ParagraphAboutUs5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        ParagraphAboutUs4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs4.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs4.setText("giving them control over their health information ");
        panel2.add(ParagraphAboutUs4, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, -1, 30));

        ParagraphAboutUs3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs3.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs3.setText("everyone. Our mission is to empower patients by ");
        panel2.add(ParagraphAboutUs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, 380, -1));

        ParagraphAboutUs2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs2.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs2.setText("healthcare more accessible and convenient for ");
        panel2.add(ParagraphAboutUs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 380, 20));

        ParagraphAboutUs1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs1.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs1.setText("At SaveCare, we're committed to making ");
        panel2.add(ParagraphAboutUs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 380, -1));

        aboutHearIcon.setBackground(new java.awt.Color(255, 255, 255));
        aboutHearIcon.setForeground(new java.awt.Color(255, 255, 255));
        aboutHearIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icon1.png"))); // NOI18N
        panel2.add(aboutHearIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        aboutMedicalIcon.setBackground(new java.awt.Color(255, 255, 255));
        aboutMedicalIcon.setForeground(new java.awt.Color(255, 255, 255));
        aboutMedicalIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icon2.png"))); // NOI18N
        panel2.add(aboutMedicalIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 10, -1, -1));

        ParagraphAboutUs11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs11.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs11.setText("We believe that healthcare should be a ");
        panel2.add(ParagraphAboutUs11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, -1, 20));

        ParagraphAboutUs12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs12.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs12.setText("collaborative effort between patients and providers. ");
        panel2.add(ParagraphAboutUs12, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 90, -1, 20));

        ParagraphAboutUs13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs13.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs13.setText("By providing patients with the tools they need ");
        panel2.add(ParagraphAboutUs13, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 120, 360, -1));

        ParagraphAboutUs14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs14.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs14.setText("to manage their own health, we hope to facilitate ");
        panel2.add(ParagraphAboutUs14, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 150, -1, -1));

        ParagraphAboutUs15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        ParagraphAboutUs15.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphAboutUs15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphAboutUs15.setText("better communication and trust between patients ");
        panel2.add(ParagraphAboutUs15, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 180, -1, -1));

        AboutUsPanel.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 1260, 450));

        aboutUsHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        aboutUsHeading.setText("About Us");
        AboutUsPanel.add(aboutUsHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 80, -1, -1));

        ourMissinLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        ourMissinLabel.setText("Check Out Our Mission Below");
        AboutUsPanel.add(ourMissinLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 267, 53));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        AboutUsPanel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(AboutUsPanel, "card6");

        ContactUsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ContactUsLabel.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        ContactUsLabel.setText("Contact Us");
        ContactUsPanel.add(ContactUsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 24, -1, -1));

        contactInfoLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        contactInfoLabel.setText("Check Out Our Contact Information Below");
        ContactUsPanel.add(contactInfoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 77, 494, 61));

        contactPanel1.setBackground(new java.awt.Color(14, 78, 108));

        SaveCareAddressLbl.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        SaveCareAddressLbl.setForeground(new java.awt.Color(255, 255, 255));
        SaveCareAddressLbl.setText("Address");

        AddressLbl.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        AddressLbl.setForeground(new java.awt.Color(255, 255, 255));
        AddressLbl.setText("42 AmberStreet Square,");

        AddressLbl2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        AddressLbl2.setForeground(new java.awt.Color(255, 255, 255));
        AddressLbl2.setText("Dublin 12");

        EircodeLbl.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        EircodeLbl.setForeground(new java.awt.Color(255, 255, 255));
        EircodeLbl.setText("D12 Y72A");

        SaveCareEircodeLbl1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        SaveCareEircodeLbl1.setForeground(new java.awt.Color(255, 255, 255));
        SaveCareEircodeLbl1.setText("Address Eircode");

        contactAddressIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/addressIcon.png"))); // NOI18N

        javax.swing.GroupLayout contactPanel1Layout = new javax.swing.GroupLayout(contactPanel1);
        contactPanel1.setLayout(contactPanel1Layout);
        contactPanel1Layout.setHorizontalGroup(
            contactPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanel1Layout.createSequentialGroup()
                .addGroup(contactPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contactPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(contactPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(contactPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(SaveCareAddressLbl))
                            .addGroup(contactPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(AddressLbl2))
                            .addComponent(SaveCareEircodeLbl1)
                            .addComponent(AddressLbl)
                            .addGroup(contactPanel1Layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(EircodeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(contactPanel1Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(contactAddressIcon)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        contactPanel1Layout.setVerticalGroup(
            contactPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel1Layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(contactAddressIcon)
                .addGap(39, 39, 39)
                .addComponent(SaveCareAddressLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddressLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddressLbl2)
                .addGap(29, 29, 29)
                .addComponent(SaveCareEircodeLbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EircodeLbl)
                .addGap(136, 136, 136))
        );

        ContactUsPanel.add(contactPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 170, -1, -1));

        contactPanel2.setBackground(new java.awt.Color(14, 78, 108));

        SaveCareEmailLbl.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        SaveCareEmailLbl.setForeground(new java.awt.Color(255, 255, 255));
        SaveCareEmailLbl.setText("Email Address");

        EmailLbl.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        EmailLbl.setForeground(new java.awt.Color(255, 255, 255));
        EmailLbl.setText("info@savecare.ie");

        contactEmailIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/emailIcon.png"))); // NOI18N

        javax.swing.GroupLayout contactPanel2Layout = new javax.swing.GroupLayout(contactPanel2);
        contactPanel2.setLayout(contactPanel2Layout);
        contactPanel2Layout.setHorizontalGroup(
            contactPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanel2Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(contactEmailIcon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel2Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addGroup(contactPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(contactPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(EmailLbl))
                    .addComponent(SaveCareEmailLbl))
                .addGap(95, 95, 95))
        );
        contactPanel2Layout.setVerticalGroup(
            contactPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(contactEmailIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(SaveCareEmailLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EmailLbl)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        ContactUsPanel.add(contactPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(463, 170, -1, 471));

        contactPanel3.setBackground(new java.awt.Color(14, 78, 108));

        SaveCareCustomerNumber.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        SaveCareCustomerNumber.setForeground(new java.awt.Color(255, 255, 255));
        SaveCareCustomerNumber.setText("Customer Service");

        CustomerNumerLbl.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        CustomerNumerLbl.setForeground(new java.awt.Color(255, 255, 255));
        CustomerNumerLbl.setText("(01) 7826 392 719");

        SaveCareTelephoneNumber1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        SaveCareTelephoneNumber1.setForeground(new java.awt.Color(255, 255, 255));
        SaveCareTelephoneNumber1.setText("Change Appointment");

        CustomerNumerLbl1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        CustomerNumerLbl1.setForeground(new java.awt.Color(255, 255, 255));
        CustomerNumerLbl1.setText("(01) 7826 234 524");

        contactPhoneIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/phoneIcon.png"))); // NOI18N

        javax.swing.GroupLayout contactPanel3Layout = new javax.swing.GroupLayout(contactPanel3);
        contactPanel3.setLayout(contactPanel3Layout);
        contactPanel3Layout.setHorizontalGroup(
            contactPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contactPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                        .addComponent(contactPhoneIcon)
                        .addGap(162, 162, 162))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                        .addComponent(CustomerNumerLbl)
                        .addGap(104, 104, 104))))
            .addGroup(contactPanel3Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(CustomerNumerLbl1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                .addGap(0, 65, Short.MAX_VALUE)
                .addGroup(contactPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                        .addComponent(SaveCareTelephoneNumber1)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contactPanel3Layout.createSequentialGroup()
                        .addComponent(SaveCareCustomerNumber)
                        .addGap(74, 74, 74))))
        );
        contactPanel3Layout.setVerticalGroup(
            contactPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contactPanel3Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(contactPhoneIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(SaveCareCustomerNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerNumerLbl)
                .addGap(68, 68, 68)
                .addComponent(SaveCareTelephoneNumber1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CustomerNumerLbl1)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        ContactUsPanel.add(contactPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(885, 170, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        ContactUsPanel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(ContactUsPanel, "card7");

        UserLoggedInPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        loggedInPersonalInforPanel.setBackground(new java.awt.Color(14, 78, 108));
        loggedInPersonalInforPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loggedInPersonalInforPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loggedInPersonalInforPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loggedInPersonalInforPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loggedInPersonalInforPanelMouseExited(evt);
            }
        });

        loggedInPersonalInformationButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        loggedInPersonalInformationButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedInPersonalInformationButton.setText("Pesonal Info");
        loggedInPersonalInformationButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        personalicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        personalicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-information-96.png"))); // NOI18N
        personalicon.setMaximumSize(new java.awt.Dimension(101, 102));
        personalicon.setMinimumSize(new java.awt.Dimension(101, 102));

        javax.swing.GroupLayout loggedInPersonalInforPanelLayout = new javax.swing.GroupLayout(loggedInPersonalInforPanel);
        loggedInPersonalInforPanel.setLayout(loggedInPersonalInforPanelLayout);
        loggedInPersonalInforPanelLayout.setHorizontalGroup(
            loggedInPersonalInforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInPersonalInforPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedInPersonalInformationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInPersonalInforPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(personalicon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );
        loggedInPersonalInforPanelLayout.setVerticalGroup(
            loggedInPersonalInforPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInPersonalInforPanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(personalicon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(loggedInPersonalInformationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        UserLoggedInPanel.add(loggedInPersonalInforPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 260, 250, 300));

        loggedInMedicalHistoryPanel.setBackground(new java.awt.Color(14, 78, 108));
        loggedInMedicalHistoryPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loggedInMedicalHistoryPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loggedInMedicalHistoryPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loggedInMedicalHistoryPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loggedInMedicalHistoryPanelMouseExited(evt);
            }
        });

        medicalHistoryIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medicalHistoryIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-activity-history-100.png"))); // NOI18N
        medicalHistoryIcon.setMaximumSize(new java.awt.Dimension(101, 102));
        medicalHistoryIcon.setMinimumSize(new java.awt.Dimension(101, 102));

        loggedInMedicalHistoryButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        loggedInMedicalHistoryButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedInMedicalHistoryButton.setText("Medical History");
        loggedInMedicalHistoryButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        javax.swing.GroupLayout loggedInMedicalHistoryPanelLayout = new javax.swing.GroupLayout(loggedInMedicalHistoryPanel);
        loggedInMedicalHistoryPanel.setLayout(loggedInMedicalHistoryPanelLayout);
        loggedInMedicalHistoryPanelLayout.setHorizontalGroup(
            loggedInMedicalHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInMedicalHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedInMedicalHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInMedicalHistoryPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(medicalHistoryIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        loggedInMedicalHistoryPanelLayout.setVerticalGroup(
            loggedInMedicalHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInMedicalHistoryPanelLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(medicalHistoryIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(loggedInMedicalHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        UserLoggedInPanel.add(loggedInMedicalHistoryPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, 250, 300));

        logeedInMedicationPanel.setBackground(new java.awt.Color(14, 78, 108));
        logeedInMedicationPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logeedInMedicationPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logeedInMedicationPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logeedInMedicationPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logeedInMedicationPanelMouseExited(evt);
            }
        });

        MedicationTietableIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MedicationTietableIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-sports-mode-96.png"))); // NOI18N

        loggedInMedicationButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        loggedInMedicationButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedInMedicationButton.setText("Lifestyle");
        loggedInMedicationButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));

        javax.swing.GroupLayout logeedInMedicationPanelLayout = new javax.swing.GroupLayout(logeedInMedicationPanel);
        logeedInMedicationPanel.setLayout(logeedInMedicationPanelLayout);
        logeedInMedicationPanelLayout.setHorizontalGroup(
            logeedInMedicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logeedInMedicationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedInMedicationButton, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logeedInMedicationPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MedicationTietableIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        logeedInMedicationPanelLayout.setVerticalGroup(
            logeedInMedicationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logeedInMedicationPanelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(MedicationTietableIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(loggedInMedicationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        UserLoggedInPanel.add(logeedInMedicationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 260, 250, 300));

        loggedInQRPanel.setBackground(new java.awt.Color(14, 78, 108));
        loggedInQRPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loggedInQRPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loggedInQRPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loggedInQRPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loggedInQRPanelMouseExited(evt);
            }
        });

        QRCodeIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QRCodeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-qr-code-96.png"))); // NOI18N
        QRCodeIcon.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        loggedInQRButton.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        loggedInQRButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loggedInQRButton.setText("QR Code");
        loggedInQRButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 3, true));
        loggedInQRButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loggedInQRButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loggedInQRButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout loggedInQRPanelLayout = new javax.swing.GroupLayout(loggedInQRPanel);
        loggedInQRPanel.setLayout(loggedInQRPanelLayout);
        loggedInQRPanelLayout.setHorizontalGroup(
            loggedInQRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInQRPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loggedInQRButton, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(loggedInQRPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(QRCodeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loggedInQRPanelLayout.setVerticalGroup(
            loggedInQRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInQRPanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(QRCodeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(loggedInQRButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        UserLoggedInPanel.add(loggedInQRPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 250, 300));

        SignedInHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        SignedInHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SignedInHeading.setText("Welcome Sean");
        UserLoggedInPanel.add(SignedInHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 750, 60));

        loggedInChooseOneHeading.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        loggedInChooseOneHeading.setText("Choose One Of The Following Options");
        UserLoggedInPanel.add(loggedInChooseOneHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, -1, 50));

        loggedInBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        UserLoggedInPanel.add(loggedInBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(UserLoggedInPanel, "card8");

        PersonalInformationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        personalInformationHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        personalInformationHeading.setText("Personal Information");
        PersonalInformationPanel.add(personalInformationHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, -1, 40));

        personalInformationContentContainer.setBackground(new java.awt.Color(14, 78, 108));
        personalInformationContentContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PPSDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        PPSDisplay.setForeground(new java.awt.Color(255, 255, 255));
        PPSDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PPSDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(PPSDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 151, 345, 38));

        patientNumber.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        patientNumber.setForeground(new java.awt.Color(255, 255, 255));
        patientNumber.setText("Patient Number");
        personalInformationContentContainer.add(patientNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        firstNameLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        firstNameLabel.setText("First Name");
        personalInformationContentContainer.add(firstNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 111, -1));

        firstNameDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        firstNameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        firstNameDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstNameDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(firstNameDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 290, 43));

        dateOfBirthLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        dateOfBirthLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateOfBirthLabel.setText("Date Of Birth");
        personalInformationContentContainer.add(dateOfBirthLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 302, -1, -1));

        lastNameDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lastNameDisplay.setForeground(new java.awt.Color(255, 255, 255));
        lastNameDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastNameDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(lastNameDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 241, 290, 43));

        ppsLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        ppsLabel.setForeground(new java.awt.Color(255, 255, 255));
        ppsLabel.setText("PPS Number");
        personalInformationContentContainer.add(ppsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 121, -1, -1));

        dateOfBirthDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        dateOfBirthDisplay.setForeground(new java.awt.Color(255, 255, 255));
        dateOfBirthDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateOfBirthDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(dateOfBirthDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 332, 290, 40));

        mobileNumberLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        mobileNumberLabel.setForeground(new java.awt.Color(255, 255, 255));
        mobileNumberLabel.setText("Mobile Number");
        personalInformationContentContainer.add(mobileNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        MobileNumberDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        MobileNumberDisplay.setForeground(new java.awt.Color(255, 255, 255));
        MobileNumberDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MobileNumberDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(MobileNumberDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 290, 40));

        lastNameLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        lastNameLabel.setText("Last Name");
        personalInformationContentContainer.add(lastNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 211, -1, -1));

        homeAddressLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        homeAddressLabel.setForeground(new java.awt.Color(255, 255, 255));
        homeAddressLabel.setText("Home Address");
        personalInformationContentContainer.add(homeAddressLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 385, -1, -1));

        patientNumberDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        patientNumberDisplay.setForeground(new java.awt.Color(255, 255, 255));
        patientNumberDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        patientNumberDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(patientNumberDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 290, 43));

        homeAddressDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        homeAddressDisplay.setForeground(new java.awt.Color(255, 255, 255));
        homeAddressDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homeAddressDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(homeAddressDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 421, 756, 39));

        eircodeLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        eircodeLabel.setForeground(new java.awt.Color(255, 255, 255));
        eircodeLabel.setText("Eircode");
        personalInformationContentContainer.add(eircodeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 120, 75, -1));

        emailLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(255, 255, 255));
        emailLabel.setText("Email");
        personalInformationContentContainer.add(emailLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 207, 75, -1));

        NationalityLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        NationalityLabel.setForeground(new java.awt.Color(255, 255, 255));
        NationalityLabel.setText("Nationality");
        personalInformationContentContainer.add(NationalityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 298, -1, -1));

        maritalStatusLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        maritalStatusLabel.setForeground(new java.awt.Color(255, 255, 255));
        maritalStatusLabel.setText("Marital Status");
        personalInformationContentContainer.add(maritalStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 207, -1, -1));

        eircodeDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        eircodeDisplay.setForeground(new java.awt.Color(255, 255, 255));
        eircodeDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        eircodeDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(eircodeDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 150, 345, 44));

        emailDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        emailDisplay.setForeground(new java.awt.Color(255, 255, 255));
        emailDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(emailDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 237, 345, 40));

        nationalityDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        nationalityDisplay.setForeground(new java.awt.Color(255, 255, 255));
        nationalityDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nationalityDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(nationalityDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 333, 345, 40));

        maritalStatusDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        maritalStatusDisplay.setForeground(new java.awt.Color(255, 255, 255));
        maritalStatusDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maritalStatusDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(maritalStatusDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 237, 345, 40));

        sexLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        sexLabel.setForeground(new java.awt.Color(255, 255, 255));
        sexLabel.setText("Sex");
        personalInformationContentContainer.add(sexLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 300, 40, 20));

        sexDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        sexDisplay.setForeground(new java.awt.Color(255, 255, 255));
        sexDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sexDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(sexDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(842, 328, 345, 45));

        passwordLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        passwordLabel.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel.setText("Password");
        personalInformationContentContainer.add(passwordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 30, -1, -1));

        raceDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        raceDisplay.setForeground(new java.awt.Color(255, 255, 255));
        raceDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        raceDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(raceDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 345, 45));

        raceLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        raceLabel.setForeground(new java.awt.Color(255, 255, 255));
        raceLabel.setText("Race");
        personalInformationContentContainer.add(raceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, -1, -1));

        passwordDisplay.setEditable(false);
        passwordDisplay.setBackground(new java.awt.Color(14, 78, 108));
        passwordDisplay.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        passwordDisplay.setForeground(new java.awt.Color(255, 255, 255));
        passwordDisplay.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        personalInformationContentContainer.add(passwordDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 347, 43));

        PersonalInformationPanel.add(personalInformationContentContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 1240, 480));

        updateComboBox.setBackground(new java.awt.Color(14, 78, 108));
        updateComboBox.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        updateComboBox.setForeground(new java.awt.Color(255, 255, 255));
        updateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Name", "Last Name", "Mobile Number", "Email", "Eircode", "Home Address", "Password", "Marital Status" }));
        updateComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PersonalInformationPanel.add(updateComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 610, 350, 50));

        updateFieldContent.setBackground(new java.awt.Color(14, 78, 108));
        updateFieldContent.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        updateFieldContent.setForeground(new java.awt.Color(255, 255, 255));
        updateFieldContent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PersonalInformationPanel.add(updateFieldContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 610, 360, 50));

        updateFieldLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        updateFieldLabel.setText("Select Field To Update");
        PersonalInformationPanel.add(updateFieldLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 200, 50));

        updatePatientPersonalInfoBtn.setBackground(new java.awt.Color(14, 78, 108));
        updatePatientPersonalInfoBtn.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        updatePatientPersonalInfoBtn.setForeground(new java.awt.Color(255, 255, 255));
        updatePatientPersonalInfoBtn.setText("Update Information");
        updatePatientPersonalInfoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updatePatientPersonalInfoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientPersonalInfoBtnActionPerformed(evt);
            }
        });
        PersonalInformationPanel.add(updatePatientPersonalInfoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 610, 250, 50));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        PersonalInformationPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(PersonalInformationPanel, "card9");

        QRScannerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        QRCodeScannerHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        QRCodeScannerHeading.setText("QR Code Scanner");
        QRScannerPanel.add(QRCodeScannerHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 330, 40));

        QRScannerGoBackButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QRScannerGoBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        QRScannerGoBackButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        QRScannerGoBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QRScannerGoBackButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QRScannerGoBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QRScannerGoBackButtonMouseExited(evt);
            }
        });
        QRScannerPanel.add(QRScannerGoBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 90, 90));

        cameraContainer.setEnabled(false);
        cameraContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        QRScannerPanel.add(cameraContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 810, 430));

        signInPatientEmail.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        QRScannerPanel.add(signInPatientEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 600, 340, 50));

        jLabel21.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel21.setText("Patien's Email:");
        QRScannerPanel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, 140, 40));

        signPatientIn.setBackground(new java.awt.Color(14, 78, 108));
        signPatientIn.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        signPatientIn.setForeground(new java.awt.Color(255, 255, 255));
        signPatientIn.setText("Retrive Patient Data");
        signPatientIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signPatientInActionPerformed(evt);
            }
        });
        QRScannerPanel.add(signPatientIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 600, 260, 50));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        QRScannerPanel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(QRScannerPanel, "card10");

        GeneratedQRCodePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        GeneratedQRCodePanel.add(GeneratedQRCodeContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 130, 560, 440));

        GeneratedQRLabel.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        GeneratedQRLabel.setText("Patient QR Generator");
        GeneratedQRCodePanel.add(GeneratedQRLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 379, 67));

        QRGeneratorGoBackButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        QRGeneratorGoBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        QRGeneratorGoBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QRGeneratorGoBackButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                QRGeneratorGoBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                QRGeneratorGoBackButtonMouseExited(evt);
            }
        });
        GeneratedQRCodePanel.add(QRGeneratorGoBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, 100));

        GeneratedQRBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        GeneratedQRCodePanel.add(GeneratedQRBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(GeneratedQRCodePanel, "card11");

        MedicalHistoryPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CurrentMedicationHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        CurrentMedicationHeading.setText("Current Medical Information");
        MedicalHistoryPanel.add(CurrentMedicationHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, -1, 61));

        medicationNameLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        medicationNameLabel.setText("Medication Name");
        MedicalHistoryPanel.add(medicationNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 102, 500, 35));

        medicationNameOutput.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medicationNameOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        MedicalHistoryPanel.add(medicationNameOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 143, 500, 47));

        MedicationBrandLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        MedicationBrandLabel.setText("Medication Brand");
        MedicalHistoryPanel.add(MedicationBrandLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 208, 500, 28));

        medicationBrandOutput.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medicationBrandOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        MedicalHistoryPanel.add(medicationBrandOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 242, 500, 47));

        MedicationDatePrescribedLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        MedicationDatePrescribedLabel.setText("Date Prescribed");
        MedicalHistoryPanel.add(MedicationDatePrescribedLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 307, 500, 40));

        medicationPrescribedOutput.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medicationPrescribedOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        MedicalHistoryPanel.add(medicationPrescribedOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 353, 500, 49));

        medicationDateEndOutput.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        medicationDateEndOutput.setText("Medication Finish Date");
        MedicalHistoryPanel.add(medicationDateEndOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 420, 500, 37));

        medicationFinishDateOutput.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medicationFinishDateOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        MedicalHistoryPanel.add(medicationFinishDateOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 500, 49));

        medicationSideEffectsLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        medicationSideEffectsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medicationSideEffectsLabel.setText("Medication Side Effects");
        MedicalHistoryPanel.add(medicationSideEffectsLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 100, 530, 34));

        medicationSideEffectsOutput.setEditable(false);
        medicationSideEffectsOutput.setColumns(20);
        medicationSideEffectsOutput.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        medicationSideEffectsOutput.setLineWrap(true);
        medicationSideEffectsOutput.setRows(5);
        medicationSideEffectsOutput.setMargin(new java.awt.Insets(40, 40, 40, 40));
        jScrollPane5.setViewportView(medicationSideEffectsOutput);

        MedicalHistoryPanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 143, 530, 369));

        medicationDoctorNameLabel.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        medicationDoctorNameLabel.setText("Doctor Name");
        MedicalHistoryPanel.add(medicationDoctorNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 524, 500, 36));

        medicationDoctorNameOutput.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medicationDoctorNameOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        MedicalHistoryPanel.add(medicationDoctorNameOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 560, 500, 50));

        medicationPreviousButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medicationPreviousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/leftArrow.png"))); // NOI18N
        medicationPreviousButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        medicationPreviousButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicationPreviousButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicationPreviousButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                medicationPreviousButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                medicationPreviousButtonMouseExited(evt);
            }
        });
        MedicalHistoryPanel.add(medicationPreviousButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 540, -1, -1));

        medicationNextButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        medicationNextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/rightArrow.png"))); // NOI18N
        medicationNextButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        medicationNextButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicationNextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicationNextButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                medicationNextButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                medicationNextButtonMouseExited(evt);
            }
        });
        MedicalHistoryPanel.add(medicationNextButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 540, -1, -1));

        MedicationInfoOptions.setBackground(new java.awt.Color(14, 78, 108));
        MedicationInfoOptions.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        MedicationInfoOptions.setForeground(new java.awt.Color(255, 255, 255));
        MedicationInfoOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Medication", "Procedures", "Surgery Appointments", "Hospital Appointments" }));
        MedicationInfoOptions.setToolTipText("");
        MedicationInfoOptions.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        MedicationInfoOptions.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MedicationInfoOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MedicationInfoOptionsActionPerformed(evt);
            }
        });
        MedicalHistoryPanel.add(MedicationInfoOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 250, 60));

        AddInformation.setBackground(new java.awt.Color(14, 78, 108));
        AddInformation.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        AddInformation.setForeground(new java.awt.Color(255, 255, 255));
        AddInformation.setText("Add Medication");
        AddInformation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AddInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddInformationActionPerformed(evt);
            }
        });
        MedicalHistoryPanel.add(AddInformation, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 560, 250, 90));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        MedicalHistoryPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(MedicalHistoryPanel, "card13");

        SignInPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        signInComponentsConainer.setBackground(new java.awt.Color(14, 78, 108));

        signInEmailLabel.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        signInEmailLabel.setForeground(new java.awt.Color(255, 255, 255));
        signInEmailLabel.setText("Email");

        signInPasswordLabel.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        signInPasswordLabel.setForeground(new java.awt.Color(255, 255, 255));
        signInPasswordLabel.setText("Password");

        loginEmail.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        loginEmail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginEmail.setText("Thomas@beaumont.ie");
        loginEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        loginPassword.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        loginPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginPassword.setText("StacyStacy");
        loginPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        signInButton.setBackground(new java.awt.Color(137, 207, 240));
        signInButton.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        signInButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-sign-in-32.png"))); // NOI18N
        signInButton.setText("Sign In");
        signInButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        createAccountButton.setBackground(new java.awt.Color(137, 207, 240));
        createAccountButton.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        createAccountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-account-32.png"))); // NOI18N
        createAccountButton.setText("Create Account");
        createAccountButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        createAccountButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signInComponentsConainerLayout = new javax.swing.GroupLayout(signInComponentsConainer);
        signInComponentsConainer.setLayout(signInComponentsConainerLayout);
        signInComponentsConainerLayout.setHorizontalGroup(
            signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInComponentsConainerLayout.createSequentialGroup()
                .addGroup(signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signInComponentsConainerLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(loginEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(createAccountButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                                .addComponent(signInButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(signInComponentsConainerLayout.createSequentialGroup()
                        .addGap(311, 311, 311)
                        .addComponent(signInPasswordLabel))
                    .addGroup(signInComponentsConainerLayout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(signInEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        signInComponentsConainerLayout.setVerticalGroup(
            signInComponentsConainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInComponentsConainerLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(signInEmailLabel)
                .addGap(18, 18, 18)
                .addComponent(loginEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(signInPasswordLabel)
                .addGap(18, 18, 18)
                .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(signInButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(createAccountButton)
                .addGap(46, 46, 46))
        );

        SignInPanel.add(signInComponentsConainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        signInHeading.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        signInHeading.setText("Sign In");
        SignInPanel.add(signInHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        SignInPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(SignInPanel, "card4");

        PrescribMedicationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        prescribeMedicationHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        prescribeMedicationHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prescribeMedicationHeading.setText("Add Prescription Page");
        PrescribMedicationPanel.add(prescribeMedicationHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 25, 444, 69));

        AddPrescriptionMedNameLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        AddPrescriptionMedNameLabel.setText("Medication Name");
        PrescribMedicationPanel.add(AddPrescriptionMedNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, 160, 43));

        addPrescriptionDateStartLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        addPrescriptionDateStartLabel.setText("Date Prescrbed");
        PrescribMedicationPanel.add(addPrescriptionDateStartLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 220, 140, 33));

        addPrescriptionDateFinishLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        addPrescriptionDateFinishLabel.setText("Medication FInish Date");
        PrescribMedicationPanel.add(addPrescriptionDateFinishLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 200, -1));

        medicationComboBox.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PrescribMedicationPanel.add(medicationComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 420, 50));

        medicationPrescribedMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationPrescribedMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        medicationPrescribedMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicationPrescribedMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicationPrescribedMonthActionPerformed(evt);
            }
        });
        PrescribMedicationPanel.add(medicationPrescribedMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 213, 140, 40));

        medicationPrescribedDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationPrescribedDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PrescribMedicationPanel.add(medicationPrescribedDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 213, 130, 40));

        medicationFinishMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationFinishMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        medicationFinishMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicationFinishMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicationFinishMonthActionPerformed(evt);
            }
        });
        PrescribMedicationPanel.add(medicationFinishMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 263, 140, 40));

        medicationFinishDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationFinishDay.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PrescribMedicationPanel.add(medicationFinishDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 262, 130, 40));

        addPrescriptionButton.setBackground(new java.awt.Color(14, 78, 108));
        addPrescriptionButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addPrescriptionButton.setForeground(new java.awt.Color(255, 255, 255));
        addPrescriptionButton.setText("Add Patient Prescription");
        addPrescriptionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addPrescriptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrescriptionButtonActionPerformed(evt);
            }
        });
        PrescribMedicationPanel.add(addPrescriptionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 330, 350, 61));

        nextMedication.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextMedication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/rightArrow.png"))); // NOI18N
        nextMedication.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        nextMedication.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextMedication.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMedicationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextMedicationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextMedicationMouseExited(evt);
            }
        });
        PrescribMedicationPanel.add(nextMedication, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 490, 156, 156));

        previousMedication.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        previousMedication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/leftArrow.png"))); // NOI18N
        previousMedication.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        previousMedication.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        previousMedication.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                previousMedicationMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                previousMedicationMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                previousMedicationMouseExited(evt);
            }
        });
        PrescribMedicationPanel.add(previousMedication, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 156, 156));

        updatePrescriptionButton.setBackground(new java.awt.Color(14, 78, 108));
        updatePrescriptionButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        updatePrescriptionButton.setForeground(new java.awt.Color(255, 255, 255));
        updatePrescriptionButton.setText("Update Medication You Prescribed ");
        updatePrescriptionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updatePrescriptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePrescriptionButtonActionPerformed(evt);
            }
        });
        PrescribMedicationPanel.add(updatePrescriptionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 350, 61));

        AddPrescriptionGoBackButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AddPrescriptionGoBackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        AddPrescriptionGoBackButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddPrescriptionGoBackButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AddPrescriptionGoBackButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AddPrescriptionGoBackButtonMouseExited(evt);
            }
        });
        PrescribMedicationPanel.add(AddPrescriptionGoBackButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 12, -1, -1));

        updatePrescriptionButtonQuery.setBackground(new java.awt.Color(14, 78, 108));
        updatePrescriptionButtonQuery.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        updatePrescriptionButtonQuery.setForeground(new java.awt.Color(255, 255, 255));
        updatePrescriptionButtonQuery.setText("Update Presctiption");
        updatePrescriptionButtonQuery.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updatePrescriptionButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePrescriptionButtonQueryActionPerformed(evt);
            }
        });
        PrescribMedicationPanel.add(updatePrescriptionButtonQuery, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 600, 70));

        medicationFinishYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        medicationFinishYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        PrescribMedicationPanel.add(medicationFinishYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 260, 130, 40));

        medicationPrescribedYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        PrescribMedicationPanel.add(medicationPrescribedYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 210, 130, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        PrescribMedicationPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(PrescribMedicationPanel, "card14");

        ProceduresCompletedPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        proceduresCompletedHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        proceduresCompletedHeading.setText("Add and Edit Procedures ");
        ProceduresCompletedPanel.add(proceduresCompletedHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, 440, 37));

        procedureDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        procedureDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        ProceduresCompletedPanel.add(procedureDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 121, -1));

        procedureMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        procedureMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        procedureMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procedureMonthActionPerformed(evt);
            }
        });
        ProceduresCompletedPanel.add(procedureMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 120, -1));

        procedureYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        ProceduresCompletedPanel.add(procedureYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 250, 122, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel5.setText("Procedure Name");
        ProceduresCompletedPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, -1, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel6.setText("Precedure Date");
        ProceduresCompletedPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel7.setText("Procedure Summary");
        ProceduresCompletedPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));

        procedureSummary.setColumns(20);
        procedureSummary.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        procedureSummary.setLineWrap(true);
        procedureSummary.setRows(5);
        procedureSummary.setMargin(new java.awt.Insets(20, 20, 20, 20));
        jScrollPane1.setViewportView(procedureSummary);

        ProceduresCompletedPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 405, 292));

        procedureComboBox.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        ProceduresCompletedPanel.add(procedureComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 381, -1));

        addProcedureButton.setBackground(new java.awt.Color(14, 78, 108));
        addProcedureButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        addProcedureButton.setForeground(new java.awt.Color(255, 255, 255));
        addProcedureButton.setText("Add Procedure");
        addProcedureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProcedureButtonActionPerformed(evt);
            }
        });
        ProceduresCompletedPanel.add(addProcedureButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 475, 71));

        showEditOptionsButton.setBackground(new java.awt.Color(14, 78, 108));
        showEditOptionsButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        showEditOptionsButton.setForeground(new java.awt.Color(255, 255, 255));
        showEditOptionsButton.setText("Edit Current Patients Procedure Informaion");
        showEditOptionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEditOptionsButtonActionPerformed(evt);
            }
        });
        ProceduresCompletedPanel.add(showEditOptionsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 250, 475, 69));

        prevProcedureUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevProcedureUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/leftArrow.png"))); // NOI18N
        prevProcedureUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        prevProcedureUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevProcedureUpdateMouseClicked(evt);
            }
        });
        ProceduresCompletedPanel.add(prevProcedureUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 340, 155, 160));

        nextProcedureUpdate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextProcedureUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/rightArrow.png"))); // NOI18N
        nextProcedureUpdate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        nextProcedureUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextProcedureUpdateMouseClicked(evt);
            }
        });
        ProceduresCompletedPanel.add(nextProcedureUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 340, 155, 160));

        updateProcedureButton.setBackground(new java.awt.Color(14, 78, 108));
        updateProcedureButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        updateProcedureButton.setForeground(new java.awt.Color(255, 255, 255));
        updateProcedureButton.setText("Update Currently Displayed Procedure");
        updateProcedureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateProcedureButtonActionPerformed(evt);
            }
        });
        ProceduresCompletedPanel.add(updateProcedureButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 520, 460, 80));

        goBackProceduresPage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goBackProceduresPage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        goBackProceduresPage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goBackProceduresPageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                goBackProceduresPageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                goBackProceduresPageMouseExited(evt);
            }
        });
        ProceduresCompletedPanel.add(goBackProceduresPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 100));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        ProceduresCompletedPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(ProceduresCompletedPanel, "card15");

        AppointmentAddPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Add / Edit Patients Hospital Appontments");
        AppointmentAddPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(282, 28, 741, 63));

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel12.setText("Appointment Date");
        AppointmentAddPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 157, -1));

        jLabel13.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel13.setText("Appointment Time");
        AppointmentAddPanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, -1, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel14.setText("Appointment Summary");
        AppointmentAddPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 130, 207, -1));

        appointmentDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        appointmentDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "19", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        appointmentDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentDayActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(appointmentDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 85, 40));

        appointmentMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        appointmentMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        appointmentMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentMonthActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(appointmentMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 87, 40));

        appointmentYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        appointmentYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentYearActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(appointmentYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 88, 40));

        appointmentTimeDigit.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        appointmentTimeDigit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                appointmentTimeDigitActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(appointmentTimeDigit, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 150, 40));

        appointmentSummary.setColumns(20);
        appointmentSummary.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        appointmentSummary.setLineWrap(true);
        appointmentSummary.setRows(5);
        appointmentSummary.setMargin(new java.awt.Insets(40, 40, 40, 40));
        jScrollPane6.setViewportView(appointmentSummary);

        AppointmentAddPanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 490, 280));

        AddNewAppointment.setBackground(new java.awt.Color(14, 78, 108));
        AddNewAppointment.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        AddNewAppointment.setForeground(new java.awt.Color(255, 255, 255));
        AddNewAppointment.setText("Add Patients Appointment");
        AddNewAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddNewAppointmentActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(AddNewAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, 450, 80));

        dispalyUpdateComponents.setBackground(new java.awt.Color(14, 78, 108));
        dispalyUpdateComponents.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        dispalyUpdateComponents.setForeground(new java.awt.Color(255, 255, 255));
        dispalyUpdateComponents.setText("Update Existing Appoinment");
        dispalyUpdateComponents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispalyUpdateComponentsActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(dispalyUpdateComponents, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 570, 450, 80));

        updateExistingAppointment.setBackground(new java.awt.Color(14, 78, 108));
        updateExistingAppointment.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        updateExistingAppointment.setForeground(new java.awt.Color(255, 255, 255));
        updateExistingAppointment.setText("Update Appointment");
        updateExistingAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateExistingAppointmentActionPerformed(evt);
            }
        });
        AppointmentAddPanel.add(updateExistingAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 440, 80));

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel11.setText("Hospital Name");
        AppointmentAddPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 157, 27));

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel15.setText("Department Name");
        AppointmentAddPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 157, 27));

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel17.setText("Clinic");
        AppointmentAddPanel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 157, 31));

        prevAppointmentButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevAppointmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/leftArrow.png"))); // NOI18N
        prevAppointmentButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        prevAppointmentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevAppointmentButtonMouseClicked(evt);
            }
        });
        AppointmentAddPanel.add(prevAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 520, 150, 140));

        nextAppointmentButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextAppointmentButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/rightArrow.png"))); // NOI18N
        nextAppointmentButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        nextAppointmentButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextAppointmentButtonMouseClicked(evt);
            }
        });
        AppointmentAddPanel.add(nextAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 520, 150, 140));

        AppointmentAddPanel.add(hospitalNameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 270, 40));

        AppointmentAddPanel.add(DepartmentComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 270, 40));

        AppointmentAddPanel.add(clinicNameComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 270, 40));

        appointmentGoBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        appointmentGoBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        appointmentGoBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        appointmentGoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                appointmentGoBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                appointmentGoBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                appointmentGoBackMouseExited(evt);
            }
        });
        AppointmentAddPanel.add(appointmentGoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 100, 100));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        AppointmentAddPanel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, -1));

        ApplicationInterfacePanel.add(AppointmentAddPanel, "card15");

        SurgeryAppointmentPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        surgeryAppointmentHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        surgeryAppointmentHeading.setText("Add / Edit Surgery Appointment");
        SurgeryAppointmentPanel.add(surgeryAppointmentHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 53, 577, 51));

        sAppDate.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        sAppDate.setText("Apointment Date");
        SurgeryAppointmentPanel.add(sAppDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 180, 200, 21));

        sAppTime.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        sAppTime.setText("Appointment Time");
        SurgeryAppointmentPanel.add(sAppTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 220, -1));

        sAppSurgerySummary.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        sAppSurgerySummary.setText("Appointment Summary");
        SurgeryAppointmentPanel.add(sAppSurgerySummary, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 280, -1));

        prevSAppointment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        prevSAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/leftArrow.png"))); // NOI18N
        prevSAppointment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        prevSAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prevSAppointmentMouseClicked(evt);
            }
        });
        SurgeryAppointmentPanel.add(prevSAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 145, 136));

        nextSAppointment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nextSAppointment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/rightArrow.png"))); // NOI18N
        nextSAppointment.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        nextSAppointment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextSAppointmentMouseClicked(evt);
            }
        });
        SurgeryAppointmentPanel.add(nextSAppointment, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 145, 136));

        sAppSummary.setColumns(20);
        sAppSummary.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        sAppSummary.setLineWrap(true);
        sAppSummary.setRows(5);
        sAppSummary.setMargin(new java.awt.Insets(40, 40, 40, 40));
        jScrollPane7.setViewportView(sAppSummary);

        SurgeryAppointmentPanel.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(621, 177, 610, 272));

        updateSAppointmentButton.setBackground(new java.awt.Color(14, 78, 108));
        updateSAppointmentButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        updateSAppointmentButton.setForeground(new java.awt.Color(255, 255, 255));
        updateSAppointmentButton.setText("Update Appointment");
        updateSAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSAppointmentButtonActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(updateSAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, 310, 80));

        showSAppUpdateComponents.setBackground(new java.awt.Color(14, 78, 108));
        showSAppUpdateComponents.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        showSAppUpdateComponents.setForeground(new java.awt.Color(255, 255, 255));
        showSAppUpdateComponents.setText("Update Exitsting Appointment");
        showSAppUpdateComponents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSAppUpdateComponentsActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(showSAppUpdateComponents, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 570, 500, 80));

        addSAppointmentButton.setBackground(new java.awt.Color(14, 78, 108));
        addSAppointmentButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        addSAppointmentButton.setForeground(new java.awt.Color(255, 255, 255));
        addSAppointmentButton.setText("Add Appointment");
        addSAppointmentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSAppointmentButtonActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(addSAppointmentButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, 500, 80));

        surgeryAppDay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        surgeryAppDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "19", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        surgeryAppDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surgeryAppDayActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(surgeryAppDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 110, 40));

        surgeryAppMonth.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        surgeryAppMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "19", "11", "12" }));
        surgeryAppMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                surgeryAppMonthActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(surgeryAppMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 220, 100, 40));

        surgeryAppYear.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        SurgeryAppointmentPanel.add(surgeryAppYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 100, 40));

        sAppTImeNumber.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        sAppTImeNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sAppTImeNumberActionPerformed(evt);
            }
        });
        SurgeryAppointmentPanel.add(sAppTImeNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 160, 40));

        suregerAppGoBack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        suregerAppGoBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/icons8-go-back-96.png"))); // NOI18N
        suregerAppGoBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suregerAppGoBackMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                suregerAppGoBackMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                suregerAppGoBackMouseExited(evt);
            }
        });
        SurgeryAppointmentPanel.add(suregerAppGoBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 90));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        SurgeryAppointmentPanel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(SurgeryAppointmentPanel, "card16");

        LifeStylePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel20.setText("Lifestyle Page");
        jLabel20.setToolTipText("");
        LifeStylePanel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 40, 257, -1));

        bloodLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        bloodLabel.setText("Blood Type");
        LifeStylePanel.add(bloodLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 110, 20));

        emergencyNumberLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        emergencyNumberLabel.setText("Emergency Number");
        LifeStylePanel.add(emergencyNumberLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, -1, 21));

        occupationLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        occupationLabel.setText("Occupation");
        LifeStylePanel.add(occupationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 260, 98, -1));

        SmokerLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        SmokerLabel.setText("Smoker Status");
        LifeStylePanel.add(SmokerLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 260, 136, -1));

        activityLabel.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        activityLabel.setText("Weekly Activity");
        LifeStylePanel.add(activityLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 140, -1));

        bloodTypeDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        bloodTypeDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bloodTypeDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        LifeStylePanel.add(bloodTypeDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 395, 34));

        emergencyNumberDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        emergencyNumberDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emergencyNumberDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        LifeStylePanel.add(emergencyNumberDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 395, 34));

        occupationDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        occupationDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        occupationDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        LifeStylePanel.add(occupationDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, 395, 34));

        smokeQuestionDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        smokeQuestionDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        smokeQuestionDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        LifeStylePanel.add(smokeQuestionDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 290, 395, 35));

        weeklyActivityDisplay.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        weeklyActivityDisplay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        weeklyActivityDisplay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        LifeStylePanel.add(weeklyActivityDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 360, 395, 34));

        updateLifestylePanel.setBackground(new java.awt.Color(14, 78, 108));

        jLabel26.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Update Lifestyle ");

        OptionTextUpdateTf.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N

        UpdateOptionBox.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        UpdateOptionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Blood Type", "Emergency Number", "Occupation", "Smoker", "Weekly Activity" }));

        UpdateActivityButton.setBackground(new java.awt.Color(137, 207, 240));
        UpdateActivityButton.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        UpdateActivityButton.setText("Update");
        UpdateActivityButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        UpdateActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActivityButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateLifestylePanelLayout = new javax.swing.GroupLayout(updateLifestylePanel);
        updateLifestylePanel.setLayout(updateLifestylePanelLayout);
        updateLifestylePanelLayout.setHorizontalGroup(
            updateLifestylePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLifestylePanelLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(updateLifestylePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLifestylePanelLayout.createSequentialGroup()
                        .addComponent(UpdateOptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(OptionTextUpdateTf, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLifestylePanelLayout.createSequentialGroup()
                        .addComponent(UpdateActivityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateLifestylePanelLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(262, 262, 262))))
        );
        updateLifestylePanelLayout.setVerticalGroup(
            updateLifestylePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateLifestylePanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel26)
                .addGap(29, 29, 29)
                .addGroup(updateLifestylePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UpdateOptionBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OptionTextUpdateTf, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(UpdateActivityButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        LifeStylePanel.add(updateLifestylePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 710, 210));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        LifeStylePanel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 680));

        ApplicationInterfacePanel.add(LifeStylePanel, "card17");

        BenefitsPanel.setPreferredSize(new java.awt.Dimension(1295, 690));
        BenefitsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        benefitsReminderHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        benefitsReminderHeading.setText("Reminders & Notifications");
        BenefitsPanel.add(benefitsReminderHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        smoothHealthHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        smoothHealthHeading.setText("Smooth Health ");
        smoothHealthHeading.setToolTipText("");
        BenefitsPanel.add(smoothHealthHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 140, -1));

        benefitsInstantHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        benefitsInstantHeading.setText("Instant Access to");
        BenefitsPanel.add(benefitsInstantHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 130, 150, -1));

        benefitsPrivacyHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        benefitsPrivacyHeading.setText("Enhanced Privacy ");
        BenefitsPanel.add(benefitsPrivacyHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 400, 170, -1));

        BenefitsHeading.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        BenefitsHeading.setText("Benefits Of Joining SaveCare");
        BenefitsHeading.setToolTipText("");
        BenefitsPanel.add(BenefitsHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 40, 514, -1));

        benefitsHeading2.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        benefitsHeading2.setText("Check Out The Key Features Below");
        BenefitsPanel.add(benefitsHeading2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, -1));

        benefitsInformationHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        benefitsInformationHeading.setText("Information Management");
        BenefitsPanel.add(benefitsInformationHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, -1, -1));

        benefitsMedicalHeading.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        benefitsMedicalHeading.setText(" Medical Professionals");
        BenefitsPanel.add(benefitsMedicalHeading, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 200, -1));

        BenefitBox3.setBackground(new java.awt.Color(14, 78, 108));
        BenefitBox3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ParagraphBenefit3Box1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box1.setText("SaveCare keeps patients informed about upcoming ");
        BenefitBox3.add(ParagraphBenefit3Box1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, 394, -1));

        ParagraphBenefit3Box2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box2.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box2.setText("appointments, medication schedules and other ");
        BenefitBox3.add(ParagraphBenefit3Box2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 394, -1));

        ParagraphBenefit3Box3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box3.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box3.setText("important health events with timely reminders ");
        BenefitBox3.add(ParagraphBenefit3Box3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 394, 30));

        ParagraphBenefit3Box4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box4.setText("and notifications. This helps patients to stay on ");
        BenefitBox3.add(ParagraphBenefit3Box4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 394, -1));

        ParagraphBenefit3Box5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box5.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box5.setText("track with their healthcare and ensure they don't ");
        BenefitBox3.add(ParagraphBenefit3Box5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 394, -1));

        ParagraphBenefit3Box6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box6.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box6.setText("miss any important appointments or ");
        BenefitBox3.add(ParagraphBenefit3Box6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 394, 30));

        ParagraphBenefit3Box7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box7.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box7.setText("medication schedules.");
        BenefitBox3.add(ParagraphBenefit3Box7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 380, -1));

        ParagraphBenefit3Box8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit3Box8.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit3Box8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit3Box8.setText("SaveCare keeps patients informed about upcoming ");
        BenefitBox3.add(ParagraphBenefit3Box8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 400, 30));

        BenefitsPanel.add(BenefitBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 420, 210));

        BenefitBox2.setBackground(new java.awt.Color(14, 78, 108));
        BenefitBox2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ParagraphBenefit2Box1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box1.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box1.setText("SaveCare provides patients with quick access to ");
        BenefitBox2.add(ParagraphBenefit2Box1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 50));

        ParagraphBenefit2Box2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box2.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box2.setText("medical professionals, including doctors, specialists ");
        BenefitBox2.add(ParagraphBenefit2Box2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 410, 40));

        ParagraphBenefit2Box3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box3.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box3.setText("and nurses, through a secure messaging system. ");
        BenefitBox2.add(ParagraphBenefit2Box3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 410, 50));

        ParagraphBenefit2Box4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box4.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box4.setText("Patients can easily communicate with their ");
        BenefitBox2.add(ParagraphBenefit2Box4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 410, 40));

        ParagraphBenefit2Box5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box5.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box5.setText("healthcare providers to schedule appointments, ");
        BenefitBox2.add(ParagraphBenefit2Box5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 410, 30));

        ParagraphBenefit2Box6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit2Box6.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit2Box6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit2Box6.setText("ask questions, and receive medical advice ");
        BenefitBox2.add(ParagraphBenefit2Box6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 410, 20));

        ParagraphBenefit2Box7.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        ParagraphBenefit2Box7.setText("and support.");
        BenefitBox2.add(ParagraphBenefit2Box7, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 678, -1, -1));

        BenefitsPanel.add(BenefitBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 160, 430, 210));

        BenefitBox1.setBackground(new java.awt.Color(14, 78, 108));
        BenefitBox1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ParagraphBenefit1Box1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box1.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box1.setText("SaveCare allows you to store all your health ");
        BenefitBox1.add(ParagraphBenefit1Box1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 310, 30));

        ParagraphBenefit1Box2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box2.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box2.setText("information in one place. You can easily access ");
        BenefitBox1.add(ParagraphBenefit1Box2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 330, 20));

        ParagraphBenefit1Box3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box3.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box3.setText("your medical history, prescriptions, and ");
        BenefitBox1.add(ParagraphBenefit1Box3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 290, 30));

        ParagraphBenefit1Box4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box4.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box4.setText("appointments from any device, at any time.");
        BenefitBox1.add(ParagraphBenefit1Box4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 300, 20));

        ParagraphBenefit1Box5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box5.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box5.setText("This makes it easy for you to keep track of your ");
        BenefitBox1.add(ParagraphBenefit1Box5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 320, 30));

        ParagraphBenefit1Box6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box6.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box6.setText("health, and share your information with healthcare ");
        BenefitBox1.add(ParagraphBenefit1Box6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 360, 20));

        ParagraphBenefit1Box7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit1Box7.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit1Box7.setText("providers whenever necessary.");
        BenefitBox1.add(ParagraphBenefit1Box7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 210, 30));

        BenefitsPanel.add(BenefitBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, 420, 220));

        BenefitBox4.setBackground(new java.awt.Color(14, 78, 108));
        BenefitBox4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ParagraphBenefit4Box1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box1.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box1.setText("SaveCare provides enhanced privacy and security ");
        BenefitBox4.add(ParagraphBenefit4Box1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 400, 30));

        ParagraphBenefit4Box2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box2.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box2.setText("for patients' health information, ensuring that their ");
        BenefitBox4.add(ParagraphBenefit4Box2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 400, 40));

        ParagraphBenefit4Box3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box3.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box3.setText("data is protected and secure at all times. With strict ");
        BenefitBox4.add(ParagraphBenefit4Box3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 400, 30));

        ParagraphBenefit4Box4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box4.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box4.setText("access controls and encryption protocols, patients ");
        BenefitBox4.add(ParagraphBenefit4Box4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 400, 40));

        ParagraphBenefit4Box5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box5.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box5.setText("can rest assured that their personal and ");
        BenefitBox4.add(ParagraphBenefit4Box5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 410, 50));

        ParagraphBenefit4Box6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        ParagraphBenefit4Box6.setForeground(new java.awt.Color(255, 255, 255));
        ParagraphBenefit4Box6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ParagraphBenefit4Box6.setText("medical information is safe and secure.");
        BenefitBox4.add(ParagraphBenefit4Box6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 410, 40));

        BenefitsPanel.add(BenefitBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 430, 430, 220));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/teamprojectsavecare/img/HomeBackgroundImg.jpg"))); // NOI18N
        BenefitsPanel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        ApplicationInterfacePanel.add(BenefitsPanel, "card3");

        getContentPane().add(ApplicationInterfacePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 181, 1296, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateUserOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateUserOptionActionPerformed

        //This method swaps components around when the type of user determines which type of account is to be created
        if (CreateUserOption.getSelectedItem().toString().matches("Doctor")) {
            createHospitalIdLabel.setVisible(true);
            CreateHospitalID.setVisible(true);
            createDoctorLabel.setVisible(true);
            createDoctorNumber.setVisible(true);
            createPatientMobileNumber.setVisible(false);
            createAddressLabel.setVisible(false);
            createPatientAddress.setVisible(false);
            createEircodeLabel.setVisible(false);
            createPatientEircode.setVisible(false);
            createDOBLabel.setVisible(false);
            createDay.setVisible(false);
            createMonth.setVisible(false);
            createYear.setVisible(false);
            createMaritalStatuseLabel.setVisible(false);
            createPatientMaritalStatus.setVisible(false);
            createSexLabel.setVisible(false);
            createPatientSex.setVisible(false);
            createRaceLabel.setVisible(false);
            createPatientRace.setVisible(false);
            createNationalityLabel.setVisible(false);
            createPatientNationality.setVisible(false);
            createMobileLabel.setVisible(false);
        }
        if (CreateUserOption.getSelectedItem().toString().matches("Patient")) {
            createHospitalIdLabel.setVisible(false);
            CreateHospitalID.setVisible(false);
            createDoctorLabel.setVisible(false);
            createDoctorNumber.setVisible(false);
            createMobileLabel.setVisible(true);
            createPatientMobileNumber.setVisible(true);
            createAddressLabel.setVisible(true);
            createPatientAddress.setVisible(true);
            createEircodeLabel.setVisible(true);
            createPatientEircode.setVisible(true);
            createDOBLabel.setVisible(true);
            createDay.setVisible(true);
            createMonth.setVisible(true);
            createYear.setVisible(true);
            createMaritalStatuseLabel.setVisible(true);
            createPatientMaritalStatus.setVisible(true);
            createSexLabel.setVisible(true);
            createPatientSex.setVisible(true);
            createRaceLabel.setVisible(true);
            createPatientRace.setVisible(true);
            createNationalityLabel.setVisible(true);
            createPatientNationality.setVisible(true);
        }
    }//GEN-LAST:event_CreateUserOptionActionPerformed

    private void createClearButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createClearButtonMouseClicked
        // This method clears all the create user fields when the clear button is pressed.
        createPatientPPS.setText("");
        createPatientFirstName.setText("");
        createPatientLastName.setText("");
        createPatientEmail.setText("");
        createPatientPassword.setText("");
        createPatientAddress.setText("");
        createPatientMobileNumber.setText("");
        createRePassword.setText("");
        createPatientEircode.setText("");
        createPatientEircode.setText("");


    }//GEN-LAST:event_createClearButtonMouseClicked

    private void createAccountBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountBtnMouseClicked

        //This method validates all user inputs when creating an account 
        // Validation to check all fields are not submitted empty is completed first.
        //Validation to ensure that the input are correct format or certain unique values (eg email, pps) are not registered twice.
        //Regex pattern matching was used to validate inputs and ensure they are of a certain format.
        String dateOfBirth = createYear.getSelectedItem() + "-" + createMonth.getSelectedItem() + "-" + createDay.getSelectedItem();

        if (emptyInput(createPatientFirstName.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the first name field");
            return;

        }

        if (emptyInput(createPatientLastName.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the last name field");
            return;

        }
        if (emptyInput(createPatientPPS.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the PPS number field");
            return;
        }

        if (patient.ppsNumberValidation(createPatientPPS.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid PPS Number entered");
            return;
        }

        if (emptyInput(createPatientMobileNumber.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the mobile numbe field");
            return;
        }

        if (patient.validatePhoneNumber(createPatientMobileNumber.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid mobile number");
            return;
        }

        if (emptyInput(createPatientAddress.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the home address field");
            return;

        }

        if (emptyInput(createPatientEircode.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid Eircode 'A65 F4E2' is a vilid format");
            return;
        }

        if (patient.validateEircode(createPatientEircode.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid Eircode 'A65 F4E2' is a vilid format");
            return;
        }

        if (emptyInput(createPatientEmail.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the email field");
            return;

        }
        if (patient.validateEmail(createPatientEmail.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid Email");

            return;
        }

        if (user.validateExistingEmail(createPatientEmail.getText()) == false) {
            JOptionPane.showMessageDialog(null, "The Email entered can't be used");
            return;
        }

        if (emptyInput(createPatientPassword.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill the password field");
            return;

        }

        if (patient.validatePassword(createPatientPassword.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid password,make sure your password is around 8 characters with special charaters,uppercase/lower case letters and numbers are included ");
            return;
        }

        if (!createRePassword.getText().equals(createPatientPassword.getText())) {
            JOptionPane.showMessageDialog(null, "Sorry both passwords should match ");
            return;

        }

        //ensure a user is ages 14 and older.
        if (getAge(dateOfBirth) < 14) {
            JOptionPane.showMessageDialog(null, "Aplogies, you must be at least 14 to register and use this application");
            return;
        }

        // This method regidsters the new created account into the user table.
        registerIntoUserTable();
        if (String.valueOf(CreateUserOption.getSelectedItem()).matches("Patient")) {
            //if the combobox value matches 'patient' we create a patient account
            createPatientAccount();
            setPatientInterface();
            UserLoggedInPanel.setVisible(false);
            LifeStylePanel.setVisible(true);

        }
        if (String.valueOf(CreateUserOption.getSelectedItem()).matches("Doctor")) {
            //if the combobox value matches 'doctor' we create a doctor account
            createDoctorAccount();
            doctor.setUserEmail(createPatientEmail.getText());

        }


    }//GEN-LAST:event_createAccountBtnMouseClicked

    //After validation occurs this moethod is called to insert thevalid input into the database
    public void createPatientAccount() {
        try {
            String dob = createDay.getSelectedItem() + "/" + createMonth.getSelectedItem() + "/" + createYear.getSelectedItem();
            //User user = new User();
            //create statement
            String id2 = setIDCreate("Patient");
            String newId = deconstructAndDecrementID(id2);

            String patientNumber1 = setIDCreate("Patient Number");
            int patNum = Integer.parseInt(patientNumber1);
            patNum++;
            patientNumber1 = String.valueOf(patNum);
            //execute sql update
            //set ID's to null 
            String insertPatient = "INSERT INTO Patient VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = patient.getMyConn().prepareStatement(insertPatient);
            pstmt.setString(1, newId);
            pstmt.setNull(2, Types.NULL);
            pstmt.setNull(3, Types.NULL);
            pstmt.setNull(4, Types.NULL);
            pstmt.setString(5, createPatientPPS.getText());
            pstmt.setString(6, createPatientFirstName.getText());
            pstmt.setString(7, createPatientLastName.getText());
            pstmt.setString(8, createPatientEmail.getText());
            pstmt.setString(9, createPatientPassword.getText());
            pstmt.setString(10, createPatientAddress.getText());
            pstmt.setString(11, createPatientEircode.getText());
            pstmt.setString(12, createPatientMobileNumber.getText());
            pstmt.setString(13, createPatientNationality.getSelectedItem().toString());
            pstmt.setString(14, createPatientRace.getSelectedItem().toString());
            pstmt.setString(15, dob);
            pstmt.setString(16, createPatientMaritalStatus.getSelectedItem().toString());
            pstmt.setString(17, String.valueOf(createPatientSex.getSelectedItem()));
            pstmt.setString(19, patientNumber1);
            pstmt.setNull(18, Types.NULL);
            pstmt.setNull(20, Types.NULL);
            pstmt.setNull(21, Types.NULL);
            pstmt.setNull(22, Types.NULL);
            pstmt.setNull(23, Types.NULL);
            pstmt.execute();
            //patient email is set. When connect to display is called it sets all fields of the created/signed in patient
            //the database is queried using the patients email value (email is set up to be a unique value in the design of this project).
            //After fields are set for the patient the personal information page is filled with the patients details.
            patient.setUserEmail(createPatientEmail.getText());
            connectToDisplay(patient);
            displayUserData(patient);
            user = new Patient();
        } catch (SQLException e) {
            System.out.print(e + "\n");
        } catch (NumberFormatException ex) { // handle your exception
            System.out.print(ex + "\n");
        }

    }

    //if a doctors account is created and all inputs are valid this method iss called to insert the doctor into the database
    public void createDoctorAccount() {
        try {
            //User user = new User();
            //create statement
            String id2 = setIDCreate("Doctor");
            String newId = deconstructAndDecrementID(id2);
            //execute sql update
            //set ID's to null 
            String insertPatient = "INSERT INTO Doctor VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = patient.getMyConn().prepareStatement(insertPatient);
            pstmt.setString(1, newId);
            
            pstmt.setNull(2, Types.NULL);
            pstmt.setNull(3, Types.NULL);
            pstmt.setString(4, createPatientPPS.getText());
            pstmt.setString(5, createPatientFirstName.getText());
            pstmt.setString(6, createPatientLastName.getText());
            pstmt.setString(7, createPatientEmail.getText());
            pstmt.setString(8, createPatientPassword.getText());
            pstmt.setNull(9, Types.NULL);
            pstmt.setNull(10, Types.NULL);
            pstmt.setNull(11,Types.NULL);
            pstmt.execute();

        } catch (SQLException e) {
            System.out.print(e + "\n");
        } catch (NumberFormatException ex) { // handle your exception
            System.out.print(ex + "\n");
        }

    }

    //The created user is sent into this method to insert into user table. The user table is used to determine which type of user is signed in.
    //If a doctor signs in, the gui will have access to update o certain information
    //if the patient signs in they will have access to their own functionalities.
    //This user table was idea that resolved this problem unsuring the correct gui was displayed where needed.
    public void registerIntoUserTable() {

        try {

            String userEmail;
            String userType;
            String userPassword;
            String userId;
            String userOption = CreateUserOption.getSelectedItem().toString();

            String id2 = setIDCreate("User");
            String newId = deconstructAndDecrementID(id2);

            user.setUserEmail(createPatientEmail.getText());
            userEmail = user.getUserEmail();
            user.setUserPassword(createPatientPassword.getText());
            userPassword = user.getUserPassword();
            user.setUserId(newId);
            userId = user.getUserId();
            if (userOption.equals("Doctor")) {
                user.setUserType("Doctor");
            }

            if (userOption.equals("Patient")) {
                user.setUserType("Patient");
            }

            userType = user.getUserType();

            Statement myStatement = patient.myConn.createStatement();
            myStatement.executeUpdate("INSERT INTO Users "
                    + "VALUES('" + userId + "','" + userEmail + "','" + userPassword + "','" + userType + "')");

            // while loop to iterate through all records of customer table and display them
            // in the text field
        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    //The below lines from 3436 to 3506 are all asthetiic method for the gui components to change colour or sizes when hovered over.
    private void homeMenuButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuButtonMouseEntered
        homeMenuButton.setForeground(hoverColor);
    }//GEN-LAST:event_homeMenuButtonMouseEntered

    private void homeMenuButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuButtonMouseExited
        homeMenuButton.setForeground(whiteColor);
    }//GEN-LAST:event_homeMenuButtonMouseExited

    private void homeBenefitsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBenefitsButtonMouseEntered
        homeBenefitsButton.setForeground(hoverColor);
    }//GEN-LAST:event_homeBenefitsButtonMouseEntered

    private void homeBenefitsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBenefitsButtonMouseExited
        homeBenefitsButton.setForeground(whiteColor);
    }//GEN-LAST:event_homeBenefitsButtonMouseExited

    private void homeAboutUsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeAboutUsButtonMouseExited
        homeAboutUsButton.setForeground(whiteColor);
    }//GEN-LAST:event_homeAboutUsButtonMouseExited

    private void homeAboutUsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeAboutUsButtonMouseEntered
        homeAboutUsButton.setForeground(hoverColor);
    }//GEN-LAST:event_homeAboutUsButtonMouseEntered

    private void homeContactUsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeContactUsButtonMouseEntered
        homeContactUsButton.setForeground(hoverColor);
    }//GEN-LAST:event_homeContactUsButtonMouseEntered

    private void homeContactUsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeContactUsButtonMouseExited
        homeContactUsButton.setForeground(whiteColor);
    }//GEN-LAST:event_homeContactUsButtonMouseExited

    private void homeSignInButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeSignInButtonMouseEntered
        homeSignInButton.setForeground(hoverColor);
    }//GEN-LAST:event_homeSignInButtonMouseEntered

    private void homeSignInButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeSignInButtonMouseExited
        homeSignInButton.setForeground(whiteColor);
    }//GEN-LAST:event_homeSignInButtonMouseExited

    private void mainMenuPersonalInfoButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuPersonalInfoButtonMouseEntered
        mainMenuPersonalInfoButton.setForeground(hoverColor);
    }//GEN-LAST:event_mainMenuPersonalInfoButtonMouseEntered

    private void mainMenuPersonalInfoButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuPersonalInfoButtonMouseExited
        mainMenuPersonalInfoButton.setForeground(whiteColor);
    }//GEN-LAST:event_mainMenuPersonalInfoButtonMouseExited

    private void mainMenuMedicalHistoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuMedicalHistoryButtonMouseEntered
        mainMenuMedicalHistoryButton.setForeground(hoverColor);
    }//GEN-LAST:event_mainMenuMedicalHistoryButtonMouseEntered

    private void mainMenuMedicalHistoryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuMedicalHistoryButtonMouseExited
        mainMenuMedicalHistoryButton.setForeground(whiteColor);
    }//GEN-LAST:event_mainMenuMedicalHistoryButtonMouseExited

    private void mainUserOptionsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainUserOptionsMouseEntered
        mainUserOptions.setForeground(hoverColor);
    }//GEN-LAST:event_mainUserOptionsMouseEntered

    private void mainUserOptionsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainUserOptionsMouseExited
        mainUserOptions.setForeground(whiteColor);
    }//GEN-LAST:event_mainUserOptionsMouseExited

    private void mainMenuLogOutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuLogOutButtonMouseEntered
        mainMenuLogOutButton.setForeground(hoverColor);
    }//GEN-LAST:event_mainMenuLogOutButtonMouseEntered

    private void mainMenuLogOutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuLogOutButtonMouseExited
        mainMenuLogOutButton.setForeground(whiteColor);
    }//GEN-LAST:event_mainMenuLogOutButtonMouseExited

    private void mainMenuPersonalInfoButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuPersonalInfoButtonMouseClicked
        if (secureDoctorLoggedInPage() == false) {
            return;
        }
        PersonalInformationPanel.setVisible(true);
        PrescribMedicationPanel.setVisible(false);
        ProceduresCompletedPanel.setVisible(false);
        AppointmentAddPanel.setVisible(false);
        SurgeryAppointmentPanel.setVisible(false);
        LifeStylePanel.setVisible(false);
        QRScannerPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);

        ContactUsPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(false);


    }//GEN-LAST:event_mainMenuPersonalInfoButtonMouseClicked

    private void homeMenuButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMenuButtonMouseClicked
        HomePagePanel.setVisible(true);
        BenefitsPanel.setVisible(false);
        SignInPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
    }//GEN-LAST:event_homeMenuButtonMouseClicked

    private void homeBenefitsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeBenefitsButtonMouseClicked
        BenefitsPanel.setVisible(true);
        HomePagePanel.setVisible(false);
        SignInPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
    }//GEN-LAST:event_homeBenefitsButtonMouseClicked

    private void homeAboutUsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeAboutUsButtonMouseClicked
        AboutUsPanel.setVisible(true);
        HomePagePanel.setVisible(false);
        SignInPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        BenefitsPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
    }//GEN-LAST:event_homeAboutUsButtonMouseClicked

    private void homeContactUsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeContactUsButtonMouseClicked
        ContactUsPanel.setVisible(true);
        HomePagePanel.setVisible(false);
        SignInPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        BenefitsPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
    }//GEN-LAST:event_homeContactUsButtonMouseClicked

    private void homeSignInButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeSignInButtonMouseClicked
        SignInPanel.setVisible(true);
        HomePagePanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        BenefitsPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
    }//GEN-LAST:event_homeSignInButtonMouseClicked

    //Method when medical history tab is click that the medication history page shows
    private void mainMenuMedicalHistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuMedicalHistoryButtonMouseClicked
        //validation to ensure a patient is signed in because there will be no medical history to review or patients to add any information.
        if (secureDoctorLoggedInPage() == false) {
            return;
        }
        //method loads the medical history panel
        loadMedicationIntoPage();


    }//GEN-LAST:event_mainMenuMedicalHistoryButtonMouseClicked

    //medications are the first components to fill the page.
    // medsPrescribed array list is cleared then filled with all medication prescribed by previous doctors
    //after retrieval if no medications exist in the arraylist, the user is informed that the patient has no 
    //if the arraylist is not empty, the first medication fields are displyes to the user
    public void loadMedicationIntoPage() {
        MedicalHistoryPanel.setVisible(true);
        PrescribMedicationPanel.setVisible(false);
        ProceduresCompletedPanel.setVisible(false);
        AppointmentAddPanel.setVisible(false);
        SurgeryAppointmentPanel.setVisible(false);
        LifeStylePanel.setVisible(false);
        QRScannerPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        UserLoggedInPanel.setVisible(false);

        medsPrescribed.clear();
        patient.storeMedications(patient, medsPrescribed);
        if (medsPrescribed.isEmpty()) {
            medicationSideEffectsOutput.setText(" You have no medications prescribed on this system.");
            return;
        }

        setMedicationLabels();
        fillMedicationPanel();
        MedicationInfoOptions.setSelectedItem("Medication");
        AddInformation.setText("Add Medication");
    }
    private void mainUserOptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainUserOptionsMouseClicked

        //Opens User Logged in panel
        UserLoggedInPanel.setVisible(true);
        PrescribMedicationPanel.setVisible(false);
        ProceduresCompletedPanel.setVisible(false);
        AppointmentAddPanel.setVisible(false);
        SurgeryAppointmentPanel.setVisible(false);
        LifeStylePanel.setVisible(false);
        QRScannerPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(false);
    }//GEN-LAST:event_mainUserOptionsMouseClicked


    private void mainMenuLogOutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainMenuLogOutButtonMouseClicked

        Connection con = patient.getMyConn();
        try {
            con.close();

            doctor.getMyConn().close();
        } catch (SQLException ex) {
            Logger.getLogger(SaveCareGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        logOut();

    }//GEN-LAST:event_mainMenuLogOutButtonMouseClicked

    //method to empty the medication fields  
    public void emptyMedicalHistoryFields() {
        medicationNameOutput.setText("");
        medicationBrandOutput.setText("");
        medicationPrescribedOutput.setText("");
        medicationFinishDateOutput.setText("");
        medicationDoctorNameOutput.setText("");
        medicationSideEffectsOutput.setText("");

    }

    // this method fills the medications of panels comonents with the correct data for the user to make sense of the medication prescribed
    //medication prescribed number is the index of the arraylist to be displayed to the user
    public void fillMedicationPanel() {

        medicationNameOutput.setText(medsPrescribed.get(medicationPrescribedNumber).getMedicationName());
        medicationBrandOutput.setText(medsPrescribed.get(medicationPrescribedNumber).getMedicationBrand());
        medicationPrescribedOutput.setText(medsPrescribed.get(medicationPrescribedNumber).getMedicationDatePrescribed());
        if (medsPrescribed.get(medicationPrescribedNumber).getMedicationDateEnded() == null) {
            medicationFinishDateOutput.setText("Medication Still Prescribed");
        }
        if (medsPrescribed.get(medicationPrescribedNumber).getMedicationDateEnded() != null) {
            medicationFinishDateOutput.setText(medsPrescribed.get(medicationPrescribedNumber).getMedicationDateEnded());
        }

        medicationDoctorNameOutput.setText(medsPrescribed.get(medicationPrescribedNumber).getDoctorFirstName() + " " + medsPrescribed.get(medicationPrescribedNumber).getDoctorLastName());
        medicationSideEffectsOutput.setText("");
        medicationSideEffectsOutput.append(
                "Side Effects\n\n" + medsPrescribed.get(medicationPrescribedNumber).getMedicationSideEffects() + "\n\n"
                + "Dose\n\n"
                + medsPrescribed.get(medicationPrescribedNumber).getMedicationDose()
                + "\n\nMedication Recommendations\n\n"
                + medsPrescribed.get(medicationPrescribedNumber).getMedicationRecommendation());
    }

    public void emptyPanels() {

        // This methos is used to ensure all panels of the inteface cant beviewed.
        UserLoggedInPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(false);
        SignInPanel.setVisible(false);
        HomePagePanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        CreateAccountPanel.setVisible(false);
        BenefitsPanel.setVisible(false);
        AboutUsPanel.setVisible(false);
    }


    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed

        //This method validate the user inputs for the sign in panel of the application
        //Empty validation is done first and then format of email is checked to ensure the user is informed if they are entering an email of incorrrect format.
        if (emptyInput(loginEmail.getText()) == false || emptyInput(loginPassword.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields");
            return;
        }
        if (patient.validateEmail(loginEmail.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Email invalid address format");
            return;
        }

        if (user.signIn(loginEmail.getText(), loginPassword.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Invalid credentials");
            return;
        }

        if (user.signIn(loginEmail.getText(), loginPassword.getText()) == true) {
            switch (user.getUserType()) {
                case "Patient":
                    patient.setUserEmail(loginEmail.getText());
                    setPatientInterface();
                    break;

                case "Doctor":
                    doctor.setDoctorsFields(loginEmail.getText());
                    user = new Doctor();
                    UserLoggedInPanel.setVisible(true);
                    MainMenuPanel.setVisible(true);
                    HomeMenu.setVisible(false);
                    PersonalInformationPanel.setVisible(false);
                    SignInPanel.setVisible(false);
                    QRScannerPanel.setVisible(false);
                    GeneratedQRCodePanel.setVisible(false);
                    MedicalHistoryPanel.setVisible(false);
                    ContactUsPanel.setVisible(false);
                    updateLifestylePanel.setVisible(false);
                    updatePatientPersonalInfoBtn.setVisible(false);
                    updateFieldLabel.setVisible(false);
                    updateComboBox.setVisible(false);
                    updateFieldContent.setVisible(false);
                    AddInformation.setVisible(true);
                    SignedInHeading.setText("Welcome " + doctor.getUserFirstName());
                    break;
            }

        }


    }//GEN-LAST:event_signInButtonActionPerformed

    public void setPatientInterface() {

        //This method used when a patient creates a new account or if the user is already registered and just signing in.
        //
        user = new Patient();
        updatePatientPersonalInfoBtn.setVisible(true);
        updateFieldLabel.setVisible(true);
        updateComboBox.setVisible(true);
        updateFieldContent.setVisible(true);
        MainMenuPanel.setVisible(true);
        UserLoggedInPanel.setVisible(true);
        HomeMenu.setVisible(false);
        PersonalInformationPanel.setVisible(false);
        SignInPanel.setVisible(false);
        QRScannerPanel.setVisible(false);
        GeneratedQRCodePanel.setVisible(false);
        MedicalHistoryPanel.setVisible(false);
        ContactUsPanel.setVisible(false);
        updateLifestylePanel.setVisible(true);
        AddInformation.setVisible(false);
        CreateAccountPanel.setVisible(false);
        displayUserData(connectToDisplay(patient));
        SignedInHeading.setText("Welcome " + patient.getUserFirstName());
    }

    //This method validates a doctor has signed a patient into the application before trying to access certain applicatin pages
    public boolean secureDoctorLoggedInPage() {
        if (patient.getPatientID().matches("")) {
            JOptionPane.showMessageDialog(null, "You must sign in a patient with the QR Scanner \nbefore you can access these application pages");
            return false;
        }

        return true;
    }

    public void displayUserData(Patient patient) {

        firstNameDisplay.setText(patient.getUserFirstName());
        lastNameDisplay.setText(patient.getUserLastName());
        dateOfBirthDisplay.setText(patient.getPatientDateOfBirth());
        PPSDisplay.setText(patient.getUserPPSNumber());
        MobileNumberDisplay.setText(patient.getPatientPhoneNumber());
        homeAddressDisplay.setText(patient.getPatientAddress());
        eircodeDisplay.setText(patient.getPatientEircode());
        emailDisplay.setText(patient.getUserEmail());
        nationalityDisplay.setText(patient.getPatientNationality());
        raceDisplay.setText(patient.getPatientRace());
        maritalStatusDisplay.setText(patient.getPatientMaritalStatus());
        sexDisplay.setText(patient.getPatientSex());
        passwordDisplay.setText(patient.getUserPassword());
        patientNumberDisplay.setText(patient.getPatientNumber());
        bloodTypeDisplay.setText(patient.getPatientBloodType());
        emergencyNumberDisplay.setText(patient.getPatientEmergencyNumber());
        occupationDisplay.setText(patient.getPatientOccupation());
        smokeQuestionDisplay.setText(patient.getPatientSmoker());
        weeklyActivityDisplay.setText(patient.getPatientWeeklyActivity());

    }

    //This method sets all the patients field weather they sign in themselves or if a doctor retrieves them in while using the application.
    //Calls database with signed in or scanned qr code value of email address.
    //All fields selected from the patient table equal to the patien email.
    public Patient connectToDisplay(Patient patient) {
        try {
            patient.getConnection();

            Statement stmt = patient.getMyConn().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from Patient where PatientEmail='"
                    + patient.getUserEmail() + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {

                patient.setPatientID(rs.getString(1));
                patient.setPatientHospitalId(rs.getString(2));
                patient.setPharmacyID(rs.getString(3));
                patient.setPatientSurgeryId(rs.getString(4));
                patient.setUserPPSNumber(rs.getString(5));
                patient.setUserFirstName(rs.getString(6));
                patient.setUserLastName(rs.getString(7));
                patient.setUserEmail(rs.getString(8));
                patient.setUserPassword(rs.getString(9));
                patient.setPatientAddress(rs.getString(10));
                patient.setPatientEircode(rs.getString(11));
                patient.setPatientPhoneNumber(rs.getString(12));
                patient.setPatientNationality(rs.getString(13));
                patient.setPatientRace(rs.getString(14));
                patient.setPatientDateOfBirth(rs.getString(15));
                patient.setPatientMaritalStatus(rs.getString(16));
                patient.setPatientSex(rs.getString(17));
                patient.setPatientEmergencyNumber(rs.getString(18));
                patient.setPatientNumber(rs.getString(19));
                patient.setPatientBloodType(rs.getString(20));
                patient.setPatientSmoker(rs.getString(21));
                patient.setPatientWeeklyActivity(rs.getString(22));
                patient.setPatientOccupation(rs.getString(23));
            }
            return patient;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return patient;
    }

    public boolean emptyInput(String input) {

        if (input.matches("")) {
            return false;
        }
        return true;
    }

    //This method is used to inialise the webcam and qrScanner
    //video assistance link below.
    //https://www.youtube.com/watch?v=bqS5tjsy6A4&t=2s&ab_channel=SourceCodePH
    //link to zxing package below
    //https://github.com/zxing/zxing
    public void initWebcam() {

        if (webcam != null) {
            webcam = null;
            panel = null;

            cameraContainer = null;

        }

        //It initializes a Dimension object with the size of the video resolution
        Dimension size = WebcamResolution.QVGA.getSize();
        //It retrieves the default webcam connected to the system by calling the getWebcams()
        webcam = Webcam.getWebcams().get(0);
        //It sets the view size of the webcam to the previously initialized resolution

        if (webcam.getViewSize() != size) {
            webcam.setViewSize(size);
        }
        //It Creates a new WebcamPanel object, which is a graphical panel for displaying the video stream from the webcam, passing in the webcam object as a parameter.
        panel = new WebcamPanel(webcam);

        //It adds the webcam panel to a container called cameraContainer using the add() method, specifying the layout constraints using the AbsoluteLayout manager from the org.netbeans.lib.awtextra package
        cameraContainer.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 433));
        //It submits the current object (implementing the Runnable interface) to an executor for execution, indicating that it will run in a separate thread.
        executor.execute(this);

    }

    @Override
    public Thread newThread(Runnable r) {
        //New Thread Object is created with the passed in runnable object.
        t = new Thread(r, "My Thread");
        //A daemon thread is a thread that runs in the background and does not prevent the JVM from exiting if all non-daemon threads have terminated.
        t.setDaemon(true);
        return t;
    }

    @Override
    public void run() {
        try {
            //It starts a loop that will continue forever, as the condition true will always be true, unless the thread is interrupted or an exception occurs.
            do {

                try {
                    // It causes the thread to sleep for 100 milliseconds
                    //Ensures a delay for each loop 
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                //Objectsare used to store the result of decoding a QR code and capturing an image from the webcam.
                Result result = null;
                BufferedImage image = null;
                //It captures an image and assigns it to the image variable. If the captured image is null,
                //the loop continues to the next iteration.
                if (webcam.isOpen()) {
                    if ((image = webcam.getImage()) == null) {
                        continue;
                    }
                }
                try {
                    //LuminanceSource is a class that represents the luminance data of an image, which is required for decoding QR codes.
                    LuminanceSource source = new BufferedImageLuminanceSource(image);

                    //It creates a BinaryBitmap object from the LuminanceSource object,
                    //using a HybridBinarizer which is an implementation of the Binarizer interface.
                    //BinaryBitmap is a class that represents a binary image, which is required for decoding QR codes.
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    //It decodes the QR code from the BinaryBitmap object using an instance of MultiFormatReader, which is a multi-format
                    //barcode reader that can decode various types of barcodes, including QR codes. 
                    result = new MultiFormatReader().decode(bitmap);
                } catch (NotFoundException ex) {
                }
                //It checks if a QR code was successfully decoded by checking if the result variable is not null
                if (result != null) {
                    patient.setUserEmail(result.getText());
                    displayUserData(connectToDisplay(patient));
                    //Once QR is validated with correct qr value.
                    //Closes webcam and it signs patient in
                    if (!patient.getUserFirstName().equals("")) {
                        webcam.close();

                        updatePatientPersonalInfoBtn.setVisible(false);
                        updateFieldLabel.setVisible(false);
                        updateComboBox.setVisible(false);
                        updateFieldContent.setVisible(false);
                        PersonalInformationPanel.setVisible(true);
                        QRScannerPanel.setVisible(false);
                        return;
                    }
                    //if image is not null with invalid qr value. User is informed
                    JOptionPane.showMessageDialog(null, "The scanned qr is not a registered patients qrcode");
                }
            } while (true);
        } catch (Exception e) {
        }
    }

    //Lines 3941 - 4043 are methods for compontent interaction design.
    private void homeJoinUsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeJoinUsButtonMouseEntered

        homeJoinUsButton.setForeground(whiteColor);
        homeJoinUsButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));

    }//GEN-LAST:event_homeJoinUsButtonMouseEntered

    private void homeJoinUsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeJoinUsButtonMouseExited
        homeJoinUsButton.setForeground(blackColor);
        homeJoinUsButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_homeJoinUsButtonMouseExited

    private void homeJoinUsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeJoinUsButtonMouseClicked
        SignInPanel.setVisible(true);
        HomePagePanel.setVisible(false);

    }//GEN-LAST:event_homeJoinUsButtonMouseClicked

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed
        CreateAccountPanel.setVisible(true);
        SignInPanel.setVisible(false);
        // method to load date of birth years in to a combobox.
        loadDateOfBirthYears(createYear);
        String date = String.valueOf(getDateToday());
        splitDate(date);
    }//GEN-LAST:event_createAccountButtonActionPerformed

    // lines 3969 - 4043 are methods for compontent interaction design.
    private void QRScannerGoBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRScannerGoBackButtonMouseEntered
        QRScannerGoBackButton.setForeground(whiteColor);
        QRScannerGoBackButton.setBorder(BorderFactory.createLineBorder(whiteColor, 2));
    }//GEN-LAST:event_QRScannerGoBackButtonMouseEntered

    private void QRScannerGoBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRScannerGoBackButtonMouseExited
        QRScannerGoBackButton.setForeground(blackColor);
        QRScannerGoBackButton.setBorder(BorderFactory.createLineBorder(whiteColor, 0));
    }//GEN-LAST:event_QRScannerGoBackButtonMouseExited

    private void loggedInQRButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInQRButtonMouseEntered
        loggedInQRButton.setForeground(whiteColor);

        loggedInQRButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_loggedInQRButtonMouseEntered

    private void loggedInQRButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInQRButtonMouseExited
        loggedInQRButton.setForeground(blackColor);

        loggedInQRButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_loggedInQRButtonMouseExited

    private void loggedInQRPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInQRPanelMouseEntered
        loggedInQRButton.setForeground(whiteColor);
        loggedInQRButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));

        QRCodeIcon.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_loggedInQRPanelMouseEntered

    private void loggedInQRPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInQRPanelMouseExited

        loggedInQRButton.setForeground(blackColor);
        loggedInQRButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));

        QRCodeIcon.setBorder(BorderFactory.createLineBorder(blackColor, 2));
    }//GEN-LAST:event_loggedInQRPanelMouseExited

    private void loggedInMedicalHistoryPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInMedicalHistoryPanelMouseEntered
        loggedInMedicalHistoryButton.setForeground(whiteColor);
        loggedInMedicalHistoryButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));

        medicalHistoryIcon.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_loggedInMedicalHistoryPanelMouseEntered

    private void loggedInMedicalHistoryPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInMedicalHistoryPanelMouseExited
        loggedInMedicalHistoryButton.setForeground(blackColor);
        loggedInMedicalHistoryButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
        medicalHistoryIcon.setBorder(BorderFactory.createLineBorder(whiteColor, 0));
    }//GEN-LAST:event_loggedInMedicalHistoryPanelMouseExited

    private void loggedInPersonalInforPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInPersonalInforPanelMouseEntered
        loggedInPersonalInformationButton.setForeground(whiteColor);
        loggedInPersonalInformationButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));
        personalicon.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_loggedInPersonalInforPanelMouseEntered

    private void loggedInPersonalInforPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInPersonalInforPanelMouseExited
        loggedInPersonalInformationButton.setForeground(blackColor);
        loggedInPersonalInformationButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
        personalicon.setBorder(BorderFactory.createLineBorder(whiteColor, 0));
    }//GEN-LAST:event_loggedInPersonalInforPanelMouseExited

    private void logeedInMedicationPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logeedInMedicationPanelMouseEntered
        loggedInMedicationButton.setForeground(whiteColor);
        loggedInMedicationButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));
        MedicationTietableIcon.setBorder(BorderFactory.createLineBorder(whiteColor, 3));

    }//GEN-LAST:event_logeedInMedicationPanelMouseEntered

    private void logeedInMedicationPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logeedInMedicationPanelMouseExited
        loggedInMedicationButton.setForeground(blackColor);
        loggedInMedicationButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
        MedicationTietableIcon.setBorder(BorderFactory.createLineBorder(whiteColor, 0));

    }//GEN-LAST:event_logeedInMedicationPanelMouseExited

    private void loggedInQRPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInQRPanelMouseClicked

        //This method determines which panels is to be displayed when the qrcode button is pressed on the UserLoggedInPanel.
        //If the doctor is signed in the qr scanner shows
        //If a patient is signed in then the patient email is read and converted into a qr code and displated.
        boolean result = user instanceof Patient;
        if (result == true) {

            UserLoggedInPanel.setVisible(false);
            GeneratedQRCodePanel.setVisible(true);
            GeneratedQRCodeContainer.setIcon(patient.generateQR());
            return;
        }

        result = user instanceof Doctor;
        if (result == true) {

            UserLoggedInPanel.setVisible(false);
            emptyPanels();
            initWebcam();
            QRScannerPanel.setVisible(true);
        }


    }//GEN-LAST:event_loggedInQRPanelMouseClicked

    private void loggedInMedicalHistoryPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInMedicalHistoryPanelMouseClicked

        //It loads the medicationHistory page ( medications displayed first)
        if (secureDoctorLoggedInPage() == false) {
            return;
        }
        loadMedicationIntoPage();
    }//GEN-LAST:event_loggedInMedicalHistoryPanelMouseClicked

    private void loggedInPersonalInforPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loggedInPersonalInforPanelMouseClicked
        //This method opens the patients personal page from the User Logged In Panel
        if (secureDoctorLoggedInPage() == false) {
            return;
        }
        UserLoggedInPanel.setVisible(false);
        PersonalInformationPanel.setVisible(true);
    }//GEN-LAST:event_loggedInPersonalInforPanelMouseClicked

    private void logeedInMedicationPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logeedInMedicationPanelMouseClicked
        //This method opens the patients lifeStyle page from the User Logged In Panel
        if (secureDoctorLoggedInPage() == false) {
            return;
        }

        UserLoggedInPanel.setVisible(false);
        LifeStylePanel.setVisible(true);


    }//GEN-LAST:event_logeedInMedicationPanelMouseClicked

    //methods adds stng values to am arraylist.
    //These string values are injected into the update sql query ensuring the correct field is updated depending on 
    //the field selected by the user
    public void fillUpdateFields() {
        patientUpdateFields.add("Password");
        patientUpdateFields.add("Sex");
        patientUpdateFields.add("HomeAddress");
        patientUpdateFields.add("PhoneNumber");
        patientUpdateFields.add("MaritalStatus");
        patientUpdateFields.add("FirstName");
        patientUpdateFields.add("LastName");
        patientUpdateFields.add("PhoneNumber");
        patientUpdateFields.add("Email");
        patientUpdateFields.add("Eircode");
        patientUpdateFields.add("EmergencyNumber");
        patientUpdateFields.add("BloodType");
        patientUpdateFields.add("Smoker");
        patientUpdateFields.add("WeeklyActivity");
        patientUpdateFields.add("Occupation");

    }

    //It determines what postition of the arraylist should be used to be inserted into the udate sql query.
    public String validatUpdateField(String input) {

        if (input.matches("Password")) {
            input = patientUpdateFields.get(0);
            return input;
        }
        if (input.matches("Gender")) {
            input = patientUpdateFields.get(1);
            return input;
        }
        if (input.matches("Home Address")) {
            input = patientUpdateFields.get(2);
            return input;
        }
        if (input.matches("Phone Number")) {
            input = patientUpdateFields.get(3);
            return input;
        }
        if (input.matches("Marital Status")) {
            input = patientUpdateFields.get(4);
            return input;
        }
        if (input.matches("First Name")) {
            input = patientUpdateFields.get(5);
            return input;
        }
        if (input.matches("Last Name")) {
            input = patientUpdateFields.get(6);
            return input;
        }
        if (input.matches("Mobile Number")) {
            input = patientUpdateFields.get(7);
            return input;
        }
        if (input.matches("Email")) {
            input = patientUpdateFields.get(8);
            return input;
        }
        if (input.matches("Eircode")) {
            input = patientUpdateFields.get(9);
            return input;
        }
        if (input.matches("Emergency Number")) {
            input = patientUpdateFields.get(10);
            return input;
        }
        if (input.matches("Blood Type")) {
            input = patientUpdateFields.get(11);
            return input;
        }
        if (input.matches("Smoker")) {
            input = patientUpdateFields.get(12);
            return input;
        }
        if (input.matches("Weekly Activity")) {
            input = patientUpdateFields.get(13);
            return input;
        }
        if (input.matches("Occupation")) {
            input = patientUpdateFields.get(14);
            return input;
        }
        return null;
    }


    private void updatePatientPersonalInfoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePatientPersonalInfoBtnActionPerformed
        //This method validates if the update field is not calling the database while the value is empty 
        //The combobox value submitted is passed through a swtch statement to determine what arraylist value is used for the update query
        //once a valid input has been entered for the selected update field. Its then updated
        if (emptyInput(updateFieldContent.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Sorry you cant update with an empty value");
            return;
        }
        String updateField = validatUpdateField(String.valueOf(updateComboBox.getSelectedItem()));

        System.out.println(updateField);
        switch (updateField) {
            case "Password":
                String newPassword = updateFieldContent.getText();

                if (patient.validatePassword(newPassword) == false) {
                    JOptionPane.showMessageDialog(null, "Password is not long enough. Password was not updated");
                    return;
                }
                patient.changeData(updateFieldContent.getText(), updateField);
                patient.changeDataUsersTable(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "New Password has been updated");
                patient.changeDataUsersTable(updateFieldContent.getText(), updateField);
                displayUserData(connectToDisplay(patient));
                return;

            case "HomeAddress":
                patient.changeData(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Address has been updated");
                displayUserData(connectToDisplay(patient));
                return;

            case "PhoneNumber":

                if (patient.validatePhoneNumber(updateFieldContent.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "The number is not a valid mobile number. ");
                    return;
                }
                patient.changeData(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Phone Number has been updated");
                displayUserData(connectToDisplay(patient));

                return;
            case "MaritalStatus":
                if (patient.validateMaritalStatus(updateFieldContent.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "You must enter a valid marital status eg,\nSingle\nMarried\nDivorced\nSeperated");
                    return;
                }
                patient.changeData(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Marital Status has been updated");
                displayUserData(connectToDisplay(patient));
                return;

            case "FirstName":
                patient.changeData(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your First Name has been updated to " + updateFieldContent.getText());
                displayUserData(connectToDisplay(patient));

                return;

            case "LastName":
                patient.changeData(updateFieldContent.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Last Name has been updated to " + updateFieldContent.getText());
                displayUserData(connectToDisplay(patient));
                return;
            case "Email":
                if (patient.validateEmail(updateFieldContent.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "The entered input was not a valid Email. Please try again");

                    return;
                }
                if (patient.validateExistingEmail(updateFieldContent.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "The entered email cant be used(for correcter, this is validation to ensure\n users emails are not reused for other accounts)");

                    return;
                }
                patient.changeData(updateFieldContent.getText(), updateField);
                patient.changeDataUsersTable(updateFieldContent.getText(), updateField);
                patient.setUserEmail(updateFieldContent.getText());
                JOptionPane.showMessageDialog(null, "Your Email has been updated to " + updateFieldContent.getText());
                displayUserData(connectToDisplay(patient));

                return;

            case "Eircode":
                patient.changeData(updateFieldContent.getText(), updateField);
                patient.setPatientEircode(updateFieldContent.getText());
                JOptionPane.showMessageDialog(null, "Your Eircode has been updated to " + updateFieldContent.getText());
                displayUserData(connectToDisplay(patient));
                return;

        }
    }//GEN-LAST:event_updatePatientPersonalInfoBtnActionPerformed

    //lines 4290 - 4301  are methods for compontent interaction design.
    private void QRGeneratorGoBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRGeneratorGoBackButtonMouseEntered
        QRGeneratorGoBackButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_QRGeneratorGoBackButtonMouseEntered

    private void QRGeneratorGoBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRGeneratorGoBackButtonMouseExited
        QRGeneratorGoBackButton.setBorder(BorderFactory.createLineBorder(whiteColor, 0));
    }//GEN-LAST:event_QRGeneratorGoBackButtonMouseExited

    private void QRGeneratorGoBackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRGeneratorGoBackButtonMouseClicked
        UserLoggedInPanel.setVisible(true);
        GeneratedQRCodePanel.setVisible(false);
    }//GEN-LAST:event_QRGeneratorGoBackButtonMouseClicked

    private void medicationNextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationNextButtonMouseClicked

        //This method get the next procedure, medicaation,hospital appointment or surgery appointment
        //depending on what is currently selected by the used.
        //Arraylist is checked depending om selected item from combobox
        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Medication")) {
            if (medsPrescribed.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no medication history on this system");
                return;
            }
            if (medicationPrescribedNumber == medsPrescribed.size() - 1) {
                JOptionPane.showMessageDialog(null, "There are no more Medications");
                return;
            }
            //Arraylist index is incremented and fill medicstion panel is called to set the medication of the next arraylist medication
            medicationPrescribedNumber++;
            fillMedicationPanel();
            return;
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Procedures")) {
            if (proceduresCompleted.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no procedural history on this system");
                return;
            }
            if (proceduresCompletedNumber == proceduresCompleted.size() - 1) {
                JOptionPane.showMessageDialog(null, "There are no more Procedures Completed");
                return;
            }
            proceduresCompletedNumber++;
            setProcedurePanel();
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Hospital Appointments")) {

            if (hospitalAppointments.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no Appointment history on this system");
                return;
            }
            if (hospitalAppointmentNumber == hospitalAppointments.size() - 1) {
                JOptionPane.showMessageDialog(null, "There are no more Appointments to view");
                return;
            }
            hospitalAppointmentNumber++;
            fillAppointmentPanel();
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Surgery Appointments")) {

            if (surgeryAppointments.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no Appointment history on this system");
                return;
            }
            if (surgeryAppointmentNumber == surgeryAppointments.size() - 1) {
                JOptionPane.showMessageDialog(null, "There are no more Appointments to view");
                return;
            }
            surgeryAppointmentNumber++;
            fillSurgeryAppointmentPanel();
        }


    }//GEN-LAST:event_medicationNextButtonMouseClicked

    private void MedicationInfoOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MedicationInfoOptionsActionPerformed

        displayMedicalHistoryInformation();


    }//GEN-LAST:event_MedicationInfoOptionsActionPerformed

    public void fillSurgeryAppointmentPanel() {

        //It fills the surgeryAppointments panel with Surgery appointment informeation to be navigated through.
        medicationNameOutput.setText(surgeryAppointments.get(surgeryAppointmentNumber).getSurgeryName());
        medicationBrandOutput.setText(surgeryAppointments.get(surgeryAppointmentNumber).getSurgeryPhoneNumber());

        medicationPrescribedOutput.setText(surgeryAppointments.get(surgeryAppointmentNumber).getSurgeryPhoneNumber());

        medicationFinishDateOutput.setText(surgeryAppointments.get(surgeryAppointmentNumber).getDoctorFirstName() + " "
                + surgeryAppointments.get(surgeryAppointmentNumber).getDoctorLastName());

        medicationDoctorNameOutput.setText(surgeryAppointments.get(surgeryAppointmentNumber).getAppointmentDate() + " @ " + surgeryAppointments.get(surgeryAppointmentNumber).getAppointmentTime());

        medicationSideEffectsOutput.setText("");
        medicationSideEffectsOutput.append(surgeryAppointments.get(surgeryAppointmentNumber).getAppointmentSummary());
    }
    private void medicationPreviousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationPreviousButtonMouseClicked

        //This method get the previous procedure, medicaation,hospital appointment or surgery appointment
        //depending on what is currently selected by the used.
        //Arraylist is checked depending om selected item from combobox
        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Medication")) {
            if (medsPrescribed.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no medication history on this system");
                return;
            }
            if (medicationPrescribedNumber == 0) {
                JOptionPane.showMessageDialog(null, "There are no more Medications");
                return;
            }
            medicationPrescribedNumber--;
            fillMedicationPanel();
            return;
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Procedures")) {
            if (proceduresCompleted.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no procedural history on this system");
                return;
            }
            if (proceduresCompletedNumber == 0) {
                JOptionPane.showMessageDialog(null, "There are no more Procedures Completed");
                return;
            }
            proceduresCompletedNumber--;
            setProcedurePanel();
            return;
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Hospital Appointments")) {

            if (hospitalAppointments.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no Appointment history on this system");
                return;
            }
            if (hospitalAppointmentNumber == 0) {
                JOptionPane.showMessageDialog(null, "There are no more previous Appointments to view");
                return;
            }
            hospitalAppointmentNumber--;
            fillAppointmentPanel();
            return;
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Surgery Appointments")) {

            if (surgeryAppointments.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You have no Appointment history on this system");
                return;
            }
            if (surgeryAppointmentNumber == 0) {
                JOptionPane.showMessageDialog(null, "There are no more Appointments to view");
                return;
            }
            surgeryAppointmentNumber--;
            fillSurgeryAppointmentPanel();
        }
    }//GEN-LAST:event_medicationPreviousButtonMouseClicked

    //This method check the requested arraylist.
    //If the array list is empty the arraylist is sent to a method (depending on which arraylist is selected)
    //This method fills the arraylist with the medication/procedure/ appointment that the patient had. 
    //if the arraylist is still empty means there is no history of the patient with regards to the topic requested.
    //User is informed.
    //If any history exists the correct information is dispayed to the user
    public void displayMedicalHistoryInformation() {
        emptyMedicalHistoryFields();
        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Procedures")) {
            AddInformation.setText("Add Procedure");
            setProcedureLabels();
            if (proceduresCompleted.isEmpty()) {
                patient.storeProcedures(patient, proceduresCompleted);
            }

            if (proceduresCompleted.isEmpty()) {
                medicationSideEffectsOutput.setText("You do nove have any Procedures Completed on this System.");
                return;
            }

            setProcedurePanel();
            setProcedureLabels();
            return;

        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Medication")) {
            AddInformation.setText("Add Medication");
            if (medsPrescribed.isEmpty()) {
                patient.storeMedications(patient, medsPrescribed);
            }

            if (medsPrescribed.isEmpty()) {
                medicationSideEffectsOutput.setText(" You have no medications prescribed on this system.");
                return;
            }
            fillMedicationPanel();
            setMedicationLabels();
            return;
        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Hospital Appointments")) {
            AddInformation.setText("Add Hospital Appointment");
            if (hospitalAppointments.isEmpty()) {
                patient.storeAppointmets(patient, hospitalAppointments);
            }

            if (hospitalAppointments.isEmpty()) {
                medicationSideEffectsOutput.setText(" You have no future or past appoinments on this system.");
                return;
            }
            fillAppointmentPanel();
            setAppointmentLabels();
            return;

        }

        if (String.valueOf(MedicationInfoOptions.getSelectedItem()).matches("Surgery Appointments")) {

            AddInformation.setText("Add Surgery Appointment");
            if (surgeryAppointments.isEmpty()) {
                patient.storeSurgeryAppointmets(patient, surgeryAppointments);
            }

            if (surgeryAppointments.isEmpty()) {
                medicationSideEffectsOutput.setText(" You have no future or past Surgery Appoinments on this system.");
                return;
            }
            fillSurgeryAppointmentPanel();
            setAppointmentLabels();
            medicationNameLabel.setText("Surgery Name");
            MedicationBrandLabel.setText("Surgery Phone Number");
        }

    }

    //Lines 4530 - 4546 are methods for compontent interaction design.
    private void medicationPreviousButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationPreviousButtonMouseEntered
        medicationPreviousButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_medicationPreviousButtonMouseEntered

    private void medicationPreviousButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationPreviousButtonMouseExited
        medicationPreviousButton.setBorder(BorderFactory.createLineBorder(blackColor, 2));
    }//GEN-LAST:event_medicationPreviousButtonMouseExited

    private void medicationNextButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationNextButtonMouseEntered
        medicationNextButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_medicationNextButtonMouseEntered

    private void medicationNextButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicationNextButtonMouseExited
        medicationNextButton.setBorder(BorderFactory.createLineBorder(blackColor, 2));
    }//GEN-LAST:event_medicationNextButtonMouseExited

    //This method loads all times available for appointments into an arraylist. 
    //To be loaded into combox box later with already existing appointments times removed for the arraylist and then added to combobox
    //Ensuring that appointments are not over booked at any time.        
    public void loadTimes() {
        appointmentTimes.add("00.00");
        appointmentTimes.add("00.15");
        appointmentTimes.add("00.30");
        appointmentTimes.add("00.45");
        appointmentTimes.add("01.00");
        appointmentTimes.add("01.15");
        appointmentTimes.add("01.30");
        appointmentTimes.add("01.45");
        appointmentTimes.add("02.00");
        appointmentTimes.add("02.15");
        appointmentTimes.add("02.30");
        appointmentTimes.add("02.45");
        appointmentTimes.add("03.00");
        appointmentTimes.add("03.15");
        appointmentTimes.add("03.30");
        appointmentTimes.add("03.45");
        appointmentTimes.add("04.00");
        appointmentTimes.add("04.15");
        appointmentTimes.add("04.30");
        appointmentTimes.add("04.45");
        appointmentTimes.add("05.00");
        appointmentTimes.add("05.15");
        appointmentTimes.add("05.30");
        appointmentTimes.add("05.45");
        appointmentTimes.add("06.00");
        appointmentTimes.add("06.15");
        appointmentTimes.add("06.30");
        appointmentTimes.add("06.45");
        appointmentTimes.add("07.00");
        appointmentTimes.add("07.15");
        appointmentTimes.add("07.30");
        appointmentTimes.add("07.45");
        appointmentTimes.add("08.00");
        appointmentTimes.add("08.15");
        appointmentTimes.add("08.30");
        appointmentTimes.add("08.45");
        appointmentTimes.add("09.00");
        appointmentTimes.add("09.15");
        appointmentTimes.add("09.30");
        appointmentTimes.add("09.45");
        appointmentTimes.add("10.00");
        appointmentTimes.add("10.15");
        appointmentTimes.add("10.30");
        appointmentTimes.add("10.45");
        appointmentTimes.add("11.00");
        appointmentTimes.add("11.15");
        appointmentTimes.add("11.30");
        appointmentTimes.add("11.45");
        appointmentTimes.add("12.00");
        appointmentTimes.add("12.15");
        appointmentTimes.add("12.30");
        appointmentTimes.add("12.45");
        appointmentTimes.add("13.00");
        appointmentTimes.add("13.15");
        appointmentTimes.add("13.30");
        appointmentTimes.add("13.45");
        appointmentTimes.add("14.00");
        appointmentTimes.add("14.15");
        appointmentTimes.add("14.30");
        appointmentTimes.add("14.45");
        appointmentTimes.add("15.00");
        appointmentTimes.add("15.15");
        appointmentTimes.add("15.30");
        appointmentTimes.add("15.45");
        appointmentTimes.add("16.00");
        appointmentTimes.add("16.15");
        appointmentTimes.add("16.30");
        appointmentTimes.add("16.45");
        appointmentTimes.add("17.00");
        appointmentTimes.add("17.15");
        appointmentTimes.add("17.30");
        appointmentTimes.add("17.45");
        appointmentTimes.add("18.00");
        appointmentTimes.add("18.15");
        appointmentTimes.add("18.30");
        appointmentTimes.add("18.45");
        appointmentTimes.add("19.00");
        appointmentTimes.add("19.15");
        appointmentTimes.add("19.30");
        appointmentTimes.add("19.45");
        appointmentTimes.add("20.00");
        appointmentTimes.add("20.15");
        appointmentTimes.add("20.30");
        appointmentTimes.add("20.45");
        appointmentTimes.add("21.00");
        appointmentTimes.add("21.15");
        appointmentTimes.add("21.30");
        appointmentTimes.add("21.45");
        appointmentTimes.add("22.00");
        appointmentTimes.add("22.15");
        appointmentTimes.add("22.30");
        appointmentTimes.add("22.45");
        appointmentTimes.add("23.00");
        appointmentTimes.add("23.15");
        appointmentTimes.add("23.30");
        appointmentTimes.add("23.45");
    }

    private void AddInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddInformationActionPerformed

        //When this button is pressed its check the text value the button and depending on the vaue of the text determines what happens
        // the text value determines if the ade/edit medication,procedures,hospital appointment or surgery appointment panel is to be opened
        String date = String.valueOf(getDateToday());
        if (AddInformation.getText().matches("Add Medication")) {
            //method to add medications name and dose to combobox for prescribing to patient
            addMedicationsComboBox();
            updatePrescriptionButtonQuery.setVisible(false);
            nextMedication.setVisible(false);
            previousMedication.setVisible(false);
            MedicalHistoryPanel.setVisible(false);
            PrescribMedicationPanel.setVisible(true);
            //method to load start years and finish years of prescription into combo box
            loadYears(medicationPrescribedYear, medicationFinishYear);

            //the following sequence occurs if all other if statements, other additional steps completed in ech if will be commented.
        }

        if (AddInformation.getText().matches("Add Procedure")) {
            addProceduresComboBox();
            loadYearsSingle(procedureYear);
            addProcedureButton.setVisible(true);
            showEditOptionsButton.setVisible(true);
            prevProcedureUpdate.setVisible(false);
            nextProcedureUpdate.setVisible(false);
            updateProcedureButton.setVisible(false);
            updatePrescriptionButtonQuery.setVisible(false);
            nextMedication.setVisible(false);
            previousMedication.setVisible(false);
            MedicalHistoryPanel.setVisible(false);
            ProceduresCompletedPanel.setVisible(true);

        }

        if (AddInformation.getText().matches("Add Hospital Appointment")) {

            if (doctor.getHospitalID() == null) {
                JOptionPane.showMessageDialog(null, "You are currently hired by a Surgery and\n"
                        + "can't create apppointment for any hospital or other surgerys bar your own.");
                return;
            }
            hospitalNameComboBox.removeAllItems();
            clinicNameComboBox.removeAllItems();

            clinic.clear();

            // call method to fill appointment (departments name, clinic names)
            appointmentComboList = doctor.doctorsHospitalName(doctor, clinic);
            for (int i = 0; i < appointmentComboList.size(); i++) {
                //booleans used to ensure hospital name and department is added into combo boxes if found through terations
                boolean hospitalDepartment = false;
                boolean hospitalName = false;
                AppointmentHospitalSetter appointment = appointmentComboList.get(i);
                //This loop ensures the hospital name is not inserted on each iteration  of the parent loop and the hospital name will be the same every iteration.
                //if statement to ensure loop does not need to execute again once boolean condition is true
                //Ensures the method executes faster rather than allowing the 2 for loops to run each time.
                if (!hospitalDepartment) {
                    for (int j = 0; j < DepartmentComboBox.getHeight(); j++) {
                        if (appointment.getDepartmentName().matches(String.valueOf(DepartmentComboBox.getItemAt(j)))) {
                            hospitalDepartment = true;
                        }

                    }
                }

                //on first iteration the hospital epartment and name will be inserted into combo box but then will not be inserted again to ensure replications of the same options are not inserted into the combo box
                if (!hospitalName) {
                    for (int j = 0; j < hospitalNameComboBox.getHeight(); j++) {
                        if (appointment.getHospitalName().matches(String.valueOf(hospitalNameComboBox.getItemAt(j)))) {
                            hospitalName = true;
                        }

                    }
                }

                if (hospitalDepartment == false) {
                    DepartmentComboBox.addItem(appointment.getDepartmentName());
                }
                if (hospitalName == false) {
                    hospitalNameComboBox.addItem(appointment.getHospitalName());
                }
                // clinic name is to be added on each iteration ad the number of clinics determines the number of iterations on the loop
                clinicNameComboBox.addItem(appointment.getClinicName());

            }

            //method to load th appoinments years
            loadYear(appointmentYear);

            String appDate = String.valueOf(appointmentDay.getSelectedItem()) + "/" + String.valueOf(appointmentMonth.getSelectedItem()) + "/" + String.valueOf(appointmentYear.getSelectedItem());
            //remove all appointment times in the arraylist
            //refill arraylis with times  from new date for same doctor.
            //set new times into combo box
            appointmentTimeDigit.removeAllItems();
            doctor.getAllDateAppontments(allAppointmentTimes, appDate);
            displayAvailableTimes(appointmentTimeDigit, allAppointmentTimes, appointmentTimes);
            //Ensure correct components show on the appoinment add/edit panel
            AppointmentAddPanel.setVisible(true);
            prevProcedureUpdate.setVisible(false);
            nextProcedureUpdate.setVisible(false);
            updateProcedureButton.setVisible(false);
            updatePrescriptionButtonQuery.setVisible(false);
            nextMedication.setVisible(false);
            previousMedication.setVisible(false);
            MedicalHistoryPanel.setVisible(false);
            ProceduresCompletedPanel.setVisible(false);
            AddNewAppointment.setVisible(true);
            dispalyUpdateComponents.setVisible(true);
            updateExistingAppointment.setVisible(false);
            prevAppointmentButton.setVisible(false);
            nextAppointmentButton.setVisible(false);

        }
        //Same procedure occurs as Hospital Appointments if statment. less complcated
        if (AddInformation.getText().matches("Add Surgery Appointment")) {
            if (doctor.getSurgeryID() == null) {
                JOptionPane.showMessageDialog(null, "You can only make an appointment for yourself \n"
                        + "in the Hospital Department you are employed by");
                return;
            }
            surgeryAppYear.removeAllItems();
            loadYearsSingle(surgeryAppYear);
            String appDate = String.valueOf(surgeryAppDay.getSelectedItem()) + "/" + String.valueOf(surgeryAppMonth.getSelectedItem()) + "/" + String.valueOf(surgeryAppYear.getSelectedItem());

            sAppTImeNumber.removeAllItems();
            doctor.getAllDateAppontments(allAppointmentTimes, appDate);
            displayAvailableTimes(sAppTImeNumber, allAppointmentTimes, appointmentTimes);

            SurgeryAppointmentPanel.setVisible(true);
            MedicalHistoryPanel.setVisible(false);

        }
        //method to split todays date in to 3 seperate strings and load into 3 seperate combo boxes
        splitDate(date);


    }//GEN-LAST:event_AddInformationActionPerformed

    //This method takes a combobox to hold times. an arraylist of times retrieved from the database
    //for a particular date and an arraylist filled with all available appointment times.
    public void displayAvailableTimes(JComboBox box, ArrayList<String> allApps, ArrayList<String> apps) {

        //remove all combo box items
        box.removeAllItems();
        //if no element is arraylist containing existing appointment times means there are no appointments that doctor on the date selected so load all times in
        if (allApps.isEmpty()) {
            for (int i = 0; i < apps.size(); i++) {
                box.addItem(apps.get(i));
            }
            return;
        }

        // loop through database times
        for (int i = 0; i < allApps.size(); i++) {

            //loop all appointment times possible
            for (int j = apps.size() - 1; j >= 0; j--) {
                //if the database time matches to possible time remove the tim prom possible appointment times
                if (apps.get(j).matches(allApps.get(i))) {
                    apps.remove(j);
                    break;
                }
            }
        }
        //after all the appoinment times from the database that are  the same as the possible times are removed from possible appointment times arraylist
        //are taken out we are left with only the times that are not booked.

        // Add the remainder of the dates into the combo box
        for (int j = 0; j < apps.size(); j++) {
            box.addItem(apps.get(j));
        }
        //clear all possible times
        apps.clear();
        // call method that reloads all possible dates back into the arraylist
        loadTimes();

    }

    // This method splits dates a inters them into combo boxes when the medication history page loads in.
    // Ensure all fill with todays date
    public void splitDate(String date) {
        String day = "";
        String month = "";
        String year = "";

        for (int i = 0; i < date.length(); i++) {
            if (i < 4) {
                year = year + date.charAt(i);
            }
            if (i > 4 && i < 7) {
                month = month + date.charAt(i);
            }

            if (i > 7 && i < date.length()) {
                day = day + date.charAt(i);
            }
        }

        procedureMonth.setSelectedItem(month);
        procedureDay.setSelectedItem(day);
        procedureYear.setSelectedItem(year);

        medicationPrescribedMonth.setSelectedItem(month);
        medicationPrescribedYear.setSelectedItem(year);
        medicationPrescribedDay.setSelectedItem(day);

        medicationFinishMonth.setSelectedItem(month);
        medicationFinishYear.setSelectedItem(year);
        medicationFinishDay.setSelectedItem(day);

        appointmentMonth.setSelectedItem(month);
        appointmentYear.setSelectedItem(year);
        appointmentDay.setSelectedItem(day);

        surgeryAppMonth.setSelectedItem(month);
        surgeryAppYear.setSelectedItem(year);
        surgeryAppDay.setSelectedItem(day);
        createMonth.setSelectedItem(month);

        createDay.setSelectedItem(day);
    }

    public String changeforwadDateToHiphen(String date) {

        //This method changes the string date a hiphen format and returned to be parsed. 
        String day = "";
        String month = "";
        String year = "";

        //loop through date lenght. make 3 strings join strings in yyyy-mm-dd format
        for (int i = 0; i < date.length(); i++) {
            if (i < 2) {
                day = day + date.charAt(i);
            }
            if (i > 2 && i < 5) {
                month = month + date.charAt(i);
            }

            if (i > 5 && i < date.length()) {
                year = year + date.charAt(i);
            }
        }
        String newDate = year + "-" + month + "-" + day;
        return newDate;
    }

    public LocalDate getDateToday() {

        LocalDate date = LocalDate.now();
        return date;

    }
    private void medicationFinishMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicationFinishMonthActionPerformed
        //call method to fill prescription month and day finish comboboxes
        fillDateDays(medicationFinishMonth, medicationFinishDay);
    }//GEN-LAST:event_medicationFinishMonthActionPerformed

    //This method ensure that when the days of each month are loaded into the combo box the numbers 1 to 9 have the number 0 put in fornt of them to ensure 
    //the correct number is in combo box when selected. eg 1 now =01 (if not done date  = 1/01/2021 when should be 01/01/2021)
    public void fillDateDays(JComboBox monthBox, JComboBox dayBox) {
        String mont = String.valueOf(monthBox.getSelectedItem());
        int months2 = Integer.parseInt(mont);

        int num = months.get(months2 - 1);

        dayBox.removeAllItems();

        //iterate the first 9 loops adding "0" to the string value of the i+1 and adding full value to combo box.
        //after 9th iteration the string number is added to the combo box as it is.
        for (int i = 0; i < num; i++) {
            if ((i) + 1 < 10) {
                dayBox.addItem(String.valueOf("0" + ((i) + 1)));
            }
            if ((i) + 1 >= 10) {

                dayBox.addItem(String.valueOf((i) + 1));
            }

        }

    }

    //this method loads in two years in one combo boxes and 1 year into another
    public void loadYears(JComboBox startBox, JComboBox finishBox) {

        int year = LocalDate.now().getYear();
        startBox.removeAllItems();
        finishBox.removeAllItems();
        for (int i = year + 1; i > year - 1; i--) {
            if (i == year) {
                startBox.addItem(String.valueOf(year));
            }
            finishBox.addItem(String.valueOf(i));

        }
    }

    //this method loads 2 years into 2 comboboxes
    public void loadYearsSingle(JComboBox startBox) {

        int year = LocalDate.now().getYear();
        startBox.removeAllItems();

        startBox.addItem(String.valueOf(year + 1));
        startBox.addItem(String.valueOf(year));

    }

    // this method loads past 124 years in for date of birth
    public void loadUpdateYears(JComboBox startBox, JComboBox finishBox) {

        int year = LocalDate.now().getYear();

        startBox.removeAllItems();
        finishBox.removeAllItems();
        for (int i = year + 1; i > year - 80; i--) {

            startBox.addItem(String.valueOf(i - 1));
            finishBox.addItem(String.valueOf(i));

        }
    }

    //This method loads 80 years into a combo box for past procedures if procedure information is being updated
    public void loadYear(JComboBox startBox) {

        int year = LocalDate.now().getYear();
        startBox.removeAllItems();

        for (int i = year + 1; i > year - 80; i--) {

            startBox.addItem(String.valueOf(i - 1));

        }
    }

    //This method loads in the patients date of burth years from 2008 to 1900 will adapt as the application adapts.
    public void loadDateOfBirthYears(JComboBox startBox) {

        int year = LocalDate.now().getYear();
        startBox.removeAllItems();

        for (int i = year - 13; i > year - 114; i--) {

            startBox.addItem(String.valueOf(i - 1));

        }
    }


    private void medicationPrescribedMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicationPrescribedMonthActionPerformed
        //call method to load dates into day and month combo boxes
        fillDateDays(medicationPrescribedMonth, medicationPrescribedDay);
    }//GEN-LAST:event_medicationPrescribedMonthActionPerformed

    //this method displays and removes components from the prescription add/edit panel to ensure only correct buttons can be clicked one an update has began.
    private void updatePrescriptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePrescriptionButtonActionPerformed
        //call method to load dates prescribed start and finsih dates
        loadUpdateYears(medicationPrescribedYear, medicationFinishYear);

        nextMedication.setVisible(true);
        previousMedication.setVisible(true);
        updatePrescriptionButtonQuery.setVisible(true);
        updatePrescriptionButton.setVisible(false);
        addPrescriptionButton.setVisible(false);
        //call method to set up updating information. 
        //gets the medication you were update most previous.
        //gets first in arraylist if no updating has occured of the signed in patient
        setMedicationUpdate();

    }//GEN-LAST:event_updatePrescriptionButtonActionPerformed

    //This method is used to update and edit surgery appointments 
    //the passed in value will either consist of "Add" or "Update"
    //This value determines which lines of code are to execute
    public void addEditSurgeryAppointment(String value) {

        String appDate = surgeryAppYear.getSelectedItem() + "-" + surgeryAppMonth.getSelectedItem() + "-" + surgeryAppDay.getSelectedItem();
        String appTime = String.valueOf(sAppTImeNumber.getSelectedItem());
        String appSummary = sAppSummary.getText();

        //if statement to validate if the date entered is in the past as appointments cant be book for the past
        if (value.matches("Add")) {
            if (pastDateValidation(appDate) == true) {
                JOptionPane.showMessageDialog(null, "You cant add an Appointment in the past.\n Todays date or future dates can be inserted to indicate when an Appointment will commence");
                return;

            }
        }

        Appointment appointment = new Appointment();

        //if the value "Add" is passed in, a new appointment is to be created so a new id is created.
        //query to get lowest primary key and decrement it creating a new unique id for the new insert.
        if (value.matches("Add")) {
            String appId = setIDCreate("Appointment");
            String incrementedId = deconstructAndDecrementID(appId);
            appointment.setAppointmentId(incrementedId);
        }

        if (value.matches("Update")) {

            //if the value is update then the Id is collected from the current selected update index of the arraylist
            // the id is used to determine which appointment is to be updated.
            appointment.setAppointmentId(surgeryAppointments.get(surgeryAppointmentUpdateIndex).getAppointmentId());

        }

        //all fieds are set for the appointment to be inserted into the database
        appointment.setPatientId(patient.getPatientID());
        appointment.setAppointmentTime(appTime);
        appointment.setAppointmentSummary(appSummary);
        appointment.setDepartmentId(null);
        appointment.setHospitalId(null);
        appointment.setDepartmentId(null);
        appointment.setAdminStaffId(null);
        appointment.setDoctorId(doctor.getDoctorId());
        appDate = surgeryAppDay.getSelectedItem() + "/" + surgeryAppMonth.getSelectedItem() + "/" + surgeryAppYear.getSelectedItem();
        appointment.setAppointmentDate(appDate);
        appointment.setSurgeryId(doctor.getSurgeryID());

        //if the value is "Add", a mthod to insert the new surgery appointment is called passing the surgeryAppointment object and the patient
        if (value.matches("Add")) {
            doctor.addNewAPpointment(appointment);

            //arraylist is cleared and the database is called to refill the arraylist with all data plus the most recent addition from the databasr
            surgeryAppointments.clear();
            patient.storeSurgeryAppointmets(patient, surgeryAppointments);

        }

        if (value.matches("Update")) {

            doctor.updateExistingSurgeryAppointment(appointment);
            //arraylist is cleared and the database is called to refill the arraylist with all data and new updated information from the database
            surgeryAppointments.clear();
            patient.storeSurgeryAppointmets(patient, surgeryAppointments);

        }

    }

    //The addEditHospitalAppointment method --- addEditProcedure method and prescription method------ All have the same process as the addEditSurgeryAppointment method on line 5039
    public void addEditHospitalAppointment(String value) {

        String appointmentDate = appointmentYear.getSelectedItem() + "-" + appointmentMonth.getSelectedItem() + "-" + appointmentDay.getSelectedItem();
        String appointmentTime = String.valueOf(appointmentTimeDigit.getSelectedItem());
        String appointmentSUmmary = appointmentSummary.getText();

        if (value.matches("Add")) {
            if (pastDateValidation(appointmentDate) == true) {
                JOptionPane.showMessageDialog(null, "You cant add an Appointment in the past.\n Todays date or future dates can be inserted to indicate when a procedure was completed");
                return;

            }
        }
        Appointment appointment = new Appointment();
        appointment.setPatientId(patient.getPatientID());
        appointment.setAppointmentTime(appointmentTime);
        appointment.setAppointmentSummary(appointmentSUmmary);

        if (value.matches("Add")) {
            String appId = setIDCreate("Appointment");
            String incrementedId = deconstructAndDecrementID(appId);
            appointment.setAppointmentId(incrementedId);

        }

        if (value.matches("Update")) {
            appointment.setAppointmentId(hospitalAppointments.get(hospitalAppointmentUpdateIndex).getAppointmentId());
        }

        doctor.getClinicDepartmentId(String.valueOf(clinicNameComboBox.getSelectedItem()), appointment);
        appointment.setHospitalId(doctor.getHospitalID());
        appointment.setAdminStaffId(null);
        appointment.setDoctorId(doctor.getDoctorId());

        appointmentDate = appointmentDay.getSelectedItem() + "/" + appointmentMonth.getSelectedItem() + "/" + appointmentYear.getSelectedItem();
//        System.out.println(appointmentDate +" sdfghjhgfdsdfgh");
        appointment.setAppointmentDate(appointmentDate);
        appointment.setSurgeryId(null);

        if (value.matches("Add")) {
            doctor.addNewAPpointment(appointment);
            hospitalAppointments.clear();
            doctor.storeAppointmets(patient, hospitalAppointments);
        }

        if (value.matches("Update")) {
            doctor.updateExistingHospitalAppointment(appointment);

            hospitalAppointments.clear();
            doctor.storeAppointmets(patient, hospitalAppointments);
        }

    }

    public void addEditProcedure(String value) {

        String procedureDate = procedureYear.getSelectedItem() + "-" + procedureMonth.getSelectedItem() + "-" + procedureDay.getSelectedItem();
        String procdureName = String.valueOf(procedureComboBox.getSelectedItem());
        if (value.matches("Add")) {
            if (pastDateValidation(procedureDate) == true) {
                JOptionPane.showMessageDialog(null, "You cant add a procedure in the past.\n Todays date or future dates can be inserted to indicate when a procedure is to be completed");
                return;

            }
        }

        ProceduresCompleted procedure = new ProceduresCompleted();
        procedure.setProcedureName(procdureName);
        String dateInsert = procedureDay.getSelectedItem() + "/" + procedureMonth.getSelectedItem() + "/" + procedureYear.getSelectedItem();
        procedure.setProcedureCompletedDate(dateInsert);
        procedure.setPatientId(patient.getPatientID());
        procedure.setDoctorId(doctor.getDoctorId());
        procedure.setProcedureCompletedSummary(procedureSummary.getText());
        procedure.setHospitalId(doctor.getHospitalID());

        if (value.matches("Add")) {

            String procedureId = setIDCreate("Procedure");

            String incrementedId = deconstructAndDecrementID(procedureId);

            procedure.setProcedureCompletedId(incrementedId);
        }

        for (int i = 0; i < Allprocedure.size(); i++) {
            if (Allprocedure.get(i).getProcedureName().matches(procdureName)) {

                procedure.setProcedureId(Allprocedure.get(i).getProcedureId());
                break;
            }
        }

        if (value.matches("Update")) {
            int index = 0;
            boolean idFound = false;
            while (idFound == false) {
                if (proceduresCompleted.get(index).getProcedureCompletedId() != null) {
                    if (proceduresCompleted.get(index).getProcedureName().matches(procdureName)) {
                        procedure.setProcedureCompletedId(proceduresCompleted.get(index).getProcedureCompletedId());
                        idFound = true;
                    }
                }

            }

        }

        if (value.matches("Add")) {
            doctor.registerCompletedProcedure(procedure);
            proceduresCompleted.clear();
            patient.storeProcedures(patient, proceduresCompleted);
            return;
        }

        if (value.matches("Update")) {
            doctor.updateExistingProcedure(procedure);
            proceduresCompleted.clear();
            patient.storeProcedures(patient, proceduresCompleted);

        }

    }

    public void prescription(String value) {
        String startDate = medicationPrescribedYear.getSelectedItem() + "-" + medicationPrescribedMonth.getSelectedItem() + "-" + medicationPrescribedDay.getSelectedItem();
        String finishDate = medicationFinishYear.getSelectedItem() + "-" + medicationFinishMonth.getSelectedItem() + "-" + medicationFinishDay.getSelectedItem();

        if (value.matches("Add")) {
            if (pastDateValidation(startDate) == true) {
                JOptionPane.showMessageDialog(null, "You cant add a medication in the past, Only present or future dates will be accepted");
                return;

            }
            if (pastDateValidation(finishDate) == true) {
                JOptionPane.showMessageDialog(null, "You cant finish a medication in the past, Only today or future dates will be accepted");
                return;

            }

        }

        if (twoDateValidation(startDate, finishDate) == true) {
            JOptionPane.showMessageDialog(null, "You cant add a medication to finish before it starts");
            return;
        }

        String medName = "";
        String medDose = "";
        boolean secondStringDetected = false;
        String medNameDose = String.valueOf(medicationComboBox.getSelectedItem());

        MedicationPrescribed medPrescribed = new MedicationPrescribed();
        //A the name and dose needs to be split for validation of the medication to be precribed the the patient.
        //The string is split into one name string and one dose string to ensure the correct name adn dose is input into the object. 
        //This was done to not call to the database ensuring the correct id was selected from the arraylist
        for (int i = 0; i < medNameDose.length(); i++) {

            if (medNameDose.charAt(i) == (',')) {
                secondStringDetected = true;
                i = i + 2;
            }

            if (secondStringDetected == false) {
                medName = medName + medNameDose.charAt(i);
            }

            if (secondStringDetected == true) {
                medDose = medDose + medNameDose.charAt(i);
            }

        }
        medPrescribed.setMedicationName(medName);
        medPrescribed.setMedicationDose(medDose);
        //set database format string values
        String startDateInsert = String.valueOf(medicationPrescribedDay.getSelectedItem() + "/"
                + String.valueOf(medicationPrescribedMonth.getSelectedItem() + "/"
                        + String.valueOf(medicationPrescribedYear.getSelectedItem())));

        String finishDateInsert = String.valueOf(medicationFinishDay.getSelectedItem() + "/"
                + String.valueOf(medicationFinishMonth.getSelectedItem() + "/"
                        + String.valueOf(medicationFinishYear.getSelectedItem())));

        if (value.matches("Add")) {

            //if a new medication is to be prescribed a new id is generated as discussed in the add edit 
            String medId = setIDCreate("Medication");
            String incrementedId = deconstructAndDecrementID(medId);
            medPrescribed.setPrescriptionId(incrementedId);

            //we loop through the medications prescribed to see if that medication name is changed that the user must first be prescribed.
            //only the dates of the medication can be changed.
            for (int i = 0; i < medsPrescribed.size(); i++) {
                String date = changeforwadDateToHiphen(medsPrescribed.get(i).getMedicationDateEnded());
                if (medsPrescribed.get(i).getMedicationName() != null) {

                    if (medsPrescribed.get(i).getMedicationName().matches(medName)
                            && medsPrescribed.get(i).getMedicationDose().matches(medDose)
                            && pastDateValidation(date) == false) {
                        String name = patient.returnPatientFullName();
                        JOptionPane.showMessageDialog(null, name + " is already prescribed this medication currently\n please update this medication instead of trying to add again.");
                        return;
                    }
                }

            }

        }

        if (value.matches("Update")) {

            // if an update is to occure the correct id must be cound. The med name and dose is used to compare to values in the arraylist.
            //the correct id is set as the medication prescribed object id.
            for (int i = 0; i < medsPrescribed.size(); i++) {
                if (medsPrescribed.get(i).getMedicationName() != null) {
                    if (medsPrescribed.get(i).getMedicationName().matches(medName)
                            && medsPrescribed.get(i).getMedicationDose().matches(medDose)) {
                        medPrescribed.setPrescriptionId(medsPrescribed.get(i).getPrescriptionId());
                        break;
                    }
                }

            }
            if (medPrescribed.getMedicationName() == null) {
                JOptionPane.showMessageDialog(null, "" + patient.getUserFirstName() + patient.getUserLastName() + " must be prescribed \nthis medication before you can edit the informatio");
                return;
            }

        }

        medPrescribed.setDoctorId(doctor.getDoctorId());
        medPrescribed.setMedicationDatePrescribed(startDateInsert);
        medPrescribed.setMedicationDateEnded(finishDateInsert);

        medPrescribed.setPatientId(patient.getPatientID());

        for (int i = 0; i < medicationNameDose.size(); i++) {
            if (medicationNameDose.get(i).getMedicationId() != null) {
                System.out.println(medicationNameDose.get(i).getMedicationName() + "  " + medicationNameDose.get(i).getMedicationDose());
                if (medicationNameDose.get(i).getMedicationName().matches(medName)
                        && medicationNameDose.get(i).getMedicationDose().matches(medDose)) {

                    medPrescribed.setMedicationId(medicationNameDose.get(i).getMedicationId());
                    break;
                }
            }
        }

        if (value.matches("Add")) {
            doctor.presctibeMedication(medPrescribed);

        }

        if (value.matches("Update")) {
            doctor.updateExistingPrescription(medPrescribed);
        }

        medsPrescribed.clear();
        patient.storeMedications(patient, medsPrescribed);
    }

    // this method calls the prescrition method to add to the database
    private void addPrescriptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrescriptionButtonActionPerformed
        prescription("Add");


    }//GEN-LAST:event_addPrescriptionButtonActionPerformed

    // this method calls the prescrition method to udate and existing record of the database medication prescribed table
    private void updatePrescriptionButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePrescriptionButtonQueryActionPerformed

        prescription("Update");

        //method to load years into start and end comboboxes
        loadYears(medicationPrescribedYear, medicationFinishYear);
        nextMedication.setVisible(false);
        previousMedication.setVisible(false);
        updatePrescriptionButtonQuery.setVisible(false);
        updatePrescriptionButton.setVisible(true);
        addPrescriptionButton.setVisible(true);

    }//GEN-LAST:event_updatePrescriptionButtonQueryActionPerformed

    private void AddPrescriptionGoBackButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPrescriptionGoBackButtonMouseEntered

        AddPrescriptionGoBackButton.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_AddPrescriptionGoBackButtonMouseEntered

    private void AddPrescriptionGoBackButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPrescriptionGoBackButtonMouseExited
        AddPrescriptionGoBackButton.setBorder(BorderFactory.createLineBorder(blackColor, 0));
    }//GEN-LAST:event_AddPrescriptionGoBackButtonMouseExited

    private void AddPrescriptionGoBackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddPrescriptionGoBackButtonMouseClicked
        //method to displatt the medicl history panel
        displayMedicalHistoryInformation();
        MedicalHistoryPanel.setVisible(true);
        PrescribMedicationPanel.setVisible(false);
    }//GEN-LAST:event_AddPrescriptionGoBackButtonMouseClicked

    private void procedureMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procedureMonthActionPerformed
        fillDateDays(procedureMonth, procedureDay);
    }//GEN-LAST:event_procedureMonthActionPerformed

    private void addProcedureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProcedureButtonActionPerformed
        //method called to add a procedure to the database

        addEditProcedure("Add");
        procedureSummary.setText("");


    }//GEN-LAST:event_addProcedureButtonActionPerformed

    private void showEditOptionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEditOptionsButtonActionPerformed
        //calls a method to fill page components with existing procedure completed information.
        setProcedureUpdate();

        //Add procedure components are removed from the page
        //Edit procedure components are added to the page
        prevProcedureUpdate.setVisible(true);
        nextProcedureUpdate.setVisible(true);

        updateProcedureButton.setVisible(true);
        addProcedureButton.setVisible(false);
        showEditOptionsButton.setVisible(false);


    }//GEN-LAST:event_showEditOptionsButtonActionPerformed

    private void prevProcedureUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevProcedureUpdateMouseClicked

        //check index of stored arraylist ensure not to go out of bounds 
        if (procedureCompletedUpdatIndex == 0) {
            JOptionPane.showMessageDialog(null, "There are no more Procedures");
            return;
        }

        //if not at 0 minuse index number and call method to fll field with arraylist object with the index of the minused number.
        procedureCompletedUpdatIndex--;
        setProcedureUpdate();
    }//GEN-LAST:event_prevProcedureUpdateMouseClicked

    private void nextProcedureUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextProcedureUpdateMouseClicked
        //method to get the next procedure completed in the arraylist
        if (proceduresCompleted.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You have no medication history on this system");
            return;
        }
        //check we are not at the end of the arraylist
        //ensuring not going out of bounds of the arraylist
        if (procedureCompletedUpdatIndex == proceduresCompleted.size() - 1) {
            JOptionPane.showMessageDialog(null, "There are no more Procedures");
            return;
        }
        //if list isnt empty or the index is not at the last position of the arraylist then the index is increments and the next procedure information in the arraylist is displayed
        procedureCompletedUpdatIndex++;
        setProcedureUpdate();
    }//GEN-LAST:event_nextProcedureUpdateMouseClicked

    private void updateProcedureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateProcedureButtonActionPerformed
        //method called updates an existing procedure and removes the update components from the page and add the Add procedure components.
        addEditProcedure("Update");
        updateProcedureButton.setVisible(false);
        addProcedureButton.setVisible(true);
        showEditOptionsButton.setVisible(true);
        prevProcedureUpdate.setVisible(false);
        nextProcedureUpdate.setVisible(false);
        procedureSummary.setText("");

    }//GEN-LAST:event_updateProcedureButtonActionPerformed

    private void previousMedicationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMedicationMouseClicked
        //method to get the next medication prescribed in the arraylist
        if (prescriptionUpdateIndex == 0) {
            JOptionPane.showMessageDialog(null, "There are no more recent Medications");
            return;
        }
        //if not at 0 minuse index number and call method to fll field with arraylist object with the index of the minused number.
        prescriptionUpdateIndex--;
        setMedicationUpdate();
    }//GEN-LAST:event_previousMedicationMouseClicked

    private void nextMedicationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMedicationMouseClicked
        //method to get the next procedure completed in the arraylist
        //check we are not at the end of the arraylist
        //ensuring not going out of bounds of the arraylist
        if (prescriptionUpdateIndex == medsPrescribed.size() - 1) {
            JOptionPane.showMessageDialog(null, "There are no more recent Medications");
            return;
        }
        //if list isnt empty or the index is not at the last position of the arraylist then the index is increments and the next procedure information in the arraylist is displayed.
        prescriptionUpdateIndex++;
        setMedicationUpdate();
    }//GEN-LAST:event_nextMedicationMouseClicked

    private void previousMedicationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMedicationMouseEntered
        previousMedication.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_previousMedicationMouseEntered

    private void nextMedicationMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMedicationMouseEntered
        nextMedication.setBorder(BorderFactory.createLineBorder(whiteColor, 3));
    }//GEN-LAST:event_nextMedicationMouseEntered

    private void previousMedicationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_previousMedicationMouseExited
        previousMedication.setBorder(BorderFactory.createLineBorder(blackColor, 2));
    }//GEN-LAST:event_previousMedicationMouseExited

    private void nextMedicationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMedicationMouseExited
        nextMedication.setBorder(BorderFactory.createLineBorder(blackColor, 2));
    }//GEN-LAST:event_nextMedicationMouseExited

    private void dispalyUpdateComponentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dispalyUpdateComponentsActionPerformed

        // this method displays the update components for the  hospital appointment add/edit panel and removes the add components
        AddNewAppointment.setVisible(false);
        dispalyUpdateComponents.setVisible(false);
        updateExistingAppointment.setVisible(true);
        prevAppointmentButton.setVisible(true);
        nextAppointmentButton.setVisible(true);
        setHospitaAppointmentlUpdate();


    }//GEN-LAST:event_dispalyUpdateComponentsActionPerformed

    private void updateExistingAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateExistingAppointmentActionPerformed

        //method is called to update an existing hospital appointment
        addEditHospitalAppointment("Update");
        //method called to fill the appoiontment times available on the date when the add/edit panel is generated
        setUpHospitalAppointmentTimes();

        //removes all edit appointment components and add Add appointment components
        AddNewAppointment.setVisible(true);
        dispalyUpdateComponents.setVisible(true);
        updateExistingAppointment.setVisible(false);
        prevAppointmentButton.setVisible(false);
        nextAppointmentButton.setVisible(false);
        appointmentSummary.setText("");

    }//GEN-LAST:event_updateExistingAppointmentActionPerformed

    private void AddNewAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddNewAppointmentActionPerformed

        //method called to add a hospital appointment to the database
        addEditHospitalAppointment("Add");
        //After added the time must be removed from the combobox
        setUpHospitalAppointmentTimes();
        appointmentSummary.setText("");
    }//GEN-LAST:event_AddNewAppointmentActionPerformed

    private void appointmentMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentMonthActionPerformed
        //method called to reload ties into the days of the combobox and the set the available times for the date selected.
        setUpHospitalAppointmentTimes();
        fillDateDays(appointmentMonth, appointmentDay);
    }//GEN-LAST:event_appointmentMonthActionPerformed

    private void prevAppointmentButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevAppointmentButtonMouseClicked

        //method to get the previous appointments in the arraylist.
        //validation to ensure that the index of the arraylist does not go out of bounds
        if (hospitalAppointmentUpdateIndex == 0) {
            JOptionPane.showMessageDialog(null, "There are no previous Appointments");
            return;
        }

        // if not going to go out of bounds the decrement the value and display the hospital information of new indexed arraylist appointment object
        hospitalAppointmentUpdateIndex--;
        setHospitaAppointmentlUpdate();
    }//GEN-LAST:event_prevAppointmentButtonMouseClicked

    private void nextAppointmentButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextAppointmentButtonMouseClicked
        //method to get the next hospital appointment in the arraylist.
        //validate before incrementing the arraylist index to ensure index will not go out of bounds
        if (hospitalAppointmentUpdateIndex == hospitalAppointments.size() - 1) {
            JOptionPane.showMessageDialog(null, "There are no more Appointments");
            return;
        }

        //increments the arrayist index
        //Displat the new appointment information
        hospitalAppointmentUpdateIndex++;
        setHospitaAppointmentlUpdate();
    }//GEN-LAST:event_nextAppointmentButtonMouseClicked

    private void createMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createMonthActionPerformed
        fillDateDays(createMonth, createDay);
    }//GEN-LAST:event_createMonthActionPerformed

    private void createAccountBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountBtnMouseEntered
        createAccountBtn.setForeground(blackColor);
        createAccountBtn.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_createAccountBtnMouseEntered

    private void createAccountBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountBtnMouseExited
        createAccountBtn.setForeground(whiteColor);
        createAccountBtn.setBorder(BorderFactory.createLineBorder(whiteColor, 2));
    }//GEN-LAST:event_createAccountBtnMouseExited

    private void createClearButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createClearButtonMouseEntered

        createClearButton.setForeground(blackColor);
        createClearButton.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_createClearButtonMouseEntered

    private void createClearButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createClearButtonMouseExited
        createClearButton.setForeground(whiteColor);
        createClearButton.setBorder(BorderFactory.createLineBorder(whiteColor, 2));
    }//GEN-LAST:event_createClearButtonMouseExited

    private void surgeryAppMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surgeryAppMonthActionPerformed
        fillDateDays(surgeryAppMonth, surgeryAppDay);
        setUpSurgeryAppointmentTimes();
    }//GEN-LAST:event_surgeryAppMonthActionPerformed

    private void addSAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSAppointmentButtonActionPerformed
        addEditSurgeryAppointment("Add");
        setUpSurgeryAppointmentTimes();
        sAppSummary.setText("");
    }//GEN-LAST:event_addSAppointmentButtonActionPerformed

    public void setUpSurgeryAppointmentTimes() {
        String appDate = String.valueOf(surgeryAppDay.getSelectedItem()) + "/" + String.valueOf(surgeryAppMonth.getSelectedItem()) + "/" + String.valueOf(surgeryAppYear.getSelectedItem());
        doctor.getAllDateAppontments(allAppointmentTimes, appDate);
        displayAvailableTimes(sAppTImeNumber, allAppointmentTimes, appointmentTimes);
    }
    private void updateSAppointmentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSAppointmentButtonActionPerformed
        addEditSurgeryAppointment("Update");
        setUpSurgeryAppointmentTimes();
        addSAppointmentButton.setVisible(true);
        showSAppUpdateComponents.setVisible(true);
        updateSAppointmentButton.setVisible(false);
        nextSAppointment.setVisible(false);
        prevSAppointment.setVisible(false);
        sAppSummary.setText("");
    }//GEN-LAST:event_updateSAppointmentButtonActionPerformed

    private void showSAppUpdateComponentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSAppUpdateComponentsActionPerformed

        setSurgeryAppointmentsUpdate();
        addSAppointmentButton.setVisible(false);
        showSAppUpdateComponents.setVisible(false);
        updateSAppointmentButton.setVisible(true);
        nextSAppointment.setVisible(true);
        prevSAppointment.setVisible(true);
    }//GEN-LAST:event_showSAppUpdateComponentsActionPerformed

    private void nextSAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextSAppointmentMouseClicked
        if (surgeryAppointmentUpdateIndex == surgeryAppointments.size() - 1) {
            JOptionPane.showMessageDialog(null, "There are no more Appointments");
            return;
        }
        surgeryAppointmentUpdateIndex++;
        setSurgeryAppointmentsUpdate();
    }//GEN-LAST:event_nextSAppointmentMouseClicked

    private void prevSAppointmentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prevSAppointmentMouseClicked
        if (surgeryAppointmentUpdateIndex == 0) {
            JOptionPane.showMessageDialog(null, "There are no more Appointments");
            return;
        }
        surgeryAppointmentUpdateIndex--;
        setSurgeryAppointmentsUpdate();
    }//GEN-LAST:event_prevSAppointmentMouseClicked

    private void appointmentTimeDigitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentTimeDigitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_appointmentTimeDigitActionPerformed

    private void UpdateActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActivityButtonActionPerformed

        // This method validates the selected option from the combo box and unsures if the option is chosed that the correct validation occurs for the input update field.
        //Validation to ensure the update text field is not submitted empty before calling the database
        //Regex was used to validate the user input and combo box option on most fields. Validation can be found in the user class
        //if the user inputs an invalid input then they will be effectively informed using JOption panes
        if (UpdateOptionBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Please Select Update Option");
            OptionTextUpdateTf.setText("");
            return;
        }
        if (emptyInput(OptionTextUpdateTf.getText()) == false) {
            JOptionPane.showMessageDialog(null, "Sorry you cant update with an empty value");
            return;
        }
        String updateField = validatUpdateField(String.valueOf(UpdateOptionBox.getSelectedItem()));
        System.out.println(updateField);

        switch (updateField) {
            case "EmergencyNumber":
                if (patient.validateEmergencyNumber(OptionTextUpdateTf.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Enter a valid Irish mobile, starting with '+353' or '0' and \nthe prefix '83', '85', '86', '87', or '89'. \nNo spaces/dashes.");
                    OptionTextUpdateTf.setText("");
                    return;
                }
                patient.changeData(OptionTextUpdateTf.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Emergency Number Has Been Updated.");
                displayUserData(connectToDisplay(patient));
                OptionTextUpdateTf.setText("");
                return;
            case "BloodType":
                if (patient.validateBloodType(OptionTextUpdateTf.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid blood type. Use A, B, AB, O with +/-");
                    OptionTextUpdateTf.setText("");
                    return;
                }
                patient.changeData(OptionTextUpdateTf.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Blood Type Has Been Updated.");
                displayUserData(connectToDisplay(patient));
                OptionTextUpdateTf.setText("");
                return;
            case "Smoker":
                if (patient.validateSmoke(OptionTextUpdateTf.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Use 'never', 'current #', or 'former #'");
                    OptionTextUpdateTf.setText("");
                    return;
                }
                patient.changeData(OptionTextUpdateTf.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Smoke Condition Has Been Updated.");
                displayUserData(connectToDisplay(patient));
                OptionTextUpdateTf.setText("");
                return;
            case "WeeklyActivity":
                if (patient.validateWeeklyActivity(OptionTextUpdateTf.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid input\n Format: 'Activity Level' followed by Number of Hours \n(e.g., 'light 10 hours', vigorous 5 hours', 'moderate 8 hous','sedentary 6 hours)");
                    OptionTextUpdateTf.setText("");
                    return;
                }
                patient.changeData(OptionTextUpdateTf.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Weekly Activity Has Been Updated.");
                displayUserData(connectToDisplay(patient));
                OptionTextUpdateTf.setText("");
                return;
            case "Occupation":
                if (patient.validateOccupation(OptionTextUpdateTf.getText()) == false) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Use only letters - no numbers or symbols allowed.");
                    OptionTextUpdateTf.setText("");
                    return;
                }
                patient.changeData(OptionTextUpdateTf.getText(), updateField);
                JOptionPane.showMessageDialog(null, "Your Occupation Has Been Updated.");
                displayUserData(connectToDisplay(patient));
                OptionTextUpdateTf.setText("");
                return;
        }

    }//GEN-LAST:event_UpdateActivityButtonActionPerformed

    private void sAppTImeNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sAppTImeNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sAppTImeNumberActionPerformed

    public void setUpHospitalAppointmentTimes() {
        String appDate = String.valueOf(appointmentDay.getSelectedItem()) + "/" + String.valueOf(appointmentMonth.getSelectedItem()) + "/" + String.valueOf(appointmentYear.getSelectedItem());
        doctor.getAllDateAppontments(allAppointmentTimes, appDate);
        displayAvailableTimes(appointmentTimeDigit, allAppointmentTimes, appointmentTimes);
    }


    private void appointmentDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentDayActionPerformed
        setUpHospitalAppointmentTimes();
    }//GEN-LAST:event_appointmentDayActionPerformed

    private void surgeryAppDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_surgeryAppDayActionPerformed
        //When the day of an appointment is selected the entire date is selected and checked to see if any appointments exist on a particular datae
        //All appointment times are collected for the date for the doctor signed in. 
        //The filled arraylist of existing appointment times are compared to possible times of appointments.
        //If the time of one arralist is also present, the hardcoded times arrayllist will remove all appointments matching.
        //Thus we are left with only avalable times to be populated in the combo box
        //I think this explaination will also suffice for the (setHospitalAppointmentTimes() --line 5738 )
        String appDate = String.valueOf(surgeryAppDay.getSelectedItem()) + "/" + String.valueOf(surgeryAppMonth.getSelectedItem()) + "/" + String.valueOf(surgeryAppYear.getSelectedItem());
        doctor.getAllDateAppontments(allAppointmentTimes, appDate);
        displayAvailableTimes(sAppTImeNumber, allAppointmentTimes, appointmentTimes);
    }//GEN-LAST:event_surgeryAppDayActionPerformed

    private void signPatientInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signPatientInActionPerformed
        //This method is to be used if a qr code cant be scannned. User email is accepted 
        if (signInPatientEmail.getText().matches("")) {
            JOptionPane.showMessageDialog(null, "Please enter an email value into the text field");
            return;
        }

        if (patient.validateEmail(signInPatientEmail.getText()) == false) {
            JOptionPane.showMessageDialog(null, "The email submitted was not of email address format.\nPlease insert a valid email.");
            return;
        }

        patient.setUserEmail(signInPatientEmail.getText());
        displayUserData(connectToDisplay(patient));
        if (!patient.getUserFirstName().equals("")) {

            webcam.close();
            PersonalInformationPanel.setVisible(true);
            QRScannerPanel.setVisible(false);
            signInPatientEmail.setText("");
            return;

        }

        JOptionPane.showMessageDialog(null, "The submitted email is not a registered patients email.");


    }//GEN-LAST:event_signPatientInActionPerformed

    private void appointmentYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_appointmentYearActionPerformed
        setUpHospitalAppointmentTimes();
    }//GEN-LAST:event_appointmentYearActionPerformed

    private void appointmentGoBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentGoBackMouseEntered
        appointmentGoBack.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_appointmentGoBackMouseEntered

    private void appointmentGoBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentGoBackMouseExited
        appointmentGoBack.setBorder(BorderFactory.createLineBorder(blackColor, 0));
    }//GEN-LAST:event_appointmentGoBackMouseExited

    private void appointmentGoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_appointmentGoBackMouseClicked
        //It calls the method to dispay medical history page
        displayMedicalHistoryInformation();
        AppointmentAddPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(true);
    }//GEN-LAST:event_appointmentGoBackMouseClicked

    private void suregerAppGoBackMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suregerAppGoBackMouseEntered
        suregerAppGoBack.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_suregerAppGoBackMouseEntered

    private void suregerAppGoBackMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suregerAppGoBackMouseExited
        suregerAppGoBack.setBorder(BorderFactory.createLineBorder(blackColor, 0));
    }//GEN-LAST:event_suregerAppGoBackMouseExited

    private void suregerAppGoBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suregerAppGoBackMouseClicked
        //This methd brings the user back to the Medical History page when the go back button is pressed on the Surgery Appointment Add/edit panel. 
        displayMedicalHistoryInformation();
        SurgeryAppointmentPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(true);
    }//GEN-LAST:event_suregerAppGoBackMouseClicked

    private void goBackProceduresPageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackProceduresPageMouseEntered
        goBackProceduresPage.setBorder(BorderFactory.createLineBorder(blackColor, 3));
    }//GEN-LAST:event_goBackProceduresPageMouseEntered

    private void goBackProceduresPageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackProceduresPageMouseExited
        goBackProceduresPage.setBorder(BorderFactory.createLineBorder(blackColor, 0));
    }//GEN-LAST:event_goBackProceduresPageMouseExited

    private void goBackProceduresPageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_goBackProceduresPageMouseClicked
        displayMedicalHistoryInformation();
        ProceduresCompletedPanel.setVisible(false);
        MedicalHistoryPanel.setVisible(true);
    }//GEN-LAST:event_goBackProceduresPageMouseClicked

    private void QRScannerGoBackButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QRScannerGoBackButtonMouseClicked
        if (webcam != null) {
            webcam.close();
        }
        QRScannerPanel.setVisible(false);
        UserLoggedInPanel.setVisible(true);

    }//GEN-LAST:event_QRScannerGoBackButtonMouseClicked

    //This methd calls the method in hospital user calss to fill the medications Arraylist 
    public void addMedicationsComboBox() {
        medicationNameDose.clear();
        doctor.retrieveMedications(medicationNameDose);
        addMedicationsToComboBox();
    }

    //This methd calls the method in hospital user calss to fill the procedures Arraylist 
    public void addProceduresComboBox() {
        Allprocedure.clear();
        doctor.retrieveProcedures(Allprocedure);
        addProceduresToComboBox();
    }

    //Allm edications in the database are retrieved and inserted into an arraylist 
    //The arraylist medication name and dose is insrted into the combo box this will ensure the correct medication is prescribed to the patient and less inputs needed to be used by the doctor
    public void addMedicationsToComboBox() {
        medicationComboBox.removeAllItems();
        for (int i = 0; i < medicationNameDose.size(); i++) {

            medicationComboBox.addItem(medicationNameDose.get(i).getMedicationName() + ", " + medicationNameDose.get(i).getMedicationDose());

        }
    }

    //This method fills the add procedure combobox with all available procedures in the hospital.
    // A query extract all procedure names, loads into array list and then loaded in to combobox
    //This could be fixed to query only procedures allowed by the signed in doctor but more testing data is neede and time is currently running short. 
    public void addProceduresToComboBox() {
        procedureComboBox.removeAllItems();
        for (int i = 0; i < Allprocedure.size(); i++) {

            procedureComboBox.addItem(Allprocedure.get(i).getProcedureName());

        }
    }

    //This method fills the appointment panel with the correct fields of index of the hospitalAppointment Arraylist (filled from database)
    public void fillAppointmentPanel() {
        medicationNameOutput.setText(hospitalAppointments.get(hospitalAppointmentNumber).getHospitalName());
        medicationBrandOutput.setText(hospitalAppointments.get(hospitalAppointmentNumber).getHospitalPhoneNumber());

        medicationPrescribedOutput.setText(hospitalAppointments.get(hospitalAppointmentNumber).getHospitalPhoneNumber());

        medicationFinishDateOutput.setText(hospitalAppointments.get(hospitalAppointmentNumber).getDoctorFirstName() + " "
                + hospitalAppointments.get(hospitalAppointmentNumber).getDoctorLastName());

        medicationDoctorNameOutput.setText(hospitalAppointments.get(hospitalAppointmentNumber).getAppointmentDate() + " @ " + hospitalAppointments.get(hospitalAppointmentNumber).getAppointmentTime());

        medicationSideEffectsOutput.setText("");
        medicationSideEffectsOutput.append(hospitalAppointments.get(hospitalAppointmentNumber).getAppointmentSummary());
    }

    //Lines 5782 - 5809 are methods to ensure the correct label values are set when certain information is displayed in fields
    //The method names explain the functunality of the functions.
    public void setAppointmentLabels() {
        medicationNameLabel.setText("Hospital Name");
        MedicationBrandLabel.setText("Hospital  Phone Number");
        MedicationDatePrescribedLabel.setText(" Phone Number");
        medicationDateEndOutput.setText("Doctor Name");
        medicationDoctorNameLabel.setText("Appointment Date");
        medicationSideEffectsLabel.setText("Appointment Summary");
    }

    public void setProcedureLabels() {

        medicationNameLabel.setText("Procedure Name");
        MedicationBrandLabel.setText("Hospital  Name");
        MedicationDatePrescribedLabel.setText("Doctor Name");
        medicationDateEndOutput.setText("Procedure Date");
        medicationDoctorNameLabel.setText("Risk Level of Procedure");
    }

    public void setMedicationLabels() {
        medicationNameLabel.setText("Medication Name");
        MedicationBrandLabel.setText("Hospital  Brand");
        MedicationDatePrescribedLabel.setText("Date Prescribed");
        medicationDateEndOutput.setText("Medication Date Finished");
        medicationDoctorNameLabel.setText("Doctor Name");
        medicationSideEffectsLabel.setText("Additional Information");

    }

    public void setSurgeryAppointmentsUpdate() {
        //This method sets up the update prescription components of the Surgery Apointment add/ edit panel.
        //Selects fields for the index in the SurgeryApointment arraylist (arraylist filled with patients completed Surgery Apointments retrieved from database)
        String date = surgeryAppointments.get(surgeryAppointmentUpdateIndex).getAppointmentDate();
        String day = "";
        String month = "";
        String year = "";
        for (int i = 0; i < date.length(); i++) {

            if (i < 2) {
                day = day + date.charAt(i);
            }
            if (i > 2 && i < 5) {
                month = month + date.charAt(i);
            }

            if (i > 5 && i < date.length()) {
                year = year + date.charAt(i);
            }

        }
        surgeryAppMonth.setSelectedItem(month);
        surgeryAppDay.setSelectedItem(day);
        surgeryAppYear.setSelectedItem(year);

        //The time of this appointment will be removed from the combobox because the implementation of getting rid of unavailable appoinments to stop over booking.
        //As a result the time of the appointment being edited must be taken and inserted into the combo box and then making it the selected value.
        //This ensures that the updated time will not override
        sAppTImeNumber.addItem(surgeryAppointments.get(surgeryAppointmentUpdateIndex).getAppointmentTime());
        sAppTImeNumber.setSelectedItem(surgeryAppointments.get(surgeryAppointmentUpdateIndex).getAppointmentTime());

        sAppSummary.setText(surgeryAppointments.get(surgeryAppointmentUpdateIndex).getAppointmentSummary());

    }

    public void setHospitaAppointmentlUpdate() {
        //This method sets up the update prescription components of the Hospital Apointment add/ edit panel.
        //Selects fields for the index in the HospitalApointment arraylist (arraylist filled with patients completed Hospital Apointments retrieved from database)
        String date = hospitalAppointments.get(hospitalAppointmentUpdateIndex).getAppointmentDate();
        String day = "";
        String month = "";
        String year = "";
        for (int i = 0; i < date.length(); i++) {

            if (i < 2) {
                day = day + date.charAt(i);
            }
            if (i > 2 && i < 5) {
                month = month + date.charAt(i);
            }

            if (i > 5 && i < date.length()) {
                year = year + date.charAt(i);
            }

        }

        //loop to terate through the date, splitting it into 3 different strings and make 3 different comboboxed the selected values of the day,month and year string values.
        appointmentMonth.setSelectedItem(month);
        appointmentDay.setSelectedItem(day);
        appointmentYear.setSelectedItem(year);

        //The time of this appointment will be removed from the combobox because the implementation of getting rid of unavailable appoinments to stop over booking.
        //As a result the time of the appointment being edited must be taken and inserted into the combo box and then making it the selected value.
        //This ensures that the updated time will not override
        appointmentTimeDigit.addItem(hospitalAppointments.get(hospitalAppointmentUpdateIndex).getAppointmentTime());
        appointmentTimeDigit.setSelectedItem(hospitalAppointments.get(hospitalAppointmentUpdateIndex).getAppointmentTime());
        appointmentSummary.setText(hospitalAppointments.get(hospitalAppointmentUpdateIndex).getAppointmentSummary());
        clinicNameComboBox.setSelectedItem(hospitalAppointments.get(hospitalAppointmentUpdateIndex).getClinicName());

    }

    public void setProcedureUpdate() {
        //This method sets up the update prescription components of the procedures add/ edit panel.
        //Selects fields for the index in the procedureCompeted arraylist (arraylist filled with patients completed procedures take from database)

        String date = proceduresCompleted.get(procedureCompletedUpdatIndex).getProcedureCompletedDate();
        procedureComboBox.setSelectedItem(proceduresCompleted.get(procedureCompletedUpdatIndex).getProcedureName());
        String day = "";
        String month = "";
        String year = "";
        for (int i = 0; i < date.length(); i++) {

            if (i < 2) {
                day = day + date.charAt(i);
            }
            if (i > 2 && i < 5) {
                month = month + date.charAt(i);
            }

            if (i > 5 && i < date.length()) {
                year = year + date.charAt(i);
            }

        }

        procedureMonth.setSelectedItem(month);
        procedureDay.setSelectedItem(day);
        procedureYear.setSelectedItem(year);

        procedureSummary.setText("");
        procedureSummary.append("\n" + proceduresCompleted.get(procedureCompletedUpdatIndex).getProcedureCompletedSummary());
    }

    public void setMedicationUpdate() {
        //This method sets up the update prescription components of the prescription add/ edit panel.
        //Selects fields for the index in the medsPrescribed arraylist (arraylist filled with patients prescriptions take from database)
        String startDate = medsPrescribed.get(prescriptionUpdateIndex).getMedicationDatePrescribed();
        String endDate = medsPrescribed.get(prescriptionUpdateIndex).getMedicationDateEnded();

        medicationComboBox.setSelectedItem(medsPrescribed.get(prescriptionUpdateIndex).getMedicationName() + ", " + medsPrescribed.get(prescriptionUpdateIndex).getMedicationDose());
        String sday = "";
        String smonth = "";
        String syear = "";
        String fday = "";
        String fmonth = "";
        String fyear = "";
        //loop to terate through the date, splitting it into 3 different strings and make 3 different comboboxed the selected values of the day,month and year string values.
        for (int i = 0; i < startDate.length(); i++) {

            if (i < 2) {
                sday = sday + startDate.charAt(i);
                fday = fday + endDate.charAt(i);
            }
            if (i > 2 && i < 5) {
                smonth = smonth + startDate.charAt(i);
                fmonth = fmonth + endDate.charAt(i);
            }

            if (i > 5 && i < startDate.length()) {
                syear = syear + startDate.charAt(i);
                fyear = fyear + endDate.charAt(i);
            }

        }

        //finish date and start date is inserted into comboboxes
        medicationPrescribedMonth.setSelectedItem(smonth);
        medicationPrescribedDay.setSelectedItem(sday);
        medicationPrescribedYear.setSelectedItem(syear);

        medicationFinishMonth.setSelectedItem(fmonth);
        medicationFinishDay.setSelectedItem(fday);
        medicationFinishYear.setSelectedItem(fyear);

    }

    //This method sets the procedure panel up and loads the fields into the correct components
    public void setProcedurePanel() {
        medicationNameOutput.setText(proceduresCompleted.get(proceduresCompletedNumber).getProcedureName());
        medicationBrandOutput.setText(proceduresCompleted.get(proceduresCompletedNumber).getHopspitalName());

        medicationPrescribedOutput.setText(proceduresCompleted.get(proceduresCompletedNumber).getDoctorFirstName() + " "
                + proceduresCompleted.get(proceduresCompletedNumber).getDoctorLastName());

        medicationFinishDateOutput.setText(proceduresCompleted.get(proceduresCompletedNumber).getProcedureCompletedDate());
        medicationDoctorNameOutput.setText(proceduresCompleted.get(proceduresCompletedNumber).getProcedureRiskLevel());

        medicationSideEffectsOutput.setText("");
        medicationSideEffectsOutput.append(proceduresCompleted.get(proceduresCompletedNumber).getProcedureCompletedSummary()
                + "\n\nProcedure Description\n\n" + proceduresCompleted.get(proceduresCompletedNumber).getProcedureDescription());
    }

    //this method loads the days of the months of the year into an arraylist
    //when a month is selected in the combo box the values are refereced
    public void loadData() {
        months.clear();
        months.add(31);
        months.add(28);
        months.add(31);
        months.add(30);
        months.add(31);
        months.add(30);
        months.add(31);
        months.add(31);
        months.add(30);
        months.add(31);
        months.add(30);
        months.add(31);

    }

    public int getAge(String date) {

        //this method subtracts the current dates year from the date of birth year to find out the age of the person
        //The below link was used to get the correct fuction name to get the years between two dates
        //the string date is parsed into a LocalDate object
        //the years difference from today and the date of birth is calculated and returned with year value
        // https://www.tutorialspoint.com/localdate-getyear-method-in-java#:~:text=The%20year%20for%20a%20particular,range%20from%20MIN_YEAR%20to%20MAX_YEAR.
        LocalDate birthDate = changeDate(date);

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthDate, currentDate);

        int age = period.getYears();
        System.out.println("Age in years: " + age);

        return age;

    }

    public LocalDate changeDate(String date) {

        //This method converts the string date into a date object after validated and returns it
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(date, formatter);
        return birthDate;
    }

    public boolean pastDateValidation(String date) {

        // This method checks if the date of birth entered is in the future of past
        // This isBefore function was found on :     https://geeksforgeeks.org/localdate-isbefore-method-in-java-with-examples/
        System.out.println(date);
        LocalDate dob = changeDate(date);
        LocalDate currentDate = LocalDate.now();

        return dob.isBefore(currentDate);
    }

    public boolean twoDateValidation(String beforeDate, String afterDate) {

        // This method checks if the date of birth entered is in the future of past
        // This isBefore function was found on :     https://geeksforgeeks.org/localdate-isbefore-method-in-java-with-examples/
        LocalDate date1 = changeDate(beforeDate);
        LocalDate date2 = changeDate(afterDate);
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date2.isBefore(date1));

        return date2.isBefore(date1);
    }

    public String deconstructAndDecrementID(String id) {

        StringBuffer idpart1 = new StringBuffer();
        StringBuffer idPart2 = new StringBuffer();

        System.out.println("howiya" + id);
        for (int i = 0; i < id.length(); i++) {
            if (i < 3) {
                idpart1.append(id.charAt(i));
            }

            if (i > 2) {
                idPart2.append(id.charAt(i));
            }
        }
        int idPart2Plus = Integer.parseInt(String.valueOf(idPart2));
        idPart2Plus--;
        String newId = String.valueOf(idpart1) + String.valueOf(idPart2Plus);
        System.out.print(newId);
        return newId;

    }

    //This method is used to get the lowest id of a table and return it
    //Te id will be decremented and used as a new inserts Primary Key
    public String setIDCreate(String value) {
        try {
            String query = "";
            String q1 = "select  PatientId FROM Patient ORDER BY PatientId asc LIMIT 1;";
            String q2 = "select  DoctorId FROM Doctor ORDER BY DoctorId asc LIMIT 1;";
            String q3 = "select  PatientNumber FROM Patient ORDER BY PatientNumber desc LIMIT 1;";
            String q4 = "select  HospitalId FROM Hospital ORDER BY HospitalId asc LIMIT 1;";
            String q5 = "select  UserId FROM Users ORDER BY UserId asc LIMIT 1;";
            String q6 = "select  PrescriptionId FROM MedicationPrescribed ORDER BY PrescriptionId asc LIMIT 1;";
            String q7 = "select  ProcedureCompletedId FROM ProceduresCompleted ORDER BY ProcedureCompletedId asc LIMIT 1;";
            String q8 = "select  AppointmentId FROM Appointment ORDER BY AppointmentId asc LIMIT 1;";

            // here sakila is database name, root is username and password.... password is 
            // set as a variable above. 
            if (value.matches("Patient")) {
                query = q1;
            }
            if (value.matches("Doctor")) {
                query = q2;
            }

            if (value.matches("User")) {
                query = q5;
            }

            if (value.matches("Medication")) {
                query = q6;
            }

            if (value.matches("Procedure")) {
                query = q7;
            }

            if (value.matches("Appointment")) {
                query = q8;
            }

            if (value.matches("Patient Number")) {
                query = q3;
            }

            String id = "";

            Statement stmt = patient.getMyConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // while loop to iterate through all records of customer table and display them 
            // in the text field 

            while (rs.next()) {

                id = rs.getString(1);

            }

            return id;

        } catch (SQLException ex) {
            System.out.println(ex + "\n");
        } catch (Exception ex) {
            System.out.println(ex + "\n");
        }
        return "";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SaveCareGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaveCareGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaveCareGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaveCareGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaveCareGUI().setVisible(true);
            }
        });
    }

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public ArrayList<Integer> getMonths() {
        return months;
    }

    public void setMonths(ArrayList<Integer> months) {
        this.months = months;
    }

    public Webcam getWebcam() {
        return webcam;
    }

    public void setWebcam(Webcam webcam) {
        this.webcam = webcam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ArrayList<String> getPatientUpdateFields() {
        return patientUpdateFields;
    }

    public void setPatientUpdateFields(ArrayList<String> patientUpdateFields) {
        this.patientUpdateFields = patientUpdateFields;
    }

    public ArrayList<String> getAppointmentTimes() {
        return appointmentTimes;
    }

    public void setAppointmentTimes(ArrayList<String> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    public ArrayList<Appointment> getSurgeryAppointments() {
        return surgeryAppointments;
    }

    public void setSurgeryAppointments(ArrayList<Appointment> surgeryAppointments) {
        this.surgeryAppointments = surgeryAppointments;
    }

    public JComboBox<String> getMedicationComboBox() {
        return medicationComboBox;
    }

    public void setMedicationComboBox(JComboBox<String> medicationComboBox) {
        this.medicationComboBox = medicationComboBox;
    }

    public ArrayList<Medication> getMedicationNameDose() {
        return medicationNameDose;
    }

    public void setMedicationNameDose(ArrayList<Medication> medicationNameDose) {
        this.medicationNameDose = medicationNameDose;
    }

    public ArrayList<Procedures> getAllprocedure() {
        return Allprocedure;
    }

    public void setAllprocedure(ArrayList<Procedures> Allprocedure) {
        this.Allprocedure = Allprocedure;
    }

    public ArrayList<Clinic> getClinic() {
        return clinic;
    }

    public void setClinic(ArrayList<Clinic> clinic) {
        this.clinic = clinic;
    }
    
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel AboutUsPanel;
    private javax.swing.JButton AddInformation;
    private javax.swing.JButton AddNewAppointment;
    private javax.swing.JLabel AddPrescriptionGoBackButton;
    private javax.swing.JLabel AddPrescriptionMedNameLabel;
    private javax.swing.JLabel AddressLbl;
    private javax.swing.JLabel AddressLbl2;
    private java.awt.Panel ApplicationInterfacePanel;
    private java.awt.Panel AppointmentAddPanel;
    private javax.swing.JPanel BenefitBox1;
    private javax.swing.JPanel BenefitBox2;
    private javax.swing.JPanel BenefitBox3;
    private javax.swing.JPanel BenefitBox4;
    private javax.swing.JLabel BenefitsHeading;
    private java.awt.Panel BenefitsPanel;
    private javax.swing.JLabel ContactUsLabel;
    private java.awt.Panel ContactUsPanel;
    private java.awt.Panel CreateAccountPanel;
    private javax.swing.JTextField CreateHospitalID;
    private javax.swing.JComboBox<String> CreateUserOption;
    private javax.swing.JLabel CurrentMedicationHeading;
    private javax.swing.JLabel CustomerNumerLbl;
    private javax.swing.JLabel CustomerNumerLbl1;
    private javax.swing.JComboBox<String> DepartmentComboBox;
    private javax.swing.JLabel EircodeLbl;
    private javax.swing.JLabel EmailLbl;
    private javax.swing.JLabel GeneratedQRBackground;
    private javax.swing.JLabel GeneratedQRCodeContainer;
    private java.awt.Panel GeneratedQRCodePanel;
    private javax.swing.JLabel GeneratedQRLabel;
    private javax.swing.JLabel HomeHeading;
    private java.awt.Panel HomeMenu;
    private java.awt.Panel HomePagePanel;
    private java.awt.Panel LifeStylePanel;
    private java.awt.Panel MainMenuPanel;
    private java.awt.Panel MedicalHistoryPanel;
    private javax.swing.JLabel MedicationBrandLabel;
    private javax.swing.JLabel MedicationDatePrescribedLabel;
    private javax.swing.JComboBox<String> MedicationInfoOptions;
    private javax.swing.JLabel MedicationTietableIcon;
    private java.awt.Panel MenuContainer;
    private javax.swing.JLabel MobileNumberDisplay;
    private javax.swing.JLabel NationalityLabel;
    private javax.swing.JTextField OptionTextUpdateTf;
    private javax.swing.JLabel PPSDisplay;
    private javax.swing.JLabel ParagraphAboutUs1;
    private javax.swing.JLabel ParagraphAboutUs10;
    private javax.swing.JLabel ParagraphAboutUs11;
    private javax.swing.JLabel ParagraphAboutUs12;
    private javax.swing.JLabel ParagraphAboutUs13;
    private javax.swing.JLabel ParagraphAboutUs14;
    private javax.swing.JLabel ParagraphAboutUs15;
    private javax.swing.JLabel ParagraphAboutUs16;
    private javax.swing.JLabel ParagraphAboutUs17;
    private javax.swing.JLabel ParagraphAboutUs18;
    private javax.swing.JLabel ParagraphAboutUs19;
    private javax.swing.JLabel ParagraphAboutUs2;
    private javax.swing.JLabel ParagraphAboutUs20;
    private javax.swing.JLabel ParagraphAboutUs21;
    private javax.swing.JLabel ParagraphAboutUs22;
    private javax.swing.JLabel ParagraphAboutUs23;
    private javax.swing.JLabel ParagraphAboutUs24;
    private javax.swing.JLabel ParagraphAboutUs25;
    private javax.swing.JLabel ParagraphAboutUs26;
    private javax.swing.JLabel ParagraphAboutUs27;
    private javax.swing.JLabel ParagraphAboutUs28;
    private javax.swing.JLabel ParagraphAboutUs29;
    private javax.swing.JLabel ParagraphAboutUs3;
    private javax.swing.JLabel ParagraphAboutUs30;
    private javax.swing.JLabel ParagraphAboutUs31;
    private javax.swing.JLabel ParagraphAboutUs4;
    private javax.swing.JLabel ParagraphAboutUs5;
    private javax.swing.JLabel ParagraphAboutUs6;
    private javax.swing.JLabel ParagraphAboutUs7;
    private javax.swing.JLabel ParagraphAboutUs8;
    private javax.swing.JLabel ParagraphAboutUs9;
    private javax.swing.JLabel ParagraphBenefit1Box1;
    private javax.swing.JLabel ParagraphBenefit1Box2;
    private javax.swing.JLabel ParagraphBenefit1Box3;
    private javax.swing.JLabel ParagraphBenefit1Box4;
    private javax.swing.JLabel ParagraphBenefit1Box5;
    private javax.swing.JLabel ParagraphBenefit1Box6;
    private javax.swing.JLabel ParagraphBenefit1Box7;
    private javax.swing.JLabel ParagraphBenefit2Box1;
    private javax.swing.JLabel ParagraphBenefit2Box2;
    private javax.swing.JLabel ParagraphBenefit2Box3;
    private javax.swing.JLabel ParagraphBenefit2Box4;
    private javax.swing.JLabel ParagraphBenefit2Box5;
    private javax.swing.JLabel ParagraphBenefit2Box6;
    private javax.swing.JLabel ParagraphBenefit2Box7;
    private javax.swing.JLabel ParagraphBenefit3Box1;
    private javax.swing.JLabel ParagraphBenefit3Box2;
    private javax.swing.JLabel ParagraphBenefit3Box3;
    private javax.swing.JLabel ParagraphBenefit3Box4;
    private javax.swing.JLabel ParagraphBenefit3Box5;
    private javax.swing.JLabel ParagraphBenefit3Box6;
    private javax.swing.JLabel ParagraphBenefit3Box7;
    private javax.swing.JLabel ParagraphBenefit3Box8;
    private javax.swing.JLabel ParagraphBenefit4Box1;
    private javax.swing.JLabel ParagraphBenefit4Box2;
    private javax.swing.JLabel ParagraphBenefit4Box3;
    private javax.swing.JLabel ParagraphBenefit4Box4;
    private javax.swing.JLabel ParagraphBenefit4Box5;
    private javax.swing.JLabel ParagraphBenefit4Box6;
    private java.awt.Panel PersonalInformationPanel;
    private java.awt.Panel PrescribMedicationPanel;
    private java.awt.Panel ProceduresCompletedPanel;
    private javax.swing.JLabel QRCodeIcon;
    private javax.swing.JLabel QRCodeScannerHeading;
    private javax.swing.JLabel QRGeneratorGoBackButton;
    private javax.swing.JLabel QRScannerGoBackButton;
    private java.awt.Panel QRScannerPanel;
    private javax.swing.JLabel SaveCareAddressLbl;
    private javax.swing.JLabel SaveCareCustomerNumber;
    private javax.swing.JLabel SaveCareEircodeLbl1;
    private javax.swing.JLabel SaveCareEmailLbl;
    private javax.swing.JLabel SaveCareTelephoneNumber1;
    private java.awt.Panel SignInPanel;
    private javax.swing.JLabel SignedInHeading;
    private javax.swing.JLabel SmokerLabel;
    private java.awt.Panel SurgeryAppointmentPanel;
    private javax.swing.JButton UpdateActivityButton;
    private javax.swing.JComboBox<String> UpdateOptionBox;
    private java.awt.Panel UserLoggedInPanel;
    private javax.swing.JLabel aboutHearIcon;
    private javax.swing.JLabel aboutMedicalIcon;
    private javax.swing.JLabel aboutTimetableicon;
    private javax.swing.JLabel aboutUsHeading;
    private javax.swing.JLabel activityLabel;
    private javax.swing.JButton addPrescriptionButton;
    private javax.swing.JLabel addPrescriptionDateFinishLabel;
    private javax.swing.JLabel addPrescriptionDateStartLabel;
    private javax.swing.JButton addProcedureButton;
    private javax.swing.JButton addSAppointmentButton;
    private javax.swing.JComboBox<String> appointmentDay;
    private javax.swing.JLabel appointmentGoBack;
    private javax.swing.JComboBox<String> appointmentMonth;
    private javax.swing.JTextArea appointmentSummary;
    private javax.swing.JComboBox<String> appointmentTimeDigit;
    private javax.swing.JComboBox<String> appointmentYear;
    private javax.swing.JLabel benefitsHeading2;
    private javax.swing.JLabel benefitsInformationHeading;
    private javax.swing.JLabel benefitsInstantHeading;
    private javax.swing.JLabel benefitsMedicalHeading;
    private javax.swing.JLabel benefitsPrivacyHeading;
    private javax.swing.JLabel benefitsReminderHeading;
    private javax.swing.JLabel bloodLabel;
    private javax.swing.JLabel bloodTypeDisplay;
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.Panel cameraContainer;
    private javax.swing.JComboBox<String> clinicNameComboBox;
    private javax.swing.JLabel contactAddressIcon;
    private javax.swing.JLabel contactEmailIcon;
    private javax.swing.JLabel contactInfoLabel;
    private java.awt.Panel contactPanel1;
    private java.awt.Panel contactPanel2;
    private java.awt.Panel contactPanel3;
    private javax.swing.JLabel contactPhoneIcon;
    private javax.swing.JLabel createAccountBtn;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JLabel createAddressLabel;
    private javax.swing.JLabel createClearButton;
    private javax.swing.JLabel createDOBLabel;
    private javax.swing.JComboBox<String> createDay;
    private javax.swing.JLabel createDoctorLabel;
    private javax.swing.JTextField createDoctorNumber;
    private javax.swing.JLabel createEircodeLabel;
    private javax.swing.JLabel createEmailLabel;
    private javax.swing.JLabel createFirstNameLabel;
    private javax.swing.JLabel createHospitalIdLabel;
    private javax.swing.JLabel createLastNameLabel;
    private javax.swing.JLabel createMaritalStatuseLabel;
    private javax.swing.JLabel createMobileLabel;
    private javax.swing.JComboBox<String> createMonth;
    private javax.swing.JLabel createNationalityLabel;
    private javax.swing.JLabel createPPSLabel;
    private javax.swing.JLabel createPasswordLabel;
    private javax.swing.JTextField createPatientAddress;
    private javax.swing.JTextField createPatientEircode;
    private javax.swing.JTextField createPatientEmail;
    private javax.swing.JTextField createPatientFirstName;
    private javax.swing.JTextField createPatientLastName;
    private javax.swing.JComboBox<String> createPatientMaritalStatus;
    private javax.swing.JTextField createPatientMobileNumber;
    private javax.swing.JComboBox<String> createPatientNationality;
    private javax.swing.JTextField createPatientPPS;
    private javax.swing.JTextField createPatientPassword;
    private javax.swing.JComboBox<String> createPatientRace;
    private javax.swing.JComboBox<String> createPatientSex;
    private javax.swing.JLabel createRaceLabel;
    private javax.swing.JTextField createRePassword;
    private javax.swing.JLabel createRePasswordLabel;
    private javax.swing.JLabel createSexLabel;
    private java.awt.Panel createUserFieldsContainer;
    private javax.swing.JComboBox<String> createYear;
    private javax.swing.JLabel dateOfBirthDisplay;
    private javax.swing.JLabel dateOfBirthLabel;
    private javax.swing.JButton dispalyUpdateComponents;
    private javax.swing.JLabel eircodeDisplay;
    private javax.swing.JLabel eircodeLabel;
    private javax.swing.JLabel emailDisplay;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emergencyNumberDisplay;
    private javax.swing.JLabel emergencyNumberLabel;
    private javax.swing.JLabel firstNameDisplay;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel goBackProceduresPage;
    private javax.swing.JLabel hoeJoinHeading;
    private javax.swing.JLabel homeAboutUsButton;
    private javax.swing.JLabel homeAddressDisplay;
    private javax.swing.JLabel homeAddressLabel;
    private javax.swing.JLabel homeBenefitsButton;
    private javax.swing.JLabel homeContactUsButton;
    private javax.swing.JLabel homeJoinUsButton;
    private javax.swing.JLabel homeMenuButton;
    private javax.swing.JLabel homeMenuLogo;
    private javax.swing.JLabel homePageBackground;
    private javax.swing.JLabel homeSignInButton;
    private javax.swing.JLabel homeTransformHeading;
    private javax.swing.JComboBox<String> hospitalNameComboBox;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lastNameDisplay;
    private javax.swing.JLabel lastNameLabel;
    private java.awt.Panel logeedInMedicationPanel;
    private javax.swing.JLabel loggedInBackground;
    private javax.swing.JLabel loggedInChooseOneHeading;
    private javax.swing.JLabel loggedInMedicalHistoryButton;
    private java.awt.Panel loggedInMedicalHistoryPanel;
    private javax.swing.JLabel loggedInMedicationButton;
    private java.awt.Panel loggedInPersonalInforPanel;
    private javax.swing.JLabel loggedInPersonalInformationButton;
    private javax.swing.JLabel loggedInQRButton;
    private java.awt.Panel loggedInQRPanel;
    private javax.swing.JTextField loginEmail;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JLabel mainMenuLogOutButton;
    private javax.swing.JLabel mainMenuLogo;
    private javax.swing.JLabel mainMenuMedicalHistoryButton;
    private javax.swing.JLabel mainMenuPersonalInfoButton;
    private javax.swing.JLabel mainUserOptions;
    private javax.swing.JLabel maritalStatusDisplay;
    private javax.swing.JLabel maritalStatusLabel;
    private javax.swing.JLabel medicalHistoryIcon;
    private javax.swing.JLabel medicationBrandOutput;
    private javax.swing.JComboBox<String> medicationComboBox;
    private javax.swing.JLabel medicationDateEndOutput;
    private javax.swing.JLabel medicationDoctorNameLabel;
    private javax.swing.JLabel medicationDoctorNameOutput;
    private javax.swing.JLabel medicationFinishDateOutput;
    private javax.swing.JComboBox<String> medicationFinishDay;
    private javax.swing.JComboBox<String> medicationFinishMonth;
    private javax.swing.JComboBox<String> medicationFinishYear;
    private javax.swing.JLabel medicationNameLabel;
    private javax.swing.JLabel medicationNameOutput;
    private javax.swing.JLabel medicationNextButton;
    private javax.swing.JComboBox<String> medicationPrescribedDay;
    private javax.swing.JComboBox<String> medicationPrescribedMonth;
    private javax.swing.JLabel medicationPrescribedOutput;
    private javax.swing.JComboBox<String> medicationPrescribedYear;
    private javax.swing.JLabel medicationPreviousButton;
    private javax.swing.JLabel medicationSideEffectsLabel;
    private javax.swing.JTextArea medicationSideEffectsOutput;
    private javax.swing.JLabel mobileNumberLabel;
    private javax.swing.JLabel nationalityDisplay;
    private javax.swing.JLabel nextAppointmentButton;
    private javax.swing.JLabel nextMedication;
    private javax.swing.JLabel nextProcedureUpdate;
    private javax.swing.JLabel nextSAppointment;
    private javax.swing.JLabel occupationDisplay;
    private javax.swing.JLabel occupationLabel;
    private javax.swing.JLabel ourMissinLabel;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPasswordField passwordDisplay;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel patientNumber;
    private javax.swing.JLabel patientNumberDisplay;
    private java.awt.Panel personalInformationContentContainer;
    private javax.swing.JLabel personalInformationHeading;
    private javax.swing.JLabel personalicon;
    private javax.swing.JLabel ppsLabel;
    private javax.swing.JLabel prescribeMedicationHeading;
    private javax.swing.JLabel prevAppointmentButton;
    private javax.swing.JLabel prevProcedureUpdate;
    private javax.swing.JLabel prevSAppointment;
    private javax.swing.JLabel previousMedication;
    private javax.swing.JComboBox<String> procedureComboBox;
    private javax.swing.JComboBox<String> procedureDay;
    private javax.swing.JComboBox<String> procedureMonth;
    private javax.swing.JTextArea procedureSummary;
    private javax.swing.JComboBox<String> procedureYear;
    private javax.swing.JLabel proceduresCompletedHeading;
    private javax.swing.JLabel raceDisplay;
    private javax.swing.JLabel raceLabel;
    private javax.swing.JLabel sAppDate;
    private javax.swing.JTextArea sAppSummary;
    private javax.swing.JLabel sAppSurgerySummary;
    private javax.swing.JComboBox<String> sAppTImeNumber;
    private javax.swing.JLabel sAppTime;
    private javax.swing.JLabel sexDisplay;
    private javax.swing.JLabel sexLabel;
    private javax.swing.JButton showEditOptionsButton;
    private javax.swing.JButton showSAppUpdateComponents;
    private javax.swing.JLabel signInBackground;
    private javax.swing.JButton signInButton;
    private java.awt.Panel signInComponentsConainer;
    private javax.swing.JLabel signInEmailLabel;
    private javax.swing.JLabel signInHeading;
    private javax.swing.JLabel signInPasswordLabel;
    private javax.swing.JTextField signInPatientEmail;
    private javax.swing.JButton signPatientIn;
    private javax.swing.JLabel smokeQuestionDisplay;
    private javax.swing.JLabel smoothHealthHeading;
    private javax.swing.JLabel suregerAppGoBack;
    private javax.swing.JComboBox<String> surgeryAppDay;
    private javax.swing.JComboBox<String> surgeryAppMonth;
    private javax.swing.JComboBox<String> surgeryAppYear;
    private javax.swing.JLabel surgeryAppointmentHeading;
    private javax.swing.JComboBox<String> updateComboBox;
    private javax.swing.JButton updateExistingAppointment;
    private javax.swing.JTextField updateFieldContent;
    private javax.swing.JLabel updateFieldLabel;
    private java.awt.Panel updateLifestylePanel;
    private javax.swing.JButton updatePatientPersonalInfoBtn;
    private javax.swing.JButton updatePrescriptionButton;
    private javax.swing.JButton updatePrescriptionButtonQuery;
    private javax.swing.JButton updateProcedureButton;
    private javax.swing.JButton updateSAppointmentButton;
    private javax.swing.JLabel weeklyActivityDisplay;
    // End of variables declaration//GEN-END:variables

}
