package teamprojectsavecare;

import java.util.ArrayList;

/**
 *
 * @author craig
 */


//This Class consists of a constructor  and getters and setter of each fiels.
//The getter and setter are used to sccess the crivate variables without having direct access to them


public class Appointment {
    
    private String appointmentId;
    private String patientId;
    private String hospitalId;
    private String hospitalName;
    private String surgeryId;
    private String doctorId;
    private String surgeryName;
    private String doctorFirstName;
    private String doctorLastName;
    private String adminFirstName;
    private String adminLastName;
    private String adminStaffId;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentSummary;
    private String surgeryPhoneNumber;
    private String hospitalPhoneNumber;
    private String departmentName;
    private String clinicId;
    private String clinicName;
    private String departmentId;
    private ArrayList <Clinic> clinic;

    public Appointment(String appointmentId, String patientId, String hospitalId, String hospitalName, String surgeryId, String doctorId, String surgeryName, String doctorFirstName, String doctorLastName, String adminFirstName, String adminLastName, String adminStaffId, String appointmentDate, String appointmentTime, String appointmentSummary, String surgeryPhoneNumber, String hospitalPhoneNumber, String departmentName, String clinicId, String departmentId, ArrayList<Clinic> clinic) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.surgeryId = surgeryId;
        this.doctorId = doctorId;
        this.surgeryName = surgeryName;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.adminFirstName = adminFirstName;
        this.adminLastName = adminLastName;
        this.adminStaffId = adminStaffId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentSummary = appointmentSummary;
        this.surgeryPhoneNumber = surgeryPhoneNumber;
        this.hospitalPhoneNumber = hospitalPhoneNumber;
        this.departmentName = departmentName;
        this.clinicId = clinicId;
        this.departmentId = departmentId;
        this.clinic = clinic;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    
    
    
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public ArrayList<Clinic> getClinic() {
        return clinic;
    }

    public void setClinic(ArrayList<Clinic> clinic) {
        this.clinic = clinic;
    }
    
    

    public Appointment() {
       clinic = new ArrayList<>();
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSurgeryId() {
        return surgeryId;
    }

    public void setSurgeryId(String surgeryId) {
        this.surgeryId = surgeryId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getAdminFirstName() {
        return adminFirstName;
    }

    public void setAdminFirstName(String adminFirstName) {
        this.adminFirstName = adminFirstName;
    }

    public String getAdminLastName() {
        return adminLastName;
    }

    public void setAdminLastName(String adminLastName) {
        this.adminLastName = adminLastName;
    }

    public String getAdminStaffId() {
        return adminStaffId;
    }

    public void setAdminStaffId(String adminStaffId) {
        this.adminStaffId = adminStaffId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentSummary() {
        return appointmentSummary;
    }

    public void setAppointmentSummary(String appointmentSummary) {
        this.appointmentSummary = appointmentSummary;
    }

    public String getSurgeryPhoneNumber() {
        return surgeryPhoneNumber;
    }

    public void setSurgeryPhoneNumber(String surgeryPhoneNumber) {
        this.surgeryPhoneNumber = surgeryPhoneNumber;
    }

    public String getHospitalPhoneNumber() {
        return hospitalPhoneNumber;
    }

    public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
        this.hospitalPhoneNumber = hospitalPhoneNumber;
    }
    
    

    @Override
    public String toString() {
        return "Appointment{" + "appointmentId=" + appointmentId + ", patientId=" + patientId + ", hospitalId=" + hospitalId + ", hospitalName=" + hospitalName + ", surgeryId=" + surgeryId + ", doctorId=" + doctorId + ", surgeryName=" + surgeryName + ", doctorFirstName=" + doctorFirstName + ", doctorLastName=" + doctorLastName + ", adminFirstName=" + adminFirstName + ", adminLastName=" + adminLastName + ", adminStaffId=" + adminStaffId + ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", appointmentSummary=" + appointmentSummary + ", surgeryPhoneNumber=" + surgeryPhoneNumber + ", hospitalPhoneNumber=" + hospitalPhoneNumber + '}';
    }
    
    
    
    

    
    
    
    
    
}
