/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author seanr
 */
public class Patient extends HospitalUser {

    private String patientID;
    private String pharmacyID;
    private String patientAddress;
    private String patientEircode;
    private String patientPhoneNumber;
    private String patientNationality;
    private String patientRace;
    private String patientDateOfBirth;
    private String patientMaritalStatus;
    private String patientSex;
    private String patientEmergencyNumber;
    private String patientNumber;
    private String patientBloodType;
    private String patientSmoker;
    private String patientWeeklyActivity;
    private String patientOccupation;
    private String patientHospitalId;
    private String patientSurgeryId;

    public Patient(String patientID, String pharmacyID, String patientAddress, String patientEircode, String patientPhoneNumber, String patientNationality, String patientRace, String patientDateOfBirth, String patientMaritalStatus, String patientSex, String patientEmergencyNumber, String patientNumber, String patientBloodType, String patientSmoker, String patientWeeklyActivity, String patientOccupation, String patientHospitalId, String patientSurgeryId, String hospitalID, String surgeryID, String UserId, String userFirstName, String userLastName, String userPPSNumber, String userEmail, String userPassword, String userType) {
        super(hospitalID, surgeryID, UserId, userFirstName, userLastName, userPPSNumber, userEmail, userPassword, userType);
        this.patientID = patientID;
        this.pharmacyID = pharmacyID;
        this.patientAddress = patientAddress;
        this.patientEircode = patientEircode;
        this.patientPhoneNumber = patientPhoneNumber;
        this.patientNationality = patientNationality;
        this.patientRace = patientRace;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientMaritalStatus = patientMaritalStatus;
        this.patientSex = patientSex;
        this.patientEmergencyNumber = patientEmergencyNumber;
        this.patientNumber = patientNumber;
        this.patientBloodType = patientBloodType;
        this.patientSmoker = patientSmoker;
        this.patientWeeklyActivity = patientWeeklyActivity;
        this.patientOccupation = patientOccupation;
        this.patientHospitalId = patientHospitalId;
        this.patientSurgeryId = patientSurgeryId;
    }

