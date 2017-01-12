package sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul Dennis on 1/12/2017.
 */
public class TodoList {

    List<TodoItem> todoList;

    public TodoList () {
        todoList = new ArrayList<>();
    }

    public void add (TodoItem item) {
        todoList.add(item);
    }

    public List<TodoItem> getTodoList () {
        return todoList;
    }

    public void setTodoList (List<TodoItem> todoList) {
        this.todoList = todoList;
    }
}
