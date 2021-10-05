[![Build Status](https://travis-ci.org/ZubovVP/job4j_car_accident.svg?branch=master)](https://travis-ci.org/ZubovVP/job4j_car_accident)

# Car_accident (Автонарушители).
Car_accident - это Web проект, которые позволяет заносить информацию о нарушениях, в системе существует две роли - обычные пользователи и автоинспекторы.   
В проекте настроена система фильтрации, для того чтобы попасть на стартовую страницу, необходимо пройти систему регистрации.    
![регистрация](src/main/resources/images/registration.png)  
Пользователи добавляют автонарушения.   
![стартовая страница](src/main/resources/images/index_page.png)     
В заявлении указывает: название, описание, адрес, тип нарушения (участники нарушения), статья нарушения.    
У заявки есть статус. Принята. Отклонена. Завершена.    
Инспекторам позволяется видеть все заявки и изменять.     
![стартовая страница](src/main/resources/images/index_page(admin).png)  
Изменение заявки осуществляется через заполнение формы.     
![стартовая страница](src/main/resources/images/corret_accident.png)    
Данный проект разрабатывался на основе следующих модулей Spring:    
1) Spring Core Container (Beans, Core, Context, SpEl);  
2) Spring Data (JDBC, ORM); 
3) Spring Web (Servlet, Web);   
4) Spring Test; 
5) Spring Security. 

Кроме Spring в ходе разработки проекта использовались следующие инструменты:
   * java
   * servlets
   * postgresql
   * js
   * ajax
   * jquery
   * bootstrap
   * mockito
   * jstl
   * jsp
   * hibernate
   * maven
   * liquibase
   * junit