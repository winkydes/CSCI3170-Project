import java.util.Scanner;

//HelloWorld.java

class HelloWorld {
    public static String mainMenuMsg = "Welcome to sales system! \n \n-----Main menu-----\nWhat kinds of operation would you like to perform?\n1. Operations for administrator\n2. Operations for salesperson\n3. Operations for manager\n4. Exit this program\nEnter Your Choice: ";
    public static void main(String[] args) {
        Integer choice = 0;
        Scanner choiceScanner = new Scanner(System.in);
        while (choice != 4) {
            System.out.println(mainMenuMsg);
            choice = choiceScanner.nextInt();
            switch (choice) {
                case 1:
                case 2:
            }
        }
        choiceScanner.close();
    }
}
