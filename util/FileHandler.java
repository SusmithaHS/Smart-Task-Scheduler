package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import core.Task;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

public class FileHandler {

    public static void saveTasks(List<Task> tasks, String filename) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter(filename);
        gson.toJson(tasks, writer);
        writer.close();
    }

    public static PriorityQueue<Task> LoadedTasks(String filename) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader(filename);
        Type listType = new TypeToken<List<Task>>() {}.getType();
        List<Task> taskList = gson.fromJson(reader, listType);
        reader.close();
        return new PriorityQueue<>(taskList); 
    }
}
