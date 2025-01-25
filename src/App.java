import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import in.model.Guest;
import in.service.ServiceP;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServiceP service = new ServiceP();

        System.out.println("Hello, World!");

        while (true) {
            System.out.println("1.Reserve a room");
            System.out.println("2.View reservation");
            System.out.println("3.Get room number");
            System.out.println("4.Update reservation");
            System.out.println("5.Delete reservation");
            System.out.println("0.Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {

                case 0:
                    // closing application
                    sc.close();
                    service.Exit();
                    System.exit(0);
                    break;

                case 1:
                    // Adding new guest
                    System.out.print("\tEnter guest name: ");
                    String guestName = sc.nextLine();
                    int room_number;
                    boolean roomStatus;
                    do {      
                        System.out.print("\tEnter room number: ");
                        room_number = sc.nextInt();
                        roomStatus = service.checkRoomNumber(room_number);
                        if (roomStatus) System.out.println("Room Number Already allocated"); 
                        if(room_number == 0) System.out.println("Room number can't be Zero");                 
                    } while ( roomStatus || room_number == 0);
                    
                    sc.nextLine(); // Consume one line

                    System.out.print("\tEnter contact number: ");
                    String contact_number = sc.nextLine();

                    Guest g = new Guest(guestName, room_number, contact_number);
                    service.reserveRoom(g);

                    break;

                case 2:
                    // View all guest
                    List<Guest> viewReservations = service.viewReservations();
                    if (viewReservations.isEmpty()) {
                        System.out.println("\t\tNo reservation found");
                    }
                    ListIterator<Guest> listIterator = viewReservations.listIterator();
                    while (listIterator.hasNext()) {
                        System.out.println("\t******************************************");
                        System.out.println("\t" + listIterator.next());
                    }
                    System.out.println("\t******************************************");

                    break;

                case 3:
                    // Get room number by id
                    System.out.print("\tEnter guest id: ");
                    int id = sc.nextInt();
                    System.out.println(service.getRoomNumber(id) == -1? "\tGuest Id doesn't match...":"\tRoom Number: " + service.getRoomNumber(id));
                    break;

                case 4:
                //Updating guest
                    System.out.print("\tEnter guest Id: ");
                    id = sc.nextInt();
                    if(service.checkById(id)){
                        System.out.println("\t\tGuest id not Found");
                        break;
                    }
                    sc.nextLine(); // Consume one line

                    System.out.print("\tEnter guest name: ");
                    guestName = sc.nextLine();

                    do {      
                        System.out.print("\tEnter room number: ");
                        room_number = sc.nextInt();
                        roomStatus = service.checkRoomNumber(room_number);
                        if (roomStatus) System.out.println("Room Number Already allocated"); 
                        if(room_number == 0) System.out.println("Room number can't be Zero");                 
                    } while ( roomStatus || room_number == 0);

                    sc.nextLine(); // Consume one line

                    System.out.print("\tEnter contact number: ");
                    contact_number = sc.nextLine();

                    g = new Guest(guestName, room_number, contact_number);
                    String status = (service.updateReservation(id, g)) ? "Successfully Updated"
                            : "Unsuccessful due to some error";
                    System.out.println("\t\t" + status);
                    break;

                case 5:
                    System.out.print("\tEnter guest id: ");
                    id = sc.nextInt();
                    if (service.checkById(id)) {
                        System.out.println("\t\tNo Guest found with this id");
                        break;
                    }
                    status = (service.deleteReservation(id)) ? "Successfully Deleted":"Unsuccessful due to some error";
                    System.out.println("\t\t" + status);

                    break;

                default:
                    System.out.println("\tInvalid input");

            }

        }

    }
}
