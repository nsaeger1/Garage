package Fee;

import sun.security.krb5.internal.Ticket;

public class SpecialEventFee implements FeeStrategy {
    @Override
    public double calculateFee(int hours) {
        return 20.0;
    }
}
