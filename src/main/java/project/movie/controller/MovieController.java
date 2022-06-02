package project.movie.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import project.movie.model.Actor;
import project.movie.model.Movie;
import project.movie.service.ActorService;


@Controller
public class MovieController {

	@Autowired
	ActorService service;
	
	@Autowired
	MessageSource messageSource;
	
	
	@RequestMapping(value = "/movie/new", method = RequestMethod.GET)
	public ModelAndView displayMovieForm() {
		ModelAndView mv = new ModelAndView("movie-entry");
		mv.addObject("movie", new Movie());

		return mv;
	}
	
	@RequestMapping(value = "/movie/add", method = RequestMethod.POST)
	public ModelAndView processMovieForm(@Valid @ModelAttribute Movie movie, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie", movie);
		
		if (result.hasErrors())
			mv.setViewName("movie-entry");
		else {
			mv.setViewName("movie-list");
			service.createMovie(movie);
			mv.addObject("movies", service.findAllMovies());
		}
		return mv;
	}
	
	@RequestMapping(value = "/list-movies", method = RequestMethod.GET)
	public ModelAndView listMovie() {
		ModelAndView mv = new ModelAndView("movie-list");
		mv.addObject("movies", service.findAllMovies());
		return mv;
	}
	
	@RequestMapping(value = { "/movie/view/{id}", "/movie/{id}" }, method = RequestMethod.GET)
	public ModelAndView viewMovie(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("view-info");
		Movie movie = service.findMovieById(id);
		mv.addObject("movie", movie);
		mv.addObject("actor", new Actor(movie));
		return mv;
	}

	@RequestMapping(value = "/movie/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteMovie(@PathVariable long id) {
		ModelAndView mv = new ModelAndView("movie-list");
		service.deleteMovie(id);
		mv.addObject("movies", service.findAllMovies());
		return mv;
	}

	@RequestMapping(value = "/movie/actor/add", method = RequestMethod.POST)
	public ModelAndView addActorToMovie(@Valid @ModelAttribute Actor actor, BindingResult result) {
		ModelAndView mv = new ModelAndView("view-info");
		Movie movie = service.findMovieById(actor.getMovie().getId());
		
		if(result.hasErrors() == false) {
			service.createActor(actor);
			mv.addObject("actor", new Actor(movie));
		}
		else {
			mv.addObject("actor", actor);
		}
		
		mv.addObject("movie", movie);
		
		return mv;
	}

	@RequestMapping(value = "/movie/actor/delete/{pid}/{phid}", method = RequestMethod.GET)
	public ModelAndView deleteActor(@PathVariable long pid, @PathVariable long phid) {
		ModelAndView mv = new ModelAndView("view-info");
		service.deleteActor(phid);
		Movie movie = service.findMovieById(pid);
		mv.addObject("movie", movie);
		mv.addObject("actor", new Actor(movie));		
		
		return mv;
	}
	
	@RequestMapping(value = "/movie/update/{id}", method = RequestMethod.GET)
	public ModelAndView updateMovie(@PathVariable (value = "id") long id) {
		ModelAndView mv = new ModelAndView("update-movie");
		Movie movie = service.findMovieById(id);
		mv.addObject("movie", movie);
		return mv;
	}
	
	@RequestMapping(value = "/movie/updateNew", method = RequestMethod.POST)
	public ModelAndView updateNewMovie(@Valid @ModelAttribute Movie movie, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("movie", movie);
		
		if (result.hasErrors())
			mv.setViewName("update-movie");
		else {
			mv.setViewName("movie-list");
			service.updateMovie(movie);
			mv.addObject("movies", service.findAllMovies());
		}
		return mv;
	}

}
