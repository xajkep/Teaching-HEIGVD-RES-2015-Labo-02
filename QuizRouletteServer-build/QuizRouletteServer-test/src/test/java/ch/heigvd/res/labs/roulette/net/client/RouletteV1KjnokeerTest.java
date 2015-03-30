package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
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
 * implementation of the Roulette Protocol (version 1)
 * 
 * @author Dolt Mathias
 */
public class RouletteV1KjnokeerTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldCorrectlyBeStopped() throws IOException {
    assertTrue(roulettePair.getServer().isRunning());
    roulettePair.getServer().stopServer();
    assertFalse(roulettePair.getServer().isRunning());
    roulettePair.getServer().startServer();
  }

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldPickACorrectStudent() throws IOException, EmptyStoreException {
    List<Student> students = new ArrayList<>();
    students.add(new Student("Mathias Dolt"));
    students.add(new Student());
    roulettePair.getClient().loadStudents(students);
    Student s = roulettePair.getClient().pickRandomStudent();
    assertTrue(students.contains(s));
  }

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldCorrectlyLoadAStudent() throws Exception {
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudent("Mathias Dolt");
    assertEquals(1, roulettePair.getClient().getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldCorrectlyLoadStudents() throws IOException {
    List<Student> students = new ArrayList<>();
    students.add(new Student("Mathias Dolt"));
    students.add(new Student());
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudents(students);
    assertEquals(2, roulettePair.getClient().getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldReturnTheSameNumeberOfStudentsWhenMultipleClients() throws IOException {
    int port = roulettePair.getServer().getPort();
    RouletteV1ClientImpl client1 = new RouletteV1ClientImpl();
    RouletteV1ClientImpl client2 = new RouletteV1ClientImpl();
    client1.connect("localhost", port);
    client2.connect("localhost", port);
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudent("Mathias Dolt");
    assertEquals(client1.getNumberOfStudents(), client2.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "Kjnokeer")
  public void theServerShouldReturnTheOnlyStudentToTheOtherClient() throws IOException, EmptyStoreException {
    String student = "Mathias Dolt";
    assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    roulettePair.getClient().loadStudent(student);
    RouletteV1ClientImpl client2 = new RouletteV1ClientImpl();
    client2.connect("localhost", roulettePair.getServer().getPort());
    Student s = client2.pickRandomStudent();
    assertEquals(student, s.getFullname());
  }
  
}
