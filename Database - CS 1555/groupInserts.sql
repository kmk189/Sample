--Group Inserts 
INSERT INTO groups(gID, name, description)
	VALUES(1, 'Pitt Students', 'students at pitt');
INSERT INTO groups(gID, name, description)
	VALUES(2, 'Pitt CS', 'students at pitt in CS');
INSERT INTO groups(gID, name, description)
	VALUES(3, 'CatzRAwesome', 'join if you love cats');
INSERT INTO groups(gID, name, description)
	VALUES(4, 'Super Cool People', 'party');
INSERT INTO groups(gID, name, description)
	VALUES(5, 'I love 1555', 'best class ever');
INSERT INTO groups(gID, name, description)
	VALUES(6, 'Secret Group', 'shhhhhh');
INSERT INTO groups(gID, name, description)
	VALUES(7, 'What are Memes', 'lolz');
INSERT INTO groups(gID, name, description)
	VALUES(8, 'Free Money', 'help im poor');
INSERT INTO groups(gID, name, description, member_limit)
	VALUES(9, 'A+++', 'exclusive club for group 1 members', 2);
INSERT INTO groups(gID, name, description)
	VALUES(10, 'Puppy Pics', 'so cute');
	
INSERT INTO groupMembership(gID, userID, role)
	VALUES(9, 's_lambert', 'admin');
INSERT INTO groupMembership(gID, userID, role)
	VALUES(9, 'k_krause', 'member');
