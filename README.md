# gge1
Test task#1 for GGE

Существует БД в СУБД Postges (или иной реляционной СУБД) c с именем squeryl 

Пользователь: postgres

Пароль: postgres

(Или иные имя пользователя и пароль)


Таблица Person имеет вид


CREATE TABLE PERSON (id bigint primary key, firstName: varchar(128), lastName: varchar(128))


При помощи библиотеки ORM Squeryl создайте методы:

1. создания таблицы;

2. удаления таблицы;

3. помещения тестовых данных в таблицу с использованием _автонумерации_ ключа;

4. удалении данных по заданному ключу из таблицы;

5. выборки данных из таблицы для показа на консоли.



Т.е. сигнатуры методов будут
```
def mydrop 
def mycreate
def myinsert
def mydelete(id: Long) 
def myselect 
```

Естественно, требуются перехват исключительных ситуаций и откат 
или подтверждение изменения данных.

Создайте с использованием Scala Squeryl программу, реализующую и демонстрирующую к
корректность работы всех вышеприведенных методов.

Задачу можно оформить в IDE IJ IDEA или Eclipse или в видк автономной программы.

Желательно с использованием средства автоматической сборкой проекта sbt.
