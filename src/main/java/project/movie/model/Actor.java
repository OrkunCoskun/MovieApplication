package project.movie.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tbl_actor")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "act_fname")
	private String firstName;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "act_lname")
	private String lastName;

	@NotNull
	@Size(min = 1, max = 30)
	@Column(name = "act_role")
	private String role;

	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;

	public Actor() {

	}

	public Actor(Movie movie) {
		this.movie = movie;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
