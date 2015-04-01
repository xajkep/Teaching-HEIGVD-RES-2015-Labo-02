

package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.Random;
import java.util.LinkedList;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Samira Kouchali
 * @author Raphael Mas
 */
public class RouletteV1_SamiraKouchali_RaphaelMas_Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    /**
     * @author: Samira Kouchali
     * @author: Raphael Mas
     *
     * Test personnel ajouté à la suite de ceux implémenté par M. Liechti.
     */
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldLoadSomeStudentsFromAList() throws IOException {
        
        // Ajoute un nombre aléatoire d'étudiant. Min: 0 et Max: 99
        Random rn = new Random();
        final int NUMBER_OF_STUDENTS_SHOULD_BE_ADDED = rn.nextInt() % 100;
                
        // Définition d'une liste vide
        LinkedList<Student> students = new LinkedList<>();
        
        
        // Ajoute les étudiants à la liste
        for (byte i = 0; i < NUMBER_OF_STUDENTS_SHOULD_BE_ADDED; ++i){
            students.add(new Student("TR forever" + i));
        }
        
        // Ajoute la liste des étudiants au sein de la BD.
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudents(students);
        
        // Vérifie si au final le bon nombre d'étudiant a été ajouté
        assertEquals(3, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void verifyTheFullNameOfTheStudentAddedOnTheServer () throws IOException, EmptyStoreException {
        
        // Défini le nom d'un étudiant
        final String STUDENT_FULLNAME = "Raphael Mas";
        
        // Ajout l'étudiant
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent(STUDENT_FULLNAME);
        
        // Récupère "aléatoirement l'unique étudiant et le compare.
        assertEquals(STUDENT_FULLNAME, client.pickRandomStudent().getFullname());
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldBeDisconnected () throws IOException {
        IRouletteV1Client client = roulettePair.getClient();
        client.disconnect();
        assertFalse(client.isConnected());
    }

    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void multipleClientCouldLoadStudentsAtTheSameTime () throws IOException {
        
        Random rn = new Random();
        
        // Défini un nombre de client [1,10].
        final int NUMBER_OF_CLIENT = (rn.nextInt() % 10) +1;
        
        // Effectue les connections au serveur.
        IRouletteV1Client clients[] = new RouletteV1ClientImpl[NUMBER_OF_CLIENT];
        IRouletteV1Client client = roulettePair.getClient();
        
        for (int i = 0; i < NUMBER_OF_CLIENT; ++i) {
            clients[i] = new RouletteV1ClientImpl();
            clients[i].connect("localhost", roulettePair.getServer().getPort());
        }
        
        // Ajoute les étudiants
        for (int i = 0; i < clients.length; ++i) {
            clients[i].loadStudent("Samira Kouchali" + i);
            clients[i].loadStudent("Raphael Mas" + i + i);
        }
        
        // Vérifie le nombre d'étudiant est correct.
        assertEquals(2 * clients.length, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void multipleClientCouldDisconnectFromTheServer () throws IOException {
              
        // Défini un nombre de client [1,10].
        Random rn = new Random();
        final int NUMBER_OF_CLIENT = (rn.nextInt() % 10) +1;
        
        IRouletteV1Client clients[] = new RouletteV1ClientImpl[NUMBER_OF_CLIENT];
        
        for (int i = 0; i < NUMBER_OF_CLIENT; ++i) {
            clients[i] = new RouletteV1ClientImpl();
            clients[i].connect("localhost", roulettePair.getServer().getPort());
        }
        for (int i = 0; i < NUMBER_OF_CLIENT; ++i) {
            clients[i].disconnect();
            assertFalse(clients[i].isConnected());
        }
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldPickACorrecStudentRandomly () throws IOException, EmptyStoreException {
        
        // Crée divers étudiant.
        LinkedList<Student> students = new LinkedList<>();
        students.add(new Student("Samira Kouchali"));
        students.add(new Student("Raphael Mas"));
        students.add(new Student("Anastasia Zherkova"));
        students.add(new Student("Jean Ayoub"));
        
        // Les ajoutes dans la base de donnée
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudents(students);
        
        // Tire au sort un étudiant, si une exception est propagé
        // cela signifie que le test est un échec.
        Student student = roulettePair.getClient().pickRandomStudent();
    }
}
