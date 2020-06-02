package AddressBook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.Duration;

class AddressBookControllerTest {
  // creates the AddressBookController instance
  AddressBookController testAddressBookController = new AddressBookController(new AddressBook());
  // sample person content
  Person testPerson;
  Person additionalPerson;
  // adds our sample data onto the sample AddressBook

  @BeforeEach
  void setUp() {
    testPerson = new Person("Juan", "Ariza", "1109 TEST Street","Cape Coral","Florida", "33914", "2396991317");
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void add() {
    // we add the test person into the test Addressbook
    testAddressBookController.add(testPerson);
    // check that the test person's last name is the same as the entry in the addressbook as given by the controller
    assertEquals(testPerson.getLastName(), testAddressBookController.addressBook.getValueAt(0,0));
  }

  @Test
  void set() {
    // declare a new Person, then add both Persons onto the AddressBook
    additionalPerson = new Person("test", "person", "1222 TEST Street","Fort Myers","Florida", "33914", "2398679000");
    testAddressBookController.add(testPerson);
    testAddressBookController.add(additionalPerson);
    // now, switch the index of the first person over to the second person
    String firstName = "Juan";
    testAddressBookController.set(1, testPerson);
    // checks that the second position's value is the same as expected (Person moved from index 0 to index 1)
    assertEquals(firstName, testAddressBookController.get(1).getField(1));
  }

  @Test
  void remove() {
    testAddressBookController.add(testPerson);
    int noEntries = 0;
    // once we remove the entry from the AddressBook, it is removed from the array of Persons, so its length is 0
    testAddressBookController.addressBook.remove(0);
    assertEquals(noEntries, testAddressBookController.addressBook.getPersons().length);
  }

  @Test
  void removeTimeout() {
    // use declared address book
    testAddressBookController.add(testPerson);

    //test that the time to remove an entry from the address book is less than 2ms
    // when it is called from the AddressBookController class
    assertTimeout(Duration.ofMillis(2), () -> {
      // remove the person
      testAddressBookController.remove(0);
    });
  }

  @Test
  void get() {
    // add Person onto the addressbook
    testAddressBookController.add(testPerson);
    // check that the testPerson is the same as the one currently in the first index
    assertEquals(testPerson, testAddressBookController.addressBook.get(0));
  }

  @Test
  void clear() {
    // since the person array has no entries, it should have a lenght of 0
    int noEntries = 0;
    // we add the Person onto the Addressbook, then clear it out
    testAddressBookController.add(testPerson);
    testAddressBookController.clear();
    // tests that the number of Persons in the Addressbook is 0 once we clear the entry
    assertEquals(noEntries, testAddressBookController.addressBook.getPersons().length);
  }

  @Test
  void open() throws FileNotFoundException, SQLException {
    // in order to test open, we are testing the FileSystem methods as well, which require an AddressBook and a File to
    // save the changes
    AddressBook testBook = new AddressBook();
    testBook.add(testPerson);

    File file = new File("testFile");
    testAddressBookController.addressBook = testBook;
    testAddressBookController.open(file);
  }

  @Test
  void save() throws SQLException {
    AddressBook testBook = new AddressBook();
    testBook.add(testPerson);

    File file = new File("testSaveFile");

    testAddressBookController.save(file);
  }

  @Test
  void getModel() {
  }

}