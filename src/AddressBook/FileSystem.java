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
import java.sql.*;


public class FileSystem {
    
    public void readFile(AddressBook addressBook, File file) throws SQLException, FileNotFoundException {
        if (!file.exists() || !file.canRead()) {
            throw new FileNotFoundException();
        }
       
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        ResultSet rs = connection.createStatement().executeQuery("SELECT lastName, firstName, address, city, state, zip, phone FROM persons");
        // Clear the current AddressBook contents
        addressBook.clear();
        // Iterate through all the records, adding them to the AddressBook
        while (rs.next()) {
            Person p = new Person(
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("state"),
                    rs.getString("zip"),
                    rs.getString("phone"));
            addressBook.add(p);
        }
      
        connection.close();
    }


    public void saveFile(AddressBook addressBook, File file) throws SQLException {
        // Create the table structure
        Connection connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS persons");
        statement.execute("CREATE TABLE persons (firstName TEXT, lastName TEXT, address TEXT, city TEXT, state TEXT, zip TEXT, phone TEXT)");
        // Insert the data into the database
        PreparedStatement insert = connection.prepareStatement("INSERT INTO persons (lastName, firstName, address, city, state, zip, phone) VALUES (?, ?, ?, ?, ?, ?, ?)");
        for (Person p : addressBook.getPersons()) {
            for (int i = 0; i < Person.fields.length; i++) {
                insert.setString(i + 1, p.getField(i));
            }
            insert.executeUpdate();
        }
        
        connection.close();
    }
}
