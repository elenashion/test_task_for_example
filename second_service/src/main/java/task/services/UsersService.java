package task.services;

import org.joda.time.Instant;
import task.helpers.UsersHelper;

public class UsersService
{
    private final UsersHelper usersHelper;

    public UsersService(UsersHelper usersHelper)
    {
         this.usersHelper = usersHelper;
    }

    public void sendActiveStatus(Integer userId, Boolean isActive)
    {
        usersHelper.changeUserActivity(isActive, userId, Instant.now());
    }

}
