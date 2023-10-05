/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class AppointmentHospitalSetterTest {
    
    public AppointmentHospitalSetterTest() {
    }

    /**
     * Test of getHospitalName method, of class AppointmentHospitalSetter.
     */
   /* 

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license 

 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template 

 */ 


  

    /** 

     * Test of getHospitalName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testGetHospitalName() { 

        System.out.println("getHospitalName"); 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setHospitalName("PT-012"); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setHospitalName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testSetHospitalName() { 

        System.out.println("setHospitalName"); 

        String hospitalName = "PT-012"; 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setHospitalName(hospitalName); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getDepartmentName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testGetDepartmentName() { 

        System.out.println("getDepartmentName"); 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setDepartmentName("PT-012"); 

        String result = instance.getDepartmentName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDepartmentName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testSetDepartmentName() { 

        System.out.println("setDepartmentName"); 

        String departmentName = "PT-012"; 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setDepartmentName(departmentName); 

        String result = instance.getDepartmentName(); 

        assertEquals(expResult,result); 

    } 

  

    /** 

     * Test of getClinicName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testGetClinicName() { 

        System.out.println("getClinicName"); 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setClinicName("PT-012"); 

        String result = instance.getClinicName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setClinicName method, of class AppointmentHospitalSetter. 

     */ 

    @Test 

    public void testSetClinicName() { 

        System.out.println("setClinicName"); 

        String clinicName = "PT-012"; 

        AppointmentHospitalSetter instance = new AppointmentHospitalSetter(); 

        String expResult = "PT-012"; 

        instance.setClinicName(clinicName); 

        String result = instance.getClinicName(); 

        assertEquals(expResult,result); 

    } 

     

} 
    

