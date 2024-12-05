import java.io.*;
import java.net.Socket;

/**
 * Handles communication between the server and one client, for SketchServer
 * @author Chris Bailey-Kellogg, Dartmouth CS 10, Fall 2012; revised Winter 2014 to separate SketchServerCommunicator
 * @author Lydia Jin
 * @author Sudi Zhao
 */
public class SketchServerCommunicator extends Thread {
	private Socket sock;					// to talk with client
	private BufferedReader in;				// from client
	private PrintWriter out;				// to client
	private SketchServer server;			// handling communication for

	public SketchServerCommunicator(Socket sock, SketchServer server) {
		this.sock = sock;
		this.server = server;
	}

	/**
	 * Sends a message to the client
	 * @param msg
	 */
	public void send(String msg) {
		out.println(msg);
	}

	/**
	 * @param command   String command to be handled
	 */
	public void handleMessage(String command) {
		Message message = new Message(command, server.getSketch());
		message.handleMessage();
	}

	/**
	 * Keeps listening for and handling (your code) messages from the client
	 */
	public void run() {
		try {
			System.out.println("someone connected");

			// Communication channel
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream(), true);

			// Tell the client the current state of the world
			// TODO: YOUR CODE HERE
			for (int id : server.getSketch().getMap().keySet()) {
				//loop through the keys in the map containing the shapes in the server sketch

				Shape shape = server.getSketch().getMap().get(id);

				//see the message class comments to understand how the format of the messaging works xoxo
				if (server.getSketch().getShapeType(id).equals("polyline")) {
					String pointCoordinates = "";
					for (int i = 0; i < ((Polyline)shape).getPoints().size(); i++) {
						pointCoordinates += ((Polyline)shape).getPoints().get(i).x + " " + ((Polyline)shape).getPoints().get(i).y + " ";
					}
					out.println("A " + id + " " + server.getSketch().getShapeType(id) + " " + shape.getColor().getRGB() + " " + pointCoordinates);
				}

				else if (server.getSketch().getShapeType(id).equals("ellipse")) {
					out.println("A " + id + " " + server.getSketch().getShapeType(id) + " " + ((Ellipse)shape).getX1() + " " + ((Ellipse)shape).getY1() + " " + ((Ellipse)shape).getX2() + " " + ((Ellipse)shape).getY2() + " " + shape.getColor().getRGB());
				}

				else if (server.getSketch().getShapeType(id).equals("segment")) {
					out.println("A " + id + " " + server.getSketch().getShapeType(id) + " " + ((Segment)shape).getX1() + " " + ((Segment)shape).getY1() + " " + ((Segment)shape).getX2() + " " + ((Segment)shape).getY2() + " " + shape.getColor().getRGB());
				}

				else if (server.getSketch().getShapeType(id).equals("rectangle")) {
					out.println("A " + id + " " + server.getSketch().getShapeType(id) + " " + ((Rectangle)shape).getX1() + " " + ((Rectangle)shape).getY1() + " " + ((Rectangle)shape).getX2() + " " + ((Rectangle)shape).getY2() + " " + shape.getColor().getRGB());
				}
			}

			// Keep getting and handling messages from the client
			// TODO: YOUR CODE HERE
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println("in while loop");
				System.out.println("sketch server received: " + line);
				handleMessage(line); //handle the message
				server.broadcast(line); //broadcast the message to all the editors for them to handle
				System.out.println(server.getSketch().getMap());
			}

			// Clean up -- note that also remove self from server's list so it doesn't broadcast here
			server.removeCommunicator(this);
			out.close();
			in.close();
			sock.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
