package com.copeapp.entities.calendar;

import com.copeapp.entities.common.Classe;
import com.copeapp.entities.common.Student;
import com.copeapp.entities.common.Subject;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class Interrogazione {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="eventsGenerator")
    @SequenceGenerator(name="eventsGenerator", sequenceName="events_sequence", allocationSize = 1, initialValue = 50)
    private Integer interrogazioneId;

    @NonNull
    @ManyToMany
    private Classe classe;

    @NonNull
    @ManyToMany
    private Subject subject;

    @NonNull
    @ManyToMany // aggiungere allo user le sue interrogazioni?
    private List<Student> studentiInterrogati;

    @NonNull
    @ManyToOne //TODO inverse join
    private List<GiornoInterrogazione> dateInterrogazioni;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date inizioInterrogazioni;

    @NonNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date fineInterrogazioni;

    @NonNull
    private Integer giorniPrimaDellaChiusura;

}
