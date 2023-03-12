package ex01;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {
    private static final String DICTIONARY_FILENAME = "dictionary.txt".toString();
    private static final List<String> allWords = new ArrayList<String>();
    public static void main(String[] args){
        checkArgs(args);
        try {
            BufferedReader firstFile = new BufferedReader(new FileReader(args[0]));
            BufferedReader secondFile = new BufferedReader(new FileReader(args[1]));
            Map<String, Integer> firstDict = new HashMap<String,Integer>();
            Map<String, Integer> secondDict = new HashMap<String,Integer>();
            addToDictAndWordList(firstDict,firstFile);
            addToDictAndWordList(secondDict,secondFile);
            firstFile.close(); secondFile.close();
            System.out.printf("Similarity = %.2g",calcSimilarity(firstDict,secondDict));
            printToFileDictionary();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
    private static void checkArgs(String[] args){
        if(args.length!=2){
            System.err.println("Incorrect number of arguments!");
            System.exit(-1);
        }
    }
    private static void addToDictAndWordList(Map<String, Integer> dict, BufferedReader file) throws IOException {
        String temp;
        while((temp = file.readLine()) != null){
            String[] libraryOfLine = temp.replaceAll("\\p{Punct}", "").toLowerCase().split("\\s+");
            for(String elemOfLine:libraryOfLine){
                if(dict.containsKey(elemOfLine)){
                    dict.put(elemOfLine, dict.get(elemOfLine) + 1);
                } else {
                    if(!allWords.contains(elemOfLine)){
                        allWords.add(elemOfLine);
                    }
                    dict.put(elemOfLine, 1);
                }
            }
        }
    }

    private static double calcSimilarity(Map<String, Integer> firstDict,Map<String, Integer> secondDict){
        double numerator = 0.0;
        double sumPowFirst = 0.0, sumPowSecond = 0.0;
        for(String word: allWords){
            sumPowFirst+=Math.pow(firstDict.getOrDefault(word,0),2);
            sumPowSecond+=Math.pow(secondDict.getOrDefault(word,0),2);
            numerator+=(firstDict.getOrDefault(word,0)*secondDict.getOrDefault(word,0));
        }
        double denominator = Math.sqrt(sumPowFirst) * Math.sqrt(sumPowSecond);
        return numerator/denominator;
    }
    private static void printToFileDictionary() throws IOException {
        BufferedWriter dictionaryFile=new BufferedWriter(new FileWriter(DICTIONARY_FILENAME));
        for(String word:allWords){
            dictionaryFile.write(word);
            dictionaryFile.newLine();
        }
        dictionaryFile.close();
    }
}
