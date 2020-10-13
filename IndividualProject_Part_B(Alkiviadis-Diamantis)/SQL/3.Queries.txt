
-- LIST OF ALL THE STUDENTS
SELECT * FROM STUDENTS;
-- LIST OF ALL THE TRAINERS
SELECT * FROM TRAINERS;
-- LIST OF ALL THE ASSIGNMENTS
SELECT * FROM ASSIGNMENTS;
-- LIST OF ALL THE COURSES
SELECT * FROM COURSES;


-- LIST OF ALL THE STUDENTS PER COURSE

 select  courses.title , students.first_name , students.last_name  
 from students
 join students_courses on students.STUDENT_ID = students_courses.stud_id
 join courses on course_id = students_courses.COUR_ID;
 
 
 
-- LIST OF ALL THE TRAINERS PER COURSE 

 select courses.title, courses.type, trainers.first_name, trainers.Last_name
 from trainers
 join trainer_courses on trai_id = trainer_id
 join courses on course_id = cour_id;


 
 -- LIST OF ALL THE ASSIGNMENTS PER COURSE

 select courses.title as COURSE_TITLE ,courses.type, assignments.title AS ASSIGNMENT_TITLE
 from assignments
 join assigments_courses on assigments_courses.assignment_id = assignments.assigment_id
 join courses on courses.course_id = assigments_courses.cour_id
 ORDER BY courses.title;
 



-- LIST OF ALL THE ASSIGNMENTS PER SRUDENT PER COURSE

 select students.first_name , students.last_name , courses.title AS COURSE ,courses.type, assignments.title AS ASSIGNMENT,student_assignment.MARK,student_assignment.oral_mark
 from students
 JOIN student_assignment ON student_assignment.student_id = students.STUDENT_ID
 join assigments_courses on assigments_courses.assi_course_id = student_assignment.assi_course_id
 join courses on courses.COURSE_ID = assigments_courses.COUR_ID
 join assignments on assignments.ASSIGMENT_ID = assigments_courses.ASSIGNMENT_ID
 ORDER BY courses.title;
 


 -- LIST OF ALL THE STUDENTS THAT BELONGS TO MORE THAN ONE COURSES
 select  students.first_name , students.last_name, COUNT(*) AS NUMMBER_OF_COURSES
 from students
 join students_courses on students.STUDENT_ID = students_courses.stud_id
 join courses on course_id = students_courses.COUR_ID
 GROUP BY STUD_ID
 HAVING COUNT(*)>1


 
 