package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    /**
     * Employee object to be used in the tests
     * This object is initialized in the setUp method
     */
    Employee employee;

    /**
     * Valid first name to be used in the tests
     */
    String firstName;

    /**
     * Valid last name to be used in the tests
     */
    String lastName;

    /**
     * Valid description to be used in the tests
     */

    String description;


    /**
     * Sets up the employee object and valid data for the tests
     * This method is executed before each test
     */
    @BeforeEach
    void setUp() {
        firstName = "Gandalf";
        lastName = "The Grey";
        description = "Wizard";
        employee = new Employee(firstName, lastName, description);
    }

    /**
     * This method tests the Employee constructor
     * The expected result is that the employee object is not null
     */
    @Test
    void testEmployeeConstructorValidArguments() {
        // Arrange & Act
        Employee employeeResult = new Employee(firstName, lastName, description);
        // Assert
        assertNotNull(employeeResult);
    }

    /**
     * This method tests the Employee constructor when the first name is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorNullFirstName() {
        // Arrange
        String invalidFirstName = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                invalidFirstName, lastName, description));
    }

    /**
     * This method tests the Employee constructor when the first name is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmptyFirstName() {
        // Arrange
        String invalidFirstName = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                invalidFirstName, lastName, description));
    }

    /**
     * This method tests the Employee constructor when the first name is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorBlankFirstName() {
        // Arrange
        String invalidFirstName = "   ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                invalidFirstName, lastName, description));
    }

    /**
     * This method tests the Employee constructor when the last name is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorNullLastName() {
        // Arrange
        String invalidLastName = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, invalidLastName, description));
    }

    /**
     * This method tests the Employee constructor when the last name is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmptyLastName() {
        // Arrange
        String invalidLastName = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, invalidLastName, description));
    }

    /**
     * This method tests the Employee constructor when the last name is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorBlankLastName() {
        // Arrange
        String invalidLastName = "  ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, invalidLastName, description));
    }

    /**
     * This method tests the Employee constructor when the description is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorNullDescription() {
        // Arrange
        String invalidDescription = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, invalidDescription));
    }

    /**
     * This method tests the Employee constructor when the description is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmptyDescription() {
        // Arrange
        String invalidDescription = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, invalidDescription));
    }

    /**
     * This method tests the Employee constructor when the description is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorBlankDescription() {
        // Arrange
        String invalidDescription = "  ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, invalidDescription));
    }


}