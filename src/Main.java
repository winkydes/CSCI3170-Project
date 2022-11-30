import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

//Main.java
class Main {
    // Admin operations1 : create table
    public static void CreateTable(Connection con) {
        String Table_Category = "CREATE TABLE CATEGORY " +
                "(cid INTEGER NOT NULL, " +
                " cname VARCHAR(20) NOT NULL, " +
                " PRIMARY KEY ( cid ))";
        String Table_Manufacturer = "CREATE TABLE MANUFACTURER " +
                "(mid INTEGER(2) NOT NULL, " +
                " mname VARCHAR(20) NOT NULL, " +
                " maddress VARCHAR(50) NOT NULL, " +
                " mphonenumber INTEGER(8) NOT NULL, " +
                " PRIMARY KEY ( mid ))";
        String Table_Part = "CREATE TABLE PART " +
                "(pid INTEGER(3) NOT NULL, " +
                " pname VARCHAR(20) NOT NULL, " +
                " pprice INTEGER(5) NOT NULL, " +
                " mid INTEGER(2) NOT NULL, " +
                " cid INTEGER NOT NULL, " +
                " pWarrantyPeriod INTEGER(2) NOT NULL, " +
                " pAvailableQuantity INTEGER(2) NOT NULL, " +
                " PRIMARY KEY ( pid ))";
        String Table_SalesPerson = "CREATE TABLE SALESPERSON " +
                "(sid INTEGER(2) NOT NULL, " +
                " sname VARCHAR(20) NOT NULL, " +
                " saddress VARCHAR(50) NOT NULL, " +
                " sPhoneNumber INTEGER(8) NOT NULL, " +
                " sExperience INTEGER NOT NULL, " +
                " PRIMARY KEY ( sid ))";
        String Table_Transaction = "CREATE TABLE TRANSACTION " +
                "(tid INTEGER(4) NOT NULL, " +
                " pid INTEGER(3) NOT NULL, " +
                " sid INTEGER(2) NOT NULL, " +
                " tdate VARCHAR(10) NOT NULL, " +
                " PRIMARY KEY ( tid ))";
        try {
            System.out.print("Processing...");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Table_Category);
            stmt.executeUpdate(Table_Manufacturer);
            stmt.executeUpdate(Table_Part);
            stmt.executeUpdate(Table_SalesPerson);
            stmt.executeUpdate(Table_Transaction);
            System.out.print("Done. Database is initialized.");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            Administrator(con);
        }
    }

    public static void DropTable(Connection con) {
        String Delete_Category = "DROP TABLE IF EXISTS CATEGORY";
        String Delete_Manufacturer = "DROP TABLE IF EXISTS MANUFACTURER";
        String Delete_Part = "DROP TABLE IF EXISTS PART";
        String Delete_SalesPerson = "DROP TABLE IF EXISTS SALESPERSON";
        String Delete_Transaction = "DROP TABLE IF EXISTS TRANSACTION";
        try {
            System.out.println("Processing...");
            Statement stmt = con.createStatement();
            stmt.executeUpdate(Delete_Category);
            stmt.executeUpdate(Delete_Manufacturer);
            stmt.executeUpdate(Delete_Part);
            stmt.executeUpdate(Delete_SalesPerson);
            stmt.executeUpdate(Delete_Transaction);
            System.out.println("Done. Database is removed.");
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            Administrator(con);
        }
    }

    public static void Loadcategory(Connection con, String path) {
        try {
            Scanner infile = new Scanner(new File("./" + path + "/category.txt"));
            String dataTXT = "";
            String dataSQL = "";
            String InsertCategorySQL = "INSERT INTO CATEGORY VALUES";
            while (infile.hasNextLine()) {
                dataTXT = dataTXT + infile.nextLine();
                String[] row = new String[2];
                row = dataTXT.split("\\t");
                for (int i = 0; i < 2; i++) {
                    if (i == 0) {
                        dataSQL += row[i];
                    } else {
                        dataSQL += ", '" + row[i] + "'";
                    }
                }
                Statement stmt = con.createStatement();
                try {
                    stmt.executeUpdate(InsertCategorySQL + "(" + dataSQL + ")");
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                dataTXT = "";
                dataSQL = "";
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void LoadManufacturer(Connection con, String path) {
        try {
            Scanner infile = new Scanner(new File("./" + path + "/manufacturer.txt"));
            String dataTXT = "";
            String dataSQL = "";
            String InsertManufacturerSQL = "INSERT INTO MANUFACTURER VALUES";
            while (infile.hasNextLine()) {
                dataTXT = dataTXT + infile.nextLine();
                String[] row = new String[4];
                row = dataTXT.split("\\t");
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        dataSQL += row[i];
                    } else {
                        dataSQL += ", '" + row[i] + "'";
                    }
                }
                Statement stmt = con.createStatement();
                try {
                    stmt.executeUpdate(InsertManufacturerSQL + "(" + dataSQL + ")");
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                dataTXT = "";
                dataSQL = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void LoadPart(Connection con, String path) {
        try {
            Scanner infile = new Scanner(new File("./" + path + "/part.txt"));
            String dataTXT = "";
            String dataSQL = "";
            String InsertPartSQL = "INSERT INTO PART VALUES";
            while (infile.hasNextLine()) {
                dataTXT = dataTXT + infile.nextLine();
                String[] row = new String[7];
                row = dataTXT.split("\\t");
                for (int i = 0; i < 7; i++) {
                    if (i == 0) {
                        dataSQL += row[i];
                    } else {
                        dataSQL += ", '" + row[i] + "'";
                    }
                }
                Statement stmt = con.createStatement();
                try {
                    stmt.executeUpdate(InsertPartSQL + "(" + dataSQL + ")");
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                dataTXT = "";
                dataSQL = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void LoadSalesperson(Connection con, String path) {
        try {
            Scanner infile = new Scanner(new File("./" + path + "/salesperson.txt"));
            String dataTXT = "";
            String dataSQL = "";
            String InsertSalespersonSQL = "INSERT INTO SALESPERSON VALUES";
            while (infile.hasNextLine()) {
                dataTXT = dataTXT + infile.nextLine();
                String[] row = new String[5];
                row = dataTXT.split("\\t");
                for (int i = 0; i < 5; i++) {
                    if (i == 0) {
                        dataSQL += row[i];
                    } else {
                        dataSQL += ", '" + row[i] + "'";
                    }
                }
                Statement stmt = con.createStatement();
                try {
                    stmt.executeUpdate(InsertSalespersonSQL + "(" + dataSQL + ")");
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                dataTXT = "";
                dataSQL = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void LoadTransaction(Connection con, String path) {
        try {
            Scanner infile = new Scanner(new File("./" + path + "/transaction.txt"));
            String dataTXT = "";
            String dataSQL = "";
            String InsertTransactionSQL = "INSERT INTO TRANSACTION VALUES";
            while (infile.hasNextLine()) {
                dataTXT = dataTXT + infile.nextLine();
                String[] row = new String[4];
                row = dataTXT.split("\\t");
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        dataSQL += row[i];
                    } else {
                        dataSQL += ", '" + row[i] + "'";
                    }
                }
                Statement stmt = con.createStatement();
                try {
                    stmt.executeUpdate(InsertTransactionSQL + "(" + dataSQL + ")");
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                dataTXT = "";
                dataSQL = "";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void LoadDataFile(Connection con) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nType in the Source Data Folder Path: ");
        String path = sc.next();
        try {
            Loadcategory(con, path);
            LoadPart(con, path);
            LoadManufacturer(con, path);
            LoadSalesperson(con, path);
            LoadTransaction(con, path);
            System.out.println("Data is inputted to the database.");
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            Administrator(con);
        }
        sc.close();
    }

    public static void Administrator(Connection con) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nWhat kind of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show number of records in each table");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter your choice: ");
        int inputAdmin = sc.nextInt();
        if (inputAdmin == 1) {
            CreateTable(con);
        } else if (inputAdmin == 2) {
            DropTable(con);
        } else if (inputAdmin == 3) {
            LoadDataFile(con);
        } else if (inputAdmin == 4) {

        } else if (inputAdmin == 5) {
            salesSystem(con);
        }
        sc.close();
    }

    // salesperson menu display
    public static void salespersonMenu(Connection con) {
        int salespersonChoice = 0;
        String salespersonMenuMsg = "-----Operations for salesperson menu-----\nWhat kinds of operation would you like to perform?\n1. Search for parts\n2. Sell a part\n3. Return to main menu\nEnter Your Choice: ";
        Scanner salespersonChoiceScanner = new Scanner(System.in);
        System.out.print(salespersonMenuMsg);
        salespersonChoice = salespersonChoiceScanner.nextInt();
        switch (salespersonChoice) {
            case 1: {
                searchForParts(con);
                break;
            }
            case 2: {
                sellParts(con);
                break;
            }
            case 3: {
                salesSystem(con);
                break;
            }
            default: {
                System.out.println("Wrong Input, please try again.");
                salespersonChoice = 0;
                salespersonMenu(con);
                break;
            }
        }
        salespersonChoiceScanner.close();
    }

    // Salesperson: search for parts
    public static void searchForParts(Connection con) {
        int choice = 0;
        System.out.println("Choose the search criterion:");
        System.out.println("1. Part Name");
        System.out.println("2. Manufacturer Name");
        System.out.print("Choose the search criterion: ");
        Scanner choiceScanner = new Scanner(System.in);
        choice = choiceScanner.nextInt();
        if (choice == 1) {
            // search by part name
            int validCount = 0;
            while (validCount <= 0) {
                System.out.print("Type in the Search keyword: ");
                Scanner keywordScanner = new Scanner(System.in);
                String keyword = keywordScanner.nextLine();
                System.out.println("Choose ordering:");
                System.out.println("1. By price, ascending order");
                System.out.println("2. By price, descending order");
                System.out.print("Choose the search criterion: ");
                choice = choiceScanner.nextInt();
                ResultSet rs = null;
                try {
                    Statement stmt = con.createStatement();
                    if (choice == 1) {
                        rs = stmt.executeQuery(
                                "SELECT p.PID, p.PNAME, m.MNAME, c.CNAME, p.PAVAILABLEQUANTITY, p.PWARRANTYPERIOD, p.PPRICE FROM PART p, MANUFACTURER m, CATEGORY c WHERE c.CID = p.CID AND m.MID = p.MID AND p.PNAME LIKE '%"
                                        + keyword + "%' ORDER BY p.PPRICE ASC");
                    } else if (choice == 2) {
                        rs = stmt.executeQuery(
                                "SELECT p.PID, p.PNAME, m.MNAME, c.CNAME, p.PAVAILABLEQUANTITY, p.PWARRANTYPERIOD, p.PPRICE FROM PART p, MANUFACTURER m, CATEGORY c WHERE c.CID = p.CID AND m.MID = p.MID AND p.PNAME LIKE '%"
                                        + keyword + "%' ORDER BY p.PPRICE DESC");
                    }
                    if (rs.next()) {
                        validCount = rs.getInt(1);
                    } else {
                        System.out.println("Error: No such part found, please try again.");
                        continue;
                    }
                    rs.beforeFirst();
                    System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (rs.next()) {
                        System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3)
                                + " | " + rs.getString(4) + " | " + rs.getInt(5) + " | " + rs.getInt(6) + " | "
                                + rs.getInt(7) + " |");
                    }
                    System.out.println("End of Query");
                    salespersonMenu(con);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                keywordScanner.close();
            }

        } else if (choice == 2) {
            // search by manufacturer name
            int validCount = 0;
            while (validCount <= 0) {
                System.out.print("Type in the Search keyword: ");
                Scanner keywordScanner = new Scanner(System.in);
                String keyword = keywordScanner.nextLine();
                System.out.println("Choose ordering:");
                System.out.println("1. By price, ascending order");
                System.out.println("2. By price, descending order");
                System.out.print("Choose the search criterion: ");
                choice = choiceScanner.nextInt();
                ResultSet rs = null;
                try {
                    Statement stmt = con.createStatement();
                    if (choice == 1) {
                        rs = stmt.executeQuery(
                                "SELECT p.PID, p.PNAME, m.MNAME, c.CNAME, p.PAVAILABLEQUANTITY, p.PWARRANTYPERIOD, p.PPRICE FROM PART p, MANUFACTURER m, CATEGORY c WHERE c.CID = p.CID AND m.MID = p.MID AND m.MNAME LIKE '%"
                                        + keyword + "%' ORDER BY p.PPRICE ASC");
                    } else if (choice == 2) {
                        rs = stmt.executeQuery(
                                "SELECT p.PID, p.PNAME, m.MNAME, c.CNAME, p.PAVAILABLEQUANTITY, p.PWARRANTYPERIOD, p.PPRICE FROM PART p, MANUFACTURER m, CATEGORY c WHERE c.CID = p.CID AND m.MID = p.MID AND m.MNAME LIKE '%"
                                        + keyword + "%' ORDER BY p.PPRICE DESC");
                    }
                    if (rs.next()) {
                        validCount = rs.getInt(1);
                    } else {
                        System.out.println("Error: No such part found, please try again.");
                        continue;
                    }
                    rs.beforeFirst();
                    System.out.println("| ID | Name | Manufacturer | Category | Quantity | Warranty | Price |");
                    while (rs.next()) {
                        System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " + rs.getString(3)
                                + " | " + rs.getString(4) + " | " + rs.getInt(5) + " | " + rs.getInt(6) + " | "
                                + rs.getInt(7) + " |");
                    }
                    System.out.println("End of Query");
                    salespersonMenu(con);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                keywordScanner.close();
            }
        }
        choiceScanner.close();
    }

    // salesperson: sell a part
    public static void sellParts(Connection con) {
        int partId = 0;
        int salespersonId = 0;
        Scanner partIdScanner = new Scanner(System.in);
        Scanner salesPersonIdScanner = new Scanner(System.in);
        System.out.print("Enter The Part ID: ");
        partId = partIdScanner.nextInt();
        System.out.print("Enter The Salesperson ID: ");
        salespersonId = salesPersonIdScanner.nextInt();
        salesPersonIdScanner.close();
        partIdScanner.close();
        // TODO: Implement transaction query
        salesSystem(con);
    }

    // Manager menu
    public static void ManagerMenu(Connection con) {
        int ManagerChoice = 0;
        Scanner ManagerChoiceScanner = new Scanner(System.in);
        while (ManagerChoice == 0) {
            String ManagerMenuMsg = "-----Operations for manager menu-----\nWhat kinds of operation would you like to perform?\n1. List all salespersons\n2. Count the no. of transaction records of each salesperson within a specific range on years of experience\n3. Show the total sales value of each manufacturer\n4. Show the N most popular part\n5. Return to the main menu.\nEnter Your Choice: ";
            System.out.print(ManagerMenuMsg);
            ManagerChoice = ManagerChoiceScanner.nextInt();
            switch (ManagerChoice) {
                case 1: {
                    ListSalesperson(con);
                    break;
                }
                case 2: {
                    CountNumTransaction(con);
                    break;
                }
                case 3: {
                    TotalSalesValue(con);
                    break;
                }
                case 4: {
                    NMostPopular(con);
                    break;
                }
                case 5: {
                    salesSystem(con);
                    break;
                }
                default: {
                    System.out.println("Wrong Input, please try again.");
                    ManagerChoice = 0;
                    break;
                }
            }
        }
        ManagerChoiceScanner.close();
    }

    public static void ListSalesperson(Connection con) {
        String ListSalespersonMsg = "Choose ordering:\n1. By ascending order\n2. By descending order\nChoose the list ordering: ";
        System.out.print(ListSalespersonMsg);
        int OrderChoice = 0;
        String Order = null;
        Scanner OrderChoiceScanner = new Scanner(System.in);
        while (OrderChoice == 0) {

            OrderChoice = OrderChoiceScanner.nextInt();
            if (OrderChoice == 1) {
                Order = "ASC";
            } else if (OrderChoice == 2) {
                Order = "DESC";
            } else {
                System.out.println("Wrong Input, please try again.");
                OrderChoice = 0;
            }
        }

        String SelectSalespersons = "SELECT sid, sname, sPhoneNumber, sExperience FROM SALESPERSON ORDER BY sExperience ";

        int validCount = 0;
        while (validCount <= 0) {
            ResultSet rs = null;
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(SelectSalespersons + Order);
                if (rs.next()) {
                    validCount = rs.getInt(1);
                } else {
                    System.out.println("Error: No such element found, please try again.");
                    continue;
                }
                rs.beforeFirst();
                System.out.println("| ID | Name | Mobile Phone | Years of Experience |");
                while (rs.next()) {
                    System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " +
                            rs.getInt(3) + " | " + rs.getInt(4) + " |");
                }
                System.out.println("End of Query");
                ManagerMenu(con);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        OrderChoiceScanner.close();
    }

    public static void CountNumTransaction(Connection con) {
        int upper = 0;
        int lower = 1;
        Scanner BoundScanner = new Scanner(System.in);
        while (lower > upper) {
            System.out.print("Type in the lower bound for years of experience: ");
            lower = BoundScanner.nextInt();
            System.out.print("Type in the upper bound for years of experience: ");
            upper = BoundScanner.nextInt();
            if (lower > upper) {
                System.out.println("Error: Lower bound greater than upper bound, please try again.");
                continue;
            }
        }
        String CountNunTransEachSale = "SELECT S.sid, S.sname, S.sExperience, COUNT(*) FROM SALESPERSON S JOIN TRANSACTION T ON S.sid LIKE T.sid WHERE S.sExperience >= "
                + lower + " AND S.sExperience <= " + upper + " GROUP BY S.sname ORDER BY S.sid DESC";

        int validCount = 0;
        while (validCount <= 0) {
            ResultSet rs = null;
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(CountNunTransEachSale);
                if (rs.next()) {
                    validCount = rs.getInt(1);
                } else {
                    System.out.println("Error: No such element found, please try agaisn.");
                    continue;
                }
                rs.beforeFirst();
                System.out.println("| ID | Name | Years of Experience | Number of Transaction | ");
                while (rs.next()) {
                    System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " +
                            rs.getInt(3) + " | " + rs.getInt(4) + " |");
                }
                System.out.println("End of Query");
                ManagerMenu(con);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        BoundScanner.close();
    }

    public static void TotalSalesValue(Connection con) {
        String TotSalesValEachManu = "SELECT M.mid, M.mname, SUM(P.pprice) FROM MANUFACTURER M JOIN PART P ON M.mid LIKE P.mid JOIN TRANSACTION T ON T.pid LIKE P.pid GROUP BY M.mid ORDER BY SUM(P.pprice) DESC";
        // TODO: TotSalesValEachManu Query
        // SELECT M.mid, M.mname, SUM(P.pprice) FROM MANUFACTURER M JOIN PART P ON M.mid
        // LIKE P.mid JOIN TRANSACTION T ON T.pid LIKE M.pid GROUP BY M.mid ORDER BY
        // SUM(P.pprice)

        int validCount = 0;
        while (validCount <= 0) {
            ResultSet rs = null;
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(TotSalesValEachManu);
                if (rs.next()) {
                    validCount = rs.getInt(1);
                } else {
                    System.out.println("Error: No such element found, please try agaisn.");
                    continue;
                }
                rs.beforeFirst();
                System.out.println(" | Manufacturer ID | Manufacturer Name | Total Sales Value | ");
                while (rs.next()) {
                    System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " +
                            rs.getInt(3) + " | ");
                }
                System.out.println("End of Query");
                ManagerMenu(con);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void NMostPopular(Connection con) {
        String NMostPopParts = "";
        // TODO: NMostPopParts Query
        int validCount = 0;
        while (validCount <= 0) {
            ResultSet rs = null;
            try {
                Statement stmt = con.createStatement();
                rs = stmt.executeQuery(NMostPopParts);
                if (rs.next()) {
                    validCount = rs.getInt(1);
                } else {
                    System.out.println("Error: No such element found, please try again.");
                    continue;
                }
                rs.beforeFirst();
                System.out.println("| Part ID | Part Name | No. of Transaction | ");
                while (rs.next()) {
                    System.out.println("| " + rs.getInt(1) + " | " + rs.getString(2) + " | " +
                            rs.getInt(3) + " | ");
                }
                System.out.println("End of Query");
                salesSystem(con);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        salesSystem(con);
    }

    public static void salesSystem(Connection con) {
        int choice = 0;
        while (choice == 0) {
            Scanner choiceScanner = new Scanner(System.in);
            String mainMenuMsg = "Welcome to sales system! \n \n-----Main menu-----\nWhat kinds of operation would you like to perform?\n1. Operations for administrator\n2. Operations for salesperson\n3. Operations for manager\n4. Exit this program\nEnter Your Choice: ";
            System.out.print(mainMenuMsg);
            choice = choiceScanner.nextInt();
            switch (choice) {
                case 1: {
                    Administrator(con);
                    break;
                }
                case 2: {
                    salespersonMenu(con);
                    break;
                }
                case 3: {
                    ManagerMenu(con);
                    break;
                }
                case 4: {
                    System.exit(0);
                    break;
                }
                default: {
                    System.out.println("Invalid Choice");
                    choice = 0;
                    break;
                }
            }
            choiceScanner.close();
        }
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
                salesSystem(con);
            } catch (Exception e) {
                System.out.println("Fail to get in sales system initially with problem" + e);
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
