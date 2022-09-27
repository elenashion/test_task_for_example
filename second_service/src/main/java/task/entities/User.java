package task.entities;

import lombok.*;
import org.joda.time.Instant;
import task.entities.common.InstantToDateColumnConverter;
import task.entities.common.UserStateEnumConverter;
import task.entities.enums.UserState;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "users", schema = "crud")
public class User extends IntBaseEntity
{
    @Getter
    @Setter
    @Column(name = "state", nullable = false)
    @Convert(converter = UserStateEnumConverter.class)
    private UserState state;

    @Getter
    @Setter
    @Column(name = "last_activity", nullable = false)
    @Convert(converter = InstantToDateColumnConverter.class)
    private Instant timeOfLastActivity = Instant.now();

    public User(UserState state)
    {
        this.state = state;
    }

    @Override
    public String toString()
    {
        return "[id=" + getId() + ", state=" + getState() +
                ", timeOfLastActivity=" + getTimeOfLastActivity() + "]";
    }

}
