import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

//Main.java
class Main {
    public static void salesSystem(Connection con) {
        Integer choice = 0;
        Scanner choiceScanner = new Scanner(System.in);
        String mainMenuMsg = "Welcome to sales system! \n \n-----Main menu-----\nWhat kinds of operation would you like to perform?\n1. Operations for administrator\n2. Operations for salesperson\n3. Operations for manager\n4. Exit this program\nEnter Your Choice: ";
        while (choice != 4) {
            System.out.println(mainMenuMsg);
            choice = choiceScanner.nextInt();
            switch (choice) {
                case 1: 
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
                salesSystem(con);
            } catch (Exception e) {
                System.out.println("Fail to get in book system initially");
                System.exit(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
