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
import javax.swing.JOptionPane;

/**
 *
 * @author seanr
 */
public class HospitalUser extends User {

    protected String hospitalID;
    protected String surgeryID;

    public HospitalUser() {
        myConn=getConnection();
    }

    public HospitalUser(String hospitalID, String surgeryID, String UserId, String userFirstName, String userLastName, String userPPSNumber, String userEmail, String userPassword, String userType) {
        super(UserId, userFirstName, userLastName, userPPSNumber, userEmail, userPassword, userType);
        this.hospitalID = hospitalID;
        this.surgeryID = surgeryID;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getSurgeryID() {
        return surgeryID;
    }

    public void setSurgeryID(String surgeryID) {
        this.surgeryID = surgeryID;
    }

    @Override
    public String toString() {
        return super.toString() + "HospitalUser{" + "hospitalID=" + hospitalID + ", surgeryID=" + surgeryID + '}';
    }



    //This method update fields of the user table.
    //If the patient changes fields in the patient table it may need to be updated in the user table also. 
    //if so then this method is called where needed.
    public void changeDataUsersTable(String updateContent, String updateField) {
        try {
            Statement statement = myConn.createStatement();

            statement.execute("Update Users"
                    + " SET User" + updateField + "='" + updateContent + "'"
                    + " WHERE " + "UserEmail" + "='" + userEmail + "';");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e); //error from try
        }//end try catch
    }

    public void setDoctorsFields(String email) {
    }

