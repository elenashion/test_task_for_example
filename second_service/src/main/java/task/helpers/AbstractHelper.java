package task.helpers;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractHelper<T>
{
    public abstract T getById(Integer id);
}
