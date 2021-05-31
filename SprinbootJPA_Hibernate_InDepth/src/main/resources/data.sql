
insert into Course (id,name,is_Deleted) VALUES 
	(1001,'Java Fundamentals',false), 
	(1002,'Data structures in Java',false),
	(1003,'Hibernate AND JPA',false),
	(1004,'Data Science',false),
	(1005,'Dummy1',false), 
	(1006,'Dummy2',false),
	(1007,'Dummy3',false),
	(1008,'Dummy4',false),
	(1009,'Dummy5',false);
	

insert into Passport (id,number) VALUES 
	(3001,'S12342'), 
	(3002,'M54342'),
	(3003,'A89345');
	
insert into Student (id,name,passport_id) VALUES 
	(2001,'Shujat',3001), 
	(2002,'Majid',3002),
	(2003,'Aftab Ali',3003);
	
	
insert into	Review (id,rating,description,course_id,student_id) VALUES 
	(4001,'FIVE','Wonderful Course',1001,2001), 
	(4002,'FOUR','Good Course',1002,2001),
	(4003,'FIVE','Awesome Course',1001,2002);
	
insert into STUDENT_COURSE(student_id,course_id) values
	(2001,1001),
	(2001,1002),
	(2002,1001),
	(2002,1002);
	