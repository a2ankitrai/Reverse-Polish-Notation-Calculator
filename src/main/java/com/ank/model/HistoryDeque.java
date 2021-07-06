package com.ank.model;

import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;

@Data
public class HistoryDeque {

    private final Deque<String> deque = new ArrayDeque<>();

    public String remove() {
        return deque.pollLast();
    }

    public String peek() {
        return deque.peekLast();
    }

    public void add(String value) {
        if (value == null) {
            throw new RuntimeException("Null value passed.");
        }
        deque.offerLast(value);
    }

    public void clear(){
        deque.clear();
    }

    public boolean isEmpty(){
        return deque.isEmpty();
    }
}
