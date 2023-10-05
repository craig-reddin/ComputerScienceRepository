/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import com.github.sarxos.webcam.Webcam;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;
import javax.swing.JComboBox;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class SaveCareGUITest {

    public SaveCareGUITest() {
    }

    /**
     * Test of logOut method, of class SaveCareGUI.
     */
    @Test
    public void testLogOut() {
        System.out.println("logOut");
        SaveCareGUI instance = new SaveCareGUI();
        //method to open the camer. Logout closes camera
        instance.initWebcam();
        instance.logOut();
        assertNull(instance.getWebcam());
    }
//
//    /**
//     * Test of initializeComponents method, of class SaveCareGUI.
//     */

    @Test
    public void testInitializeComponents() {
        System.out.println("initializeComponents");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getPatient().setUserFirstName("James");
        instance.initializeComponents();
        //  patient first name should set to "" when the initializeComponents method is called.
        String expResult = "";
        String result = instance.getPatient().getUserFirstName();

        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of createPatientAccount method, of class SaveCareGUI.
//     */

    @Test
    public void testCreatePatientAccount() {
        try {
            System.out.println("createPatientAccount");

            SaveCareGUI instance = new SaveCareGUI();
            instance.getPatient().setMyConn(instance.getPatient().getConnection());
            instance.createPatientAccount();
            boolean emailExists = false;
            boolean expectedResult = true;
            Patient patient = new Patient();
            patient.setMyConn(patient.getConnection());
            Statement stmt = patient.getMyConn().createStatement();
            String email = "craig1@gmail.com";
            ResultSet rs = stmt.executeQuery(
                    "select  PatientEmail from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (email.equals(rs.getString(1))) {
                    emailExists = true;
                }
            }
            assertEquals(expectedResult, emailExists);
        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
//
//    /**
//     * Test of createDoctorAccount method, of class SaveCareGUI.
//     */

    @Test
    public void testInitWebcam() {
        System.out.println("initWebcam");
        SaveCareGUI instance = new SaveCareGUI();
        instance.setWebcam(null);
        instance.initWebcam();
        boolean result = false;
        boolean expResult = true;
        if (instance.getWebcam() != null) {
            result = true;
        }
        
        assertEquals(expResult, result);
        
    }

    @Test
    public void testNewThread() {
        System.out.println("newThread");
        Runnable r = null;
        SaveCareGUI instance = new SaveCareGUI();
        Thread result = null;
        result = instance.newThread(r);
        assertNotNull(result);

    }

    @Test
    public void testRun() {
        System.out.println("run");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getWebcam();
        boolean isWebcamOpen = false;
        instance.initWebcam();
        if (instance.getWebcam() != null) {
            isWebcamOpen = true;
        }
        boolean openWebcam = true;
        assertEquals(isWebcamOpen, openWebcam);

    }

    @Test
    public void testCreateDoctorAccount() {
        try {
            System.out.println("createDoctorAccount");
            SaveCareGUI instance = new SaveCareGUI();
            instance.getPatient().setMyConn(instance.getPatient().getConnection());
            instance.createDoctorAccount();
            boolean emailExists = false;
            boolean expResult = true;
            Patient patient = new Patient();
            patient.setMyConn(patient.getConnection());
            Statement stmt = patient.getMyConn().createStatement();

            String email = "craig1@gmail.com";
            ResultSet rs = stmt.executeQuery(
                    "select  DoctorEmail from Doctor where DoctorEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (email.equals(rs.getString(1))) {
                    emailExists = true;
                }
            }
            assertEquals(expResult, emailExists);
        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
//    /**
//     * Test of registerIntoUserTable method, of class SaveCareGUI.
//     */

    @Test
    public void testRegisterIntoUserTable() {

        try {
            System.out.println("registerIntoUserTable");
            SaveCareGUI instance = new SaveCareGUI();
            instance.registerIntoUserTable();
            boolean expectedResult = true;

            boolean emailExists = false;

            User user = new User();
            user.setMyConn(user.getConnection());
            Statement stmt = user.getMyConn().createStatement();
            String email = "craig1@gmail.com";
            ResultSet rs = stmt.executeQuery(
                    "select  UserEmail from Users where UserEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (email.equals(rs.getString(1))) {
                    emailExists = true;
                }
            }
            assertEquals(expectedResult, emailExists);
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of setPatientInterface method, of class SaveCareGUI.
     */
    @Test
    public void testSetPatientInterface() {
        System.out.println("setPatientInterface");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getPatient().setUserEmail("stacys@gmail.ie");
        instance.setPatientInterface();
        String name = instance.getPatient().getUserFirstName();
        String expResult = "Stacy";
        assertEquals(expResult, name);

    }
//
//    /**
//     * Test of secureDoctorLoggedInPage method, of class SaveCareGUI.
//     */

    @Test
    public void testSecureDoctorLoggedInPagePatientLoggedIn() {
        System.out.println("secureDoctorLoggedInPage - Patient Logged In");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getPatient().setPatientID("PT-4000000");
        boolean expResult = true;
        boolean result = instance.secureDoctorLoggedInPage();
        assertEquals(expResult, result);
    }

    @Test
    public void testSecureDoctorLoggedInPagePatientNotLoggedIn() {
        System.out.println("secureDoctorLoggedInPage - Patient Not Logged In");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getPatient().setPatientID("");
        boolean expResult = false;
        boolean result = instance.secureDoctorLoggedInPage();
        assertEquals(expResult, result);
    }

    //Validation os completed beofre this method is executed, One example needed 
    @Test
    public void testConnectToDisplay() {
        System.out.println("connectToDisplay");
        Patient patient = new Patient();
        patient.setUserEmail("stacys@gmail.ie");
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "6815338U";
        instance.connectToDisplay(patient);
        String result = patient.getUserPPSNumber();
        assertEquals(expResult, result);
    }

//
//    /**
//     * Test of fillUpdateFields method, of class SaveCareGUI.
//     */
    @Test
    public void testFillUpdateFields() {
        System.out.println("fillUpdateFields");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getPatientUpdateFields().clear();
        instance.fillUpdateFields();
        int number = instance.getPatientUpdateFields().size();
        assertEquals(15, number);

    }
//
//    /**
//     * Test of validatUpdateField method, of class SaveCareGUI.
//     */

    @Test
    public void testValidatUpdateField() {
        System.out.println("validatUpdateField - Password");
        String input = "Password";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Password";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateFieldGender() {
        System.out.println("validatUpdateField");
        String input = "Gender";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Sex";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateFieldHomeAddress() {
        System.out.println("validatUpdateField - Home Address");
        String input = "Home Address";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "HomeAddress";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateFieldNumber() {
        System.out.println("validatUpdateField - Mobile Number");
        String input = "Phone Number";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "PhoneNumber";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateMaritalStatus() {
        System.out.println("validatUpdateField - Marital Status");
        String input = "Marital Status";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "MaritalStatus";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateFirstName() {
        System.out.println("validatUpdateField - First Name");
        String input = "First Name";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "FirstName";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateLastName() {
        System.out.println("validatUpdateField - Last Name");
        String input = "Last Name";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "LastName";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateEmail() {
        System.out.println("validatUpdateField - Email");
        String input = "Email";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Email";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateEircode() {
        System.out.println("validatUpdateField - Eircode");
        String input = "Eircode";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Eircode";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateEmergencyNumber() {
        System.out.println("validatUpdateField - Eircode");
        String input = "Emergency Number";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "EmergencyNumber";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateBloodType() {
        System.out.println("validatUpdateField - Blood Type");
        String input = "Blood Type";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "BloodType";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateSmoker() {
        System.out.println("validatUpdateField - Blood Type");
        String input = "Smoker";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Smoker";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateWeeklyActivity() {
        System.out.println("validatUpdateField - Weekly Activity");
        String input = "Weekly Activity";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "WeeklyActivity";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatUpdateWeeklyOccupation() {
        System.out.println("validatUpdateField - Occupation");
        String input = "Occupation";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "Occupation";
        String result = instance.validatUpdateField(input);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of displayMedicalHistoryInformation method, of class SaveCareGUI.
//     */
    @Test
    public void testDisplayMedicalHistoryInformation() {
        System.out.println("displayMedicalHistoryInformation");
        SaveCareGUI instance = new SaveCareGUI();

        Patient patient = new Patient();
        patient.setPatientID("PT-4000000");
        patient.setPatientHospitalId("HH-4000000");
        patient.setPharmacyID("PA-4000000");
        patient.setPatientSurgeryId("SA-4000000");
        patient.setUserPPSNumber("6815338U");

        instance.displayMedicalHistoryInformation();
    }

//
//    /**
//     * Test of loadTimes method, of class SaveCareGUI.
//     */
    @Test
    public void testLoadTimes() {
        System.out.println("loadTimes");
        SaveCareGUI instance = new SaveCareGUI();
        instance.getAppointmentTimes().clear();
        instance.loadTimes();
        int expResult = 96;
        int result = instance.getAppointmentTimes().size();

        assertEquals(expResult, result);

    }

//
//    /**
//     * Test of changeforwadDateToHiphen method, of class SaveCareGUI.
//     */
    @Test
    public void testChangeforwadDateToHiphen() {
        System.out.println("changeforwadDateToHiphen");
        String date = "25/10/2010";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "2010-10-25";
        String result = instance.changeforwadDateToHiphen(date);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of getDateToday method, of class SaveCareGUI.
//     */

    @Test
    public void testGetDateToday() {
        System.out.println("getDateToday");

        LocalDate date = LocalDate.now();
        SaveCareGUI instance = new SaveCareGUI();

        LocalDate result = instance.getDateToday();
        assertEquals(date, result);

    }

//    /**
//     * Test of addMedicationsComboBox method, of class SaveCareGUI.
    //*/
    @Test
    public void testAddMedicationsComboBox() {
        System.out.println("addMedicationsComboBox");
        SaveCareGUI instance = new SaveCareGUI();
        instance.addMedicationsComboBox();
        int expResult = 2;
        int result = instance.getMedicationNameDose().size();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of addProceduresComboBox method, of class SaveCareGUI.
//     */

    @Test
    public void testAddProceduresComboBox() {
        System.out.println("addProceduresComboBox");
        SaveCareGUI instance = new SaveCareGUI();
        instance.addProceduresComboBox();
        int expResult = 2;
        int result = instance.getAllprocedure().size();
        assertEquals(expResult, result);

    }

//    /**
//     * Test of loadData method, of class SaveCareGUI.
//     */
    @Test
    public void testLoadData() {
        System.out.println("loadData");
        SaveCareGUI instance = new SaveCareGUI();

        instance.loadData();
        int expResult = 12;
        int result = instance.getMonths().size();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of getAge method, of class SaveCareGUI.
//     */

    @Test
    public void testGetAge() {
        System.out.println("getAge");
        String date = "2022-04-21";
        SaveCareGUI instance = new SaveCareGUI();
        int expResult = 1;
        int result = instance.getAge(date);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of changeDate method, of class SaveCareGUI.
//     */

    @Test
    public void testChangeDate() {
        System.out.println("changeDate");
        String date = "2010-04-22";
        SaveCareGUI instance = new SaveCareGUI();
        LocalDate expResult = LocalDate.of(2010, 04, 22);
        LocalDate result = instance.changeDate(date);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of pastDateValidation method, of class SaveCareGUI.
//     */

    @Test
    public void testPastDateValidation() {
        System.out.println("pastDateValidation");
        String date = "2020-10-10";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = true;
        boolean result = instance.pastDateValidation(date);

        assertEquals(expResult, result);
    }

    @Test
    public void testPastDateValidationNegative() {
        System.out.println("pastDateValidation negative");
        String date = "2024-10-10";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = false;
        boolean result = instance.pastDateValidation(date);

        assertEquals(expResult, result);
    }

//    /**
//     * Test of twoDateValidation method, of class SaveCareGUI.
//     */
    @Test
    public void testTwoDateValidationFalse() {
        System.out.println("twoDateValidation - false Testing");
        String beforeDate = "2022-10-10";
        String afterDate = "2022-10-11";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = false;
        boolean result = instance.twoDateValidation(beforeDate, afterDate);
        assertEquals(expResult, result);

    }

    @Test
    public void testTwoDateValidationFalseSameDate() {
        System.out.println("twoDateValidation - Negative Testing same date");
        String beforeDate = "2022-10-10";
        String afterDate = "2022-10-10";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = false;
        boolean result = instance.twoDateValidation(beforeDate, afterDate);
        assertEquals(expResult, result);

    }

    @Test
    public void testTwoDateValidationPositiveSameDate() {
        System.out.println("twoDateValidation - Positive (return true) Testing 2 dates");
        String beforeDate = "2022-10-10";
        String afterDate = "2022-10-09";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = true;
        boolean result = instance.twoDateValidation(beforeDate, afterDate);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of deconstructAndDecrementID method, of class SaveCareGUI.
//     */
    //The value passed to this method is extracted from the database and changed to become a unique id.
    //No user input is used and no invalid numbers will be created from this method

    @Test
    public void testDeconstructAndDecrementID() {
        System.out.println("deconstructAndDecrementID Positive Test");
        String id = "PT-11";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "PT-10";
        String result = instance.deconstructAndDecrementID(id);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of setIDCreate method, of class SaveCareGUI.
//     */

    @Test
    public void testSetIDCreatePatient() {
        System.out.println("setIDCreate Patient");
        Patient p = new Patient();
        p.setMyConn(p.getConnection());
        SaveCareGUI instance2 = new SaveCareGUI();
        String id = instance2.setIDCreate("Patient");
        String value = "Patient";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = id;
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testSetIDCreateDoctor() {
        System.out.println("setIDCreate Doctor");
        Doctor d = new Doctor();
        d.setMyConn(d.getConnection());
        SaveCareGUI instance2 = new SaveCareGUI();
        String id = instance2.setIDCreate("Doctor");
        String value = "Doctor";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "DA-3999553";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetIDCreateUser() {
        System.out.println("setIDCreate");
        String value = "User";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "US-3999554";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testSetIDCreateMedication() {
        System.out.println("setIDCreate");
        String value = "Medication";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "MP-4000000";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testSetIDCreateProcedure() {
        System.out.println("setIDCreate");
        String value = "Procedure";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "PC-4000000";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testSetIDCreateAppointment() {
        System.out.println("setIDCreate");
        String value = "Appointment";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "AP-3999998";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testSetIDCreatePatientNumber() {

        System.out.println("setIDCreate");

        String value = "Patient Number";
        SaveCareGUI instance = new SaveCareGUI();
        String expResult = "1000000";
        String result = instance.setIDCreate(value);
        assertEquals(expResult, result);

    }

    @Test
    public void testEmptyInput() {
        System.out.println("emptyInput Test");
        String input = "";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = false;
        boolean result = instance.emptyInput(input);
        assertEquals(expResult, result);

    }

    @Test
    public void testNonEmptyInput() {
        System.out.println("emptyInput Test");
        String input = "Hi";
        SaveCareGUI instance = new SaveCareGUI();
        boolean expResult = true;
        boolean result = instance.emptyInput(input);
        assertEquals(expResult, result);

    }

    @AfterClass
    public static void tearDown() throws Exception {
        File file = new File("C:/mydb/");
        file.delete();

        LocalSQLiteDatabase db = new LocalSQLiteDatabase();
        db.loadDatabase();
        ExtractDoctorData docData = new ExtractDoctorData();
        docData.loadCSV();
    }

}
