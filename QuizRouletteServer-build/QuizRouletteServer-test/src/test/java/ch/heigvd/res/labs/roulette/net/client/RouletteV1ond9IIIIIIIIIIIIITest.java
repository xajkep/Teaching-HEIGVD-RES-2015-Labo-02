package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.net.Socket;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.*;
import java.util.List;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Cyrill Zundler and Rick Wertenbroek
 */
public class RouletteV1ond9IIIIIIIIIIIIITest {

    final static int BUFFER_SIZE = 1024;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void RouletteServerExpectedResponseToHelp() throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;

        Socket s = new Socket("localhost", roulettePair.getServer().getPort());

        reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        String inviteMSG = reader.readLine(); // not used
        
        writer.println("HELP");
        writer.flush();

        String response = reader.readLine();

        String expectedResponse = "Commands: [HELP, RANDOM, LOAD, INFO, BYE]";
        assertEquals(expectedResponse, response);
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void NumberOfStudentShouldBeCorrect() throws IOException {
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 0);
        roulettePair.getClient().loadStudent("farfadet");
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 1);
        List<Student> students = new LinkedList<>();
        students.add(new Student("Cici"));
        students.add(new Student("Rick"));
        roulettePair.getClient().loadStudents(students);
        assertEquals(roulettePair.getClient().getNumberOfStudents(), 3);
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void RouletteServerExpectedResponseToInfo() throws IOException {

        BufferedReader reader = null;
        PrintWriter writer = null;

        Socket s = new Socket("localhost", roulettePair.getServer().getPort());

        Integer numberOfStudent = roulettePair.getClient().getNumberOfStudents();

        reader = new BufferedReader(new InputStreamReader(s.getInputStream(), "UTF-8"));
        writer = new PrintWriter(new OutputStreamWriter(s.getOutputStream(), "UTF-8"));

        String inviteMSG = reader.readLine(); // not used
        
        writer.println("INFO");
        writer.flush();

        String response = reader.readLine();

        String expectedResponse = "{\"protocolVersion\":\"1.0\",\"numberOfStudents\":" + numberOfStudent.toString() + "}";
        assertEquals(expectedResponse, response);
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theTestRouletteServerShouldAcceptTwoClientsEachLoadingAStudent() throws IOException {
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        client1.connect("localhost", port);
        client2.connect("localhost", port);

        client1.loadStudent("Laurent");
        assertEquals(1, client2.getNumberOfStudents());
        client2.loadStudent("Fafardet");

        assertEquals(2, client1.getNumberOfStudents());
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theClientShouldBeDisconnectedAfterDisconnect() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        assertEquals(true, client.isConnected()); // Client should be connected (connected by Pair)
        client.disconnect();
        assertEquals(false, client.isConnected()); // Client should be disconnected
    }

    @Test
    @TestAuthor(githubId = {"ond9", "IIIIIIIIIIIII"})
    public void theClientShouldNotBeAbleToLoadAfterDisconnect() throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        client.disconnect();
        exception.expect(IOException.class);
        client.loadStudent("Etudiant Fantôme"); // Should throw IOException !
    }

}
