package project.movie.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.movie.dao.ActorRepository;
import project.movie.dao.MovieRepository;
import project.movie.model.Actor;
import project.movie.model.Movie;

@Service
@Transactional
public class ActorService{
	
	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	MovieRepository movieRepository;
	
	
	public List<Movie> findAllMovies() {
		return movieRepository.findAll();
	}
	
	
	public Movie findMovieById(long id) {
		return movieRepository.findById(id);
	}
	
	public void createMovie(Movie movie) {
		movieRepository.create(movie);
	}
	
	public void createActor(Actor actor) {
		actorRepository.create(actor);
	}
	
	public void deleteMovie(long id) {
		actorRepository.deleteByMovieId(id);
		movieRepository.delete(id);
	}
	
	public void deleteActor(long id) {
		actorRepository.delete(id);
	}
	
	public void updateMovie(Movie movie) {
		movieRepository.update(movie);
	}
	
}
