package com.copeapp.entities.calendar;

import com.copeapp.entities.common.*;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name="interrogationdays")
public class InterrogationDay extends Event {

    @NonNull
    @ManyToMany
    private List<Student> studentsInterrogated;

    @NonNull
    @ManyToMany
    private Subject subject;

    @ManyToOne //TODO inverse join
    private Interrogation interrogation;

    @NonNull
    private Boolean isOpen;

    public InterrogationDay(User insertUser, Date insertDate, String type, Date eventDate, List<Student> studentsInterrogated, Subject subject){
        super(insertUser, insertDate, type, eventDate);
        this.studentsInterrogated = studentsInterrogated;
        this.subject = subject;
        this.isOpen = false;
        setType("interrogazione");
        List<Role> targetRole = new ArrayList<>();
        targetRole.add(new Role("studente", "Studente"));
        targetRole.add(new Role("prof", "Professore"));
        setDestinationRoles(targetRole);
        List<Classe> targetClasse = new ArrayList<>();
        targetClasse.add(studentsInterrogated.get(0).getClasse());
        setDestinationClasses(targetClasse);
    }

    public InterrogationDay(User insertUser, Date insertDate, String type, Date eventDate, List<Student> studentsInterrogated, Subject subject, Boolean isOpen) {
        super(insertUser, insertDate, type, eventDate);
        this.isOpen = isOpen;
        this.studentsInterrogated = studentsInterrogated;
        this.subject = subject;
        this.isOpen = false;
        setType("interrogazione");
        List<Role> targetRole = new ArrayList<>();
        targetRole.add(new Role("studente", "Studente"));
        targetRole.add(new Role("prof", "Professore"));
        setDestinationRoles(targetRole);
        List<Classe> targetClasse = new ArrayList<>();
        targetClasse.add(studentsInterrogated.get(0).getClasse());
        setDestinationClasses(targetClasse);
    }
    }
