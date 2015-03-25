package ch.heigvd.res.labs.roulette.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that represents a student. We only use a single attribute to store
 * the full name of the student.
 *
 * @author Olivier Liechti
 */
public class Student {

  private String fullname;

  public Student() {
    this.fullname = "UNKNOWN";
  }

  public Student(String fullname) {
    this.fullname = fullname;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String name) {
    this.fullname = name;
  }

  public static Student fromJson(String json) throws IOException {
    return JsonObjectMapper.parseJson(json, Student.class);
  }

  @Override
  public String toString() {
    String json = "invalid";
    try {
      json = JsonObjectMapper.toJson(this);
    } catch (JsonProcessingException ex) {
      Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
    }
    return json;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.fullname);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Student other = (Student) obj;
    if (!Objects.equals(this.fullname, other.fullname)) {
      return false;
    }
    return true;
  }

}
