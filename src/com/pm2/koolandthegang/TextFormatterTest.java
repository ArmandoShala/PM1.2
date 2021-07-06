package com.pm2.koolandthegang;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Test Class for TextFormatter.
 *
 * @author Gruppe2 IT20ta_WIN
 *
 * @version 2020-11-06
 */
public class TextFormatterTest {

    private TextFormatter textFormatter;

    @Before
    public void setUp(){
        textFormatter= new TextFormatter();
    }

    /**
     * Test the method getAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords1(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Lorem ipsum dolor sit amet, consetetur sadipscing elitr");
        testData.add ("At vero eos et accusam et justo duo dolores et ea rebum");
        testData.add ("Stet clita kasd gubergren, no sea takimata sanctus");
        testData.add ("Duis autem vel eum iriure dolor");
        testData.add ("o sea takimata sanctus");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add ("Lorem");
        expectedData.add ("At");
        expectedData.add ("Stet");
        expectedData.add ("Duis");
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords2(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("lorem ipsum dolor sit amet, consetetur sadipscing elitr, Sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam");
        testData.add ("it vero eos et accusam et justo duo dolores et ea rebum. ");
        testData.add ("stet clita kasd gubergren, no sea Takimata sanctus est Lorem ipsum dolor sit amet");
        testData.add ("suis autem vel eum iriure Dolor");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add("Sed");
        expectedData.add ("Takimata");
        expectedData.add ("Lorem");
        expectedData.add ("Dolor");
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords3(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("DUIS AUTEM VEL EUM");
        testData.add ("F F A  ");
        testData.add ("stet clita kasd gubergren, no sea takimata sanctus est lorem ipsum dolor sit amet");
        testData.add ("suis autem vel euM iriurE Dolor");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add ("DUIS");
        expectedData.add ("AUTEM");
        expectedData.add ("VEL");
        expectedData.add ("EUM");
        expectedData.add ("F");
        expectedData.add ("F");
        expectedData.add ("A");
        expectedData.add ("Dolor");
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);

    }

    /**
     * Test the method getAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords4(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("facer possim assum. lorem ipsum dolor ");
        testData.add ("llamcorper suscipit lobortis ni");
        ArrayList<String> expectedData= new ArrayList<>();
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);

    }

    /**
     * Test the method getAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords5(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("It VeRo EoS eT aCcUsAm Et JuStO dUo DoLoReS eT eA rEbUm.");
        testData.add ("suis autem vel euM iriure Dolor");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add ("It");
        expectedData.add ("VeRo");
        expectedData.add ("EoS");
        expectedData.add ("Et");
        expectedData.add ("JuStO");
        expectedData.add ("DoLoReS");
        expectedData.add ("Dolor");
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method testGetAllUppercaseWords
     */
    @Test
    public void checkGetAllUppercaseWords6(){
        ArrayList<String> testData= new ArrayList<>();
        ArrayList<String> expectedData= new ArrayList<>();
        List<String> returnData= textFormatter.testGetAllUppercaseWords (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllWordsToBeIndexed
     */
    @Test
    public void checkGetAllWordsToBeIndexed1(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Lorem");
        testData.add ("lorem");
        testData.add ("lorem");
        testData.add ("Lorem");
        testData.add ("Lorem");
        testData.add ("Lorem");
        testData.add ("Lorem");
        HashSet<String> expectedData= new HashSet<>();
        expectedData.add ("Lorem");
        HashSet<String> returnData= textFormatter.testGetAllWordsToBeIndexed (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllWordsToBeIndexed
     */
    @Test
    public void checkGetAllWordsToBeIndexed2(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("tation");
        testData.add ("tation");
        testData.add ("Tation");
        testData.add ("Lorem");
        testData.add ("Lorem");
        testData.add ("Tation");
        testData.add ("Lorem");
        HashSet<String> expectedData= new HashSet<>();
        HashSet<String> returnData= textFormatter.testGetAllWordsToBeIndexed (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllWordsToBeIndexed
     */
    @Test
    public void checkGetAllWordsToBeIndexed3(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("tation");
        testData.add ("tation");
        testData.add ("Tation");
        testData.add ("Tation");
        testData.add ("Lorem");
        testData.add ("Tation");
        testData.add ("Lorem");
        HashSet<String> expectedData= new HashSet<>();
        HashSet<String> returnData= textFormatter.testGetAllWordsToBeIndexed (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllWordsToBeIndexed
     */
    @Test
    public void checkGetAllWordsToBeIndexed4(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Dolor");
        testData.add ("Dolor");
        testData.add ("Dolor");
        testData.add ("dolor");
        testData.add ("Lorem");
        testData.add ("Dolor");
        testData.add ("Dolor");
        HashSet<String> expectedData= new HashSet<>();
        expectedData.add("Dolor");
        HashSet<String> returnData= textFormatter.testGetAllWordsToBeIndexed (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method getAllWordsToBeIndexed
     */
    @Test
    public void checkGetAllWordsToBeIndexed5(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Lorem");
        testData.add ("Dolor");
        testData.add ("Lorem");
        testData.add ("Dolor");
        testData.add ("Lorem");
        testData.add ("Dolor");
        testData.add ("Lorem");
        testData.add ("Dolor");
        HashSet<String> expectedData= new HashSet<>();
        expectedData.add("Lorem");
        expectedData.add("Dolor");
        HashSet<String> returnData= textFormatter.testGetAllWordsToBeIndexed (testData);

        Assert.assertEquals(expectedData,returnData);
    }

    /**
     * Test the method printIndex
     * To make printIndex() testable, add return type of ArrayList<String>
     * and a new local variable of same type. Add the string that gets printed to
     * new ArrayList and return it finally. The returnData should not be null
     */
    @Test
    public void checkPrintIndex1(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Lorem ipsum dolor sit Lorem, Lorem Lorem elitr, Oke Lorem nonumy ");
        testData.add ("ipsum Lorem Oke amet");
        testData.add ("augue duis dolore te feugait nulla dolore. Lorem Dolore dolor sit amet, Dolore adipiscing elit. ");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add("Lorem 1,2,3");
        expectedData.add("Dolore 1,2,3");
        ArrayList<String> returnData= null;//textFormatter.printIndex (testData);

        Assert.assertEquals(expectedData,returnData);

    }

    /**
     * Test the method printIndex
     * To make printIndex() testable, add return type of ArrayList<String>
     * and a new local variable of same type. Add the string that gets printed to
     * new ArrayList and return it finally. The returnData should not be null
     */
    @Test
    public void checkPrintIndex2(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("aMeT, aMeT, aMeT, AMeT, aMeT eugait nulla Amet");
        testData.add ("am liber nobis cum soluta nobis nobis option ");
        testData.add ("nonumy nobis nonumy nonumy Nonumy");
        ArrayList<String> expectedData= new ArrayList<>();
        ArrayList<String> returnData= null;//textFormatter.printIndex (testData);

        Assert.assertEquals(expectedData,returnData);

    }

    /**
     * Test the method printIndex
     * To make printIndex() testable, add return type of ArrayList<String>
     * and a new local variable of same type. Add the string that gets printed to
     * new ArrayList and return it finally. The returnData should not be null
     */
    @Test
    public void checkPrintIndex3(){
        ArrayList<String> testData= new ArrayList<>();
        testData.add ("Lorem, Lorem, Lorem, Lorem, Lorem Lorem Lorem Amet");
        testData.add ("nobis nobis lorem cum nobis nobis nobis dolore");
        testData.add ("Dolore Dolore Dolore Dolore Dolore");
        ArrayList<String> expectedData= new ArrayList<>();
        expectedData.add("Lorem 1");
        expectedData.add("Dolore 3");
        ArrayList<String> returnData= null;//textFormatter.printIndex (testData);

        Assert.assertEquals(expectedData,returnData);

    }

    /**
     * Test the method printIndex
     * To make printIndex() testable, add return type of ArrayList<String>
     * and a new local variable of same type. Add the string that gets printed to
     * new ArrayList and return it finally. The returnData should not be null
     */
    @Test
    public void checkPrintIndex4(){
        ArrayList<String> testData= new ArrayList<>();
        ArrayList<String> expectedData= new ArrayList<>();
        ArrayList<String> returnData= null;//textFormatter.printIndex (testData);

        Assert.assertEquals(expectedData,returnData);
    }

}
