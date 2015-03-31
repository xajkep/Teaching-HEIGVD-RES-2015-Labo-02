package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Dolt Mathias
 */
public class RouletteV2KjnokeerTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
    assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldListTheCorrectStudent() throws IOException {
    RouletteV2ClientImpl client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.loadStudent("Mathias Dolt");
    List<Student> students = new ArrayList<>();
    students.add(new Student("Mathias Dolt"));
    assertEquals(students, client.listStudents());
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldCorreclyClearStudents() throws IOException {
    List<Student> students = new ArrayList<>();
    students.add(new Student("Mathias Dolt"));
    students.add(new Student());
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudents(students);
    assertEquals(2, roulettePair.getClient().getNumberOfStudents());
    RouletteV2ClientImpl client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.clearDataStore();
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldThrowAnExceptionWhenEmptyDatastore() throws IOException, EmptyStoreException {
    RouletteV2ClientImpl client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.clearDataStore();
    exception.expect(EmptyStoreException.class);
    
    client.pickRandomStudent();
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldReturnTheOnlyStudent() throws IOException, EmptyStoreException {
    String student = "Mathias Dolt";
    roulettePair.getClient().loadStudent(student);
    assertEquals(student, roulettePair.getClient().pickRandomStudent().getFullname());
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void itShouldBePossibleToLoadTwoStudentsWithSameName() throws IOException {
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudent("Mathias Dolt");
    assertEquals(1, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudent("Mathias Dolt");
    assertEquals(2, roulettePair.getClient().getNumberOfStudents());
  }
  
}
