/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastructuresca1;

import java.util.*;

public class PriorityQueue implements PriorityQueueInterface {

    private ArrayList<Patient> priorityQueue;

    public PriorityQueue() {
        priorityQueue = new ArrayList<Patient>();
    }

    @Override
    public boolean queueIsEmpty() {
        return priorityQueue.isEmpty();
    }

    
    @Override
    public int queueSize() {
        return priorityQueue.size();
    }

    // new element with a given key and object info will be added in the right position
    // according to priority, so we are basically sorting the list as we insert new elements into it
    @Override
    public String enqueue(Patient patient) {

        // if statement to check if the Priority Queue is empty
        if (priorityQueue.isEmpty()) {
            priorityQueue.add(patient);
            return "This vaccination patient is in position 1 in the Queue and will be vaccinated next";
        }

     
        //Loop through the queue and if the insterted patient priority is greater that the current patients (i) 
        //then insert the  patient in the position of the arraylists existing patients position
        for (int i = 0; i < priorityQueue.size(); i++) {
            String message = "This vaccination patient is in position " + String.valueOf(i + 1) + "of the Queue";
            if (patient.getPriority() > priorityQueue.get(i).getPriority()) {
                priorityQueue.add(i, patient);
                return message;

            }

        }
        
        //if the patient has not been entered after loop has finished this meas is and has the lowest priority and is added to the end of the list
        String message = "This patient is at the end of the queue";
        priorityQueue.add(patient);

        return message;
    }

    //the first element has the highest priority
    public String nextPatient() {
        
        //This method removes the first Patient fom the queue ( highest priority) and returns the patients data to be displayed to the user
        String name = priorityQueue.get(0).getName();
        int priority = priorityQueue.get(0).getPriority();
        priorityQueue.remove(0);
        return name + " is next with a priority of " + String.valueOf(priority) + " and is removed from the priority queue";
    }

     
    public String printPQueue() {
        
        //This method loops the arraylist and sequecially adds the patients values to the String and returns all the patients data. 
        String list = "";
        for (int i = 0; i < priorityQueue.size(); i++) {
            list = list + priorityQueue.get(i).toString();

        }
        return list;
    }

    

 

    

    

}
