package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Yassin Kammoun
 * @author Raphael Racine
 */
public class RouletteV1YibnlRaphaelRacineTest {

   @Rule
   public ExpectedException exception = ExpectedException.none();
   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheClientShouldNotBeConnectedAfterDisconnected() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV1Client client = new RouletteV1ClientImpl();
         assertFalse(client.isConnected());
         client.connect("localhost", port);
         assertTrue(client.isConnected());
         client.disconnect();
         assertFalse(client.isConnected());
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheClientShouldNotBeConnectedWithAWrongPort() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV1Client client = new RouletteV1ClientImpl();
         assertFalse(client.isConnected());
         client.connect("localhost", port + 1);
         assertFalse(client.isConnected());
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheClientShouldGetTheSameRandomStudentAfterHavingLoadedItAtStart() throws EmptyStoreException {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV1Client client = new RouletteV1ClientImpl();
         client.connect("localhost", port);
         client.loadStudent("olivier liechti");
         assertEquals("olivier liechti", client.pickRandomStudent().getFullname());
      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheClientSouldReturnTheCorrectVersionNumber() throws IOException {
      int port = roulettePair.getServer().getPort();
      IRouletteV1Client client = new RouletteV1ClientImpl();
      client.connect("localhost", port);
      assertEquals(client.getProtocolVersion(), roulettePair.protocolVersion);
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheServerShouldCountStudentsWithConcurrentStringVersionLoad() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV1Client client1 = new RouletteV1ClientImpl();
         IRouletteV1Client client2 = new RouletteV1ClientImpl();
         client1.connect("localhost", port);
         client2.connect("localhost", port);
         
         client1.loadStudent("olivier");
         client1.loadStudent("raphael");
         client2.loadStudent("yassin");
         client2.loadStudent("simon");
         
         assertEquals(client1.getNumberOfStudents(), 4);
         
         client2.loadStudent("zoé");
         client1.loadStudent("sébastien");
         
         assertEquals(client2.getNumberOfStudents(), 6);
         

      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   @Test
   @TestAuthor(githubId = {"yibnl", "raphaelracine"})
   public void TheServerShouldCountStudentsWithConcurrentListVersionLoad() {
      try {
         int port = roulettePair.getServer().getPort();
         IRouletteV1Client client1 = new RouletteV1ClientImpl();
         IRouletteV1Client client2 = new RouletteV1ClientImpl();
         client1.connect("localhost", port);
         client2.connect("localhost", port);
         
         List<Student> students = new ArrayList<>();
         students.add(new Student("georges"));
         students.add(new Student("rosalie"));
         students.add(new Student("wilfried"));
         client1.loadStudents(students);         
         assertEquals(client2.getNumberOfStudents(), 3);
         
         students.clear();
         students.add(new Student("éléonore"));
         students.add(new Student("mélanie"));
         students.add(new Student("miguel"));
         client2.loadStudents(students);         
         assertEquals(client1.getNumberOfStudents(), 6);
         

      } catch (IOException ex) {
         Logger.getLogger(RouletteV1YibnlRaphaelRacineTest.class.getName()).log(Level.SEVERE, null, ex);
      }
   }
}