package task.services;

import task.helpers.MessagesHelper;
import task.helpers.UsersHelper;

public class MessagesService
{
    private MessagesHelper messagesHelper;

    public MessagesService(MessagesHelper messagesHelper)
    {
        this.messagesHelper = messagesHelper;
    }

    public void createMessage(Integer userId, String message)
    {
        messagesHelper.createMessage(userId, message);
    }
}
