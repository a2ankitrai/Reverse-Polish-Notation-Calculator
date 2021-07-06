package com.ank.service;

import com.ank.model.HistoryDeque;
import com.ank.model.NumberDeque;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class SpecialOperatorEvaluatorTest {

    NumberDeque numberDeque = new NumberDeque();
    HistoryDeque historyDeque = new HistoryDeque();
    SpecialOperatorEvaluator specialOperatorEvaluator = new SpecialOperatorEvaluator();

    @BeforeEach
    public void cleanUp() {
        numberDeque.clear();
        historyDeque.clear();
    }

    @Test
    public void testClear() {

        numberDeque.add(BigDecimal.ONE);
        numberDeque.add(BigDecimal.TEN);

        specialOperatorEvaluator.performClearOperation(numberDeque, historyDeque);

        Assertions.assertNull(numberDeque.remove());
    }

    @Test
    public void testUndoOperands() {

        BigDecimal num1 = BigDecimal.valueOf(5), num2 = BigDecimal.valueOf(2);
        numberDeque.add(num1);
        numberDeque.add(num2);

        historyDeque.add(num1.toString());
        historyDeque.add(num2.toString());
        specialOperatorEvaluator.performUndoOperation(numberDeque, historyDeque);

        Assertions.assertEquals(num1, numberDeque.remove());
    }
}
