package ch.heigvd.res.labs.roulette.net.client;

import ch.heigvd.res.labs.roulette.data.EmptyStoreException;
import ch.heigvd.res.labs.roulette.data.Student;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Olivier Liechti
 */
public interface IRouletteV1Client {

  /**
   * Establishes a connection with the server, given its IP address or DNS name
   * and its port.
   *
   * @param server the IP address or DNS name of the servr
   * @param port the TCP port on which the server is listening
   * @throws java.io.IOException
   */
  public void connect(String server, int port) throws IOException;

  /**
   * Disconnects from the server by issuing the 'BYE' command.
   *
   * @throws IOException
   */
  public void disconnect() throws IOException;

  /**
   * Checks if the client is connected with the server
   *
   * @return true if the client is connected with the server
   */
  public boolean isConnected();

  /**
   * Adds a student in the server database, by issuing the 'LOAD' command
   *
   * @param fullname the student's full name
   * @throws IOException
   */
  public void loadStudent(String fullname) throws IOException;

  /**
   * Adds a list of students in the server database, by issuing the 'LOAD'
   * command
   *
   * @param students
   * @throws IOException
   */
  public void loadStudents(List<Student> students) throws IOException;

  /**
   * Asks the server to select a random student, by issuing the 'RANDOM' command
   * and converting the result from json into a Student instance
   *
   * @return an instance of Student randomly selected by the server
   * @throws IOException
   */
  public Student pickRandomStudent() throws EmptyStoreException, IOException;

  /**
   * Asks the server how many students are in the database by issuing the 'INFO'
   * command
   *
   * @return the number of students in the database
   * @throws IOException
   */
  public int getNumberOfStudents() throws IOException;
  
  /**
   * Returns the protocol version implemented by the server
   * @return the version of the Roulette Protocol
   * @throws IOException 
   */
  public String getProtocolVersion() throws IOException;

}
