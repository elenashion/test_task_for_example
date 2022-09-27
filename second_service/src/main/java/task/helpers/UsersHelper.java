package task.helpers;

import org.joda.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.entities.User;
import task.entities.enums.UserState;
import task.spring.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsersHelper extends AbstractHelper<User>
{
    private final Map<Integer, User> allUsersById = new ConcurrentHashMap<>();
    private final Map<Integer, User> activeUsersById = new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    @Autowired
    public UsersHelper(UserRepository userRepository)
    {
        this.userRepository = userRepository;
        loadUsers();
    }

    private void loadUsers()
    {
        for (User user : userRepository.findAll())
        {
            allUsersById.put(user.getId(), user);
        }
    }

    public Collection<User> getUsers(boolean onlyActive)
    {
        if (onlyActive)
        {
            return Collections.unmodifiableCollection(activeUsersById.values());
        }
        else
        {
            return Collections.unmodifiableCollection(allUsersById.values());
        }
    }

    public void changeUserState(User user, UserState state)
    {
        if (user == null || state == null)
        {
            return;
        }
        synchronized (user)
        {
            if (state.equals(user.getState()))
            {
                return;
            }
            user.setState(state);
            userRepository.save(user);
        }
    }

    public User changeUserActivity(boolean isActive, Integer id, Instant activityTime)
    {
        User user = allUsersById.get(id);
        if (user == null && isActive)
        {
            user = createUser();
        }
        if (user != null)
        {
            synchronized (user)
            {
                if (UserState.ARCHIVED.equals(user.getState()) && isActive)
                {
                    return null;
                }
                if (isActive)
                {
                    user.setTimeOfLastActivity(activityTime);
                    user = userRepository.save(user);
                    activeUsersById.put(user.getId(), user);
                }
                else
                {
                    activeUsersById.remove(user.getId());
                }
            }
        }
        return user;
    }

    public User createUser()
    {
        User user = new User(UserState.ACTIVE);
        user = userRepository.save(user);
        allUsersById.put(user.getId(), user);
        return user;
    }

    @Override
    public User getById(Integer id)
    {
        return allUsersById.get(id);
    }


}
