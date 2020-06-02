package AddressBook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {
    Person p;

    // Creates a person object to be used in all of the tests
    @BeforeEach
    void setUp() {
        p = new Person("Robert", "Karish", "12345 Bonita Landing Circle", "Bonita Springs", "FL", "12345", "239-821-1466");
    }

    // Test branch coverage of the person class constructor with negative testing
    @Test
    void negativeTest_personFirstNameEmpty(){
        // firstName empty
        assertThrows(IllegalArgumentException.class, ()-> new Person("", "person",
                "street", "city", "state", "zip", "phone"));
    }

    // Test branch coverage of the person class constructor with negative testing
    @Test
    void negativeTest_personFirstNameNull(){
        // firstName null
        assertThrows(IllegalArgumentException.class, ()-> new Person(null, "person",
                "street", "city", "state", "zip", "phone"));
    }

    // Test branch coverage of the person class constructor with negative testing
    @Test
    void negativeTest_personLastNameEmpty(){
        // lastName empty
        assertThrows(IllegalArgumentException.class, ()-> new Person("test", "",
                "street", "city", "state", "zip", "phone"));
    }

    // Test branch coverage of the person class constructor with negative testing
    @Test
    void negativeTest_personLastNameNull(){
        // lastName null
        assertThrows(IllegalArgumentException.class, ()-> new Person("test", null,
                "street", "city", "state", "zip", "phone"));
    }

    // Test the getFirstName method
    @Test
    void getFirstNameTest() {
        String firstName = "Robert";
        // Call assertEquals to verify the values are equal
        assertEquals(p.getFirstName(), firstName);
    }

    // Test the getLastName method
    @Test
    void getLastNameTest() {
        String lastName = "Karish";
        // Call assertEquals to verify the values are equal
        assertEquals(p.getLastName(), lastName);
    }

    // Test the getAddress method
    @Test
    void getAddressTest() {
        String address = "12345 Bonita Landing Circle";
        // Call assertEquals to verify the values are equal
        assertEquals(address,p.getAddress());
    }

    // Test the getCity method
    @Test
    void getCityTest() {
        String city = "Bonita Springs";
        // Call assertEquals to verify the values are equal
        assertEquals(city, p.getCity());
    }

    // Test the getState method
    @Test
    void getStateTest() {
        String state = "FL";
        // Call assertEquals to verify the values are equal
        assertEquals(state, p.getState());
    }

    // Test the getZip method
    @Test
    void getZipTest() {
        String zip = "12345";
        // Call assertEquals to verify the values are equal
        assertEquals(zip, p.getZip());
    }

    // Test the getPhone method
    @Test
    void getPhoneTest() {
        String phone = "239-821-1466";
        // Call assertEquals to verify the values are equal
        assertEquals(phone, p.getPhone());
    }

    // Test the toString method
    @Test
    void toStringTest() {
        String toString = "Karish, Robert";
        // Call assertEquals to verify the values are equal
        assertEquals(toString, p.toString());
    }

    // Test the containsString method
    @Test
    void containsStringTest() {
        // Test a false value for branch coverage
        assertFalse(p.containsString("test"));
        // Test all of the true cases
        assertTrue(p.containsString("Robert"));
        assertTrue(p.containsString("Karish"));
        assertTrue(p.containsString("12345 Bonita Landing Circle"));
        assertTrue(p.containsString("Bonita Springs"));
        assertTrue(p.containsString("FL"));
        assertTrue(p.containsString("12345"));
        assertTrue(p.containsString("239-821-1466"));
    }

    // Test the getField method
    @Test
    void getFieldTest() {
        String name = "Robert";
        String lastName = "Karish";
        String address = "12345 Bonita Landing Circle";
        String city = "Bonita Springs";
        String state = "FL";
        String zip = "12345";
        String phone = "239-821-1466";
        // Test that all of the fields match
        assertEquals(name, p.getField(1));
        assertEquals(lastName, p.getField(0));
        assertEquals(address, p.getField(2));
        assertEquals(city, p.getField(3));
        assertEquals(state, p.getField(4));
        assertEquals(zip, p.getField(5));
        assertEquals(phone, p.getField(6));

        // Test the exception of the method
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> p.getField(7));
        assertEquals("Field number out of bounds", thrown.getMessage());
    }
}
