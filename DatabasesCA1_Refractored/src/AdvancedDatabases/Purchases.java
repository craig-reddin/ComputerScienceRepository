/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AdvancedDatabases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author craig
 */
public class Purchases {

    private int purchasesId;
    private int productId;
    private int branchId;
    private int customerId;
    private int ReturnsId;
    private double purchaseTotal;
    private double ReturnTotal;

    public Purchases(int purchasesId, int productId, int branchId, int customerId, int ReturnsId, double purchaseTotal, double ReturnTotal) {
        this.purchasesId = purchasesId;
        this.productId = productId;
        this.branchId = branchId;
        this.customerId = customerId;
        this.ReturnsId = ReturnsId;
        this.purchaseTotal = purchaseTotal;
        this.ReturnTotal = ReturnTotal;
    }

    public int getPurchasesId() {
        return purchasesId;
    }

    public void setPurchasesId(int purchasesId) {
        this.purchasesId = purchasesId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getReturnsId() {
        return ReturnsId;
    }

    public void setReturnsId(int ReturnsId) {
        this.ReturnsId = ReturnsId;
    }

    public double getPurchaseTotal() {
        return purchaseTotal;
    }

    public void setPurchaseTotal(double purchaseTotal) {
        this.purchaseTotal = purchaseTotal;
    }

    public double getReturnTotal() {
        return ReturnTotal;
    }

    public void setReturnTotal(double ReturnTotal) {
        this.ReturnTotal = ReturnTotal;
    }

    public void Sales(String url) {
        int counter = 3;
        while(counter>0){
            try ( Connection conn = DriverManager.getConnection(url)) {
                PreparedStatement st = conn.prepareStatement("SELECT PurchasesTable.PurchaseTotal, PurchasesTable.BranchId, Branch.CommunityPopulation FROM PurchasesTable"
                        + " INNER JOIN Branch ON PurchasesTable.BranchId = Branch.BranchId where PurchasesTable.BranchId = 01;");
                //st.setString(1, str2); //pass id string to statement, why 1? multiple statements? location?
                //Excuting Query 
//                branch1.clear();
//                ResultSet rs = st.executeQuery(); //get result set rs by executing the Prepared Statement
//                while (rs.next()) { //iterate rs
//                    String s = rs.getString(1);
//                    String s1 = rs.getString(2);
//                    String s2 = rs.getString(3);//get id from rs, save as s                 
//
//                    populationDub = Double.parseDouble(s2);
//                    branch1.add(Double.parseDouble(s));
//                    System.out.println(s2);
//
//                }
//
//                System.out.println(populationDub);
//
//                for (int i = 0; i < branch1.size(); i++) {
//                    double b1 = branch1.get(i);
//
//                    branch11 = branch11 + b1;
//
//                }
//                populationAverageA = branch11 / populationDub;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }//end try catch
        }
    }

}
