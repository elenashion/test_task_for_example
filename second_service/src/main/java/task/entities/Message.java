package task.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.Instant;
import task.entities.common.InstantToDateColumnConverter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", schema = "crud")
public class Message extends IntBaseEntity
{
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @Getter
    @Setter
    @Column(name = "message", nullable = false, length = 1024)
    private String message;

    @Getter
    @Setter
    @Column(name = "create_time", nullable = false)
    @Convert(converter = InstantToDateColumnConverter.class)
    private Instant createTime = Instant.now();

    @Override
    public String toString()
    {
        return "Message: [id=" + getId() + ", userId=" + getUser().getId() + ", createTime=" + getCreateTime() +
                "message=" + getMessage().substring(0, 99) + "]";
    }
}
