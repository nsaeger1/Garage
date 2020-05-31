package Fee;

import sun.security.krb5.internal.Ticket;

public class LostTicketFee implements FeeStrategy {
    @Override
    public double calculateFee(int hours) {
        return 25.0;
    }
}
