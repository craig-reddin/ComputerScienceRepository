/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructuresca1;

import java.util.Date;

/**
 *
 * @author craig
 */
public class Patient {
    
    private String name;
    private int priority;
    private Boolean medicalCondition;
    
    
    public Patient(){
        
    }

    public Patient(String name, int priority, Boolean medicalCondition) {
        this.name = name;
        this.priority = priority;
        this.medicalCondition = medicalCondition;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Boolean getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(Boolean medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    @Override
    public String toString() {
        return "Patient Name:" + name + "\nPatient Priority: " + priority + "\nMedical Condition: " + medicalCondition+"\n";
    }
    
    
    
}
