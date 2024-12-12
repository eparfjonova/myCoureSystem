package at.hakimst.dataaccess;

import domain.Student;

import java.util.List;

/*package at.hakimst.dataaccess;

import at.hakimst.dataaccess.BaseReposetory;
import domain.Course;
import domain.CourseType;
import domain.Student;

import java.sql.Date;
import java.util.List;

public interface MyStudentReposetory extends BaseReposetory<Course, Long> {
    //ich setze fpr T und I => Course und Long ein
    //eigentliches DAO das von BaseReposetory erbt
    //CRUD ist schon von BaseRepository vorgegeben

    /*List<Student> findAllStudentsByName(String vn);
    List<Student> findAllStudentsByDescription(String nn);
    List<Student> findAllStudentsByDescriptionOrName(String serchText);
}*/




import java.util.List;

public interface MyStudentReposetory extends BaseReposetory <Student, Long>{
    List<Student> findByVn(String vn);
    List<Student> searchStudentByBirthdate(java.sql.Date birthdate);




}
