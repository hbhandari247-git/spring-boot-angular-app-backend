package spring.angular.backend.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.angular.backend.app.business.StudentService;
import spring.angular.backend.app.model.Student;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student createdStudent = studentService.createStudent(student);
		if (createdStudent != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getStudentById/{studentId}")
	public ResponseEntity<Student> getSchoolById(@PathVariable("studentId") Long studentId) {
		Student existingStudent = studentService.getStudentById(studentId);
		if (existingStudent != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(existingStudent);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/findAllStudents")
	public ResponseEntity<List<Student>> findAllSchools() {
		List<Student> existingStudents = studentService.findAllStudents();
		if (existingStudents != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(existingStudents);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateSchoolById(@RequestBody Student student) {
		String message = studentService.updateStudentById(student);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/deleteStudent/{studentId}")
	public ResponseEntity<String> updateSchoolById(@PathVariable("studentId") Long studentId) {
		String message = studentService.deleteStudentById(studentId);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
