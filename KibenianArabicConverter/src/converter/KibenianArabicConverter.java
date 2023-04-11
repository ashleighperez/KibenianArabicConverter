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
        // TODO check to see if the number is valid, then set it equal to the string
        this.number = number;

        if (Integer.parseInt(number) > 1) { // needs && !
            aNum = Integer.parseInt(number);
        } else if (true) { //TODO
            kNum = number;
        } else {
            throw new ValueOutOfBoundsException("Integer cannot be represented in the Kibenian number system");
        }
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        kNum = number;
        if (kNum.equals("I")){
            aNum = 1;
        }
        if (kNum.equals("V")){
            aNum = 5;
        }
        if (kNum.equals("X")){
            aNum = 10;
        }
        if (kNum.equals("L")){
            aNum = 50;
        }
        else{
            //break;
            //throw new MalformedNumberException("Error");
        }

        return aNum;
    }

    /**
     * Converts the number to an Kibenian numeral or returns the current value if it is already in the Kibenian form.
     *
     * @return A Kibenian value
     */
    public String toKibenian() {
        /*int numOf60 = aNum/60;
        int remainder = aNum%60;
        int numOf50 = remainder/50;
        remainder = remainder%50;
        int numOf10 = remainder/10;
        remainder = remainder%10;
        int numOf5 = remainder/5;
        remainder = remainder%5;
        int numOf1 = remainder;

        if(numOf60>0){

        }
        if(numOf50>0){
            kNum+=;
        }*/

        switch(aNum) {
            case 1: kNum += "I"; break;
            case 5: kNum += "V"; break;
            case 10: kNum += "X"; break;
            case 50: kNum += "L"; break;
            default: break;
        }


        return kNum;
    }

}
