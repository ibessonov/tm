package ibessonov.tm;

import ibessonov.tm.Generator.TM;

import static ibessonov.tm.Generator.Command.command;
import static ibessonov.tm.Generator.Direction.*;
import static ibessonov.tm.Generator.TM.tm;
import static ibessonov.tm.Main.Letter.*;
import static ibessonov.tm.Main.State.*;

public class Main {

    enum State {
        Start,
        Scan,
        Back,
        Check,
        Halt
    }

    enum Letter {
        A,
        B,
        x {
            @Override
            public String toString() {
                return "*";
            }
        },
        $ {
            @Override
            public String toString() {
                return "#";
            }
        }
    }

    public static void main(String[] args) {
        TM<State, Letter> tm = tm(
            command(Start, $, Scan, $, R),

            command(Scan, B, Back, x, L),
            command(Scan, $, Check, $, L),
            command(Scan, A, Scan, A, R),
            command(Scan, x, Scan, x, R),

            command(Back, A, Scan, x, R),
            command(Back, B, Back, B, L),
            command(Back, x, Back, x, L),

            command(Check, $, Halt, $, S),
            command(Check, x, Check, x, L)
        );
        tm.run(true, Start, A, A, B, A, B, B);
//        tm.generateBuilder("ABBuilder","String", Start, System.out, A, B); // result is already present in "ABBuilder.java" file
    }
}
