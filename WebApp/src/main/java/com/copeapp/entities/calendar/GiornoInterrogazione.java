package com.copeapp.entities.calendar;

import com.copeapp.entities.common.*;
import lombok.NonNull;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiornoInterrogazione extends Event {

    @NonNull
    @ManyToMany
    private List<Student> studentiInterrogati;

    @NonNull
    @ManyToMany
    private Subject subject;

    @ManyToOne //TODO inverse join
    private Interrogazione interrogazione;

    @NonNull
    private Boolean isOpen;

    public GiornoInterrogazione(User insertUser, Date insertDate, String type, Date eventDate, List<Student> studentiInterrogati, Subject subject){
        super(insertUser, insertDate, type, eventDate);
        this.studentiInterrogati = studentiInterrogati;
        this.subject = subject;
        this.isOpen = false;
        setType("interrogazione");
        List<Role> targetRole = new ArrayList<>();
        targetRole.add(new Role("studente", "Studente"));
        targetRole.add(new Role("prof", "Professore"));
        setDestinationRoles(targetRole);
        List<Classe> targetClasse = new ArrayList<>();
        targetClasse.add(studentiInterrogati.get(0).getClasse());
        setDestinationClasses(targetClasse);
    }

    public GiornoInterrogazione(User insertUser, Date insertDate, String type, Date eventDate, List<Student> studentiInterrogati, Subject subject, Boolean isOpen) {
        super(insertUser, insertDate, type, eventDate);
        this.isOpen = isOpen;
        this.studentiInterrogati = studentiInterrogati;
        this.subject = subject;
        this.isOpen = false;
        setType("interrogazione");
        List<Role> targetRole = new ArrayList<>();
        targetRole.add(new Role("studente", "Studente"));
        targetRole.add(new Role("prof", "Professore"));
        setDestinationRoles(targetRole);
        List<Classe> targetClasse = new ArrayList<>();
        targetClasse.add(studentiInterrogati.get(0).getClasse());
        setDestinationClasses(targetClasse);
    }
    }
