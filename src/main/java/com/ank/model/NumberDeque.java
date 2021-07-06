package com.ank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

@Data
public class NumberDeque {

    private final Deque<BigDecimal> deque = new ArrayDeque<>();

    public BigDecimal remove() {
        return deque.pollLast();
    }

    public BigDecimal peek() {
        return deque.peekLast();
    }

    public void add(BigDecimal value) {
        if (value == null) {
            throw new RuntimeException("Null value passed.");
        }
        deque.offerLast(value);
    }

    public void clear() {
        deque.clear();
    }

    public String toString() {
        return deque.stream()
                    .map(this::setScale)
                    .map(BigDecimal::toString)
                    .collect(Collectors.joining(" "));
    }

    private BigDecimal setScale(BigDecimal bigDecimal) {

        BigDecimal scaled = bigDecimal;
        if (bigDecimal.scale() > 10) {
            scaled = bigDecimal.setScale(10, RoundingMode.HALF_UP)
                               .stripTrailingZeros();
        } else if (bigDecimal.scale() > 0) {
            scaled = bigDecimal.stripTrailingZeros();
        }
        return scaled;
    }

}
