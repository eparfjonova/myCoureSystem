package at.hakimst.dataaccess;

import domain.Course;
import domain.CourseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlCourseReposetory implements MyCourseReposetory{


    //Verbindung zur DB herstellen
    private Connection con;
    //Constructor
    public MySqlCourseReposetory() throws SQLException, ClassNotFoundException {
        System.out.println("Connection: " + con);
        //this.con = MySqlDatabaseConnection.getConnection("jdbc:mysql://127.0.0.1:3306/imstkurssystem","root","");
        this.con = MySqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/imstkurssystem",  "root", "");
        System.out.println("Connection123123123: " + con);

    }
    //Verbindung zur DB herstellen Ende 18:37





    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM `courses`";
        System.out.println("Connection2: " + con);
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Course> courseList = new ArrayList<>();

            while (resultSet.next()) {
                courseList.add(new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype"))
                        )
                );
            } return courseList;
        } catch (SQLException e) {
            throw new DatabaseException("Database error occured!");
        }
    }


    @Override
    public Optional<Course> getByID(Long id) {
        return Optional.empty();
    }




    @Override
    public Optional<Course> insert(Course entity) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> update(Course entity) {
        return Optional.empty();
    }

    @Override
    public void deleatById(Long id) {

    }




    @Override
    public List<Course> findAllCouresesByName(String name) {
        return List.of();
    }

    @Override
    public List<Course> findAllCouresesByDescription(String description) {
        return List.of();
    }

    @Override
    public List<Course> findAllCouresesByDescriptionOrName(String serchText) {
        return List.of();
    }

    @Override
    public List<Course> findAllCouresesByCourseType(CourseType courseType) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByStartDate(Date startDate) {
        return List.of();
    }

    @Override
    public List<Course> findAllRunningCourses() {
        return List.of();
    }

}
