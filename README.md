# Парсинг wildberries по брендам
Сохраняются:
- айди товара;
- название бренда;
- название товара;
- стоимость товара;
- оценка;
- кол-во отзывов.

## 1. Парсинг с помощью jsoup и selenium
- http://localhost:8080/api/v1/parsing/html/brands/{brandId}
## 2. Парсинг с помощью api 
- http://localhost:8080/api/v1/parsing/api/brands/{brandId}


### Для запуска необходимо: 
- Создать базу данных Postgres
- в файле application.properties поменять следующие параметры
  - spring.datasource.url
  - spring.datasource.username
  - spring.datasource.password
