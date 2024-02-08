# Manga API 
API для нативного сервиса чтения манги.
<br>
<br>
Это приложение может производить операции над мангой
<br>
Вы можете получить доступ к Swagger и просмотреть все доступные конечные точки, посетивhttp://localhost/swagger-ui/index.html

## Схема компонентов
![image](https://github.com/SaScp/native_manga_manager/assets/96395954/2180d464-eb1b-4e2d-b068-01d472e9a343)

Клиент взаимодействует с основным приложеним посредством обрщения к web - серверу(в нашем случае Nginx), который взаимодействует с Основным приложением. Основное приложние взаимодействует с кешем (мы используем Redis), базами данных (мы используем Postgresql и MongoDB).
## Схема базы данных (PostgreSQL)
![image](https://github.com/SaScp/native_manga_manager/assets/96395954/c6101647-c2cf-4639-9a46-810f7063b221)

У нас есть два основных класса — User и Manga .

Класс пользователя представляет пользователя в этом приложении. Пользователь может входить в систему, сохранять и просмтаривать мангу.

Пользователь может иметь роли - ROLE_USER, ROLE_ADMIN, ROLE_BLOCK, ROLE_MUTE (на данный момент реализованы не все роли).

Класс Манги представляет мангу в этом приложении. Мангу можно искать по параметрам, отсортировывать и фильтровать.

Также к манге можно оставлять комментарии
## Переменные среды

Чтобы запустить это приложение, вам необходимо создать <code>.env</code> файл в корневом каталоге со следующими средами:

<code>HOST</code>- хост базы данных Postgresql
<br>
<code>POSTGRES_USERNAME</code>- имя пользователя для базы данных Postgresql
<br>
<code>POSTGRES_PASSWORD</code>- пароль для базы данных Postgresql
<br>
<code>POSTGRES_DATABASE</code>- имя базы данных Postgresql
<br>
<code>REDIS_HOST</code>— хост экземпляра Redis
<br>
<code>MONGO_DATABASE</code> - имя базы данны MongoDB
<br>
<code>MONGO_PASSWORD</code> - пароль для базы данных MongoDB
<br>
<code>MONGO_USERNAME</code> - имя пользователя для базы данных MongoDB 
<br>
<code>MONGO_HOST</code> - имя хоста для базы данных MongoDB

## Docker
После создания <code>.env</code> файла нужно запустить Docker compose в консоли в основном каталоге данного API с помощью команды: <code>docker-compose up</code>
<br>
Для того, чтобы обновить основной контейнер приложения в Docker нужно воспользоваться командой
<br>
<code>docker-compose bulid</code>
