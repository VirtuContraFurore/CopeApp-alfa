package com.copeapp.dao.commons;

import com.copeapp.entities.common.Classe;
import com.copeapp.entities.common.Student;
import com.copeapp.entities.common.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {

    public static List<Student> selectAllStudent(Teacher teacher){
        List<Student> allStudent = new ArrayList<>();
        for (Classe c : teacher.getClassi()){
            allStudent.addAll(c.getStudents());
        }
        return allStudent;
    }
}
