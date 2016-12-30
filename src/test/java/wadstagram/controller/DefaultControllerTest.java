package wadstagram.controller;

import static org.junit.Assert.*;
import javax.net.ssl.SSLEngineResult;
import javax.servlet.Filter;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ActiveProfiles("development")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultControllerTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;

    /*    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).addFilter(springSecurityFilterChain).build();
    }*/
    @Test
    public void testLoginRequirement() {
        goTo("http://localhost:" + port);
        assertEquals("wadstagram - login", title());
    }

    @Test
    public void testLoginPage() {
        goTo("http://localhost:" + port + "/login");
        assertEquals("wadstagram - login", title());
    }

    @Test
    public void testErrorPage() {
        goTo("http://localhost:" + port + "/oops");
        assertEquals("wadstagram - oops", title());
    }

    @Test
    public void testRegisterPage() {
        goTo("http://localhost:" + port + "/register");
        assertEquals("wadstagram - register", title());
    }

    @Test
    public void testLoggingIn() {
        goTo("http://localhost:" + port);
        fill(find("#username")).with("user");
        fill(find("#password")).with("user");
        submit(find("#loginform"));
        assertEquals("wadstagram - index", title());
    }

    @Test
    public void testRegistering() {
        goTo("http://localhost:" + port + "/register");
        fill(find("#username")).with("tester");
        fill(find("#password")).with("tester");
        submit(find("#registerform"));
        assertEquals("wadstagram - login", title());
    }
}
