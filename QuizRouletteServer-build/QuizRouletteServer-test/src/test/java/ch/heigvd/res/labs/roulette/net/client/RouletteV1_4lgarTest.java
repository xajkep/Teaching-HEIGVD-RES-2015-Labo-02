package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.glassfish.jersey.client.ClientLifecycleListener;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 */
public class RouletteV1_4lgarTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeDisconnectedWhenWeUseDisconectMethod() throws IOException {
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.disconnect();
        assertFalse(roulettePair.getServer().isRunning());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeAbleToAddAStudent() throws IOException, EmptyStoreException{
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.loadStudent("Jean");
        assertEquals("Jean", client.pickRandomStudent().getFullname());
    }

    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeAbleToAddAListOfStudent() throws IOException, EmptyStoreException{
        IRouletteV1Client client = new RouletteV1ClientImpl();
        List<Student> list = new ArrayList<Student>();
        
        list.add(new Student("Jean"));
        list.add(new Student("Christian"));
        list.add(new Student("Micheline"));
        
        assertEquals(3, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = "4lgar")
    public void theServerShouldBeAbleToWorkWithTwoClient() throws IOException, EmptyStoreException{
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        
        client1.loadStudent("Jean");
        client2.loadStudent("Christianne");
        client2.loadStudent("Vanessa");
        
        assertEquals(client1.getNumberOfStudents(), client2.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeAbleToAddStudentWhoHaveTheSameName() throws IOException, EmptyStoreException{
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.loadStudent("Jean");
        client.loadStudent("Jean");
        assertEquals(2, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = "4lgar")    
    public void theClientRandomShouldBeFair() throws IOException, EmptyStoreException{
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.loadStudent("Jean");
        client.loadStudent("Danièle");
        client.loadStudent("Michel");
        
        int[] score = new int[3];
        
        for(int i = 0; i < 900; i++){
            switch(client.pickRandomStudent().getFullname()){
                case"Jean":
                    ++score[0];
                    break;
                case"Danièle":
                    ++score[1];
                    break;
                case"Michel":
                    ++score[2];
                    break;
            }
        }
        
        //If the distribution is uniform, we will have 300 in each row of score
        //It wouldn't be the case. So, we juste calculate the mean of all the
        //delta and compare to a wanted delta.
        score[0] -= 300;
        score[1] -= 300;
        score[2] -= 300;
        double delta = (score[1] + score[2] + score[3])/3.0;
        //Delta should be between in a confidance interval
        //(here, between 1 and 3)
        assertTrue(delta >= 1 && delta <= 3);
    }
}
