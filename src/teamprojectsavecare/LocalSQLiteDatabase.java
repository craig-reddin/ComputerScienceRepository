/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teamprojectsavecare;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author craig
 */
public class LocalSQLiteDatabase {

    private String url;

    public LocalSQLiteDatabase() {

        url = "jdbc:sqlite:c:/mydb/saveCare.db";

    }

    public void loadDatabase() {

        if (new File("c:/mydb/").exists()) {
            System.out.println("Local Database already exists");
            return;
        }

        new File("c:/mydb/").mkdir(); //creates the folder were the DB will be saved.

        try { //loads driver into memory, just one way of doing it
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (Exception ex) {
        }//end try catch

        //Makes a connection called conn to the url created
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) { //if the connection is not null 
                System.out.println("Database Is loading In");
            }//end if

            loadTables();

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch
    }

    //The below meethod creates tables and inserts data that is to be inserted for testing the application
    //variable with sql queriey values are executed
    public void loadTables() {
        String sqlCreateHospitalInformation = "CREATE TABLE Hospital (\n"
                + "	HospitalId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	HospitalName varchar (20),\n"
                + "	HospitalAddress varchar (255),\n"
                + "	HospitalEirCode varchar (8),\n"
                + "	HospitalEmail varchar (50),\n"
                + "    HospitalPhoneNumber varchar (13)\n"
                + "  );";

        String sqlCreateSurgeryInformation = "CREATE TABLE Surgery (\n"
                + "	SurgeryId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	SurgeryName varchar (60),\n"
                + "	SurgeryType varchar (50),\n"
                + "	SurgeryAddress varchar (255),\n"
                + "    SurgeryPhoneNumber varchar (15),\n"
                + "	SurgeryEmail varchar (50),\n"
                + "    SurgeryWebsite varchar (50)\n"
                + "  );";

        String sqlCreatePharmaceuticalCompanyInformation = "CREATE TABLE PharmaceuticalCompany (\n"
                + "	CompanyId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	CompanyName varchar (30),\n"
                + "	CompanyAddress varchar (255),\n"
                + "    CompanyEircode varchar (8)\n"
                + "	\n"
                + "  );";

        String sqlCreatePharmacyInformation = " CREATE TABLE Pharmacy (\n"
                + "	PharmacyId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	PharmacyName varchar (50),\n"
                + "	PharmacyAddress varchar (255),\n"
                + "    PharmacyEircode varchar (8),\n"
                + "    PharmacyOpeningHours varchar (255),\n"
                + "    PharmacyCurrentManager varchar (40),\n"
                + "    PharmacyEmail varchar (50),\n"
                + "    PharmacyPhoneNumber varchar (14),\n"
                + "    PharmacyWebsite varchar (50)\n"
                + "  );";

        String sqlCreateNurseInformation = "CREATE TABLE Nurse (\n"
                + "	NurseId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    HospitalId varchar(15),\n"
                + "    NursePPS varchar (9),\n"
                + "	NurseFirstName varchar (25),\n"
                + "	NurseLastName varchar (25),\n"
                + "    NurseEmail varchar (50),\n"
                + "    NursePassword varchar (25),\n"
                + "    NurseDepartment varchar (50),\n"
                + "    FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalID) \n"
                + "  );";

        String sqlCreateDoctorInformation = "CREATE TABLE Doctor (\n"
                + "	DoctorId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    HospitalId varchar(15),\n"
                + "    SurgeryId varchar(15),\n"
                + "    DoctorPPS varchar (9),\n"
                + "	DoctorFirstName varchar (25),\n"
                + "	DoctorLastName varchar (25),\n"
                + "    DoctorEmail varchar (50),\n"
                + "    DoctorPassword varchar (25),\n"
                + "    DoctorSpecialisation varchar (50),\n"
                + "    DoctorDepartment varchar (50),\n"
                + "    DoctorNumber varchar (60),\n"
                + "    FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "	FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId)\n"
                + "  );";

