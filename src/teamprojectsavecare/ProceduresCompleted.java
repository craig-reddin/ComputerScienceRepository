/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class ProceduresCompleted extends Procedures{
    
    private String procedureCompletedId;
    private String patientId;
    private String hopspitalName;
    private String DoctorFirstName;
    private String doctorLastName;
    private String HospitalId;
    private String doctorId;
    private String procedureCompletedDate;
    private String procedureCompletedSummary;

    public ProceduresCompleted(String procedureCompletedId, String patientId, String hopspitalName, String DoctorFirstName, String doctorLastName, String HospitalId, String doctorId, String procedureCompletedDate, String procedureCompletedSummary, String procedureId, String procedureName, String procedureDescription, String procedureDuration, String procedureRiskLevel, String procedurePreperation, String procedureAfterCare) {
        super(procedureId, procedureName, procedureDescription, procedureDuration, procedureRiskLevel, procedurePreperation, procedureAfterCare);
        this.procedureCompletedId = procedureCompletedId;
        this.patientId = patientId;
        this.hopspitalName = hopspitalName;
        this.DoctorFirstName = DoctorFirstName;
        this.doctorLastName = doctorLastName;
        this.HospitalId = HospitalId;
        this.doctorId = doctorId;
        this.procedureCompletedDate = procedureCompletedDate;
        this.procedureCompletedSummary = procedureCompletedSummary;
    }

    public String getHospitalId() {
        return HospitalId;
    }

    public void setHospitalId(String HospitalId) {
        this.HospitalId = HospitalId;
    }

   

   
    
    public ProceduresCompleted(){
        
    }
    
    

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    

    public String getProcedureCompletedId() {
        return procedureCompletedId;
    }

    public void setProcedureCompletedId(String procedureCompletedId) {
        this.procedureCompletedId = procedureCompletedId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getHopspitalName() {
        return hopspitalName;
    }

    public void setHopspitalName(String hopspitalName) {
        this.hopspitalName = hopspitalName;
    }

    public String getDoctorFirstName() {
        return DoctorFirstName;
    }

    public void setDoctorFirstName(String DoctorFirstName) {
        this.DoctorFirstName = DoctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getProcedureCompletedDate() {
        return procedureCompletedDate;
    }

    public void setProcedureCompletedDate(String procedureCompletedDate) {
        this.procedureCompletedDate = procedureCompletedDate;
    }

    public String getProcedureCompletedSummary() {
        return procedureCompletedSummary;
    }

    public void setProcedureCompletedSummary(String procedureCompletedSummary) {
        this.procedureCompletedSummary = procedureCompletedSummary;
    }

    @Override
    public String toString() {
        return "ProceduresCompleted{" + "procedureCompletedId=" + procedureCompletedId + ", patientId=" + patientId + ", hopspitalName=" + hopspitalName + ", DoctorFirstName=" + DoctorFirstName + ", doctorLastName=" + doctorLastName + ", procedureCompletedDate=" + procedureCompletedDate + ", procedureCompletedSummary=" + procedureCompletedSummary + '}';
    }
    
    

    
    
  
    



   
    
    
    
    
    
}
