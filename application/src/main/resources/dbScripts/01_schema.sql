create table app_users (
     id number(19,0) not null,
      access_token varchar2(255 char),
      email varchar2(255 char),
      gender varchar2(255 char),
      google_id varchar2(255 char),
      link varchar2(255 char),
      name varchar2(255 char),
      picture varchar2(255 char),
      primary key (id)
  );


create table parent (
   id number(19,0) not null,
    added_by number(19,0),
    parent_id number(19,0),
    primary key (id)
);


create table parent_children (
   family_id number(19,0) not null,
    children_id number(19,0) not null,
    primary key (family_id, children_id)
);

create table person_db (
   id number(19,0) not null,
    added_by number(19,0),
    additional_name varchar2(255 char),
    date_of_birth date,
    date_of_death date,
    first_name varchar2(255 char),
    gender varchar2(255 char),
    last_name varchar2(255 char),
    nickname varchar2(255 char),
    photo_url varchar2(255 char),
    resource_name varchar2(255 char),
    synched number(1,0) not null,
    user_id number(19,0),
    primary key (id)
);


create table phrase (
   id number(19,0) not null,
    cebuano varchar2(255 char),
    english varchar2(255 char) not null,
    french varchar2(255 char),
    ilocano varchar2(255 char),
    japanese varchar2(255 char),
    primary key (id)
);


create table plugin (
   id number(19,0) not null,
    description varchar2(255 char),
    ide number(10,0),
    image varchar2(255 char),
    link varchar2(255 char),
    name varchar2(255 char),
    primary key (id)
);


create table relation_look_up (
   id number(19,0) not null,
    female_name varchar2(255 char),
    male_name varchar2(255 char),
    opposite_id number(19,0),
    primary key (id)
);

create table relationship (
   id number(19,0) not null,
    added_by number(19,0),
    main_id number(19,0),
    other_id number(19,0),
    primary key (id)
);

create table relationship_relation (
   relationship_id number(19,0) not null,
    relation_name varchar2(255 char) not null,
    primary key (relationship_id, relation_name)
);

create table relation_type (
   name varchar2(255 char) not null,
    primary key (name)
);

create table role (
   name varchar2(255 char) not null,
    primary key (name)
);

create table shortcut (
 id number(19,0) not null,
  description varchar2(255 char),
  eclipse varchar2(255 char),
  intellij varchar2(255 char),
  vs_code varchar2(255 char),
  primary key (id)
);

create table user_role (
   user_id number(19,0) not null,
    role_id varchar2(255 char) not null,
    primary key (user_id, role_id)
);


    alter table parent
       add constraint FKnasc5b5dqwsfkj0vqdd5n6oiw
       foreign key (parent_id)
       references person_db;


    alter table parent_children
       add constraint FK5tbpx8nb2dh0lc2jvojck1nyd
       foreign key (children_id)
       references person_db;

    alter table parent_children
       add constraint FKlaaas72u3wf6fbg2gy6t227w4
       foreign key (family_id)
       references parent;

    alter table person_db
       add constraint FKhag3e1cbu5dy6amxwd09kio0m
       foreign key (user_id)
       references app_users;

    alter table relation_look_up
       add constraint FKlurcsndamxkcpxcufn6ino2bc
       foreign key (female_name)
       references relation_type;

    alter table relation_look_up
       add constraint FK9k4k3ea882ard6ubnv1wdu6ds
       foreign key (male_name)
       references relation_type;

    alter table relation_look_up
       add constraint FKl4vbwo25cmsfic2y7l2rrgec4
       foreign key (opposite_id)
       references relation_look_up;

    alter table relationship
       add constraint FKkq61nr34hipr9fvnlcjqyhjnj
       foreign key (main_id)
       references person_db;

    alter table relationship
       add constraint FKepbg61jiggu218g90k49pueg7
       foreign key (other_id)
       references person_db;

    alter table relationship_relation
       add constraint FK4ol1x7qwfsh1pbsxo0j6u0jd1
       foreign key (relation_name)
       references relation_type;

    alter table relationship_relation
       add constraint FKe7vb6lxnth0r7204ak1p4wv8l
       foreign key (relationship_id)
       references relationship;

    alter table user_role
       add constraint FKa68196081fvovjhkek5m97n3y
       foreign key (role_id)
       references role;

    alter table user_role
       add constraint FKnnjwin2r8oajs3wmc8sbn0672
       foreign key (user_id)
       references app_users;
