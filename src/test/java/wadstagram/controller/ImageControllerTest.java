package wadstagram.controller;

import java.util.UUID;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
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
@ActiveProfiles("testing")
@WithUserDetails("admin")
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
    
    @After
    public void cleanUp() {
        this.imageRepository.deleteAll();
    }

    @Test
    public void canAddNewImage() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        mockMvc.perform(fileUpload("/image").file(multipartFile).param("description", description)).andReturn().getResponse().getErrorMessage();
        assertEquals(1, imageRepository.findAll().size());
    }

    @Test
    public void imagePageCreatedReturnedAndWorks() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("file", imageName, "image/png", content.getBytes());
        mockMvc.perform(fileUpload("/image").file(multipartFile).param("description", description));

        for (Image image : imageRepository.findAll()) {
            MvcResult res = mockMvc.perform(get("/image/{id}", image.getId()))
                    .andExpect(model().attributeExists("image"))
                    .andExpect(status().is2xxSuccessful())
                    .andReturn();
            Image i = (Image) res.getModelAndView().getModel().get("image");
            assertEquals(image.getDescription(), i.getDescription());
        }
    }

    @Test
    public void cantAddInvalidType() throws Exception {
        String description = UUID.randomUUID().toString().substring(0, 6);
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("file", imageName, "invalid/file", content.getBytes());
        mockMvc.perform(fileUpload("/image").file(multipartFile).param("description", description)).andReturn();
        assertEquals(0, imageRepository.findAll().size());
    }

    @Test
    public void emptyDescriptionNotAllowed() throws Exception {
        String imageName = UUID.randomUUID().toString().substring(0, 6);
        String content = UUID.randomUUID().toString().substring(0, 6);
        MockMultipartFile multipartFile = new MockMultipartFile("image", imageName, "image/png", content.getBytes());
        mockMvc.perform(fileUpload("/image").file(multipartFile).param("description", "")).andReturn();
        assertEquals(0, imageRepository.findAll().size());
    }
}
