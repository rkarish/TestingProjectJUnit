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

package GUI;

import AddressBook.Person;
// Removed so the program runs with the newest version of Java
// import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.*;


public class PersonDialog extends JDialog {

    public enum Result {
        OK,
        CANCEL,
    }
    private Frame frame;
    private Result result;
    private JTextField firstName;
    private JTextField lastName;
    private JTextField address;
    private JTextField city;
    private JTextField state;
    private JTextField zip;
    private JTextField phone;

  
    public PersonDialog(Frame parent) {
        super(parent);

        frame = parent;
        
        JLabel l;
        AtomicReference<JPanel> p = new AtomicReference<>(new JPanel(new SpringLayout()));

        l = new JLabel("First name:", JLabel.TRAILING);
        p.get().add(l);
        firstName = new JTextField(20);
        l.setLabelFor(firstName);
        p.get().add(firstName);

      
        l = new JLabel("Last name:", JLabel.TRAILING);
        p.get().add(l);
        lastName = new JTextField(20);
        l.setLabelFor(lastName);
        p.get().add(lastName);

  
        l = new JLabel("Address:", JLabel.TRAILING);
        p.get().add(l);
        address = new JTextField(20);
        l.setLabelFor(address);
        p.get().add(address);

     
        l = new JLabel("City:", JLabel.TRAILING);
        p.get().add(l);
        city = new JTextField(20);
        l.setLabelFor(city);
        p.get().add(city);

       
        l = new JLabel("State:", JLabel.TRAILING);
        p.get().add(l);
        state = new JTextField(20);
        l.setLabelFor(state);
        p.get().add(state);

        l = new JLabel("ZIP code:", JLabel.TRAILING);
        p.get().add(l);
        zip = new JTextField(20);
        l.setLabelFor(zip);
        p.get().add(zip);

       
        l = new JLabel("Telephone:", JLabel.TRAILING);
        p.get().add(l);
        phone = new JTextField(20);
        l.setLabelFor(phone);
        p.get().add(phone);

        
        SpringUtilities.makeCompactGrid(p.get(), 7, 2, 6, 6, 6, 6);

        // Set up the buttons
        JPanel buttons = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.setMnemonic('O');
        okButton.addActionListener(e ->
        {
            result = Result.OK;
            setVisible(false);
        });
        buttons.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setMnemonic('C');
        cancelButton.addActionListener(e ->
        {
            result = Result.CANCEL;
            setVisible(false);
        });
        buttons.add(cancelButton);

        // Set window properties
        getContentPane().add(p.get(), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.PAGE_END);
        pack();
        setTitle("Person Information");
        setModalityType(ModalityType.DOCUMENT_MODAL);
        setLocation((parent.getWidth() - getWidth()) / 2, (parent.getHeight() - getHeight()) / 2);
    }

    // public PersonDialog(Frame parent, @Nullable Person person)
    public PersonDialog(Frame parent, Person person) {
        this(parent);
        if (person == null)
            return;
        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        address.setText(person.getAddress());
        city.setText(person.getCity());
        state.setText(person.getState());
        zip.setText(person.getZip());
        phone.setText(person.getPhone());
    }

    
    public Result showDialog() {
        // Default to CANCEL if the user closes the dialog window
        result = Result.CANCEL;
        setVisible(true);
        return result;
    }

 
    public Person getPerson() {
        // Regex for phone and zip for input validation
        String phoneRegex = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
        String zipRegex = "\\d{5}(-\\d{4})?";
        if (firstName != null && lastName != null && !firstName.getText().isEmpty() && !lastName.getText().isEmpty()
            && phone.getText().matches(phoneRegex) && zip.getText().matches(zipRegex)) {
            return new Person(firstName.getText(),
                    lastName.getText(),
                    address.getText(),
                    city.getText(),
                    state.getText(),
                    zip.getText(),
                    phone.getText());
        } else {
            if (!phone.getText().matches(phoneRegex)) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid phone.");
            } else if (!zip.getText().matches(zipRegex)) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid zip.");
            } else if (firstName == null || firstName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid first name.");
            } else if (lastName == null || lastName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame,
                        "Invalid last name.");
            }

            return null;
        }
    }
}
