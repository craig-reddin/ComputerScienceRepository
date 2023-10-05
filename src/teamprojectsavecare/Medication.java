/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class Medication {

    private String companyId;
    private String companyStaffId;
    private String medicationName;
    private String medicationBrand;
    private String medicationSideEffects;
    private String medicationDose;
    private String medicationUse;
    private String medicationDateRecommended;
    private String medicationRecommendation;
    private String medicationId;
    
    

    public Medication(String companyId, String companyStaffId, String medicationName, String medicationBrand, String medicationSideEffects, String medicationDose, String medicationUse, String medicationDateRecommended, String medicationRecommendation, String medicationId) {
        this.companyId = companyId;
        this.companyStaffId = companyStaffId;
        this.medicationName = medicationName;
        this.medicationBrand = medicationBrand;
        this.medicationSideEffects = medicationSideEffects;
        this.medicationDose = medicationDose;
        this.medicationUse = medicationUse;
        this.medicationDateRecommended = medicationDateRecommended;
        this.medicationRecommendation = medicationRecommendation;
        this.medicationId = medicationId;
    }

    
    
    public Medication(){
        
    }

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }
    

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyStaffId() {
        return companyStaffId;
    }

    public void setCompanyStaffId(String companyStaffId) {
        this.companyStaffId = companyStaffId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationBrand() {
        return medicationBrand;
    }

    public void setMedicationBrand(String medicationBrand) {
        this.medicationBrand = medicationBrand;
    }

    public String getMedicationSideEffects() {
        return medicationSideEffects;
    }

    public void setMedicationSideEffects(String medicationSideEffects) {
        this.medicationSideEffects = medicationSideEffects;
    }

    public String getMedicationDose() {
        return medicationDose;
    }

    public void setMedicationDose(String medicationDose) {
        this.medicationDose = medicationDose;
    }

    public String getMedicationUse() {
        return medicationUse;
    }

    public void setMedicationUse(String medicationUse) {
        this.medicationUse = medicationUse;
    }

    public String getMedicationDateRecommended() {
        return medicationDateRecommended;
    }

    public void setMedicationDateRecommended(String medicationDateRecommended) {
        this.medicationDateRecommended = medicationDateRecommended;
    }

    public String getMedicationRecommendation() {
        return medicationRecommendation;
    }

    public void setMedicationRecommendation(String medicationRecommendation) {
        this.medicationRecommendation = medicationRecommendation;
    }

    @Override
    public String toString() {
        return "Medication{" + "companyId=" + companyId + ", companyStaffId=" + companyStaffId + ", medicationName=" + medicationName + ", medicationBrand=" + medicationBrand + ", medicationSideEffects=" + medicationSideEffects + ", medicationDose=" + medicationDose + ", medicationUse=" + medicationUse + ", medicationDateRecommended=" + medicationDateRecommended + ", medicationRecommendation=" + medicationRecommendation + '}';
    }
    
    
    
    
    
}
