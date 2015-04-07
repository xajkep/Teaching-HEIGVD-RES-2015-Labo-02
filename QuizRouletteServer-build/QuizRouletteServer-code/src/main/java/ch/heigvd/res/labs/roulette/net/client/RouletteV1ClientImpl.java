package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;
import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import ch.heigvd.res.labs.roulette.data.Student;
import ch.heigvd.res.labs.roulette.net.protocol.InfoCommandResponse;
import ch.heigvd.res.labs.roulette.net.protocol.RandomCommandResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import ch.heigvd.res.labs.roulette.data.JsonObjectMapper;



/**
 * This class implements the client side of the protocol specification (version 1).
 * 
 * @author Benoît Zuckschwerdt
 */
public class RouletteV1ClientImpl implements IRouletteV1Client {

  private static final Logger LOG = Logger.getLogger(RouletteV1ClientImpl.class.getName());
  private Socket conn;
  private PrintWriter output;
  private BufferedReader input;

  @Override
  public void connect(String server, int port) throws IOException {
    conn = new Socket(server, port);
    
    output = new PrintWriter(conn.getOutputStream(), true);
    input = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    input.readLine();
  }

  @Override
  public void disconnect() throws IOException {
    conn.close();
  }

  @Override
  public boolean isConnected() {
    return conn != null;
  }

  @Override
  public void loadStudent(String fullname) throws IOException {
      output.println(fullname+"\nENDOFDATA");
  }

  @Override
  public void loadStudents(List<Student> students) throws IOException {
      for (int i = 0; i < students.size(); i++) {
          output.println(students.get(i));
      }
      output.println("ENDOFDATA");
  }

  @Override
  public Student pickRandomStudent() throws EmptyStoreException, IOException {
      output.println("RANDOM");
      RandomCommandResponse r = JsonObjectMapper.parseJson(
              input.readLine(), RandomCommandResponse.class);
      
      if (r.getError() != "") {
          throw new EmptyStoreException();
      }
      
      return new Student(r.getFullname());
  }

  @Override
  public int getNumberOfStudents() throws IOException {
      output.println("INFO");
      InfoCommandResponse r = JsonObjectMapper.parseJson(input.readLine(), InfoCommandResponse.class);
      return r.getNumberOfStudents();
  }

  @Override
  public String getProtocolVersion() throws IOException {
    output.println("INFO");
    InfoCommandResponse r = JsonObjectMapper.parseJson(input.readLine(), InfoCommandResponse.class);
    return r.getProtocolVersion();
  }
}
