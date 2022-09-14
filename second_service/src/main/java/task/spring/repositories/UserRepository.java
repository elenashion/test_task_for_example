package task.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import task.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>
{
//    List<User>
}
