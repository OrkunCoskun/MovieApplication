package project.movie.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import project.movie.model.Actor;

@Repository
public class ActorRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Actor> findAll() {
		return entityManager.createQuery("from Actor", Actor.class).getResultList();
	}
	
	public Actor findById(long id) {
		return entityManager.find(Actor.class, id);
	}
	
	public List<Actor> findByMovieId(long id) {
		return entityManager.createQuery("from Actor where movie.id = :id", Actor.class).setParameter("id", id).getResultList();
	}
	
	public List<Actor> findByLastName(String lastName) {
		return entityManager.createQuery("from Actor where lastName = :last", Actor.class).setParameter("last", lastName).getResultList();
	}

	public void create(Actor actor) {
		entityManager.persist(actor);
	}

	public void delete(long id) {
		entityManager.remove(entityManager.getReference(Actor.class, id));
	}
	
	public void deleteByMovieId(long id) {
		entityManager.createQuery("delete from Actor where movie.id = :id").setParameter("id", id).executeUpdate();
	}

}
