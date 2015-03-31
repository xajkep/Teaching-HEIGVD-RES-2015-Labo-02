package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
public class RouletteV1jermoretTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);
    
    @Test
    @TestAuthor(githubId = "jermoret")
    public void noOneShouldBeAddedIfAnEmptyListIsPassed() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudents(new LinkedList<Student>());
        assertEquals(0, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void theClientShouldBeAbleToDisconnect() throws IOException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.connect("localhost", port);
        client.disconnect();
        assertFalse(client.isConnected());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void theClientMustBeAbleToAddStudents() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        List<Student> list = new LinkedList<>();
        list.add(new Student("Jerome"));
        list.add(new Student("Mario"));
        client.loadStudents(list);
        assertEquals(2, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void theServerShouldntSendAnErrorWhenRandomIsCallWithStudentsInStore() throws IOException, EmptyStoreException {
        try {
            IRouletteV1Client client = roulettePair.getClient();
            client.loadStudent("student");
            client.pickRandomStudent();
        } catch (EmptyStoreException ex) {
            fail("Expected that EmptyStoreException would not be thrown");
        }
    }
    
    @Test
    @TestAuthor(githubId = "jermoret")
    public void twoClientCouldBeConnectedSimultaneously() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        
        client1.connect("localhost", port);
        client2.connect("localhost", port);
        
        assertTrue(client1.isConnected());
        assertTrue(client2.isConnected());
    }
    
    @Test
    @TestAuthor(githubId = "jermoret")
    public void theClient2MustHaveStudentsInsertedByTheClient1() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        
        client1.connect("localhost", port);
        client2.connect("localhost", port);
        
        client1.loadStudent("Jerome");
        client1.loadStudent("Mario");
        
        assertEquals(2, client2.getNumberOfStudents());
    }
}
