package task.entities.common;

import org.joda.time.Instant;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Date;

@Converter
public class InstantToDateColumnConverter implements AttributeConverter<Instant, Date>
{
    @Override
    public Date convertToDatabaseColumn(Instant attribute)
    {
        return attribute != null ? attribute.toDate() : null;
    }

    @Override
    public Instant convertToEntityAttribute(Date dbData)
    {
        return dbData != null ? new Instant(dbData) : null;
    }
}
