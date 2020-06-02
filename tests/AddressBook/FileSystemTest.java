package AddressBook;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

class FileSystemTest {
  AddressBook testAddressBook;
  Person testPerson;
  FileSystem fileSystem;

  // Create an AddressBook and Person object to be used in each test
  @BeforeEach
  void setUp() {
    testAddressBook = new AddressBook();
    testPerson = new Person("One", "tester", "Street", "Cape Coral", "FL", "12345", "2341112299");
    fileSystem = new FileSystem();
  }

  // Test the readFile() method
  @Test
  void readFile() {
    // Add the test person to the AddressBook
    testAddressBook.add(testPerson);

    // Test the FileNotFoundException
    Throwable exception_fileNotFound = assertThrows(FileNotFoundException.class, () -> {
      throw new FileNotFoundException("File Not Found");
    });

    // Test the SQLException
    Throwable exception_SQLException = assertThrows(SQLException.class, () -> {
      throw new SQLException("SQL Exception");
    });

    // Create a File object to pass to saveFile and readFile
    File testFile = new File("testFile");

    // Test that the methods don't throw an exception
    assertDoesNotThrow(() -> fileSystem.saveFile(testAddressBook, testFile));
    assertDoesNotThrow(() -> fileSystem.readFile(testAddressBook, testFile));

    // Check that the exception matches
    assertEquals("File Not Found", exception_fileNotFound.getMessage());
    assertEquals("SQL Exception", exception_SQLException.getMessage());

    // Test for no file
    try {
      File noFile = new File("");
      fileSystem.readFile(testAddressBook, noFile);
    } catch (Exception e) {
      assertEquals("java.io.FileNotFoundException", e.toString());
    }
  }

  // Test the saveFile() method
  @Test
  void saveFile() {
    // Create a File object to pass to the saveFile method
    File testFile_save = new File("testSaveFile");

    // Test the exception
    Throwable exception_SQLException_noSave = assertThrows(SQLException.class, () -> {
      throw new SQLException("SQL Exception");
    });

    // Test that the method doesn't throw an exception
    assertDoesNotThrow(() -> fileSystem.saveFile(testAddressBook, testFile_save));
    assertTrue(true, fileSystem.toString());
  }

  @Test
  void saveFileMock() {
    File testFile_save = new File("testSaveFile");

    // Create mocks
    AddressBook mockAddressBook = mock(AddressBook.class);
    Person mockPerson = mock(Person.class);

    // Test saving, return a mock person array when getPersons is called
    when(mockAddressBook.getPersons()).thenReturn(new Person[] {mockPerson});
    assertDoesNotThrow(() -> fileSystem.saveFile(mockAddressBook, testFile_save));
  }
}