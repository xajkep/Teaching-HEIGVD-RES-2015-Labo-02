package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.LinkedList;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Olivier Liechti
 * @author Mélanie Huck
 * @author James Nolan
 */
public class RouletteV2melhkTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
    assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void listOfStudentsShouldBeEmptyAtStart() throws IOException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      assertTrue(client.listStudents().isEmpty());
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void itShouldBePossibleToClearTheList() throws IOException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      assertEquals(0, client.getNumberOfStudents());
      client.loadStudent("Mew");
      assertEquals(1, client.getNumberOfStudents());
      client.loadStudent("Gnocchi");
      assertEquals(2, client.getNumberOfStudents());
      client.clearDataStore();
      assertEquals(0, client.getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void clientShouldRetrieveTheSameListAsItHasSent() throws IOException {
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      List<Student> list1, list2;
      
      list1 = new LinkedList<Student>();
      
      list1.add(new Student("Annabelle"));
      list1.add(new Student("Chocolat-man"));
      client.loadStudents(list1);
      
      list2 = client.listStudents();
      for (int i = 0; i < list1.size(); i++) {
          assertTrue(list1.get(i).equals(list2.get(i)));
      }
      
      assertEquals(list1.size(), list2.size());
  }
  
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theServerShouldSendAnErrorResponseWhenRandomIsCalledAndStoreHasBeenCleared() throws IOException, EmptyStoreException {
    IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
    client.loadStudent("Superpoisson");
    client.clearDataStore();
    exception.expect(EmptyStoreException.class);
    client.pickRandomStudent();
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theClientShouldBeAbleToRetrieveAnotherClientList() throws IOException {
    int port = roulettePair.getServer().getPort();
    IRouletteV2Client client1  = new RouletteV2ClientImpl();
    IRouletteV2Client client2 = new RouletteV2ClientImpl();
    client1.connect("localhost", port);
    client2.connect("localhost", port);
    client1.loadStudent("Max Poulet");
    client1.loadStudent("Ventriloque népalais");
    client1.disconnect();
    assertEquals(2, client2.listStudents().size());
  }


  
}
