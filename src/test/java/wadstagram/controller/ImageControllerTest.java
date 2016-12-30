package wadstagram.controller;

import java.util.UUID;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import wadstagram.repository.ImageRepository;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wadstagram.domain.Image;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("development")
public class ImageControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    @Autowired
    private ImageRepository imageRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).apply(springSecurity()).build();
    }

    @Before
    public void logIn() throws Exception {
        mockMvc.perform(formLogin("/login").user("admin").password("admin"));
    }

    @Test
    public void canAddNewImage() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        MvcResult res = mockMvc.perform(fileUpload("/image").file(multipartFile).param("description", description))
                .andReturn();

        for (Image image : imageRepository.findAll()) {
            res = mockMvc.perform(get("/image/{id}", image.getId()))
                    .andExpect(model().attributeExists("image"))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            Image i = (Image) res.getModelAndView().getModel().get("image");
            assertEquals(image.getDescription(), i.getDescription());
        }
    }

}
