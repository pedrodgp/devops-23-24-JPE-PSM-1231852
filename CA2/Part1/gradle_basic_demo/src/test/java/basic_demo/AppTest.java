package basic_demo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for App class.
 */
public class AppTest {

    /**
     * Test of getGreeting method, of class App.
     * If the app has a greeting, the test will pass.
     * If the app does not have a greeting, the test will fail.
     */
    @Test
    public void testAppHasAGreeting(){
        App classUnderTest= new App();
        assertNotNull("appshould have agreeting",classUnderTest.getGreeting());
    }
}