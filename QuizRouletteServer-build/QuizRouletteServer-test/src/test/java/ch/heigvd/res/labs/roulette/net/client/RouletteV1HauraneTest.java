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
import sun.rmi.server.UnicastRef;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 */
public class RouletteV1HauraneTest {
    
    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void theTestRouletteServerShouldRunDuringTests() throws IOException {
        assertTrue(roulettePair.getServer().isRunning());
    }
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void theTestRouletteClientShouldBeConnectedWhenATestStarts() throws IOException {
        assertTrue(roulettePair.getClient().isConnected());
    }
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void itShouldBePossibleForARouletteClientToConnectToARouletteServer() throws Exception {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        assertFalse(client.isConnected());
        client.connect("localhost", port);
        assertTrue(client.isConnected());
    }
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV1Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void theServerShouldHaveZeroStudentsAtStart() throws IOException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.connect("localhost", port);
        int numberOfStudents = client.getNumberOfStudents();
        assertEquals(0, numberOfStudents);
    }
    
    @Test
    @TestAuthor(githubId = {"wasadigi", "SoftEng-HEIGVD"})
    public void theServerShouldStillHaveZeroStudentsAtStart() throws IOException {
        assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = "SoftEng-HEIGVD")
    public void theServerShouldCountStudents() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        assertEquals(0, client.getNumberOfStudents());
        client.loadStudent("sacha");
        assertEquals(1, client.getNumberOfStudents());
        client.loadStudent("olivier");
        assertEquals(2, client.getNumberOfStudents());
        client.loadStudent("fabienne");
        assertEquals(3, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = "wasadigi")
    public void theServerShouldSendAnErrorResponseWhenRandomIsCalledAndThereIsNoStudent() throws IOException, EmptyStoreException {
        IRouletteV1Client client = roulettePair.getClient();
        exception.expect(EmptyStoreException.class);
        client.pickRandomStudent();
    }
    
    @Test    
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void theServerShouldSendStudentDataWhenStudentsAreInDB() throws EmptyStoreException, IOException {
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("Jean");
        Student jean = client.pickRandomStudent();
        assertNotNull(jean.getFullname());
    }
    
    @Test    
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void theClientShouldBeAbleToAddMultipleStudents() throws IOException {
        List<Student> list = new LinkedList();
        list.add(new Student("Jean"));
        list.add(new Student("Jaques"));
        list.add(new Student("Michel"));
        roulettePair.getClient().loadStudents(list);
        assertEquals(3, roulettePair.getClient().getNumberOfStudents());
    }
    
    @Test    
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void serverShouldHandleEmptyLists() throws IOException {
        List<Student> list = new LinkedList();
        roulettePair.getClient().loadStudents(list);
        assertEquals(0, roulettePair.getClient().getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void theServerShouldNotLoadAnEmptyString() throws IOException {
        RouletteV1ClientImpl client = (RouletteV1ClientImpl) roulettePair.getClient();
        client.loadStudent("");
        assertEquals(0, client.getNumberOfStudents());
    }
    
    @Test    
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void serverHandlesMultipleConnectionsAndDeconnections() throws IOException {
        int port = roulettePair.getServer().getPort();
        int maxClients = 40;
        IRouletteV1Client clients[] = new IRouletteV1Client[maxClients];
        for (int i = 0; i < maxClients; i++) {
            clients[i] = new RouletteV1ClientImpl();
            clients[i].connect("localhost", port);
        }
        for (int i = 0; i < maxClients; i++) {
            clients[i].disconnect();
        }
        assertTrue(roulettePair.getServer().isRunning());
    }
    
    @Test
    @TestAuthor(githubId = {"haurane", "JoaoDomingues"})
    public void theClientShouldDisconnectProperly() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        client.disconnect();
        assertFalse(client.isConnected());
    }
    
}
