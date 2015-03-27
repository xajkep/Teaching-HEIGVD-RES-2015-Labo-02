/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Thibault
 */
public class RouletteV1TS_MBTest {
    
  @Rule
  public ExpectedException exception = ExpectedException.none();
  
  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);
    
    // random doit retourner un étudiant s'il y en a
    // créer étudiants
    // en choisir un au hasard
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerShouldSendAStudentIfThereIsOne() throws IOException, EmptyStoreException{
      IRouletteV1Client client = roulettePair.getClient();
      client.loadStudent("Paul");
      assertEquals("Paul",client.pickRandomStudent().getFullname());
  }
  
  // test si on peut load students (avec une liste d'étudiant)
    // créer une liste d'étudiant
    // la loader
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theClientShouldBeAbleToLoadAListOfStudents() throws IOException{
      IRouletteV1Client client = roulettePair.getClient();
      List<Student> liste = new ArrayList<Student>();
      
      liste.add(new Student("Paul"));
      liste.add(new Student("Rachelle"));
      liste.add(new Student("Nadia"));
      
      client.loadStudents(liste);
      
      assertEquals(liste.size(), client.getNumberOfStudents());
  }
  
  
  // Un client doit pouvoir se déconnecter
    // connecter un client et le déconnecter -> vérif qu'il le soit
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theClientShouldBeAbleToDisconnect() throws IOException{
      IRouletteV1Client client = roulettePair.getClient();
      client.disconnect();
      assertTrue(!client.isConnected());
  }
  
  
  // le serveur doit marcher avec deux clients
    // client 1 -> ajoute etudiants
    // client 2 -> ajoute etudiants
    // les deux doivent reçevoir le bon nombre d'étudiants
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theClientShouldWorkWithTwoClients() throws IOException{
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.connect("localhost", port);
        
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        client2.connect("localhost", port);
        
        assertTrue(client.isConnected());
        assertTrue(client2.isConnected());
        
        client.loadStudent("Pierre");
        client.loadStudent("Paul");
        
        client2.loadStudent("Sarah");
        client2.loadStudent("Vanessa");
        client2.loadStudent("Cherry");
        
        assertEquals(5, client.getNumberOfStudents());
        assertEquals(5, client2.getNumberOfStudents());
  }
  
  // Si on effectue deux load d'affilée avec plusieurs étudiants dans chaqu'un
  // les étudiants sont ajoutés aux étudiants existants et non remplacés
    // client -> load étudiants
    // client -> load étudiants
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theLoadFunctionDontEraseAlreadyExistingStudents() throws IOException{
      IRouletteV1Client client = roulettePair.getClient();
      List<Student> liste = new ArrayList<Student>();
      List<Student> liste2 = new ArrayList<Student>();
      
      liste.add(new Student("Paul"));
      liste.add(new Student("Rachelle"));
      liste.add(new Student("Nadia"));
      
      liste2.add(new Student("Jeremy"));
      liste2.add(new Student("Laure"));
      liste2.add(new Student("Toutancamon"));
      
      client.loadStudents(liste);
      client.loadStudents(liste2);
      
      assertEquals(6, client.getNumberOfStudents());
  }
  
  
  // Récupérer un étudiant, changer son nom et vérifier que le nom a bien été changé
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void itShouldBePossibleToChangeStudentName() throws IOException{
      Student etudiant = new Student("Paul");
      etudiant.setFullname("Toaster");
      
      assertEquals("Toaster", etudiant.getFullname());
  }
    
}
