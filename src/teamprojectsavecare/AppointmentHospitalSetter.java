/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */

// This class was created to have a light weight object that onnly will consist for a hospital appointment.
//this class was used to ensure the correct clinic names, department and hospital names are displayed for doctors to make appointments for
public class AppointmentHospitalSetter {

    private String hospitalName;
    private String departmentName;
    private String clinicName;

    public AppointmentHospitalSetter() {
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    
    
    
}
