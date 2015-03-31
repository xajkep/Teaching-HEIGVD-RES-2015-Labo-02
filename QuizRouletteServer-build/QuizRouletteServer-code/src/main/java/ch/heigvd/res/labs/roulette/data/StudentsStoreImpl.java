package ch.heigvd.res.labs.roulette.data;

import ch.heigvd.res.labs.roulette.net.protocol.RouletteV1Protocol;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple implementation of the IStudentStore contract. The data is managed in
 * memory (no persistent storage).
 *
 * @author Olivier Liechti
 */
public class StudentsStoreImpl implements IStudentsStore {

  static final Logger LOG = Logger.getLogger(StudentsStoreImpl.class.getName());

  private final List<Student> students = new LinkedList<>();

  @Override
  public synchronized void clear() {
    students.clear();
  }

  @Override
  public synchronized void addStudent(Student student) {
    students.add(student);
  }

  @Override
  public synchronized List<Student> listStudents() {
    List<Student> result = new LinkedList<>(students);
    return result;
  }

  @Override
  public synchronized Student pickRandomStudent() throws EmptyStoreException {
    if (students.isEmpty()) {
      throw new EmptyStoreException();
    }
    int n = (int) (Math.random() * students.size());
    return students.get(n);
  }

  @Override
  public synchronized int getNumberOfStudents() {
    return students.size();
  }

  @Override
  public void importData(BufferedReader reader) throws IOException {
    LOG.log(Level.INFO, "Importing data from input reader of type {0}", reader.getClass());
    List<Student> studentsToAdd = new ArrayList<>();
    String record;
    boolean endReached = false;
    while (!endReached && (record = reader.readLine()) != null) {
      if (record.equalsIgnoreCase(RouletteV1Protocol.CMD_LOAD_ENDOFDATA_MARKER)) {
        LOG.log(Level.INFO, "End of stream reached. New students have been added to the store. How many? We'll tell you when the lab is complete...");
        endReached = true;
      } else {
        if (record != null && !record.trim().equals("")) {
          LOG.log(Level.INFO, "Adding student {0} to the store.", record);
          studentsToAdd.add(new Student(record));
        }
      }
    }
    synchronized (this) {
      students.addAll(studentsToAdd);
    }
    LOG.log(Level.INFO, "There are now {0} students in the store.", getNumberOfStudents());
  }

}
