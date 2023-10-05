/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class AppointmentTest {
    
    public AppointmentTest() {
    }

    /** 

     * Test of getClinicName method, of class Appointment. 

     */ 

    @Test 

    public void testGetClinicName() { 

        System.out.println("getClinicName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setClinicName("PT-012"); 

        String result = instance.getClinicName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setClinicName method, of class Appointment. 

     */ 

    @Test 

    public void testSetClinicName() { 

        System.out.println("setClinicName"); 

        String clinicName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setClinicName(clinicName); 

        String result = instance.getClinicName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getClinicId method, of class Appointment. 

     */ 

    @Test 

    public void testGetClinicId() { 

        System.out.println("getClinicId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setClinicId("PT-012"); 

        String result = instance.getClinicId(); 

        assertEquals(expResult, result); 

         

    } 

  

    /** 

     * Test of setClinicId method, of class Appointment. 

     */ 

    @Test 

    public void testSetClinicId() { 

        System.out.println("setClinicId"); 

        String clinicId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setClinicId(clinicId); 

        String result = instance.getClinicId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDepartmentId method, of class Appointment. 

     */ 

    @Test 

    public void testGetDepartmentId() { 

        System.out.println("getDepartmentId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDepartmentId("PT-012"); 

        String result = instance.getDepartmentId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDepartmentId method, of class Appointment. 

     */ 

    @Test 

    public void testSetDepartmentId() { 

        System.out.println("setDepartmentId"); 

        String departmentId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDepartmentId(departmentId); 

        String result = instance.getDepartmentId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDepartmentName method, of class Appointment. 

     */ 

    @Test 

    public void testGetDepartmentName() { 

        System.out.println("getDepartmentName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDepartmentName("PT-012"); 

        String result = instance.getDepartmentName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDepartmentName method, of class Appointment. 

     */ 

    @Test 

    public void testSetDepartmentName() { 

        System.out.println("setDepartmentName"); 

        String departmentName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDepartmentName(departmentName); 

        String result = instance.getDepartmentName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getClinic method, of class Appointment. 

     */ 

    @Test 

    public void testGetClinic() { 

        System.out.println("getClinic"); 

        Appointment instance = new Appointment(); 

        Clinic clinicA = new Clinic(); 

        clinicA.setClinicName("Clinic A"); 

        clinicA.setDepartementName("Clinic West"); 

        clinicA.setHospitalName("JioLet"); 

         

        Clinic clinicB = new Clinic(); 

        clinicB.setClinicName("Clinic B"); 

        clinicB.setDepartementName("Clinic East"); 

        clinicB.setHospitalName("Siolet"); 

         

         

        ArrayList<Clinic> expResult = new ArrayList<>(); 

        expResult.add(clinicA); 

        expResult.add(clinicB); 

         

        instance.setClinic(expResult); //setting clinic array list for the appointment instance 

         

        ArrayList<Clinic> result = instance.getClinic(); 

        assertEquals(expResult, result); 

    } 

    /** 

     * Test of setClinic method, of class Appointment. 

     */ 

    @Test 

    public void testSetClinic() { 

        System.out.println("getClinic"); 

        Appointment instance = new Appointment(); 

        Clinic clinicA = new Clinic(); 

        clinicA.setClinicName("Clinic A"); 

        clinicA.setDepartementName("Clinic West"); 

        clinicA.setHospitalName("JioLet"); 

         

        Clinic clinicB = new Clinic(); 

        clinicB.setClinicName("Clinic B"); 

        clinicB.setDepartementName("Clinic East"); 

        clinicB.setHospitalName("Siolet"); 

         

         

        ArrayList<Clinic> expResult = new ArrayList<>(); 

        expResult.add(clinicA); 

        expResult.add(clinicB); 

         

        instance.setClinic(expResult); //setting clinic array list for the appointment instance 

         

        ArrayList<Clinic> result = instance.getClinic(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of getAppointmentId method, of class Appointment. 

     */ 

    @Test 

    public void testGetAppointmentId() { 

        System.out.println("getAppointmentId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentId("PT-012"); 

        String result = instance.getAppointmentId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAppointmentId method, of class Appointment. 

     */ 

    @Test 

    public void testSetAppointmentId() { 

        System.out.println("setAppointmentId"); 

        String appointmentId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentId(appointmentId); 

        String result = instance.getAppointmentId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getPatientId method, of class Appointment. 

     */ 

    @Test 

    public void testGetPatientId() { 

        System.out.println("getPatientId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setPatientId("PT-012"); 

        String result = instance.getPatientId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setPatientId method, of class Appointment. 

     */ 

    @Test 

    public void testSetPatientId() { 

        System.out.println("setPatientId"); 

        String patientId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setPatientId(patientId); 

        String result = instance.getPatientId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getHospitalId method, of class Appointment. 

     */ 

    @Test 

    public void testGetHospitalId() { 

        System.out.println("getHospitalId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalId("PT-012"); 

        String result = instance.getHospitalId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setHospitalId method, of class Appointment. 

     */ 

    @Test 

    public void testSetHospitalId() { 

        System.out.println("setHospitalId"); 

        String hospitalId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalId(hospitalId); 

        String result = instance.getHospitalId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getHospitalName method, of class Appointment. 

     */ 

    @Test 

    public void testGetHospitalName() { 

        System.out.println("getHospitalName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalName("PT-012"); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setHospitalName method, of class Appointment. 

     */ 

    @Test 

    public void testSetHospitalName() { 

        System.out.println("setHospitalName"); 

        String hospitalName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalName(hospitalName); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getSurgeryId method, of class Appointment. 

     */ 

    @Test 

    public void testGetSurgeryId() { 

        System.out.println("getSurgeryId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryId("PT-012"); 

        String result = instance.getSurgeryId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setSurgeryId method, of class Appointment. 

     */ 

    @Test 

    public void testSetSurgeryId() { 

        System.out.println("setSurgeryId"); 

        String surgeryId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryId(surgeryId); 

        String result = instance.getSurgeryId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDoctorId method, of class Appointment. 

     */ 

    @Test 

    public void testGetDoctorId() { 

        System.out.println("getDoctorId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorId("PT-012"); 

        String result = instance.getDoctorId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDoctorId method, of class Appointment. 

     */ 

    @Test 

    public void testSetDoctorId() { 

        System.out.println("setDoctorId"); 

        String doctorId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorId(doctorId); 

        String result = instance.getDoctorId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getSurgeryName method, of class Appointment. 

     */ 

    @Test 

    public void testGetSurgeryName() { 

        System.out.println("getSurgeryName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryName("PT-012"); 

        String result = instance.getSurgeryName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setSurgeryName method, of class Appointment. 

     */ 

    @Test 

    public void testSetSurgeryName() { 

        System.out.println("setSurgeryName"); 

        String surgeryName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryName(surgeryName); 

        String result = instance.getSurgeryName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDoctorFirstName method, of class Appointment. 

     */ 

    @Test 

    public void testGetDoctorFirstName() { 

        System.out.println("getDoctorFirstName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorFirstName("PT-012"); 

        String result = instance.getDoctorFirstName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDoctorFirstName method, of class Appointment. 

     */ 

    @Test 

    public void testSetDoctorFirstName() { 

        System.out.println("setDoctorFirstName"); 

        String doctorFirstName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorFirstName(doctorFirstName); 

        String result = instance.getDoctorFirstName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDoctorLastName method, of class Appointment. 

     */ 

    @Test 

    public void testGetDoctorLastName() { 

        System.out.println("getDoctorLastName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorLastName("PT-012"); 

        String result = instance.getDoctorLastName(); 

        assertEquals(expResult, result); 

    } 
    /** 

     * Test of setDoctorLastName method, of class Appointment. 

     */ 

    @Test 

    public void testSetDoctorLastName() { 

        System.out.println("setDoctorLastName"); 

        String doctorLastName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setDoctorLastName(doctorLastName); 

        String result = instance.getDoctorLastName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAdminFirstName method, of class Appointment. 

     */ 

    @Test 

    public void testGetAdminFirstName() { 

        System.out.println("getAdminFirstName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminFirstName("PT-012"); 

        String result = instance.getAdminFirstName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAdminFirstName method, of class Appointment. 

     */ 

    @Test 

    public void testSetAdminFirstName() { 

        System.out.println("setAdminFirstName"); 

        String adminFirstName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminFirstName(adminFirstName); 

        String result = instance.getAdminFirstName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAdminLastName method, of class Appointment. 

     */ 

    @Test 

    public void testGetAdminLastName() { 

        System.out.println("getAdminLastName"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminLastName("PT-012"); 

        String result = instance.getAdminLastName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAdminLastName method, of class Appointment. 

     */ 

    @Test 

    public void testSetAdminLastName() { 

        System.out.println("setAdminLastName"); 

        String adminLastName = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminLastName(adminLastName); 

        String result = instance.getAdminLastName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAdminStaffId method, of class Appointment. 

     */ 

    @Test 

    public void testGetAdminStaffId() { 

        System.out.println("getAdminStaffId"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminStaffId("PT-012"); 

        String result = instance.getAdminStaffId(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAdminStaffId method, of class Appointment. 

     */ 

    @Test 

    public void testSetAdminStaffId() { 

        System.out.println("setAdminStaffId"); 

        String adminStaffId = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAdminStaffId(adminStaffId); 

        String result = instance.getAdminStaffId(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAppointmentDate method, of class Appointment. 

     */ 

    @Test 

    public void testGetAppointmentDate() { 

        System.out.println("getAppointmentDate"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentDate("PT-012"); 

        String result = instance.getAppointmentDate(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAppointmentDate method, of class Appointment. 

     */ 

    @Test 

    public void testSetAppointmentDate() { 

        System.out.println("setAppointmentDate"); 

        String appointmentDate = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentDate(appointmentDate); 

        String result = instance.getAppointmentDate(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAppointmentTime method, of class Appointment. 

     */ 

    @Test 

    public void testGetAppointmentTime() { 

        System.out.println("getAppointmentTime"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentTime("PT-012"); 

        String result = instance.getAppointmentTime(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAppointmentTime method, of class Appointment. 

     */ 

    @Test 

    public void testSetAppointmentTime() { 

        System.out.println("setAppointmentTime"); 

        String appointmentTime = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentTime(appointmentTime); 

        String result = instance.getAppointmentTime(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getAppointmentSummary method, of class Appointment. 

     */ 

    @Test 

    public void testGetAppointmentSummary() { 

        System.out.println("getAppointmentSummary"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentSummary("PT-012"); 

        String result = instance.getAppointmentSummary(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setAppointmentSummary method, of class Appointment. 

     */ 

    @Test 

    public void testSetAppointmentSummary() { 

        System.out.println("setAppointmentSummary"); 

        String appointmentSummary = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setAppointmentSummary(appointmentSummary); 

        String result = instance.getAppointmentSummary(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getSurgeryPhoneNumber method, of class Appointment. 

     */ 

    @Test 

    public void testGetSurgeryPhoneNumber() { 

        System.out.println("getSurgeryPhoneNumber"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryPhoneNumber("PT-012"); 

        String result = instance.getSurgeryPhoneNumber(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setSurgeryPhoneNumber method, of class Appointment. 

     */ 

    @Test 

    public void testSetSurgeryPhoneNumber() { 

        System.out.println("setSurgeryPhoneNumber"); 

        String surgeryPhoneNumber = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setSurgeryPhoneNumber(surgeryPhoneNumber); 

        String result = instance.getSurgeryPhoneNumber(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getHospitalPhoneNumber method, of class Appointment. 

     */ 

    @Test 

    public void testGetHospitalPhoneNumber() { 

        System.out.println("getHospitalPhoneNumber"); 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalPhoneNumber("PT-012"); 

        String result = instance.getHospitalPhoneNumber(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setHospitalPhoneNumber method, of class Appointment. 

     */ 

    @Test 

    public void testSetHospitalPhoneNumber() { 

        System.out.println("setHospitalPhoneNumber"); 

        String hospitalPhoneNumber = "PT-012"; 

        Appointment instance = new Appointment(); 

        String expResult = "PT-012"; 

        instance.setHospitalPhoneNumber(hospitalPhoneNumber); 

        String result = instance.getHospitalPhoneNumber(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of toString method, of class Appointment. 

     */ 

        @Test 

    public void testToString() { 

        System.out.println("toString"); 

        Appointment instance = new Appointment(); 

        instance.setAppointmentId("AP-3999999"); 

        instance.setPatientId("PT-4000000"); 

        instance.setHospitalId("HH-4000000"); 

        instance.setSurgeryId(null); 

        instance.setDoctorId("DA-4000000"); 

        instance.setAdminStaffId("AS-4000000"); 

        instance.setAppointmentDate("21/02/2021"); 

        instance.setAppointmentTime("16.15"); 

        instance.setAppointmentSummary("The patient healing very well to Cardiac Ablation procedue. Appointment made to be made for in 18 months time. Tube integrity obsevation required."); 

        instance.setDepartmentId("DE-4000000"); 

        instance.setClinicId("CA-3999999"); 

        String resultString = instance.toString(); 

        boolean result; 

        boolean expResult; 

        if(resultString.equals("Appointment{appointmentId=AP-3999999, patientId=PT-4000000, hospitalId=HH-4000000, hospitalName=null, surgeryId=null, doctorId=DA-4000000, surgeryName=null, doctorFirstName=null, doctorLastName=null, adminFirstName=null, adminLastName=null, adminStaffId=AS-4000000, appointmentDate=21/02/2021, appointmentTime=16.15, appointmentSummary=The patient healing very well to Cardiac Ablation procedue. Appointment made to be made for in 18 months time. Tube integrity obsevation required., surgeryPhoneNumber=null, hospitalPhoneNumber=null}")){ 

            expResult = true; 

            result = true; 

        } else{ 

            expResult = false; 

            result = true; 

        } 

        assertEquals(expResult, result); 

    } 
    
}
