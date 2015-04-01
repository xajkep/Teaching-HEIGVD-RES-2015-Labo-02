package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.io.*;
import java.net.Socket;
import java.util.Vector;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Cyrill Zundler and Rick Wertenbroek
 */
public class RouletteV2ond9IIIIIIIIIIIIITest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theServerShouldBeEmptyAfterClear() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Ben Qui Râme"); // On ajouter un student afin qu'il y aie au moins un student
        client.clearDataStore(); // On vide le serveur
        assertEquals(0, client.getNumberOfStudents()); // Le serveur devrait être vide
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theClientShouldHaveTheSameListOfStudentsAsServer() throws IOException {
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        Vector<Student> students = new Vector<>(); // Students to be sent to the server
        Vector<String> namesClient = new Vector<>();
        Vector<String> namesServer = new Vector<>();
        List<Student> studentsReceived; // Students received by the server

        // Creation of a test batch of students
        for (int i = 0; i < 4; ++i) {
            students.add(new Student("Number " + (i + 1)));
            namesClient.add(students.elementAt(i).getFullname());
        }

        // Load the batch onto the server
        client.loadStudents(students);

        // We should now get the same students
        studentsReceived = client.listStudents();
        for (Student st : studentsReceived) {
            namesServer.add(st.getFullname());
        }

        namesClient.sort(null);
        namesServer.sort(null);

        // We will check this by testing their names
        assertArrayEquals(namesClient.toArray(), namesServer.toArray());
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theServerShouldRespondCorrectlyOnRandomAfterClear() throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;

        Socket s = new Socket("localhost", roulettePair.getServer().getPort());

        reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        String inviteMSG = reader.readLine(); // not used
        
        writer.println("CLEAR");
        writer.flush();
        inviteMSG = reader.readLine();

        writer.println("RANDOM");
        writer.flush();

        String response = reader.readLine();

        String expectedResponse = "{\"error\":\"There is no student, you cannot pick a random one\"}";
        assertEquals(expectedResponse, response);

    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theServerShouldClearTheDataStore() throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;

        Socket s = new Socket("localhost", roulettePair.getServer().getPort());

        reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        String inviteMSG = reader.readLine(); // not used
        
        writer.println("CLEAR");
        writer.flush();
        inviteMSG = reader.readLine(); // not used
        
        writer.println("INFO");
        writer.flush();

        String response = reader.readLine();

        String expectedResponse = "{\"protocolVersion\":\"2.0\",\"numberOfStudents\":0}";
        assertEquals(expectedResponse, response);
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theServerShouldReturnTheCorrectNumberOfCommands() throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;

        Socket s = new Socket("localhost", roulettePair.getServer().getPort());

        reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        String inviteMSG = reader.readLine(); // not used
        
        writer.println("BYE");
        writer.flush();

        String response = reader.readLine();

        String expectedResponse = "{\"status\":\"success\",\"numberOfCommands\":1}";
        assertEquals(expectedResponse, response);

    }

}
