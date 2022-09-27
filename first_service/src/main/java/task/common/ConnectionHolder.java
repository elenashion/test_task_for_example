package task.common;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ConnectionHolder
{
    private static final Logger log = LoggerFactory.getLogger(ConnectionHolder.class.getName());

    private static final String PROPERTIES_FILE = "\\rabbitmq.properties";

    private final ConnectionFactory factory;

    private static Connection connection;

    private static volatile ConnectionHolder connectionHolder;

    private ConnectionHolder(Properties properties)
    {
        factory = new ConnectionFactory();
        factory.setHost(properties.getProperty("rabbitmq.host"));
        factory.setUsername(properties.getProperty("rabbitmq.username"));
        factory.setPassword(properties.getProperty("rabbitmq.password"));
        factory.setPort(PropertiesUtility.getIntegerOrDefault(properties.getProperty("rabbitmq.port"), 9401));
        factory.setAutomaticRecoveryEnabled(true);
        createConnect();
    }

    private void createConnect()
    {
        try
        {
            connection = factory.newConnection();
        }
        catch (Exception e)
        {
            log.error("Can't create new connection cause={}", e.getCause().toString());
        }
    }

    public static ConnectionHolder getConnection()
    {
        if (connectionHolder == null)
        {
            synchronized (ConnectionHolder.class)
            {
                if (connectionHolder == null)
                {
                    connectionHolder = new ConnectionHolder(getProperties());
                }
            }
        }
        return connectionHolder;
    }

    private static Properties getProperties()
    {
        return PropertiesUtility.loadProperties(PROPERTIES_FILE);
    }

    public Channel createChannel()
    {
        try
        {
            return connection.createChannel();
        }
        catch (Exception e)
        {
            log.error("Can't create new channel cause={}", e.getCause().toString());
        }
        return null;
    }

}
