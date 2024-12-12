package at.hakimst.dataaccess;

import domain.Course;
import domain.CourseType;
import util.Assert;

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
        this.con = MysqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/imstkurssystem",  "root", "");
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
        Assert.notNull(id);
        if(countCoursesInDbWithId(id) == 0) {
            return Optional.empty();
        }else{
            try {
                String sql = "SELECT * FROM `courses` WHERE `id` = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                Course course = new Course(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("hours"),
                        resultSet.getDate("begindate"),
                        resultSet.getDate("enddate"),
                        CourseType.valueOf(resultSet.getString("coursetype"))
                );
                return Optional.of(course);

            }catch(SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }
    }

    private int countCoursesInDbWithId(Long id) {
        try{
        String countSql = "SELECT COUNT(*) FROM `courses` WHERE `id` = ?";
        PreparedStatement preparedStatementCount = con.prepareStatement(countSql);
        preparedStatementCount.setLong(1,id);
        ResultSet resultSetCount = preparedStatementCount.executeQuery();
        resultSetCount.next();
        int courseCount = resultSetCount.getInt(1);
        return courseCount;
        }catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public Optional<Course> insert(Course entity) {
        Assert.notNull(entity);

        try{
            String sql = "INSERT INTO `courses`(`name`, `description`, `hours`, `begindate`, `enddate`, `coursetype`) VALUES (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,entity.getName());
            preparedStatement.setString(2,entity.getDescription());
            preparedStatement.setInt(3,entity.getHours());
            preparedStatement.setDate(4,entity.getBeginDate());
            preparedStatement.setDate(5,entity.getEndDate());
            preparedStatement.setString(6,entity.getCourseType().toString());

            int affectedRows = preparedStatement.executeUpdate();

            if(affectedRows == 0) {
                return Optional.empty();
            }

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if(generatedKeys.next()) {
                //wir holen uns die generierte ID die beim inserten erstllt wurde
                return this.getByID(generatedKeys.getLong(1));
            }else{
                return Optional.empty();
            }

        }catch(SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
    }




    @Override
    public Optional<Course> update(Course entity) {

        Assert.notNull(entity);

        String sql = "UPDATE `courses` SET `name`=?,`description`=?,`hours`=?,`begindate`=?,`enddate`=?,`coursetype`=? WHERE `course`.`id` = ?";

        if(countCoursesInDbWithId(entity.getId()) == 0) {
            return Optional.empty();
        } else {
            try{
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1,entity.getName());
                preparedStatement.setString(2,entity.getDescription());
                preparedStatement.setInt(3,entity.getHours());
                preparedStatement.setDate(4,entity.getBeginDate());
                preparedStatement.setDate(5,entity.getEndDate());
                preparedStatement.setString(6,entity.getCourseType().toString());

                preparedStatement.setLong(7,entity.getId());

                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    return Optional.empty();
                }else{
                    return this.getByID(entity.getId());
                }

            }catch(SQLException sqlException){
                throw new DatabaseException(sqlException.getMessage());
            }
        }
    }




    @Override
    public void deleatById(Long id) {

        Assert.notNull(id);
        String sql = "DELETE FROM `courses` WHERE `id` = ?";
        if(countCoursesInDbWithId(id) == 1) {

            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }
    }


    @Override
    public List<Course> findAllCouresesByDescriptionOrName(String serchText) {

        try{

            String sql = "SELECT * FROM `courses` WHERE LOWER(`description`) LIKE LOWER(?) OR LOWER(`name`) LIKE LOWER(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,"%"+serchText+"%");
            preparedStatement.setString(2,"%"+serchText+"%");

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Course> courseList = new ArrayList<>();

            while(resultSet.next()) {
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
            }
            return courseList;
        } catch(SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
    }

    @Override
    public List<Course> findAllRunningCourses() {

        String sql = "SELECT * FROM `courses` WHERE NOW()<`enddate`";

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
            }
            return courseList;
        }catch (SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
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
    public List<Course> findAllCouresesByCourseType(CourseType courseType) {
        return List.of();
    }

    @Override
    public List<Course> findAllCoursesByStartDate(Date startDate) {
        return List.of();
    }
}
