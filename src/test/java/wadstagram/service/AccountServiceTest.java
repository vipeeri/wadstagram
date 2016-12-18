package wadstagram.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import wadstagram.domain.Account;

import static org.assertj.core.api.Assertions.*;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import wadstagram.repository.AccountRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService as;

    public AccountServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        as.createAccount("test", "test", "USER");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testFindByUserNameAndId() {

        assertEquals(as.getUserByName("test"), as.getUserById(1L));
    }

}
