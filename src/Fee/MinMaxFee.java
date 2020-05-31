package Fee;


public class MinMaxFee implements FeeStrategy {
    double cost = 15.0;
    public double calculateFee(int hours) {
        this.cost = hours + 5;
        if (this.cost > 15) {
            this.cost = 15;
        }
        return this.cost;
    }
}
