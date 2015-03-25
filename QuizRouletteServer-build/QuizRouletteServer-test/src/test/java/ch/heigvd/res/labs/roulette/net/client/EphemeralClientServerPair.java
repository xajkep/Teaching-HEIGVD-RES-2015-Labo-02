package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.net.server.RouletteServer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.rules.ExternalResource;

/**
 *
 * @author Olivier Liechti
 */
public class EphemeralClientServerPair extends ExternalResource {

  RouletteServer server;
  IRouletteV1Client client;
  String protocolVersion;

  public EphemeralClientServerPair(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  @Override
  protected void before() throws Throwable {
    server = new RouletteServer(protocolVersion);
    server.startServer();
    if (RouletteV1Protocol.VERSION.equals(protocolVersion)) {
      client = new RouletteV1ClientImpl();
    } else {
      client = new RouletteV2ClientImpl();
    }
    client.connect("localhost", server.getPort());
  }

  @Override
  protected void after() {
    try {
      client.disconnect();
    } catch (IOException ex) {
      Logger.getLogger(EphemeralClientServerPair.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
      server.stopServer();
    } catch (IOException ex) {
      Logger.getLogger(EphemeralClientServerPair.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public RouletteServer getServer() {
    return server;
  }

  public IRouletteV1Client getClient() {
    return client;
  }

}
