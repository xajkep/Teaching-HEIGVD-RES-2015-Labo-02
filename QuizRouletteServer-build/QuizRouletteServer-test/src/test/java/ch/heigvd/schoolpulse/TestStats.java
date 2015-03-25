package ch.heigvd.schoolpulse;

import java.util.Map;
import org.junit.runner.Result;

/**
 * A simple class to serialize test stats (results and number of tests written
 * by author) into JSON.
 * 
 * @author Olivier Liechti
 */

public class TestStats {
  
  private Map<String, Integer> numberOfTestsByAuthor;
  
  private Result testResults;

  public Map<String, Integer> getNumberOfTestsByAuthor() {
    return numberOfTestsByAuthor;
  }

  public void setNumberOfTestsByAuthor(Map<String, Integer> numberOfTestsByAuthor) {
    this.numberOfTestsByAuthor = numberOfTestsByAuthor;
  }

  public Result getTestResults() {
    return testResults;
  }

  public void setTestResults(Result testResults) {
    this.testResults = testResults;
  }

}
