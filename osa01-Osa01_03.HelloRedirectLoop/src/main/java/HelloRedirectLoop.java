
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class HelloRedirectLoop {

	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(8080);

		while (true) {
			Socket request = server.accept();

			Scanner reqReader = new Scanner(request.getInputStream());

			if (reqReader.nextLine().contains("/quit")) {
				break;
			}

			PrintWriter response = new PrintWriter(request.getOutputStream());
			response.println("HTTP/1.1 302 Found");
			response.println("Location: http://localhost:8080");
			response.println("date: " + new Date().toString());
			response.flush();

			response.close();
			reqReader.close();
			request.close();
		}

		server.close();
	}
}
