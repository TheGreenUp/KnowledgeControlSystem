package command;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class CommandDto implements Serializable {
    protected Command command;
}
