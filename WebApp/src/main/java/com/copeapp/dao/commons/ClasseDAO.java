package com.copeapp.dao.commons;

import com.copeapp.entities.common.Classe;
import com.copeapp.utilities.EntityManagerGlobal;

import javax.persistence.TypedQuery;
import java.util.List;

public class ClasseDAO {

    public static List<Classe> selectClassByIndirizzo(String indirizzo){
        TypedQuery<Classe> query = EntityManagerGlobal.getEntityManager()
                .createQuery("FROM Classe WHERE (indirizzo = :indirizzo)", Classe.class);
        query.setParameter("indirizzo", indirizzo);
        return query.getResultList();
    }

    public static List<Classe> selectClassByIndirizzoNumber(String indirizzo, Integer number){
        TypedQuery<Classe> query = EntityManagerGlobal.getEntityManager()
                .createQuery("FROM Classe WHERE (indirizzo = :indirizzo AND number = :number)", Classe.class);
        query.setParameter("number", number);
        query.setParameter("indirizzo", indirizzo);
        return query.getResultList();
    }

    public static List<Classe> selectClassByNumber(Integer number){
        TypedQuery<Classe> query = EntityManagerGlobal.getEntityManager()
                .createQuery("FROM Classe WHERE number = :number", Classe.class);
        query.setParameter("number", number);
        return query.getResultList();
    }
}
