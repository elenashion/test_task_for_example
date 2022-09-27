package task.tasks;

import task.common.SessionInfo;
import task.services.UsersService;

public class SendUserStatusTask extends AbstractTask
{
    private final UsersService service;

    public SendUserStatusTask(boolean createTask, int timeDelay, int period, UsersService service)
    {
        super(createTask, timeDelay, period);
        this.service = service;
    }

    @Override
    protected void executeTask()
    {
        if (SessionInfo.ACTIVE_USER <= 0)
        {
            return;
        }
        service.sendActiveStatus(true);
    }
}
