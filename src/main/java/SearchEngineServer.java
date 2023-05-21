import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SearchEngineServer {
    private final int port;
    private final BooleanSearchEngine engine;

    public SearchEngineServer(int port, BooleanSearchEngine engine) {
        this.port = port;
        this.engine = engine;
    }

    public void start() throws IOException {
        System.out.println("Запушен сервер на порту " + port + " ...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.println(engine.search(parserJson(in)));
                }
            }
        }
    }

    private String parserJson(BufferedReader in) throws IOException {
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(in.readLine());
        JsonObject jsonObject = (JsonObject) obj;
        return jsonObject.get("word").getAsString();
    }
}
