/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class Procedures {
    
    private String procedureId;
    private String procedureName;
    private String procedureDescription;
    private String procedureDuration;
    private String procedureRiskLevel;
    private String procedurePreperation;
    private String procedureAfterCare;

    public Procedures(String procedureId, String procedureName, String procedureDescription, String procedureDuration, String procedureRiskLevel, String procedurePreperation, String procedureAfterCare) {
        this.procedureId = procedureId;
        this.procedureName = procedureName;
        this.procedureDescription = procedureDescription;
        this.procedureDuration = procedureDuration;
        this.procedureRiskLevel = procedureRiskLevel;
        this.procedurePreperation = procedurePreperation;
        this.procedureAfterCare = procedureAfterCare;
    }

    
    
    public Procedures(){
        
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getProcedureDescription() {
        return procedureDescription;
    }

    public void setProcedureDescription(String procedureDescription) {
        this.procedureDescription = procedureDescription;
    }

    public String getProcedureDuration() {
        return procedureDuration;
    }

    public void setProcedureDuration(String procedureDuration) {
        this.procedureDuration = procedureDuration;
    }

    public String getProcedureRiskLevel() {
        return procedureRiskLevel;
    }

    public void setProcedureRiskLevel(String procedureRiskLevel) {
        this.procedureRiskLevel = procedureRiskLevel;
    }

    public String getProcedurePreperation() {
        return procedurePreperation;
    }

    public void setProcedurePreperation(String procedurePreperation) {
        this.procedurePreperation = procedurePreperation;
    }

    public String getProcedureAfterCare() {
        return procedureAfterCare;
    }

    public void setProcedureAfterCare(String procedureAfterCare) {
        this.procedureAfterCare = procedureAfterCare;
    }

   

    @Override
    public String toString() {
        return "Procedures{" + "ProcedureId=" + procedureId + ", ProcedureName=" + procedureName + ", PocedureDescription=" + procedureDescription + ", procedureDuration=" + procedureDuration + ", procedureRiskLevel=" + procedureRiskLevel + ", procedurePreperation=" + procedurePreperation + ", procedureAfterCare=" + procedureAfterCare + '}';
    }
    
    
    
    
    
    
}
