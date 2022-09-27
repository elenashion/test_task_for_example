package task.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task.services.MessagesService;
import task.services.UsersService;
import task.tasks.TasksManager;

@Configuration
public class BeanConfiguration
{
    @Bean
    public TasksManager tasksManager(UsersService usersService, MessagesService messagesService)
    {
        return new TasksManager(usersService, messagesService);
    }
}
