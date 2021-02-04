package com.trade_accounting.util;

public class SortNumberConverter {

    public static String convert(String sortNumber) {
        int number = Integer.parseInt(sortNumber);
        if (number < 10) return "0000" + sortNumber;
        else if (number < 100) return "000" + sortNumber;
        else if (number < 1000) return "00" + sortNumber;
        else if (number < 10000) return "0" + sortNumber;
        else
            return sortNumber;
    }

}
