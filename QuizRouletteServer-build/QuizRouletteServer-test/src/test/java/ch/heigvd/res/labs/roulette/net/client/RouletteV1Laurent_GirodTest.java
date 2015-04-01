package ch.heigvd.res.labs.roulette.net.client;

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
 * @author Laurent Girod
 */
public class RouletteV1Laurent_GirodTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void itShouldBePossibleForTwoRouletteClientsToConnectToARouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client clients[] = new RouletteV1ClientImpl[2];
    
    for (int i = 0; i < clients.length; ++i) {
    	clients[i] = new RouletteV1ClientImpl();
    	clients[i].connect("localhost", port);
    	assertTrue(clients[i].isConnected());
    }
  }

  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void itShouldBePossibleForARouletteClientToDisconnectFromARouletteServer() throws Exception {
    //int port = roulettePair.getServer().getPort();
    //IRouletteV1Client client = new RouletteV1ClientImpl();
    //assertFalse(client.isConnected());
    //client.connect("localhost", port);
    IRouletteV1Client client = roulettePair.getClient();
    client.disconnect();
    assertFalse(client.isConnected());
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void itShouldBePossibleForTwoRouletteClientsToDisconnectFromARouletteServer() throws Exception {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client clients[] = new RouletteV1ClientImpl[2];
    
    for (int i = 0; i < clients.length; ++i) {
    	clients[i] = new RouletteV1ClientImpl();
    	clients[i].connect("localhost", port);
    }
    for (int i = 0; i < clients.length; ++i) {
    	clients[i].disconnect();
    	assertFalse(clients[i].isConnected());
    }
  }

  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldRespondWithTheStudentWhenThereIsExactlyOneStudent() throws Exception {
	String studentName = "John Doe";
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudent(studentName);
    assertTrue(client.pickRandomStudent().getFullname().equals(studentName));
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldLoadAListOfStudents() throws Exception {
	String studentName = "John Doe";
	final int num = 5;
	List<Student> students = new ArrayList<Student>();
	
	for (int i = 1; i <= num; ++i) {
		students.add(new Student(studentName + i));
	}
    IRouletteV1Client client = roulettePair.getClient();
    client.loadStudents(students);
    assertEquals(students.size(), client.getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldLoadStudentsFromTwoClients() throws IOException {
    int port = roulettePair.getServer().getPort();
    IRouletteV1Client clients[] = new RouletteV1ClientImpl[2];
    IRouletteV1Client client = roulettePair.getClient();
    
    for (int i = 0; i < clients.length; ++i) {
    	clients[i] = new RouletteV1ClientImpl();
    	clients[i].connect("localhost", port);
    }
    for (int i = 0; i < clients.length; ++i) {
    	clients[i].loadStudent("john Doe" + i);
    }

    assertEquals(clients.length, client.getNumberOfStudents());
  }
}
