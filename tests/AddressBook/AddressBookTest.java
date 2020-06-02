package AddressBook;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class AddressBookTest {
  AddressBook addressBook;

  // Create an AddressBook object to be used in all of the test
  @BeforeEach
  void setUp() {
    addressBook = new AddressBook();
  }

  // Used for no exceptions
  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  // Test the getPersons() method
  @Test
  void getPersons() {
    // Add two persons
    addressBook.add(new Person("firstName1", "lastName1", "address1",
            "city1", "state1", "zip1", "phone1"));

    addressBook.add(new Person("firstName2", "lastName2", "address2",
            "city2", "state2", "zip2", "phone2"));

    // Create array of expected persons
    final Person[] expected = new Person[] { addressBook.get(0), addressBook.get(1) };

    // Get actual array of persons
    final Person[] actual = addressBook.getPersons();

    // Compare the actual to the expected
    assertArrayEquals(actual, expected);
  }

  // Test the add() method
  @Test
  void add() {
    // Add a person
    addressBook.add(new Person("Robert", "Karish", "12345 Landing Circle",
            "Bonita Springs", "FL", "12345", "239-123-1466"));

    // Get expected person
    final Person expected = addressBook.get(0);

    // Make sure the person is not null
    assertNotNull(expected);
  }

  // Test for timeout of adding a person
  @Test
  void addTimeout() {
    // Timeout if add operation takes longer than 10 milliseconds
    assertTimeout(Duration.ofMillis(2), () -> {
      // Add a person
      addressBook.add(new Person("Robert", "Karish", "12345 Landing Circle",
              "Bonita Springs", "FL", "12345", "239-123-1466"));
    });
  }

  // Test the set() method
  @Test
  void set() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Create an expected person
    final Person expected = new Person("newFirstName", "newLastName",
            "newAddress", "newCity", "newState", "newZip", "newPhone");

    // Set original person to expected person
    addressBook.set(0, expected);

    // Get actual person
    final Person actual = addressBook.get(0);

    // Compare the actual to the expected
    assertEquals(actual, expected);
  }

  // Test the remove() method
  @Test
  void remove() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Remove person
    addressBook.remove(0);

    // Get actual length of persons
    final int actual = addressBook.getPersons().length;

    // Make sure the person is null
    assertEquals(0, actual);
  }

  // Test the get() method
  @Test
  void get() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Get actual person
    final Person actual = addressBook.get(0);

    // Make sure the person is not null
    assertNotNull(actual);
  }

  // Test the clear() method
  @Test
  void clear() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Create expected person array
    final Person[] expected = new Person[] { };

    // Create expected null person for branch coverage
    final Person[] actualNull = null;

    // Clear person array
    addressBook.clear();

    // Get actual person array
    final Person[] actual = addressBook.getPersons();

    // Compare the actual to the expected
    assertArrayEquals(actual, expected);

    // Clear the addressbook again for branch coverage
    addressBook.clear();
  }

  // Test the getRowCount() method
  @Test
  void getRowCount() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Create expected row count
    final int expected = 1;

    // Get actual row count
    final int actual = addressBook.getRowCount();

    // Compare the actual to the expected
    assertEquals(actual, expected);
  }

  // Test the getColumnCount() method
  @Test
  void getColumnCount() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Create expected column count
    final int expected = 7;

    // Get actual column count
    final int actual = addressBook.getColumnCount();

    // Compare the actual to the expected
    assertEquals(actual, expected);
  }

  // Test the getValueAt() method
  @Test
  void getValueAt() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Get actual value
    final Object actual = addressBook.getValueAt(0, 0);

    // Make sure the value is not null
    assertNotNull(actual);
  }

  // Test the getColumnName() method
  @Test
  void getColumnName() {
    // Add a person
    addressBook.add(new Person("firstName", "lastName", "address",
            "city", "state", "zip", "phone"));

    // Get actual value
    final String actual = addressBook.getColumnName(0);

    // Make sure the value is not null
    assertNotNull(actual);
  }
}
