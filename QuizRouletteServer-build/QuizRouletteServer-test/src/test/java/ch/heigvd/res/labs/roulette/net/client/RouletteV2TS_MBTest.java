/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV2Protocol;
import ch.heigvd.res.labs.roulette.net.server.RouletteServer;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Thibault
 */
public class RouletteV2TS_MBTest  {
    
  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Rule
  public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV2Protocol.VERSION);

  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerShouldBeAbleToExportAList() throws IOException{
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      List<Student> liste = new ArrayList<Student>();
      
      liste.add(new Student("Paul"));
      liste.add(new Student("Rachelle"));
      liste.add(new Student("Nadia"));
      
      client.loadStudents(liste);
      
      List<Student> testList = client.listStudents();
      
      assertTrue(liste.equals(testList));
  }
  
  
  //
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerCanClearTheList() throws IOException{
      IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      
      
      List<Student> liste = new ArrayList<Student>();
      
      liste.add(new Student("Paul"));
      liste.add(new Student("Rachelle"));
      liste.add(new Student("Nadia"));
      
      client.loadStudents(liste);
      
      assertEquals(3, client.getNumberOfStudents());
      client.clearDataStore();
      assertEquals(0, client.getNumberOfStudents());
  }
  
  
  // Retourne une liste vide en cas d'erreur
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerReturnsAEmptyList() throws IOException{
    IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      
    List<Student> liste = client.listStudents();
    assertTrue(liste.isEmpty());
  }
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void AnUnnamedStudentShouldBeNamedUNKNOWN() throws IOException{
    IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      
    Student etudiant = new Student();
    List<Student> liste = new ArrayList<Student>();
    liste.add(etudiant);
    
    client.loadStudents(liste);
    
    assertEquals("UNKNOWN", client.listStudents().get(0).getFullname());
  }
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerShouldAddAStudentFromAList() throws IOException{
    IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      
    Student etudiant = new Student("Bob");
    List<Student> liste = new ArrayList<Student>();
    liste.add(etudiant);
    
    client.loadStudents(liste);
    
    assertTrue(client.listStudents().contains(etudiant));
  }
  
  @Test
  @TestAuthor(githubId = {"ThibaultSchowing","mberthouzoz"})
  public void theServerShouldAddAllStudentsFromAList() throws IOException{
    IRouletteV2Client client = (IRouletteV2Client)roulettePair.getClient();
      
    List<Student> liste = new ArrayList<Student>();
    liste.add(new Student("Bob"));
    liste.add(new Student("Joe"));
    liste.add(new Student("John"));
    liste.add(new Student("Contoso"));
    
    client.loadStudents(liste);
    
    assertTrue(client.listStudents().containsAll(liste));
  }
  
  
  
  
  
}

