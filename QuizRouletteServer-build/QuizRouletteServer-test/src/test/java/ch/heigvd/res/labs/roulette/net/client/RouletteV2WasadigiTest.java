package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import ch.heigvd.res.labs.roulette.data.Student;
import static org.junit.Assert.*;


/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Olivier Liechti
 */
public class RouletteV2WasadigiTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  /* Copy of V1 TESTs */
  
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theTestRouletteServerShouldRunDuringTests() throws IOException {
//     assertTrue(roulettePair.getServer().isRunning());
//   }
// 
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theTestRouletteClientShouldBeConnectedWhenATestStarts() throws IOException {
//     assertTrue(roulettePair.getClient().isConnected());
//   }
// 
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void itShouldBePossibleForARouletteClientToConnectToARouletteServer() throws Exception {
//     int port = roulettePair.getServer().getPort();
//     IRouletteV2Client client = new RouletteV2ClientImpl();
//     assertFalse(client.isConnected());
//     client.connect("localhost", port);
//     assertTrue(client.isConnected());
//   }
//   
//     @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
//     assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
//   }
// 
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldHaveZeroStudentsAtStart() throws IOException {
//     int port = roulettePair.getServer().getPort();
//     IRouletteV2Client client = new RouletteV2ClientImpl();
//     client.connect("localhost", port);
//     int numberOfStudents = client.getNumberOfStudents();
//     assertEquals(0, numberOfStudents);
//   }
// 
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldStillHaveZeroStudentsAtStart() throws IOException {
//     assertEquals(0, roulettePair.getClient().getNumberOfStudents());
//   }
// 
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldCountStudents() throws IOException {
//     IRouletteV2Client client = roulettePair.getClient();
//     assertEquals(0, client.getNumberOfStudents());
//     client.loadStudent("sacha");
//     assertEquals(1, client.getNumberOfStudents());
//     client.loadStudent("olivier");
//     assertEquals(2, client.getNumberOfStudents());
//     client.loadStudent("fabienne");
//     assertEquals(3, client.getNumberOfStudents());
//   }

//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldSendAnErrorResponseWhenRandomIsCalledAndThereIsNoStudent() throws IOException, EmptyStoreException {
//     IRouletteV2Client client = roulettePair.getClient();
//     exception.expect(EmptyStoreException.class);
//     client.pickRandomStudent();
//   }
  
  /* Exclusives TESTs for V2 */
  
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerShouldClearData() throws IOException, EmptyStoreException {
//     IRouletteV2Client client = roulettePair.getClient();
//     client.loadStudent("Foo Bar");
//     
//     client.clearDataStore();
//     
//     assertNull(client.pickRandomStudent());
//   }
  
//   @Test
//   @TestAuthor(githubId = {"xajkep", "msaw"})
//   public void theServerListCmdShouldReturnStudentList() throws IOException, EmptyStoreException {
//     IRouletteV2Client client = roulettePair.getClient();
//     List<Student> students = new ArrayList<>();
//     students.add(new Student("Foo Bar"));
//     students.add(new Student("Bar Foo"));
//     
//     client.loadStudents(students);
//     
//     assertEquals(client.listStudents(), students);
//   }
}
