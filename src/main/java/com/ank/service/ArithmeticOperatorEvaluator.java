package com.ank.service;

import com.ank.exception.CalculatorException;
import com.ank.exception.InsufficientParameterException;
import com.ank.model.HistoryDeque;
import com.ank.model.NumberDeque;
import com.ank.model.Operator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class ArithmeticOperatorEvaluator {

    private final MathContext mathContext = new MathContext(17);

    public void evaluate(Operator operator, NumberDeque numberDeque, HistoryDeque historyDeque) {

        int operandCount = operator.getOperandCount();

        BigDecimal op1 = null, op2 = null;

        if (operandCount == 1) {
            op1 = numberDeque.remove();

            if (op1 == null) {
                String errorMessage = insufficientParametersErrorMessage(operator.getSymbol());
                throw new InsufficientParameterException(errorMessage);
            }

        } else if (operandCount == 2) {
            op2 = numberDeque.remove();
            op1 = numberDeque.remove();

            if (op1 == null || op2 == null) {

                String errorMessage = insufficientParametersErrorMessage(operator.getSymbol());
                if (op1 != null) {
                    numberDeque.add(op1);
                }
                if (op2 != null) {
                    numberDeque.add(op2);
                }
                throw new InsufficientParameterException(errorMessage);
            }
        }

        BigDecimal result;

        switch (operator) {
            case ADD:
                result = op1.add(op2);
                break;
            case SUBTRACT:
                result = op1.subtract(op2);
                break;
            case MULTIPLY:
                result = op1.multiply(op2);
                break;
            case DIVIDE:
                try {
                    result = divideOperation(op1, op2);
                } catch (CalculatorException ae) {
                    // restore operands to number deque
                    numberDeque.add(op1);
                    numberDeque.add(op2);
                    throw ae;
                }
                break;
            case SQRT:
                result = op1.sqrt(mathContext);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operator);
        }

        // Storing the expression result to the number deque
        numberDeque.add(result);

        // Storing the history log for supporting undo operation
        historyDeque.add(op1.toString());
        if (op2 != null) {
            historyDeque.add(op2.toString());
        }
        historyDeque.add(operator.getSymbol());
        historyDeque.add(result.toString());
    }

    private BigDecimal divideOperation(BigDecimal op1, BigDecimal op2) {
        BigDecimal result;
        try {
            result = op1.divide(op2, 15, RoundingMode.HALF_UP);
        } catch (ArithmeticException ae) {
            throw new CalculatorException("Divide Operation not possible : " + ae.getMessage());
        }
        return result;
    }

    String insufficientParametersErrorMessage(String symbol) {
        return "Operator " + symbol + " : insufficient parameters";
    }
}
