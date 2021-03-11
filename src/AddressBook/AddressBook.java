/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
