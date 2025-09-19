package org.example.utils;

import java.math.BigDecimal;

public class AmountUtils {
    public static boolean isValidAmount(BigDecimal amount){
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0;
    }


    public static boolean hasSufficientBalance(BigDecimal balance , BigDecimal withdrawAmount){
        return balance != null && withdrawAmount != null && balance.compareTo(withdrawAmount) >= 0;
    }
}
