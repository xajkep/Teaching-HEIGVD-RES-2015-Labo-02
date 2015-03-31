package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Paul Ntawuruhunga and Karim Gohzlani
 */
public class RouletteV2PaulntaTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);


    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theServerShouldClearDataCorrectly() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
        client.loadStudent("Paul");
        client.loadStudent("Karim");
        assertEquals(2, client.getNumberOfStudents());
        client.clearDataStore();
        assertEquals(0, client.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theServerShouldKeepStudentsAfterADisconnection() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
        client.loadStudent("Paul Walker");
        client.loadStudent("Paul McCartney");
        client.loadStudent("Paul Nta");
        client.disconnect();

        IRouletteV2Client client2 = new RouletteV2ClientImpl();
        client2.connect("localhost", roulettePair.getServer().getPort());
        assertEquals(3, client2.getNumberOfStudents());
    }


    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }
    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theServerShouldFetchAStudentInTheList() throws IOException {
        IRouletteV2Client client =(IRouletteV2Client) roulettePair.getClient();
        Student jack = new Student("Jacques");
        client.loadStudent(jack.getFullname());
        assertTrue(client.listStudents().contains(jack));
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theSizeOfTheListMustBeEqualToTheNumberOfClint() throws IOException {
        IRouletteV2Client client =(IRouletteV2Client) roulettePair.getClient();
        assertTrue(client.listStudents().isEmpty());
    }


    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theSizeOfTheListMustBequalToTheNumberOfClient() throws IOException {
        IRouletteV2Client client =(IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Jacques");
        assertTrue(client.listStudents().size() == 1);
        client.loadStudent("Josiane");
        assertTrue(client.listStudents().size() == 2);
    }



}
