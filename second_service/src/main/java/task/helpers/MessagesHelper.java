package task.helpers;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.entities.Message;
import task.entities.User;
import task.spring.repositories.MessageRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class MessagesHelper extends AbstractHelper<Message>
{
    private final MessageRepository messageRepository;

    private UsersHelper usersHelper;

    @Autowired
    public MessagesHelper(MessageRepository messageRepository, UsersHelper usersHelper)
    {
        this.messageRepository = messageRepository;
        this.usersHelper = usersHelper;
    }

    public Set<Message> getMessagesByUserId(Integer userId)
    {
        if (userId == null)
        {
            return Collections.emptySet();
        }
        Set<Message> messages = messageRepository.findByUserId(userId);
        if (messages == null)
        {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(messages);
    }

    public void deleteMessage(Message message)
    {
        if (message == null || message.getId() == 0)
        {
            return;
        }
        messageRepository.deleteById(message.getId());
    }

    public void createMessage(Integer userId, String message)
    {
        if (message == null)
        {
            return;
        }
        Instant createTime = Instant.now();
        User user = usersHelper.changeUserActivity(true, userId, createTime);
        Message newMessage = new Message(user, message, createTime);
        messageRepository.save(newMessage);
    }

    @Override
    public Message getById(Integer id)
    {
        return messageRepository.findById(id).get();
    }
}
