package Server;

import java.util.*;
import java.net.*;
import java.io.*;

public class ServerApp {
	static List<Worker> listUser = new ArrayList<Worker>();

	public static void main(String[] args) {
		new ServerApp();
	}

	public ServerApp() {
		try {
			ServerSocket serverSocket = new ServerSocket(6969);
			Socket socket;
			while (true) {
				socket = serverSocket.accept();
				System.out.println("- Accepted " + socket.getRemoteSocketAddress());
				Worker worker = new Worker(socket);
				listUser.add(worker);
				System.out.println("No thread: " + listUser.size());
				worker.start();
			}
		} catch (Exception e) {

		}
	}
}

class Worker extends Thread {
	Socket socket;
	DataInputStream dis;

	public Worker(Socket _socket) {
		socket = _socket;
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			System.out.println("  + User: " + dis.readUTF());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
