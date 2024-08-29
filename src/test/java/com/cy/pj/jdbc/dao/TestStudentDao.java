package com.cy.pj.jdbc.dao;

import com.cy.pj.jdbc.pojo.Student;
import org.junit.Test;

import java.util.List;

public class TestStudentDao {
    private StudentDao studentDao = new StudentDao();

    @Test
    public void testFindAll(){
        List<Student> list= studentDao.findAll();
        for (Student student : list) {
            System.out.println(student);
        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
    }

    @Test
    public void testSaveStudent(){
        Student student = new Student();
        student.setId(1005);
        student.setName("张三丰");
        student.setGender("男");
        student.setCourse("java");
        student.setScore(99.0);
        int row = studentDao.saveStudent(student);
        System.out.println("受影响的行数为：" + row);
        testFindAll();
    }

    @Test
    public void testFindStudentById(){
        Student student = studentDao.findStudentById(1005);
        if(student == null){
            System.out.println("查无此生");
        } else {
            System.out.println("id：" + student.getId());
            System.out.println("name：" + student.getName());
            System.out.println("gender：" + student.getGender());
            System.out.println("course：" + student.getCourse());
            System.out.println("score：" + student.getScore());
        }
    }

    @Test
    public void testUpdateStudentById(){
        Student student = new Student();
        student.setId(1005);
        student.setName("张三");
        student.setGender("女");
        student.setCourse("数学");
        student.setScore(90.0);
        int row = studentDao.updateStudentById(student);
        System.out.println("受影响行数为：" + row);
        testFindAll();
    }

    @Test
    public void testDeleteStudentById(){
        int row = studentDao.deleteStudentById(1004);
        System.out.println("受影响行数为：" + row);
        testFindAll();
    }
}