package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        ((RouletteV2ClientImpl) roulettePair.getClient()).clearDataStore();
        assertTrue(roulettePair.getClient().getNumberOfStudents() == 0);
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void shouldReturnEmptyListWhenThereAreNoStudent() throws IOException {
        assertTrue(((RouletteV2ClientImpl) roulettePair.getClient()).listStudents().isEmpty());
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void TheDefaultNameIsUNKNOWN() throws IOException {
        List<Student> l = new LinkedList<>();
        l.add(new Student());
        ((RouletteV2ClientImpl) roulettePair.getClient()).loadStudents(l);
        assertEquals("UNKNOWN", ((RouletteV2ClientImpl) roulettePair.getClient()).listStudents().get(0).getFullname());

    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void shouldReturnNullWhenPickingARandomStudentFromEmptyList() throws IOException, EmptyStoreException {
        assertNull(((RouletteV2ClientImpl) roulettePair.getClient()).pickRandomStudent());
    }

    @Test
    @TestAuthor(githubId = {"BinaryBrain", "D34D10CK"})
    public void clearingAnEmptyDataStoreShouldNotCrash() {
        try {
            RouletteV2ClientImpl c = (RouletteV2ClientImpl) roulettePair.getClient();
            c.clearDataStore();
            c.clearDataStore();
        } catch (IOException ex) {
            fail();
        }
    }
}