        String sqlCreatePharmacyStaffInformation = "CREATE TABLE PharmacyStaff(\n"
                + "	PharmacyStaffId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    PharmacyId varchar(15),\n"
                + "    PharmacyStaffFirstName varchar(25),\n"
                + "    PharmacyStaffLastName varchar (25),\n"
                + "	PharmacyStaffPPS varchar (9),\n"
                + "	PharmacyStaffEmail varchar (50),\n"
                + "    PharmacyStaffPassword varchar (25),\n"
                + "    PharmacyStaffPosition varchar (25),\n"
                + "    FOREIGN KEY (PharmacyId) REFERENCES Pharmacy(PharmacyId)\n"
                + "	\n"
                + "  );";

        String sqlCreatePatientInformation = "CREATE TABLE Patient (\n"
                + "	PatientId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    HospitalId varchar(15),\n"
                + "	PharmacyId varchar(15),\n"
                + "    SurgeryId varchar(15),\n"
                + "    PatientPPS varchar (9),\n"
                + "	PatientFirstName varchar (25),\n"
                + "	PatientLastName varchar (25),\n"
                + "    PatientEmail varchar (50),\n"
                + "    PatientPassword varchar (25),\n"
                + "    PatientHomeAddress varchar (255),\n"
                + "    PatientEircode varchar (10),\n"
                + "    PatientPhoneNumber varchar (25),\n"
                + "    PatientNationality varchar (20),\n"
                + "    PatientRace varchar (20),\n"
                + "    PatientDateOfBirth varchar (15),\n"
                + "    PatientMaritalStatus varchar (10),\n"
                + "    PatientSex varchar (8),\n"
                + "    PatientEmergencyNumber varchar (20),\n"
                + "    PatientNumber varchar (20),\n"
                + "    PatientBloodType varchar (18),\n"
                + "    PatientSmoker varchar (20),\n"
                + "    PatientWeeklyActivity varchar (20),\n"
                + "    PatientOccupation varchar (50),\n"
                + "    FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "	FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId),\n"
                + "	FOREIGN KEY (PharmacyId) REFERENCES Pharmacy(PharmacyId)\n"
                + "\n"
                + "  );";

        String sqlCreateDepartmentInformation = " Create table Department(\n"
                + "  DepartmentId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "  HospitalId varchar(15),\n"
                + "  DepartmentName varchar (50),\n"
                + "  DepartmentFloor varchar (20),\n"
                + "  \n"
                + "  FOREIGN Key (HospitalId) References  Hospital(HospitalId)\n"
                + "  );";

        String sqlCreateWardsInformation = "Create table Wards("
                + " WardId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "  HospitalId varchar(15),\n"
                + "  DepartmentId varchar (15),\n"
                + "  WardName varchar (50),\n"
                + "  WardFloor varchar (20),\n"
                + "  WardType varchar(20),\n"
                + "  FOREIGN Key (HospitalId) References  Hospital(HospitalId),\n"
                + "  FOREIGN Key (DepartmentId) References  Department(DepartmentId)"
                + "  );";

        String sqlCreateClinicsInformation = "Create table Clinics(\n"
                + "  ClinicId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "  HospitalId varchar(15),\n"
                + "  DepartmentId varchar (15),\n"
                + "  ClinicName varchar (50),\n"
                + "\n"
                + "  FOREIGN Key (HospitalId) References  Hospital(HospitalId),\n"
                + "  FOREIGN Key (DepartmentId) References  Department(DepartmentId)\n"
                + "  );";

        String sqlCreateCompanyStaffInformation = "Create table CompanyStaff(\n"
                + "	CompanyStaffId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    CompanyId varchar(15),\n"
                + "    CompanyStaffFirstName varchar(25),\n"
                + "    CompanyStaffLastName varchar (25),\n"
                + "	CompanyStaffPPS varchar (9),\n"
                + "	CompanyStaffEmail varchar (50),\n"
                + "    CompanyStaffPassword varchar (25),\n"
                + "    CompanyStaffPosition varchar (50),\n"
                + "   \n"
                + "    FOREIGN KEY (CompanyId) REFERENCES PharmaceuticalCompany(CompanyId)\n"
                + "  );";

        String sqlCreateAdminStaffInformation = "Create table AdminStaff(\n"
                + "	AdminStaffId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    HospitalId varchar (15),\n"
                + "    SurgeryId varchar (15),\n"
                + "    AdminStaffPPS varchar (9),\n"
                + "    AdminStaffFirstName varchar(25),\n"
                + "    AdminStaffLastName varchar (25),\n"
                + "	AdminStaffEmail varchar (50),\n"
                + "    AdminStaffPassword varchar (25),\n"
                + "    CompanyStaffDepartment varchar (15),\n"
                + "   \n"
                + "    FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "	FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId)\n"
                + "	\n"
                + "  );";

        String Medication = "Create table Medication(\n"
                + "	\n"
                + "    MedicationId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    CompanyId varchar (15),\n"
                + "    CompanyStaffId varchar (15),\n"
                + "    MedicationName varchar (60),\n"
                + "    MedicationBrand varchar(25),\n"
                + "    MedicationSideEffects varchar (255),\n"
                + "    MedicationDose varchar(25),\n"
                + "	MedicationUse varchar (25),\n"
                + "    MedicationDateCreated varchar (15),\n"
                + "    MedicationRecommendation varchar (255),\n"
                + "    FOREIGN KEY (CompanyId) REFERENCES PharmaceuticalCompany(CompanyId),\n"
                + "    FOREIGN KEY (CompanyStaffId) REFERENCES CompanyStaff(CompanyStaffId)\n"
                + "\n"
                + "  );";

        String sqlCreateProceduresInformation = "Create table Procedures(\n"
                + "	ProcedureId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	ProcedureName varchar (60),\n"
                + "	ProcedureDescription varchar(255),\n"
                + "    ProcedureDuration varchar(10),\n"
                + "    ProcedureRiskLevel varchar (30),\n"
                + "    ProcedurePreperation varchar (255),\n"
                + "    ProcedureAfterCare varchar (255)\n"
                + "  );";

        String sqlCreateProceduresCompletedInformation = "Create table ProceduresCompleted(\n"
                + "	ProcedureCompletedId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    PatientId varchar (15),\n"
                + "    ProcedureId varchar (15),\n"
                + "    HospitalId varchar(15),\n"
                + "    \n"
                + "    DoctorId varchar (15),\n"
                + "    ProcedureCompletedDate varchar (15),\n"
                + "    ProcedureCompletedSummary varchar (255),\n"
                + "    \n"
                + "        FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),\n"
                + "		FOREIGN KEY (ProcedureId) REFERENCES Procedures(ProcedureId),\n"
                + "        FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "          FOREIGN KEY (DoctorId) REFERENCES Doctor(DoctorId)\n"
                + "\n"
                + "    \n"
                + "  );";

        String sqlCreateIllnessInformation = "Create table Illnesses(\n"
                + "	IllnessId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	IllnessName varchar (60),\n"
                + "	IllnessType varchar(25),\n"
                + "    IllnessSymptoms varchar(255),\n"
                + "    IllnessFactLink varchar (255),\n"
                + "    IllnessDescription varchar (255)\n"
                + "    \n"
                + "  );";

        String sqlCreateIllnessDiagnosedInformation = "Create table IllnessDiagnosed(\n"
                + "	IllnessDiagnosesId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "    IllnessId varchar (15),\n"
                + "    PatientId varchar (15),\n"
                + "    HospitalId varchar(15),\n"
                + "    SurgeryId varchar (15),\n"
                + "    DoctorId varchar (15),\n"
                + "    DateDiagnosed varchar (15),\n"
                + "    \n"
                + "    \n"
                + "        FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),\n"
                + "		FOREIGN KEY (IllnessId) REFERENCES Illnesses(IllnessId),\n"
                + "        FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "		FOREIGN KEY (DoctorId) REFERENCES Doctor(DoctorId),\n"
                + "        FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId)\n"
                + "    \n"
                + "  );";

        String sqlCreateMedicationPrescribedInformation = "Create table MedicationPrescribed(\n"
                + "	PrescriptionId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	DoctorId varchar (15),\n"
                + "    MedicationId varchar (15),\n"
                + "    PatientId varchar (15),\n"
                + "	MedicationDatePrescribed varchar (15),\n"
                + "    MedicationDateEnd varchar (15),\n"
                + "    \n"
                + "    \n"
                + "        FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),\n"
                + "		FOREIGN KEY (MedicationId) REFERENCES Medication(MedicationId),\n"
                + "        FOREIGN KEY (DoctorId) REFERENCES Doctor(DoctorId)\n"
                + "\n"
                + "    \n"
                + "  );";

        String sqlCreatePatientHistoryInformation = " Create table PatientHistory(\n"
                + "	HistoryId varchar (15) NOT NULL PRIMARY KEY,\n"
                + "	HospitalId varchar (15),\n"
                + "    PatientId varchar (15),\n"
                + "    SurgeryId varchar (15),\n"
                + "    DoctorId varchar (15),\n"
                + "	VisitDate varchar (15),\n"
                + "    VisitType varchar (25),\n"
                + "	VisitLength varchar (25),\n"
                + "    ReasonForVisit varchar (50),\n"
                + "    PatientSummary varchar (255),\n"
                + "    \n"
                + "    \n"
                + "        FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),\n"
                + "        FOREIGN KEY (DoctorId) REFERENCES Doctor(DoctorId),\n"
                + "		FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "		FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId)\n"
                + "\n"
                + "\n"
                + "    \n"
                + "  );";

        String sqlCreateAppointmentInformation = "Create table Appointment(\n"
                + "	AppointmentId varchar (15) Not Null Primary Key,\n"
                + "    PatientId varchar (15),\n"
                + "	HospitalId varchar (15),\n"
                + "	SurgeryId varchar (15),\n"
                + "    DoctorId varchar (15),\n"
                + "    AdminStaffId varchar (15),\n"
                + "    AppointmentDate varchar (15),\n"
                + "	AppointmentTime varchar(12),\n"
                + "	AppointmentSummary varchar(255),\n"
                + "    DepartmentId varchar(15),\n"
                + "	ClinicId varchar(15),	\n"
                + "\n"
                + "    \n"
                + "		FOREIGN KEY (PatientId) REFERENCES Patient(PatientId),\n"
                + "        FOREIGN KEY (DoctorId) REFERENCES Doctor(DoctorId),\n"
                + "		FOREIGN KEY (HospitalId) REFERENCES Hospital(HospitalId),\n"
                + "		FOREIGN KEY (SurgeryId) REFERENCES Surgery(SurgeryId),\n"
                + "		FOREIGN KEY (AdminStaffId) REFERENCES AdminStaff(AdminStaffId),\n"
                + "        FOREIGN KEY (DepartmentId) REFERENCES Department(DepartmentId),\n"
                + "		FOREIGN KEY (ClinicId) REFERENCES Clinics(ClinicId)\n"
                + "  );";

        String sqlCreateUsersInformation = "Create Table Users(\n"
                + "UserId varchar (15) Not Null Primary Key,\n"
                + "UserEmail varchar (50),\n"
                + "UserPassword varchar (25),\n"
                + "UserType varchar (20)\n"
                + ");";

        //Makes a connection called conn to the url created earlier
        //may not be needed again if used already, no harm in retrying
        try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) { //makes a statement object called stmt
            stmt.execute(sqlCreateHospitalInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        Statement stmt = null; //clear stmt after first usage
        //Makes a connection called conn to the url created earlier
        //may not be needed again if used already, no harm in retrying
        try (Connection conn = DriverManager.getConnection(url); Statement stmt2 = conn.createStatement()) { //makes a statement object called stmt
            stmt2.execute(sqlCreateSurgeryInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        Statement stmt2 = null;

        try (Connection conn = DriverManager.getConnection(url); Statement stmt3 = conn.createStatement()) { //makes a statement object called stmt
            stmt3.execute(sqlCreatePharmaceuticalCompanyInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt4 = conn.createStatement()) { //makes a statement object called stmt
            stmt4.execute(sqlCreatePharmacyInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt5 = conn.createStatement()) { //makes a statement object called stmt
            stmt5.execute(sqlCreateNurseInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt6 = conn.createStatement()) { //makes a statement object called stmt
            stmt6.execute(sqlCreateDoctorInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt7 = conn.createStatement()) { //makes a statement object called stmt
            stmt7.execute(sqlCreatePharmacyStaffInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt8 = conn.createStatement()) { //makes a statement object called stmt
            stmt8.execute(sqlCreatePatientInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt9 = conn.createStatement()) { //makes a statement object called stmt
            stmt9.execute(sqlCreateDepartmentInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt10 = conn.createStatement()) { //makes a statement object called stmt
            stmt10.execute(sqlCreateWardsInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch

        try (Connection conn = DriverManager.getConnection(url); Statement stmt11 = conn.createStatement()) { //makes a statement object called stmt
            stmt11.execute(sqlCreateClinicsInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation

        try (Connection conn = DriverManager.getConnection(url); Statement stmt12 = conn.createStatement()) { //makes a statement object called stmt
            stmt12.execute(sqlCreateCompanyStaffInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        try (Connection conn = DriverManager.getConnection(url); Statement stmt13 = conn.createStatement()) { //makes a statement object called stmt
            stmt13.execute(sqlCreateAdminStaffInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        try (Connection conn = DriverManager.getConnection(url); Statement stmt14 = conn.createStatement()) { //makes a statement object called stmt
            stmt14.execute(Medication); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        try (Connection conn = DriverManager.getConnection(url); Statement stmt15 = conn.createStatement()) { //makes a statement object called stmt
            stmt15.execute(sqlCreateProceduresInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        try (Connection conn = DriverManager.getConnection(url); Statement stmt16 = conn.createStatement()) { //makes a statement object called stmt
            stmt16.execute(sqlCreateProceduresCompletedInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        try (Connection conn = DriverManager.getConnection(url); Statement stmt17 = conn.createStatement()) { //makes a statement object called stmt
            stmt17.execute(sqlCreateIllnessInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }//end try catch   sqlCreateCompanyStaffInformation
        // sqlCreateIllnessDiagnosedInformation sqlCreateIllnessDiagnosedInformation

        try (Connection conn = DriverManager.getConnection(url); Statement stmt18 = conn.createStatement()) { //makes a statement object called stmt
            stmt18.execute(sqlCreateIllnessDiagnosedInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }
        try (Connection conn = DriverManager.getConnection(url); Statement stmt19 = conn.createStatement()) { //makes a statement object called stmt
            stmt19.execute(sqlCreateMedicationPrescribedInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }
        try (Connection conn = DriverManager.getConnection(url); Statement stmt20 = conn.createStatement()) { //makes a statement object called stmt
            stmt20.execute(sqlCreatePatientHistoryInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }
        try (Connection conn = DriverManager.getConnection(url); Statement stmt21 = conn.createStatement()) { //makes a statement object called stmt
            stmt21.execute(sqlCreateAppointmentInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }
        try (Connection conn = DriverManager.getConnection(url); Statement stmt22 = conn.createStatement()) { //makes a statement object called stmt
            stmt22.execute(sqlCreateUsersInformation); //passes the sql string statement to stmt

        } catch (SQLException e) {
            System.out.println(e.getMessage()); //println for catching error, popup would be better
        }

        try {
            Connection conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
            //add to warehouses table

            stmt.executeUpdate("INSERT INTO Hospital" + " VALUES('HH-4000000','Beaumont Hospital','Beaumont Road, Artane', 'D09V2N0', 'info@beaumont.ie','01-8093000'),\n"
                    + "    ('HH-3999999','Mater Hospital','Matter Road, O Connell Steet, Dublin 1', 'D08V1N3', 'info@Mater.ie','01-8094000'),\n"
                    + "    ('HH-3999998','Mullingar Hospital','Mullingar Road, Mullingar', 'D49V3N9', 'info@Mullingar.ie','01-8095000'),\n"
                    + "    ('HH-3999997','St Josephs Hospital','Raheny Road, Raheny Dublin 13', 'D45V6N7', 'info@josephs.ie','01-8096000'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO Surgery" + " VALUES('SA-4000000','Sutton Cross Surgery','General Practitioner', 'Suite 1, Superquinn Sutton Centre, Cross, Sutton, Co. Dublin','018326438', 'suttoncrosssurgery@gmail.com', 'suttoncrossfamilypractice.com'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO PharmaceuticalCompany" + " VALUES('CA-4000000','Mylan','Mylan Unit 25, Baldoye Industrial Estate, Co. Dublin', 'null'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO Pharmacy" + " VALUES('PA-4000000','McCartans Pharmacy Sutton',\n"
                    + "    'Unit 1, Superquinn Centre Station Rd Sutton, Co. Dublin', 'D13 VP29',\n"
                    + "    'Monday to Friday 8.30am - 7.00pm Saturday 9.00am - 6.00pm Sunday 11.00am - 5.00pm','Siobhan Taggart MPSI',\n"
                    + "    'sutton@mccartans.ie', '01 8393941','https://mccartans.ie/pages/sutton-cross'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Nurse" + " VALUES('NA-4000000','HH-4000000','5069973i','Mary', 'Ward','Mary@beaumont.ie','MaryMary','Cardiology'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Doctor" + " VALUES('DA-4000000','HH-4000000',null,'6858338U', 'Thomas','Shelby',\n"
                    + "    'Thomas@beaumont.ie','ThomasThomas', 'Cardiologist', 'Cardiology','1020302'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Doctor" + " VALUES('DA-3999999',null,'SA-4000000','6858337U', 'Kelly','Harris',\n"
                    + "    'Kelly@suttoncross.ie','KellyKelly', 'General Practitioner', null,'1020303'"
                    + "  );");
            stmt.executeUpdate("INSERT INTO PharmacyStaff" + " VALUES('PS-4000000','PA-4000000','Richie','Rich', '50655125P','RichieRich@Beaumont.ie',\n"
                    + "    'RIchieRichie','OTC Staff'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Patient" + " VALUES('PT-4000000','HH-4000000','PA-4000000','SA-4000000','6815338U', 'Stacy','Starling',\n"
                    + "    'stacys@gmail.ie','StacyStacy','32 Bruke Road, Howth, Dublin 13', 'V130945',\n"
                    + "    '0825656787', 'Ireland','White', '24/04/1992', 'Married', 'Female','0725969596', '1000000', 'O-',\n"
                    + "    'Heavy Smoker 20+', '6 Hours', 'Secretary'"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Department" + " VALUES('DE-4000000','HH-4000000','Cardiology','Second Floor'\n"
                    + "  ),("
                    + "	'DE-3999999','HH-4000000','Radiology','First Floor'\n"
                    + "   );");
            stmt.executeUpdate("INSERT INTO Wards" + " VALUES('WA-4000000','HH-4000000','DE-4000000','St.Lukes', 'Second Floor','Post-Procedure'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Clinics" + " VALUES('CA-4000000','HH-4000000','DE-4000000','Clinic A'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Clinics" + " VALUES( 'CA-3999999','HH-4000000','DE-4000000','Clinic B' \n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Clinics" + " VALUES('CA-3999998','HH-4000000','DE-4000000','Clinic C'\n"
                    + "  );");
            
            
             stmt.executeUpdate("INSERT INTO Clinics" + " VALUES('CA-3999997','HH-4000000','DE-3999999','Clinic A'\n"
                    + "  );");
             stmt.executeUpdate("INSERT INTO Clinics" + " VALUES('CA-3999996','HH-4000000','DE-3999999','Clinic B'\n"
                    + "  );");
              
             
            
            
            
            
            
            
            stmt.executeUpdate("INSERT INTO CompanyStaff" + " VALUES('CS-4000000','CA-4000000','Hannah','Strange', '58683848U','Hannah@Mylan.ie','HannahHannah','Pharmaceutical Scientist'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO AdminStaff" + " VALUES('AS-4000000','HH-4000000',null,'596959U', 'Stephen','Lynch','Stephen@beaumont.ie','StephenStephen','Cardiology'\n"
                    + "  ),('AS-3999999', null, 'SA-4000000', '5067676U','Alex','Mangrove', 'Alex@suttonCross.ie','AlexAlex', 'Cardiology');");

            stmt.executeUpdate("INSERT INTO Medication" + " VALUES('MA-4000000','CA-4000000','CS-4000000','Amiodarone', 'Parcerone','Nausea, Vomiting, Fatique, Tremor, Constipation','200mg','Oral',\n"
                    + "    '01/01/1961','Use as recommended by doctor'),\n"
                    + "    ('MA-3999998','CA-4000000','CS-4000000','Amiodarone', 'Parcerone','Nausea, Vomiting, Fatique, Tremor, Constipation','400mg','Oral',\n"
                    + "    '01/01/1961','Use as recommended by doctor'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO Procedures" + " VALUES('PR-4000000','Cardiac Ablation',\n"
                    + "    'Long, flexible tubes are inserted through a vein in your groin or at your neck and threaded up to your heart. In the correct position, high frequency energy is sent through the wire to heat up and destroy an area of tissue that is causing the arrhythmia',\n"
                    + "    '2-4 Hours', 'High Risk','12 Hour Fasting','Intensive care 48 hours. Patient should rest for two weeks.'),\n"
                    + "    ('PR-3999998','Aortic Valve Repair',\n"
                    + "    'Aortic valve repair is usually done with open-heart surgery and by opening the chest bone (sternotomy). Surgeons wire the bone back together after the procedure to prevent movement and aid in healing.',\n"
                    + "    '4-6 Hours', 'Medium - High Risk','Stop eating and drinking the night before surgery','Day or more in the intensive care unit (ICU), 6 - 8 weeks (Breastbone Healing) and 2-3 months full recovery'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO ProceduresCompleted" + " VALUES('PC-4000000','PT-4000000','PR-4000000','HH-4000000', 'DA-4000000','14/02/2023','Procedure was successful. Patient reacted well to treatment'\n"
                    + "  );");

            stmt.executeUpdate("INSERT INTO Illnesses" + " VALUES('IL-4000000',\n"
                    + "    'Ventrincular Fibrillation',\n"
                    + "    'Heart Disease',\n"
                    + "    'Pounding in your chest, Dizziness, Fainting, Anxiety ,Blurry vision ',\n"
                    + "    'https://irishheart.ie/heart-and-stroke-conditions-a-z/arrhythmia/',\n"
                    + "    'A rare, rapid and disorganised rhythm of heartbeats that rapidly leads to loss of consciousness and sudden death if not treated immediately'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO IllnessDiagnosed" + " VALUES('DL-4000000',\n"
                    + "    'IL-4000000',\n"
                    + "    'PT-4000000',\n"
                    + "    'HH-4000000',\n"
                    + "	null,\n"
                    + "    'DA-4000000',\n"
                    + "	'24/05/2001'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO MedicationPrescribed" + " VALUES('MP-4000000',\n"
                    + "    'DA-4000000',\n"
                    + "    'MA-4000000',\n"
                    + "    'PT-4000000',\n"
                    + "	'24/05/2001',\n"
                    + "    '25/11/2011'\n"
                    + "  );");
            stmt.executeUpdate("INSERT INTO Appointment" + " VALUES('AP-4000000', 'PT-4000000', null, 'SA-4000000','DA-3999999','AS-3999999','16/03/2021','13.00','Patient complaining of chest pains. Refering to cardiac secialist in Beaumont hospital. Urgent attention required',null,null\n"
                    + "  ),('AP-3999999','PT-4000000', 'HH-4000000',null,'DA-4000000','AS-4000000','21/02/2021', '16.15','The patient healing very well to Cardiac Ablation procedue. Appointment made to be made for in 18 months time. Tube integrity obsevation required.','DE-4000000','CA-3999999'),"
                    + "('AP-3999998', 'PT-4000000', 'HH-4000000', null,'DA-4000000','AS-4000000','24/03/2022','14.00','Patient is healing well. No healing complications. Blood pressure is now 100mmHg.','DE-4000000','CA-4000000');");

            stmt.executeUpdate("INSERT INTO Users" + " VALUES"
                    + "('US-4000000','stacys@gmail.ie','StacyStacy', 'Patient'),\n"
                    + "('US-3999999','Mary@beaumont.ie','Mary', 'Nurse'),\n"
                    + "('US-3999998','Thomas@beaumont.ie','StacyStacy', 'Doctor'),\n"
                    + "('US-3999997','Hannah@Mylan.ie','HannahHannah', 'Pharmaceutical'),\n"
                    + "('US-3999996','Stephen@beaumont.ie','StephenStephen', 'Admin'),\n"
                    + "('US-3999995','Kelly@suttoncross.ie','KellyKelly','Doctor'),\n"
                    + "('US-3999994','RichieRich@Beaumont.ie','Richie', 'Pharmacy'\n"
                    + "  );"
            );
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.print(e); //println for catching error, popup would be better
        }

    }

}
