## Database Setup

This project uses [MongoDB](https://www.mongodb.com/) for data persistence. If you're new to [MongoDB](https://www.mongodb.com/) or [Spring Data](https://spring.io/projects/spring-data), I highly recommend you to read the official [Spring's documentation](https://spring.io/projects/spring-data-mongodb) / [getting started guide]
(https://spring.io/guides/gs/accessing-data-mongodb/) 📚. 

### Install and Launch MongoDB

##### Install MongoDB on MacOS
```bash
$ brew install mongodb
```

##### With MacPorts
```bash
$ port install mongodb
```

For other systems with package management, such as Redhat, Ubuntu, Debian, CentOS, and Windows, see instructions at http://docs.mongodb.org/manual/installation/.

After you install [MongoDB](https://www.mongodb.com/), launch it in a console window to test it out.
```bash
$ mongod
```
That is all you need to get started, Spring will generate for you an empty [MongoDB](https://www.mongodb.com/) database when you run our project for the first time. You can interact with this database, using [Intellij's MongoDB Plugin](https://plugins.jetbrains.com/plugin/7141-mongo-plugin) or using your terminal `mongod`.
