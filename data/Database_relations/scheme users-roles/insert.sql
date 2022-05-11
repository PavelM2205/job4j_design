insert into role(name) values('admin');
insert into role (name) values('contractor');
insert into role(name) values('user');

insert into rules(name) values('common');
insert into rules(name) values('for user');
insert into rules(name) values('for admin');
insert into rules(name) values('for contractor');

insert into role_rules(role_id, rule_id) values(1, 1);
insert into role_rules(role_id, rule_id) values(1, 3);
insert into role_rules(role_id, rule_id) values(2, 1);
insert into role_rules(role_id, rule_id) values(2, 4);
insert into role_rules(role_id, rule_id) values(3, 1);
insert into role_rules(role_id, rule_id) values(3, 2);

insert into users(name, role_id) values('Ivan', 1);
insert into users(name, role_id) values('Pavel', 2);
insert into users(name, role_id) values('Nikolay', 3);

insert into category(name) values('consultation');
insert into category(name) values('program error');
insert into category(name) values('changing a cartridge');

insert into state(name) values('sent');
insert into state(name) values('in operation');
insert into state(name) values('done');

insert into item(user_id, category_id, state_id) values(3, 2, 2);

insert into comments(item_id, message) values(1, 'How long ?');

insert into attachs(item_id, file_name) values(1, 'my_error.jpg');


