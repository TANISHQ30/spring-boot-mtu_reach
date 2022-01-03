-- Sample Data Insert on Application Start
INSERT INTO Mentee (menteeId, email, firstName, surname) VALUES (1, "mbolt@reach.ie", "Michael", "Bolt");
INSERT INTO Mentee (menteeId, email, firstName, surname) VALUES (2, "drowling@reach.ie", "David", "Rowling");
INSERT INTO Mentee (menteeId, email, firstName, surname) VALUES (3, "jsears@reach.ie", "John", "Sears");

INSERT INTO Note (id, created, text, noteMentee) VALUES (1, CURRENT_DATE(), "Note One", 3);
INSERT INTO Note (id, created, text, student_id) VALUES (2, CURRENT_DATE(), "Note Two", 1);
INSERT INTO Note (id, created, text, student_id) VALUES (3, CURRENT_DATE(), "Note Three", 3);
