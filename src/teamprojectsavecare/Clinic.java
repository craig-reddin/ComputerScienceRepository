/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class Clinic {
    private String clinicName;
    private String hospitalName;
    private String departementName;

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartementName() {
        return departementName;
    }

    public void setDepartementName(String departementName) {
        this.departementName = departementName;
    }
    
    
    

    public Clinic() {
    }

    @Override
    public String toString() {
        return "Clinic{" + "clinicName=" + clinicName + ", hospitalName=" + hospitalName + ", departementName=" + departementName + '}';
    }
    
    

    
    
    
}
