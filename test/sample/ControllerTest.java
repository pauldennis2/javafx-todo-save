package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Paul Dennis on 1/12/2017.
 */
public class ControllerTest {

    Controller controller;
    @Before
    public void setUp() throws Exception {
        controller = new Controller();
    }

    @After
    public void tearDown() throws Exception {

    }

    /*@Test
    public void testJsonSerialization () {
        String jsonString = controller.jsonSave(new TodoItem("Unit Testing"));
        TodoItem restoredItem = controller.jsonRestore(jsonString);
        assertNotNull(restoredItem);
        assertEquals("Unit Testing", restoredItem.getText());
    }*/

   /*@Test
    public void testListJsonSerialization () {
        ObservableList<TodoItem> list = FXCollections.observableArrayList();
        list.add(new TodoItem("Unit Test List"));
        String jsonString = controller.jsonSave(list);
        ObservableList<TodoItem> restoredList = controller.jsonRestore(jsonString);
        assertNotNull(restoredList);
        assertEquals("Unit Test List", restoredList.get(0).getText());
    }*/

    @Test
    public void testWrappedListJsonSerialization () {
        String text = "Unit Test List";
        TodoList todoList = new TodoList();
        todoList.add(new TodoItem(text));
        String jsonString = controller.jsonSave(todoList);
        TodoList restoredList = controller.jsonRestore(jsonString);
        assertNotNull(restoredList);
        assertEquals(text, restoredList.getTodoList().get(0).getText());
    }
}