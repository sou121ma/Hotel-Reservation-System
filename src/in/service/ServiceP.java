package in.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import in.manage.ConnectionProvider;
import in.model.Guest;

public class ServiceP {

    Connection con;

    public ServiceP() {
        ConnectionProvider p = new ConnectionProvider();
        con = p.creaConnection();
    }

    // Reserving room : inserting data in database
    public boolean reserveRoom(Guest g) {

        String q = "INSERT INTO reservations(guest_name,room_number,contact_number) VALUES(?,?,?)";

        try {
            PreparedStatement pst = con.prepareStatement(q);
            pst.setString(1, g.getGuest_name());
            pst.setInt(2, g.getRoom_number());
            pst.setString(3, g.getContact_number());

            int rowEffect = pst.executeUpdate();

            pst.close();
            return rowEffect > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    // view reservation list - working for case 2
    public List<Guest> viewReservations() {
        List<Guest> guests = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM reservations;");

            while (rs.next()) {

                // taking values from result set and store in local variable

                int id = rs.getInt("reservation_id");
                String name = rs.getString("guest_name");
                int room_number = rs.getInt("room_number");
                String contact_number = rs.getString("contact_number");
                Timestamp tp = rs.getTimestamp("reservation_date");
                LocalDateTime date = tp.toLocalDateTime();

                // create guest using those variables and adding in list
                Guest g = new Guest(id, name, room_number, contact_number, date);
                guests.add(g);

            }
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return guests;
    }

    // Getting the room number by guest ID
    public int getRoomNumber(int id) {
        int roomNumber = -1;
        String query = "SELECT room_number FROM reservations WHERE reservation_id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                roomNumber = rs.getInt("room_number");
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomNumber;
    }

    // Updating reservation details
    public boolean updateReservation(int id, Guest g) {
        boolean status = false;
        String query = "UPDATE reservations SET guest_name = ?, room_number = ?, contact_number = ? WHERE reservation_id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, g.getGuest_name());
            pst.setInt(2, g.getRoom_number());
            pst.setString(3, g.getContact_number());
            pst.setInt(4, id);

            int rowsAffected = pst.executeUpdate();
            status = rowsAffected > 0;
            pst.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    // Deleting a reservation
    public boolean deleteReservation(int id) {
        boolean status = false;
        String query = "DELETE FROM reservations WHERE reservation_id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, id);

            int rowsAffected = pst.executeUpdate();
            status = rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return status;
    }

    public void Exit(){
        try {
            con.close();
            System.out.println("\tThanks for using ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkById(int id) {
        int roomNumber = getRoomNumber(id);
        return roomNumber == -1;
    }

    public boolean checkRoomNumber(int room_number) {
        int count = 0;
        String query = "select count(guest_name) from reservations where room_number =?";

        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, room_number);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
               count = rs.getInt(1);
            }
        } catch (SQLException e) {    
            e.printStackTrace();
        }
        return count > 0;
    }

}
