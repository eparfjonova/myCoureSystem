package at.hakimst.dataaccess;

import domain.Course;
import domain.CourseType;

import java.sql.Date;
import java.util.List;

public interface MyCourseReposetory extends BaseReposetory<Course, Long> {
    //ich setze fpr T und I => Course und Long ein
    //eigentliches DAO das von BaseReposetory erbt
    //CRUD ist schon von BaseRepository vorgegeben

    List<Course> findAllCouresesByName(String name);
    List<Course> findAllCouresesByDescription(String description);
    List<Course> findAllCouresesByDescriptionOrName(String serchText);
    List<Course> findAllCouresesByCourseType(CourseType courseType);
    List<Course> findAllCoursesByStartDate(Date startDate);
    List<Course> findAllRunningCourses();
}
