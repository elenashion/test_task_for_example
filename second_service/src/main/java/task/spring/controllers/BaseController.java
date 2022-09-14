package task.spring.controllers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import task.helpers.AbstractHelper;

import java.util.Optional;
import java.util.function.Function;

public class BaseController
{
    public <T> ResponseEntity<String> requestWithIdParameter(Integer id, AbstractHelper<T> helper, Class<T> entityClass,
        Function<T, ResponseEntity<String>> function)
    {
        try
        {
            T entity = helper.getById(id);
            if (entity == null)
            {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return function.apply((T) entity);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
