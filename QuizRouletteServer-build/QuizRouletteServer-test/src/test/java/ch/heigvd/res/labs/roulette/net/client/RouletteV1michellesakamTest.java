package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.data.StudentsList;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.awt.List;
import java.io.IOException;
import static java.util.Collections.list;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 * 
 * @author Olivier Liechti
 */
public class RouletteV1michellesakamTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theTestRouletteServerShouldRunDuringTests() throws IOException {
    assertTrue(roulettePair.getServer().isRunning());
  }

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theTestRouletteClientShouldBeConnectedWhenATestStarts() throws IOException {
    assertTrue(roulettePair.getClient().isConnected());
  }

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void itShouldBePossibleForARouletteClientToConnectToARouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client = new RouletteV1ClientImpl();
    assertFalse(client.isConnected());
    client.connect("localhost", port);
    assertTrue(client.isConnected());
  }
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
    assertEquals(RouletteV1Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
  }

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldHaveZeroStudentsAtStart() throws IOException {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client = new RouletteV1ClientImpl();
    client.connect("localhost", port);
    int numberOfStudents = client.getNumberOfStudents();
    assertEquals(0, numberOfStudents);
  }

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldStillHaveZeroStudentsAtStart() throws IOException {
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldCountStudents() throws IOException {
    IRouletteV1Client client = roulettePair.getClient();
    assertEquals(0, client.getNumberOfStudents());
    client.loadStudent("sacha");
    assertEquals(1, client.getNumberOfStudents());
    client.loadStudent("olivier");
    assertEquals(2, client.getNumberOfStudents());
    client.loadStudent("fabienne");
    assertEquals(3, client.getNumberOfStudents());
  }
   
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldSendAnErrorResponseWhenRandomIsCalledAndThereIsNoStudent() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    exception.expect(EmptyStoreException.class);
    client.pickRandomStudent();
  }
  
  // debut des ajouts 
   @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
   public void theServerShouldAddnewStudentWhenLoaded() throws Exception {
    IRouletteV1Client client = roulettePair.getClient();
    int nb = client.getNumberOfStudents();
    client.loadStudent("michelle");
    assertEquals(nb + 1, client.getNumberOfStudents());
   }
   
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
   public void theServerShouldAddnewStudentsWhenLoaded() throws Exception {
       
   IRouletteV1Client client = roulettePair.getClient();
   int nb = client.getNumberOfStudents();
   LinkedList<Student> students= new LinkedList<>();
   students.add(new Student("brice"));
   students.add(new Student("martial"));
   client.loadStudents(students);
   assertEquals(++nb, client.getNumberOfStudents());
   }
   
  @Test
 @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void itShouldBePossibleForARouletteClientTodisConnectForARouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client = new RouletteV1ClientImpl();
    assertFalse(client.isConnected());
    client.connect("localhost", port);
    assertTrue(client.isConnected());
    client.disconnect();
    assertFalse(client.isConnected());
  }
  
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void itShouldBePossibleForSeveralRouletteClientsToConnectToTheSameRouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    
    IRouletteV1Client client = new RouletteV1ClientImpl();
    assertFalse(client.isConnected());
    client.connect("localhost", port);
    assertTrue(client.isConnected());
    assertEquals(0, client.getNumberOfStudents());
    client.loadStudent("sacha");
    
    IRouletteV1Client client1 = new RouletteV1ClientImpl();
    assertFalse(client1.isConnected());
    client.connect("localhost", port);
    assertTrue(client1.isConnected());
    assertEquals(1, client1.getNumberOfStudents());
   
  }
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void itShouldBePossibleForARouletteListeClientToDisConnectToARouletteServer() throws Exception {
      
    IRouletteV1Client client =roulettePair.getClient();
    assertTrue(client.isConnected());
    client.disconnect();
    assertFalse(client.isConnected());
    
    IRouletteV1Client client1 = roulettePair.getClient();
    assertTrue(client1.isConnected());
    client1.disconnect();
    assertFalse(client1.isConnected());
    
  }
  
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldSendRandomStudent() throws IOException, EmptyStoreException {
  
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client = new RouletteV1ClientImpl();
    client.connect("localhost", port);
    int numberOfStudents = client.getNumberOfStudents();
    assertEquals(0, numberOfStudents);
    client.loadStudent("michelle");
    client.loadStudent("olivier"); 
    assertNotEquals(null, client.pickRandomStudent());
      
  }
}
