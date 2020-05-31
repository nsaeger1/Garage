import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketList {

    /**
     * This Class implementes singleton so there is only one list of tickets being utilized at a time.
     */
    private TicketList() {
        initTicketList();
    }

    protected List<Ticket> tickets = new ArrayList<>();

    private static TicketList instance = null;

    public static TicketList getInstance() {
        if (instance == null) {
            instance = new TicketList() {};
        }
        return instance;
    }
    public void createTicket() {
        Ticket ticket = new Ticket(Time.getCheckInTime());
        tickets.add(ticket);
    }
    public void createSpecialEvent() {
        Ticket ticket = new Ticket(Time.getCheckInTime(), true);
        tickets.add(ticket);
    }
    public Ticket getTicket(int ticket) {
        if (ticket == 0) {
            return tickets.get(0);
        } else {
            return tickets.get(ticket);
        }
    }
    public void CloseGarage() {
        try {
            CSV.Write("ticket.csv",tickets);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }




    protected void initTicketList() {
        try {
            tickets = CSV.Read("ticket.csv");
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }

    }
}
