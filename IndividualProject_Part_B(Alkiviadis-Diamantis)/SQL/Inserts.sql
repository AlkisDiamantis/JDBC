insert into STUDENTS
values 
(null,'Alkiviadis','Diamantis','1987-02-18 12:12:12',450),
(null,'John','Johnopoulos','1997-02-13 12:12:12',400),
(null,'Adam','Adamopoulos','1977-03-16 12:12:12',300),
(null,'Mary','Mariopoulou','1957-12-18 12:12:12',550),
(null,'Jehny','Jehnopoulou','1989-03-10 12:12:12',950),
(null,'Paul','Pavlopoulos','1990-11-08 12:12:12',850),
(null,'Andy','Opoulos','1987-11-18 12:12:12',450),
(null,'Jack','Iakovidis','1967-02-11 12:12:12',750),
(null,'Katy','Katopoulou','1991-06-28 12:12:12',490),
(null,'Jim','Dimitriadis','1997-11-28 12:12:12',550);


insert into courses
values
(null,'Java','java','Full-time','2020-01-01 12:12:12','2020-12-20 12:12:12' ),
(null,'Java','java','Part-time','2020-01-01 12:12:12','2021-12-20 12:12:12' ),
(null,'C#','C#','Full-time','2020-01-01 12:12:12','2020-12-20 12:12:12' ),
(null,'C#','C#','Part-time','2020-01-01 12:12:12','2021-12-20 12:12:12' ),
(null,'Sql','Sql','Full-time','2020-01-01 12:12:12','2020-12-20 12:12:12' ),
(null,'Sql','Sql','part-time','2020-01-01 12:12:12','2021-12-20 12:12:12' );



insert into ASSIGNMENTS
values
(null,'School','Make a school structure',30,100),
(null,'e-shop','Make an e-shop structure',30,100),
(null,'Hospital','Make a hospital structure',30,100),
(null,'Bank','Make a Bank structure',30,100);


insert into trainers
values
(null,'Tasos','Lelakis','Java-pro'),
(null,'Argyris','Pagidas','Sql-pro'),
(null,'George','Pasparakis','pro'),
(null,'Michalis','Chamilos','Theory-pro'),
(null,'George','Irakleidis','Java-pro');

insert into STUDENTS_COURSES
values
(1,1),(2,1),(3,1),
(4,2),(5,2),(6,3),
(7,4),(8,4),(9,5),
(10,6),(2,2),(3,5),(5,6);

insert into TRAINER_COURSES
values
(1,1),(1,2),
(2,5),(2,6),
(3,3),(3,4),(3,6),
(4,5),(4,6),(4,3),
(5,1),(5,2);

insert into ASSIGMENTS_COURSES
values
(null,1,1,'2020-10-20 12:12:12'),
(null,1,2,'2021-10-20 12:12:12'),
(null,2,1,'2020-10-20 12:12:12'),
(null,2,2,'2021-10-20 12:12:12'),
(null,3,3,'2020-10-20 12:12:12'),
(null,3,4,'2021-10-20 12:12:12'),
(null,4,5,'2020-10-20 12:12:12'),
(null,4,6,'2021-10-20 12:12:12');


insert into Student_assignment
values 
(1,1,80,20),
(2,4,70,10),
(3,1,90,12),
(4,4,89,13),
(5,4,70,14);



