package Fee;

public class FeeCalculator {
    /**
     * This is my factory which creates strategies.
     * @param feeType
     * @return
     */
    public FeeStrategy getFee(String feeType) {
        if (feeType.equalsIgnoreCase("MinMax")) {
            return new MinMaxFee();
        } else if (feeType.equalsIgnoreCase("SpecialEvent")) {
            return new SpecialEventFee();
        } else if (feeType.equalsIgnoreCase("LostTicket")) {
            return new LostTicketFee();
        }
        return null;
    }
}
