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

import spring.angular.backend.app.business.SchoolService;
import spring.angular.backend.app.model.School;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

	@Autowired
	private SchoolService schoolService;

	@PostMapping("/create")
	public ResponseEntity<School> createSchool(@RequestBody School school) {
		School createdSchool = schoolService.createSchool(school);
		if (createdSchool != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdSchool);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/getSchoolById/{schoolId}")
	public ResponseEntity<School> getSchoolById(@PathVariable("schoolId") Long schoolId) {
		School existingSchool = schoolService.getSchoolById(schoolId);
		if (existingSchool != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(existingSchool);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@GetMapping("/findAllSchools")
	public ResponseEntity<List<School>> findAllSchools() {
		List<School> existingSchools = schoolService.findAllSchool();
		if (existingSchools != null) {
			return ResponseEntity.status(HttpStatus.FOUND).body(existingSchools);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateSchoolById(@RequestBody School school) {
		String message = schoolService.updateSchoolById(school);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@DeleteMapping("/deleteSchool/{schoolId}")
	public ResponseEntity<String> updateSchoolById(@PathVariable("schoolId") Long schoolId) {
		String message = schoolService.deleteSchoolById(schoolId);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
