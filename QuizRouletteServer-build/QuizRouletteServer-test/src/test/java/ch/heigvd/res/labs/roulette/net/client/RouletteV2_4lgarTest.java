package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.glassfish.jersey.client.ClientLifecycleListener;
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
public class RouletteV2_4lgarTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);
    
    private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());

    @Test
    @TestAuthor(githubId = "4lgar")
    public void theServerShouldBeAbleToWorkWithTwoClient() throws IOException, EmptyStoreException{
        IRouletteV2Client client1 = new RouletteV2ClientImpl();
        client1.connect("localhost", roulettePair.getServer().getPort());
        IRouletteV2Client client2 = new RouletteV2ClientImpl();
        client2.connect("localhost", roulettePair.getServer().getPort());
        
        client1.loadStudent("Jean");
        client2.loadStudent("Christianne");
        client2.loadStudent("Vanessa");
        
        assertEquals(client1.getNumberOfStudents(), client2.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeDisconnectedWhenWeUseDisconectMethod() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.disconnect();
        assertFalse(client.isConnected());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeAbleToLoadAStudent() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        client.loadStudent("Jean");
        assertEquals("Jean", client.listStudents().get(0).getFullname());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theServerShouldSendACorrectFormattedList() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        
        List<Student> stu = new ArrayList<Student>();
        
        stu.add(new Student("Jean"));
        stu.add(new Student("Marie"));
        stu.add(new Student("Thibault"));
        
        client.loadStudents(stu);
        
        assertEquals(JsonObjectMapper.toJson(stu), JsonObjectMapper.toJson(client.listStudents()));
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theServerShouldCountTheNumberOfCommandCorrectly() throws IOException {
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        
        client.loadStudent("Jean");
        client.loadStudent("Marie");
        client.loadStudent("Danièle");
        assertEquals(3, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = "4lgar")
    public void theClientShouldBeAbleToAddAListOfStudent() throws IOException, EmptyStoreException{
        IRouletteV2Client client = new RouletteV2ClientImpl();
        client.connect("localhost", roulettePair.getServer().getPort());
        
        List<Student> list = new ArrayList<Student>();
        
        list.add(new Student("Jean"));
        list.add(new Student("Christian"));
        list.add(new Student("Micheline"));
        
        //AND don't forget to send it to the server...
        client.loadStudents(list);
                
        assertTrue(client.listStudents().containsAll(list));
    }
}
