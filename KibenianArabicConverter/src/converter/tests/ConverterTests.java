package converter.tests;

import converter.KibenianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the KibenianArabicConverter class.
 */
public class ConverterTests {


    @Test
    public void ArabicToKibenianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("1");
        assertEquals(converter.toKibenian(), "I");
        converter = new KibenianArabicConverter("5");
        assertEquals(converter.toKibenian(), "V");
        converter = new KibenianArabicConverter("10");
        assertEquals(converter.toKibenian(), "X");
        converter = new KibenianArabicConverter("50");
        assertEquals(converter.toKibenian(), "L");
    }

    @Test
    public void KibenianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
        converter = new KibenianArabicConverter("V");
        assertEquals(converter.toArabic(), 5);
        converter = new KibenianArabicConverter("X");
        assertEquals(converter.toArabic(), 10);
        converter = new KibenianArabicConverter("L");
        assertEquals(converter.toArabic(), 50);
    }


//    @Test(expected = MalformedNumberException.class)
//    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
//        throw new MalformedNumberException("TEST");
//    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    @Test
    public void ArabicToKibenianUnder60() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("42");
        assertEquals(converter.toArabic(), "XXXXII");
    }

    @Test
    public void ArabicToKibenianOver60() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("76");
        assertEquals(converter.toArabic(), "I_XVI");
    }

    // TODO Add more test cases

}
