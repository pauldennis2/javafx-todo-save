package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    public static String FILE_NAME = "todos.json";

    ObservableList<TodoItem> todoItems = FXCollections.observableArrayList();

    @FXML
    ListView todoList;

    @FXML
    TextField todoText;


    @Override
    public void initialize (URL url, ResourceBundle resources) {
        initializeTodosFromFile(FILE_NAME);
        todoList.setItems(todoItems);
    }

    public void addItem() {
        String text = todoText.getText();
        System.out.println("Adding item *" + text + "*");
        todoItems.add(new TodoItem(text));
        todoText.setText("");
    }

    public void removeItem() {
        System.out.println("in removegu");
        TodoItem todoItem = (TodoItem)todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            System.out.println("Removing " + todoItem.text + " ...");
            todoItems.remove(todoItem);
        }
    }

    public void toggleItem() {
        System.out.println("Toggling item ...");
        TodoItem todoItem = (TodoItem)todoList.getSelectionModel().getSelectedItem();
        if (todoItem != null) {
            todoItem.isDone = !todoItem.isDone;
            todoList.setItems(null);
            todoList.setItems(todoItems);
        }
    }

    public void initializeTodosFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            String jsonFileString = fileScanner.nextLine();
            TodoList todoList = jsonRestore(jsonFileString);
            for (TodoItem item : todoList.getTodoList()) {
                todoItems.add(item);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found for some bizarre reason.");
        }
    }

    public void writeTodosToFile (String fileName) {
        try {
            File todoFile = new File (fileName);
            FileWriter fileWriter = new FileWriter(todoFile);
            TodoList wrappedList = new TodoList();
            for (TodoItem item : todoItems) {
                wrappedList.add(item);
            }
            String jsonString = jsonSave(wrappedList);
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String jsonSave(TodoItem todoToSave) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(todoToSave);

        return jsonString;
    }

    /*public TodoItem jsonRestore(String jsonTD) {
        JsonParser toDoItemParser = new JsonParser();
        TodoItem item = toDoItemParser.parse(jsonTD, TodoItem.class);

        return item;
    }*/

    /*public String jsonSave(ObservableList<TodoItem> list) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(list);
        return jsonString;
    }

    public ObservableList<TodoItem> jsonRestore (String jsonList) {
        JsonParser listParser = new JsonParser();
        ObservableList<TodoItem> list = listParser.parse(jsonList, ObservableList.class);
        return list;
    }*/

    public String jsonSave(TodoList list) {
        JsonSerializer jsonSerializer = new JsonSerializer().deep(true);
        String jsonString = jsonSerializer.serialize(list);
        return jsonString;
    }

    public TodoList jsonRestore (String jsonTodoList) {
        JsonParser listParser = new JsonParser();
        TodoList list = listParser.parse(jsonTodoList, TodoList.class);
        return list;
    }
}
