package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 * @author Jan Purro, Benoist Wolleb
 */
public class RouletteV2JPBWTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void theDataStoreShouldBeEmptyAtFirst() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        assertEquals(0, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void getNumberOfStudentsShouldReturnTheCorrectNumberOfStudentsInTheDataStore() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        assertEquals(0, client.getNumberOfStudents());
        client.loadStudent("Athos");
        assertEquals(1, client.getNumberOfStudents());
        client.loadStudent("Porthos");
        assertEquals(2, client.getNumberOfStudents());
        client.loadStudent("Aramis");
        assertEquals(3, client.getNumberOfStudents());
        client.loadStudent("D'Artagnan");
        assertEquals(4, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void theDataStoreShouldBeEmptyAfterAClear() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Gauvain");
        client.loadStudent("Lancelot");
        client.loadStudent("Arthur");
        client.loadStudent("Merlin");
        client.loadStudent("Perceval");
        client.clearDataStore();
        assertEquals(0, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void listStudentsShouldNeverReturnANullReference() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        assertTrue(client.listStudents() != null);
        client.loadStudent("Personne");
        assertTrue(client.listStudents() != null);
        client.clearDataStore();
        assertTrue(client.listStudents() != null);
    }
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void listStudentsShouldContainTheCorrectNumberOfStudents() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        assertEquals(client.getNumberOfStudents(), client.listStudents().size());
        client.loadStudent("Edward");
        assertEquals(client.getNumberOfStudents(), client.listStudents().size());
        client.loadStudent("Bella");
        assertEquals(client.getNumberOfStudents(), client.listStudents().size());
        client.loadStudent("Jacob");
        assertEquals(client.getNumberOfStudents(), client.listStudents().size());
    }
    
    @Test
    @TestAuthor(githubId = {"jurporan", "benoistwolleb"})
    public void listStudentsShouldReturnAnEmptyListAfterAClear() throws IOException
    {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Achille");
        client.loadStudent("Jason");
        client.loadStudent("Ajax");
        client.loadStudent("Bellerophon");
        client.clearDataStore();
        assertTrue(client.listStudents().isEmpty());
    }
}
