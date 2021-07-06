package com.ank.service;

import com.ank.model.HistoryDeque;
import com.ank.model.NumberDeque;
import com.ank.model.Operator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Getter
@Setter
public class InputProcessor {

    private final NumberDeque numberDeque;
    private final HistoryDeque historyDeque;
    private final ArithmeticOperatorEvaluator arithmeticOperatorEvaluator;
    private final SpecialOperatorEvaluator specialOperatorEvaluator;

    public InputProcessor() {
        numberDeque = new NumberDeque();
        historyDeque = new HistoryDeque();
        arithmeticOperatorEvaluator = new ArithmeticOperatorEvaluator();
        specialOperatorEvaluator = new SpecialOperatorEvaluator();
    }

    public String process(String input) {

        String[] inputList = input.trim().split("\\s+");

        if (inputList.length > 0) {
            for (String s : inputList) {

                if (NumberUtils.isParsable(s)) {
                    BigDecimal number = new BigDecimal(s);

                    numberDeque.add(number);
                    historyDeque.add(number.toString());
                } else {
                    Operator operator;
                    try {
                        operator = Operator.getOperator(s);
                    } catch (NoSuchElementException e) {
                        System.err.println("Invalid operator");
                        break;
                    }
                    if (operator != null) {
                        try {
                            applyOperator(operator, numberDeque, historyDeque);
                        } catch (RuntimeException re) {
                            System.err.println(re.getMessage());
                            break;
                        }
                    }
                }
            }
        }

        return numberDeque.toString();

    }

    public void applyOperator(Operator operator, NumberDeque numberDeque, HistoryDeque historyDeque) {

        switch (operator) {

            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
            case SQRT:
                arithmeticOperatorEvaluator.evaluate(operator, numberDeque, historyDeque);
                break;
            case CLEAR:
                specialOperatorEvaluator.performClearOperation(numberDeque, historyDeque);
                break;
            case UNDO:
                specialOperatorEvaluator.performUndoOperation(numberDeque, historyDeque);
                break;
            case EXIT:
                System.exit(0);

        }

    }


}
