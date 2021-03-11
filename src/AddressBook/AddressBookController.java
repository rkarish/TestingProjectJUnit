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
