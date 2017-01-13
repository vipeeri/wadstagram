package wadstagram.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() {
        this.account = new Account("test", "test", "USER");
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
