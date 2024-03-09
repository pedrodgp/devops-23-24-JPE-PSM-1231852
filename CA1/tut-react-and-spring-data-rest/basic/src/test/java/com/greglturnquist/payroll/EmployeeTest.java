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
     * Valid job years to be used in the tests
     */
    Integer jobYears;

    /**
     * Valid e-mail to be used in the tests
     */
    String email;


    /**
     * Sets up the employee object and valid data for the tests
     * This method is executed before each test
     */
    @BeforeEach
    void setUp() {
        firstName = "Gandalf";
        lastName = "The Grey";
        description = "Wizard";
        jobYears = 1000;
        email = "gandalf123@gmail.com";
        employee = new Employee(firstName, lastName, description, jobYears, email);
    }

    /**
     * This method tests the Employee constructor
     * The expected result is that the employee object is not null
     */
    @Test
    void testEmployeeConstructorValidArguments() {
        // Arrange & Act
        Employee employeeResult = new Employee(firstName, lastName, description, jobYears, email);
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
                invalidFirstName, lastName, description, jobYears, email));
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
                invalidFirstName, lastName, description, jobYears, email));
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
                invalidFirstName, lastName, description, jobYears, email));
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
                firstName, invalidLastName, description, jobYears, email));
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
                firstName, invalidLastName, description, jobYears, email));
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
                firstName, invalidLastName, description, jobYears, email));
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
                firstName, lastName, invalidDescription, jobYears, email));
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
                firstName, lastName, invalidDescription, jobYears, email));
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
                firstName, lastName, invalidDescription, jobYears, email));
    }

    /**
     * This method tests the Employee constructor when the job years is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorNullJobYears() {
        // Arrange
        Integer invalidJobYears = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, invalidJobYears, email));
    }

    /**
     * This method tests the Employee constructor when the e-mail is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorNullEmail() {
        // Arrange
        String invalidEmail = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests the Employee constructor when the e-mail is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmptyEmail() {
        // Arrange
        String invalidEmail = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests the Employee constructor when the e-mail is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorBlankEmail() {
        // Arrange
        String invalidEmail = "   ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests the Employee constructor when the e-mail is without an '@'
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmailWithoutAt() {
        // Arrange
        String invalidEmail = "frodo.com";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests the Employee constructor when the e-mail is with an '@' at the beginning
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmailWithAtAtBeginning() {
        // Arrange
        String invalidEmail = "@frodo.com";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests the Employee constructor when the e-mail is with an '@' at the end
     * The expected result is an IllegalArgumentException
     */
    @Test
    void testEmployeeConstructorEmailWithAtAtTheEnd() {
        // Arrange
        String invalidEmail = "frodo.com@";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new Employee(
                firstName, lastName, description, jobYears, invalidEmail));
    }

    /**
     * This method tests if the equals method returns true when comparing two employee objects with the same values
     * The expected result is true
     */
    @Test
    void testEquals_sameValues() {
        // Arrange
        Employee sameValuesEmployee = new Employee("Gandalf", "The Grey", "Wizard", 1000, "gandalf123@gmail.com");
        boolean expected = true;
        // Act
        boolean result = employee.equals(sameValuesEmployee);
        // Act & Assert
        assertEquals(expected, result);
    }

    /**
     * This method tests if the equals method returns false when comparing two employee objects with different values
     * The expected result is false
     */
    @Test
    void testEquals_differentValues() {
        // Arrange
        Employee differentValuesEmployee = new Employee("Frodo", "Baggins", "Hobbit", 100, "123@gmail.com");
        boolean expected = false;
        // Act
        boolean result = employee.equals(differentValuesEmployee);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is without an '@'
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_withoutAt() {
        // Arrange
        String invalidEmail = "frodo.com";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(invalidEmail));
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is with an '@' at the beginning
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_withAtAtBeginning() {
        // Arrange
        String invalidEmail = "@frodo.com";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(invalidEmail));
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is with an '@' at the end
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_withAtAtTheEnd() {
        // Arrange
        String invalidEmail = "frodo.com@";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(invalidEmail));
    }

    /**
     * This method tests if the equals method returns false when comparing an employee object to null
     */
    @Test
    void testEquals_equalsToNull() {
        // Arrange
        Employee nullEmployee = null;
        boolean expected = false;
        // Act
        boolean result = employee.equals(nullEmployee);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This method tests if the equals method returns false when comparing an employee object to a different class
     */
    @Test
    void testEquals_equalsToDifferentClass() {
        // Arrange
        String differentClass = "not an Employee";
        boolean expected = false;
        // Act
        boolean result = employee.equals(differentClass);
        // Assert
        assertEquals(expected, result);
    }

    /**
     * This method tests if the hashCode method returns the same value when comparing two employee objects with the same values
     * The expected result is the same hash code
     */
    @Test
    void testHashCode_sameValues() {
        // Arrange
        Employee sameValuesEmployee = new Employee("Gandalf", "The Grey", "Wizard", 1000, "gandalf123@gmail.com");
        // Act
        int result = employee.hashCode();
        int result2 = sameValuesEmployee.hashCode();
        // Assert
        assertEquals(result, result2);
    }

    /**
     * This method tests if the hashCode method returns different values when comparing two employee objects with different values
     * The expected result is different hash codes
     */
    @Test
    void testHashCode_differentValues() {
        // Arrange
        Employee differentEmployee = new Employee("Frodo", "Baggins", "Hobbit", 12, "123@gmail.com");
        // Act
        int result = employee.hashCode();
        int result2 = differentEmployee.hashCode();
        // Assert
        assertNotEquals(result, result2);
    }

    /**
     * This method tests the getId method of the Employee class
     * It checks if the id of the employee object is null
     * The expected result is null
     */
    @Test
    void getId() {
        // Arrange
        Long idExpected = null;
        // Act
        Long result = employee.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * This method tests the setId method of the Employee class
     * It sets the id of the employee object to 1L
     * The expected result is 1L
     */
    @Test
    void setId() {
        // Arrange
        Long idExpected = 1L;
        // Act
        employee.setId(idExpected);
        Long result = employee.getId();
        // Assert
        assertEquals(idExpected, result);
    }

    /**
     * This method tests the getFirstName method of the Employee class
     * It checks if the first name of the employee object is "Gandalf"
     * The expected result is "Gandalf"
     */
    @Test
    void getFirstName() {
        // Arrange
        String firstNameExpected = "Gandalf";
        // Act
        String result = employee.getFirstName();
        // Assert
        assertEquals(firstNameExpected, result);
    }

    /**
     * This method tests the setFirstName method of the Employee class
     * It sets the first name of the employee object to "Sr. Gandalf"
     * The expected result is "Sr. Gandalf"
     */
    @Test
    void setFirstName_validFirstName() {
        // Arrange
        String firstNameExpected = "Sr. Gandalf";
        // Act
        employee.setFirstName(firstNameExpected);
        String result = employee.getFirstName();
        // Assert
        assertEquals(firstNameExpected, result);
    }

    /**
     * This method tests the setFirstName method of the Employee class when the first name is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setFirstName_invalidFirstName_null() {
        // Arrange
        String invalidFirstName = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(invalidFirstName));
    }

    /**
     * This method tests the setFirstName method of the Employee class when the first name is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setFirstName_invalidFirstName_empty() {
        // Arrange
        String invalidFirstName = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(invalidFirstName));
    }

    /**
     * This method tests the setFirstName method of the Employee class when the first name is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setFirstName_invalidFirstName_blank() {
        // Arrange
        String invalidFirstName = "  ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setFirstName(invalidFirstName));
    }

    /**
     * This method tests the getLastName method of the Employee class
     * It checks if the last name of the employee object is "The Grey"
     * The expected result is "The Grey"
     */
    @Test
    void getLastName() {
        // Arrange
        String lastNameExpected = "The Grey";
        // Act
        String result = employee.getLastName();
        // Assert
        assertEquals(lastNameExpected, result);
    }

    /**
     * This method tests the setLastName method of the Employee class
     * It sets the last name of the employee object to "The White"
     * The expected result is "The White"
     */
    @Test
    void setLastName_validLastName() {
        // Arrange
        String lastNameExpected = "The White";
        // Act
        employee.setLastName(lastNameExpected);
        String result = employee.getLastName();
        // Assert
        assertEquals(lastNameExpected, result);
    }

    /**
     * This method tests the setLastName method of the Employee class when the last name is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setLastName_invalidFirstName_null() {
        // Arrange
        String invalidLastName = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setLastName(invalidLastName));
    }

    /**
     * This method tests the setLastName method of the Employee class when the last name is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setLastName_invalidFirstName_empty() {
        // Arrange
        String invalidLastName = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setLastName(invalidLastName));
    }

    /**
     * This method tests the setLastName method of the Employee class when the last name is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setLastName_invalidFirstName_blank() {
        // Arrange
        String invalidLastName = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setLastName(invalidLastName));
    }

    /**
     * This method tests the getDescription method of the Employee class
     * It checks if the description of the employee object is "Wizard"
     * The expected result is "Wizard"
     */
    @Test
    void getDescription() {
        // Arrange
        String descriptionExpected = "Wizard";
        // Act
        String result = employee.getDescription();
        // Assert
        assertEquals(descriptionExpected, result);
    }

    /**
     * This method tests the setDescription method of the Employee class
     * It sets the description of the employee object to "White Wizard"
     * The expected result is "White Wizard"
     */
    @Test
    void setDescription() {
        // Arrange
        String descriptionExpected = "White Wizard";
        // Act
        employee.setDescription(descriptionExpected);
        String result = employee.getDescription();
        // Assert
        assertEquals(descriptionExpected, result);
    }

    /**
     * This method tests the setDescription method of the Employee class when the description is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setDescription_invalidDescription_null() {
        // Arrange
        String invalidDescription = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(invalidDescription));
    }

    /**
     * This method tests the setDescription method of the Employee class when the description is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setDescription_invalidDescription_empty() {
        // Arrange
        String invalidDescription = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(invalidDescription));
    }

    /**
     * This method tests the setDescription method of the Employee class when the description is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setDescription_invalidDescription_blank() {
        // Arrange
        String invalidDescription = "  ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(invalidDescription));
    }

    /**
     * This method tests the getJobYears method of the Employee class
     * It checks if the job years of the employee object is 1000
     * The expected result is 1000
     */
    @Test
    void getJobYears() {
        // Arrange
        Integer jobYearsExpected = 1000;
        // Act
        Integer result = employee.getJobYears();
        // Assert
        assertEquals(jobYearsExpected, result);
    }

    /**
     * This method tests the setJobYears method of the Employee class
     * It sets the job years of the employee object to 500
     * The expected result is 500
     */
    @Test
    void setJobYears_validJobYears() {
        // Arrange
        Integer jobYearsExpected = 500;
        // Act
        employee.setJobYears(jobYearsExpected);
        Integer result = employee.getJobYears();
        // Assert
        assertEquals(jobYearsExpected, result);
    }

    /**
     * This method tests the setJobYears method of the Employee class when the job years is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setJobYears_invalidJobYears_null() {
        // Arrange
        Integer invalidJobYears = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setJobYears(invalidJobYears));
    }

    /**
     * This method tests the getEmail method of the Employee class
     * It checks if the e-mail of the employee object is "gandalf123@gmail.com"
     * The expected result is "gandalf123@gmail.com"
     */
    @Test
    void getEmail() {
        // Arrange
        String emailExpected = "gandalf123@gmail.com";
        // Act
        String result = employee.getEmail();
        // Assert
        assertEquals(emailExpected, result);
    }

    /**
     * This method tests the setEmail method of the Employee class
     * It sets the e-mail of the employee object to "lord@hotmail.com"
     * The expected result is "lord@hotmail.com"
     */
    @Test
    void setEmail_valid() {
        // Arrange
        String emailExpected = "lord@hotmail.com";
        // Act
        employee.setEmail(emailExpected);
        String result = employee.getEmail();
        // Assert
        assertEquals(emailExpected, result);
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is null
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_null() {
        // Arrange
        String invalidEmail = null;
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setDescription(invalidEmail));
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is empty
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_empty() {
        // Arrange
        String invalidEmail = "";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(invalidEmail));
    }

    /**
     * This method tests the setEmail method of the Employee class when the e-mail is blank
     * The expected result is an IllegalArgumentException
     */
    @Test
    void setEmail_invalidEmail_blank() {
        // Arrange
        String invalidEmail = "  ";
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> employee.setEmail(invalidEmail));
    }

    /**
     * This method tests the toString method of the Employee class
     * It checks if the string representation of the employee object is as expected
     * The expected result is "Employee{id=null, firstName='Gandalf', lastName='The Grey', description='Wizard',
     * jobYears='1000', email='gandalf123@gmail.com'}"
     */
    @Test
    void testToString() {
        // Arrange
        String expected = "Employee{id=null, firstName='Gandalf', lastName='The Grey', description='Wizard', jobYears='1000', email='gandalf123@gmail.com'}";

        // Act
        String result = employee.toString();
        // Assert
        assertEquals(expected, result);
    }

}