package com.ank.service;

import com.ank.model.HistoryDeque;
import com.ank.model.NumberDeque;
import com.ank.model.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ArithmeticOperatorEvaluatorTest {

    NumberDeque numberDeque = new NumberDeque();
    HistoryDeque historyDeque = new HistoryDeque();
    ArithmeticOperatorEvaluator arithmeticOperatorEvaluator = new ArithmeticOperatorEvaluator();

    @BeforeEach
    public void cleanUp() {
        numberDeque.clear();
        historyDeque.clear();
    }

    @Test
    public void testAdd() {
        numberDeque.add(BigDecimal.valueOf(1));
        numberDeque.add(BigDecimal.valueOf(2));

        arithmeticOperatorEvaluator.evaluate(Operator.ADD, numberDeque, historyDeque);
        Assertions.assertEquals(0, BigDecimal.valueOf(3).compareTo(numberDeque.remove()));
    }

    @Test
    public  void testSubtract() {
        numberDeque.add(BigDecimal.valueOf(3));
        numberDeque.add(BigDecimal.valueOf(2));

        arithmeticOperatorEvaluator.evaluate(Operator.SUBTRACT, numberDeque, historyDeque);
        Assertions.assertEquals(0, BigDecimal.valueOf(1).compareTo(numberDeque.remove()));
    }

    @Test
    public  void testMultiply() {
        numberDeque.add(BigDecimal.valueOf(3));
        numberDeque.add(BigDecimal.valueOf(2));

        arithmeticOperatorEvaluator.evaluate(Operator.MULTIPLY, numberDeque, historyDeque);
        Assertions.assertEquals(0, BigDecimal.valueOf(6).compareTo(numberDeque.remove()));
    }

    @Test
    public void testDivide() {
        numberDeque.add(BigDecimal.valueOf(10));
        numberDeque.add(BigDecimal.valueOf(2));

        arithmeticOperatorEvaluator.evaluate(Operator.DIVIDE, numberDeque, historyDeque);
        Assertions.assertEquals(0, BigDecimal.valueOf(5).compareTo(numberDeque.remove()));
    }

    @Test
    public void testSquareRoot() {
        numberDeque.add(BigDecimal.valueOf(25));

        arithmeticOperatorEvaluator.evaluate(Operator.SQRT, numberDeque, historyDeque);
        Assertions.assertEquals(0, BigDecimal.valueOf(5).compareTo(numberDeque.remove()));
    }

    @Test
    public void testPrecisionStored() {
        numberDeque.add(BigDecimal.valueOf(5));
        numberDeque.add(BigDecimal.valueOf(3));
        arithmeticOperatorEvaluator.evaluate(Operator.DIVIDE, numberDeque, historyDeque);
        int actualScale = numberDeque.remove().scale();
        Assertions.assertEquals(15, actualScale);
    }
}
