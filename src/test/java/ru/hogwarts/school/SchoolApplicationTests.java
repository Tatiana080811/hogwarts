package ru.hogwarts.school;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import ru.hogwarts.school.controller.StudentController;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.StudentRepository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void prepareData() {
		Student existingStudent = new Student();
		existingStudent.setId(1L);
		existingStudent.setName("Джон Доу");
		existingStudent.setAge(25);

		studentRepository.save(existingStudent);
	}


	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(studentController);
	}

	@Test
	public void testGetStudent() throws Exception {
		assertNotNull(
				this.restTemplate.getForObject("http://localhost:" + port + "/user", String.class),
				"Полученный объект должен быть не null"
		);
	}
	@Test
	public void testPostStudent() throws Exception {
		Student student = new Student();
		student.setId(1L);
		student.setName("Гермиона");

		Assertions
				.assertNotNull(this.restTemplate.postForObject("http://localhost:" + port + "/user", student, String.class));

	}

	@Test
	public void shouldCreateNewStudent() {
		Student newStudent = new Student();
		newStudent.setName("Иван");
		newStudent.setAge(20);

		ResponseEntity<Student> response = restTemplate.postForEntity(
				"http://localhost:" + port + "/student/create",
				newStudent,
				Student.class
		);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	@Test
	public void shouldReturnExistingStudent() {
		long existingId = 1L;

		ResponseEntity<Student> response = restTemplate.getForEntity(
				"http://localhost:" + port + "/student/" + existingId,
				Student.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	@Test
	public void shouldHandleNonExistentStudent() {
		long nonexistentId = 999L;

		ResponseEntity<Student> response = restTemplate.getForEntity(
				"http://localhost:" + port + "/student/" + nonexistentId,
				Student.class
		);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	@Test
	public void shouldDeleteExistingStudent() {
		long existingId = 1L;
		ResponseEntity<Void> response = restTemplate.exchange(
				"http://localhost:" + port + "/student/" + existingId,
				HttpMethod.DELETE,
				null,
				Void.class
		);

		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}