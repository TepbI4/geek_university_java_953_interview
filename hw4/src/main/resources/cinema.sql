CREATE SCHEMA `cinema`;

CREATE TABLE `cinema`.`movies` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `title` VARCHAR(50) NOT NULL,
                                   `duration` INT NOT NULL,
                                   PRIMARY KEY (`id`));


CREATE TABLE `cinema`.`sessions` (
                                     `id` INT NOT NULL AUTO_INCREMENT,
                                     `start_date` DATETIME NOT NULL,
                                     `movie_id` INT NOT NULL,
                                     PRIMARY KEY (`id`),
                                     UNIQUE INDEX `start_date_UNIQUE` (`start_date` ASC) VISIBLE,
                                     CONSTRAINT `movie_id_fk`
                                         FOREIGN KEY (`id`)
                                             REFERENCES `cinema`.`movies` (`id`)
                                             ON DELETE CASCADE
                                             ON UPDATE NO ACTION);


CREATE TABLE `cinema`.`tickets` (
                                    `id` INT NOT NULL AUTO_INCREMENT,
                                    `number` INT NOT NULL,
                                    `session_id` INT NOT NULL,
                                    `cost` DECIMAL(7,2) NOT NULL,
                                    PRIMARY KEY (`id`),
                                    CONSTRAINT `session_id_fk`
                                        FOREIGN KEY (`id`)
                                            REFERENCES `cinema`.`sessions` (`id`)
                                            ON DELETE CASCADE
                                            ON UPDATE NO ACTION);


INSERT INTO movies VALUES(1, "The Matrix Resurrections", 148);
INSERT INTO movies VALUES(2, "Spider-Man: No Way Home", 148);
INSERT INTO movies VALUES(3, "King Richard", 144);

INSERT INTO sessions VALUES(1, "2021-12-16 10:00", 1);
INSERT INTO sessions VALUES(2, "2021-12-16 12:00", 2);
INSERT INTO sessions VALUES(3, "2021-12-16 14:00", 1);
INSERT INTO sessions VALUES(4, "2021-12-16 17:00", 2);
INSERT INTO sessions VALUES(5, "2021-12-16 20:00", 3);

INSERT INTO tickets VALUES(1, 1, 1, 100);
INSERT INTO tickets VALUES(2, 2, 1, 100);
INSERT INTO tickets VALUES(3, 3, 1, 100);
INSERT INTO tickets VALUES(4, 4, 4, 200);
INSERT INTO tickets VALUES(5, 5, 4, 200);
INSERT INTO tickets VALUES(6, 6, 4, 200);
INSERT INTO tickets VALUES(7, 7, 4, 200);
INSERT INTO tickets VALUES(8, 8, 5, 150);
INSERT INTO tickets VALUES(9, 9, 5, 150);

/*mistakes in timetable*/
select
    movie1.session_id movie1_session_id,
    movie1.title movie1_title,
    movie1.start_date movie1_start_date,
    sec_to_time(movie1.duration) movie1_duration,
    movie2.session_id movie2_session_id,
    movie2.title movie2_title,
    movie2.start_date movie2_start_date,
    sec_to_time(movie2.duration) movie2_duration
from
    (select
         sessions.id session_id,
         movies.title,
         sessions.start_date,
         movies.duration * 60 duration
     from
         sessions join movies on sessions.movie_id = movies.id) as movie1,
    (select
         sessions.id session_id,
         movies.title,
         sessions.start_date,
         movies.duration * 60 duration
     from
         sessions join movies on sessions.movie_id = movies.id) as movie2
where
        movie1.session_id <> movie2.session_id
  and movie1.start_date < movie2.start_date
  and timediff(movie2.start_date, movie1.start_date) < sec_to_time(movie1.duration);

/*intervals more that 30 mins*/
select
    *
from
    (select
         movies_diff.*,
         timediff(timediff(movies_diff.next_movie_start_date, movies_diff.start_date), movies_diff.duration) interval_beetween
     from
         (select
              movies.title,
              start_date,
              sec_to_time(movies.duration * 60) duration,
              (select start_date from sessions where start_date > movie.start_date order by start_date limit 1) next_movie_start_date
         from
      sessions movie
      join movies on movie_id = movies.id) movies_diff) intervals
where
    interval_beetween > sec_to_time(30 * 60)
order by interval_beetween desc;

/*ticketing analysis by movie*/
select
    movies.title,
    movie_summary.tickets_by_movie,
    movie_summary.avg_tickets_by_movie,
    movie_summary.total_income_by_movie
from
    (
        select
            sessions.movie_id,
            sum(tickets_sold) tickets_by_movie,
            avg(tickets_sold) avg_tickets_by_movie,
            sum(total_income) total_income_by_movie
        from
            (select
                 session_id,
                 count(1) tickets_sold,
                 sum(cost) total_income
             from
                 tickets
             group by session_id) session_summary
                join sessions on sessions.id = session_summary.session_id
        group by movie_id
        order by total_income_by_movie desc
    ) movie_summary
        join movies on movie_summary.movie_id = movies.id

union all

select distinct
    null,
    sum(tickets_sold) over(),
        avg(tickets_sold) over(),
        sum(total_income) over()
from
    (
        select
            session_id,
            count(1) tickets_sold,
            sum(cost) total_income
        from
            tickets
        group by session_id
    ) session_summary;

/*ticketing analysis by daytime*/
select
    period,
    sum(tickets_sold) tickets_sold_for_period,
    sum(total_income) total_income_for_period
from
    (
        select
            case
                when start_hour >= 9 and start_hour < 15 then '9-15'
                when start_hour >= 15 and start_hour < 18 then '15-18'
                when start_hour >= 18 and start_hour < 21 then '18-21'
                when start_hour >= 21 then '21-00'
                end as period,
            start_hours.tickets_sold,
            start_hours.total_income
        from
            (
                select
                    HOUR(sessions.start_date) start_hour,
                    session_summary.tickets_sold,
                    session_summary.total_income
                from
                    (
                    select
                    session_id,
                    count(1) tickets_sold,
                    sum(cost) total_income
                    from
                    tickets
                    group by session_id) session_summary
                    join sessions on sessions.id = session_summary.session_id
            ) start_hours
    ) periods
group by period;