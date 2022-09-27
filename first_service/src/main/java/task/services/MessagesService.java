package task.services;

import org.springframework.stereotype.Service;

@Service
public class MessagesService extends BaseService
{
    public MessagesService()
    {
        super();
    }

    public void createMessage(String message)
    {
        executeWithUserIdParameter(message);
    }
}
