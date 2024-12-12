package at.hakimst.dataaccess;

import at.hakimst.dataaccess.DatabaseException;
import at.hakimst.dataaccess.MyStudentReposetory;
import at.hakimst.dataaccess.MysqlDatabaseConnection;
import domain.Student;
import util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlStudentReposetory implements MyStudentReposetory {

    private Connection con;

    public MySqlStudentReposetory() throws SQLException, ClassNotFoundException {
        this.con = MysqlDatabaseConnection.getConnection("jdbc:mysql://localhost:3306/imstkurssystem", "root", "root");
    }



    @Override
    public Optional<Student> getByID(Long id) {
        Assert.notNull(id);

        //if (countStudentsInDbWithId(id) == 0) {
        //  return Optional.empty();
        //} else {
        try {
            String sql = "SELECT * FROM `students` WHERE `id` = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Prüfen, ob ein Ergebnis vorhanden ist
            resultSet.next();
            Student student = new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("vn"),
                    resultSet.getString("nn"),
                    resultSet.getDate("birthdate")
            );
            return Optional.of(student);
        } catch (SQLException sqlException) {
            throw new DatabaseException(sqlException.getMessage());
            //}
        }
    }





    public List<Student> findStudentByVn(String vn) {
        // String sql = "SELECT * FROM `students` WHERE LOWER(`vn`) LIKE LOWER(?)";
        //PreparedStatement preparedStatement = con.prepareStatement(sql);
        //preparedStatement.setString(1, "%"+vn+"%");

        try {
            String sql = "SELECT * FROM Students WHERE vn = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, vn);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Student> studentList = new ArrayList<>();
            resultSet.next();

            studentList.add(new Student(
                            resultSet.getLong("id"),
                            resultSet.getString("vn"),
                            resultSet.getString("nn"),
                            resultSet.getDate("birthdate")
                    )
            );

            return studentList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findByVn(String vn) {
        return findStudentByVn(vn);
    }

    @Override
    public List<Student> searchStudentByBirthdate(java.sql.Date birthdate) {
        Assert.notNull(birthdate);

        String sql = "SELECT * FROM students WHERE birthdate = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setDate(1, birthdate);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("vn"),
                        resultSet.getString("nn"),
                        resultSet.getDate("birthdate")
                ));
            }
            return students;
        } catch (SQLException e) {
            throw new DatabaseException("Fehler beim Abrufen von Studenten nach Geburtsdatum: " + e.getMessage());
        }
    }


    @Override
    public Optional<Student> insert(Student entity) {
        return Optional.empty();
    }


    @Override
    public List<Student> getAll() {
        return List.of();
    }

    @Override
    public Optional<Student> update(Student entity) {
        return Optional.empty();
    }

    @Override
    public void deleatById(Long id) {

    }
}
