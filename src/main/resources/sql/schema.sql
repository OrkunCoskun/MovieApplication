create table tbl_movie(id identity primary key, 
					movie_title char(100), 
					movie_year integer,
					movie_time integer,
					movie_lang char(50),
					movie_dt_rel date,
					movie_rel_country char(50),
					movie_director char(50),
					movie_genre char(50));

create table tbl_actor(id identity primary key, act_fname char(20),act_lname char(20), act_role char(50),
					movie_id bigint,
				    foreign key(movie_id) references tbl_movie(id));