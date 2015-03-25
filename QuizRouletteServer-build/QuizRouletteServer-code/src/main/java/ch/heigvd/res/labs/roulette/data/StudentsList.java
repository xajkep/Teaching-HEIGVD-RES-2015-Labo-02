package ch.heigvd.res.labs.roulette.data;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to support serialization/deserialization of the protocol
 * messages. The JsonObjectMapper can convert instances of this class into a
 * JSON representation and vice-versa.
 * 
 * @author Olivier Liechti
 */
public class StudentsList {
  
  private final List<Student> students = new ArrayList<>();

  public List<Student> getStudents() {
    ArrayList<Student> list = new ArrayList<>();
    list.addAll(students);
    return list;
  }
  
  public void setStudents(List<Student> list) {
    students.clear();
    students.addAll(list);
  }
  
  public void addAll(List<Student> fromList) {
    setStudents(fromList);
  }

}
