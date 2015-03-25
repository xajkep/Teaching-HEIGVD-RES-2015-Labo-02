package ch.heigvd.res.labs.roulette.net.protocol;

/**
 * This class is used to serialize/deserialize the response sent by the server
 * when processing the "INFO" command defined in the protocol specification. The
 * JsonObjectMapper utility class can use this class.
 * 
 * @author Olivier Liechti
 */
public class InfoCommandResponse {

  private String protocolVersion;
  private int numberOfStudents;

  public InfoCommandResponse() {
  }

  public InfoCommandResponse(String protocolVersion, int numberOfStudents) {
    this.protocolVersion = protocolVersion;
    this.numberOfStudents = numberOfStudents;
  }

  public String getProtocolVersion() {
    return protocolVersion;
  }

  public void setProtocolVersion(String protocolVersion) {
    this.protocolVersion = protocolVersion;
  }

  public int getNumberOfStudents() {
    return numberOfStudents;
  }

  public void setNumberOfStudents(int numberOfStudents) {
    this.numberOfStudents = numberOfStudents;
  }

}
