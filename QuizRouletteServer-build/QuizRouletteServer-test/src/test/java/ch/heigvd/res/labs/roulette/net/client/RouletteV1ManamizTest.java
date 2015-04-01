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
* @author Mario Ferreira & Thibaud Duchoud
 */
public class RouletteV1ManamizTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theServerShouldBeStoppedWhenStopIsCalled() throws IOException {
        roulettePair.getServer().stopServer();
        assertFalse(roulettePair.getServer().isRunning());
        roulettePair.getServer().startServer();
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theClientShouldYieldExceptionIfStoreEmpty() throws IOException, EmptyStoreException {
        IRouletteV1Client client = roulettePair.getClient();
        exception.expect(EmptyStoreException.class);
        client.pickRandomStudent();
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theClientNotShouldBeConnected() throws IOException {
        IRouletteV1Client client = new RouletteV1ClientImpl();
        assertFalse(client.isConnected());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void theClientShouldBeConnected() throws IOException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.connect("localhost", port);
        assertTrue(client.isConnected());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void loadStudentsWithList() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        List<Student> liste = new LinkedList<>();
        liste.add(new Student("Mario Ferreira"));
        liste.add(new Student("Duchoud Thibaud"));
        client.loadStudents(liste);
        assertEquals(2, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = {"UnsafeDriving", "Manamiz"})
    public void loadStudentsWithEmptyList() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        List<Student> liste = new LinkedList<>();
        client.loadStudents(liste);
        assertEquals(0, client.getNumberOfStudents());
    }
}
