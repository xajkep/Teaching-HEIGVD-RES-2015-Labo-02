package ch.heigvd.res.labs.roulette.net.protocol;

/**
 * This class defines constants for the Roulette Protocol (version 1)
 *
 * @author Olivier Liechti
 */
public class RouletteV1Protocol {
  
  public final static String VERSION = "1.0";

  public final static int DEFAULT_PORT = 1313;

  public final static String CMD_HELP = "HELP";
  public final static String CMD_RANDOM = "RANDOM";
  public final static String CMD_LOAD = "LOAD";
  public final static String CMD_INFO = "INFO";
  public final static String CMD_BYE = "BYE";

  public final static String CMD_LOAD_ENDOFDATA_MARKER = "ENDOFDATA";

  public final static String RESPONSE_LOAD_START = "Send your data [end with ENDOFDATA]";
  public final static String RESPONSE_LOAD_DONE = "DATA LOADED";

  public final static String[] SUPPORTED_COMMANDS = new String[]{CMD_HELP, CMD_RANDOM, CMD_LOAD, CMD_INFO, CMD_BYE};

}
