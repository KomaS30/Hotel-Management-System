import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n======================================");
            System.out.println("      HOTEL MANAGEMENT SYSTEM");
            System.out.println("======================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Registration");
            System.out.println("3. Customer Login");
            System.out.println("4. Exit");
            System.out.print("Enter Your Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    Admin.adminLogin();
                    break;

                case 2:
                    Customer.registerCustomer();
                    break;

                case 3:
                    Customer.customerLogin();
                    break;

                case 4:
                    System.out.println("\nThank You For Using Hotel Management System.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nInvalid Choice! Please Try Again.");
            }
        }
    }
}