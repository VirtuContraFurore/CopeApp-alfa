package com.copeapp.dao.commons;

import com.copeapp.entities.common.Student;
import com.copeapp.entities.common.Teacher;

import java.util.List;

public class StudentDAO {

    public static List<Teacher> selectTeachers(Student student){
        return student.getClasse().getTeachers();
    }
}
