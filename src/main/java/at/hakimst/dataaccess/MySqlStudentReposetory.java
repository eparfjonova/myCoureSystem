package at.hakimst.dataaccess;

import domain.Course;
import domain.CourseType;
import domain.Student;
import util.Assert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlStudentReposetory /*implements MyStudentReposetory*/{


    //Verbindung zur DB herstellen
    private Connection con;
    //Constructor
    public MySqlStudentReposetory() throws SQLException, ClassNotFoundException {
        System.out.println("Connection: " + con);
        //this.con = MySqlDatabaseConnection.getConnection("jdbc:mysql://127.0.0.1:3306/imstkurssystem","root","");
        this.con = MysqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/imstkurssystem",  "root", "");
        System.out.println("Connection123123123: " + con);

    }
    //Verbindung zur DB herstellen Ende 18:37






    public List<Student> getAll() {
        String sql = "SELECT * FROM `student`";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Course> courseList = new ArrayList<>();

            while (resultSet.next()) {
                /*courseList.add(new Student(
                                resultSet.getLong("id"),
                                resultSet.getString("vn"),
                                resultSet.getString("nn"),
                                resultSet.getDate("geburtsdatum")
                        )
                );
            } return studentList;*/
                return null;
        /*} catch (SQLException e) {
            throw new DatabaseException("Database error occured!");*/
        }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }

/*
    @Override
    public Optional<Course> getByID(Long id) {
        /*Assert.notNull(id);
        if(countStudentInDbWithId(id) == 0) {
            return Optional.empty();
        }else{
            try {
                String sql = "SELECT * FROM `student` WHERE `id` = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                resultSet.next();
                /*Course course = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("nn"),
                        resultSet.getString("vn"),
                        resultSet.getDate("geburtsdatum"))
                );
                return Optional.of(student);

            }catch(SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
        }*/
            return null;
    }

    private int countStudentInDbWithId(Long id) {
        try{
            String countSql = "SELECT COUNT(*) FROM `student` WHERE `id` = ?";
            PreparedStatement preparedStatementCount = con.prepareStatement(countSql);
            preparedStatementCount.setLong(1,id);
            ResultSet resultSetCount = preparedStatementCount.executeQuery();
            resultSetCount.next();
            int studentCount = resultSetCount.getInt(1);
            return studentCount;
        }catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
        }
    }


    public Optional<Student> insert(Student entity) {
        /*Assert.notNull(entity);

        try{
            String sql = "INSERT INTO `student`(`vn`, `nn`, `geburtsdatum`) VALUES (?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            /*preparedStatement.setString(1,entity.getVn());
            preparedStatement.setString(2,entity.getNn());
            preparedStatement.setDate(3,entity.getGeburtsdatum());

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
        }*/
        return null;
    }





    public Optional<Student> update(Student entity) {

        /*Assert.notNull(entity);

        String sql = "UPDATE `sutdent` SET `vn`=?,`nn`=?,`geburtsdatum`=? WHERE `student`.`id` = ?";

        if(countStudentInDbWithId(entity.getId()) == 0) {
            return Optional.empty();
        } else {
            try{
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                /*preparedStatement.setString(1,entity.getVn());
                preparedStatement.setString(2,entity.getNn());
                preparedStatement.setDate(3,entity.getGeburtsdatum());

                preparedStatement.setLong(4,entity.getId());

                int affectedRows = preparedStatement.executeUpdate();
                if(affectedRows == 0) {
                    return Optional.empty();
                }else{
                    return this.getByID(entity.getId());
                }

            }catch(SQLException sqlException){
                throw new DatabaseException(sqlException.getMessage());
            }
        }*/
        return null;
    }





    public void deleatById(Long id) {

        Assert.notNull(id);
        String sql = "DELEATE feom `student` WHERE `id` = ?";
        if(countStudentInDbWithId(id) == 1) {

            try {
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }catch (SQLException sqlException) {
                throw new DatabaseException(sqlException.getMessage());
            }
        }

    }



    public List<Student> findAllCouresesByVnOrNn(String serchText) {

        try{

            String sql = "SELECT * FROM `student` WHERE LOWER(`nn`) LIKE LOWER(?) OR LOWER(`vn`) LIKE LOWER(?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,"%"+serchText+"%");
            preparedStatement.setString(2,"%"+serchText+"%");

            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Course> studentList = new ArrayList<>();

            /*while(resultSet.next()) {
                studentList.add(new Course(
                                resultSet.getLong("id"),
                                resultSet.getString("vn"),
                                resultSet.getString("nn"),
                                resultSet.getDate("geburtsdatum")
                        )
                );
            }
            return studentList;*/
            return null;
        } catch(SQLException sqlException){
            throw new DatabaseException(sqlException.getMessage());
        }
    }


    public List<Student> findAllRunningCourses() {

        /*String sql = "SELECT * FROM `student` WHERE NOW()<`enddate`";

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
        }*/
        return null;
    }
    }

