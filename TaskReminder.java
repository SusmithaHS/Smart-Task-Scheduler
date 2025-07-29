import core.Task;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TaskReminder {

    public static void setReminder(final Task task) {
        Date deadline = task.getDeadline();
        long delay = deadline.getTime() - System.currentTimeMillis();

        if (delay > 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("🔔 Reminder: " + task.getTitle() + " is due today!");
                }
            }, delay);
        }
    }
}