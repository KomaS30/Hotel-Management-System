import java.sql.*;
import java.util.Scanner;

public class Admin {

    static Scanner sc = new Scanner(System.in);

    public static void adminLogin() {

        System.out.println("\n========== ADMIN LOGIN ==========");

        System.out.print("Username : ");
        String username = sc.next();

        System.out.print("Password : ");
        String password = sc.next();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\nLogin Successful!");

                adminMenu();

            } else {

                System.out.println("\nInvalid Username or Password.");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void adminMenu() {

        while (true) {

            System.out.println("\n========== ADMIN MENU ==========");
            System.out.println("1. Add Room");
            System.out.println("2. View Rooms");
            System.out.println("3. View Customers");
            System.out.println("4. View Bookings");
            System.out.println("5. Logout");

            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    Room.addRoom();
                    break;

                case 2:
                    Room.viewRooms();
                    break;

                case 3:
                    Customer.viewCustomers();
                    break;

                case 4:
                    Booking.viewBookings();
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}