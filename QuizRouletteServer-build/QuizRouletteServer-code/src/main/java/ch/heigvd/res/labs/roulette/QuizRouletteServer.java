package ch.heigvd.res.labs.roulette;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.res.labs.roulette.net.server.RouletteServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides the main() method for starting the application. It creates an
 * instance of RouletteServer and starts it (it will bind on the default port specified
 * in the protocol).
 * 
 * @author Olivier Liechti
 */
public class QuizRouletteServer {

  /**
   * The main method creates a new Roulette server, which will accept TCP connection
   * requests on the default port defined in the Roulette Protocol specification.
   * 
   * @param args the command line arguments
   * @throws java.io.IOException
   */
  public static void main(String[] args) throws IOException {
    System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");
    RouletteServer server = new RouletteServer(RouletteV1Protocol.DEFAULT_PORT, RouletteV1Protocol.VERSION);
    try {
      server.startServer();
    } catch (IOException ex) {
      Logger.getLogger(QuizRouletteServer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
  }

}
