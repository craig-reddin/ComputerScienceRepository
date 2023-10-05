/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teamprojectsavecare;

/**
 *
 * @author craig
 */
public class TeamProjectSaveCare {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LocalSQLiteDatabase db = new LocalSQLiteDatabase();
        db.loadDatabase();
        ExtractDoctorData docData = new ExtractDoctorData();
        docData.loadCSV();
        SaveCareGUI g = new SaveCareGUI();

        g.setVisible(true);
    }

}
