/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdvancedDatabases;

/**
 *
 * @author craig
 */
public class Date {
    
    //java.sql.Date date = new java.sql.Date(millis);
    
    private int dateId; 
    private Date dateT;

    public Date(int dateId, Date dateT) {
        this.dateId = dateId;
        this.dateT = dateT;
    }

    public int getDateId() {
        return dateId;
    }

    public void setDateId(int dateId) {
        this.dateId = dateId;
    }

    public Date getDateT() {
        return dateT;
    }

    public void setDateT(Date dateT) {
        this.dateT = dateT;
    }
    
    
    
    
    
}
