package ch.heigvd.res.labs.roulette.net.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Laurent Girod
 */
public class RouletteV2Laurent_GirodTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToClearItsDatabaseEvenWhenEmpty() throws Exception {
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.clearDataStore();
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToClearItsDatabaseWhenItContainsOneStudent() throws Exception {
	String studentName = "John Doe";
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.loadStudent(studentName);
    client.clearDataStore();
    assertEquals(0, client.getNumberOfStudents());
  }

  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToListOneStudentsInItsDatabase() throws Exception {
	String studentName = "John Doe";
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.loadStudent(studentName);
    List<Student> studentsInDB = client.listStudents();
    assertEquals(1, studentsInDB.size());
    assertTrue(studentsInDB.get(0).getFullname().equals(studentName));
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToListTheCorrectNumberOfStudentsInItsDatabase() throws Exception {
	String studentName = "John Doe";
	final int num = 5;
	List<Student> students = new ArrayList<Student>();
	
	for (int i = 1; i <= num; ++i) {
		students.add(new Student(studentName + i));
	}
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.loadStudents(students);
    List<Student> studentsInDB = client.listStudents();
    assertEquals(students.size(), studentsInDB.size());
  }
  
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToListCorrectlyAllStudentsInItsDatabase() throws Exception {
	String studentName = "John Doe";
	final int num = 5;
	List<Student> students = new ArrayList<Student>();
	
	for (int i = 1; i <= num; ++i) {
		students.add(new Student(studentName + i));
	}
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.loadStudents(students);
    List<Student> studentsInDB = client.listStudents();
    
    assertTrue(studentsInDB.containsAll(students));
  }
   
  @Test
  @TestAuthor(githubId = "laurent-girod")
  public void theServerShouldBeAbleToClearItsDatabaseWhenItContainsMultipleStudent() throws Exception {
	String studentName = "John Doe";
	final int num = 5;
	List<Student> students = new ArrayList<Student>();
	
	for (int i = 1; i <= num; ++i) {
		students.add(new Student(studentName + i));
	}
    IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
    client.loadStudents(students);
    client.clearDataStore();
    assertEquals(0, client.getNumberOfStudents());
  }
  
}
