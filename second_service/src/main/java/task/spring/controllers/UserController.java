package task.spring.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.entities.User;
import task.entities.enums.UserState;
import task.helpers.UsersHelper;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController
{
    private final UsersHelper usersHelper;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JodaModule());

    @Autowired
    public UserController(UsersHelper usersHelper)
    {
        this.usersHelper = usersHelper;
    }

    @GetMapping("/getListOfUsers")
    public ResponseEntity<String> getListOfUsers()
    {
        try
        {
            return new ResponseEntity<>(mapper.writeValueAsString(usersHelper.getUsers(false)),
                    HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getListOfActiveUsers")
    public ResponseEntity<String> getListOfActiveUsers()
    {
        try
        {
            return new ResponseEntity<>(mapper.writeValueAsString(usersHelper.getUsers(true)),
                    HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id)
    {
        return requestWithIdParameter(id, usersHelper, User.class, user ->
        {
            if (UserState.ACTIVE.equals(user.getState()))
            {
                usersHelper.changeUserState(user, UserState.ARCHIVED);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        });
    }

}
