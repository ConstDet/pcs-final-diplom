import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SearchWordClient {
    public static void main(String[] args) {
        final int PORT = 8989;
        final String HOST = "localhost";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                System.out.println("Введите слово для поиска:");
                String searchWord = scanner.nextLine();
                String requestString = "{ \"word\": \""+ searchWord +"\" }";
                out.println(requestString);
                System.out.println(in.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
