import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;

public class Ticket {
    private LocalTime checkIn;
    private int ticketID;
    private static int currentID;
    private LocalTime checkOut;
    private double cost = 25.0;

    public Ticket(LocalTime checkIn) {
        this.checkIn = checkIn;
        this.ticketID = GetNextID();
    }

    private int GetNextID() {
        return ++currentID;
    }

    public double CheckOut(LocalTime checkOut) {
        this.cost = getHours(checkOut) +  5;
        if (this.cost > 15) {
            this.cost = 15;
        }
        return this.cost;
    }
    public int getHours(LocalTime checkOut) {
        Duration timeDiff = Duration.between(checkIn, checkOut);
        int hours =(int)timeDiff.toHours();
        if (timeDiff.toMinutes()%60 > 0) {
            hours++;
        }
        return hours;
    }

}
