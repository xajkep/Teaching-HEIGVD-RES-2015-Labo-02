package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
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
public class RouletteV2HauraneTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldReturnAnEmptListWhenTestStarts() throws IOException {
     assertTrue(((RouletteV2ClientImpl)roulettePair.getClient()).listStudents().isEmpty());
  }
  
  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldReturnAListAfterSomeLoads() throws IOException {
     RouletteV2ClientImpl client = (RouletteV2ClientImpl)roulettePair.getClient();
     client.loadStudent("Jean");
     client.loadStudent("Jacques");
     client.loadStudent("Pierre");
     Student[] students = new Student[]{
                new Student("Jean"), 
                new Student("Jacques"), 
                new Student("Pierre")};
     boolean sameStudents = true;
     for(int i = 0; i < students.length; i++){
        if(!students[i].equals(client.listStudents().get(i))){
           sameStudents = false;
           break;
        }
     }
     assertTrue(sameStudents);
  }
  
  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldBeEmptyAfterAClear() throws IOException {
    ((RouletteV2ClientImpl)roulettePair.getClient()).clearDataStore();
     assertEquals(0, roulettePair.getClient().getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldReturnAListAfterLoadedAList() throws IOException {
     RouletteV2ClientImpl client = (RouletteV2ClientImpl)roulettePair.getClient();
     ArrayList<Student> students = new ArrayList<>();
     students.add(new Student("Jean"));
     students.add(new Student("Jacques"));
     students.add(new Student("Pierre"));
     client.loadStudents(students);
     boolean sameStudents = true;
     for(int i = 0; i < students.size(); i++){
        if(!students.get(i).equals(client.listStudents().get(i))){
           sameStudents = false;
           break;
        }
     }
     assertTrue(sameStudents);
     client.clearDataStore();
  }

  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldNotLoadAnEmptyString() throws IOException {
     RouletteV2ClientImpl client = (RouletteV2ClientImpl)roulettePair.getClient();
     client.loadStudent("");
     assertEquals(0, client.getNumberOfStudents());
  }
  
  @Test
  @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
  public void theServerShouldNotLoadWithOnlyWhiteSpace() throws IOException {
     RouletteV2ClientImpl client = (RouletteV2ClientImpl)roulettePair.getClient();
     client.loadStudent("    ");
     assertEquals(0, client.getNumberOfStudents());
  }
}