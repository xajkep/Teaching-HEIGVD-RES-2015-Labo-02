package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author @TestAuthor(githubId = {"edri", "beedle-"})
 */
public class RouletteV2EdriTest
{
   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theServerShouldClearStudents() throws IOException
   {
      IRouletteV1Client monClient = roulettePair.getClient();
      monClient.loadStudent("William");
      monClient.loadStudent("Billy");
      monClient.loadStudent("Mariano");
      assertEquals(3, monClient.getNumberOfStudents());
      ((IRouletteV2Client)monClient).clearDataStore();
      assertEquals(0, monClient.getNumberOfStudents());
   }
    
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theServerShouldListStudents() throws IOException
   {
      IRouletteV1Client monClient = roulettePair.getClient();
      List<Student> listeStudent = new ArrayList<>();
      listeStudent.add(new Student("William"));
      listeStudent.add(new Student("Billy"));
      listeStudent.add(new Student("Mariano"));
      monClient.loadStudents(listeStudent);
      Assert.assertTrue(((IRouletteV2Client)monClient).listStudents().equals(listeStudent));
   }
    
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theDefaultPortShouldBe2613() throws IOException
   {
      assertEquals(2613, roulettePair.getServer().getPort());
   }

   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theLoadingsShouldBeSynchronizedBetweenClients() throws IOException
   {
      IRouletteV1Client client1 = roulettePair.getClient();
      IRouletteV1Client client2 = roulettePair.getClient();
      Student s1 = new Student("Christian Constantin");
      List<Student> listStudents = new ArrayList<>();
      
      listStudents.add(s1);      
      client1.loadStudents(listStudents);
      
      assertEquals(1, ((IRouletteV2Client)client2).listStudents().size());
      Assert.assertTrue(((IRouletteV2Client)client2).listStudents().contains(s1));
      
      client2.loadStudent("Alphonse Brown");
      assertEquals(2, ((IRouletteV2Client)client2).listStudents().size());
   }
  
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theClearingShouldBeSynchronizedBetweenClients() throws IOException
   {
      IRouletteV1Client client1 = roulettePair.getClient();
      IRouletteV1Client client2 = roulettePair.getClient();
      
      client1.loadStudent("Mister T.");
      ((IRouletteV2Client)client2).clearDataStore();
      
      assertEquals(0, ((IRouletteV2Client)client1).listStudents().size());
   }
  
   @Test
   @TestAuthor(githubId = {"edri", "beedle-"})
   public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
      assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
   }
}
