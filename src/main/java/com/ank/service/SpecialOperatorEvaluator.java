package com.ank.service;

import com.ank.model.HistoryDeque;
import com.ank.model.NumberDeque;
import com.ank.model.Operator;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class SpecialOperatorEvaluator {

    public void performClearOperation(NumberDeque numberDeque, HistoryDeque historyDeque) {
        numberDeque.clear();
        historyDeque.clear();
    }

    public void performUndoOperation(NumberDeque numberDeque, HistoryDeque historyDeque) {
        if (historyDeque.isEmpty()) {
            System.err.println("No previous operation found.");
            return;
        }

        String lastOperation = historyDeque.remove();
        if (NumberUtils.isParsable(lastOperation)) {
            numberDeque.remove();
        }

        // Check again if the history element was due to an operation performed.
        String secondLastOperation = historyDeque.peek();
        if (secondLastOperation == null) {
            return;
        }

        // the second last operation was an arithmetic operation.
        if (!NumberUtils.isParsable(secondLastOperation)) {

            historyDeque.remove();
            Operator op = Operator.getOperator(secondLastOperation);

            String a = null, b = null;
            if (op.getOperandCount() == 1) {
                a = historyDeque.remove();
            } else if (op.getOperandCount() == 2) {
                b = historyDeque.remove();
                a = historyDeque.remove();
            }

            if (a != null) {
                numberDeque.add(new BigDecimal(a));

            }
            if (b != null) {
                numberDeque.add(new BigDecimal(b));
            }
        }
    }
}
