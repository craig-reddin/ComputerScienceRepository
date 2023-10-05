/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author craig
 */
public class Doctor extends HospitalUser {

    private String doctorId;
    private String doctorSpecialisation;
    private String doctorDepartment;

    public Doctor() {

    }

    public Doctor(String doctorId, String doctorSpecialisation, String doctorDepartment, String hospitalID, String surgeryID, String UserId, String userFirstName, String userLastName, String userPPSNumber, String userEmail, String userPassword, String userType) {
        super(hospitalID, surgeryID, UserId, userFirstName, userLastName, userPPSNumber, userEmail, userPassword, userType);
        this.doctorId = doctorId;
        this.doctorSpecialisation = doctorSpecialisation;
        this.doctorDepartment = doctorDepartment;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorSpecialisation() {
        return doctorSpecialisation;
    }

    public void setDoctorSpecialisation(String doctorSpecialisation) {
        this.doctorSpecialisation = doctorSpecialisation;
    }

    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepertment) {
        this.doctorDepartment = doctorDepertment;
    }

    // This method takes in the doctor email 
    //queries the databse and retrieves all doctor fields retrieved]
    //sets the retrived data as the objects fields
    @Override
    public void setDoctorsFields(String email) {

        try {
            //method called to get ensure database connection is open.
           

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select * from Doctor where DoctorEmail='"
                    + email + "';");
            // while loop to iterate through all records of customer table and display them
            while (rs.next()) {

                doctorId = (rs.getString(1));
                hospitalID = (rs.getString(2));
                surgeryID = (rs.getString(3));
                userPPSNumber = (rs.getString(4));
                setUserFirstName(rs.getString(5));
                setUserLastName(rs.getString(6));
                setUserEmail(rs.getString(7));
                setUserPassword(rs.getString(8));
                setDoctorSpecialisation(rs.getString(9));
                setDoctorDepartment(rs.getString(10));
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //this method retrieves all of the medications stored in the database that can be prescibed to patients
    //All medication a retrieved using connstant query
    //Arraylist is passed into method and filled with medication objects on each iteration of the while loop
    public void retrieveMedications(ArrayList<Medication> medication) {
        try {

            

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select MedicationName, MedicationDose,MedicationId from Medication;");
            // while loop to iterate through all records of customer table and display them

            while (rs.next()) {

                Medication med = new Medication();
                med.setMedicationName(rs.getString(1));
                med.setMedicationDose(rs.getString(2));
                med.setMedicationId(rs.getString(3));
                medication.add(med);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //this method retrieves all of the procedure stored in the database that can be completed on patients
    //All procedures a retrieved using connstant query
    //Arraylist is passed into method and filled with procedure objects on each iteration of the while loop
    public void retrieveProcedures(ArrayList<Procedures> procedures) {
        try {

            

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select ProcedureName, ProcedureId from Procedures;");
            // while loop to iterate through all records of customer table and display them
            procedures.clear();
            while (rs.next()) {

                Procedures procedure = new Procedures();
                procedure.setProcedureName(rs.getString(1));
                procedure.setProcedureId(rs.getString(2));
                procedures.add(procedure);
            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //this method is used to insert a new medication prescribed to the databse for the logged in patient
    //The method takes a medication prescribed object and inserts each field value into the insert statement.
    public void presctibeMedication(MedicationPrescribed prescription) {
        try {

            //Statement – Used to execute string-based SQL queries
            Statement statement = myConn.createStatement();
            statement.executeUpdate("INSERT INTO MedicationPrescribed VALUES('"
                    + prescription.getPrescriptionId()
                    + "','" + prescription.getDoctorId()
                    + "','" + prescription.getMedicationId()
                    + "','" + prescription.getPatientId()
                    + "','" + prescription.getMedicationDatePrescribed()
                    + "','" + prescription.getMedicationDateEnded()
                    + "');");

            JOptionPane.showMessageDialog(null, "Medication Added");
            statement.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e); //show message, good for testing, not user friendly 
        } //end try catch
    }

    //This method is used to update and existing prescription
    //A prescribed medication object is passed into the method and the possible values of the existing record are updated
    public void updateExistingPrescription(MedicationPrescribed med) {

        try {
            Statement statement;
            statement = myConn.createStatement();
            statement.execute("Update MedicationPrescribed "
                    + "SET MedicationDatePrescribed='" + med.getMedicationDatePrescribed()
                    + "',MedicationDateEnd='" + med.getMedicationDateEnded() + "'"
                    + ",MedicationId='" + med.getMedicationId() + "' "
                    + " WHERE PrescriptionId='" + med.getPrescriptionId() + "';");

            JOptionPane.showMessageDialog(null, "Medication Updated");
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // This method is used insert a new procedure completed record into the databse.
    //A completed procedure object is passed into the database and the field values of the objects are inserted into the statemtn where neccessarry
    public void registerCompletedProcedure(ProceduresCompleted procedure) {

        try {

            //Statement – Used to execute string-based SQL queries
            Statement statement = myConn.createStatement();
            statement.executeUpdate("INSERT INTO ProceduresCompleted VALUES('"
                    + procedure.getProcedureCompletedId()
                    + "','" + procedure.getPatientId()
                    + "','" + procedure.getProcedureId()
                    + "','" + procedure.getHospitalId()
                    + "','" + procedure.getDoctorId()
                    + "','" + procedure.getProcedureCompletedDate()
                    + "','" + procedure.getProcedureCompletedSummary()
                    + "');");

            JOptionPane.showMessageDialog(null, "Completed Procedure Added");
            

        } catch (Exception e) {
            System.out.println(e);
        } //end try catch
    }

    //This method updates an existing procedure completed record in the database
    //Only the values that would change are inserted into the update statement
    public void updateExistingProcedure(ProceduresCompleted procedure) {

        try {
            Statement statement;
            statement = myConn.createStatement();
            statement.execute("Update ProceduresCompleted "
                    + "SET ProcedureCompletedSummary='" + procedure.getProcedureCompletedSummary()
                    + "',ProcedureCompletedDate='" + procedure.getProcedureCompletedDate() + "'"
                    + ",ProcedureId='" + procedure.getProcedureId() + "'"
                    + " WHERE ProcedureCompletedId='" + procedure.getProcedureCompletedId() + "';");

            JOptionPane.showMessageDialog(null, "Procedure Updated");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    //This method adds retrieves all the clinics that a signed in doctor can book an appointment for.
    //The below query extracts the hospital name, department name and clinics of the department
    //All clinics are loaded into arraylist
    public ArrayList<AppointmentHospitalSetter> doctorsHospitalName(Doctor doc, ArrayList<Clinic> clinic) {
        try {

            ArrayList<AppointmentHospitalSetter> appointment = new ArrayList<>();
            

            Statement stmt = getMyConn().createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select Hospital.HospitalName, Department.DepartmentName,ClinicName"
                    + " from Clinics "
                    + "INNER JOIN Hospital ON Clinics.HospitalId = Hospital.HospitalId "
                    + "INNER JOIN Department ON Clinics.DepartmentId = Department.DepartmentId "
                    + " where Hospital.HospitalId='" + doc.getHospitalID() + "'"
                    + "AND Department.DepartmentName = '" + doc.getDoctorDepartment() + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            System.out.println("hi");
            while (rs.next()) {
                AppointmentHospitalSetter h = new AppointmentHospitalSetter();
                Clinic clinicO = new Clinic();
                clinicO.setClinicName(rs.getString(3));
                clinicO.setHospitalName(rs.getString(1));
                clinicO.setDepartementName(rs.getString(2));

                System.out.println(clinicO.toString());
                h.setHospitalName(rs.getString(1));
                h.setDepartmentName(rs.getString(2));
                h.setClinicName(rs.getString(3));
                appointment.add(h);
                clinic.add(clinicO);
            }
            return appointment;

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    //This method crates a new appoinment. Method can be used by either a suregery doctor or hospital doctor
    //The insert executed depends on if the surgery or hospital id is null. 
    //A doctor will either have ne of those fields set as null as a hospital doctor cant also work in a surgery simultaneously
    public void addNewAPpointment(Appointment appointment) {

        try {
            Statement statement = myConn.createStatement();
            if (appointment.getSurgeryId() == null) {
                //Statement – Used to execute string-based SQL queries

                //maybe statement.execute? executeUpdate also works
                statement.executeUpdate("INSERT INTO Appointment VALUES('"
                        + appointment.getAppointmentId()
                        + "','" + appointment.getPatientId()
                        + "','" + appointment.getHospitalId()
                        + "'," + appointment.getSurgeryId()
                        + ",'" + appointment.getDoctorId()
                        + "','" + appointment.getAdminStaffId()
                        + "','" + appointment.getAppointmentDate()
                        + "','" + appointment.getAppointmentTime()
                        + "','" + appointment.getAppointmentSummary()
                        + "','" + appointment.getDepartmentId()
                        + "','" + appointment.getClinicId()
                        + "');");

                JOptionPane.showMessageDialog(null, "New Hospital Appointment Added");
             
            }
            if (appointment.getHospitalId() == null) {
                //Statement – Used to execute string-based SQL queries

                //maybe statement.execute? executeUpdate also works
                statement.executeUpdate("INSERT INTO Appointment VALUES('"
                        + appointment.getAppointmentId()
                        + "','" + appointment.getPatientId()
                        + "'," + appointment.getHospitalId()
                        + ",'" + appointment.getSurgeryId()
                        + "','" + appointment.getDoctorId()
                        + "','" + appointment.getAdminStaffId()
                        + "','" + appointment.getAppointmentDate()
                        + "','" + appointment.getAppointmentTime()
                        + "','" + appointment.getAppointmentSummary()
                        + "','" + appointment.getDepartmentId()
                        + "','" + appointment.getClinicId()
                        + ");");

                JOptionPane.showMessageDialog(null, "New Surgery Appointment Added");
                
            }

        } catch (Exception e) {
            System.out.println(e);
        } //end try catch
    }

    //This method updates an existing hospital appointment in the database.
    //Appointment oject is passed into the method and the fields of the objects are loaded in to the insert statement where required
    public void updateExistingHospitalAppointment(Appointment appointment) {

        try {
            Statement statement;
            statement = myConn.createStatement();
            statement.execute("Update Appointment "
                    + "SET AppointmentSummary='" + appointment.getAppointmentSummary()
                    + "',AppointmentTime='" + appointment.getAppointmentTime() + "'"
                    + ",AppointmentDate='" + appointment.getAppointmentDate() + "'"
                    + ",ClinicId='" + appointment.getClinicId() + "'"
                    + " WHERE AppointmentId='" + appointment.getAppointmentId() + "';");
            JOptionPane.showMessageDialog(null, "Appointment Updated");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    //This method updates an existing surgery appointment in the database.
    //Appointment oject is passed into the method and the fields of the objects are loaded in to the insert statement where required
    public void updateExistingSurgeryAppointment(Appointment appointment) {
        try {
            Statement statement;
            statement = myConn.createStatement();
            statement.execute("Update Appointment "
                    + "SET AppointmentSummary='" + appointment.getAppointmentSummary()
                    + "',AppointmentTime='" + appointment.getAppointmentTime() + "'"
                    + ",AppointmentDate='" + appointment.getAppointmentDate() + "'"
                    + " WHERE AppointmentId='" + appointment.getAppointmentId() + "';");

            JOptionPane.showMessageDialog(null, "Appointment Updated");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    //This method retrieves the Clinic id and Department Id.
    //The value passes will be a value taken from a combobox and retrieving id's to ensure the correct department and clinic is given to an appointment update.
    public String getClinicDepartmentId(String value, Appointment app) {

        try {

     

            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select ClinicId, Department.DepartmentId from Clinics "
                    + "INNER JOIN Department ON Clinics.DepartmentId = Department.DepartmentId "
                    + " where ClinicName='" + value + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {

                app.setClinicId(rs.getString(1));
                app.setDepartmentId(rs.getString(2));
//                System.out.println(procedure.get(0));

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return value;
    }

    @Override
    public String toString() {
        return super.toString() + "Doctor{" + "doctorId=" + doctorId + ", doctorSpecialisation=" + doctorSpecialisation + ", doctorDepartment=" + doctorDepartment + '}';
    }

    //This method populates a string arraylist with the times of appointments booked for a particular date.
    //This is to ensure appointments are not double booked for doctors
    public void getAllDateAppontments(ArrayList<String> list, String date) {
 
        try {
            //clear the arraylist and ensure when times are loaded then old times are not still populating arraylist
            list.clear();
            
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select AppointmentTime from Appointment "
                    + "INNER JOIN Doctor ON Appointment.DoctorId = Doctor.DoctorId "
                    + " where Doctor.DoctorId ='" + doctorId + "'"
                    + " AND AppointmentDate ='" + date + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}
