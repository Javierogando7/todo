<h1 align="center">
TODO
</h1>

<div align="center">

TODO is an Java, Spring Boot task management web api.

</div>

## 💻 Development

```bash
$ git clone https://github.com/Javierogando7/todo.git
$ cd todo
$ mvn -f ./pom.xml clean package
$ java -jar ./target/todo-1.0.0-SNAPSHOT.jar
```

## 💻 Development in docker

```bash
$ git clone https://github.com/Javierogando7/todo.git
$ cd todo
$ docker build -t todo .
$ docker run -d -p 8080:8080 todo
```

Navigate to `http://localhost:8080/api/task`.