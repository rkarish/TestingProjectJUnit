package AddressBook;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class AddressBook extends AbstractTableModel {
    private List<Person> persons = new ArrayList<>();

    public Person[] getPersons() {
        return persons.toArray(new Person[persons.size()]);
    }

    // Add a person to the address book
    public void add(Person p) {
        int newIndex = persons.size();
        persons.add(p);
        fireTableRowsInserted(newIndex, newIndex);
    }

    /**
     * Sets the person at the given index to the Person specified.
     *
     * @param index  Index to update.
     * @param person Person to replace the item with.
     */
    public void set(int index, Person person) {
        persons.set(index, person);
        fireTableRowsUpdated(index, index);
    }

    // Remove a person from the address book
    public void remove(int index) {
        persons.remove(index);
        fireTableRowsDeleted(index, index);
    }

    // Get a person at a specific index
    public Person get(int index) {
        return persons.get(index);
    }

    /**
     * Clears this address book.
     */
    public void clear() {
        if (persons == null || persons.size() == 0) {
            return;
        }
        fireTableRowsDeleted(0, persons.size() - 1);
        persons.clear();
    }

    // Get the row count of the AddressBook
    @Override
    public int getRowCount() {
        return persons.size();
    }

    // Get the column count of Person
    public int getColumnCount() {
        return Person.fields.length;
    }

    // Get a field from a person in the address book
    @Override
    public Object getValueAt(int row, int column) {
        return persons.get(row).getField(column);
    }

   // Get the name of a field in the Person class
    @Override
    public String getColumnName(int column) {
        return Person.fields[column];
    }
}