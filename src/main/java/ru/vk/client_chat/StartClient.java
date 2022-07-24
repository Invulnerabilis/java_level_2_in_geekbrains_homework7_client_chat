package ru.vk.client_chat;

/*
Java. Уровень 2. Урок 7.
"Написание сетевого чата. Часть I".

1. Разобраться с кодом.

2. * Реализовать личные (приватные) сообщения, если клиент пишет «/pm nick_такой-то Привет», то только клиенту с ником nick_такой-то должно прийти сообщение «Привет».

3. * Реализовать вызов метода unSubscribe. Как убрать пользователя из общего списка всех клиентов при его отключение.
Т.е., исправить когда окно какого-то пользователя уже закрыто, но оно остаётся не завершённым.
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartClient extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartClient.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Welcome to the chat room!");
        stage.setScene(scene);
        stage.setX(850);
        stage.setY(100);
        stage.setWidth(570);
        stage.setHeight(373);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
