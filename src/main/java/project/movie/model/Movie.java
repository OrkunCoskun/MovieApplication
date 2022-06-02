package project.movie.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tbl_movie")
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(name = "movie_title")
	private String movieTitle;

	@NotNull
	@Min(1895)
	@Column(name = "movie_year")
	private Integer movieYear;

	@NotNull
	@Column(name = "movie_time")
	private Integer movieTime;

	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "movie_lang")
	private String movieLang;

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "movie_dt_rel")
	private LocalDate movieDtRel;

	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "movie_rel_country")
	private String movieRelCountry;
	
	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "movie_director")
	private String movieDirector;
	
	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "movie_genre")
	private String movieGenre;
	
	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	@OneToMany(mappedBy = "movie")
	@OrderBy("id")
	private Set<Actor> actors = new HashSet<Actor>();

	public long getId() {
		return id;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public Integer getMovieYear() {
		return movieYear;
	}

	public void setMovieYear(Integer movieYear) {
		this.movieYear = movieYear;
	}

	public Integer getMovieTime() {
		return movieTime;
	}

	public void setMovieTime(Integer movieTime) {
		this.movieTime = movieTime;
	}

	public String getMovieLang() {
		return movieLang;
	}

	public void setMovieLang(String movieLang) {
		this.movieLang = movieLang;
	}

	public LocalDate getMovieDtRel() {
		return movieDtRel;
	}

	public void setMovieDtRel(LocalDate movieDtRel) {
		this.movieDtRel = movieDtRel;
	}

	public String getMovieRelCountry() {
		return movieRelCountry;
	}

	public void setMovieRelCountry(String movieRelCountry) {
		this.movieRelCountry = movieRelCountry;
	}
}
