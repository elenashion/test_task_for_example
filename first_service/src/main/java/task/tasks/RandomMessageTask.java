package task.tasks;

import task.common.SessionInfo;
import task.services.MessagesService;

public class RandomMessageTask extends AbstractTask
{
    private final MessagesService service;

    public RandomMessageTask(boolean createTask, int timeDelay, int period, MessagesService service)
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
        service.createMessage(createRandomMessage());
    }

    private static String createRandomMessage()
    {
        try
        {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i <= Math.random() * 1000; i++)
            {
                int r = Double.valueOf(Math.random() * 100).intValue();
                s.append(Character.toString(r % 10 == 0 ? r * 2 : r % 5 == 0 ? r * 5 : r % 2 == 0 ? r : r * 10));
            }
            return s.toString();
        }
        catch (Exception e)
        {
            return ("I'm just string");
        }
    }
}
