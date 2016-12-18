//
package wadstagram.domain;

import javafx.application.Application;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

public class AccountTest {

    Account account = new Account("test", "test", "USER");

    public AccountTest() {

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void getUsernameTest() {

        assertEquals(account.getUsername(), "test");

    }

    @Test
    public void getPasswordTest() {
        assertEquals(account.getPassword(), "test");
    }

    @Test
    public void getStatusTest() {
        assertEquals(account.getStatus(), "USER");
    }

}
