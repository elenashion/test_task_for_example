package task.entities.common;

import task.entities.enums.UserState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStateEnumConverter implements AttributeConverter<UserState, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(UserState attribute)
    {
        return attribute.getNumber();
    }

    @Override
    public UserState convertToEntityAttribute(Integer dbData)
    {
        for (UserState state : UserState.values())
        {
            if (state.getNumber() == dbData)
            {
                return state;
            }
        }
        return null;
    }
}
