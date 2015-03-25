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
    public void theClientShouldNotSendEmptyString()throws IOException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("");
        assertEquals(0, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theClientShouldNotSendOnlySpaceString()throws IOException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent(" ");
        assertEquals(0, client.getNumberOfStudents());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void theNameSendShouldBeTheNameRecieved()throws IOException, EmptyStoreException{
        IRouletteV1Client client = roulettePair.getClient();
        client.loadStudent("jean");
        assertEquals("jean", client.pickRandomStudent().getFullname());
    }
    
    @Test
    @TestAuthor(githubId = {"joke1196", "marom17"})
    public void itShouldBePossibleForARouletteClientToDisconnectARouletteServer()throws IOException{
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
    
   
    
}
