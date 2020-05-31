import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSV {
    public static ArrayList<Ticket> Read(String filename) throws IOException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(filename));
        String line = reader.readLine();
        while (line != null) {
            Ticket ticket = new Ticket(line.split(","));
            tickets.add(ticket);
            line = reader.readLine();

        }
        reader.close();
        return tickets;
    }
    public static void Write(String filename, List<Ticket> tickets) throws IOException {
        java.io.File file = new java.io.File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(filename));
        for (Ticket ticket : tickets) {
            writer.write(ticket.toString());
            writer.newLine();
        }
        writer.close();

    }
}
