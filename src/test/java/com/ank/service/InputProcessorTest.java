package com.ank.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputProcessorTest {

    InputProcessor inputProcessor = new InputProcessor();

    @BeforeEach
    public void setup() {
        inputProcessor.getNumberDeque().clear();
        inputProcessor.getHistoryDeque().clear();
    }

    @Test
    public void testExpression1() {
        String input = "2 sqrt";
        String result = inputProcessor.process(input);
        assertEquals("1.4142135624", result);

        input = "clear 9 sqrt";
        result = inputProcessor.process(input);
        assertEquals("3", result);
    }

    @Test
    public void testExpression2() {
        String input = "5 2 -";
        String result = inputProcessor.process(input);

        assertEquals("3", result);

        input = "3 -";
        result = inputProcessor.process(input);

        assertEquals("0", result);
    }

    @Test
    public void testExpression3() {
        String input = "5 4 3 2";
        String result = inputProcessor.process(input);
        assertEquals("5 4 3 2", result);

        input = "undo undo *";
        result = inputProcessor.process(input);
        assertEquals("20", result);

        input = "5 *";
        result = inputProcessor.process(input);
        assertEquals("100", result);

        input = "undo";
        result = inputProcessor.process(input);
        assertEquals("20 5", result);
    }

    @Test
    public void testExpression4() {
        String input = "5 3 /";
        String result = inputProcessor.process(input);
        assertEquals("1.6666666667", result);
    }

    @Test
    public void testExpression5() {
        String input = "7 12 2 /";
        String result = inputProcessor.process(input);
        assertEquals("7 6", result);

        input = "*";
        result = inputProcessor.process(input);
        assertEquals("42", result);

        input = "4 /";
        result = inputProcessor.process(input);
        assertEquals("10.5", result);
    }

    @Test
    public void testExpression6() {
        String input = "1 2 3 4 5";
        String result = inputProcessor.process(input);
        assertEquals("1 2 3 4 5", result);

        input = "*";
        result = inputProcessor.process(input);
        assertEquals("1 2 3 20", result);

        input = "clear 3 4 -";
        result = inputProcessor.process(input);
        assertEquals("-1", result);
    }

    @Test
    public void testExpression7() {
        String input = "1 2 3 4 5";
        String result = inputProcessor.process(input);
        assertEquals("1 2 3 4 5", result);

        input = "* * * *";
        result = inputProcessor.process(input);
        assertEquals("120", result);
    }

}
