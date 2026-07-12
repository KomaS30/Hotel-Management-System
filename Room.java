import java.sql.*;
import java.util.Scanner;

public class Room {

    static Scanner sc = new Scanner(System.in);

    // Add Room
    public static void addRoom() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Room Number : ");
            int roomNo = sc.nextInt();

            System.out.print("Enter Room Type (Single/Double/Deluxe/Suite): ");
            String roomType = sc.next();

            System.out.print("Enter Price : ");
            double price = sc.nextDouble();

            String sql = "INSERT INTO room(room_no,room_type,price,status) VALUES(?,?,?,'Available')";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, roomNo);
            ps.setString(2, roomType);
            ps.setDouble(3, price);

            int i = ps.executeUpdate();

            if (i > 0)
                System.out.println("Room Added Successfully.");
            else
                System.out.println("Failed to Add Room.");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Rooms
    public static void viewRooms() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM room");

            System.out.println("\n=========== ROOM DETAILS ===========");

            while (rs.next()) {

                System.out.println("---------------------------------");
                System.out.println("Room No : " + rs.getInt("room_no"));
                System.out.println("Room Type : " + rs.getString("room_type"));
                System.out.println("Price : " + rs.getDouble("price"));
                System.out.println("Status : " + rs.getString("status"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Available Rooms
    public static void availableRooms() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM room WHERE status='Available'");

            System.out.println("\n======= AVAILABLE ROOMS =======");

            while (rs.next()) {

                System.out.println("Room No : " + rs.getInt("room_no")
                        + " | Type : " + rs.getString("room_type")
                        + " | Price : " + rs.getDouble("price"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Update Room
    public static void updateRoom() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Room Number : ");
            int roomNo = sc.nextInt();

            System.out.print("Enter New Price : ");
            double price = sc.nextDouble();

            String sql = "UPDATE room SET price=? WHERE room_no=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDouble(1, price);
            ps.setInt(2, roomNo);

            int i = ps.executeUpdate();

            if (i > 0)
                System.out.println("Room Updated Successfully.");
            else
                System.out.println("Room Not Found.");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Room
    public static void deleteRoom() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.print("Enter Room Number : ");
            int roomNo = sc.nextInt();

            String sql = "DELETE FROM room WHERE room_no=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, roomNo);

            int i = ps.executeUpdate();

            if (i > 0)
                System.out.println("Room Deleted Successfully.");
            else
                System.out.println("Room Not Found.");

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}