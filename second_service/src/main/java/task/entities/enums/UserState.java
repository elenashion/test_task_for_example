package task.entities.enums;

import lombok.Getter;

public enum UserState
{
    ACTIVE(0),
    ARCHIVED(1)
    ;

    @Getter
    private final int number;

    UserState(int number)
    {
        this.number = number;
    }
}
