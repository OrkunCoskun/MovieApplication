package project.movie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import project.movie.model.Movie;


@Repository
public class MovieRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Movie> findAll() {
		return entityManager.createQuery("from Movie", Movie.class).getResultList();
	}
	
	public Movie findById(long id) {
		return entityManager.find(Movie.class, id);
	}

	public void create(Movie movie) {
		entityManager.persist(movie);
	}

	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Movie.class, id));
	}
	
	public void update(Movie movie) {
		entityManager.merge(movie);
	}
}
