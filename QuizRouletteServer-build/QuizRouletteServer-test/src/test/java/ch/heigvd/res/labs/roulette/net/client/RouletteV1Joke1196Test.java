/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.schoolpulse.TestAuthor;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;


/**
 *
 * @author David Kunzmann & Romain Maillard
 */
public class RouletteV1Joke1196Test {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theLoadFunctionShouldWork()throws IOException, EmptyStoreException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("jean");
        assertEquals("jean", client.pickRandomStudent().getFullname());
        assertEquals(1,client.getNumberOfStudents());
    }
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theRandomFunctionShouldWorkWithOneStudent()throws IOException, EmptyStoreException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("tom");
        assertEquals("tom", client.pickRandomStudent().getFullname());
        assertEquals("tom", client.pickRandomStudent().getFullname());
        assertEquals("tom", client.pickRandomStudent().getFullname());

    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theRandomFunctionShouldWork() throws IOException,EmptyStoreException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("jean");
        client.loadStudent("Tom");
        String student = client.pickRandomStudent().getFullname();
        if(student.equals("Tom") 
                || student.equals("jean")){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }
    
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void itShouldBePossibleForARouletteClientToDisconnectFromARouletteServer()throws IOException{
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client = new RouletteV1ClientImpl();
        client.connect("localhost", port);
        assertTrue(client.isConnected());
        client.disconnect();
        assertFalse(client.isConnected());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void itShouldBePossibleForMultipleRouletteClientsToConnectToARouletteServer()throws IOException{
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        client1.connect("localhost", port);
        client2.connect("localhost", port);
        assertTrue(client1.isConnected());
        assertTrue(client2.isConnected());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void itShouldBePossibleForMultipleRouletteClientsToAddStudents()throws IOException{
        int port = roulettePair.getServer().getPort();
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        client1.connect("localhost", port);
        client2.connect("localhost", port);
        client1.loadStudent("jim");
        client2.loadStudent("marc");
        assertEquals(2,client1.getNumberOfStudents());
        assertEquals(2,client2.getNumberOfStudents());
    }
    
   
    
}
