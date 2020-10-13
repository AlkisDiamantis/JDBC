create database Schooly_V1;
use Schooly_V1;

CREATE TABLE STUDENTS (
STUDENT_ID INT NOT NULL auto_increment,
FIRST_NAME VARCHAR(100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
DATE_OF_BIRTH datetime NOT NULL,
TUITION_FEES DECIMAL (8,2),
PRIMARY KEY (STUDENT_ID)
);
alter table students
add constraint uq_students unique(FIRST_NAME, LAST_NAME,DATE_OF_BIRTH);

CREATE TABLE COURSES(
COURSE_ID INT NOT NULL auto_increment,
TITLE VARCHAR(100) NOT NULL,
STREAM VARCHAR(100) NOT NULL,
TYPE VARCHAR(100) NOT NULL,
START_DATE datetime NOT NULL,
END_DATE datetime NOT NULL,
primary key (COURSE_ID)
);
alter table COURSES
add constraint uq_courses unique(TITLE, STREAM,START_DATE,END_DATE);




CREATE TABLE ASSIGNMENTS(
ASSIGMENT_ID INT NOT NULL auto_increment,
TITLE VARCHAR(100) NOT NULL,
DESCRIPTION VARCHAR(400),
ORAL_MARK DECIMAL(5,2),
TOTAL_MARK DECIMAL (5,2),
primary key (ASSIGMENT_ID)
);
alter table ASSIGNMENTS
add constraint uq_assignments unique(TITLE, DESCRIPTION);



CREATE TABLE TRAINERS(
TRAINER_ID INT NOT NULL auto_increment,
FIRST_NAME VARCHAR (100) NOT NULL,
LAST_NAME VARCHAR(100) NOT NULL,
SUBJECT VARCHAR(200),
primary key (TRAINER_ID)
);

alter table TRAINERS
add constraint uq_trainers unique(FIRST_NAME, LAST_NAME,SUBJECT);


CREATE TABLE STUDENTS_COURSES (
STUD_ID INT NOT NULL,
COUR_ID INT NOT NULL,
primary key (STUD_ID,COUR_ID),
foreign key (STUD_ID) references STUDENTS (STUDENT_ID),
foreign key (COUR_ID) references COURSES (COURSE_ID)
);




CREATE TABLE TRAINER_COURSES(
TRAI_ID INT NOT NULL,
COUR_ID INT NOT NULL,
primary key (TRAI_ID,COUR_ID),
foreign key (TRAI_ID) references TRAINERS (TRAINER_ID),
foreign key (COUR_ID) references COURSES(COURSE_ID)
);




create TABLE ASSIGMENTS_COURSES(
assi_course_id int not null auto_increment,
ASSIGNMENT_ID INT NOT NULL,
COUR_ID INT NOT NULL,
SUBMISION_DATE datetime NOT NULL,
unique(ASSIGNMENT_ID, COUR_ID),
primary key (assi_course_id),
foreign key (ASSIGNMENT_ID) references ASSIGNMENTS(ASSIGMENT_ID),
foreign key (COUR_ID) references COURSES(COURSE_ID)
);



create table Student_assignment(
student_id int not null,
assi_course_id int not null,
MARK  DECIMAL(5,2) default null,
oral_mark DECIMAL(5,2) default null,
primary key (student_id,assi_course_id),
foreign key (student_id) references STUDENTS(student_ID),
foreign key(assi_course_id ) references ASSIGMENTS_COURSES(assi_course_id)
);



