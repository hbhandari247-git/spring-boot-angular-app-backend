package spring.angular.backend.app.business;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import spring.angular.backend.app.entity.SchoolEntity;
import spring.angular.backend.app.exception.customexceptions.InternalServerException;
import spring.angular.backend.app.model.School;
import spring.angular.backend.app.repository.SchoolRepository;

@Service
public class SchoolService {

	@Autowired
	private SchoolRepository schoolRepository;

	public static final Logger LOGGER = Logger.getLogger("SchoolService.class");

	/**
	 * createSchool
	 * 
	 * @param school
	 * @return School
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public School createSchool(School school) {
		try {
			SchoolEntity schoolEntity = new SchoolEntity();
			if (null != school)
				schoolEntity.setSchoolName(school.getSchoolName());
			schoolEntity = schoolRepository.save(schoolEntity);
			if (null != schoolEntity) {
				school.setId(schoolEntity.getId());
				school.setSchoolName(schoolEntity.getSchoolName());
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new InternalServerException("Error::: School object is not valid");
		}
		return school;

	}

	/**
	 * getSchoolById
	 * 
	 * @param schoolId
	 * @return School
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public School getSchoolById(Long schoolId) {
		School school = new School();
		try {
			SchoolEntity schoolEntity = schoolRepository.getById(schoolId);
			if (null != schoolEntity) {
				school.setId(schoolEntity.getId());
				school.setSchoolName(schoolEntity.getSchoolName());
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new InternalServerException("Error::: Illegal Request");
		}
		return school;
	}

	/**
	 * findAllSchool
	 * 
	 * @return List<School>
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<School> findAllSchool() {
		List<School> schoolsLi = new ArrayList<School>();
		try {
			List<SchoolEntity> schoolEntitiesLi = schoolRepository.findAll();
			if (null != schoolEntitiesLi && !schoolEntitiesLi.isEmpty()) {
				for (SchoolEntity schoolEntity : schoolEntitiesLi) {
					School school = new School();
					school.setId(schoolEntity.getId());
					school.setSchoolName(schoolEntity.getSchoolName());
					schoolsLi.add(school);
				}
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new InternalServerException("Error::: Bad GET request");
		}
		return schoolsLi;

	}

	/**
	 * updateSchoolById
	 * 
	 * @param school
	 * @return String
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String updateSchoolById(School school) {
		String message = "Not Updated";
		try {
			SchoolEntity schoolEntity = schoolRepository.getById(school.getId());
			if (null != schoolEntity) {
				schoolEntity.setSchoolName(school.getSchoolName());
				schoolRepository.save(schoolEntity);
				message = "Updated";
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new InternalServerException("Error::: update school is not valid");
		}
		return message;
	}

	/**
	 * deleteSchoolById
	 * 
	 * @param schoolId
	 * @return String
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String deleteSchoolById(Long schoolId) {
		String message = "Not Deleted";
		try {
			schoolRepository.deleteById(schoolId);
			message = "Deleted!";
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new InternalServerException("Error in School delete API");
		}
		return message;
	}

}
