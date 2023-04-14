package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Kibenian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/31/2023
 *
 *
 *
 */
public class KibenianArabicConverter {

    // A string that holds the number (Kibenian or Arabic) you would like to convert
    private final String number;
    private int aNum;
    private String kNum = "";

    /**
     * Constructor for the KibenianArabic class that takes a string. The string should contain a valid
     * Kibenian or Arabic numeral. See the assignment instructions for what constitutes a correct input
     * and what exceptions should be raised.
     *
     * @param number A string that represents either a Kibenian or Arabic number.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic integer that cannot be represented
     * in the Kibenian number system.
     * @throws MalformedNumberException Thrown if the value is an Kibenian number that does not conform
     * to the rules of the Kibenian number system or any other error in Arabic number input.
     */
    public KibenianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {
        this.number = number;

        String arrRegex = "\\d+"; // matches if number is comprised of intergers
        String kibRegex = "\\D+"; // matches if number is comprised of letters
        String decRegex = "/^\\d*\\.?\\d*$/"; // matches if number is a decimal
        String capRegex = "[A-Z\\s]"; // matches is ALL characters are uppercase

        if (number.matches(arrRegex)) { // if number is an integer/arabic number
            int intVal = Integer.parseInt(number);
            if (intVal < 1 || intVal > 215999) {
                throw new ValueOutOfBoundsException("Integer is out of Kibenian number system range");
            } else if (number.matches(decRegex) || number.startsWith("0")) {
                throw new MalformedNumberException("Integer cannot be represented in the Kibenian number system");
            } else {
                this.aNum = Integer.parseInt(number);
            }

        } else if (number.matches(kibRegex)) { // if number is a string of letters
            if (!number.matches(capRegex)) { // if not all uppercase
                throw new MalformedNumberException("Integer cannot be represented in the Arabic number system");
            } else {
                this.kNum = number;
            }
        } else {
            throw new MalformedNumberException("Not string or integer");
        }
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() throws MalformedNumberException {

        int p1 = 0; // first place: 0 underscores
        int p60 = 0; // 60th place: 1 underscore
        int p3600 = 0; // 3600th place: 2 underscores

        String subgroup1 = "";
        String subgroup60 = "";
        String subgroup3600 = "";

        int underscoreCount = 0;

        for (Character c: kNum.toCharArray()) {
            if (c.equals('_')) { underscoreCount++; break;}

            switch(underscoreCount) {
                case 0: p1 += toArabicChar(c); subgroup1 += c; break;
                case 1: p60 += toArabicChar(c); subgroup60 += c; break;
                case 2: p3600 += toArabicChar(c); subgroup3600 += c; break;
                default: throw new MalformedNumberException("Number must have less than 3 underscores");
            }
        }

        if (p1 > 60 || p60 > 60 || p3600 > 60) {
            throw new MalformedNumberException("Subgroups cannot exceed 60");
        }

        if (correctOrder(subgroup1) || correctOrder(subgroup60) || correctOrder(subgroup3600)) {
            throw new MalformedNumberException("Subgroups must be in order of LXVI");
        }

        return p3600*3600 + p60*60 + p1;
    }

    public int toArabicChar(char c) {

        int arabicNum = 0;

        switch(c) {
            case 'I': arabicNum = 1; break;
            case 'V': arabicNum = 5; break;
            case 'X': arabicNum = 10; break;
            case 'L': arabicNum = 50; break;
            default: break;
        }

        return arabicNum;
    }

    public Boolean correctOrder(String str) {

        String pattern = "LXVI";

        for (int i = 0; i < pattern.length() - 1; i++) {

            char x = pattern.charAt(i);
            char y = pattern.charAt(i + 1);

            int last = str.lastIndexOf(x);
            int first = str.indexOf(y);

            if (last == -1 || first == -1 || last > first) { return false; }
        }

        return true;
    }

    /**
     * Converts the number to an Kibenian numeral or returns the current value if it is already in the Kibenian form.
     *
     * @return A Kibenian value
     */
    public String toKibenian() {

        int p3600 = aNum/3600; // int sum of p3600 subgroup
        int remainder = aNum%3600; // p60 + p1 subgroups
        int p60 = remainder/60; // int sum of p60 subgroup
        int p1 = remainder%60; // p1 subgroup

        kNum += subgroupConversion(p1);
        if (p60 > 0) {
            kNum += "_";
            kNum+= subgroupConversion(p60);
        }
        if (p3600 > 0) {
            kNum += "_";
            kNum+= subgroupConversion(p3600);
        }

        return new StringBuilder(kNum).reverse().toString();
    }

}
