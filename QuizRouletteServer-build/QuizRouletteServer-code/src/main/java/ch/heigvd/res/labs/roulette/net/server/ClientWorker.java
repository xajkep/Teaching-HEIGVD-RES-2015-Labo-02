package ch.heigvd.res.labs.roulette.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Instances of this class are created whenever a client has arrived and a
 * connection has been established (the instances are created by the TCPServer
 * class). The class is responsible for setting up and cleaning up the
 * communication streams, but delegates the hard work (i.e. the implementation
 * of our own application protocol) to a class that implements the
 * IClientHandler interface.
 *
 * This means that we could reuse this class, develop a new class that
 * implements the IClientHandler interface and implement another application
 * protocol.
 *
 * @author Olivier Liechti
 */
public class ClientWorker implements Runnable {

  static final Logger LOG = Logger.getLogger(ClientWorker.class.getName());

  private IClientHandler handler = null;
  private Socket clientSocket = null;
  private InputStream is = null;
  private OutputStream os = null;
  private boolean done = false;
  private RouletteServer server = null;

  public ClientWorker(Socket clientSocket, IClientHandler handler, RouletteServer server) throws IOException {
    this.clientSocket = clientSocket;
    this.handler = handler;
    this.server = server;
    is = clientSocket.getInputStream();
    os = clientSocket.getOutputStream();
  }

  @Override
  public void run() {
    try {
      handler.handleClientConnection(is, os);
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, "Exception in client handler: {0}", ex.getMessage());
    } finally {
      done = true;
      server.notifyClientWorkerDone(this);
      try {
        clientSocket.close();
      } catch (IOException ex) {
        LOG.log(Level.INFO, ex.getMessage());
      }
      try {
        is.close();
      } catch (IOException ex) {
        LOG.log(Level.INFO, ex.getMessage());
      }
      try {
        os.close();
      } catch (IOException ex) {
        LOG.log(Level.INFO, ex.getMessage());
      }
    }
  }

  public boolean isDone() {
    return done;
  }

  public void notifyServerShutdown() {
    try {
      is.close();
    } catch (IOException ex) {
      LOG.log(Level.INFO, "Exception while closing input stream on the server: {0}", ex.getMessage());
    }

    try {
      os.close();
    } catch (IOException ex) {
      LOG.log(Level.INFO, "Exception while closing output stream on the server: {0}", ex.getMessage());
    }

    try {
      clientSocket.close();
    } catch (IOException ex) {
      LOG.log(Level.INFO, "Exception while closing socket on the server: {0}", ex.getMessage());
    }
  }

}
