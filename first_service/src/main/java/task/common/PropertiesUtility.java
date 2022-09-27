package task.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtility
{
    private static final Logger log = LoggerFactory.getLogger(PropertiesUtility.class.getName());

    public static Properties loadProperties(String path)
    {
        Properties properties = new Properties();
        try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path))
        {
            properties.load(stream);
        }
        catch (Exception e)
        {
            log.error("Can't parse properties with path=[{}]", path);
        }
        return properties;
    }

    public static int getIntegerOrDefault(String property, int defaultProperty)
    {
        return property == null || !property.chars().allMatch(Character::isDigit) ? defaultProperty
                : Integer.parseInt(property);
    }

    public static boolean getBooleanOrDefault(String property, boolean defaultProperty)
    {
        return Boolean.TRUE.toString().equals(property) || (!Boolean.FALSE.toString().equals(property) && defaultProperty);
    }
}
