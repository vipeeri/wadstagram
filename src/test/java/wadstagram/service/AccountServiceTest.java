package wadstagram.service;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("testing")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void testFindByUserName() {
        assertNotNull(accountService.getUserByName("admin"));
    }

    @Test
    public void testFindById() {
        assertNotNull(accountService.getUserById(1L));
    }

    @Test
    public void testCreateAccount() {
        accountService.createAccount("test", "test", "USER");
        assertNotNull(accountService.getUserByName("test"));
    }
}
