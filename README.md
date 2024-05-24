# **Super calculator**
<details><summary><strong>Операции:</strong></summary>

1) Сложение 2х и более чисел
2) Умножение 2х и более чисел
3) Умножение первых 2х чисел и сложение с 3м числом
4) Вычитание из первого числа последующих
5) Деление первого числа на последующие

</details>

Приложение может сохранять результат либо в файл, либо в БД.
Режим работы определяется параметром `calculator.qualifier` в `application.yml`:
- `CalculatorServiceForDb` - для работы с БД;
- `CalculatorServiceForFile` - для работы с файлом;

При работе с файлом указать название файла в `calculator.filePath` или передать в аргументе командной строки.


### Стек:

> Java 17, Spring Boot, Spring Data JPA, PostgreSQL, Lombok, Maven, Docker.

---

### Спецификация API

[Документация OpenAPI (swagger)](http://localhost:8080/swagger-ui.html)

Пример запроса:
>`curl -X 'POST' \
>'http://localhost:8080/calculate/add' \
>-H 'accept: */*' \
>-H 'Content-Type: application/json' \
>-d '[
>1,2,7
>]'`

---

### Примеры запуска локально с аргументами:
1) Собрать jar     
> mvn clean package

2) #### Для записи в файл:
      > java -jar target/super-calculator-0.0.1-SNAPSHOT.jar  --spring.profiles.active=test --calculator.qualifier=CalculatorServiceForFile --calculator.filePath=output.txt
       
      Файл будет находиться по пути: `file/output.txt`

- #### Для записи в БД:
>  java -jar target/super-calculator-0.0.1-SNAPSHOT.jar  --spring.profiles.active=test --calculator.qualifier=CalculatorServiceForDb
---

### Запуск дев среды

Находясь в корневой папке проекта вызвать

-      mvn clean package

-      docker compose up

---
