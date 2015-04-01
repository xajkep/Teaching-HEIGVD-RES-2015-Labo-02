package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import ch.heigvd.schoolpulse.TestAuthor;
import org.junit.rules.ExpectedException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * This class contains automated tests (other than those proposed by wasadigi)
 * to validate the client and the server implementation of the Roulette Protocol
 * (version 2)
 *
 * @author Sébastien Henneberger
 */
public class RouletteV2Shenn299Test {

   @Rule
   public ExpectedException exception = ExpectedException.none();

   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theServerShouldReturnTheCorrectVersionNumber() throws IOException {
      // Vérification du numéro de version du client
      assertEquals(RouletteV2Protocol.VERSION, roulettePair.getClient().getProtocolVersion());
   }

   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theServerShouldHaveZeroStudentAtStart() throws IOException {
      // Création du client
      IRouletteV2Client client = new RouletteV2ClientImpl();
      // Connexion du client créé au serveur
      client.connect("localhost", roulettePair.getServer().getPort());
      // On vérifie qu'il n'a aucun étudiant chargé
      assertEquals(0, client.getNumberOfStudents());
   }

   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theServerShouldBeAbleToCountStudentsCorrectly() throws IOException {
      // Création d'un client
      IRouletteV2Client client = new RouletteV2ClientImpl();
      // Connexion du client au serveur
      client.connect("localhost", roulettePair.getServer().getPort());
      // La liste d'étudiants chargé doit valoir 0
      assertEquals(0, client.listStudents().size());
      // Chargement d'un étudiant
      client.loadStudent("Riri");
      // La liste d'étudiants chargé doit valoir 1
      assertEquals(1, client.listStudents().size());
      // Chargement d'un second étudient
      client.loadStudent("Fifi");
      // La liste d'étudients chargé doit valoir 2
      assertEquals(2, client.listStudents().size());

   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void afterAClearDataStoreTheServerShouldHaveZeroStudent() throws IOException {
      // Création du client
      IRouletteV2Client client = new RouletteV2ClientImpl();
      // Connexion du client au serveur
      client.connect("localhost", roulettePair.getServer().getPort());
      // Ajout d'un étudiant
      client.loadStudent("Toto");
      // Suppression de tous les étudiant chargé
      client.clearDataStore();
      // Vérification qu'il n'y ait plus d'étudiant
      assertEquals(0, client.getNumberOfStudents());
   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theServerShouldBeAbleToSendHisListOfStudent() throws IOException{
      // Création du client
      IRouletteV2Client client = new RouletteV2ClientImpl();
      client.connect("localhost", roulettePair.getServer().getPort());
      // Création d'une liste d'étudiant vide
      List<Student> liste = new ArrayList<>();
      // Ajout de deux étudiants à la liste créée
      liste.add(new Student("Riri"));
      liste.add(new Student("Fifi"));
      // Chargement de la liste d'étudiant
      client.loadStudents(liste);
      // Réception de la liste d'étudiant
      List<Student> listeServeur = client.listStudents();
      // Vérifiction que les deux listes soient identiques
      assertTrue(liste.equals(listeServeur));
   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")   
   public void theServerShouldHaveAllStudentAddedInHisList() throws IOException {
      // Création d'un client
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      // Création d'une liste d'étudiant vide
      List<Student> liste = new ArrayList<>();
      // Ajout de deux étudiants
      liste.add(new Student("Riri"));
      liste.add(new Student("Fifi"));
      // Chargement de la liste d'étudiant
      client.loadStudents(liste);
      // Vérification que le serveur contienne bien tous les étudiants ajoutés
      assertTrue(client.listStudents().containsAll(liste));
   }
}