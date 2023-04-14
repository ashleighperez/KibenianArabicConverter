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


    @Test // Tests that all numeric equal their respective letter values
    public void ArabicToKibenianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("1");
        assertEquals("I", converter.toKibenian());
        converter = new KibenianArabicConverter("5");
        assertEquals("V", converter.toKibenian());
        converter = new KibenianArabicConverter("10");
        assertEquals("X", converter.toKibenian());
        converter = new KibenianArabicConverter("50");
        assertEquals("L", converter.toKibenian());
    }

    @Test // Tests that all letters equal their respective numeric values
    public void KibenianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("I");
        assertEquals(1, converter.toArabic());
        converter = new KibenianArabicConverter("V");
        assertEquals(5, converter.toArabic());
        converter = new KibenianArabicConverter("X");
        assertEquals(10, converter.toArabic());
        converter = new KibenianArabicConverter("L");
        assertEquals(50, converter.toArabic());
    }

    @Test (expected = MalformedNumberException.class) // tests if a number can be converted from K to A
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class) // tests if a number can be converted from A to K
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    @Test(expected = MalformedNumberException.class)
    public void leadingZeroTest() throws MalformedNumberException, ValueOutOfBoundsException{
        KibenianArabicConverter converter = new KibenianArabicConverter("0342");
        assertThrows("Integer cannot be represented in the Kibenian number system", MalformedNumberException.class, () -> converter.toKibenian());
    }

    @Test(expected = MalformedNumberException.class)
    public void decimalTest() throws MalformedNumberException, ValueOutOfBoundsException{
        KibenianArabicConverter converter = new KibenianArabicConverter("123.45");
        assertThrows("Integer cannot be represented in the Kibenian number system", MalformedNumberException.class, () -> converter.toKibenian());
    }
    @Test(expected = MalformedNumberException.class)
    public void anyLowercaseTest() throws MalformedNumberException, ValueOutOfBoundsException{
        KibenianArabicConverter converter = new KibenianArabicConverter("xXI");
        assertThrows("Integer cannot be represented in the Arabic number system", MalformedNumberException.class, () -> converter.toArabic());
        KibenianArabicConverter converter2 = new KibenianArabicConverter("I_XvI");
        assertThrows("Integer cannot be represented in the Arabic number system", MalformedNumberException.class, () -> converter2.toArabic());
    }

    @Test // testing numbers under 60 return correctly
    public void ArabicToKibenianUnder60() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("42");
        assertEquals("XXXXII", converter.toKibenian());
    }

    @Test // testing nums over 60
    public void ArabicToKibenianOver60() throws MalformedNumberException, ValueOutOfBoundsException {
        KibenianArabicConverter converter = new KibenianArabicConverter("76");
        assertEquals("I_XVI", converter.toKibenian());
    }

    // TODO Add more test cases

}
