package sample;

/**
 * Created by Paul Dennis on 1/12/2017.
 */
public class TodoItem {

    public static final String CHECK_MARK = "\u2714";

    String text;
    boolean isDone;

    public TodoItem () {

    }

    public TodoItem(String text) {
        this.text = text;
        this.isDone = false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return text + CHECK_MARK;
        } else {
            return text;
        }
        // A one-line version of the logic above:
        // return text + (isDone ? " (done)" : "");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
