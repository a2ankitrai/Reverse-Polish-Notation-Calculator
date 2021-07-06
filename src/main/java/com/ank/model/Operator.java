package com.ank.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Operator {

    ADD("+", 2),
    SUBTRACT("-", 2),
    MULTIPLY("*", 2),
    DIVIDE("/", 2),
    SQRT("sqrt", 1),
    UNDO("undo", 0),
    CLEAR("clear", 0),
    EXIT("exit", 0);

    public final String symbol;
    public final int operandCount;

    Operator(String symbol, int operandCount) {
        this.symbol = symbol;
        this.operandCount = operandCount;
    }

    public static Operator getOperator(String symbol) {
        return Arrays.stream(Operator.values())
                     .filter(operator -> operator.getSymbol().equalsIgnoreCase(symbol))
                     .findFirst()
                     .orElseThrow();
    }

}
