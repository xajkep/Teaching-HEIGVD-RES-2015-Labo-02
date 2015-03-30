package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author @TestAuthor(githubId = {"edri", "beedle-"})
 */
public class RouletteV1EdriTest
{
   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);
   
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theClientShouldBeAbleToLoadListOfStudents() throws IOException
   {
      IRouletteV1Client monClient = roulettePair.getClient();
      List<Student> listeStudent = new ArrayList<>();
      listeStudent.add(new Student("Jean-Marc"));
      listeStudent.add(new Student("Jean-Michel"));
      listeStudent.add(new Student("Jean-Marie"));
      monClient.loadStudents(listeStudent);
      assertEquals(3, roulettePair.getClient().getNumberOfStudents());
   }

   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void pickRandomStudentShouldReturnARandomStudent() throws IOException, EmptyStoreException
   {
      boolean randomStudentExist = false;
      IRouletteV1Client monClient = roulettePair.getClient();
      List<Student> listeStudent = new ArrayList<>();
      listeStudent.add(new Student("Jean-Marc"));
      listeStudent.add(new Student("Jean-Michel"));
      listeStudent.add(new Student("Jean-Marie"));
      monClient.loadStudents(listeStudent);
      Student randomStudent = monClient.pickRandomStudent();
      for (Student s : listeStudent) {
         if (s.getFullname().equals(randomStudent.getFullname())) {
            randomStudentExist = true;
         }
      }
      assertTrue(randomStudentExist);
   }

   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void twoClientsShouldBeAbleToConnectToServerAndLoadStudents() throws IOException
   {
      IRouletteV1Client monClient1 = roulettePair.getClient();
      IRouletteV1Client monClient2 = roulettePair.getClient();
      monClient1.loadStudent("Martial");
      monClient2.loadStudent("Boris");
      monClient1.loadStudent("Michel");
      assertEquals(3, roulettePair.getClient().getNumberOfStudents());
   }
   
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theClientShouldDisconnectCorrectly() throws IOException
   {
      IRouletteV1Client client = roulettePair.getClient();
      client.disconnect();
      assertFalse(client.isConnected());
   }
   
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void aClientLoadingSouldBeVisibleForOtherClients() throws IOException, EmptyStoreException
   {
      final String STUDENT = "Joris";
      
      IRouletteV1Client client1 = roulettePair.getClient();
      IRouletteV1Client client2 = roulettePair.getClient();
      
      client1.loadStudent(STUDENT);
      client1.disconnect();
      
      assertEquals(1, client2.getNumberOfStudents());
      assertEquals(client2.pickRandomStudent().getFullname(), STUDENT);
   }
   
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void aUserMustBeDisconnectedAfterTheServerIsDown() throws IOException
   {
      roulettePair.getServer().stopServer();
      
      assertFalse(roulettePair.getClient().isConnected());
      
      roulettePair.getServer().startServer();
   }
}
