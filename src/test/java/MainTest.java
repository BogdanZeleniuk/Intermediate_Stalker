import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

    private Main mainClass = new Main();
    private Map<String,Integer> testMap = new HashMap<>();

    @Test
    public void getCountOfEqualWordsTest() throws Exception {
        String testWords = "a3";
        int value = mainClass.getCountOfEqualWords(testWords, TestData.testList);
        Assert.assertEquals(3, value);
    }

    @Test
    public void getOnlyOneHundredElementsTest() throws Exception {
        for (int i=0; i<110; i++){
            testMap.put(String.valueOf(i) + " test",i);
        }
        int testSize = Main.getOnlyOneHundredElements(testMap).size();
        Assert.assertEquals(100, testSize);
        Assert.assertEquals(7, Main.getOnlyOneHundredElements(TestData.testMap).size());
    }

    @Test
    public void getSortedMapTest() throws Exception {
        mainClass.readFile();
        mainClass.putAllElementsIntoWordsMap(Main.dataFromFile);
        Map<String, Integer> testSortedMap = Main.getSortedMap();
        Assert.assertEquals("{a2=9, a5=7, a6=5, a3=3, a7=3, a1=1, a4=1}", testSortedMap.toString());
    }
}