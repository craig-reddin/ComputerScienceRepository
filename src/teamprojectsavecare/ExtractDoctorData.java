/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author seanr
 */
public class ExtractDoctorData {

    String url ="jdbc:sqlite:c:/mydb/saveCare.db";
    String location = "C:\\Doctor_Data.csv";

    Connection myConn;
    Patient patient;
    User user;
    Doctor doctor;
    //LocalSQLiteDatabase sqlDb;

    int counter = 0;
    String doctorID;
    String hospitalID;
    String department;
    String surgeryID;
    String firstName;
    String lastName;
    String doctorSpecialisation;
    String doctorEmail;
    String doctorPassword;
    String doctorPpsNumber;
    String doctorUserID;

    public ExtractDoctorData() {
        url = "jdbc:sqlite:c:/mydb/saveCare.db";
    }

    // this method is used to ensurethat the extraction of the csv data has not be uploaded 
    //already before cause a primary key error by trying to insert already existing data into the database
    public boolean csvVal() {

        try {

            String value = "";
            getConnection();
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select UserId from Users where UserId = 'US-3999555'"
                    + ";");
            while (rs.next()) {

                System.out.println("hi");
                value = value + rs.getString(1);
                if (value.matches("US-3999555")) {
                    myConn.close();
                    return false;
                }

                
            }
            return true;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;

    }

    //This method is used to load the csv file into the database
    //The csv file consists of existing doctor data existed before the creation of this application ensureing that 
    //doctors information can be tranferred without them having to cfreate an account for tha application
    public void loadCSV() {
        try {
            if (csvVal() == false) {
                System.out.println("Extraction alrady completed");
                return;
            }
            

            myConn.setAutoCommit(false);
            String query = "INSERT INTO Doctor(DoctorId,HospitalId,SurgeryId,DoctorPPS,DoctorFirstName,DoctorLastName,DoctorEmail,DoctorPassword,DoctorSpecialisation,DoctorDepartment) VALUES (?,?,?,?,?,?,?,?,?,?)";
            String query2 = "INSERT INTO Users(UserId,UserEmail,UserPassword,UserType) VALUES (?,?,?,?)";
            PreparedStatement pmt = myConn.prepareStatement(query);
            PreparedStatement pmt2 = myConn.prepareStatement(query2);
            
            if (!new File(location).exists()) {
            System.out.println("Please go to project folder copy and paste csv file to C: Drive");
            return;
        }
            BufferedReader br = new BufferedReader(new FileReader(location));
            int batch = 10;
            String line = "";
            br.readLine();
            //br.readLine is the file equivalent to nextLine reads,ever line will be read and commited once 
            while ((line = br.readLine()) != null) {
                String[] seperator = line.split(",");
                doctorID = seperator[2];
                hospitalID = seperator[0];
                department = seperator[1];
                surgeryID = seperator[3];
                firstName = seperator[4];
                lastName = seperator[5];
                doctorSpecialisation = seperator[6];
                doctorEmail = seperator[7];
                doctorPassword = seperator[8];
                doctorPpsNumber = seperator[9];
                doctorUserID = seperator[10];
                System.out.println(doctorID);

                //deconstructAndDecrementID(newID);
                pmt2.setString(1, doctorUserID);
                pmt2.setString(2, doctorEmail);
                pmt2.setString(3, doctorPassword);
                pmt2.setString(4, "Doctor");
                //doctor table
                pmt.setString(1, doctorID);
                pmt.setString(2, hospitalID);
                pmt.setNull(3, Types.NULL);
                pmt.setString(4, doctorPpsNumber);
                pmt.setString(5, firstName);
                pmt.setString(6, lastName);
                pmt.setString(7, doctorEmail);
                pmt.setString(8, doctorPassword);
                pmt.setString(9, doctorSpecialisation);
                pmt.setString(10, department);

                //user table
                //improves speed of adding elements from the csv to the database 
                pmt2.addBatch();
                pmt.addBatch();

                if (counter % batch == 0) {
                    pmt.executeBatch();
                    pmt2.executeBatch();
                    //batches split each cell sequentially

                }
            }
            //https://www.codejava.net/coding/java-code-example-to-insert-data-from-csv-to-database
            pmt2.executeBatch();
            pmt.executeBatch();
            br.close();

            myConn.commit();
            myConn.close();
            System.out.println("Successfully added information");
            return;
        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e);

        }

    }

    public String setIDCreate(String value) {

        try {
            String query = "";
            String query2 = "";
            String query3 = "";
            String q1 = "select  PatientId FROM Patient ORDER BY PatientId desc LIMIT 1;";
            String q2 = "select  DoctorId FROM Doctor ORDER BY DoctorId desc LIMIT 1;";
            String q3 = "select  SurgeryId FROM Surgery ORDER BY SurgeryId desc LIMIT 1;";
            String q4 = "select  HospitalId FROM Hospital ORDER BY HospitalId desc LIMIT 1;";
            String q5 = "select  UserId FROM Users ORDER BY UserId desc LIMIT 1;";
            // here sakila is database name, root is username and password.... password is
            // set as a variable above.

            if (value.matches("Patient")) {
                query = q1;
                //System.out.println("reached");
            }

            if (value.matches("Doctor")) {
                query = q2;
                query2 = q3;
                query3 = q4;

            }
            if (value.matches("User")) {
                query = q5;

            }

            String id = "";
            getConnection();
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                id = rs.getString(1);
            }
            System.out.println(id + "\n");
            return id;

        } catch (SQLException ex) {
            System.out.println(ex + "\n");

        } catch (Exception ex) {
            System.out.println(ex + "\n");
        }
        return "";
    }

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

}
