## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:

Мой datasource порт 5444:5432 для DB
Мой datasource порт 5433:5432 для TEST


2. Удалил соцсети: VK, Yandex
    Удалил bean-классы VkOAuth2UserDataHandler, YandexOAuth2UserDataHandler
    Закомментировал 66-77 строки в application.yaml
    Закомментировал 51-58 строки в login.html
3. Вынес чувствительную информацию в application-sensitive.yaml
    Добавил в application.yaml: 
     config:
         import: classpath:application-sensitive.yaml
    Закомментировал все чувствительные поля в application.yaml
    Переменные окружения добавлял через CMD и после перезагрузки ПК приложение запустилось правильно.
6. Сделал рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload
5. Написал тесты для методов контроллера ProfileRestController
