import java.sql.*;
import java.util.Scanner;

public class Customer {

    static Scanner sc = new Scanner(System.in);

    // Customer Registration
    public static void registerCustomer() {

        try {
            Connection con = DBConnection.getConnection();

            System.out.println("\n====== CUSTOMER REGISTRATION ======");

            System.out.print("Enter Name: ");
            sc.nextLine();
            String name = sc.nextLine();

            System.out.print("Enter Gender: ");
            String gender = sc.nextLine();

            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();

            System.out.print("Enter Address: ");
            String address = sc.nextLine();

            System.out.print("Enter ID Proof: ");
            String idProof = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String sql = "INSERT INTO customer(name,gender,phone,address,id_proof,email,password) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, idProof);
            ps.setString(6, email);
            ps.setString(7, password);

            int i = ps.executeUpdate();

            if (i > 0) {
                System.out.println("\nCustomer Registered Successfully.");
            } else {
                System.out.println("\nRegistration Failed.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Customer Login
    public static void customerLogin() {

        try {

            Connection con = DBConnection.getConnection();

            System.out.println("\n========== CUSTOMER LOGIN ==========");

            System.out.print("Email: ");
            String email = sc.next();

            System.out.print("Password: ");
            String password = sc.next();

            String sql = "SELECT * FROM customer WHERE email=? AND password=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int customerId = rs.getInt("customer_id");

                System.out.println("\nLogin Successful.");

                Booking.customerMenu(customerId);

            } else {

                System.out.println("\nInvalid Email or Password.");

            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Admin - View Customers
    public static void viewCustomers() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM customer");

            System.out.println("\n================ CUSTOMER LIST ================");

            while (rs.next()) {

                System.out.println("------------------------------------------");
                System.out.println("Customer ID : " + rs.getInt("customer_id"));
                System.out.println("Name        : " + rs.getString("name"));
                System.out.println("Gender      : " + rs.getString("gender"));
                System.out.println("Phone       : " + rs.getString("phone"));
                System.out.println("Address     : " + rs.getString("address"));
                System.out.println("ID Proof    : " + rs.getString("id_proof"));
                System.out.println("Email       : " + rs.getString("email"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}