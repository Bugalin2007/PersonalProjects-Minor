package practice.DeepSeekOptimized;

import java.util.*;

public class paperScissorRock {
    private static final Scanner sc = new Scanner(System.in);
    private static final Random random = new Random();

    public enum Gesture {
        ROCK(0, "Rock"),
        PAPER(1, "Paper"),
        SCISSOR(2, "Scissor");

        private final int value;
        private final String displayName;

        Gesture(int value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }

        public int getValue() { return value; }
        public String getDisplayName() { return displayName; }

        private static final Map<String, Gesture> ALIAS_MAP = new HashMap<>();

        static {
            // 初始化别名映射
            registerAliases(ROCK, "rock", "rocks", "stone", "0");
            registerAliases(PAPER, "paper", "papers", "sheet", "1");
            registerAliases(SCISSOR, "scissor", "scissors", "shear", "shears", "2");
        }

        private static void registerAliases(Gesture gesture, String... aliases) {
            for (String alias : aliases) {
                ALIAS_MAP.put(alias.toLowerCase(), gesture);
            }
        }

        public static Optional<Gesture> fromInput(String input) {
            return Optional.ofNullable(ALIAS_MAP.get(input.toLowerCase()));
        }

        public static Gesture getRandom() {
            return values()[random.nextInt(values().length)];
        }

        public Result compareWith(Gesture other) {
            if (this == other) return Result.TIE;

            return switch (this) {
                case ROCK -> other == SCISSOR ? Result.WIN : Result.LOSE;
                case PAPER -> other == ROCK ? Result.WIN : Result.LOSE;
                case SCISSOR -> other == PAPER ? Result.WIN : Result.LOSE;
            };
        }
    }

    public enum Result {
        WIN("You win!"), LOSE("You lose!"), TIE("You tie!");

        private final String message;

        Result(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter your gesture (rock/paper/scissor): ");
        String input = sc.nextLine().trim();

        Gesture playerGesture = Gesture.fromInput(input)
                .orElseGet(() -> {
                    System.out.println("Invalid gesture! Using Rock as default.");
                    return Gesture.ROCK;
                });

        Gesture computerGesture = Gesture.getRandom();

        System.out.println("Player: " + playerGesture.getDisplayName());
        System.out.println("Computer: " + computerGesture.getDisplayName());

        Result result = playerGesture.compareWith(computerGesture);
        System.out.println(result.getMessage());
    }
}
