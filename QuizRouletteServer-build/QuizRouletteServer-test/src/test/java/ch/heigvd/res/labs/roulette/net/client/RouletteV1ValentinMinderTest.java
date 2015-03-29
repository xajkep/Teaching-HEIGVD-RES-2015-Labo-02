package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import ch.heigvd.res.labs.roulette.data.Student;
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
 * @author Valentin Minder
 */
public class RouletteV1ValentinMinderTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theClientShouldNOTYieldExceptionWhenNOTEmptyStore() throws IOException {
    try {
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("Simon Oulevay");
        client.pickRandomStudent();
    } catch (EmptyStoreException ex) {
        fail("Expected that EmptyStoreException would not be thrown");
    }
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void pickRandomMustNotbeNullWhenNotEmptyStore() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudent("Eric LeFrancois");
    assertNotNull(client.pickRandomStudent());
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldReturnTheOnlyStudentWhenOneElement() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    String fullname = "Jean-Francois JHH";
    client.loadStudent(fullname);
    Student s = client.pickRandomStudent();
    assertEquals(s.getFullname(), fullname);
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldReturnOneOfTheStudentWhenMultipleElementOneByOne() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    List<String> studentList = new ArrayList<>(2);
    studentList.add("Pier Donini");
    studentList.add("RRH");

    client.loadStudent(studentList.get(0));
    client.loadStudent(studentList.get(1));
    Student s = client.pickRandomStudent();
          
    assertTrue(studentList.contains(s.getFullname()));
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldReturnOneOfTheStudentWhenMultipleElementByList() throws IOException, EmptyStoreException {
    IRouletteV1Client client = roulettePair.getClient();
    List<Student> studentList = new ArrayList<>(2);
    studentList.add(new Student("Eduardo Sanchez"));
    studentList.add(new Student("Catherine Hirsch"));

    client.loadStudents(studentList);
    Student s = client.pickRandomStudent();
          
    assertTrue(studentList.contains(s));
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldCountStudentsWhenAddedInBatch() throws IOException {
    IRouletteV1Client client = roulettePair.getClient();
    List<Student> studentList = new ArrayList<>(2);
    studentList.add(new Student("HES-SO"));
    studentList.add(new Student("EPFL"));

    client.loadStudents(studentList);
    assertEquals(2, client.getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void itShouldBePossibleForTWORouletteClientToConnectToARouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client1 = new RouletteV1ClientImpl();
    IRouletteV1Client client2 = new RouletteV1ClientImpl();
    assertFalse(client1.isConnected());
    assertFalse(client2.isConnected());
    client1.connect("localhost", port);
    client2.connect("localhost", port);
    assertTrue(client1.isConnected());
    assertTrue(client2.isConnected());
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theOTHERClientShouldReturnTheOnlyStudentWhenOneElement() throws IOException, EmptyStoreException {
    IRouletteV1Client client1 = roulettePair.getClient();
    String fullname = "Arnold Schwarzenegger";
    client1.loadStudent(fullname);

    IRouletteV1Client client2 = new RouletteV1ClientImpl();
    client2.connect("localhost", roulettePair.getServer().getPort());
    Student s = client2.pickRandomStudent();
    assertEquals(s.getFullname(), fullname);
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void itShouldBePossibleForARouletteClientToDISConnectFROMARouletteServer() throws IOException {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client client = new RouletteV1ClientImpl();
    client.connect("localhost", port);
    client.disconnect();
    assertFalse(client.isConnected());
  }
  
  @Test
  @TestAuthor(githubId = "ValentinMinder")
  public void theServerShouldNOTHaveZeroStudentsWhenLoaded() throws IOException {
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudent("Vladimir Poutine");
    assertNotEquals(0, client.getNumberOfStudents());
  }
  
}
