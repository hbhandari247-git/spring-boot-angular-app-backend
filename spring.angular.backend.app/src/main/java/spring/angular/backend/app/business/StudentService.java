package spring.angular.backend.app.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.angular.backend.app.entity.SchoolEntity;
import spring.angular.backend.app.entity.StudentEntity;
import spring.angular.backend.app.exception.customexceptions.ResourceNotFoundException;
import spring.angular.backend.app.model.School;
import spring.angular.backend.app.model.Student;
import spring.angular.backend.app.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public static final Logger LOGGER = Logger.getLogger("StudentService.class");

	/**
	 * createStudent
	 * 
	 * @param student
	 * @return Student
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Student createStudent(Student student) {

		try {
			StudentEntity studentEntity = populateStudentEntity(student);
			studentEntity = studentRepository.save(studentEntity);
			if (studentEntity != null && studentEntity.getId() != 0) {
				student.setId(studentEntity.getId());
			}
			if (studentEntity != null && studentEntity.getSchool() != null) {
				School school = new School();
				school.setId(studentEntity.getSchool().getId());
				school.setSchoolName(studentEntity.getSchool().getSchoolName());
				student.setSchool(school);
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new ResourceNotFoundException("Error::: Student object is not valid");
		}
		return student;
	}

	/**
	 * getStudentById
	 * 
	 * @param id
	 * @return Student
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Student getStudentById(Long id) {
		Student student = null;
		try {
			StudentEntity studentEntity = studentRepository.getById(id);
			student = populateStudent(studentEntity);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new ResourceNotFoundException("Error::: BAD REQUEST");
		}
		return student;
	}

	/**
	 * updateStudentById
	 * 
	 * @param id
	 * @return String
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String updateStudentById(Student student) {
		String message = "Not Updated";
		try {
			StudentEntity studentEntity = studentRepository.getById(student.getId());
			studentEntity = studentRepository.save(studentEntity);
			message = "UPDATED";
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new ResourceNotFoundException("Error::: Student object is not valid");
		}
		return message;
	}

	/**
	 * deleteStudentById
	 * 
	 * @param id
	 * @return String
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String deleteStudentById(Long id) {
		String message = "Not Deleted";
		try {
			studentRepository.deleteById(id);
			message = "Deleted :: " + id;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new ResourceNotFoundException("Error::: Student delete API failed");
		}
		return message;
	}

	/**
	 * populateStudentEntity
	 * 
	 * @param student
	 * @return StudentEntity
	 */
	public StudentEntity populateStudentEntity(Student student) {
		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setDob(student.getDob());
		studentEntity.setEmailId(student.getEmailId());
		studentEntity.setFirstName(student.getFirstName());
		studentEntity.setLastName(student.getLastName());
		studentEntity.setMiddleName(student.getMiddleName());
		if (student.getSchool() != null) {
			SchoolEntity schoolEntity = new SchoolEntity();
			schoolEntity.setId(student.getSchool().getId());
			schoolEntity.setSchoolName(student.getSchool().getSchoolName());
			studentEntity.setSchool(schoolEntity);
		}
		studentEntity.setSection(student.getSection());
		studentEntity.setStandard(student.getStandard());
		studentEntity.setId(student.getId());
		return studentEntity;
	}

	/**
	 * populateStudent
	 * 
	 * @param studentEntity
	 * @return Student
	 */
	private Student populateStudent(StudentEntity studentEntity) {
		Student student = new Student();
		School school = new School();
		student.setDob(studentEntity.getDob());
		student.setEmailId(studentEntity.getEmailId());
		student.setFirstName(studentEntity.getFirstName());
		student.setLastName(studentEntity.getLastName());
		student.setMiddleName(studentEntity.getMiddleName());
		if (studentEntity.getSchool() != null) {
			school.setId(student.getSchool().getId());
			school.setSchoolName(student.getSchool().getSchoolName());
			student.setSchool(school);
		}
		student.setSection(studentEntity.getSection());
		student.setStandard(studentEntity.getStandard());
		student.setId(studentEntity.getId());
		return student;
	}

	/**
	 * findAllStudents
	 * 
	 * @return List<Student>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Student> findAllStudents() {
		List<Student> studentsLi = new ArrayList<Student>();
		try {
			List<StudentEntity> studentEntitiesLi = studentRepository.findAll();
			if (null != studentEntitiesLi && !studentEntitiesLi.isEmpty()) {
				for (StudentEntity studentEntity : studentEntitiesLi) {
					Student student = populateStudent(studentEntity);
					studentsLi.add(student);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new ResourceNotFoundException("Error::: Student GET API failed - BAD request");
		}
		return studentsLi;

	}

}
