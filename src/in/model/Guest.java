package in.model;

import java.time.LocalDateTime;

public class Guest {
    private int id;
    private String guest_name;
    private int room_number;
    private String contact_number;
    private LocalDateTime reservation_date;

    

    public Guest(String guest_name, int room_number, String contact_number) {
        this.guest_name = guest_name;
        this.room_number = room_number;
        this.contact_number = contact_number;
    }

    

    public Guest(int id, String guest_name, int room_number, String contact_number, LocalDateTime reservation_date) {
        this(guest_name,room_number,contact_number); //Constructor Chaining 
        this.id = id;
        this.reservation_date = reservation_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public LocalDateTime getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDateTime reservation_date) {
        this.reservation_date = reservation_date;
    }

    @Override
    public String toString() {
        return "Guest [id=" + id + ", guest_name=" + guest_name + ", room_number=" + room_number + ", contact_number="
                + contact_number + ", reservation_date=" + reservation_date.toLocalDate() + "]";
    }

}
