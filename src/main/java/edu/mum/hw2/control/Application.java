package edu.mum.hw2.control;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;





import edu.mum.hw2.domain.Actor;
import edu.mum.hw2.domain.Movie;
import edu.mum.hw2.domain.category;

public class Application {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {

		addMovies();
		printMoviesReport();
		emf.close();
	}

	private static void printMoviesReport() {
		
		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = em.getTransaction();
			
			
			
			@SuppressWarnings("unchecked")
			List<String> movies = 
				em.createQuery(" select m.name from Movie m join m.actors a  where a.name = 'Actor1' ").getResultList();
			System.out.println("-----size-----"+movies.size());
			for (String m : movies) {
				System.out.println("================OUTPUT==========");
				System.out.println("Movie name=" + m);
				System.out.println("================================");
			}
			
	
	

	}

	private static void addMovies() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

		// TODO your code
			Movie movie = new Movie();
			movie.setName("8 Miles");
			movie.setComment("8 Miles");
			movie.setRating("5 Star");
			movie.setCategory(category.drama);
			Actor actor1 = new Actor("5", "person1", "Actor1");
			Actor actor2 = new Actor("5", "person2", "Actor2");
			
			List<Actor> actors = new ArrayList<Actor>();
			actors.add(actor1);
			actors.add(actor2);
			movie.setActors(actors);
			
			Path p = FileSystems.getDefault().getPath("","src","main","resources", "1.jpg");
			byte[] fileData = Files.readAllBytes(p);
			movie.setCover(fileData);
			
			em.persist(actor1);
			em.persist(actor2);
			em.persist(movie);

			tx.commit();
		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive())) tx.rollback();
			System.err.println("ERROR:"+e.getMessage());
		} finally {
			if ((em != null) && (em.isOpen())) em.close();
		}
	}

}
