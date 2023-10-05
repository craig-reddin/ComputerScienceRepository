/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class HospitalUserTest {

    public HospitalUserTest() {
    }

    @Test
    public void testGetHospitalID() {
        System.out.println("getHospitalID");
        HospitalUser instance = new HospitalUser();
        instance.setHospitalID("HH-01");
        String expResult = "HH-01";
        String result = instance.getHospitalID();
        assertEquals(expResult, result);
    }

    /**
     * *
     * Test of setHospitalID method, of class HospitalUser. *
     */

    /* 

     

     */
    @Test

    public void testSetHospitalID() {
        System.out.println("setHospitalID");
        String hospitalID = "HH-02";
        HospitalUser instance = new HospitalUser();
        instance.setHospitalID(hospitalID);
        String result = instance.getHospitalID();
        assertEquals("HH-02", result);
    }
//    /** 
//     * Test of getSurgeryID method, of class HospitalUser. 
//     */ 
    @Test

    public void testGetSurgeryID() {

        System.out.println("getSurgeryID");

        HospitalUser instance = new HospitalUser();

        instance.setSurgeryID("SA-4000000");

        String expResult = "SA-4000000";

        String result = instance.getSurgeryID();

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of setSurgeryID method, of class HospitalUser. 
//     */ 
    @Test

    public void testSetSurgeryID() {

        System.out.println("setSurgeryID");

        String surgeryID = "'SA-4000000'";

        HospitalUser instance = new HospitalUser();

        instance.setSurgeryID(surgeryID);

        instance.getSurgeryID();

    }

    /**
     * Test of changeDataUsersTable method, of class HospitalUser.
     */
    @Test
    public void testChangeDataUsersTableEmail() {

        try {
            System.out.println("changeDataUsersTable - Email");
            String updateContent = "Leriz@gg.ie";
            String updateField = "Email";

            HospitalUser instance = new HospitalUser();
            instance.setUserEmail("stacys@gmail.ie");
            instance.changeDataUsersTable(updateContent, updateField);

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();

            ResultSet rs = stmt.executeQuery(
                    "select  UserEmail from Users where UserEmail='"
                    + "Leriz@gg.ie" + "';");
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
    public void testChangeDataUsersTablePassword() {

        try {
            System.out.println("changeDataUsersTable - Password");
            String updateContent = "Th!5ISPa55Word";
            String updateField = "Password";
            HospitalUser instance = new HospitalUser();
            instance.setUserEmail("Thomas@beaumont.ie");
            instance.changeDataUsersTable(updateContent, updateField);

            boolean result = false;
            boolean expectedResult = true;

            instance.setMyConn(instance.getConnection());
            Statement stmt = instance.getMyConn().createStatement();

            ResultSet rs = stmt.executeQuery(
                    "select  UserPassword from Users where UserEmail='"
                    + "Thomas@beaumont.ie" + "';");
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
     * Test of storeMedications method, of class HospitalUser.
     */
    @Test
    public void testStoreMedications() {
        System.out.println("storeMedications");
        HospitalUser instance = new HospitalUser();
        Patient patient = new Patient();
        instance.setMyConn(instance.getConnection());
        patient.setPatientID("PT-4000000");
        ArrayList<MedicationPrescribed> meds = new ArrayList<>();
        instance.storeMedications(patient, meds);
        int expResult = 1;
        int result = meds.size();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of storeProcedures method, of class HospitalUser.
//     */

    @Test
    public void testStoreProcedures() {
        System.out.println("storeProcedures");
        Patient patient = new Patient();
        patient.setPatientID("PT-4000000");
        ArrayList<ProceduresCompleted> procedures = new ArrayList<>();
        HospitalUser instance = new HospitalUser();
        instance.setMyConn(instance.getConnection());
        instance.storeProcedures(patient, procedures);
        int expResult = 1;
        int result = procedures.size();
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of storeAppointmets method, of class HospitalUser.
//     */

    @Test
    public void testStoreAppointmets() {
        System.out.println("storeAppointmets");
        Patient patient = new Patient();
        patient.setPatientID("PT-4000000");
        ArrayList<Appointment> appointments = new ArrayList<>();
        HospitalUser instance = new HospitalUser();
        instance.setMyConn(instance.getConnection());
        instance.storeAppointmets(patient, appointments);
        int expResult = 2;
        int result = appointments.size();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of storeSurgeryAppointmets method, of class HospitalUser.
//     */

    @Test
    public void testStoreSurgeryAppointmets() {
        System.out.println("storeSurgeryAppointmets");

        Patient patient = new Patient();
        patient.setPatientID("PT-4000000");
        ArrayList<Appointment> appointments = new ArrayList<>();
        HospitalUser instance = new HospitalUser();
        instance.setMyConn(instance.getConnection());
        instance.storeAppointmets(patient, appointments);
        instance.storeSurgeryAppointmets(patient, appointments);
        int expResult = 1;
        int result = appointments.size();
        assertEquals(expResult, result);

    }
}
