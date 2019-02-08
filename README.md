# TeamFinder
Сервис для получения информации по командам

# Что необходимо для запуска
  - Java 1.8
  - Gradle 5
  - Consul
  - MongoDB 4.0.1
  - RabbitMQ 3.7.11(с плагинами [администрирования](https://www.rabbitmq.com/management.html) и [метки времени](https://github.com/rabbitmq/rabbitmq-message-timestamp))

# Как запускать
  - Загрузить содержимое файла definitions.json в RabbitMQ
  - Создать в MongoDB базу данных team_db, пользователя team-db-user c паролем team-db-pass
  - Запустить Consul: `consul agent -dev -ui`
  - Открыть новое окно терминала, зайти в папку team-service, выполнить `gradle bootRun` 
  - Открыть новое окно терминала, зайти в папку messaging-service, выполнить `gradle bootRun`  
  - Открыть новое окно терминала, зайти в папку gateway-service, выполнить `gradle bootRun`

# API для взаимодействия

## Получение информации о команде

### URL
localhost:8082/team-service/{team_id}

### Тип запроса, Content-Type
GET, application/json

### Параметры
team_id - id команды

### Тело запроса
Нет

### Тело ответа(пример)
```
{
	"team_id": "gjfigh3ht38945-q3485q45",
	"participant_id": "sdbf3q4uhrf9wefcho",
	"participant_identifier": "Иванов Иван"
}
``` 

### Ответ в случае отсутствия команды
```
{
    "timestamp": "2019-02-06T17:07:20.297+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "Нет такой команды!",
    "path": "/asdfasdfas"
}
```

## Запрос на то, чтобы стать капитаном

### URL
localhost:8082/messaging-service/accept-cap

### Тип запроса, Content-Type
POST, application/json

### Параметры
Нет

### Тело запроса
```
{
	"team_id": "gjfigh3ht38945-q3485q45",
	"participant_id": "sdbf3q4uhrf9wefcho",
	"participant_identifier": "Иванов Иван"
}
```

### Тело ответа
```
{
	"message": "Заявка отправлена"
}
```

### Что происходит после отправки запроса
Тело запроса отправляется в очередь RabbitMQ. С помощью плагина [метки времени](https://github.com/rabbitmq/rabbitmq-message-timestamp)) обрабатывается заголовок с временем создания сообщения. Если капитан уже есть - в ответ отправляется сообщение `{"status": false}`, иначе - `{"status": true}`.

## Запрос на то, чтобы отказаться от капитанства

### URL
localhost:8082/team-service/decline_cap

### Тип запроса, Content-Type
POST, application/json

### Параметры
Нет

### Тело запроса
```
{
	"team_id": "gjfigh3ht38945-q3485q45",
	"cap_id": "sdbf3q4uhrf9wefcho"
}
```

### Тело ответа
```
{
	"message": "Заявка отправлена"
}
```

### Что происходит после отправки запроса
Тело запроса отправляется в очередь RabbitMQ. Результат обработки отправляется в другую очередь. 

# Тесты в проекте

## Описание
В папке `team-service/src/test` находится файл `MongoDBIntegrationTest.java`, в котором есть одновременно и интеграционный, и юнит - тест. Там проверяется получение информации о команде. Важное замечание: данный тест может падать из-за нехватки памяти. Возможное решение: увеличение количества оперативной памяти.