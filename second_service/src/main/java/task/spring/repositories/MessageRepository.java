package task.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import task.entities.Message;

import java.util.Set;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer>
{
    Set<Message> findByUserId(Integer id);
}
