package com.copeapp.dao.calendar;

import com.copeapp.entities.calendar.Interrogation;
import com.copeapp.entities.calendar.InterrogationDay;
import com.copeapp.entities.common.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterrogationDAO {

    public static List<Student> selectInterrogatedStudentByDay(Interrogation interrogation, Date date){
        List<Student> listStudent = new ArrayList<>();
        for (InterrogationDay d : interrogation.getDaysOfInterrogation()){
            if (d.getEventDate() == date) { //TODO va il .equals() ?
                return d.getStudentsInterrogated();
            }
        }
        return listStudent;
    }
}
