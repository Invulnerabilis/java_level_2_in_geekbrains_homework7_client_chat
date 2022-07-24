package ru.vk.client_chat.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import ru.vk.client_chat.models.Network;

public class ChatController {

    public Button sendButton;
    @FXML
    private ListView<String> usersList;

    @FXML
    private TextArea chatHistory;

    @FXML
    private TextField inputField;

    private Network network;

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList("Martin_Superstar", "Брюс_Уэйн", "Гендальф_Серый", "Super_Mario", "Bender", "Super_Sonic"));

        this.network = new Network();
        network.connect();
        network.startWaitingMessages(this);
    }

    public void sendMessage() {
        sendToClient(inputField.getText().trim());
        inputField.clear();
    }

    public void sendToClient(String message) {
        if (message.isEmpty()) {
            return;
        }
        appendMessage("Client: " + network.sendMessage(message));
    }

    public void appendMessage(String message) {
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
    }

    @FXML
    protected void onKeyPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER))
            if (!inputField.getText().equals("")) {
                sendToClient(inputField.getText());
                inputField.clear();
            }
    }
}
