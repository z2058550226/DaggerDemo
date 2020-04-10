package DaggerDemo.base;

import DaggerDemo.CommandRouter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Logic to process some user input.
 */
public interface Command {
    /**
     * Process the rest of the command's words and do something.
     */
    Result handleInput(List<String> input);

    class Result {
        private final Status status;
        @Nullable
        private final CommandRouter nestedCommandRouter;

        private Result(Status status, @Nullable CommandRouter nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        public static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            Objects.requireNonNull(nestedCommandRouter);
            return new Result(Status.HANDLED, nestedCommandRouter);
        }

        public static Result invalid() {
            return new Result(Status.INVALID, null);
        }

        public static Result handled() {
            return new Result(Status.HANDLED, null);
        }

        public static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, null);
        }

        public Status status() {
            return this.status;
        }

        public Optional<CommandRouter> nestedCommandRouter() {
            return Optional.ofNullable(this.nestedCommandRouter);
        }
    }

    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED
    }
}