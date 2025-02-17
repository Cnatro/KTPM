
import com.cnatro.junit5demoktpm.DemoService;
import com.cnatro.junit5demoktpm.Radix;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
public class MyTest {

    @Test
    public void soNguyenDuongChan() {
        int inputx = 2;
        int inputn = 2;
        double expectedOutput = 4;
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenDuongLe() {
        int inputx = 3;
        int inputn = 5;
        double expectedOutput = Math.pow(3, 5);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenDuongAmChan() {
        int inputx = 2;
        int inputn = -2;
        double expectedOutput = Math.pow(2, -2);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenDuongAmLe() {
        int inputx = 5;
        int inputn = -3;
        double expectedOutput = Math.pow(5, -3);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenAmChan() {
        int inputx = -2;
        int inputn = 2;
        double expectedOutput = 4;
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenAmLe() {
        int inputx = -3;
        int inputn = 5;
        double expectedOutput = Math.pow(-3, 5);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenAmNChan() {
        int inputx = -2;
        int inputn = -2;
        double expectedOutput = Math.pow(-2, -2);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @Test
    public void soNguyenAmNLe() {
        int inputx = -5;
        int inputn = -3;
        double expectedOutput = Math.pow(-5, -3);
        double actualOutput = DemoService.Power(inputx, inputn);

        Assert.assertEquals(expectedOutput, actualOutput);

    }

    @ParameterizedTest
    @CsvSource({"255,FF,16", "255,11111111,2", "255,377,8"})
    public void testConvertToHe16(int input,String expectedOutput,String indix) {
        Radix tmp = new Radix(255);
//        String expectedOutput = "FF";

        String actualOutput = tmp.ConvertDecimalToAnother(16);
        Assert.assertEquals(expectedOutput, actualOutput);

    }
}
