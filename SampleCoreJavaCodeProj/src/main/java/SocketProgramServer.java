import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketProgramServer {

	private static Logger logger = LoggerFactory.getLogger(SocketProgramServer.class);

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    
	public static void main(String[] args) {
		SocketProgramServer servletProgramServer = new SocketProgramServer();
		try {
			servletProgramServer.startSocket(6400);
			servletProgramServer.stopServer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void startSocket(int port) throws IOException {
		boolean stopClient = false;
		serverSocket = new ServerSocket(port);
       
		while (!stopClient) {
			logger.info("Liseting to Client...");
			clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
	        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String messageFromClient = in.readLine();
			
			logger.info("Message From Client " + messageFromClient);
			if (null != messageFromClient && messageFromClient.equalsIgnoreCase("hello server")) {
				out.println("hello client");
			} else if(null != messageFromClient && messageFromClient.equalsIgnoreCase("stop")) {
				out.println("stopping server");
				stopClient = true;
			} else {
				out.println("invalid message");
			}
		}
	}
	
	private void stopServer() throws Exception {
		in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
	}
}
