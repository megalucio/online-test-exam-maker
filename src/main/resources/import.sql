--Question 1
insert into question(id, display_order, title, more_than_one_answer) values (1, 1, 'What is the range of data type short in Java?', false);

insert into answer(id, display_order, text, correct, question_id) values (1, 1, '-128 to 127', false, 1);
insert into answer(id, display_order, text, correct, question_id) values (2, 2, '-32768 to 32767', true, 1);
insert into answer(id, display_order, text, correct, question_id) values (3, 3, '-2147483648 to 2147483647', false, 1);
insert into answer(id, display_order, text, correct, question_id) values (4, 4, 'None of the mentioned', false, 1);

--Question 2
insert into question(id, display_order, title, more_than_one_answer) values (2, 2, 'An expression involving byte, int, and literal numbers is promoted to which of these?', false);

insert into answer(id, display_order, text, correct, question_id) values (5, 1, 'int', true, 2);
insert into answer(id, display_order, text, correct, question_id) values (6, 2, 'long', false, 2);
insert into answer(id, display_order, text, correct, question_id) values (7, 3, 'byte', false, 2);
insert into answer(id, display_order, text, correct, question_id) values (8, 4, 'float', false, 2);

--Question 3
insert into question(id, display_order, title, more_than_one_answer) values (3, 3, 'Which of these literals can be contained in a data type float variable?', false);

insert into answer(id, display_order, text, correct, question_id) values (9, 1, '1.7e-308', true, 3);
insert into answer(id, display_order, text, correct, question_id) values (10, 2, '3.4e-038', false, 3);
insert into answer(id, display_order, text, correct, question_id) values (11, 3, '1.7e+308', false, 3);
insert into answer(id, display_order, text, correct, question_id) values (12, 4, '3.4e-050', false, 3);

--Exam 1
insert into exam(id, name, description, duration, pass_score, total_score ) values (1, 'Java Basics', 'An exam for testing Basic Java Knowledge', 20, 5.00, 10.00);

insert into exam_questions(exam_id, questions_id) values (1, 1);
insert into exam_questions(exam_id, questions_id) values (1, 2);
insert into exam_questions(exam_id, questions_id) values (1, 3);

--User 1
insert into user(username, full_name, password) values ('candidate', 'Anonimous Candidate' , 'no1knows');

