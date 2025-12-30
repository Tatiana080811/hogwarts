package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FacultyControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private FacultyController facultyController;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private FacultyService facultyService;

    private Faculty validFaculty;
    private Long validFacultyId = 1L;

    @BeforeEach
    void setUp() {
        validFaculty = new Faculty();
        validFaculty.setId(validFacultyId);
        validFaculty.setName("Mathematics");
        validFaculty.setColor("#ffffff");
    }

    @Test
    void contextLoads() throws Exception {
        Assertions.assertNotNull(facultyController);
    }

    @Test
    public void testGetFaculty() throws Exception {
        assertNotNull(
                this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class),
                "Полученный объект должен быть не null"
        );
    }
    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("kfkvfkl");

        Assertions
                .assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/user", faculty, String.class));

    }

    @Test
    void testDeleteFaculty() {
        when(facultyService.deleteFaculty(anyLong())).thenReturn(true);
        ResponseEntity<Void> response = restTemplate.exchange(
                "/faculty/" + validFacultyId,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testEditFaculty() {
        Faculty updatedFaculty = new Faculty();
        updatedFaculty.setId(validFacultyId);
        updatedFaculty.setName("Computer Science");
        updatedFaculty.setColor("#cccccc");
        when(facultyService.editFaculty(anyLong(), any())).thenReturn(updatedFaculty);

        HttpEntity<Faculty> request = new HttpEntity<>(updatedFaculty);

        ResponseEntity<Faculty> response = restTemplate.exchange(
                "/faculty/update/" + validFacultyId,
                HttpMethod.PUT,
                request,
                Faculty.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFaculty.getName(), response.getBody().getName());
        assertEquals(updatedFaculty.getColor(), response.getBody().getColor());
    }





}

