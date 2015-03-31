package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Olivier Liechti
 */
public class RouletteV2jermoretTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = "jermoret")
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void clearDataStoreOnEmptyServerDataStore() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.clearDataStore();
        assertEquals(0, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void emptyDataStoreOnServerShouldReturnAnEmptyListOfStudent() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        List<Student> list = client.listStudents();
        assertTrue(list.isEmpty());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void listOfStudentsShouldBeEmptyAtStart() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        assertTrue(client.listStudents().isEmpty());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void listOfStudentShouldWorkInNormalCase() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Mario");
        List<Student> list = client.listStudents();
        assertEquals(1, list.size());
    }

    @Test
    @TestAuthor(githubId = "jermoret")
    public void clearDataStoreOnServerDataStore() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Jerome");
        client.clearDataStore();
        assertEquals(0, client.getNumberOfStudents());
    }
}
