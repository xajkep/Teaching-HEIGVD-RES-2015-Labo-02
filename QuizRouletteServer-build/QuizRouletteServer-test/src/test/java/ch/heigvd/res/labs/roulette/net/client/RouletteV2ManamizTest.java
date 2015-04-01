package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 * 
* @author Mario Ferreira & Duchoud Thibaud
 */
public class RouletteV2ManamizTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theServerGetCorrectNumberOfStudentsWithInfoCommand() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Mario Ferreira");
        client.loadStudent("Duchoud Thibaud");
        assertEquals(2, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theListOfStudentShouldBeEmtyAtBegin() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        assertTrue(client.listStudents().isEmpty());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theListOfStudentShouldBeNotEmtyAfterAAdd() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Mario Ferreira");
        assertFalse(client.listStudents().isEmpty());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theListOfStudentShouldBeEmtyAfterAClear() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Mario Ferreira");
        client.clearDataStore();
        assertTrue(client.listStudents().isEmpty());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theListOfStudentsContainTheStudentAdded() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        String student = "Mario Ferreira";
        client.loadStudent(student);
        assertEquals(client.listStudents().get(0).getFullname(), student);
    }
}
