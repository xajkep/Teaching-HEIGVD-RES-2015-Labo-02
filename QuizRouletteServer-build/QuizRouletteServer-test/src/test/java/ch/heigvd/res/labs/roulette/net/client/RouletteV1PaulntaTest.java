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
 *
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Paul Ntawuruhunga and Karim Gohzlani
 */
public class RouletteV1PaulntaTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);


    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theServerShouldManageTwoCLients() throws IOException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        client1.connect("localhost",port);
        client2.connect("localhost",port);
        assertTrue(client1.isConnected());
        assertTrue(client2.isConnected());
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void weShouldLoadAListOfStudents() throws IOException {
        List<Student> students  = new LinkedList<Student>();
        students.add(new Student("Paul"));
        students.add(new Student("Karim"));
        students.add(new Student("Justin Bieber"));
        students.add(new Student("Austin mahone"));

        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudents(students);
        assertEquals(4, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theClientsShouldShareInformation() throws IOException {

        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();

        client1.connect("localhost", port);
        client2.connect("localhost", port);

        client1.loadStudent("Paul");
        assertEquals(1, client2.getNumberOfStudents());
        client2.loadStudent("Paulette");
        assertEquals(2, client1.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void theClientShouldBeDisconnectedWhenATestStarts() throws IOException {
        IRouletteV1Client client = new RouletteV1ClientImpl();
        assertTrue(!client.isConnected());
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void thereShouldBeTheOnlyLoadedStudentWhenRandomIsCalledWithOneStudent() throws IOException, EmptyStoreException {
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("Karim");
        assertEquals(client.pickRandomStudent().getFullname(), "Karim");
    }

    @Test
    @TestAuthor(githubId = {"gweezer7", "paulnta"})
    public void thereShouldBeNoExceptionWhenRandomIsCalled() throws IOException {
        try {
            IRouletteV1Client client = roulettePair.getClient();
            client.loadStudent("Karim");
            client.pickRandomStudent();
        } catch (EmptyStoreException e) {
            fail("Expected that EmptyStoreException would not be thrown");
        }
    }
}
