
INSERT INTO unit_of_measure (id,description) VALUES (1,'Teaspoon');
INSERT INTO unit_of_measure (id,description) VALUES (2,'Tablespoon');
INSERT INTO unit_of_measure (id,description) VALUES (3,'Cup');
INSERT INTO unit_of_measure (id,description) VALUES (4,'Pinch');
INSERT INTO unit_of_measure (id,description) VALUES (5,'Gram');
INSERT INTO unit_of_measure (id,description) VALUES (6,'');

insert into recipe (id,name,servings,vegan,instruction) values (10,'Recipe1',4,1,'aaa bbb ccc ddd');
insert into recipe (id,name,servings,vegan,instruction) values (11,'Recipe2',2,0,'eee fff ggg hhh');
insert into recipe (id,name,servings,vegan,instruction) values (12,'Recipe3',5,0,'iii jjj kkk lll');

insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('baking powder',1,4,10);
insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('eggs',2,6,10);
insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('flour',2,3,10);

insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('pasta',200,5,11);
insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('salt',1,1,11);
insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('mozzarella',50,5,11);

insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('mint',5,5,12);
insert into Ingredient (name,amount,UOM_ID,RECIPE_ID) values ('lime',4,2,12);

insert into users (id,username,password) values (100,'user1','qwertyuioasdfghjkl');
insert into users (id,username,password) values (200,'user2','qwertyuioasdfghjkl');
insert into users (id,username,password) values (300,'user3','qwertyuioasdfghjkl');

insert into user_recipe (user_id,recipe_id) values (100,10);
insert into user_recipe (user_id,recipe_id) values (100,11);
insert into user_recipe (user_id,recipe_id) values (100,12);

insert into user_recipe (user_id,recipe_id) values (200,11);
insert into user_recipe (user_id,recipe_id) values (200,12);

insert into user_recipe (user_id,recipe_id) values (300,10);
insert into user_recipe (user_id,recipe_id) values (300,12);







