package task.services;

import org.springframework.stereotype.Service;

@Service
public class UsersService extends BaseService
{
    public UsersService()
    {
        super();
    }

    public boolean sendActiveStatus(boolean isActive)
    {
        return executeWithUserIdParameter(isActive);
    }

}
