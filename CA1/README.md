# Technical Report - Class Assignment 1
### DevOps 23-24 JPE-PSM
#### Pedro Pires, 1231852

## Introduction

The primary objective of this academic endeavor was to engage with our individual repositories while integrating a basic Spring application to embed new functionalities, thereby deepening our understanding of version control processes, specifically branching and merging.

The assignment was structured to provide a practical experience in repository management, encompassing a spectrum of essential tasks such as issue creation, commit management, branch development, feature addition, and the merging of branches back into the main branch followed by issue closure.

This exercise, while focusing on fundamental features, was designed to instill a comprehensive grasp of the essential version control protocols and collaborative development workflows, thereby enhancing our competency in software development practices.

The final result of the assignment can be found [here](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852).

## Index
1. [Introduction](#introduction)
2. [Initialize the Git Repository](#initialize-the-git-repository)
3. [Issues](#issues)
4. [Getting Started](#getting-started)
5. [Implementing Changes](#implementing-changes)
    - [JobYears: Add new field jobYears & unit tests for attributes of the Employee class](#jobyears-add-new-field-jobyears--unit-tests-for-attributes-of-the-employee-class)
    - [Email Part 1: Adding the email field](#email-part-1-adding-the-email-field)
    - [Email Part 2: Fix invalid email](#email-part-2-fix-invalid-email)
6. [The timeline of the commits and branches](#the-timeline-of-the-commits-and-branches)
7. [Alternative to Git: Mercurial](#alternative-to-git-mercurial)
    - [Comparative Analysis with the Assignment Workflow](#comparative-analysis-with-the-assignment-workflow)
    - [Branching and Feature Implementation](#branching-and-feature-implementation)
    - [Committing Changes](#committing-changes)
    - [Merging and Tagging](#merging-and-tagging)
    - [Leveraging Mercurial: Design Considerations](#leveraging-mercurial-design-considerations)
    - [Conclusion: Git vs. Mercurial](#conclusion-git-vs-mercurial)
    - [Useful Mercurial Commands for Version Control](#useful-mercurial-commands-for-version-control)
8. [Conclusion](#conclusion)


## Initialize the Git repository

To set up your project's version control with Git, follow these organized steps:
   ```bash
echo "#<Repository-Name>" >> README.md
git init
git add README.md
git commit -m "first commit"
git branch -M main
git remote add origin <repository-URL>
git push -u origin main
   ```
1. **Create README.md File:** Start by creating a `README.md` file, which will serve as the initial document in your repository. It typically includes the repository name or a brief description of the project.

2. **Initialize Repository:** Use the `git init` command to initialize a new Git repository locally. This command creates a new .git directory in your project, setting up the necessary framework for your version control.

3. **Stage README.md File:** Add the `README.md` file to the staging area with `git add README.md`. Staging files prepares them for the next commit, allowing you to selectively control which changes you want to include in the upcoming commit.

4. **Commit Changes:** Commit your staged file to the repository history with `git commit -m "first commit"`. This action captures a snapshot of the project's currently staged changes.

5. **Set Main Branch:** Rename your branch to ‘main’ using `git branch -M main`, establishing it as the primary branch for your project.

6. **Link Remote Repository:** Connect your local repository to a remote server using `git remote add origin <repository-URL>`. Replace `<repository-URL>` with the URL of your remote repository. This step is crucial for remote collaboration and backup.

7. **Push Changes to Remote:** Finally, upload your local branch commits to the remote repository with `git push -u origin main`. This command synchronizes your local and remote repositories, ensuring that your initial setup is successfully mirrored online.

Each step is designed to sequentially build upon the last, ensuring a smooth and efficient Git repository initialization. By following these guidelines, you establish a solid foundation for your project’s version control system.



## Issues

The GitHub issues feature was used because they are a great way to keep **track of the work** that needs to be done, and they can be used to assign tasks to specific people, label tasks, and comment on tasks.
They can be created in the repository's main page, and can also be closed when the problem is solved by associating it with a commit
`git commit -m "Message of the commit, Closes #1"`, the `#1` is the number of the issue that is being closed in this example.

Throughout this project, **five key issues** can systematically be resolved, a link to each issue is provided by clicking on the issue number.
1. **[Issue #1](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/issues/1)** ```Copy React.js and Spring Data REST Application Code to New Folder (CA1).```
2. **[Issue #2](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/issues/2)** ```Add New Field for Employee Job Years.```
3. **[Issue #3](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/issues/3)** ```Unit Tests for Employee Attributes Validation.```
4. **[Issue #4](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/issues/4)** ```Add new field for Employee - email.```
5. **[Issue #5](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/issues/5)** ```Fix validation of an email.```

The final issue, which involves completing this README file, is currently in progress.

**All prior issues have been methodically closed** following their resolution, reflecting a structured and disciplined approach to project management.

---

## Getting Started

1. **Create the first issue** in the repository to track the changes that will be made:

- #### Issue #1
     - **Tittle:** ```Copy React.js and Spring Data REST Application Code to New Folder (CA1).```
     - **Description:** ```Create a new folder named “CA1” and copy the code from the Tutorial React.js and Spring Data REST Application into this folder.```


2. **Clone** [this repository](https://github.com/spring-guides/tut-react-and-spring-data-rest) to your local machine as it will serve as the basis for the task. Noting that only the _basic_ module is required, the other ones can be deleted.

The rest of the assignment can be done by opening a bash terminal and running the following commands:

3. **Navigate** to the project directory (assuming the Tutorial React.js and Spring Data REST Application is already locally available). The command `cd <path/>` changes the current directory.
   ```bash
   cd path/to/TutorialReactSpringDataREST
   ```

4. **Copy** the application into a new CA1 folder. The command `cp` copies the directories and files stated (`.`, the current directory), the `-r` notation says it will be copied recursively (all its contents) and `../CA1` is the destination folder.
   ```bash
   cp -r . ../CA1
   cd ../CA1
   ```


5. **Add** all files in to the staging area, setting the stage for the next commit. The `.` notation selects all files that have been modifie or added.
   ```bash
   git add .
   ```
 

6. **Commit** the staged files with a descriptive message that encapsulates the purpose of the changes, which also serves to close the initial issue.
   [Can access commit here.](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/85ae6f51f94fd12bb9b44c1f731aa41cab28f398)
   ```bash
   git commit -m "CA1: Created CA1 package with a copy of tut-react-and-spring-data-rest basic version, Closes #1"
   ``` 
   
7. **Push** your commit to the remote repository, effectively synchronizing your local advancements with the remote repository.
   ```bash
   git push
   ```
   
8. Conclude this setup phase by **tagging the commit**, which marks a significant version in your project’s development lifecycle.
   ```bash
   git tag -a v1.1.0
   git push origin v1.1.0
   ```
   
To see the actual project, open a bash in the basic folder of the app and run the following command `./mvnw spring-boot:run
`
After that, open a browser and navigate to [http://localhost:8080](http://localhost:8080) to see.
Should look like this:
![image](https://i.postimg.cc/J4YZb8rQ/Captura-de-ecr-2024-03-13-234907.png)
---

## Implementing Changes
### JobYears: Add new field jobYears & unit tests for atrributes of the Employee class

This segment of the assignment is conducted on the **main branch**, with the objective centered around the incorporation of a **new attribute** within the _Employee_ class. Additionally, it involves the creation and implementation of corresponding **unit tests** for this new field to ensure functionality and reliability.

1. **Create a new issue** in the repository to track the changes that will be made.
- #### Issue #2
  - **Tittle:** ```Add New Field for Employee Job Years.```
  - **Description:** ```Develop a new feature to add a field that records the years of an employee’s service. This field, named “jobYears,” should store integer values representing the duration of employment. Write unit tests to validate the creation of employees with the “jobYears” attribute.```
- #### Issue #3
   - **Tittle:** ```Unit Tests for Employee Attributes Validation.```
   - **Description:** ```Create unit tests specifically focused on validating all the attributes of Employee class. Ensure that no null or empty values are allowed.```

2. The right approach to write code is using **TDD (_Test Driven Development_)**, but the code came without any unit tests, so the next step is to **create a new class called _EmployeeTest_**, which will contain the unit tests for the _Employee_ class.
They should cover all the methods and the validations (to be implemented) of the class _constructor_, _getters_, _setters_, _toString_, _equals_ and _hashCode_ methods, ensuring that all **the attributes can not be null or empty**.

```java
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
      jobYears = 1000;
      email = "gandalf123@gmail.com";
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
   ```
3. **Implement the validation** methods to cover those tests.
```java
private boolean isValidName(String name) {
   return (name != null && !name.trim().isEmpty());
}
private boolean isValidDescription(String description) {
   return (description != null && !description.trim().isEmpty());
}
private boolean isValidArguments(String firstName, String lastName, String description) {
   return isValidName(firstName) &&
           isValidName(lastName) &&
           isValidDescription(description);
}
```

4. **Constructor** method need also be updated to include the validations:
```java
public Employee(String firstName, String lastName, String description) throws IllegalArgumentException {
   if (!isValidArguments(firstName, lastName, description)) {
      throw new IllegalArgumentException("Invalid Employee arguments");
   }
   this.firstName = firstName;
   this.lastName = lastName;
   this.description = description;
   this.jobYears = jobYears;
   this.email = email;
}
```

**Three commits** until now can be made to the main branch to implement the changes:
1. The first commit adds the unit tests for the constructor of the Employee class, marking the issue #3.
   - `CA1: added Unit Tests for constructor of Employee, #3` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/89be8fb413657f2b6e10cd5fb57035a68d4f10ea)

2. The second commit adds the unit tests for the equals, hashcode, toString, gets and sets methods of the Employee, marking the issue #3.
    - `CA1: added Unit Tests for (equals, hashcode, toString, gets and sets) of Employee, #3` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/fab6de38558210c0690a650132e88de143edf1aa)
3. The third commit implements the validations for the attributes of the Employee class and **closes the issue #3**.
    - `CA1: implemented validations for attributes of Employee, Closes #3` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/6659ee8b42be252650bcbc0a6901abdf3b96db35)

The next step is to add the new field to the application, which will be the **job years**.
1. Create **unit tests** for the new field, ensuring that all methods and validations are covered:

```java
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
```

2. **Add _jobYears_** atrribute:
   ```java
   private Integer jobYears;
   ```
3. Create **validation** method:
   ```java
   private boolean isValidJobYears(Integer jobYears) {
		return jobYears != null;
	}
   ```
4. Update **constructor**:
   ```java
    public Employee(String firstName, String lastName, String description, Integer jobYears) throws IllegalArgumentException {
		if (!isValidArguments(firstName, lastName, description, jobYears)) {
			throw new IllegalArgumentException("Invalid Employee arguments");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobYears = jobYears;
	}
    ```
5. Add the **_getters_** and **_setters_**, including the validations in the setter:
   ```java
   public Integer getJobYears() {
        return jobYears;
    }
   public void setJobYears(Integer jobYears) throws IllegalArgumentException {
        if (!isValidJobYears(jobYears)) {
            throw new IllegalArgumentException("Invalid job years");
        }
        this.jobYears = jobYears;
    }
   ```
6. Update **_toString_** method:
   ```java
   return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", jobYears=" + this.jobYears + '}';
   ```
   
7.Update **_equals_** method:
   ```java
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
            Objects.equals(firstName, employee.firstName) &&
            Objects.equals(lastName, employee.lastName) &&
            Objects.equals(description, employee.description) &&
            Objects.equals(jobYears, employee.jobYears);
    }
   ```
8. Update **_hashCode_** method:
    ```java
    public int hashCode() {
		 return Objects.hash(id, firstName, lastName, description, jobYears);
	}
    ```

9. Update the **_app.js Javascript_** file:
```javascript
   class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
						<th>Job Years</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
````
```javascript
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobYears}</td>
			</tr>
		)
	}
}
````
10. Update the **_DatabaseLoader_** class (you can also add new entries):
```java
   	public void run(String... strings) throws Exception { // <4>
    this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", 1));
}
```

To see the changes, open a bash in the basic folder of the app and run the following command `./mvnw spring-boot:run
`
After that, open a browser and navigate to [http://localhost:8080](http://localhost:8080) to see the changes. 
Should look like this:
![image](https://i.postimg.cc/RVZw6hBF/Captura-de-ecr-2024-03-13-234938.png)



**Four commits** in total can be made to the **main branch** to implement the changes:
1. The first commit **adds the unit tests** for the _jobYears_ field, marking the issue #2.
    - `CA1: added Unit Test for jobYears field (constructor, get and set), adapt the current ones, #2` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/392c1c3a630a45c3f144382967698b00c1c6162c)
2. The second commit **implements the new _jobYears_** field to the _Employee_ class, marking the issue #2.
    - `CA1: implemented new jobYears field to Employee (validation, get and set), #2` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/edd89164af92a59f3e3ecc7130d9a85e6a75f9d4)
3. The third commit **updates the _DatabaseLoader_** with the _jobYears_ field, marking the issue #2.
    - `CA1: updated DatabaseLoader with jobYears field, #2` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/2b702902a15d3e5f0e2ed1f84fb03a2dc5b32986)
4. The fourth commit **updates the _app.js_** with the _jobYears_ field and **closes the issue #2**.
    - `CA1: updated app.js with jobYears field, Closes #2` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/6f8c0f7ca9544f5231626ebcaf73779beee534ef)

The next step is to **add a tag** to mark the version of the application after the changes:
```bash
git tag v1.2.0
git push origin v1.2.0
```
To **mark the finish of this part 1** of the assignment is to add a tag. This tag can be accessed here: [**ca1-part1**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/releases/tag/ca1-part1)
```bash
git tag ca1-part1
git push origin ca1-part1
```
---
### Email Part 1: Adding the email field

For this part of the assignment, a **new branch** will be created to add the _email_ field to the application. The name of the new branch will be _email-field_.
1. First, an **issue is created** in the repository to track the changes that will be made.
- #### Issue #4
  - **Tittle:** ```Add new field for Employee - email```
  - **Description:** ```Create a branch named ”email-field” and develop a new feature, named “email,” to add a field that records the e-mail of an employee. Ensure that no null or empty values are allowed. Write unit tests to validate the creation of employees with the “email” attribute.```
2. **Create** a new branch.
```bash
git branch email-field
```
3. **Switch** to the new branch.
```bash
git checkout email-field
```
4. **Push** the new branch to the **remote repository**.
```bash
git push -u origin email-field
```
6. Create **unit tests** for the _email_ field, ensuring that all methods and validations are covered.

```java
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
```

2. **Add _email_** atrribute:
   ```java
   private String email;
   ```
3. Create **validation** method:
   ```java
   private boolean isValidEmail(String email) {
        return (email != null && !email.trim().isEmpty());
   }
   private boolean isValidArguments(String firstName, String lastName, String description, Integer jobYears, String email) {
        return isValidName(firstName) &&
            isValidName(lastName) &&
            isValidDescription(description) &&
            isValidJobYears(jobYears) &&
            isValidEmail(email);
   }
   ```
4. Update **constructor**:
   ```java
    public Employee(String firstName, String lastName, String description, Integer jobYears, String email) throws IllegalArgumentException {
        if (!isValidArguments(firstName, lastName, description, jobYears, email)) {
            throw new IllegalArgumentException("Invalid Employee arguments");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobYears = jobYears;
        this.email = email;
    }
    ```
5. Add the **_getters_** and **_setters_**, including the validations in the _setter_:
```java
   public String getEmail() {
		return email;
   }
   public void setEmail(String email) throws IllegalArgumentException {
        if(!isValidEmail(email)) {
			throw new IllegalArgumentException("Invalid email");
        }
           this.email = email;
 }
```
8. Update **_toString_** method:
```java
return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + ", jobYears=" + this.jobYears + ", email='" + this.email + '\'' + '}';
```
9. Add the new field to the equals method:
```java
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return Objects.equals(id, employee.id) &&
            Objects.equals(firstName, employee.firstName) &&
            Objects.equals(lastName, employee.lastName) &&
            Objects.equals(description, employee.description) &&
            Objects.equals(jobYears, employee.jobYears) &&
            Objects.equals(email, employee.email);
}
```
10. Update **_hashCode_** method:
```java
public int hashCode() {
    return Objects.hash(id, firstName, lastName, description, jobYears, email);
}
```

11. Update the **_app.js Javascript_** file:
```javascript
class EmployeeList extends React.Component{
    render() {
        const employees = this.props.employees.map(employee =>
            <Employee key={employee._links.self.href} employee={employee}/>
        );
        return (
            <table>
                <tbody>
                    <tr>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Description</th>
                        <th>Job Years</th>
                        <th>Email</th>
                    </tr>
                    {employees}
                </tbody>
            </table>
        )
    }
}
```
```javascript
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobYears}</td>
				<td>{this.props.employee.email}</td>
			</tr>
		)
	}
}
```
12. Update the **_DatabaseLoader_** class:
```java
 public void run(String... strings) throws Exception { // <4>
     this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", 2,"frodo.baggins@shire.com"));
 }
```
To see the changes, open a bash in the basic folder of the app and run the following command `./mvnw spring-boot:run
`
After that, open a browser and navigate to [http://localhost:8080](http://localhost:8080) to see the changes.
And should look like this:
![image](https://i.postimg.cc/HkJ5zNj1/Captura-de-ecr-2024-03-13-235000.png)


**Four commits** in total can be made to the **_email-field_ branch** to implement the changes:

1. The first commit **adds the unit tests** for the _email_ field, marking the issue #4.
    - `CA1: added Unit Test for email field (constructor, get and set), adapt the current ones, #4` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/97b3e7f8d8fbb4086a3f1dfa31969ccd105efde9)
2. The second commit **implements the new _email_** field to the _Employee_ class, marking the issue #4.
    - `CA1: implemented new email field to Employee (validation, get and set), #4` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/50b6a5a0c389c4b8eda172a7e908e03a170163a2)
3. The third commit **updates the _DatabaseLoader_** with the _email_ field, marking the issue #4.
    - `CA1: updated DatabaseLoader with email field, #4` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/f294fada9c674b6f5736c7f4649ad138fe95df46)
4. The fourth commit **updates the _app.js_** with the _email_ field and **closes the issue #4**.
    - `CA1: updated app.js with email field, Closes #4` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/1ea84c1762ae159136d12e0e4ae3ed81d57193bb)


13. **Switch** to the **main branch**:
```bash
git checkout main
```
14. **Merge** the email-field branch into the main branch.
```bash
git merge --no-ff email-field
```
* `Merge branch 'email-field` -  [**Can access the merge here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/545608de5e389ddc97645413060f36734e5b541e)
15. **Push** the changes to the remote repository:
```bash
git push origin main
```
15. Add a **new tag** to the commit:
```bash
git tag v1.3.0
git push origin v1.3.0
```
---
### Email Part 2: Fix invalid email

For this part of the assignment, a **new branch** will be created with the name _fix-invalid-email_.
1. First, an **issue is created** in the repository to track the changes that will be made.
- #### Issue #5
   - **Tittle:** ```Fix validation of an email```
   - **Description:** ```Create a branch called fix-invalid-email. The server should only accept Employees with a valid email (e.g., an email must have the ”@” sign). Add unit tests for testing the creation of Employees with that new validation.```
2. **Create** a new branch:
```bash
git branch fix-invalid-email
```
3. **Switch** to the new branch:
```bash
git checkout fix-invalid-email
```
4. **Push** the new branch to the **remote repository**:
```bash
git push -u origin fix-invalid-email
```
6. Create **unit tests** for the new validation method testing the creation of _Employees_ and the _setEmail_.
```java
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
```
7. **Implement the validation** method:
```java
private boolean isValidEmail(String email) {
    return (email != null && !email.trim().isEmpty() && email.matches("^.+@.+.$"));
}
```

**Two commits** in total can be made to the **_fix_invalid_email_ branch**:

1. The first commit **adds the unit tests** to fix invalid _email_ at the _Employee_ class, marking the issue #5.
    - `CA1: added Unit Tests to fix invalid email at Employee, #5` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/024c7f87ea9359eed57594408656b13d4d9a2a19)
2. The second commit **implements** the new validation to fix invalid _emails_ at the _Employee_ class and **closes the issue #5**.
    - `CA1: implemented new validation to fix invalid emails at Employee, Closes #5` -  [**Can access commit here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/1b7fb7d81ecfcdcb04ec6c6554ab420eb17237b9)

8. **Switch** to the **main branch**:
```bash
git checkout main
```
9. **Merge** the fix_invalid_email branch into the main branch:
```bash
git merge --no-ff fix-invalid-email
```
* `Merge branch 'fix-invalid-email'` -  [**Can access the merge here**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/commit/834cfe8c6ecf7a242875fd583f17d145aaf6b2bf)

10. **Push** the changes to the **remote repository**:
```bash
git push
```
Add a **new tag** to the commit:
```bash
git tag v1.3.1
git push origin v1.3.1
```
Add a tag to **mark the finish of this part** of the assignment. This tag can be accessed here: [**ca1-part2**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/releases/tag/ca1-part2)
```bash
git tag ca1-part2
git push origin ca1-part2
```

---
## The timeline of the commits and branches:
The project's development has been meticulously documented through a sequence of commits and branching, beginning with foundational setups and leading up to the implementation of key features and fixes.
The timeline of the commits can be accessed through the following link: [**Timeline of the commits**](https://github.com/pedrodgp/devops-23-24-JPE-PSM-1231852/network). And should look like this:
![timeline](https://i.postimg.cc/v8zWZ9Ck/Captura-de-ecr-2024-03-13-234533.png)
---

## Alternative to Git: Mercurial

While the assignment was meticulously executed using Git, Mercurial stands as a distinguished alternative. This section elucidates how Mercurial could be leveraged to achieve similar objectives, focusing on its comparative advantages and operational syntax.

### Comparative Analysis with the Assignment Workflow

**Initialization and Setup:**
Git's initialization (`git init`) finds its counterpart in Mercurial with `hg init`. The simplicity of starting a new repository remains consistent, setting a foundational stage for version control. In the context of the assignment, initializing the repository:

```bash
# Git
git init
git add README.md
git commit -m "first commit"

# Mercurial
hg init
hg add README.md
hg commit -m "first commit"
```

The initial commit sets the stage, capturing the project's genesis point, much like the first stepping stone laid in the Git workflow.

### **Branching and Feature Implementation:**
This assignment underscores **branching** for feature development, like adding a new field or refining existing code. Git branches (`git branch <name>`) are ephemeral, easily created or deleted. Mercurial's branches, while permanent, offer a stable lineage for features. Transitioning from Git's:

```bash
# Git
git branch new-feature
git checkout new-feature

# Mercurial
hg branch new-feature
hg update new-feature
```

reflects a paradigm shift in branch management, emphasizing long-term traceability in Mercurial.

### **Committing Changes:**
The essence of capturing progress through **commits** is pivotal in the assignment. Mercurial's `hg commit` mirrors Git's commit mechanism, serving as a snapshot of the evolving project landscape. Implementing a new feature or a bug fix culminates in a commit, encapsulating the incremental advancements:

```bash
# Git
git add .
git commit -m "Implement new feature"

# Mercurial
hg add .
hg commit -m "Implement new feature"
```

### **Merging and Tagging:**
**Merging** branches after successful feature integration or bug resolution is a critical step. Mercurial's `hg merge` aligns with Git's merge process, weaving disparate development threads into a cohesive narrative. **Tagging** in Mercurial (`hg tag`) also parallels Git's tagging, marking significant milestones like version releases or completed features:

```bash
# Git
git merge new-feature
git tag -a v1.0 -m "Version 1.0"

# Mercurial
hg merge new-feature
hg tag -m "Version 1.0" v1.0
```

### Leveraging Mercurial: Design Considerations

Adopting Mercurial involves embracing its unique version control philosophy while aligning with the project's goals. The transition requires thoughtful consideration of its permanent branching model, direct commit approach (bypassing the staging area), and its streamlined command set. Adapting the workflow to Mercurial not only involves syntactical changes but also an appreciation of its nuanced differences from Git, ensuring a smooth and efficient version control experience that aligns with the structured objectives of the assignment.


### Conclusion: Git vs. Mercurial
While both Git and Mercurial are powerful tools with their distinct advantages, the choice between them often boils down to personal preference, project requirements, and the specific workflow desired. While Mercurial's popularity has diminished since its debut in 2005, it continues to be employed by notable organizations such as Facebook and Mozilla. The primary distinction between Git and Mercurial lies in their approach to branching. Mercurial is often praised for its user-friendliness, but its branching mechanism, characterized by its permanence, can be perplexing for some. Unlike Git's flexible, lightweight branches that can be created, deleted, or switched between effortlessly, Mercurial's branches are fixed and irremovable, posing a risk of repository clutter if not managed meticulously. This necessitates extra vigilance to ensure changes are committed to the correct branch, preventing any unintended repository complications.

In conclusion, for the objectives outlined in the assignment, Mercurial provides a capable and user-friendly alternative to Git, with sufficient features to manage version control effectively, handle branching and merging, and facilitate collaboration among team members

---------


### Useful Mercurial Commands for Version Control

Mercurial is a distributed version control system that offers a collection of commands similar to other systems like Git but with its own set of functionalities and workflow optimizations. Here is an overview of some useful Mercurial commands that are integral to managing repositories, tracking changes, and collaborating in software development:

- **hg clone**: Clone a repository to create a local copy on your computer. It's akin to checking out code for review or modification.
  ```bash
  hg clone <repository-URL>
  ```

- **hg init**: Initialize a new Mercurial repository in the current directory. This sets up the necessary .hg directory with configuration files.
  ```bash
  hg init
  ```

- **hg add**: Add new files or changes to files in your working directory to the staging area.
  ```bash
  hg add <filename>
  ```

- **hg commit**: Commit your changes to the local repository. Every commit requires a commit message to describe the change.
  ```bash
  hg commit -m "Your commit message"
  ```

- **hg status**: Check the current status of the repository, including changed, added, or removed files.
  ```bash
  hg status
  ```

- **hg log**: View the revision history of the repository, showing changesets, descriptions, timestamps, and authors.
  ```bash
  hg log
  ```

- **hg diff**: Show differences between revisions, changeset parents, working directory, etc., highlighting the changes made.
  ```bash
  hg diff
  ```

- **hg update**: Update the working directory to a specific revision or the latest changes in another branch.
  ```bash
  hg update <revision>
  ```

- **hg merge**: Merge another branch into your current working branch to combine different development histories.
  ```bash
  hg merge <branch-name>
  ```

- **hg revert**: Revert changes in the working directory to the state of a specific revision. This can discard unwanted edits.
  ```bash
  hg revert --all --rev <revision>
  ```

- **hg tag**: Tag a specific changeset for future reference, often used to mark release points like "1.0" or "2.5".
  ```bash
  hg tag <tag-name>
  ```

- **hg pull**: Pull changes from a remote repository to your local repository, updating it with changes from collaborators.
  ```bash
  hg pull
  ```

- **hg push**: Push changes from your local repository to a remote repository, sharing your changes with others.
  ```bash
  hg push
  ```

- **hg branch**: List, create, or switch branches. Branches are a way to diverge from the main line of development and work independently.
  ```bash
  hg branch <new-branch>
  ```

These commands are fundamental to the workflow in Mercurial, facilitating tasks from simple code updates to complex merges and release management. Understanding and utilizing these commands can significantly enhance the efficiency and clarity of development processes in projects managed with Mercurial.


# Conclusion

This assignment marked a significant step in my journey through the world of version control systems, providing a comprehensive exploration of Git and introducing Mercurial as a noteworthy alternative. The integration and enhancement of a basic Spring application were not just exercises in coding but pivotal experiences in understanding the critical role of version control in individual software development endeavors.

Navigating through the intricacies of repository management, from initial commits to advanced branching and merging, has solidified my grasp of these essential tools. The assignment underscored how version control systems are indispensable for organizing, securing, and evolving complex software projects, even in a solitary development environment. It highlighted the balance between maintaining a rigorous structure in code management and the flexibility to adapt and innovate.

The exploration of Mercurial broadened my perspective, offering insights into the diversity of version control tools available and their unique advantages. This comparative analysis between Git and Mercurial enriched my understanding, guiding me in selecting the appropriate tools that align with the project's needs and personal workflow preferences.

Reflecting on this endeavor, I recognize the substantial growth in my technical capabilities and a deepened appreciation for the meticulous process of software development. The skills honed during this assignment have prepared me to tackle upcoming challenges with a robust set of tools, a methodical approach to code management, and a proactive attitude towards continuous learning.

Looking ahead, the proficiency gained in employing these version control systems equips me with the confidence to manage complex software solutions independently, ensuring quality, efficiency, and innovation in my future software development ventures.

