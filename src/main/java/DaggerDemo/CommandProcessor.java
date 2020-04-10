package DaggerDemo;

import DaggerDemo.base.Command;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayDeque;
import java.util.Deque;

@Singleton
public class CommandProcessor {
    private final Deque<CommandRouter> commandRouterStack = new ArrayDeque<>();

    @Inject
    public CommandProcessor(CommandRouter firstCommandRouter){
        commandRouterStack.push(firstCommandRouter);
    }

    Command.Status process(String input) {
        Command.Result result = commandRouterStack.peek().route(input);
        if (result.status().equals(Command.Status.INPUT_COMPLETED)) {
            commandRouterStack.pop();
            return commandRouterStack.isEmpty()
                    ? Command.Status.INPUT_COMPLETED
                    : Command.Status.HANDLED;
        }

        result.nestedCommandRouter().ifPresent(commandRouterStack::push);
        return result.status();
    }
}
