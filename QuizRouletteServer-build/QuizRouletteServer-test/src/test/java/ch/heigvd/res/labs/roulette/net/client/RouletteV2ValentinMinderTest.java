package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Valentin Minder
 */
public class RouletteV2ValentinMinderTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
    assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void castToIRouletteV2ClientShouldNotRaiseException() throws IOException {
    try {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    } catch (ClassCastException ex) {
        fail("Expected that cast to IRouletteV2Client wouldn't fail.");
    }
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustNotBeNull() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    assertNotNull(client.listStudents());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustBeEmptyAtStart() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    assertTrue(client.listStudents().isEmpty());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustNotBeEmptyWhenFilled() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.loadStudent("Jamel Debouzze");
    assertFalse(client.listStudents().isEmpty());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustCountFileAccordingToTest() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    assertEquals(0, client.listStudents().size());
    client.loadStudent("Woody Allen");
    assertEquals(1, client.listStudents().size());
    client.loadStudent("Steven Spielberg");
    assertEquals(2, client.listStudents().size());
    client.loadStudent("Roman Polansky");
    assertEquals(3, client.listStudents().size());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustCountFileAccordingToInfo() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    assertEquals(client.getNumberOfStudents(), client.listStudents().size());
    client.loadStudent("Steve Jobs");
    assertEquals(client.getNumberOfStudents(), client.listStudents().size());
    client.loadStudent("Bill Gates");
    assertEquals(client.getNumberOfStudents(), client.listStudents().size());
    client.loadStudent("Linus Torvalds");
    assertEquals(client.getNumberOfStudents(), client.listStudents().size());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustContainTheOnlyStudentAdded() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    String myStudent = "student";
    client.loadStudent(myStudent);
    assertEquals(client.listStudents().get(0).getFullname(), myStudent);
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void listStudentMustContainTheTwoAndOnlyTheTwoStudentAdded() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());

    List<Student> sentStudentList = new ArrayList<>(2);
    sentStudentList.add(new Student("Francois Hollande"));
    sentStudentList.add(new Student("Nicolas Sarkozy"));
    client.loadStudents(sentStudentList);

    List<Student> recvStudentList = client.listStudents();

    // all rcvStudentList must have been sent
    assertTrue(sentStudentList.containsAll(recvStudentList));
//    for (Student student : recvStudentList) {
//        assertTrue(sentStudentList.contains(student));
//    }

    // all sentStudentList must be recieved
    assertTrue(recvStudentList.containsAll(sentStudentList));
//    for (Student student : sentStudentList) {
//        assertTrue(recvStudentList.contains(student));
//    }
  }

  // >>>>> CLEAR DATA STORE <<<<< //

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void clearTheDataStoreShouldMakeNumberOfStudentsZero() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.loadStudent("Barack Obama");
    client.clearDataStore();
    assertEquals(0, client.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void clearTheDataStoreShouldMakeListStudentEmpty() throws IOException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.loadStudent("Angelina Jolie");
    client.clearDataStore();
    assertTrue(client.listStudents().isEmpty());
  }

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void clearTheDataStoreAndRandomShouldYieldException() throws IOException, EmptyStoreException {
    IRouletteV2Client client = new RouletteV2ClientImpl();
    client.connect("localhost", roulettePair.getServer().getPort());
    client.loadStudent("Brad Pitt");
    client.clearDataStore();
    exception.expect(EmptyStoreException.class);
    client.pickRandomStudent();
  }
}