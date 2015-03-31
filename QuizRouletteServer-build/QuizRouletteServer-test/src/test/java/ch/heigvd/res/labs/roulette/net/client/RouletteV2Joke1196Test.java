package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author David
 */
public class RouletteV2Joke1196Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theListShouldBeEmpty() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        assertTrue(client.listStudents().isEmpty());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void weShouldBeAbleToAddAList() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        Student bryan = new Student("Bryan");
        Student valentin = new Student("Valentin");
        List<Student> list = new ArrayList<>();
        list.add(bryan);
        list.add(valentin);
        client.loadStudents(list);
        assertFalse(client.listStudents().isEmpty());
        assertEquals(client.listStudents().size(), 2);
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void weShouldBeAbleToCountProperlyTheNumberOfStudentAddedToTheList() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        client.loadStudent("Paul");
        assertEquals(client.listStudents().size(), client.getNumberOfStudents());
        client.loadStudent("Karim");
        assertEquals(client.listStudents().size(), client.getNumberOfStudents());
        client.loadStudent("Toni");
        assertEquals(client.listStudents().size(), client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void clearDataShouldEraseTheContentOfTheList() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        client.loadStudent("Paul");
        assertEquals(client.listStudents().size(), 1);
        client.clearDataStore();
        assertTrue(client.listStudents().isEmpty());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void clearDataShouldPutTheNumberOfStudentsToZero() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        client.loadStudent("Paul");
        assertEquals(client.listStudents().size(), 1);
        client.clearDataStore();
        assertEquals(client.getNumberOfStudents(), 0);
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void randomWithAClearedListShouldThrowAnException() throws IOException, EmptyStoreException {
        int port = roulettePair.getServer().getPort();
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", port);
        client.loadStudent("Paul");
        assertEquals(client.listStudents().size(), 1);
        client.clearDataStore();
        exception.expect(EmptyStoreException.class);
        client.pickRandomStudent();
    }
    
    

}
