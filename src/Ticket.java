import Fee.FeeCalculator;
import Fee.FeeStrategy;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class Ticket {
    private LocalTime checkIn;
    private int ticketID;
    private static int currentID;
    private LocalTime checkOut;
    private double cost = 25.0;
    private boolean specialEvent = false;
    private static double totalCheckOuts;
    private static int totalLostTickets;
    private static int totalSpecialEvents;

    public Ticket(LocalTime checkIn) {
        this.checkIn = checkIn;
        this.ticketID = GetNextID();
    }

    public Ticket(LocalTime checkIn, Boolean specialEvent) {
        this.checkIn = checkIn;
        this.ticketID = GetNextID();
        this.specialEvent = specialEvent;
        NewSpecialEvent();

    }

    public static void NewLostTicket() {
        totalLostTickets++;
    }
    public static void NewSpecialEvent() {
        totalSpecialEvents++;
    }


    public static double getTotalCheckOuts() {
        return totalCheckOuts;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public static int getCurrentID() {
        return currentID;
    }
    public boolean getSpecialEvent() {
        return specialEvent;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public static int getTotalLostTickets() {
        return totalLostTickets;
    }

    public static double getTotalLostTicketsPrice() {
        return totalLostTickets * 25.0;
    }

    public static int getTotalSpecialEvents() {return totalSpecialEvents;}

    public static double getTotalSpecialEventsPrice() {
        return totalSpecialEvents * 20.0;
    }

    private int GetNextID() {
        return ++currentID;
    }

    public void CheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    public int getHours() {
        Duration timeDiff = Duration.between(checkIn, checkOut);
        int hours = (int) timeDiff.toHours();
        if (timeDiff.toMinutes() % 60 > 0) {
            hours++;
        }
        return hours;
    }

    public void Receipt() {
        try {
            System.out.println("Receipt for a vehicle id " + this.ticketID);
            System.out.println();
            System.out.println(getHours() + " hours parked  " + this.checkIn + " - " + this.checkOut);
            System.out.println("$" + this.cost);
        } catch (NullPointerException e) {
            System.out.println("Receipt for a vehicle id " + this.ticketID);
            System.out.println();
            System.out.println("Lost Ticket");
            System.out.println("$" + this.cost);
        }
    }

    public Ticket(String[] ticket) {
        String[] checkin = ticket[0].split(":");
        String[] checkout = ticket[2].split(":");

        try {
            this.specialEvent = Boolean.parseBoolean(ticket[4]);
            if (this.specialEvent) {
                NewSpecialEvent();
            }
            this.checkIn = LocalTime.of(Integer.parseInt(checkin[0]), Integer.parseInt(checkin[1]));
            this.ticketID = Integer.parseInt(ticket[1]);
            currentID = this.ticketID;
            this.checkOut = LocalTime.of(Integer.parseInt(checkout[0]), Integer.parseInt(checkout[1]));
            this.cost = Double.parseDouble(ticket[3]);
            totalCheckOuts = totalCheckOuts + this.cost;


        } catch (Exception e) {
            NewLostTicket();
        }
    }

    @Override
    public String toString() {
        return checkIn +
                "," + ticketID +
                "," + checkOut +
                "," + cost +
                "," + specialEvent;
    }
}
