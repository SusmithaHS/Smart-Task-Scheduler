package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import core.Task;
import core.TaskManager;
import util.FileHandler;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class MainUIController {

    @FXML private TextField titleField;
    @FXML private ComboBox<String> priorityBox;
    @FXML private TextField deadlineField; 
    @FXML private ListView<Task> taskListView;

    private TaskManager taskManager = new TaskManager();
    private ObservableList<Task> displayedTasks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        priorityBox.getItems().addAll("1", "2", "3");
        taskListView.setItems(displayedTasks);
    }

    @FXML
    public void handleAddTask() {
        String title = titleField.getText();
        String selectedPriority = priorityBox.getValue();
        String deadlineText = deadlineField.getText();

        if (title == null || title.isEmpty() || selectedPriority == null || deadlineText == null || deadlineText.isEmpty()) {
            showAlert("All fields must be filled.");
            return;
        }

        int priority = Integer.parseInt(selectedPriority);
        Date deadline;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            deadline = formatter.parse(deadlineText);
        } catch (ParseException e) {
            showAlert("Invalid date format! Use dd-MM-yyyy");
            return;
        }

        Task task = new Task(title, priority, deadline);
        taskManager.addTask(task);
        updateTaskList();
        clearFields();
    }

    @FXML
    public void handleSaveTasks() {
        try {
            FileHandler.saveTasks(new ArrayList<>(taskManager.getAllTasks()), "tasks.json");
            showAlert("Tasks saved successfully.");
        } catch (IOException e) {
            showAlert("Error saving tasks.");
        }
    }

    @FXML
    public void handleLoadTasks() {
        try {
            PriorityQueue<Task> loadedTasks = FileHandler.LoadedTasks("tasks.json");
            taskManager.clearTasks();
            for (Task task : loadedTasks) {
                taskManager.addTask(task);
            }
            updateTaskList();
            showAlert("Tasks loaded successfully.");
        } catch (IOException e) {
            showAlert("Error loading tasks.");
        }
    }
    @FXML
public void handleDeleteTask() {
    Task selectedTask = taskListView.getSelectionModel().getSelectedItem();
    if (selectedTask != null) {
        taskManager.removeTask(selectedTask);
        updateTaskList();
    } else {
        showAlert("Please select a task to delete.");
    }
}

    private void updateTaskList() {
        displayedTasks.clear();
        displayedTasks.addAll(taskManager.getAllTasks());
    }

    private void clearFields() {
        titleField.clear();
        priorityBox.setValue(null);
        deadlineField.clear();
    }

    private void showAlert(String message) {
        System.out.println("INFO: " + message);
    }
}