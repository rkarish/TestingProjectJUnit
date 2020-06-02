package AddressBook;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;

public class AddressBookController {
    AddressBook addressBook;

    // Constructor for the controller
    public AddressBookController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    // Add a person to the address book
    public void add(Person p) {
        addressBook.add(p);
    }

    // Change values of a person in the address book
    public void set(int index, Person person) {
        addressBook.set(index, person);
    }

    // Remove a person from the address book
    public void remove(int index) {
        addressBook.remove(index);
    }

    // Get a person from the address book
    public Person get(int index) {
        return addressBook.get(index);
    }

    // Clear all of the people in the address book
    public void clear() {
        addressBook.clear();
    }

    // Open an address book from a file
    public void open(File file) throws FileNotFoundException, SQLException {
        new FileSystem().readFile(addressBook, file);
        addressBook.fireTableDataChanged();
    }

    // Save an address book to a file
    public void save(File file) throws SQLException {
        new FileSystem().saveFile(addressBook, file);
    }

    // Model for the address book
    public AddressBook getModel() {
        return addressBook;
    }
}