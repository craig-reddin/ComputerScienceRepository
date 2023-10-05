/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class MedicationPrescribed extends Medication {

    private String prescriptionId;
    private String doctorFirstName;
    private String doctorLastName;
    private String doctorId;
    private String patientId;
    private String medicationDatePrescribed;
    private String medicationDateEnded;

    public MedicationPrescribed(String prescriptionId, String doctorFirstName, String doctorLastName, String doctorId, String patientId, String medicationDatePrescribed, String medicationDateEnded, String companyId, String companyStaffId, String medicationName, String medicationBrand, String medicationSideEffects, String medicationDose, String medicationUse, String medicationDateRecommended, String medicationRecommendation, String medicationId) {
        super(companyId, companyStaffId, medicationName, medicationBrand, medicationSideEffects, medicationDose, medicationUse, medicationDateRecommended, medicationRecommendation, medicationId);
        this.prescriptionId = prescriptionId;
        this.doctorFirstName = doctorFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.medicationDatePrescribed = medicationDatePrescribed;
        this.medicationDateEnded = medicationDateEnded;
    }

    public MedicationPrescribed() {

    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getMedicationDatePrescribed() {
        return medicationDatePrescribed;
    }

    public void setMedicationDatePrescribed(String medicationDatePrescribed) {
        this.medicationDatePrescribed = medicationDatePrescribed;
    }

    public String getMedicationDateEnded() {
        return medicationDateEnded;
    }

    public void setMedicationDateEnded(String medicationDateEnded) {
        this.medicationDateEnded = medicationDateEnded;
    }

    @Override
    public String toString() {
        return super.toString() + "prescriptionId=" + prescriptionId + ", doctorFirstName=" + doctorFirstName + ", doctorLastName=" + doctorLastName + ", patientId=" + patientId + ", medicationDatePrescribed=" + medicationDatePrescribed + ", medicationDateEnded=" + medicationDateEnded + '}';
    }

}
