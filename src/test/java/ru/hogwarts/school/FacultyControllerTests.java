package ru.hogwarts.school;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.matchers.JUnitMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ru.hogwarts.school.controller.FacultyController;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FacultyService facultyService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testGetFaculty() throws Exception {
        Long id = 1L;
        Faculty expectedFaculty = new Faculty("Mathematics", id, "#ffffff");
        when(facultyService.findFaculty(id)).thenReturn(expectedFaculty);

        mockMvc.perform(get("/faculty/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(expectedFaculty.getName()));
    }

    @Test
    public void testPostFaculty() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setName("kfkvfkl");

        mockMvc.perform(post("/faculty")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(faculty)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testEditFaculty_SuccessfullyEdited() throws Exception {
        Faculty originalFaculty = new Faculty();
        originalFaculty.setId(1L);
        originalFaculty.setName("Original Name");
        originalFaculty.setColor("#ff0000");

        Faculty updateData = new Faculty();
        updateData.setId(originalFaculty.getId());
        updateData.setName("Updated Name");
        updateData.setColor("#00ff00");

        when(facultyService.editFaculty(anyLong(), any())).thenReturn(updateData);

        mockMvc.perform(put("/faculty/{id}", originalFaculty.getId())
                        .header("Content-Type", "application/json")
                        .content(objectMapper.writeValueAsString(updateData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(originalFaculty.getId().intValue())))
                .andExpect(jsonPath("$.name", is("Updated Name")))
                .andExpect(jsonPath("$.color", is("#00ff00")));
    }
    @Test
    void shouldSuccessfullyDeleteFaculty() throws Exception {
        long facultyId = 1L;

        when(facultyService.deleteFaculty(facultyId)).thenReturn(true);
        this.mockMvc.perform(delete("/faculty/" + facultyId))
                .andExpect(status().isOk());
    }

    @Test
    void searchFacultiesByQuerySuccess() throws Exception {

        List<Faculty> faculties = List.of(
                new Faculty("Философский", 1L , "красный"));

        given(facultyService.searchFaculties("философ"))
                .willReturn(faculties);

        this.mockMvc.perform(get("/faculty/search").param("query", "философ"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Философский")))
                .andExpect(jsonPath("$[0].color", is("красный")));
    }


    }