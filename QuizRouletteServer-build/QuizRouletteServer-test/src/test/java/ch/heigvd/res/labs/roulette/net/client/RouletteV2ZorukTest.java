package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import org.junit.Test;
import static org.junit.Assert.*;

import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 2)
 *
 * @author Loïc Haas
 */
public class RouletteV2ZorukTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = "Zoruk")
    public void theClientShoudBeV2() throws IOException {
        assertNotNull((RouletteV2ClientImpl)roulettePair.getClient());
    }
    
    @Test
    @TestAuthor(githubId = "Zoruk")
    public void theClearFunctionShoudBeCalledWithNoStudents() throws IOException {
        ((RouletteV2ClientImpl)roulettePair.getClient()).clearDataStore();
    }
    
    @Test
    @TestAuthor(githubId = "Zoruk")
    public void theClearFunctionShoudRemoveAllStudents() throws IOException {
        roulettePair.getClient().loadStudent("Super student");
        ((RouletteV2ClientImpl)roulettePair.getClient()).clearDataStore();
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 0);
    }
    
    @Test
    @TestAuthor(githubId = "Zoruk")
    public void weShoudGetAllStudentsAtOntTime() throws IOException {
        for (int i = 0; i < 10; ++i)
             roulettePair.getClient().loadStudent("Super student : "  + i);
        List<Student> students = ((IRouletteV2Client)roulettePair.getClient()).listStudents();
        
        assertEquals(students.size(), 10);
    }
    
    @TestAuthor(githubId = "Zoruk")
    public void theNumberOfStudentShouldBeCorrect() throws IOException {
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 0);
        
        roulettePair.getClient().loadStudent("Loïc Haas");
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 1);
        
        roulettePair.getClient().loadStudent("Super Student");
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 2);
       
        List<Student> students = new LinkedList<>();
        
        students.add(new Student("James Nolan"));
        students.add(new Student("Stéphan Donnet"));
        students.add(new Student("Thibault Schowing"));
        
        roulettePair.getClient().loadStudents(students);
        
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 5);
        ((IRouletteV2Client) roulettePair.getClient()).clearDataStore();
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 0);
    }

    @Test
    @TestAuthor(githubId = "Zoruk")
    public void theServerShoudHandleMultipleClientAtSameTime() throws IOException {
        List<IRouletteV1Client> clients = new LinkedList<>();
        for (int i = 0; i < 50; ++i) {
           IRouletteV1Client c = new RouletteV1ClientImpl();
           c.connect("localhost", roulettePair.getServer().getPort());
           clients.add(c);
           c.loadStudent("Client " + i);
           c.getNumberOfStudents();
        }
        
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 50);
        
        for (IRouletteV1Client client : clients) {
            client.disconnect();
        }
    }
}