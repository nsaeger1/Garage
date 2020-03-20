import java.time.LocalTime;
import java.util.Random;

public class Time {
    public static LocalTime getCheckInTime() {
        Random rand = new Random();
        int h = rand.nextInt(6) +7;
        int m = rand.nextInt(60);

        LocalTime localTime = LocalTime.of(h, m);

        return localTime;
    }
    public static LocalTime getCheckOutTime() {
        Random rand = new Random();
        int h = rand.nextInt(11) +13;
        int m = rand.nextInt(60);

        LocalTime localTime = LocalTime.of(h, m);

        return localTime;
    }
}
