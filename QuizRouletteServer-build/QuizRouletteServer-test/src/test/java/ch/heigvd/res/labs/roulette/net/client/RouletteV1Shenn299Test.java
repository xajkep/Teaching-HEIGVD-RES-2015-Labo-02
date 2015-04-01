package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.List;
import java.util.ArrayList;

/**
 * This class contains automated tests (other than those proposed by wasadigi)
 * to validate the client and the server implementation of the Roulette Protocol
 * (version 1)
 *
 * @author Sébastien Henneberger
 */
public class RouletteV1Shenn299Test {

   @Rule
   public ExpectedException exception = ExpectedException.none();
   
   @Rule
   public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theClientWhoWritesByeToTheServerShouldBeDisconnectedCorrectlyToTheServer() throws IOException {

      // Premièrement, on récupère le numéro de port
      int port = roulettePair.getServer().getPort();
      // Ensuite, on crée un client
      IRouletteV1Client client = new RouletteV1ClientImpl();
      // On connecte le client au serveur
      client.connect("localhost", port);
      // On vérifie que le client est bien connecté
      assertTrue(client.isConnected());
      // Ensuite on déconnecte le client créé précédemment 
      client.disconnect();
      // On vérifie que le client soit bien deconnecté
      assertFalse(client.isConnected());
   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void itSouldBePossibleToConnectSomeClientsToTheServer() throws IOException {
      
      // Récupération du numéro de port
      int port = roulettePair.getServer().getPort();
      
      // Création de 5 clients
      IRouletteV1Client clients[] = new RouletteV1ClientImpl[5];
      for (int i = 0; i < clients.length; ++i) {
         clients[i] = new RouletteV1ClientImpl();
         clients[i].connect("localhost", port);
         // Vérification que chaque client soit connecté
    	 assertTrue(clients[i].isConnected());
      }
   }

   @Test
   @TestAuthor(githubId = "Shenn299")
   public void itShouldBePossibleForAClientToLoadAListOfStudents() throws IOException {

      // Création du client
      // (un client peut se créer de deux manières, soit comme effectué dans le test
      // précédent soit comme ci-dessous)
      IRouletteV1Client client = roulettePair.getClient();

      // Création de la liste d'étudiant
      List<Student> liste = new ArrayList<>();

      // Ajout de deux étudiants à la liste
      liste.add(new Student("Riri"));
      liste.add(new Student("Fifi"));

      // Chargement de la liste
      client.loadStudents(liste);

      // Vérification que le client ait le bon nombre d'étudiant 
      assertEquals(liste.size(), client.getNumberOfStudents());

   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void ifThereIsOneStudentLoadedTheResultOfARandomShouldPickThatStudent() throws IOException, EmptyStoreException {
      // Création du client
      IRouletteV1Client client = roulettePair.getClient();
      // Chargement d'un seul étudiant
      client.loadStudent("Loulou");
      // Vérification que l'étudiant tiré au hasard soit celui qui a été chargé
      assertEquals("Loulou", client.pickRandomStudent().getFullname());
   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theClientShouldNotBeAbleToConnectToTheServerWithWrongPort() throws IOException {
      // Nécessaire de modifier la "rule"
      // On s'attend à ce que la méthode lève une IOException
      exception.expect(IOException.class);
      
      // Création avec certitude d'un faux numéro de port
      int port = roulettePair.getServer().getPort() + 100;
      // Création d'un client
      IRouletteV1Client client = new RouletteV1ClientImpl();
      // Tentative de connexion au serveur avec le faux numéro de port
      client.connect("localhost", port);
      // On vérifie que le client n'est pas connecté
      assertFalse(client.isConnected());
   }
   
   @Test
   @TestAuthor(githubId = "Shenn299")
   public void theNumberOfStudentsShouldBeTheSameForEveryClients() throws IOException {
      
      // Création de 2 clients
      IRouletteV1Client client1 = roulettePair.getClient();
      IRouletteV1Client client2 = roulettePair.getClient();
      
      // Le premier client charge un étudiant
      client1.loadStudent("Toto");
      
      // On vérifie que le deuxième client voit aussi l'étudiant ajouté
      assertEquals(1, client2.getNumberOfStudents());
   }
  
}