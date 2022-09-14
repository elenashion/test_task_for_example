package task.services;

import org.joda.time.Instant;
import task.entities.User;
import task.entities.enums.UserState;
import task.helpers.UsersHelper;
import task.spring.repositories.UserRepository;

import java.util.Optional;

public class UsersService
{
    private UsersHelper usersHelper;

    public UsersService(UsersHelper usersHelper)
    {
         this.usersHelper = usersHelper;
    }

    public void sendActiveStatus(Boolean isActive, Integer userId)
    {
        usersHelper.changeUserActivity(isActive, userId, Instant.now());
    }

}
