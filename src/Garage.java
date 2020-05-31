import Fee.FeeCalculator;
import Fee.FeeStrategy;

import java.util.Scanner;

public class Garage {

    private Scanner keyboard;


    public static void main(String[] args) {
        new Garage();
    }

    public Garage() {
        TicketList ticketList = TicketList.getInstance();
        keyboard = new Scanner(System.in);
        boolean open = true;
/**
 * we are simulating having two terminals with users coming and going in different orders
 * by having the user first select which terminal they are at.
 */
        while (open) {
            System.out.println("Select Terminal");
            System.out.println("1 - Entry");
            System.out.println("2 - Exit");


            int terminal = Integer.parseInt(keyboard.nextLine());
            if (terminal == 1) {
                Header();
                System.out.println("1 - Check/In");
                System.out.println("2 - Special Event");
                System.out.println("3 - Close Garage");

                int choice = Integer.parseInt(keyboard.nextLine());
                switch (choice) {
                    case 1:
                        ticketList.createTicket();
                        break;
                    case 2:
                        ticketList.createSpecialEvent();
                        break;
                    case 3:
                        ticketList.CloseGarage();
                        EODReceipt();
                        System.out.println("Goodbye");
                        System.exit(0);
                        break;
                }
            } else {
                boolean validTicket = false;
                int choice = 1;
                FeeCalculator feeCalculator = new FeeCalculator();


                Ticket currentTicket = ticketList.getTicket(0);
                while (!validTicket) {
                    Header();
                    /**
                     * If we could take in tickets this would automaticlly read the ticket number or have a button for those who lost their tickets
                     */
                    System.out.println("Insert Ticket Number or 0 for lost ticket");
                    int ticketID = Integer.parseInt(keyboard.nextLine()) - 1;
                    if (ticketID == -1) {
                        validTicket = true;
                        choice = 2;
                    } else {
                        currentTicket = ticketList.getTicket(ticketID);
                        if (currentTicket.getCheckOut() == null) {
                            validTicket = true;
                        }
                    }
                }

                switch (choice) {
                    case 1:
                        currentTicket.CheckOut(Time.getCheckOutTime());
                        if (currentTicket.getSpecialEvent()) {
                            FeeStrategy specialEvent = feeCalculator.getFee("SpecialEvent");
                            currentTicket.setCost(specialEvent.calculateFee(currentTicket.getHours()));
                        } else {
                            FeeStrategy minmax = feeCalculator.getFee("minmax");
                            currentTicket.setCost(minmax.calculateFee(currentTicket.getHours()));
                        }
                        Header();
                        currentTicket.Receipt();
                        break;
                    case 2:
                        /**
                         * even though we have a lost ticket strategy there is no realistic way to apply this to the correct ticket.
                         * Instead we set the ticket cost at creation to 25$ and the default lost ticket to create the receipt.
                         */
                        FeeStrategy lostTicket = feeCalculator.getFee("lostTicket");
                        Header();
                        currentTicket.Receipt();
                        Ticket.NewLostTicket();
                        break;
                }
            }


        }
    }

    private void Header() {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================");

    }

    private void EODReceipt() {
        System.out.println("Activity to Date");
        System.out.println();
        System.out.println("$" + Ticket.getTotalCheckOuts() + " was collected from " + (Ticket.getCurrentID() - (Ticket.getTotalSpecialEvents() + Ticket.getTotalLostTickets())) + " Check Ins");
        System.out.println("$" + Ticket.getTotalSpecialEventsPrice() + " was collected from " + Ticket.getTotalSpecialEvents() + " Special Events");
        System.out.println("$" + Ticket.getTotalLostTicketsPrice() + " was collected from " + Ticket.getTotalLostTickets() + " Lost Tickets");
        System.out.println();
        System.out.println("$" + (Ticket.getTotalSpecialEventsPrice() + Ticket.getTotalLostTicketsPrice() + Ticket.getTotalCheckOuts()) + " was collected overall");

    }
}
