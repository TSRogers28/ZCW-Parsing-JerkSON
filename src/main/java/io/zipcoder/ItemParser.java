package io.zipcoder;

import java.util.*;

public class ItemParser {

    //"naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"

    public String lowerAllCasesCauseTariqCannotSpell(String rawData){

        return null;
    }


    //    0 "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##"

    //     1 +"naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##"

    //      2 +"NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##";

    public ArrayList<String> parseRawDataIntoStringArray(String rawData){
        String stringPattern = "##";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawData);

        return response;
    }

    public Item parseStringIntoItem(String rawItems) throws ItemParseException {
        String stringPattern = "[;|^|@|*|%|$|#|!|:]";
        ArrayList<String> itemsPattern = splitStringWithRegexPattern(stringPattern , rawItems);
        Item newItem = null;
        Double d;
        for(String s : itemsPattern)
            if (!s.equals("")) {
                d = Double.valueOf(itemsPattern.get(3));
                newItem = new Item(toLowerCase(itemsPattern.get(1)), d, toLowerCase(itemsPattern.get(5)), toLowerCase(itemsPattern.get(7)));
            }
            else throw new ItemParseException();
        return newItem;
    }

    public String toLowerCase(String a) {
        String s = "";
        for (int i = 0; i < a.length(); i++) {
            char aChar = a.charAt(i);
            if (65 <= aChar && aChar <= 90) {
                aChar = (char) ((aChar + 32));
            }
            s += aChar;
        }
        return s;
    }

    public Map<String, Map<Double, Integer>> collectAmounts(Item item) {
        Map<String, Map<Double, Integer>> myMap = new HashMap<>();
        int count;
        String name = item.getName();
        Double price = item.getPrice();
        if (myMap.containsKey(name)) {
            count = myMap.get(name).getOrDefault(price, 0);
            count++;
            myMap.get(name).put(price, count);
        } else{ myMap.put(name, new HashMap<>());
           myMap.get(name).put(price, 1);
        }
        return myMap;
    }

    public void printOutput(Map<String, Map<Double, Integer>> myMap){
        Double doub = 0.0;
        String key;
        Integer count;
        System.out.println(myMap.size());
        //System.out.println(myMap.keySet());
       // System.out.println(myMap.entrySet());
//        for(String m : myMap.keySet()){
//            key = m;
//            Map map = myMap.get(key);
//            Integer y = myMap.get(key).get(doub);
//            System.out.println("Name: "  + key +  "    Seen: " + y);
//            System.out.println("____________    ________________");
//            System.out.println("____________    ________________");
//            for(Object d : map.keySet())
//            doub = (Double) d;
//            count = (Integer) map.get(doub);
//            System.out.println("Price: "+ doub +    "     Seen: " + count);
//            System.out.println("____________    ________________");
//        }
    }





    public ArrayList<String> findKeyValuePairsInRawItemData(String rawItem){
        String stringPattern = "[;|^|@|*|%|$|#|!]";
        ArrayList<String> response = splitStringWithRegexPattern(stringPattern , rawItem);

        return response;
    }

    private ArrayList<String> splitStringWithRegexPattern(String stringPattern, String inputString){
        return new ArrayList<String>(Arrays.asList(inputString.split(stringPattern)));
    }



}
