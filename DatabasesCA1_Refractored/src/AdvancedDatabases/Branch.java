/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdvancedDatabases;

/**
 *
 * @author craig
 */
public class Branch {
    
    private int branchId;
    private String address;
    private double companyTotalSales;

    public Branch(int branchId, String address, double companyTotalSales) {
        this.branchId = branchId;
        this.address = address;
        this.companyTotalSales = companyTotalSales;
    }
    
    
    

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getCompanyTotalSales() {
        return companyTotalSales;
    }

    public void setCompanyTotalSales(double companyTotalSales) {
        this.companyTotalSales = companyTotalSales;
    }
    
    
}
