package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import org.junit.Rule;
import org.junit.Test;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RouletteV1BinaryBrainTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void shouldPickNotNullStudent() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudent("test");
    assertNotNull(client.pickRandomStudent());
  }

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void shouldLoadListOfStudents() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    List<Student> students = new ArrayList<>();
    students.add(new Student("Test 1"));
    students.add(new Student("Test 2"));

    client.loadStudents(students);

    assertEquals(2, client.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void shouldLoadedListOfStudentsShouldContainsStudents() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    List<Student> students = new ArrayList<>();
    students.add(new Student("Test 1"));
    students.add(new Student("Test 2"));

    Student student = client.pickRandomStudent();

    assertTrue(students.contains(student));
  }

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void returnSameClient() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    IRouletteV1Client client2 = roulettePair.getClient();

    assertTrue(client == client2);
  }

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void returnDifferentClients() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    IRouletteV1Client client2 = new RouletteV1ClientImpl();

    assertTrue(client != client2);
  }

  @Test
  @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
  public void differentClientsShouldWork() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    IRouletteV1Client client2 = new RouletteV1ClientImpl();
    client2.connect("localhost", 1314);

    assertTrue(client.isConnected() && client2.isConnected());
  }
}
