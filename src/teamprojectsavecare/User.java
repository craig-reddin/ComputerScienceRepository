package teamprojectsavecare;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author seanr
 */
public class User {

    protected String UserId;
    protected String userFirstName;
    protected String userLastName;
    protected String userPPSNumber;
    protected String userEmail;
    protected String userPassword;
    protected String userType;
    final private String url;
    protected String userTableId;

    protected Connection myConn;
    

    public User() {
        
        url = "jdbc:sqlite:c:/mydb/saveCare.db";
    }

    public User(String UserId, String userFirstName, String userLastName, String userPPSNumber, String userEmail, String userPassword, String userType) {
        this.UserId = UserId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPPSNumber = userPPSNumber;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userType = userType;
        url = "jdbc:sqlite:c:/mydb/saveCare.db";

    }

    public String getUserTableId() {
        return userTableId;
    }

    public void setUserTableId(String userTableId) {
        this.userTableId = userTableId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String UserId) {
        this.UserId = UserId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPPSNumber() {
        return userPPSNumber;
    }

    public void setUserPPSNumber(String userPPSNumber) {
        this.userPPSNumber = userPPSNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Connection getMyConn() {
        return myConn;
    }

    public void setMyConn(Connection myConn) {
        this.myConn = myConn;
    }

    public boolean signIn(String email, String password) {

        try {

            //This method is used sign the user into the application 
            //the email and password entered are passed to the method
            //the email,password and User type is retrieved.
            //if the password and the email match each other then the user is signed into the application by the returned boolean value
            
            
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select  UserEmail, UserType, UserPassword from Users where UserEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                if (email.equals(rs.getString(1)) && password.equals(rs.getString(3))) {
                    userType = rs.getString(2);
                 
                    return true;
                }
                if (!email.equals(rs.getString(1)) || !password.equals(rs.getString(3))) {
                    userType = rs.getString(2);
      
                    return false;
                }
            }

            return false;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;

    }

    // this methd is used to get a Java DataBase Connectivity connection to the MySQL Database.
    //Driver Manager is the library that manages the JDBC drivers
    //JDBC url is entered to detemine the port number to connect to
    //the roo is the username of the database
    // the satabasepassword2 is used connect to the database 
    public Connection getConnection() {
        try {
            myConn = DriverManager.getConnection(url);
            return myConn;
        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }



    //Regex pattern is used to ensure the format entered phone number format is a valid number
    public boolean validatePhoneNumber(String number) {
        if (!number.matches("^(?:\\+353|0)8[35679]\\d{7}$")) {
            return false;
        }

        return true;
    }

    
    
    //https://regex101.com/library/rP6sA9
    //The below method is used to validate an input email
    //regex was used to validat the email
    public boolean validateEmail(String email) {

        String regex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        if (!email.matches(regex)) {
            return false;
        }

        return true;
    }

    //Below is amethod to validate a password entered to ensure that it is at least 8 characters
    //Also must have a number, special caracter and a captital letter.
    //Below is the link where the regex pattern was found
   // https://stackoverflow.com/questions/38118869/password-regex-issue
    public boolean validatePassword(String createPassword) {
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        if (!createPassword.matches(regex)) {
            return false;
        }
        return true;
    }

    
    //The method below validates and eircode using regex.
    //The email must consist of a valid email format.
    //https://help.salesforce.com/s/articleView?id=000390158&type=1
    public boolean validateEircode(String eircode) {

        if (!eircode.matches("^[A-Za-z]\\d{2}\\s?[A-Za-z\\d]{4}$")) {
            return false;
        }

        return true;
    }

    //The below method vvalidates a pps number using regex.
    //https://www3.ntu.edu.sg/home/ehchua/programming/howto/Regexe.html
    public boolean ppsNumberValidation(String ppsNumber) {
        String regex = "\\d{7}[A-Z]{1,2}";

        if (!ppsNumber.matches(regex)) {
            return false;
        }
        return true;
    }

    //This method is used to check if entered email is an existing 
    //validation to ensure when a user is creating account they can register and aready existing email 
    //email is a unique value of the database thus why this validation was needed
    public boolean validateExistingEmail(String email) {

        try {
      

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT UserEmail FROM Users WHERE UserEmail='" + email + "';");
            if (rs.next()) {
                //rs.getString(1);
                if (email.equals(rs.getString(1))) {
                    
          
                    return false;
                }

            }
          
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);

        }

        return true;
    }

    public boolean validateExistingPPSNumber(String ppsNumber) {

        //this method is used to ensure that a pps number ixisting in the databse is not added to the databse
        //The pps is a unique value thus why ths validation is also needed.
        try {
          
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT UserPPSNumber FROM Users WHERE UserPPSNumber='" + ppsNumber + "';");
            if (rs.next()) {
                //rs.getString(1);
                if (ppsNumber.equals(rs.getString(1))) {

                    return false;
                }

            }
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);

        }

        return true;
    }

    @Override
    public String toString() {
        return "User{" + "UserId=" + UserId + ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userPPSNumber=" + userPPSNumber + ", userEmail=" + userEmail + ", userPassword=" + userPassword + ", userType=" + userType + ", url=" + url + ", myConn=" + myConn + '}';
    }

}
