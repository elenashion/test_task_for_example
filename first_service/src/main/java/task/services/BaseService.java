package task.services;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task.common.ConnectionHolder;

public class BaseService
{
    private static final Logger log = LoggerFactory.getLogger(BaseService.class.getName());

    private final ConnectionHolder connection;

    public BaseService()
    {
        this.connection = ConnectionHolder.getConnection();
    }

    protected <T> T executeWithUserIdParameter(Object ... parameters)
    {
        try (Channel channel = connection.createChannel())
        {
            // TODO
            return (T) channel.asyncCompletableRpc(null).get();
        }
        catch (Exception e)
        {
            log.error("Error while executing method");
        }
        return null;
    }
}
