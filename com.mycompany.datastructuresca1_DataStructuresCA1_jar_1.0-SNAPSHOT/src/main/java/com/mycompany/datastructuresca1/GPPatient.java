/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructuresca1;

/**
 *
 * @author craig
 */
public class GPPatient {
    
  private String name;
  private String dob;
  private boolean medicalConditions;

    public GPPatient(String name, String dob, boolean medicalConditions) {
        this.name = name;
        this.dob = dob;
        this.medicalConditions = medicalConditions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(boolean medicalConditions) {
        this.medicalConditions = medicalConditions;
    }
  
  
}
