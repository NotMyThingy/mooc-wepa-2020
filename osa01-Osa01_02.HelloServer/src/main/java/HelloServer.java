

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;

public class HelloServer {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8080);

		while (true) {
			Socket socket = server.accept();

			Scanner reader = new Scanner(socket.getInputStream());

			if (reader.nextLine().contains("/quit")) {
				break;
			}

			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println("HTTP/1.1 200 OK");
			writer.println("date: " + new Date().toString());
			writer.println();
			Files.lines(Paths.get("index.html")).forEach(writer::println);
			writer.flush();

			writer.close();
			reader.close();
			socket.close();
		}

		server.close();
	}
}
