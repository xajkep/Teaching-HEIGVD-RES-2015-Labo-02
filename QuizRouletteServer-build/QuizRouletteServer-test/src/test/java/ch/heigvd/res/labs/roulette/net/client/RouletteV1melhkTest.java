package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.LinkedList;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 * 
 * @author Olivier Liechti
 * @author Mélanie Huck
 * @author James Nolan
 */
public class RouletteV1melhkTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theServerShouldReturnAnUniqueStudentWhenItHasStoredOnlyOneStudent() throws IOException, EmptyStoreException {
      IRouletteV1Client client = roulettePair.getClient();
      client.loadStudent("Anakin");
      Student s = client.pickRandomStudent();
      assertEquals(s.getFullname(), "Anakin");
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theClientShouldBeAbleToDisconnect() throws IOException {
      IRouletteV1Client client = roulettePair.getClient();
      client.disconnect();
      assertFalse(client.isConnected());
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void multipleClientsShouldBeAbleToConnectToTheServer() throws IOException {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client1  = new RouletteV1ClientImpl();
    IRouletteV1Client client2 = new RouletteV1ClientImpl();
    client1.connect("localhost", port);
    client2.connect("localhost", port);
    client1.disconnect();
    client2.disconnect();
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void aClientShouldBeAbleToReceiveAStudentThatHasBeenStoreByAnotherClient() throws IOException, EmptyStoreException {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client1  = new RouletteV1ClientImpl();
    IRouletteV1Client client2 = new RouletteV1ClientImpl();
    client1.connect("localhost", port);
    client1.loadStudent("Platon");
    client1.disconnect();
    client2.connect("localhost", port);
    Student s = client2.pickRandomStudent();
    assertEquals(s.getFullname(), "Platon");
    client2.disconnect();
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theServerShouldManageStudentsThatHaveTheSameName() throws IOException {
    String student = "Xénophon";
    IRouletteV1Client client = roulettePair.getClient();
    assertEquals(0, client.getNumberOfStudents());
    client.loadStudent(student);
    assertEquals(1, client.getNumberOfStudents());
    client.loadStudent(student);
    assertEquals(2, client.getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = {"melhk", "j-nolan"})
  public void theServerShouldManageListOfStudentsWithDuplicates() throws IOException {
    LinkedList<Student> students = new LinkedList<Student>();
    IRouletteV1Client client = roulettePair.getClient();
    
    students.add(new Student("Priam"));
    students.add(new Student("Cassandre"));
    students.add(new Student("Cassandre"));
    client.loadStudents(students);
    assertEquals(3, client.getNumberOfStudents());
    
  }
}
