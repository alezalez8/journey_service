# TICKET
## ДЗ 5. Hibernate relations

#### Decision: 

Реализованы все методы в соответствующих слоях <br>


#### Task: 
  Реализовать получение всех сущностей способами: <br>
1. findAll (метод поиска через HQL)<br>
2. findAllAsNative (метод поиска через вызов sql запроса)<br>
3. findAllAsNamed (метод поиска через вызов именованного запроса по алиасу)<br> 
4. findAllAsCriteria (метод поиска через CriteriaBuilder)<br>
5. findAllAsStoredProcedure (метод поиска через вызов хранимой функции)<br>
<br> 
  То есть для каждой сущности должен быть свой repository и service классы,<br>
 в рамках которых вы реализуете все требуемые методы.<br>


