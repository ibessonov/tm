package ibessonov.tm;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    public enum Direction { L, S, R }

    private static Direction[] directions = Direction.values();

    /**
     * Command for Turing Machine
     * @param <State>  enum type of states
     * @param <Letter> enum state of letters
     */
    public static class Command<State extends Enum<State>, Letter extends Enum<Letter>> {

        public final State fromState;
        public final Letter fromLetter;

        public final State toState;
        public final Letter toLetter;

        public final Direction direction;

        private Command(State fromState, Letter fromLetter, State toState, Letter toLetter, Direction direction) {
            this.fromState  = fromState;
            this.fromLetter = fromLetter;
            this.toState    = toState;
            this.toLetter   = toLetter;
            this.direction  = direction;
        }

        /**
         * Static constructor for the command (q, a) -> (qs, b, X)
         * @param fromState  is "q"  from the definition above
         * @param fromLetter is "a"  from the definition above
         * @param toState    is "qs" from the definition above
         * @param toLetter   is "b"  from the definition above
         * @param direction  is "X"  from the definition above
         * @param <State>    enum type of states
         * @param <Letter>   enum type of letters
         * @return
         */
        public static <State extends Enum<State>, Letter extends Enum<Letter>>
        Command<State, Letter> command(
                State fromState, Letter fromLetter, State toState, Letter toLetter, Direction direction
        ) {
            return new Command<>(fromState, fromLetter, toState, toLetter, direction);
        }
    }

    /**
     * Turing Machine. Basically an array of {@link Command<State, Letter>} objects
     * @param <State>  enum type of states
     * @param <Letter> enum type of letters
     */
    public static final class TM<State extends Enum<State>, Letter extends Enum<Letter>> {

        private final Command<State, Letter>[] commands;
        private final State[] states;
        private final Letter[] letters;

        @SafeVarargs
        private TM(Command<State, Letter> ...commands) {
            this.commands = commands.clone();
            this.states = commands[0].fromState.getDeclaringClass().getEnumConstants();
            this.letters = commands[0].fromLetter.getDeclaringClass().getEnumConstants();
        }

        /**
         * Static constructor for Turing Machine
         * @param commands array of commands
         * @param <State>  enum type of states
         * @param <Letter> enum type of letters
         */
        @SafeVarargs
        public static <State extends Enum<State>, Letter extends Enum<Letter>>
        TM<State, Letter> tm(Command<State, Letter> ...commands) {
            return new TM<>(commands);
        }

        /**
         * Execute current Turing Machine
         * @param log whether this method should output execution process or not
         * @param startingState starting state of the machine
         * @param startingTape machine's input
         * @return true if machine stopped at "Halt" state. False otherwise.<br>
         *     Last element of the {@link State} enum values array is always interpreted as "Halt" state.<br>
         *     Last element of the {@link Letter} enum values array is always interpreted as "Hash" letter.<br>
         */
        @SafeVarargs
        public final boolean run(boolean log, State startingState, Letter... startingTape) {
            @SuppressWarnings("unchecked")
            Command<State, Letter>[][] c = new Command[states.length][letters.length];
            for (Command<State, Letter> command : commands) {
                c[command.fromState.ordinal()][command.fromLetter.ordinal()] = command;
            }

            List<Letter> tape = new ArrayList<>(startingTape.length + 2);
            tape.add(getHashLetter());
            tape.addAll(Arrays.asList(startingTape));
            tape.add(getHashLetter());

            int index = 0;
            State state = startingState;
            while (true) {
                if (log) {
                    System.out.println();
                    System.out.println("State: " + state);
                    System.out.print("Tape: [");
                    for (int i = 0; i < tape.size(); i++) {
                        Letter letter = tape.get(i);
                        if (i == index) {
                            System.out.print("<" + letter + "> ");
                        } else {
                            System.out.print(letter + " ");
                        }
                    }
                    System.out.println("]");
                }
                if (isHaltState(state)) {
                    return true;
                }

                Command<State, Letter> command = c[state.ordinal()][tape.get(index).ordinal()];
                if (command == null) {
                    if (log) {
                        System.out.println();
                        System.out.println("<<< Unable to continue >>>");
                    }
                    return false;
                }

                state = command.toState;
                tape.set(index, command.toLetter);
                switch (command.direction) {
                    case L: {
                        index--;
                        if (index < 0) {
                            tape.add(0, getHashLetter());
                            index = 0;
                        }
                        break;
                    }
                    case R: {
                        index++;
                        if (index == tape.size()) {
                            tape.add(getHashLetter());
                        }
                        break;
                    }
                }
            }
        }

        /**
         * Generate list of interfaces that describe Subtyping Machine corresponding to the current Turing Machine
         * @param padding string that will be prepended to every output line
         * @param z       class that should be used as terminal element in places like this: {@code E<? super E<? super Z>>}
         * @param out     output stream to write interfaces to
         */
        public void generateInterfaces(String padding, String z, PrintStream out) {
            MyWriter w = new MyWriter(padding, out);
            w.println("public interface N<X> {}");
            w.println("public interface ML<X> {}");
            w.println("public interface MR<X> {}");
            w.println();

            for (Letter letter : letters) {
                w.println("public interface L{0}<X> {1}", letter.name(), "{}");
            }
            w.println();

            w.println("public interface E<X> extends");
            for (State state : states) {
                w.println("        QLR{0}<N<? super QWR{0}<? super E<? super E<? super X>>>>>,", state.name());
                w.println("        QRL{0}<N<? super QWL{0}<? super E<? super E<? super X>>>>>{1}",
                        state.name(), isHaltState(state) ? " {}" : ",");
            }

            for (State state : states) {
                for (int i = 0; i < 2; i++) {
                    Direction m0 = directions[2 * i];
                    Direction m1 = directions[2 * i ^ 2];

                    w.println();
                    w.println("public interface Q{0}{1}{2}<X> {3}", m0, m1, state.name(), "{}");

                    w.println("public interface QW{0}{2}<X> extends", m0, m1, state.name());
                    w.println("        M{0}<N<? super Q{0}{2}<? super X>>>,", m0, m1, state.name());
                    w.println("        M{1}<N<? super QW{0}{2}<? super M{1}<? super N<? super X>>>>>,", m0, m1, state.name());
                    for (Letter letter : letters) {
                        w.println("        L{3}<N<? super QW{0}{2}<? super L{3}<? super N<? super X>>>>>,", m0, m1, state.name(), letter.name());
                    }
                    if (isHaltState(state)) {
                        w.println("        E<E<? super {0}>> {1}", z, "{}");
                    } else {
                        w.println("        E<Q{0}{1}{2}<? super N<? super X>>> {3}", m0, m1, state.name(), "{}");
                    }
                }
            }

            for (State state : states) {
                List<Command<State, Letter>> cs = Stream.of(commands).filter(c -> c.fromState == state).collect(Collectors.toList());
                if (cs.isEmpty()) {
                    w.println();
                    w.println("public interface QL{0}<X> {1}", state.name(), "{}");
                    w.println("public interface QR{0}<X> {1}", state.name(), "{}");
                } else {
                    w.println();
                    for (int i = 0; i < 2; i++) {
                        Direction m0 = directions[2 * i];
                        Direction m1 = directions[2 * i ^ 2];
                        w.println("public interface Q{0}{2}<X> extends", m0, m1, state.name());

                        Command<State, Letter> lastCommand = cs.get(cs.size() - 1);
                        for (Command<State, Letter> command : cs) {
                            String end = command == lastCommand ? " {}" : ",";
                            if (command.direction == Direction.S) {
                                if (isHashLetter(command.fromLetter)) {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super L{2}<? super N<? super M{1}<? super N<? super L{4}<? super N<? super X>>>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                } else {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super M{1}<? super N<? super L{4}<? super N<? super X>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                }
                            } else if (command.direction == m0) {
                                if (isHashLetter(command.fromLetter)) {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super L{2}<? super N<? super M{0}<? super N<? super L{4}<? super N<? super X>>>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                } else {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super M{0}<? super N<? super L{4}<? super N<? super X>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                }
                            } else {
                                if (isHashLetter(command.fromLetter)) {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super L{2}<? super N<? super L{4}<? super N<? super M{1}<? super N<? super X>>>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                } else {
                                    w.println("        L{2}<N<? super QW{0}{3}<? super L{4}<? super N<? super M{1}<? super N<? super X>>>>>>>{5}",
                                            m0, m1, command.fromLetter.name(), command.toState.name(), command.toLetter.name(), end);
                                }
                            }
                        }
                    }
                }
            }
        }

        /**
         * Generate list of interfaces that describe Subtyping Machine corresponding to the current Turing Machine
         * @param padding string that will be prepended to every output line
         * @param z       class that should be used as terminal element in places like this: {@code E<? super E<? super Z>>}
         * @param out     output stream to write interfaces to
         */
        /**
         * Generate builder class and list of interfaces that describe Subtyping Machine corresponding to the current Turing Machine
         * @param name          name of builder class
         * @param z             class that should be used as terminal element in places like this: {@code E<? super E<? super Z>>}
         * @param startingState starting state of the machine
         * @param out           output stream to write interfaces to
         * @param letters       list of letters that should be available as methods in builder class
         */
        @SafeVarargs
        public final void generateBuilder(String name, String z, State startingState, PrintStream out, Letter... letters) {
            MyWriter w = new MyWriter("", out);
            Letter hash = getHashLetter();

            w.println("abstract class {0}<X> {1}", name, "{");

            w.println();
            w.println("    public static void main(String[] args) {");
            w.println("//        E<? super E<? super {0}>> e = start.A().B().stop();", z);
            w.println("    }");

            w.println();
            w.println("    static {0}<ML<? super N<? super L{1}<? super N<? super E<? super E<? super {2}>>>>>>> start;",
                    name, hash.name(), z);
            w.println("    abstract QWR{0}<? super L{1}<? super N<? super X>>> stop();", startingState.name(), hash.name());

            w.println();
            for (Letter letter : letters) {
                w.println("    abstract {0}<L{1}<? super N<? super X>>> {1}();", name, letter.name());
            }

            w.println();
            generateInterfaces("    ", z, out);
            w.println("}");
        }

        private static class MyWriter {

            private final String padding;
            private final PrintStream out;

            public MyWriter(String padding, PrintStream out) {
                this.padding = padding;
                this.out = out;
            }

            public void println() {
                out.println();
            }

            public void println(String str) {
                out.println(padding + str);
            }

            public void println(String pattern, Object... args) {
                out.println(padding + MessageFormat.format(pattern, args));
            }
        }

        private Letter getHashLetter() {
            return letters[letters.length - 1];
        }

        private boolean isHaltState(State state) {
            return state.ordinal() + 1 == states.length;
        }

        private boolean isHashLetter(Letter letter) {
            return letter.ordinal() == letters.length;
        }
    }
}
