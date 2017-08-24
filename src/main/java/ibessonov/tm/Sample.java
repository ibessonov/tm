package ibessonov.tm;

import java.util.List;

public class Sample {

    interface BadList<T> extends List<List<? super BadList<? super T>>> {}

    public static void main(String[] args) {
        BadList<? super String> badList = null;
//        List<? super BadList<? super String>> list = badList; // this will throw StackOverflowError in compiler
    }
}
