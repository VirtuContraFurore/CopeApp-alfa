package com.copeapp.dao.calendar;

import com.copeapp.entities.calendar.Interrogation;
import com.copeapp.entities.calendar.InterrogationDay;
import com.copeapp.entities.common.*;
import com.copeapp.exception.CalendarException;
import com.copeapp.utilities.HttpStatusUtility;
import com.copeapp.utilities.MessageUtility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InterrogationDayDAO {

    public static boolean isDayOpen(InterrogationDay interrogationDay){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE,interrogationDay.getInterrogation().getDaysBeforeClosing());
        return (interrogationDay.getEventDate().before(c.getTime()));
    }

    public static boolean isValidInterrogationDay(Interrogation interrogation, Date interrogationDate){
        Date startDate = interrogation.getStartInterrogation();
        Date endDate = interrogation.getEndInterrogation();
        return (startDate.compareTo(interrogationDate)>=0 && endDate.compareTo(interrogationDate)<=0);
    }

    public static InterrogationDay createInterrogationDay(User insertUser, Date eventDate, List<Student> studentsInterrogated, Subject subject,
                                                          Interrogation interrogation, Integer minInterrogated, Integer maxInterrogated){
        InterrogationDay interrogationDay = new InterrogationDay();
        interrogationDay.setInsertUser(insertUser);
        interrogationDay.setSubject(subject);
        interrogationDay.setInterrogation(interrogation);
        interrogationDay.setIsOpen(false);
        interrogationDay.setInsertDate(new Date());
        interrogationDay.setType("interrogationday");

        List<Role> targetRole = new ArrayList<>();
        targetRole.add(new Role("studente", "Studente"));
        targetRole.add(new Role("prof", "Professore"));
        interrogationDay.setDestinationRoles(targetRole);

        List<Classe> targetClasse = new ArrayList<>();
        targetClasse.add(studentsInterrogated.get(0).getClasse());
        interrogationDay.setDestinationClasses(targetClasse);

        if (isValidInterrogationDay(interrogation, eventDate)){
            interrogationDay.setEventDate(eventDate);
        } else {
            throw new CalendarException(HttpStatusUtility.NOT_ACCEPTABLE, MessageUtility.INVALID_DATE);
        }
        if (minInterrogated>0 && maxInterrogated>=minInterrogated){
            interrogationDay.setMinInterrogated(minInterrogated);
            interrogationDay.setMaxInterrogated(maxInterrogated);
        } else {
            throw new CalendarException(HttpStatusUtility.NOT_ACCEPTABLE, MessageUtility.INVALID_NUMBER);
        }
        if (maxInterrogated>=studentsInterrogated.size() && InterrogationDAO.isValidStudent(interrogation, studentsInterrogated)){
            interrogationDay.setStudentsInterrogated(studentsInterrogated);
        } else {
            throw new CalendarException(HttpStatusUtility.NOT_ACCEPTABLE, MessageUtility.INVALID_STUDENS);
        }
        return interrogationDay;
    }
}
