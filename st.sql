create table Student
(
       id int unsigned not null auto_increment primary key,
       user_name char(20) not null,
       password char(20) not null,

       student_id char(10) not null,      
       name char(20) not null,
       sex char(4) null,
       age int null,

       telephone char(11) not null,  
       emile char(20) not null,  
                                        
       specialty char(20) not null,     
       introduce char(100) not null        
);