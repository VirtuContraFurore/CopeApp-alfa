package com.copeapp.utilities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.copeapp.entities.common.Classe;
import com.copeapp.entities.common.Role;
import com.copeapp.entities.common.Student;
import com.copeapp.entities.common.Subject;
import com.copeapp.entities.common.Teacher;
import com.copeapp.entities.common.User;

public class PopulateDatabase {
	
	public static void doPopulate() {
		
		EntityManager entityManager = EntityManagerFactoryGlobal.getInstance().getEmfactory().createEntityManager();
		entityManager.getTransaction().begin();
		try {
			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role("studente", "Studente"));
			roles.add(new Role("prof", "Professore"));
			roles.add(new Role("moderatore", "Moderatore"));
			roles.add(new Role("admin", "Amministratore"));
			roles.add(new Role("redattore", "Redattore"));
			roles.add(new Role("rappresentante", "Rappresentante"));
			
			for(Role r : roles) entityManager.persist(r);
			
			//TODO rifare il populate database
			
			Student student0 = new Student("FabioTex", "VincioGay", "Fabio", "Tessaro", roles, false);
			student0.setMail("fabio.tessaro.porta@gmail.com");
			student0.setImageUrl("");
			student0.setWallpaper("default");
			Student student1 = new Student("Gallo", "VincioGay", "Gianluca", "Galletti", roles, false);
			student1.setMail("gianlucagalletti@ymail.it");
			student1.setImageUrl("");
			student1.setWallpaper("default");
			Student student2 = new Student("Cerammerda", "VincioGay", "Luca", "Ceragioli", roles, false);
			student2.setMail("cerammerda@gioli.it");
			student2.setImageUrl("");
			student2.setWallpaper("default");
			Teacher teacher0 = new Teacher("Kyra69", "VincioGay", "Claudio", "Unguendoli", roles, false);
			teacher0.setMail("ungummerda@guendoli.it");
			teacher0.setImageUrl("");
			teacher0.setWallpaper("default");
			Teacher teacher1 = new Teacher("GabryFenoc", "VincioGay", "Gabriella", "Fenocchio", roles, false);
			teacher1.setMail("gabrifenoc@occhio.it");
			teacher1.setImageUrl("");
			teacher1.setWallpaper("default");
			
			ArrayList<Classe> classes = new ArrayList<Classe>();
			classes.add(new Classe(5, "F", "Scientifico applicato", teacher1));
			classes.add(new Classe(5, "C", "Scientifico applicato", teacher0));
			
			teacher0.setClassi(classes);
			teacher1.setClassi(classes);
			
			for (Classe c : classes) {
				entityManager.persist(c);
			}
			
			entityManager.persist(student0);
			entityManager.persist(student1);
			entityManager.persist(student2);
			entityManager.persist(teacher0);
			entityManager.persist(student1);
			
			ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			ArrayList<Subject> subjects = new ArrayList<Subject>();
			
			teachers.add(teacher1);
			subjects.add(new Subject("Italiano", "FF0000"));
			subjects.get(0).setTeachers(teachers);
			teachers.get(0).setSubjects(subjects);
			
			teachers.clear();
			subjects.clear();
			
			teachers.add(teacher0);
			subjects.add(new Subject("Informatica", "FFFF00"));
			subjects.get(0).setTeachers(teachers);
			teachers.get(0).setSubjects(subjects);
			
			teachers.clear();
			subjects.clear();
			
			for (Subject s : subjects) {
				entityManager.persist(s);
			}
		} finally {
			entityManager.getTransaction().commit();
			entityManager.close();
		}
	}

}
