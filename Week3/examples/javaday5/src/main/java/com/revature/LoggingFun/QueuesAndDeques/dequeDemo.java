package com.revature.LoggingFun.QueuesAndDeques;

import java.util.ArrayDeque;
import java.util.Deque;

public class dequeDemo {

    public static void main(String[] args) {
        Deque<String> history = new ArrayDeque<>();

        //User visits pages
        history.addLast("google.com");
        history.addLast("youtube.com");
        history.addLast("github.com");
        System.out.println("History: " + history);

        //User pressed Back Button
        String currentPage = history.removeLast();

        System.out.println("Went back from: " + currentPage);
        System.out.println("Current History: " + history);


    }
}
