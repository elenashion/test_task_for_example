package task.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.entities.Message;
import task.helpers.MessagesHelper;

@RestController
@RequestMapping("/message")
public class MessageController extends BaseController
{
    private final task.helpers.MessagesHelper messagesHelper;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());

    @Autowired
    public MessageController(MessagesHelper messagesHelper)
    {
        this.messagesHelper = messagesHelper;
    }

    @GetMapping("/getMessagesByUserId")
    public ResponseEntity<String> getMessagesByUserId(@RequestParam Integer userId)
    {
        try
        {
            return new ResponseEntity<>(mapper.writeValueAsString(messagesHelper.getMessagesByUserId(userId)),
                    HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteMessageById")
    public ResponseEntity<String> deleteMessageById(@RequestParam Integer id)
    {
        return requestWithIdParameter(id, messagesHelper, Message.class, message ->
        {
            messagesHelper.deleteMessage(message);
            return new ResponseEntity<>(HttpStatus.OK);
        });
    }
}
