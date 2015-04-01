package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import static org.junit.Assert.*;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

/**
 * This class contains automated tests to validate the client and the server
 * implementation of the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 * @author Samira Kouchali
 * @author Raphael Mas
 */
public class RouletteV2_SamiraKouchali_RaphaelMas_Test {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

    /**
     * @author: Samira Kouchali
     * @author: Raphael Mas
     *
     */
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldReturnTheCorrectVersionNumber () throws IOException {
        assertTrue(RouletteV2Protocol.VERSION.equals(roulettePair.getClient().getProtocolVersion()));
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldBeAbleToClearTheDatabase () throws IOException {
    
        // Ajoute 2 étudiants.
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Samira Kouchali");
        client.loadStudent("Anastasia Zherkova");
        client.loadStudent("Raphael Mas");
        client.loadStudent("Jean Ayoub");
        
        // Supprime la base de donnée.
        client.clearDataStore();
        
        // Vérifie que la DB soit bel et bien vide.
        assertEquals(0, client.getNumberOfStudents());
    }
            
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldBeAbleToClearTheDatabaseWhenEmpty () throws IOException {
        
        // Supprime le contenu de la base de donnée
        ((IRouletteV2Client)roulettePair.getClient()).clearDataStore();
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theListOfStudentFromDbShouldBeCorrectAnyTime () throws IOException {
        
        // Ajout d'un nombre aléatoire d'étudiants dans la DB
        Random rn = new Random();
        final int NUMBER_OF_STUDENTS = (rn.nextInt() % 10) +1;
        
        // Création de la liste d'insertion
        LinkedList<Student> students = new LinkedList<>();
        
        // Création d'une liste d'étudiant
        String fullname = "Mash";
        char concat = 'a';
        for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
            
            // Selon si l'incérment est pair ou non, ajoute une lettre au début
            // ou à la fin.
            // cette lettre change à chaque itération.
            if (i % 2 == 1) {
                fullname = fullname + concat++;                
            }
            else {
                fullname = concat++ + fullname;
            }
            
            students.add(new Student(fullname));
        }
        
        // Ajoute la liste à la DB
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudents(students);
        
        // Tri la liste des etudiant insérer. 
        /*Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student e1, Student e2) {
                return e1.getFullname().compareTo(e2.getFullname());
            }
        });*/
         
        // Récupère la liste des étudiants stockés dans le serveur 
        LinkedList<Student> db = new LinkedList<>(client.listStudents());
        
        // Vérifie que tous les éléments de db soit contenu dans students 
        // et vice-versa.
        assertTrue(students.containsAll(db) && db.containsAll(students));
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void insertionOfAStudentWithoutName () throws IOException, EmptyStoreException {
        
        // Création d'un étudiant fantome.
        Student ghost = new Student();
        LinkedList<Student> list = new LinkedList<>();
        list.add(ghost);
        
        // Charge l'étudiant dans la DB
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudents(list);
        
        // Vérifie que l'unique etudiant (notre ghost) ait le bon nom. 
        assertEquals("UNKNOWN", client.pickRandomStudent().getFullname());
    }
    
    @Test
    @TestAuthor(githubId = {"xxxkikixxx", "SamiraKouchali"})
    public void theServerShouldThrowAnExceptionWhenClientTryToPickAStudentWhenDbIsEmpty () throws IOException, EmptyStoreException {
        
        // Ajoute des étudiants à la DB
        IRouletteV2Client client = (IRouletteV2Client) roulettePair.getClient();
        client.loadStudent("Samira Kouchali");
        client.loadStudent("Raphael Mas");
        
        // Efface son contenu
        client.clearDataStore();
        
        // Essaie de prendre un étudiant au hasard
        exception.expect(EmptyStoreException.class);
        client.pickRandomStudent();
    }
}
