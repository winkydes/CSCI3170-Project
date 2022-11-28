import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//Main.java
class Main {
    //Admin
    //Admin operations1 : create table
    public static void CreateTable(Connection con){
        String Table_Category = "CREATE TABLE CATEGORY(" +
            "cid INTEGER PRIMARY KEY," +
            "cname VARCHAR(20) NOT NULL)";
        String Table_Manufacturer = "CREATE TABLE MANUFACTURER(" +
            "mid INTEGER(2) PRIMARY KEY,"+
            "mname VARCHAR(20) NOT NULL,"+
            "maddress VARCHAR(50) NOT NULL,"+
            "mphonenumber INTEGER(8) NOT NULL)";
        String Table_Part ="CREATE TABLE PART("+
            "pid INTEGER(3) PRIMARY KEY,"+
            "pname VARCHAR(20) NOT NULL,"+
            "pprice INTEGER(5) NOT NULL,"+
            "mid INTEGER(2) NOT NULL,"+
            "cid INTEGER NOT NULL,"+
            "pWarrantlyPeriod INTEGER(2) NOT NULL,"+
            "pAvailableQuantity INTEGER(2) NOT NULL";
        String Table_SalePerson="CREATE TABLE SALEPERSON("+
            "sid INTEGER(2) PRIMARY KEY,"+
            "sname VARCHAR(20) NOT NULL,"+
            "saddress VARCHAR(50) NOT NULL,"+
            "sPhoneNumber INTEGER(8) NOT NULL,"+
            "sExperience INTEGER NOT NULL";
        String Table_Transaction="CREATE TABLE TRANSACTION("+
            "tid INTEGER(4) PRIMARY KEY,"+
            "pid INTEGER(3) NOT NULL,"+
            "sid INTEGER(2) NOT NULL,"+
            "tdate VARCHAR(10) NOT NULL";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Table_Category);
            stmt.executeUpdate(Table_Manufacturer);
            stmt.executeUpdate(Table_Part);
            stmt.executeUpdate(Table_SalePerson);
            stmt.executeUpdate(Table_Transaction);
            System.out.println("Done. Database is initialized.");
        }catch (SQLException ex){
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally{
            Administrator(con);
        }
    }
    public static void Administrator(Connection con){
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWhat kind of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show number of records in each table");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter your choice: ");
        int inputAdmin = sc.nextInt();
        if(inputAdmin==1){
            CreateTable(con);
        }
    }
    // salesperson menu display
    public static void salespersonMenu() {
        String salespersonMenuMsg = "-----Operations for salesperson menu-----\nWhat kinds of operation would you like to perform?\n1. Search for parts\n2. Sell a part\n3. Return to main menu\nEnter Your Choice: ";
        Scanner salespersonChoiceScanner = new Scanner(System.in);
        int salespersonChoice = 0;
        System.out.print(salespersonMenuMsg);
        salespersonChoice = salespersonChoiceScanner.nextInt();
        switch (salespersonChoice) {
            case 1: {
                searchForParts();
            }
            case 2: {
                
            }
            case 3: {
                salesSystem();
            } 
        }
        salespersonChoiceScanner.close();
    }

    // Salesperson: search for parts
    public static void searchForParts() {
        int choice = 0;
        System.out.println("Choose the search criterion:");
        System.out.println("1. Part Name");
        System.out.println("2. Manufacturer Name");
        System.out.print("Choose the search criterion: ");
        Scanner choiceScanner = new Scanner(System.in);
        choice = choiceScanner.nextInt();
        if (choice == 1) {
            // search by part name
            System.out.print("Type in the Search keyword: ");
            Scanner keywordScanner = new Scanner(System.in);
            String keyword = keywordScanner.nextLine();
            System.out.println("Choose ordering:");
            System.out.println("1. By price, ascending order");
            System.out.println("2. By price, descending order");
            System.out.print("Choose the search criterion: ");
            choice = choiceScanner.nextInt();
            if (choice == 1) {
                // TODO: ascending
                System.out.println(choice + keyword);
            } else if (choice == 2) {
                // TODO: descending
                System.out.println(choice + keyword);
            }
            keywordScanner.close();
        } else if (choice == 2) {
            System.out.print("Type in the Search keyword: ");
            Scanner keywordScanner = new Scanner(System.in);
            String keyword = keywordScanner.nextLine();
            System.out.println("Choose ordering:");
            System.out.println("1. By price, ascending order");
            System.out.println("2. By price, descending order");
            System.out.print("Choose the search criterion: ");
            choice = choiceScanner.nextInt();
            if (choice == 1) {
                // TODO: ascending
                System.out.println(choice + keyword);
            } else if (choice == 2) {
                // TODO: descending
                System.out.println(choice + keyword);
            }
            keywordScanner.close();
        }
        choiceScanner.close();
        salesSystem();
    }
    
    public static void salesSystem() {
        int choice = 0;
        Scanner choiceScanner = new Scanner(System.in);
        String mainMenuMsg = "Welcome to sales system! \n \n-----Main menu-----\nWhat kinds of operation would you like to perform?\n1. Operations for administrator\n2. Operations for salesperson\n3. Operations for manager\n4. Exit this program\nEnter Your Choice: ";
        System.out.print(mainMenuMsg);
        choice = choiceScanner.nextInt();
        switch (choice) {
            case 1: {

            }
            case 2: {
                salespersonMenu();
            }
            case 3: {
                
            }
            case 4: {
                System.exit(0);
            }
        }
        choiceScanner.close();
    }
    public static void main(String[] args) throws SQLException {
        String dbAddress = "jdbc:mysql://projgw.cse.cuhk.edu.hk:2633/db18?autoReconnect=true&useSSL=false";
        String dbUsername = "Group18";
        String dbPassword = "CSCI3170";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            con = DriverManager.getConnection(dbAddress, dbUsername, dbPassword);
            System.out.println("Connected to database successfully");
            try {
                salesSystem();
            } catch (Exception e) {
                System.out.println("Fail to get in book system initially with problem" + e);
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
