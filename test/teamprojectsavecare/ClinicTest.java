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
public class ClinicTest {
    
    public ClinicTest() {
    }

    /**
     * Test of getClinicName method, of class Clinic.
     */
      /** 

     * Test of getClinicName method, of class Clinic. 

     */ 

    @Test 

    public void testGetClinicName() { 

        System.out.println("getClinicName"); 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setClinicName("PT-012"); 

        String result = instance.getClinicName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setClinicName method, of class Clinic. 

     */ 

    @Test 

    public void testSetClinicName() { 

        System.out.println("setClinicName"); 

        String clinicName = "PT-012"; 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setClinicName(clinicName); 

        String result = instance.getClinicName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of getHospitalName method, of class Clinic. 

     */ 

    @Test 

    public void testGetHospitalName() { 

        System.out.println("getHospitalName"); 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setHospitalName("PT-012"); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setHospitalName method, of class Clinic. 

     */ 

    @Test 

    public void testSetHospitalName() { 

        System.out.println("setHospitalName"); 

        String hospitalName = "PT-012"; 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setHospitalName(hospitalName); 

        String result = instance.getHospitalName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of getDepartementName method, of class Clinic. 

     */ 

    @Test 

    public void testGetDepartementName() { 

        System.out.println("getDepartementName"); 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setDepartementName("PT-012"); 

        String result = instance.getDepartementName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of setDepartementName method, of class Clinic. 

     */ 

    @Test 

    public void testSetDepartementName() { 

        System.out.println("setDepartementName"); 

        String departementName = "PT-012"; 

        Clinic instance = new Clinic(); 

        String expResult = "PT-012"; 

        instance.setDepartementName(departementName); 

        String result = instance.getDepartementName(); 

        assertEquals(expResult, result); 

    } 

  

    /** 

     * Test of toString method, of class Clinic. 

     */ 

    @Test 

    public void testToString() { 

        System.out.println("toString"); 

        Clinic instance = new Clinic(); 

        instance.setClinicName("Clinic A"); 

        String resultString = instance.toString(); 

        boolean result; 

        boolean expResult; 

        //clinic Id set to not null 

        if (resultString.equals("Clinic{clinicName=Clinic A, hospitalName=null, departementName=null}")) { 

            expResult = true; 

            result = true; 

        } else { 

            expResult = false; 

            result = true; 

        } 

        assertEquals(expResult, result); 

        System.out.println(instance.toString()); 

    } 
    
}
