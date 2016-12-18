package wadstagram.service;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wadstagram.repository.AccountRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() {
        
    }
    
    @Test
    public void testFindByUserName() {
        
    }

}
