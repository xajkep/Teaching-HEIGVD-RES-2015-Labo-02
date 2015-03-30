package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RouletteV2BinaryBrainTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
        assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void theServerShouldBeAbleToClearTheListOfStudents() throws IOException {
        roulettePair.getClient().loadStudent("Test");
        roulettePair.getClient().clear();
        assert (roulettePair.getClient().getNumberOfStudents() == 0);
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void shouldReturnNumberOfSentCommands() throws IOException {
        roulettePair.getClient().connect("localhost", 1313);
        int nbCommandsSent = roulettePair.getClient().disconnect();
        assert (nbCommandsSent == 1);
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void theInfoCommandSHouldReturnThecorrectProtocolNumber(){
        assertTrue(roulettePair.getClient().info().contains("2.0"));
    }
    
    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void theServerShouldReturnAListOfStudents() throws IOException{
        roulettePair.getClient().loadStudent("Test");
        assertEquals("{\"students\":[{\"fullname\":\"Test\"}]}", roulettePair.getClient().list());
    }
    
    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void loadShouldReturnTheNumberOfNewStudents() throws IOException{
        assertTrue(roulettePair.getClient().loadStudent("Test").contains("\"numberOfNewStudents\":1"));
    }
}
