/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.datastructuresca1;

/**
 *
 * @author craig
 */
public interface PriorityQueueInterface {

    public boolean queueIsEmpty();

    public int queueSize();

    public String enqueue(Patient patient);

    public String printPQueue();

    //Next Patient is also remove patient
    //Patient information is displayed 
    //The Patient object is removed form the Arraylist
    public String nextPatient();

}
