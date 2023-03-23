INSERT INTO chat.user(name, password) VALUES
('Eminem','Google'), ('Lady Gaga','Microsoft'), ('Luciano Pavarotti','Facebook'),
('Adele','UNIX'), ('Cardy B','Linux'), ('Rihanna','Apple')
;

INSERT INTO chat.chatroom(title, owner) VALUES
('Chat 1', (SELECT id FROM chat.user WHERE name = 'Eminem')),
('Chat 2', (SELECT id FROM chat.user WHERE name = 'Lady Gaga')),
('Chat 3', (SELECT id FROM chat.user WHERE name = 'Luciano Pavarotti')),
('Chat 4', (SELECT id FROM chat.user WHERE name = 'Adele')),
('Chat 5', (SELECT id FROM chat.user WHERE name = 'Cardy B')),
('Chat 6', (SELECT id FROM chat.user WHERE name = 'Rihanna'))
ON CONFLICT DO NOTHING;

INSERT INTO chat.message (author, room, text) VALUES
((SELECT id FROM chat.user WHERE name = 'Eminem'),      (SELECT id FROM chat.chatroom WHERE title = 'Chat 1'), 'Hello, I am Eminem'),
((SELECT id FROM chat.user WHERE name = 'Lady Gaga'),      (SELECT id FROM chat.chatroom WHERE title = 'Chat 2'), 'Hello, I am Lady Gaga'),
((SELECT id FROM chat.user WHERE name = 'Luciano Pavarotti'), (SELECT id FROM chat.chatroom WHERE title = 'Chat 3'), 'Hello, I am Luciano'),
((SELECT id FROM chat.user WHERE name = 'Adele'),    (SELECT id FROM chat.chatroom WHERE title = 'Chat 4'), 'Hello, I am Adele'),
((SELECT id FROM chat.user WHERE name = 'Cardy B'),  (SELECT id FROM chat.chatroom WHERE title = 'Chat 5'), 'Hello, I am Cardy B'),
((SELECT id FROM chat.user WHERE name = 'Rihanna'),    (SELECT id FROM chat.chatroom WHERE title = 'Chat 6'), 'Hello, I am Rihanna')
ON CONFLICT DO NOTHING;

