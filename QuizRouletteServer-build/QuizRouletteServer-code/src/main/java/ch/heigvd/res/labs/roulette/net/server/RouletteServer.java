package ch.heigvd.res.labs.roulette.net.server;

import ch.heigvd.res.labs.roulette.data.IStudentsStore;
import ch.heigvd.res.labs.roulette.data.StudentsStoreImpl;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the main logic of the RouletteServer. It starts a loop
 * to accept incoming connections on a TCP port. When a new connection request
 * is made by a client, the server spawns a new thread. It instantiates a client
 * handler, to which it delegates the processing of the session (the client
 * handler executes on the thread).
 *
 * It is possible to either create an instance of the RouletteServer by
 * specifying an explicit TCP port (typically, the default port defined in the
 * protocol specification), or to let the OS allocate an ephemeral port. While
 * this is not a common approach for TCP servers (because clients generally want
 * to have a well-known port to establish the communication), it is useful in
 * the context of automated tests (which may execute in parallel and therefore
 * need "isolated" servers).
 *
 * @author Olivier Liechti
 */
public class RouletteServer {

  final static Logger LOG = Logger.getLogger(RouletteServer.class.getName());

  /*
   * The TCP port where client connection requests are accepted. -1 indicates that
   * we want to use an ephemeral port number, assigned by the OS
   */
  private int listenPort = -1;

  /*
   * The server socket, used to accept client connection requests
   */
  private ServerSocket serverSocket;

  /*
   * The server maintains a list of client workers, so that they can be notified
   * when the server shuts down
   */
  List<ClientWorker> clientWorkers = new CopyOnWriteArrayList<>();

  /*
   * The server uses a data store to keep track of students
   */
  IStudentsStore store = new StudentsStoreImpl();

  /*
   * A flag that indicates whether the server should continue to run (or whether
   * a shutdown is in progress)
   */
  private boolean shouldRun = false;

  private String protocolVersion;

  /**
   * Constructor used to create a server that will accept connections on a known
   * TCP port
   *
   * @param listenPort the TCP port on which connection requests are accepted
   */
  public RouletteServer(int listenPort, String protocolVersion) {
    this.listenPort = listenPort;
    this.protocolVersion = protocolVersion;
  }

  /**
   * Constructor used to create a server that will accept connections on an
   * ephemeral port
   */
  public RouletteServer(String protocolVersion) {
    this.listenPort = -1;
    this.protocolVersion = protocolVersion;
  }

  public void startServer() throws IOException {
    if (serverSocket == null || serverSocket.isBound() == false) {
      if (listenPort == -1) {
        bindOnEphemeralPort();
      } else {
        bindOnKnownPort(listenPort);
      }
    }

    Thread serverThread = new Thread(new Runnable() {
      @Override
      public void run() {
        shouldRun = true;
        while (shouldRun) {
          try {
            LOG.log(Level.INFO, "Listening for client connection on {0}", serverSocket.getLocalSocketAddress());
            Socket clientSocket = serverSocket.accept();
            LOG.info("New client has arrived...");
            ClientWorker worker = new ClientWorker(clientSocket, getClientHandler(), RouletteServer.this);
            clientWorkers.add(worker);
            LOG.info("Delegating work to client worker...");
            Thread clientThread = new Thread(worker);
            clientThread.start();
          } catch (IOException ex) {
            LOG.log(Level.SEVERE, "IOException in main server thread, exit: {0}", ex.getMessage());
            shouldRun = false;
          }
        }
      }
    });
    serverThread.start();
  }

  private IClientHandler getClientHandler() {
    switch (protocolVersion) {
      case RouletteV1Protocol.VERSION:
        return new RouletteV1ClientHandler(store);
      case RouletteV2Protocol.VERSION:
        return new RouletteV2ClientHandler(store);
    }
    return new RouletteV1ClientHandler(store);
  }

  /**
   * Indicates whether the server is accepting connection requests, by checking
   * the state of the server socket
   *
   * @return true if the server accepts client connection requests
   */
  public boolean isRunning() {
    return (shouldRun && serverSocket.isBound());
  }

  /**
   * Getter for the TCP port number used by the server socket.
   *
   * @return the port on which client connection requests are accepted
   */
  public int getPort() {
    return serverSocket.getLocalPort();
  }

  /**
   * Requests a server shutdown. This will close the server socket and notify
   * all client workers.
   *
   * @throws IOException
   */
  public void stopServer() throws IOException {
    shouldRun = false;
    serverSocket.close();
    for (ClientWorker clientWorker : clientWorkers) {
      clientWorker.notifyServerShutdown();
    }
  }

  private void bindOnKnownPort(int port) throws IOException {
    serverSocket = new ServerSocket();
    serverSocket.bind(new InetSocketAddress(port));
  }

  private void bindOnEphemeralPort() throws IOException {
    serverSocket = new ServerSocket();
    serverSocket.bind(null);
    this.listenPort = serverSocket.getLocalPort();
  }

  /**
   * This method is invoked by the client worker when it has completed its
   * interaction with the server (e.g. the user has issued the BYE command, the
   * connection has been closed, etc.)
   *
   * @param worker the worker which has completed its work
   */
  public void notifyClientWorkerDone(ClientWorker worker) {
    clientWorkers.remove(worker);
  }

}
