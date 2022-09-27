package task.tasks;

import org.springframework.stereotype.Service;
import task.common.PropertiesUtility;
import task.services.MessagesService;
import task.services.UsersService;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TasksManager
{
    private static final String PROPERTIES_FILE = "\\periodic_tasks.properties";

    private final Map<String, AbstractTask> tasksByClassName = new ConcurrentHashMap<>();

    public TasksManager(UsersService usersService, MessagesService messagesService)
    {
        Properties properties = PropertiesUtility.loadProperties(PROPERTIES_FILE);

        boolean create = PropertiesUtility.getBooleanOrDefault(properties.getProperty("user.activity.create"), true);
        int delay = PropertiesUtility.getIntegerOrDefault(properties.getProperty("user.activity.delay"), 30);
        int period = PropertiesUtility.getIntegerOrDefault(properties.getProperty("user.activity.period"), 60);
        SendUserStatusTask sendUserStatusTask = new SendUserStatusTask(create, delay, period, usersService);
        tasksByClassName.put(SendUserStatusTask.class.getName(), sendUserStatusTask);

        create = PropertiesUtility.getBooleanOrDefault(properties.getProperty("message.send.create"), true);
        delay = PropertiesUtility.getIntegerOrDefault(properties.getProperty("message.send.delay"), 60);
        period = PropertiesUtility.getIntegerOrDefault(properties.getProperty("message.send.period"), 30);
        RandomMessageTask randomMessageTask = new RandomMessageTask(create, delay, period, messagesService);
        tasksByClassName.put(RandomMessageTask.class.getName(), randomMessageTask);

    }

    public void stopAllTasks()
    {
        for (AbstractTask task : tasksByClassName.values())
        {
            task.stopTask();
        }
    }
}
