import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static List<String> dataFromFile = new ArrayList<>();
    private static Map<String, Integer> wordsMap = new TreeMap<>();

    private File getFile(){
        String fileName = "a.txt";
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    private String getReplacesLine(String line){
            return line.trim().replaceAll("[\\s]{2,}", " ").replaceAll(" ", "\n");
    }

    protected void readFile(){
        LOG.info("Reading file...");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getFile()));
            String line;
            while ((line = reader.readLine()) != null){
                if (!"".equals(line.trim()) && !line.trim().isEmpty()) {
                    Collections.addAll(dataFromFile, getReplacesLine(line).split("\n"));
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading data from file " + e.getMessage());
        }

    }

    protected int getCountOfEqualWords(String word, List<String> data){
        int countOfWords = 0;
        for (String newWord : data) {
            if (newWord.equals(word)) {
                countOfWords++;
            }
        }
        return countOfWords;
    }

    protected void putAllElementsIntoWordsMap(List<String> dataList){
        for (int i=0; i<dataList.size(); i++){
            wordsMap.put(dataList.get(i), getCountOfEqualWords(dataList.get(i), dataList));
        }
    }

    protected static Map<String, Integer> getSortedMap(){
        Map<String,Integer> map = getOnlyOneHundredElements(wordsMap);
        Map<String, Integer> sortedMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String first, String second) {
                int result = map.get(second).compareTo(map.get(first));
                return result == 0 ? 1 : result;
            }
        });
        sortedMap.putAll(map);
        return sortedMap;
    }

    protected static Map<String, Integer> getOnlyOneHundredElements(Map<String, Integer> map){
        Map<String, Integer> oneHundredMap = new TreeMap<>();
        List<String> keys = new ArrayList<>(map.keySet());
        List<Integer> values = new ArrayList<>(map.values());
        if (map.size()>=100) {
            for (int i = 0; i < 100; i++) {
                oneHundredMap.put(keys.get(i), values.get(i));
            }
            return oneHundredMap;
        }
        else {
            for (int i = 0; i < map.size(); i++) {
                oneHundredMap.put(keys.get(i), values.get(i));
            }
        }
        return oneHundredMap;
    }

    public static void main(String[] args) {
        LOG.info("Main class from project");

        Main mainClass = new Main();
        mainClass.readFile();
        mainClass.putAllElementsIntoWordsMap(dataFromFile);

        Map<String,Integer> map = getSortedMap();
        for (Map.Entry<String, Integer> mapElement : map.entrySet()){
            System.out.println(mapElement.getKey() + " : " + mapElement.getValue());
        }
    }
}
