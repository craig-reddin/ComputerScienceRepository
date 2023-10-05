/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdvancedDatabases;

/**
 *
 * @author craig
 */
public class Returns {
    
    private int returnsId;
    private double returnAmount;

    public Returns(int returnsId, double returnAmount) {
        this.returnsId = returnsId;
        this.returnAmount = returnAmount;
    }

    public int getReturnsId() {
        return returnsId;
    }

    public void setReturnsId(int returnsId) {
        this.returnsId = returnsId;
    }

    public double getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(double returnAmount) {
        this.returnAmount = returnAmount;
    }
    
    
}
