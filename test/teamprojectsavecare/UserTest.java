/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package teamprojectsavecare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author craig
 */
public class UserTest {

    public UserTest() {
    }

    /**
     * Test of getUserTableId method, of class User.
     */
    @Test
    public void testGetUserTableId() {
        System.out.println("getUserTableId");
        User instance = new User();
        instance.setUserTableId("PT-001");
        String expResult = "PT-001";
        String result = instance.getUserTableId();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of setUserTableId method, of class User.
//     */

    @Test
    public void testSetUserTableId() {
        System.out.println("setUserTableId");
        String userTableId = "PT-001";

        User instance = new User();
        instance.setUserTableId(userTableId);

        assertEquals(instance.getUserTableId(), userTableId);

    }
//
//    /**
//     * Test of getUserId method, of class User.
//     */

    @Test
    public void testGetUserId() {
        System.out.println("getUserId");

        User instance = new User();
        instance.setUserId("PT-001");
        String expResult = "PT-001";
        String result = instance.getUserId();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of setUserId method, of class User.
//     */

    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        String UserId = "PT-001";
        User instance = new User();
        instance.setUserId(UserId);

        assertEquals(instance.getUserId(), UserId);

    }

    /**
     * Test of getUserType method, of class User.
     */
    @Test
    public void testGetUserType() {
        System.out.println("getUserType");
        User instance = new User();
        instance.setUserType("Patient");
        String expResult = "Patient";
        String result = instance.getUserType();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUserType method, of class User.
     */
    @Test
    public void testSetUserType() {
        System.out.println("setUserType");

        String userType = "Doctor";
        User instance = new User();
        instance.setUserType(userType);
        assertEquals(instance.getUserType(),"Doctor");

    }

    /**
     * Test of getUserFirstName method, of class User.
     */
    @Test
    public void testGetUserFirstName() {
        System.out.println("getUserFirstName");
        User instance = new User();
        String expResult = "Frank";
        instance.setUserFirstName("Frank");
        String result = instance.getUserFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserFirstName method, of class User.
     */
    @Test
    public void testSetUserFirstName() {
        System.out.println("setUserFirstName");
        String userFirstName = "Frank";
        User instance = new User();
        instance.setUserFirstName(userFirstName);
        assertEquals(instance.getUserFirstName(), "Frank");

    }

    /**
     * Test of getUserLastName method, of class User.
     */
    @Test
    public void testGetUserLastName() {
        System.out.println("getUserLastName");
        User instance = new User();
        instance.setUserLastName("Sinatra");
        String expResult = "Sinatra";
        String result = instance.getUserLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUserLastName method, of class User.
     */
    @Test
    public void testSetUserLastName() {
        System.out.println("setUserLastName");
        String userLastName = "Sinatra";
        User instance = new User();
        instance.setUserLastName(userLastName);
        assertEquals(instance.getUserLastName(), "Sinatra");

    }

    /**
     * Test of getUserPPSNumber method, of class User.
     */
    @Test
    public void testGetUserPPSNumber() {
        System.out.println("getUserPPSNumber");
        User instance = new User();
        instance.setUserPPSNumber("8158865U");
        String expResult = "8158865U";
        String result = instance.getUserPPSNumber();
        assertEquals(expResult, result);

    }

    /**
     * Test of setUserPPSNumber method, of class User.
     */
    @Test
    public void testSetUserPPSNumber() {
        System.out.println("setUserPPSNumber");
        String userPPSNumber = "8158865U";
        User instance = new User();
        instance.setUserPPSNumber(userPPSNumber);
        assertEquals(instance.getUserPPSNumber(), "8158865U");

    }

    /**
     * Test of getUserEmail method, of class User.
     */
    @Test
    public void testGetUserEmail() {
        System.out.println("getUserEmail");
        User instance = new User();
        instance.setUserEmail("craig@gg.ie");
        String expResult = "craig@gg.ie";
        String result = instance.getUserEmail();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of setUserEmail method, of class User.
     */
    @Test
    public void testSetUserEmail() {
        System.out.println("setUserEmail");
        String userEmail = "craig@gg.ie";
        User instance = new User();
        instance.setUserEmail(userEmail);
        
        assertEquals(instance.getUserEmail(), "craig@gg.ie");

    }
//
//    /**
//     * Test of getUserPassword method, of class User.
//     */
    @Test
    public void testGetUserPassword() {
        System.out.println("getUserPassword");
        User instance = new User();
        instance.setUserPassword("ThisPassword");
        String expResult = "ThisPassword";
        String result = instance.getUserPassword();
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of setUserPassword method, of class User.
//     */
    @Test
    public void testSetUserPassword() {
        System.out.println("setUserPassword");
        String userPassword = "ThisPassword";
        User instance = new User();
        instance.setUserPassword(userPassword);
        assertEquals(instance.getUserPassword(), "ThisPassword");
        
    }

    /**
     * Test of getMyConn method, of class User.
     */
    @Test
    public void testGetMyConn() {
        System.out.println("getMyConn");
        User instance = new User();
        instance.setMyConn(instance.getConnection());
        Connection result = instance.getMyConn();
        assertNotNull(result);
    }
//
//    /**
//     * Test of setMyConn method, of class User.
//     */
    @Test
    public void testSetMyConn() {
        System.out.println("setMyConn");
        
        User instance = new User();
        instance.setMyConn(instance.getConnection());
        
         assertNotNull(instance.getMyConn());
        
    }

    /**
     * Test of signIn method, of class User.
     */
    // For signing in to the database No emty input are to be checked, the information is validated before this method is run to ensure that
    // that only valid emails formats are passed to the method. This ensures connection to the database is not started unless a email with a valid format in inserted by the user.
    //Validation on incorrect passwiord, email is only negative testing needed and the only positive test completed.
    @Test
    public void testvalidSignIn() {
        System.out.println("Valid signIn");
        String email = "stacys@gmail.ie";
        String password = "StacyStacy";
        User instance = new User();
        instance.getConnection();
        boolean expResult = true;
        boolean result = instance.signIn(email, password);
        assertEquals(expResult, result);
    }

    @Test
    public void testInvalidEmailSignIn() {
        System.out.println("signIn Invalid email test");
        String email = "stacysss@gmail.ie";
        String password = "StacyStacy";
        User instance = new User();
        instance.myConn = instance.getConnection();
        boolean expResult = false;
        boolean result = instance.signIn(email, password);
        assertEquals(expResult, result);

    }

    @Test
    public void testInvalidPasswordSignIn() {
        System.out.println("signIn invalid password test");
        String email = "stacys@gmail.ie";
        String password = "SatcyStac";
        User instance = new User();
        instance.myConn = instance.getConnection();
        boolean expResult = false;
        boolean result = instance.signIn(email, password);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of getConnection method, of class User.
//     */
    @Test
    public void testGetConnection() {
        System.out.println("getConnection - database connection test");
        User instance = new User();
        Connection result = instance.getConnection();
        assertNotNull(result);
    }
//
//    /**
//     * Test of validatePhoneNumber method, of class User.
//     */

    @Test
    public void testValidatePhoneNumberInValidPhoneNumber() {
        System.out.println("validatePhoneNumber - Invalid test");
        String number = "086776776";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validatePhoneNumber(number);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatePhoneNumberValidPhoneNumber() {
        System.out.println("validatePhoneNumber test");
        String number = "0867767763";
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.validatePhoneNumber(number);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of validateEmail method, of class User.
//     */

    @Test
    public void testValidateEmailValidEmail() {
        System.out.println("validateEmail - Valid Email");
        String email = "craig@gg.com";
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateEmailInvalidEmail() {
        System.out.println("validateEmail - Invalid Email, no email domain name ");
        String email = "craig@.com";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateEmailInvalidEmailNoAtSymbol() {
        System.out.println("validateEmail - Invalid Email, no @ symbol");
        String email = "craiggg.com";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateEmailInvalidEmailNoperiods() {
        System.out.println("validateEmail - Invalid Email, no . symbol");
        String email = "craig@ggcom";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validateEmail(email);
        assertEquals(expResult, result);

    }

//
//    /**
//     * Test of validatePassword method, of class User.
//     */
    @Test
    public void testInValidatePasswordNoAtSymbol() {
        System.out.println("validatePassword Invalid -special Character");
        String createPassword = "ThisIsAValidPA55WOrd";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validatePassword(createPassword);
        assertEquals(expResult, result);

    }

    @Test
    public void testInValidatePasswordNoCapitalLetter() {
        System.out.println("validatePassword Invalid - No Capital letter");
        String createPassword = "thisis@validpa55word";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validatePassword(createPassword);
        assertEquals(expResult, result);

    }

    @Test
    public void testInValidatePasswordNoNumbers() {
        System.out.println("validatePassword Invalid - No numbers");
        String createPassword = "thisis@validpAssword";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validatePassword(createPassword);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateVvalidPassword() {
        System.out.println("validate Invalid Password");
        String createPassword = "ThisIsAV@lidPA55WOrd";
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.validatePassword(createPassword);
        assertEquals(expResult, result);

    }
//
//    /**
//     * Test of validateEircode method, of class User.
//     */

    @Test
    public void testValidateEircode() {
        System.out.println("validateEircode Valid Eircode");
        String eircode = "A65F4E2";
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.validateEircode(eircode);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidateEircodeInvalide() {
        System.out.println("validateEircode Invalid Eircode");
        String eircode = "A65F4E";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.validateEircode(eircode);
        assertEquals(expResult, result);
    }
//
//    /**
//     * Test of ppsNumberValidation method, of class User.
//     */

    @Test
    public void testPpsNumberValidation() {
        System.out.println("ppsNumberValidation Invalid PPS");
        String ppsNumber = "75665";
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.ppsNumberValidation(ppsNumber);
        assertEquals(expResult, result);

    }

    @Test
    public void testPpsNumberValidationValidPPS() {
        System.out.println("ppsNumberValidation Valid PPS");
        String ppsNumber = "8168845I";
        User instance = new User();
        boolean expResult = true;
        boolean result = instance.ppsNumberValidation(ppsNumber);
        assertEquals(expResult, result);

    }

//    /**
//     * Test of validateExistingEmail method, of class User.
//     */
    @Test
    public void testValidateExistingEmail() {
        System.out.println("validateExistingEmail");

        String email = "stacys@gmail.ie";
        User instance = new User();
        instance.setMyConn(instance.getConnection());
        boolean expResult = false;
        boolean result = instance.validateExistingEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateExistingEmailNonExisting() {
        System.out.println("validateExistingEmail non-existent email");
        String email = "stacyssss@gmail.ie";
        User instance = new User();
        instance.setMyConn(instance.getConnection());
        boolean expResult = true;
        boolean result = instance.validateExistingEmail(email);
        assertEquals(expResult, result);

    }


    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User();
        instance.setUserFirstName("Frank");
        instance.setUserLastName("Boyle");
        instance.setUserEmail("Frank@gg.ie");
        instance.setUserPPSNumber("45678");
        String expResult = "User{UserId=null, userFirstName=Frank, userLastName=Boyle,"
                + " userPPSNumber=45678, userEmail=Frank@gg.ie, userPassword=null, "
                + "userType=null, url=jdbc:sqlite:c:/mydb/saveCare.db, myConn=null}";
        System.out.println(instance.toString());
        String result = instance.toString();
        assertEquals(expResult, result);

    }
}
