package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 */
public class RouletteV2michellesakamTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
@TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void numberOfStudentsMustBeZeroAfterClearCommand() throws IOException {
     IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
     LinkedList<Student> students= new LinkedList<>();
     students.add(new Student("brice"));
     client.loadStudents(students);
     client.clearDataStore();// supression des students enrégistrés dans le serveur
     assertEquals(0, client.getNumberOfStudents());
     
  }
  
   @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldSendAnErrorResponseWhenClearIsCalledOnAnEmptyDataDeposit() throws IOException, EmptyStoreException {
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    if(client.getNumberOfStudents()== 0){
    exception.expect(EmptyStoreException.class);
    client.clearDataStore();
    }
  }
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theTestRouletteClientShouldStopTheConnectionWhenATtheServerIsNotStealRunning() throws IOException {
    roulettePair.getServer().stopServer();
    assertFalse(roulettePair.getClient().isConnected());
  }
  
  
   @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theServerShouldSendAnErrorResponseWhenListIsCalledAndThereIsNoStudent() throws IOException, EmptyStoreException {
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    exception.expect(EmptyStoreException.class);
    client.listStudents();
  }
  
   @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
  public void theCommandListShouldListTheSudentsInTheOrderOfLoad() throws IOException, EmptyStoreException {
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.clearDataStore();
    client.loadStudent("brice");
    client.loadStudent("dorine");
    List<Student> list = client.listStudents();
    assertEquals("brice", list.get(0));
    assertEquals("dorine", list.get(1));
    
  }
  
  @Test
  @TestAuthor(githubId = {"michellesakam", "djeule2"})
   public void theServerShouldreconizeTheEndOfTheData() throws Exception {
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudent("michelle");
    client.loadStudent("ENDOFDATA");
    exception.expect(EmptyStoreException.class);
    client.loadStudent("olivier");
   }
  
  
}
