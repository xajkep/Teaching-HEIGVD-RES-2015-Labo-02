package ch.heigvd.res.labs.roulette.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Olivier Liechti
 */
public class JsonObjectMapperTest {
  
  @Test
  public void itShouldBePossibleToSerializeAStudentToJson() throws JsonProcessingException {
    Student student = new Student("olivier liechti");
    String json = JsonObjectMapper.toJson(student);
    assertEquals("{\"fullname\":\"olivier liechti\"}", json);
  }

  @Test
  public void itShouldBePossibleToConvertAJsonStringIntoAStudent() throws IOException {
    String json = "{\"fullname\":\"olivier liechti\"}}";
    Student student = JsonObjectMapper.parseJson(json, Student.class);
    assertNotNull(student);
    assertEquals("olivier liechti", student.getFullname());
  }
  
  @Test
  public void itShouldBePossibleToTransformAStudentIntoJsonAndBack() throws Exception {
    Student student = new Student("olivier liechti");
    String json = JsonObjectMapper.toJson(student);
    Student student2 = JsonObjectMapper.parseJson(json, Student.class);
    assertEquals(student, student2);
  }

}