    //This method stores all the signed in patients  medications prescribed stored in the database
    //Procedures completed are added into the passed in medications prescribed arraylist
    public void storeMedications(Patient patient, ArrayList<MedicationPrescribed> meds) {
        try {
         
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select MedicationDatePrescribed,"
                    + "MedicationDateEnd, "
                    + "Medication.MedicationName,"
                    + "Medication.MedicationBrand, "
                    + "Medication.MedicationSideEffects, "
                    + "Medication.MedicationDose,"
                    + "Doctor.DoctorFirstName,"
                    + "Doctor.DoctorLastName, "
                    + "Medication.MedicationRecommendation, "
                    + " PrescriptionId "
                    + "from MedicationPrescribed "
                    + "INNER JOIN Doctor ON MedicationPrescribed.DoctorId = Doctor.DoctorId "
                    + "INNER JOIN Medication ON MedicationPrescribed.MedicationId = Medication.MedicationId "
                    + "WHERE PatientId ='" + patient.getPatientID() + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            meds.clear();
            while (rs.next()) {
                MedicationPrescribed med = new MedicationPrescribed();
                med.setMedicationDatePrescribed(rs.getString(1));
                med.setMedicationDateEnded(rs.getString(2));
                med.setMedicationName(rs.getString(3));
                med.setMedicationBrand(rs.getString(4));
                med.setMedicationSideEffects(rs.getString(5));
                med.setMedicationDose(rs.getString(6));
                med.setDoctorFirstName(rs.getString(7));
                med.setDoctorLastName(rs.getString(8));
                med.setMedicationRecommendation(rs.getString(9));
                med.setPrescriptionId(rs.getString(10));
                meds.add(med);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //This method stores all the signed in patients procedures completed stored in the database
    //Procedures completed are added into the passed in proecedures compleded arraylist
    public void storeProcedures(Patient patient, ArrayList<ProceduresCompleted> procedures) {
        try {
          
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select Procedures.ProcedureName,"
                    + "Procedures.ProcedureDescription, "
                    + "Hospital.HospitalName,"
                    + "Doctor.DoctorFirstName,"
                    + "Doctor.DoctorLastName, "
                    + "ProcedureCompletedDate, "
                    + "ProcedureCompletedSummary, "
                    + "Procedures.ProcedureRiskLevel, "
                    + "Procedures.ProcedureId, "
                    + "ProcedureCompletedId "
                    + "from ProceduresCompleted "
                    + "INNER JOIN Doctor ON ProceduresCompleted.DoctorId = Doctor.DoctorId "
                    + "INNER JOIN Procedures ON ProceduresCompleted.ProcedureId = Procedures.ProcedureId "
                    + "INNER JOIN Hospital ON ProceduresCompleted.HospitalId = Hospital.HospitalId "
                    + "WHERE PatientId ='" + patient.getPatientID() + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            procedures.clear();
            while (rs.next()) {
                ProceduresCompleted procedureObject = new ProceduresCompleted();
                procedureObject.setProcedureName(rs.getString(1));
                procedureObject.setProcedureDescription(rs.getString(2));
                procedureObject.setHopspitalName(rs.getString(3));
                procedureObject.setDoctorFirstName(rs.getString(4));
                procedureObject.setDoctorLastName(rs.getString(5));
                procedureObject.setProcedureCompletedDate(rs.getString(6));
                procedureObject.setProcedureCompletedSummary(rs.getString(7));
                procedureObject.setProcedureRiskLevel(rs.getString(8));
                procedureObject.setProcedureId(rs.getString(9));
                procedureObject.setProcedureCompletedId(rs.getString(10));

                procedures.add(procedureObject);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    //This method stores all the signed in patients hospital appointments.
    //Query completed referencing the patient id signed in.
    //All appoinments of the signed in patient are loaded into an arraylist.
    public void storeAppointmets(Patient patient, ArrayList<Appointment> appointments) {
        try {

         
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select Hospital.HospitalName, "
                    + "Hospital.HospitalPhoneNumber, "
                    + "Doctor.DoctorFirstName,"
                    + "Doctor.DoctorLastName, "
                    + "AppointmentDate, "
                    + "AppointmentTime, "
                    + "AppointmentSummary, "
                    + "Department.DepartmentId, "
                    + "Clinics.ClinicId, "
                    + "Clinics.ClinicName, "
                    + "AppointmentId "
                    + "from Appointment "
                    + "INNER JOIN Doctor ON Appointment.DoctorId = Doctor.DoctorId "
                    + "INNER JOIN Patient ON Appointment.PatientId = Patient.PatientId "
                    + "INNER JOIN Hospital ON Appointment.HospitalId = Hospital.HospitalId "
                    + "INNER JOIN Department ON Appointment.DepartmentId = Department.DepartmentId "
                    + "INNER JOIN Clinics ON Appointment.ClinicId = Clinics.ClinicId "
                    + "WHERE Patient.PatientId ='" + patient.getPatientID()
                    + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            appointments.clear();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setHospitalName(rs.getString(1));
                appointment.setHospitalPhoneNumber(rs.getString(2));
                appointment.setDoctorFirstName(rs.getString(3));
                appointment.setDoctorLastName(rs.getString(4));
                appointment.setAppointmentDate(rs.getString(5));
                appointment.setAppointmentTime(rs.getString(6));
                appointment.setAppointmentSummary(rs.getString(7));
                appointment.setDepartmentId(rs.getString(8));
                appointment.setClinicId(rs.getString(9));
                appointment.setAppointmentId(rs.getString(11));
                appointment.setClinicName(rs.getString(10));

                appointments.add(appointment);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    //This method stores all the signed in patients surgery appointments.
    //Query completed referencing the patient id signed in.
    //All appoinments of the signed in patient are loaded into an arraylist.
    public void storeSurgeryAppointmets(Patient patient, ArrayList<Appointment> hospitalAppointments) {

        try {
        
            Statement stmt = myConn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "select Surgery.SurgeryName, "
                    + "Surgery.SurgeryPhoneNumber, "
                    + "Doctor.DoctorFirstName,"
                    + "Doctor.DoctorLastName, "
                    + "AppointmentDate, "
                    + "AppointmentTime, "
                    + "AppointmentSummary, "
                    + "AppointmentId "
                    + "from Appointment "
                    + "INNER JOIN Doctor ON Appointment.DoctorId = Doctor.DoctorId "
                    + "INNER JOIN Patient ON Appointment.PatientId = Patient.PatientId "
                    + "INNER JOIN Surgery ON Appointment.SurgeryId = Surgery.SurgeryId "
                    + "WHERE Patient.PatientId ='" + patient.getPatientID()
                    + "';");
            // while loop to iterate through all records of customer table and display them
            // in the text field
            hospitalAppointments.clear();
            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setSurgeryName(rs.getString(1));
                appointment.setSurgeryPhoneNumber(rs.getString(2));
                appointment.setDoctorFirstName(rs.getString(3));
                appointment.setDoctorLastName(rs.getString(4));
                appointment.setAppointmentDate(rs.getString(5));
                appointment.setAppointmentTime(rs.getString(6));
                appointment.setAppointmentSummary(rs.getString(7));
                appointment.setAppointmentId(rs.getString(8));
                hospitalAppointments.add(appointment);

            }

        } catch (SQLException ex) {
            System.out.println(ex);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}
