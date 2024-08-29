package com.cy.pj.jdbc.dao;

import com.cy.pj.jdbc.pojo.Student;
import com.cy.pj.jdbc.util.DBUtil;
import com.cy.pj.jdbc.util.JDBCUtil_V1;
import com.cy.pj.jdbc.util.JDBCUtil_V2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // 根据id查找学生
    public Student findStudentById(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Student student = null;
        try {
            connection = JDBCUtil_V2.getConnection();
            String sql = "select * from stu where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                student = new Student();
                student.setId(id);
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getString("gender"));
                student.setCourse(resultSet.getString("course"));
                student.setScore(resultSet.getDouble("score"));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil_V2.close(connection, preparedStatement, resultSet);
        }
        return student;
    }

    // 根据id修改学生数据
    public int updateStudentById(Student student){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil_V2.getConnection();
            String sql = "update stu set name = ?, gender = ?, course = ?, score = ? where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getGender());
            preparedStatement.setString(3,student.getCourse());
            preparedStatement.setDouble(4,student.getScore());
            preparedStatement.setInt(5,student.getId());
            return preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil_V2.close(connection, preparedStatement, null);
        }
    }

    // 根据id删除学生信息
    public int deleteStudentById(Integer id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "delete from stu where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(connection, preparedStatement, null);
        }
    }

    // 创建学生信息
    public int saveStudent(Student student){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil_V1.getConnection();
            String sql = "insert into stu values(?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,student.getId());
            preparedStatement.setString(2,student.getName());
            preparedStatement.setString(3,student.getGender());
            preparedStatement.setString(4,student.getCourse());
            preparedStatement.setDouble(5,student.getScore());
            return preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil_V1.close(connection, preparedStatement, null);
        }
    }

    // 查找所有学生信息
    public List<Student> findAll(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        List<Student> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hc_db?severTimezone=Asia/Shanghai","root","1234");
            String sql = "select * from stu";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setGender(resultSet.getString("gender"));
                student.setCourse(resultSet.getString("course"));
                student.setScore(resultSet.getDouble("score"));
                list.add(student);
            }
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return null;
    }
}
