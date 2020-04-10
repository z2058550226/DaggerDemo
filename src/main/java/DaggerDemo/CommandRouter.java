package DaggerDemo;

import DaggerDemo.base.Command;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CommandRouter {
    private final Map<String, Command> commands;

    // The @Inject annotation indicates to Dagger that when we ask for a CommandRouter,
    // Dagger should call new CommandRouter().
    // 这里有两个术语：在实例化CommandRouter的时候会请求(request)HelloWorldCommand, 或者叫CommandRouter
    // 依赖(depend on)HelloWorldCommand
    @Inject
    CommandRouter(Map<String, Command> commands) {
        // This map contains:
        // "hello" -> HelloWorldCommand
        // "login" -> LoginCommand
        this.commands = commands;
    }

    Command.Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        Command.Result result =
                command.handleInput(splitInput.subList(1, splitInput.size()));
        if (result.status() == Command.Status.INVALID) {
            System.out.println(commandKey + ": invalid arguments");
        }
        return result;
    }

    private Command.Result invalidCommand(String input) {
        System.out.println(
                String.format("couldn't understand \"%s\". please try again.", input));
        return Command.Result.invalid();
    }

    // Split on whitespace
    private static List<String> split(String string) {
        return Arrays.asList(string.split(" "));
    }
}
