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
* This class contains automated tests to validate the client and the server
* implementation of the Roulette Protocol (version 1)
*
* @author Jan Purro, Benoist Wolleb
*/
public class RouletteV1JPBWTest
{

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public EphemeralClientServerPair roulettePair = new EphemeralClientServerPair(RouletteV1Protocol.VERSION);

    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theClientShouldBeDisconnectedAfterADisconnectCommand () throws IOException
    {
        IRouletteV1Client client = roulettePair.getClient();
        
        client.disconnect();
        
        if (client.isConnected())
        {
            fail("The client should be disconnected after a disconnect command.");
        }
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theReportedVersionOfTheProtocolShouldBeV1 () throws IOException
    {
        IRouletteV1Client client = roulettePair.getClient();
        
        if (!client.getProtocolVersion().equals(RouletteV1Protocol.VERSION))
        {
            fail("The reported version of the protocol should be V1.");
        }
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void ARandomPickShouldReturnSomething () throws IOException
    {
        IRouletteV1Client client = roulettePair.getClient();
        
        client.loadStudent("Test student");
        
        try {client.pickRandomStudent();}
        catch (EmptyStoreException e)
        {
            fail("The server should return a student.");
        }
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theServerShouldProperlyStoreANewStudent () throws IOException
    {
        IRouletteV1Client client = roulettePair.getClient();
        
        int beforeInsertion = client.getNumberOfStudents();
        client.loadStudent("Test student");
        int afterInsertion = client.getNumberOfStudents();
        
        if (afterInsertion <= beforeInsertion)
        {
            fail("The server should store a new student.");
        }
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theReportedNumberOfStudentsCannotBeNegative () throws IOException
    {
        IRouletteV1Client client = roulettePair.getClient();
        
        int nbStudents = client.getNumberOfStudents();
        
        if (nbStudents < 0)
        {
            fail("The number of students cannot be negative.");
        }
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theServerShouldBeAbleToHandleTwoSimultaneousConnections () throws IOException
    {
        int port = roulettePair.getServer().getPort();
        
        IRouletteV1Client client1 = new RouletteV1ClientImpl();
        IRouletteV1Client client2 = new RouletteV1ClientImpl();
        
        client1.connect("localhost", port);
        client2.connect("localhost", port);
        
        if (!client1.isConnected() && client2.isConnected())
        {
            fail("The server should be able to handle two simultaneous connections.");
        }
        
        client1.disconnect();
        client2.disconnect();
    }
    
    @Test
    @TestAuthor (githubId={"jurporan", "benoistwolleb"})
    public void theServerShouldBeAbleToHandleManySimultaneousConnections () throws IOException
    {
        int port = roulettePair.getServer().getPort();
        int NB_CLIENTS = 1000;
        
        IRouletteV1Client clients[] = new IRouletteV1Client[NB_CLIENTS];
        
        for (int i = 0; i < NB_CLIENTS; i++)
        {
            clients[i] = new RouletteV1ClientImpl();
            clients[i].connect("localhost", port);
        }
        
        for (int i = 0; i < NB_CLIENTS; i++)
        {
            if (!clients[i].isConnected())
            {
                fail("The server should be able to handle many simultaneaous connections.");
            }
        }
        
        for (int i = 0; i < NB_CLIENTS; i++)
        {
            clients[i].disconnect();
        }
    }
}
