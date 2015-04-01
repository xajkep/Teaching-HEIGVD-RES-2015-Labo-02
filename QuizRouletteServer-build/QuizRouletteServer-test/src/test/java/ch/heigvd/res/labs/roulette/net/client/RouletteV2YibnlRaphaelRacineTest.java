package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 */
public class RouletteV2YibnlRaphaelRacineTest {

   @Rule
   public ExpectedException exception = ExpectedException.none();
   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void AfterClearingStudentsTheNumberOfStudentShouldBeZero() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV2Client client = new RouletteV2ClientImpl();
         client.connect("localhost", port);

         client.loadStudent("toto");
         client.loadStudent("titi");
         client.loadStudent("tata");
         client.loadStudent("tété");

         assertEquals(client.getNumberOfStudents(), 4);

         client.clearDataStore();
         assertEquals(client.getNumberOfStudents(), 0);

         client.disconnect();
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheServerShouldReturnAnEmptyListAtStart() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV2Client client = new RouletteV2ClientImpl();
         client.connect("localhost", port);
         assertTrue(client.listStudents().isEmpty());
         client.disconnect();
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheServerShouldReturnTheRightStudent() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV2Client client = new RouletteV2ClientImpl();
         client.connect("localhost", port);

         Student s = new Student("tintin");
         client.loadStudent(s.getFullname());

         assertTrue(client.listStudents().get(0).equals(s));

         client.disconnect();
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheServerShouldCountStudentsWithConcurrentStringVersionLoad() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV2Client client1 = new RouletteV2ClientImpl();
         IRouletteV2Client client2 = new RouletteV2ClientImpl();
         client1.connect("localhost", port);
         client2.connect("localhost", port);

         client1.loadStudent("olivier");
         client1.loadStudent("raphael");
         client2.loadStudent("yassin");
         client2.loadStudent("simon");

         client1.clearDataStore();
         assertEquals(client1.getNumberOfStudents(), 0);

      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheClientHasToConnectToRightServer() throws IOException {
      IRouletteV2Client client = new RouletteV2ClientImpl();
      int port = roulettePair.server.getPort();
      exception.expect(IOException.class);
      client.connect("www.globulos.com", port);
      assertFalse(client.isConnected());
   }
}
