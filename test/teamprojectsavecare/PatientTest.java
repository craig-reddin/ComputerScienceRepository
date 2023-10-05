/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class PatientTest {

    public PatientTest() {
    }

    
    
    
    
    
    
    
     /** 

     * Test of getPatientID method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientID() { 

        System.out.println("getPatientID"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientID("PT-012"); 

        String result = instance.getPatientID(); 

        assertEquals(expResult, result);   

    } 

    /** 

     * Test of setPatientID method, of class Patient. 

     */   

   @Test 

   public void testSetPatientID() { 

        System.out.println("setPatientID"); 

        String patientID = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientID(patientID); 

        String result = instance.getPatientID(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPharmacyID method, of class Patient. 

     */ 

    @Test 

    public void testGetPharmacyID() { 

        System.out.println("getPharmacyID"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPharmacyID("PT-012"); 

        String result = instance.getPharmacyID(); 

        assertEquals(expResult, result); 

    } 

    /** 

     * Test of setPharmacyID method, of class Patient. 

     */ 

    @Test 

    public void testSetPharmacyID() { 

        System.out.println("setPharmacyID"); 

        String pharmacyID = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPharmacyID(pharmacyID); 

        String result = instance.getPharmacyID(); 

        assertEquals(expResult,result); 

    } 

    /** 

     * Test of getPatientAddress method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientAddress() { 

        System.out.println("getPatientAddress"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientAddress("PT-012"); 

        String result = instance.getPatientAddress(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientAddress method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientAddress() { 

        System.out.println("setPatientAddress"); 

        String patientAddress = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientAddress(patientAddress); 

        String result = instance.getPatientAddress(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientEircode method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientEircode() { 

        System.out.println("getPatientEircode"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientEircode("PT-012"); 

        String result = instance.getPatientEircode(); 

        assertEquals(expResult, result); 

         

    } 

  

    /** 

     * Test of setPatientEircode method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientEircode() { 

        System.out.println("setPatientEircode"); 

        String patientEircode = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientEircode(patientEircode); 

        String result = instance.getPatientEircode(); 

        assertEquals(expResult,result); 

    } 

    /** 

     * Test of getPatientPhoneNumber method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientPhoneNumber() { 

        System.out.println("getPatientPhoneNumber"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientPhoneNumber("PT-012"); 

        String result = instance.getPatientPhoneNumber(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientPhoneNumber method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientPhoneNumber() { 

        System.out.println("setPatientPhoneNumber"); 

        String patientPhoneNumber = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientPhoneNumber(patientPhoneNumber); 

        String result = instance.getPatientPhoneNumber(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientNationality method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientNationality() { 

        System.out.println("getPatientNationality"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientNationality("PT-012"); 

        String result = instance.getPatientNationality(); 

        assertEquals(expResult, result); 

    } 

    /** 

     * Test of setPatientNationality method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientNationality() { 

        System.out.println("setPatientNationality"); 

        String patientNationality = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientNationality(patientNationality); 

        String result = instance.getPatientNationality(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientRace method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientRace() { 

        System.out.println("getPatientRace"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientRace("PT-012"); 

        String result = instance.getPatientRace(); 

        assertEquals(expResult, result); 

         

    } 

    /** 

     * Test of setPatientRace method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientRace() { 

        System.out.println("setPatientRace"); 

        String patientRace = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientRace(patientRace); 

        String result = instance.getPatientRace(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientDateOfBirth method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientDateOfBirth() { 

        System.out.println("getPatientDateOfBirth"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientDateOfBirth("PT-012"); 

        String result = instance.getPatientDateOfBirth(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientDateOfBirth method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientDateOfBirth() { 

        System.out.println("setPatientDateOfBirth"); 

        String patientDateOfBirth = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientDateOfBirth(patientDateOfBirth); 

        String result = instance.getPatientDateOfBirth(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientMaritalStatus method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientMaritalStatus() { 

        System.out.println("getPatientMaritalStatus"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientMaritalStatus("PT-012"); 

        String result = instance.getPatientMaritalStatus(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientMaritalStatus method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientMaritalStatus() { 

        System.out.println("setPatientMaritalStatus"); 

        String patientMaritalStatus = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientMaritalStatus(patientMaritalStatus); 

        String result = instance.getPatientMaritalStatus(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientSex method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientSex() { 

        System.out.println("getPatientSex"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSex("PT-012"); 

        String result = instance.getPatientSex(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientSex method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientSex() { 

        System.out.println("setPatientSex"); 

        String patientSex = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSex(patientSex); 

        String result = instance.getPatientSex(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientEmergencyNumber method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientEmergencyNumber() { 

        System.out.println("getPatientEmergencyNumber"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientEmergencyNumber("PT-012"); 

        String result = instance.getPatientEmergencyNumber(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientEmergencyNumber method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientEmergencyNumber() { 

        System.out.println("setPatientEmergencyNumber"); 

        String patientemergencyNumber = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientEmergencyNumber(patientemergencyNumber); 

        String result = instance.getPatientEmergencyNumber(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientHospitalId method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientHospitalId() { 

        System.out.println("getPatientHospitalId"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientHospitalId("PT-012"); 

        String result = instance.getPatientHospitalId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientHospitalId method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientHospitalId() { 

        System.out.println("setPatientHospitalId"); 

        String patientHospitalId = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientHospitalId(patientHospitalId); 

        String result = instance.getPatientHospitalId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientSurgeryId method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientSurgeryId() { 

        System.out.println("getPatientSurgeryId"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSurgeryId("PT-012"); 

        String result = instance.getPatientSurgeryId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientSurgeryId method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientSurgeryId() { 

        System.out.println("setPatientSurgeryId"); 

        String patientSurgeryId = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSurgeryId(patientSurgeryId); 

        String result = instance.getPatientSurgeryId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientNumber method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientNumber() { 

        System.out.println("getPatientNumber"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientNumber("PT-012"); 

        String result = instance.getPatientNumber(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientNumber method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientNumber() { 

        System.out.println("setPatientNumber"); 

        String patientNumber = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientNumber(patientNumber); 

        String result = instance.getPatientNumber(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientBloodType method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientBloodType() { 

        System.out.println("getPatientBloodType"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientBloodType("PT-012"); 

        String result = instance.getPatientBloodType(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientBloodType method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientBloodType() { 

        System.out.println("setPatientBloodType"); 

        String patientBloodType = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientBloodType(patientBloodType); 

        String result = instance.getPatientBloodType(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientSmoker method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientSmoker() { 

        System.out.println("getPatientSmoker"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSmoker("PT-012"); 

        String result = instance.getPatientSmoker(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientSmoker method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientSmoker() { 

        System.out.println("setPatientSmoker"); 

        String patientSmoker = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientSmoker(patientSmoker); 

        String result = instance.getPatientSmoker(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientWeeklyActivity method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientWeeklyActivity() { 

        System.out.println("getPatientWeeklyActivity"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientWeeklyActivity("PT-012"); 

        String result = instance.getPatientWeeklyActivity(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientWeeklyActivity method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientWeeklyActivity() { 

        System.out.println("setPatientWeeklyActivity"); 

        String patientWeeklyActivity = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientWeeklyActivity(patientWeeklyActivity); 

        String result = instance.getPatientWeeklyActivity(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientOccupation method, of class Patient. 

     */ 

    @Test 

    public void testGetPatientOccupation() { 

        System.out.println("getPatientOccupation"); 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientOccupation("PT-012"); 

        String result = instance.getPatientOccupation(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientOccupation method, of class Patient. 

     */ 

    @Test 

    public void testSetPatientOccupation() { 

        System.out.println("setPatientOccupation"); 

        String patientOccupation = "PT-012"; 

        Patient instance = new Patient(); 

        String expResult = "PT-012"; 

        instance.setPatientOccupation(patientOccupation); 

        String result = instance.getPatientOccupation(); 

        assertEquals(expResult,result); 

    } 
    
    
    

    @Test
    public void testChangeData() {
        try {

            System.out.println("changeData");
            String updateContent = "NewP@ssW0rd!";
            String updateField = "Password";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";
            instance.changeData(updateContent, updateField);
            ResultSet rs = stmt.executeQuery(
                    "select  PatientPassword from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of changeData method, of class Patient.
     */

    @Test
    public void testChangeDataFirstName() {
        try {

            System.out.println("changeData - FirstName");
            String updateContent = "Staccy";
            String updateField = "FirstName";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";
            instance.changeData(updateContent, updateField);
            ResultSet rs = stmt.executeQuery(
                    "select  PatientFirstName from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of changeData method, of class Patient.
     */

    @Test
    public void testChangeDataLastName() {
        try {

            System.out.println("changeData - Last Name");
            String updateContent = "Henny";
            String updateField = "LastName";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";
            instance.changeData(updateContent, updateField);
            ResultSet rs = stmt.executeQuery(
                    "select  PatientLastName from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of changeData method, of class Patient.
     */

    @Test
    public void testChangeDataMobileNumber() {
        try {

            System.out.println("changeData - Mobile Number");
            String updateContent = "0876776624";
            String updateField = "PhoneNumber";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";

            instance.changeData(updateContent, updateField);

            ResultSet rs = stmt.executeQuery(
                    "select  PatientPhoneNumber from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of changeData method, of class Patient.
     */

    @Test
    public void testChangeDataMaritalStatus() {
        try {

            System.out.println("changeData - Marital Status");
            String updateContent = "Single";
            String updateField = "MaritalStatus";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";

            instance.changeData(updateContent, updateField);

            ResultSet rs = stmt.executeQuery(
                    "select  PatientMaritalStatus from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    @Test
     public void testChangeDataAddress() {
        try {

            System.out.println("changeData - Address");
            String updateContent = "23 Love Lane";
            String updateField = "HomeAddress";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";

            instance.changeData(updateContent, updateField);

            ResultSet rs = stmt.executeQuery(
                    "select  PatientHomeAddress from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
     
     
     @Test
     public void testChangeDataSex() {
        try {

            System.out.println("changeData - Sex");
            String updateContent = "Male";
            String updateField = "Sex";
            Patient instance = new Patient();
            instance.setUserEmail("stacys@gmail.ie");

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();
            String email = "stacys@gmail.ie";

            instance.changeData(updateContent, updateField);

            ResultSet rs = stmt.executeQuery(
                    "select  PatientSex from Patient where PatientEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (updateContent.equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expectedResult, result);

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Test of setPatientVariable method, of class Patient.
     */
    @Test
    public void testSetPatientVariablePassword() {
        System.out.println("setPatientVariable - Password");
        String updateField = "Password";
        String updateContent = "ThisI5Pa55Word@";
        Patient instance = new Patient();
        instance.setPatientVariable(updateField, updateContent);
        assertEquals(instance.getUserPassword(),updateContent);

    }
    
    
    @Test
    public void testSetPatientVariableSex() {
        System.out.println("setPatientVariable - Sex");
        String updateField = "Sex";
        String updateContent = "Male";
        Patient instance = new Patient();
        instance.setPatientVariable(updateField, updateContent);
        assertEquals(instance.getPatientSex(),updateContent);

    }
    @Test
    public void testSetPatientVariableAddress() {
        System.out.println("setPatientVariable - Address");
        String updateField = "Address";
        String updateContent = "23 Holy Field";
        Patient instance = new Patient();
        instance.setPatientVariable(updateField, updateContent);
        assertEquals(instance.getPatientAddress(),updateContent);

    }
    
    @Test
    public void testSetPatientVariablePhoneNumber() {
        System.out.println("setPatientVariable - Mobile Number");
        String updateField = "PhoneNumber";
        String updateContent = "0876665566";
        Patient instance = new Patient();
        instance.setPatientVariable(updateField, updateContent);
        assertEquals(instance.getPatientPhoneNumber(),updateContent);

    }
    
    @Test
    public void testSetPatientVariableMaritalStatus() {
        System.out.println("setPatientVariable - Marital Status");
        String updateField = "MaritalStatus";
        String updateContent = "Married";
        Patient instance = new Patient();
        instance.setPatientVariable(updateField, updateContent);
        assertEquals(instance.getPatientMaritalStatus(),updateContent);

    }

    /**
     * Test of validateMaritalStatus method, of class Patient.
     */
    
    @Test
    public void testValidateMaritalStatusSingle() {
        System.out.println("validateMaritalStatus  - Single");
        String maritalStatus = "Single";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValidateMaritalStatusSingleEmptyInput() {
        System.out.println("validateMaritalStatus  - Empty Input");
        String maritalStatus = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
     @Test
    public void testValidateMaritalStatusMarried() {
        System.out.println("validateMaritalStatus  - Married");
        String maritalStatus = "Married";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
     @Test
    public void testValidateMaritalStatusSeperated() {
        System.out.println("validateMaritalStatus  - Seperated");
        String maritalStatus = "Seperated";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
     @Test
    public void testValidateMaritalStatusDivorced() {
        System.out.println("validateMaritalStatus  - Divorced");
        String maritalStatus = "Divorced";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
    
    
     @Test
    public void testValidateMaritalStatusSingleInvalidInput() {
        System.out.println("validateMaritalStatus  - Invalid Input");
        String maritalStatus = "sdfghgfdsdfg";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateMaritalStatus(maritalStatus);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of generateQR method, of class Patient.
//     */
    
    //A negative qr code will never be dislayes to the user. the value is extracted from the database and changed into a qr code.
    @Test
    public void testGenerateQR() {
        System.out.println("generateQR - check if file exists after running method");
        Patient instance = new Patient();
        instance.setUserEmail("stacys@gmail.ie");
        if (new File("qr.png").exists()){
           new File("qr.png").delete();
        }
        instance.generateQR();
        
        boolean result = false;
        boolean expectedResult = true;
        
        if (new File("qr.png").exists()){
            result = true;
        }
        
        assertEquals(expectedResult, result);
    }
    
    
//
//    /**
//     * Test of returnPatientFullName method, of class Patient.
//     */
    @Test
    public void testReturnPatientFullName() {
        System.out.println("returnPatientFullName");
        Patient instance = new Patient();
        instance.setUserFirstName("Craig");
        instance.setUserLastName("Reddin");
        
        String expResult = "Craig Reddin";
        String result = instance.returnPatientFullName();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of connectToDisplay method, of class Patient.
//     */
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
//     * Test of validateEmergencyNumber method, of class Patient.
//     */
    @Test
    public void testValidateEmergencyNumber() {
        System.out.println("validateEmergencyNumber - Valid Number");
        String emergencyNumber = "0876776621";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateEmergencyNumber(emergencyNumber);
        assertEquals(expResult, result);

    }
    
    
      @Test
    public void testValidateEmergencyNumberInvalidNumber() {
        System.out.println("validateEmergencyNumber - Invalid Number");
        String emergencyNumber = "0876776";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateEmergencyNumber(emergencyNumber);
        assertEquals(expResult, result);

    }
    
    
    
//
//    /**
//     * Test of validateBloodType method, of class Patient.
//     */
    @Test
    public void testValidateBloodTypeANegative() {
        System.out.println("validateBloodType - A-");
        String bloodType = "A-";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateBloodTypeAPositive() {
        System.out.println("validateBloodType - A-");
        String bloodType = "A-";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateBloodTypONegative() {
        System.out.println("validateBloodType - O-");
        String bloodType = "O-";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
     @Test
    public void testValidateBloodTypBNegative() {
        System.out.println("validateBloodType - B-");
        String bloodType = "B-";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateBloodTypABNegative() {
        System.out.println("validateBloodType - AB-");
        String bloodType = "AB-";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
      @Test
    public void testValidateBloodTypeOPositive() {
        System.out.println("validateBloodType - O+");
        String bloodType = "O+";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
       @Test
    public void testValidateBloodTypeABPositive() {
        System.out.println("validateBloodType - AB+");
        String bloodType = "AB+";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
           @Test
    public void testValidateBloodTypeBPositive() {
        System.out.println("validateBloodType - B+");
        String bloodType = "B+";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
       @Test
    public void testValidateBloodTypeInvalidEmptyBloodType() {
        System.out.println("validateBloodType -Invalid Empty Blood Type");
        String bloodType = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }
    
      @Test
    public void testValidateBloodTypeInvalidBloodType() {
        System.out.println("validateBloodType -Invalid Blood Type");
        String bloodType = "dfg";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateBloodType(bloodType);
        assertEquals(expResult, result);
    }

    /**
     * Test of validateSmoke method, of class Patient.
     */
    @Test
    public void testValidateSmoke() {
        System.out.println("validateSmoke - valid input");
        String Smoker = "never";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateSmoke(Smoker);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValidateSmokeHeavy() {
        System.out.println("validateSmoke - valid input");
        String Smoker = "current 20+";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateSmoke(Smoker);
        assertEquals(expResult, result);

    }
    
     @Test
    public void testValidateEmpty() {
        System.out.println("validateSmoke - empty invalid input");
        String Smoker = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateSmoke(Smoker);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValidateSmokerInvalidInput() {
        System.out.println("validateSmoke - Invalid input");
        String Smoker = "sdfghgfds";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateSmoke(Smoker);
        assertEquals(expResult, result);

    }
    
    
    
    
    
//
//    /**
//     * Test of validateWeeklyActivity method, of class Patient.
//     */
    @Test
    public void testValidateWeeklyActivityValid() {
        System.out.println("validateWeeklyActivity - Valid Input");
        String weeklyActivity = "sedentary 4 hours";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateWeeklyActivity(weeklyActivity);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testValidateWeeklyActivityInValidEmpty() {
        System.out.println("validateWeeklyActivity - Invalid Empty Input");
        String weeklyActivity = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateWeeklyActivity(weeklyActivity);
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void testValidateWeeklyActivityInValidValue() {
        System.out.println("validateWeeklyActivity - Invalid Input");
        String weeklyActivity = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateWeeklyActivity(weeklyActivity);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of validateOccupation method, of class Patient.
//     */
    @Test
    public void testValidateOccupationInvalidEmpty() {
        System.out.println("validateOccupation - Empty Invalid Input");
        String occupation = "";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateOccupation(occupation);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValidateOccupationvalid() {
        System.out.println("validateOccupation - valid Input");
        String occupation = "Truck Driver";
        Patient instance = new Patient();
        boolean expResult = true;
        boolean result = instance.validateOccupation(occupation);
        assertEquals(expResult, result);

    }
    
    @Test
    public void testValidateOccupationInvalidSPecialCharacters() {
        System.out.println("validateOccupation - valid Input");
        String occupation = "Truck@!Driver";
        Patient instance = new Patient();
        boolean expResult = false;
        boolean result = instance.validateOccupation(occupation);
        assertEquals(expResult, result);

    }
}
