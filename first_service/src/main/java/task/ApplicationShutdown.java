package task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;
import task.services.UsersService;
import task.tasks.TasksManager;

@Component
public class ApplicationShutdown implements ApplicationListener<ContextClosedEvent>
{
    private static final Logger log = LoggerFactory.getLogger(ApplicationShutdown.class.getName());

    @Autowired
    private UsersService usersService;

    @Autowired
    TasksManager tasksManager;

    @Override
    public void onApplicationEvent(ContextClosedEvent event)
    {
        usersService.sendActiveStatus(false);
        log.debug("Stop user activity message sended");
        tasksManager.stopAllTasks();
        log.debug("All tasks stoped");
    }
}
