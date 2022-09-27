package task.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SessionInfo
{
    private static final Logger log = LoggerFactory.getLogger(SessionInfo.class.getName());

    public static int ACTIVE_USER;

    private static final String PROPERTIES_FILE = "\\main.properties";

    public SessionInfo() throws BusinessLogicException
    {
        ACTIVE_USER = PropertiesUtility.getIntegerOrDefault(PropertiesUtility.loadProperties(PROPERTIES_FILE)
                .getProperty("active.user"), 0);
        if (ACTIVE_USER == 0)
        {
            throw new BusinessLogicException("Can't find active user for session");
        }
        log.info("Create session with user={}", ACTIVE_USER);
    }

    public void changeActiveUser(int newUserId)
    {
        if (newUserId <= 0 || Objects.equals(newUserId, ACTIVE_USER))
        {
            return;
        }
        log.info("Active user changed from [{}] to [{}]", ACTIVE_USER, newUserId);
        ACTIVE_USER = newUserId;
    }
}
