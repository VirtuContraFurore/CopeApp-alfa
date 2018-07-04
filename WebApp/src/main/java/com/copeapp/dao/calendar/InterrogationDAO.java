package com.copeapp.dao.calendar;

import com.copeapp.dao.commons.UserDAO;
import com.copeapp.entities.calendar.Interrogation;
import com.copeapp.entities.calendar.InterrogationDay;
import com.copeapp.entities.common.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InterrogationDAO {

    public static List<Student> selectInterrogatedStudentsByDay(Interrogation interrogation, Date date){
        List<Student> listStudent = new ArrayList<>();
        for (InterrogationDay d : interrogation.getDaysOfInterrogation()){
            if (d.getEventDate().equals(date)) { //TODO == oppure .equals()
                return d.getStudentsInterrogated();
            }
        }
        return listStudent;
    }

    public static boolean isValidStudent(Interrogation interrogation, List<Student> student){ //TODO gli studenti che sono già stati interrogati in un giorno precedente
        return !(student.retainAll(interrogation.getStudentsInterrogated())); //TODO dovrebbe andare
    }

    public static boolean canCreateInterrogation(User user, Classe classe, Subject subject){
        List<Role> roles = user.getRoles();
        Role studente = new Role("studente", "Studente");
        Role prof = new Role("prof", "Professore");
        if (roles.contains(studente)){
            Student s = (Student) user;
            return (s.getClasse().equals(classe) && UserDAO.isRappresentanteByClass(s,classe));
        } else if (roles.contains(prof)){
            Teacher t = (Teacher) user;
            return (t.getClassi().contains(classe) && t.getSubjects().contains(subject));
        } else {
            //TODO solo return false oppure errore?
            return false;
        }
    }
}
