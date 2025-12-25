package ru.hogwarts.school;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    private Student existingStudent;
    @BeforeEach
    void setUp() {
        existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setName("Гермиона");
        existingStudent.setAge(20);
    }

    @Test
    public void shouldCreateNewStudent() throws Exception {
        Student newStudent = new Student();
        newStudent.setName("Иван");
        newStudent.setAge(20);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/student/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newStudent)));

        result.andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnExistingStudent() throws Exception {
        when(studentService.findStudent(anyLong()))
                .thenReturn(existingStudent);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/student/1"));

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Гермиона"))
                .andExpect(jsonPath("$.age").value(20));
    }

    @Test
    public void shouldHandleNonExistentStudent() throws Exception {
        when(studentService.findStudent(anyLong()))
                .thenReturn(null);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/student/999"));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void shouldDeleteExistingStudent() throws Exception {
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"));

        result.andExpect(status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
