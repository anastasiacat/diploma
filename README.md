# Процедура запуска автотестов
#### Предисловие:
Убедитесь, что Docker Daemon запущен. На Windows для этого нужно запустить Docker Desktop.
### 1. Склонировать репозиторий
git clone https://github.com/anastasiacat/diploma
### 2. Перейти в папку diploma
## Для работы с базой данных MySQL
### 3. Запустить docker-контейнер
docker-compose -f docker-compose-mysql.yml up -d

После первого запуска нужно будет подождать несколько минут, чтобы база данных MySQL проинициализировалась.
### 4. Запустить приложение
java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar
### 5. Запустить тесты
gradlew test "-Ddb.url=jdbc:mysql://localhost/app" --no-build-cache
### 6. Сформировать отчёты
gradlew allureReport
### 7. Открыть отчёты
gradlew allureServe
### 8. Остановить запущенное приложение
Комбинацией клавиш Ctrl+C
### 9. Остановить запущенный docker-контейнер
docker-compose -f docker-compose-mysql.yml down

## Для работы с базой данных PostgreSQL
### 3. Запустить docker-контейнер
docker-compose -f docker-compose-postgresql.yml up -d
### 4. Запустить приложение
java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar
### 5. Запустить тесты
gradlew test "-Ddb.url=jdbc:postgresql://localhost/app" --no-build-cache
### 6. Сформировать отчёты
gradlew allureReport
### 7. Открыть отчёты
gradlew allureServe
### 8. Остановить запущенное приложение
Комбинацией клавиш Ctrl+C
### 9. Остановить запущенный docker-контейнер
docker-compose -f docker-compose-postgresql.yml down
