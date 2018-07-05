package io.zipcoder;

import org.apache.commons.io.IOUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        int count =0;
        String output = (new Main()).readRawDataToString();
        ItemParser itemParser = new ItemParser();
        ArrayList<String> outputList = itemParser.parseRawDataIntoStringArray(output);
        Map<String, Map<Double, Integer>> myMap = null;
        for(String s: outputList) {
            try {
                Item item = itemParser.parseStringIntoItem(s);
                myMap = itemParser.collectAmounts(item);
            }
            catch (ItemParseException e){
                count++;
            }

        }
        itemParser.printOutput(myMap);
        // TODO: parse the data in output into items, and display to console.
    }
}
