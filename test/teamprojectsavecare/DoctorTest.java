/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author craig
 */
public class DoctorTest {

    public DoctorTest() {

    }

    /**
     * *
     * Test of getDoctorId method, of class Doctor. *
     */
    @Test

    public void testGetDoctorId() {

        System.out.println("getDoctorId");

        Doctor instance = new Doctor();

        String expResult = "DA-3999999";

        instance.setDoctorId(expResult);

        String result = instance.getDoctorId();

        assertEquals(expResult, result);

    }

    /**
     * *
     * Test of setDoctorId method, of class Doctor. *
     */
    @Test

    public void testSetDoctorId() {

        System.out.println("setDoctorId");

        String doctorId = "DA-1122";

        Doctor instance = new Doctor();

        instance.setDoctorId(doctorId);

        assertEquals("DA-1122", doctorId);

    }

//    /** 
//     * Test of getDoctorSpecialisation method, of class Doctor. 
//     */ 
    @Test

    public void testGetDoctorSpecialisation() {

        System.out.println("getDoctorSpecialisation");

        Doctor instance = new Doctor();

        String expResult = "General Practitioner";

        instance.setDoctorSpecialisation(expResult);

        String result = instance.getDoctorSpecialisation();

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of setDoctorSpecialisation method, of class Doctor. 
//     */ 
    @Test

    public void testSetDoctorSpecialisation() {

        System.out.println("setDoctorSpecialisation");

        String doctorSpecialisation = "General Practitioner";

        Doctor instance = new Doctor();

        instance.setDoctorSpecialisation(doctorSpecialisation);

        assertEquals("General Practitioner", doctorSpecialisation);

    }

//    /** 
//     * Test of getDoctorDepartment method, of class Doctor. 
//     */ 
    @Test

    public void testGetDoctorDepartment() {

        System.out.println("getDoctorDepartment");

        Doctor instance = new Doctor();

        String expResult = "Cardiology";

        instance.setDoctorDepartment(expResult);

        String result = instance.getDoctorDepartment();

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of setDoctorDepartment method, of class Doctor. 
//     */ 
    @Test

    public void testSetDoctorDepartment() {

        System.out.println("setDoctorDepartment");

        String doctorDepertment = "Cardiology";

        Doctor instance = new Doctor();

        instance.setDoctorDepartment(doctorDepertment);

        assertEquals("Cardiology", doctorDepertment);

    }

//    /** 
//     * Test of setDoctorsFields method, of class Doctor. 
//     */ 
    @Test

    public void testSetDoctorsFields() {

        System.out.println("setDoctorsFields");

        String email = "Thomas@beaumont.ie";

        Doctor instance = new Doctor();

        instance.setMyConn(instance.getConnection());

        instance.setUserEmail(email);

        String firstName = "Thomas";

        String result = instance.getUserFirstName();

        assertEquals(result, firstName);

    }

//    /** 
//     * Test of retrieveMedications method, of class Doctor. 
//     */ 
    @Test

    public void testRetrieveMedications() {

        System.out.println("retrieveMedications");

        ArrayList<Medication> medication = new ArrayList<>();

        Doctor instance = new Doctor();

        instance.setMyConn(instance.getConnection());

        instance.retrieveMedications(medication);

        int expResult = 2;

        int result = medication.size();

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of retrieveProcedures method, of class Doctor. 
//     */ 
    @Test

    public void testRetrieveProcedures() {

        System.out.println("retrieveProcedures");

        ArrayList<Procedures> procedures = new ArrayList<>();

        Doctor instance = new Doctor();

        instance.setMyConn(instance.getConnection());

        instance.retrieveProcedures(procedures);

        int expResult = 2;

        int result = procedures.size();

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of presctibeMedication method, of class Doctor. 
//     */ 
    @Test
    public void testPresctibeMedication() {

        try {

            System.out.println("presctibeMedication");
            MedicationPrescribed prescription = new MedicationPrescribed();
            prescription.setDoctorId("DA-4000000");
            prescription.setPrescriptionId("PA-3000000");
            prescription.setMedicationId("MA-4000000");
            prescription.setPatientId("PT-4000000");
            prescription.setMedicationDatePrescribed("21/04/2023");
            prescription.setMedicationDateEnded("22/04/2023");
            Doctor instance = new Doctor();

            instance.setMyConn(instance.getConnection());

            instance.presctibeMedication(prescription);

            boolean expResult = true;

            boolean result = false;

            Statement stmt = instance.getMyConn().createStatement();

            ResultSet rs = stmt.executeQuery(
                    "select  PrescriptionId from MedicationPrescribed where PrescriptionId='"
                    + "PA-3000000" + "';");

            // while loop to iterate through all records of customer table and display them 
            // in the text field 
            while (rs.next()) {

                if ("PA-3000000".equals(rs.getString(1))) {

                    result = true;

                }

            }

            assertEquals(expResult, result);

        } catch (SQLException ex) {

            System.out.println(ex);

        } catch (Exception ex) {

            System.out.println(ex);

        }

    }

//    /** 
//     * Test of updateExistingPrescription method, of class Doctor. 
//     */ 
    @Test

    public void testUpdateExistingPrescription() {

        try {

            System.out.println("updateExistingPrescription");

            MedicationPrescribed med = new MedicationPrescribed();

            Doctor instance = new Doctor();

            String newMedicationDay = "24/05/2001";

            med.setMedicationDatePrescribed("24/05/2001");

            med.setMedicationDateEnded("25/11/2011");

            med.setMedicationId("MA-4000000");

            med.setPrescriptionId("MP-4000000");

            med.setDoctorId("DA-4000000");

            med.setPatientId("PT-4000000");

            instance.setMyConn(instance.getConnection());
            instance.updateExistingPrescription(med);
            boolean result = false;
            boolean expResult = true;
            instance.updateExistingPrescription(med);
            Statement stmt = instance.getMyConn().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select MedicationDatePrescribed from MedicationPrescribed"
                    + " where MedicationDatePrescribed='"
                    + newMedicationDay + "';");

            while (rs.next()) {

                if ("24/05/2001".equals(rs.getString(1))) {

                    result = true;
                }
            }
            assertEquals(expResult, result);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

//    /** 
//     * Test of registerCompletedProcedure method, of class Doctor. 
//     */ 
    @Test

    public void testRegisterCompletedProcedure() {

        System.out.println("registerCompletedProcedure");

        try {

            ProceduresCompleted procedure = new ProceduresCompleted();
            Doctor instance = new Doctor();
            procedure.setPatientId("PT-4000000");
            procedure.setHospitalId("");
            procedure.setProcedureCompletedId("PC-3555555");
            procedure.setProcedureId("PR-4000000");
            procedure.setDoctorId("DA-4000000");
            procedure.setProcedureCompletedDate("14/02/2023");
            procedure.setProcedureCompletedSummary("Procedure was successful. Patient "
                    + "reacted well to treatment");
            instance.setMyConn(instance.getConnection());
            boolean expResult = true;
            boolean result = false;
            instance.registerCompletedProcedure(procedure);
            Statement stmt = instance.getMyConn().createStatement();

            ResultSet rs = stmt.executeQuery(
                    "select ProcedureCompletedId from ProceduresCompleted where "
                    + "ProcedureCompletedId='"
                    + "PC-3555555" + "';");

            // while loop to iterate through all records of customer table and display them 
            // in the text field 
            while (rs.next()) {

                if ("PC-3555555".equals(rs.getString(1))) {

                    result = true;

                }

            }

            assertEquals(expResult, result);

        } catch (Exception e) {

            System.out.println(e);

        }

    }

//    /** 
//     * Test of updateExistingProcedure method, of class Doctor. 
//     */ 
    @Test

    public void testUpdateExistingProcedure() {

        System.out.println("updateExistingProcedure");

        try {

            ProceduresCompleted procedure = new ProceduresCompleted();
            Doctor instance = new Doctor();
            instance.setMyConn(instance.getConnection());
            String newProcedureSummary = "Procedure was successful. Patient reacted well to treatment";
            procedure.setProcedureCompletedSummary("Procedure was successful. Patient reacted well to treatment");
            procedure.setProcedureCompletedDate("25/04/2023");
            procedure.setProcedureId("PR-4000000");
            procedure.setProcedureCompletedId("PC-4000000");
            instance.updateExistingProcedure(procedure);
            Statement stmt = instance.getMyConn().createStatement();

            boolean expResult = true;

            boolean result = false;

            ResultSet rs = stmt.executeQuery(
                    "select ProcedureCompletedSummary from ProceduresCompleted  where ProcedureCompletedSummary='"
                    + newProcedureSummary + "';");

            while (rs.next()) {

                if ("Procedure was successful. Patient reacted well to treatment".equals(rs.getString(1))) {

                    result = true;

                }

            }

            assertEquals(expResult, result);

        } catch (SQLException ex) {

            System.out.println(ex);

        } catch (Exception ex) {

            System.out.println(ex);

        }

    }

//    } 
//    /** 
//     * Test of doctorsHospitalName method, of class Doctor. 
//     */ 
    @Test

    public void testDoctorsHospitalName() {

        System.out.println("doctorsHospitalName");

        ArrayList<Clinic> clinic = new ArrayList<>();

        Doctor instance = new Doctor();
        instance.setHospitalID("HH-4000000");

        instance.setDoctorDepartment("Cardiology");

        int expResult = 3;

        int result = instance.doctorsHospitalName(instance, clinic).size();

        assertEquals(expResult, result);

    }

// 
////    /** 
////     * Test of addNewAPpointment method, of class Doctor. 
////     */ 
    @Test

    public void testAddNewAPpointment() {

        System.out.println("addNewAPpointment");

        try {
            Appointment appointment = new Appointment();
            Doctor instance = new Doctor();
            instance.setMyConn(instance.getConnection());
            String newDate = "21/02/2021";
            appointment.setPatientId("PT-4000000");
            appointment.setHospitalId("HH-4000000");
            appointment.setSurgeryId(null);
            appointment.setDoctorId("DA-4000000");
            appointment.setAdminStaffId("AS-4000000");
            appointment.setAppointmentDate("21/02/2021");
            appointment.setAppointmentTime("16.15");
            appointment.setAppointmentId("AP-399");
            appointment.setAppointmentSummary("The patient healing very well to Cardiac "
                    + "Ablation procedue. Appointment made to be made for in 18 months "
                    + "time. Tube integrity obsevation required.");
            appointment.setDepartmentId("DE-4000000");
            appointment.setClinicId("CA-3999999");
            instance.addNewAPpointment(appointment);
            boolean expResult = true;

            boolean result = false;

            Statement stmt = instance.getMyConn().createStatement();

            ResultSet rs = stmt.executeQuery(
                    "select AppointmentDate from Appointment where AppointmentDate='"
                    + newDate + "';");
            while (rs.next()) {
                if ("21/02/2021".equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expResult, result);

        } catch (Exception e) {

            System.out.println(e);

        }

    }
//    /** 
//     * Test of updateExistingHospitalAppointment method, of class Doctor. 
//     */ 

    @Test

    public void testUpdateExistingHospitalAppointment() {

        System.out.println("updateExistingHospitalAppointment");

        try {

            Appointment appointment = new Appointment();

            Doctor instance = new Doctor();

            instance.setMyConn(instance.getConnection());

            String newAppointmentDate = "The patient healing very well to Cardiac Ablation procedue."
                    + " Appointment made to be made for in 18 months time. Tube integrity obsevation required.";

            appointment.setAppointmentTime("16.15");

            appointment.setAppointmentDate("21/02/2021");

            appointment.setClinicId("CA-3999999");

            appointment.setAppointmentId("AP-3999898");

            appointment.setAppointmentSummary("The patient healing very well to Cardiac Ablation procedue."
                    + " Appointment made to be made for in 18 months time. Tube integrity obsevation required.");

            instance.updateExistingHospitalAppointment(appointment);

            Statement stmt = instance.getMyConn().createStatement();

            boolean expResult = true;

            boolean result = false;

            ResultSet rs = stmt.executeQuery(
                    "select AppointmentSummary from Appointment  where AppointmentSummary='"
                    + newAppointmentDate + "';");

            while (rs.next()) {

                if ("The patient healing very well to Cardiac Ablation procedue. Appointment made to be made for in 18 months time. Tube integrity obsevation required.".equals(rs.getString(1))) {

                    result = true;

                }

            }

            assertEquals(expResult, result);

        } catch (SQLException ex) {

            System.out.println(ex);

        } catch (Exception ex) {

            System.out.println(ex);

        }

    }

//    /** 
//     * Test of updateExistingSurgeryAppointment method, of class Doctor. 
//     */ 
    @Test

    public void testUpdateExistingSurgeryAppointment() {

        System.out.println("updateExistingSurgeryAppointment");

        try {

            Appointment appointment = new Appointment();

            Doctor instance = new Doctor();
            instance.setMyConn(instance.getConnection());
            String newAppointmentDate = "16/03/2021";
            appointment.setAppointmentSummary("Patient complaining of chest pains. Refering to cardiac"
                    + " secialist in Beaumont hospital. Urgent attention required");
            appointment.setAppointmentDate("16/03/2021");
            appointment.setAppointmentId("AP-4000000");
            appointment.setAppointmentTime("13.00");
            appointment.setAdminStaffId("AS-3999999");
            appointment.setSurgeryId("SA-4000000");
            appointment.setPatientId("PT-4000000");
            appointment.setHospitalId(null);
            appointment.setDepartmentId(null);
            appointment.setClinicId(null);
            instance.updateExistingSurgeryAppointment(appointment);
            Statement stmt = instance.getMyConn().createStatement();
            boolean expResult = true;
            boolean result = false;
            ResultSet rs = stmt.executeQuery(
                    "select AppointmentDate from Appointment  where AppointmentDate='"
                    + newAppointmentDate + "';");
            while (rs.next()) {
                if ("16/03/2021".equals(rs.getString(1))) {
                    result = true;
                }
            }
            assertEquals(expResult, result);
        } catch (SQLException ex) {

            System.out.println(ex);

        } catch (Exception ex) {

            System.out.println(ex);

        }

    }

// 
//    /** 
//     * Test of getClinicDepartmentId method, of class Doctor. 
//     */ 
    @Test

    public void testGetClinicDepartmentId() {

        System.out.println("getClinicDepartmentId");

        String value = "DE-3999999";

        Appointment app = new Appointment();

        Doctor instance = new Doctor();

        instance.setMyConn(instance.getConnection());

        String expResult = "DE-3999999";

        String result = instance.getClinicDepartmentId(value, app);

        assertEquals(expResult, result);

    }

//    /** 
//     * Test of getAllDateAppontments method, of class Doctor. 
//     */ 
    @Test

    public void testGetAllDateAppontments() {

        System.out.println("getAllDateAppontments");

        ArrayList<String> list = new ArrayList<>();

        String date = "21/02/2021";

        Doctor instance = new Doctor();

        instance.setDoctorId("DA-4000000");

        instance.setMyConn(instance.getConnection());

        instance.getAllDateAppontments(list, date);

        int expResult = 2;

        int result = list.size();

        assertEquals(expResult, result);

    }

}
