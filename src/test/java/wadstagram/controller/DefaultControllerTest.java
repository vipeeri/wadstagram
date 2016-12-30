package wadstagram.controller;

import static org.junit.Assert.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("testing")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DefaultControllerTest extends FluentTest {

    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @LocalServerPort
    private Integer port;

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
        fill(find("#username")).with("tester");
        fill(find("#password")).with("tester");
        submit(find("#loginform"));
        assertEquals("wadstagram - index", title());
    }
}