    public Patient() {

    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPharmacyID() {
        return pharmacyID;
    }

    public void setPharmacyID(String pharmacyID) {
        this.pharmacyID = pharmacyID;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientEircode() {
        return patientEircode;
    }

    public void setPatientEircode(String patientEircode) {
        this.patientEircode = patientEircode;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public String getPatientNationality() {
        return patientNationality;
    }

    public void setPatientNationality(String patientNationality) {
        this.patientNationality = patientNationality;
    }

    public String getPatientRace() {
        return patientRace;
    }

    public void setPatientRace(String patientRace) {
        this.patientRace = patientRace;
    }

    public String getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(String patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientMaritalStatus() {
        return patientMaritalStatus;
    }

    public void setPatientMaritalStatus(String patientMaritalStatus) {
        this.patientMaritalStatus = patientMaritalStatus;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientEmergencyNumber() {
        return patientEmergencyNumber;
    }

    public void setPatientEmergencyNumber(String patientemergencyNumber) {
        this.patientEmergencyNumber = patientemergencyNumber;
    }

    public String getPatientHospitalId() {
        return patientHospitalId;
    }

    public void setPatientHospitalId(String patientHospitalId) {
        this.patientHospitalId = patientHospitalId;
    }

    public String getPatientSurgeryId() {
        return patientSurgeryId;
    }

    public void setPatientSurgeryId(String patientSurgeryId) {
        this.patientSurgeryId = patientSurgeryId;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getPatientBloodType() {
        return patientBloodType;
    }

    public void setPatientBloodType(String patientBloodType) {
        this.patientBloodType = patientBloodType;
    }

    public String getPatientSmoker() {
        return patientSmoker;
    }

    public void setPatientSmoker(String patientSmoker) {
        this.patientSmoker = patientSmoker;
    }

    public String getPatientWeeklyActivity() {
        return patientWeeklyActivity;
    }

    public void setPatientWeeklyActivity(String patientWeeklyActivity) {
        this.patientWeeklyActivity = patientWeeklyActivity;
    }

    public String getPatientOccupation() {
        return patientOccupation;
    }

    public void setPatientOccupation(String patientOccupation) {
        this.patientOccupation = patientOccupation;
    }

    @Override
    public String toString() {
        return super.toString() + "Patient{" + "patientID=" + patientID + ", pharmacyID=" + pharmacyID + ", patientAddress=" + patientAddress + ", patientEircode=" + patientEircode + ", patientPhoneNumber=" + patientPhoneNumber + ", patientNationality=" + patientNationality + ", patientRace=" + patientRace + ", patientDateOfBirth=" + patientDateOfBirth + ", patientMaritalStatus=" + patientMaritalStatus + ", patientSex=" + patientSex + ", patientEmergencyNumber=" + patientEmergencyNumber + ", patientNumber=" + patientNumber + ", patientBloodType=" + patientBloodType + ", patientSmoker=" + patientSmoker + ", patientWeeklyActivity=" + patientWeeklyActivity + ", patientOccupation=" + patientOccupation + ", patientHospitalId=" + patientHospitalId + ", patientSurgeryId=" + patientSurgeryId + '}';
    }

    //This method is used to update a patients personal and lyfestyle information.
    //the table column to be udated and the field to be updated are sent into the  and injected into the sql query.
    //We found this idea useful instead of seprerate queries.
  
    public void changeData(String updateContent, String updateField) {
        try {
            Statement statement = myConn.createStatement();

            

            statement.execute("Update Patient"
                    + " SET Patient" + updateField + "='" + updateContent + "'"
                    + " WHERE " + "" + "PatientEmail='" + userEmail + "';");

            //after the database is updated then the object variable must be updateed.
            setPatientVariable(updateField, updateContent);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e); //error from try
        }//end try catch

    }

    //this method updates the patient objects field
    public void setPatientVariable(String updateField, String updateContent) {
        if (updateField.matches("Password")) {
            userPassword = updateContent;

        }
        if (updateField.matches("Sex")) {
            patientSex = updateContent;
        }
        if (updateField.matches("Address")) {
            patientAddress = updateContent;
        }
        if (updateField.matches("PhoneNumber")) {
            patientPhoneNumber = updateContent;
        }
        if (updateField.matches("MaritalStatus")) {
            patientMaritalStatus = updateContent;
        }
    }

    //This methd validates that the mariatal status inserted matches of of the if statement parameters
    public boolean validateMaritalStatus(String maritalStatus) {
        if (maritalStatus.equals("Single") || maritalStatus.equals("Married") || maritalStatus.equals("Seperated") || maritalStatus.equals("Divorced")) {
            return true;
        }
        return false;
    }

    //This method generates a qr code from the patients email and returns it from the method
    public ImageIcon generateQR() {

        try {
            //The link to this code is below
            //https://www.geeksforgeeks.org/how-to-generate-and-read-qr-code-with-java-using-zxing-library/
            // The file path string represents a file path the qrcode will be stored

            String filepath = "qr.png";

            //the charset string represents the character encoding for the qr code encoding
            String charset = "UTF-8";

            //Map stores hints for the QR code generation process
            //We are unsure of how this occurs but it was a nessesarry step for the qrcode generation to occur
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();

            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            //The bitmatrix is a zxing class that represents a matrix of bits which represents the qrcode
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(userEmail.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 500, 500, hintMap);

            // this method is used to store the qrcode in the specified format and stores it in the file location
            MatrixToImageWriter.writeToFile(matrix, filepath.substring(filepath.lastIndexOf('.') + 1), new File(filepath));

            //The ImageIcon is used to load the qr code into 
            //the icn is then returned from the method
            ImageIcon icon = new ImageIcon("qr.png");

            return icon;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public String returnPatientFullName() {

        return getUserFirstName() + " " + getUserLastName();

    }

    public Patient connectToDisplay(Patient patient) {
        try {

            // here sakila is database name, root is username and password.... password is
            // set as a variable above.Patient p = new Patient();
            

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from Patient where PatientEmail='"
                    + patient.getUserEmail() + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {

                patient.setPatientID(rs.getString(1));
                patient.setPatientHospitalId(rs.getString(2));
                patient.setPharmacyID(rs.getString(3));
                patient.setPatientSurgeryId(rs.getString(4));
                patient.setUserPPSNumber(rs.getString(5));
                patient.setUserFirstName(rs.getString(6));
                patient.setUserLastName(rs.getString(7));
                patient.setUserEmail(rs.getString(8));
                patient.setUserPassword(rs.getString(9));
                patient.setPatientAddress(rs.getString(10));
                patient.setPatientEircode(rs.getString(11));
                patient.setPatientPhoneNumber(rs.getString(12));
                patient.setPatientNationality(rs.getString(13));
                patient.setPatientRace(rs.getString(14));
                patient.setPatientDateOfBirth(rs.getString(15));
                patient.setPatientMaritalStatus(rs.getString(16));
                patient.setPatientSex(rs.getString(17));
                patient.setPatientEmergencyNumber(rs.getString(18));
                patient.setPatientNumber(rs.getString(19));
                patient.setPatientBloodType(rs.getString(20));
                patient.setPatientSmoker(rs.getString(21));
                patient.setPatientWeeklyActivity(rs.getString(22));
                patient.setPatientOccupation(rs.getString(23));
                System.out.println(rs.getString(15));

            }

            return patient;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return patient;
    }

    public boolean validateEmergencyNumber(String emergencyNumber) {
        if (!emergencyNumber.matches("^(?:\\+353|0)8[35679]\\d{7}$")) {
            return false;
        }
        return true;
    }

    public boolean validateBloodType(String bloodType) {
        String[] validBloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

        for (String validType : validBloodTypes) {
            if (bloodType.equalsIgnoreCase(validType)) {
                return true;
            }
        }
        return false;
    }

    public boolean validateSmoke(String Smoker) {
        String regex = "^(?i)(never|(current|former)\\s+\\d+\\s*.*)$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Smoker);

        return matcher.matches();
    }

    public boolean validateWeeklyActivity(String weeklyActivity) {
        String regex = "^(?i)(sedentary|light|moderate|vigorous)\\s?\\d+.*$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(weeklyActivity);

        return matcher.matches();
    }

    public boolean validateOccupation(String occupation) {
        if (!occupation.matches("^[a-zA-Z\\s]+$")) {
            return false;
        }
        return true;
    }

}
