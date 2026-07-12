import java.sql.*;
import java.util.Scanner;

public class Booking {

    static Scanner sc = new Scanner(System.in);

    // Customer Menu
    public static void customerMenu(int customerId) {

        while (true) {

            System.out.println("\n========== CUSTOMER MENU ==========");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View My Booking");
            System.out.println("4. Check Out");
            System.out.println("5. Logout");
            System.out.print("Enter Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    Room.availableRooms();
                    break;

                case 2:
                    bookRoom(customerId);
                    break;

                case 3:
                    viewMyBooking(customerId);
                    break;

                case 4:
                    checkOut(customerId);
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid Choice.");
            }
        }
    }

    // Book Room
    public static void bookRoom(int customerId) {

        try {

            Room.availableRooms();

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Room Number : ");
            int roomNo = sc.nextInt();

            System.out.print("Enter Check In Date (YYYY-MM-DD): ");
            String checkIn = sc.next();

            System.out.print("Enter Check Out Date (YYYY-MM-DD): ");
            String checkOut = sc.next();

            PreparedStatement ps1 = con.prepareStatement(
                    "SELECT price FROM room WHERE room_no=?");

            ps1.setInt(1, roomNo);

            ResultSet rs = ps1.executeQuery();

            double amount = 0;

            if (rs.next()) {
                amount = rs.getDouble("price");
            }

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO booking(customer_id,room_no,check_in,check_out,total_amount) VALUES(?,?,?,?,?)");

            ps.setInt(1, customerId);
            ps.setInt(2, roomNo);
            ps.setString(3, checkIn);
            ps.setString(4, checkOut);
            ps.setDouble(5, amount);

            int i = ps.executeUpdate();

            if (i > 0) {

                PreparedStatement ps2 = con.prepareStatement(
                        "UPDATE room SET status='Booked' WHERE room_no=?");

                ps2.setInt(1, roomNo);
                ps2.executeUpdate();

                System.out.println("Room Booked Successfully.");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View My Booking
    public static void viewMyBooking(int customerId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM booking WHERE customer_id=?");

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println("-----------------------------");
                System.out.println("Booking ID : " + rs.getInt("booking_id"));
                System.out.println("Room No : " + rs.getInt("room_no"));
                System.out.println("Check In : " + rs.getDate("check_in"));
                System.out.println("Check Out : " + rs.getDate("check_out"));
                System.out.println("Amount : " + rs.getDouble("total_amount"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Admin - View All Bookings
    public static void viewBookings() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM booking");

            while (rs.next()) {

                System.out.println("-----------------------------");
                System.out.println("Booking ID : " + rs.getInt("booking_id"));
                System.out.println("Customer ID : " + rs.getInt("customer_id"));
                System.out.println("Room No : " + rs.getInt("room_no"));
                System.out.println("Check In : " + rs.getDate("check_in"));
                System.out.println("Check Out : " + rs.getDate("check_out"));
                System.out.println("Amount : " + rs.getDouble("total_amount"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Check Out
    public static void checkOut(int customerId) {

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT room_no FROM booking WHERE customer_id=?");

            ps.setInt(1, customerId);

            ResultSet rs = ps.executeQuery();

            int roomNo = 0;

            if (rs.next()) {

                roomNo = rs.getInt("room_no");

                PreparedStatement ps1 = con.prepareStatement(
                        "DELETE FROM booking WHERE customer_id=?");

                ps1.setInt(1, customerId);

                ps1.executeUpdate();

                PreparedStatement ps2 = con.prepareStatement(
                        "UPDATE room SET status='Available' WHERE room_no=?");

                ps2.setInt(1, roomNo);

                ps2.executeUpdate();

                System.out.println("Checked Out Successfully.");
            }
            else {
                System.out.println("No Booking Found.");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}