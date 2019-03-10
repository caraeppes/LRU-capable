import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LFU_CacheTests {

    @Test
    public void LFU_CacheConstructorTest() {
        // Given
        Map<Integer, LFU_Item> expectedByKey = new HashMap<>();
        int expectedNodeValue = 0;

        // When
        LFU_Cache lfu_cache = new LFU_Cache();
        Map<Integer, LFU_Item> actualByKey = lfu_cache.getByKey();
        int actualNodeValue = lfu_cache.getHeadFrequencyNode().getValue();

        // Then
        Assert.assertEquals(expectedByKey, actualByKey);
        Assert.assertEquals(expectedNodeValue, actualNodeValue);
    }


    @Test
    public void getNewFrequencyNodeTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(5, 5);
        for(int i  = 0; i < 4; i++){
            cache.accessItem(5);
        }
        cache.insert(2, 2);
        cache.accessItem(2);

        int expectedValue = 4;
        FrequencyNode expectedPrevious = ((LFU_Item)(cache.getByKey().get(2))).getParent();
        FrequencyNode expectedNext = ((LFU_Item)(cache.getByKey().get(5))).getParent();

        // When
        FrequencyNode newNode = cache.getNewFrequencyNode(expectedValue, expectedPrevious, expectedNext);

        int actualValue = newNode.getValue();
        FrequencyNode actualPrevious = newNode.getPrevious();
        FrequencyNode actualNext = newNode.getNext();

        FrequencyNode expectedPreviousNodeNext = newNode;
        FrequencyNode expectedNextNodePrevious = newNode;
        FrequencyNode actualPreviousNodeNext = expectedPrevious.getNext();
        FrequencyNode actualNextNodePrevious = expectedNext.getPrevious();

        // Then
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedNext, actualNext);
        Assert.assertEquals(expectedPrevious, actualPrevious);
        Assert.assertEquals(expectedNextNodePrevious, actualNextNodePrevious);
        Assert.assertEquals(expectedPreviousNodeNext, actualPreviousNodeNext);
    }

    
    @Test (expected = Exception.class)
    public void getLFUitemWhenSetIsEmptyTest() throws Exception{
        // Given
        LFU_Cache cache = new LFU_Cache();

        //When
        cache.getLFUitem();
    }

    @Test
    public void getLFUitemTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(1, "data");
        cache.insert(2, "more data");
        cache.insert(3, "even more");

        cache.accessItem(1);

        for(int i = 0; i < 20; i++){
            cache.accessItem(2);
        }

        for(int i = 0; i < 100; i++){
            cache.accessItem(3);
        }

        LFU_Item expected = (LFU_Item) cache.getByKey().get(1);

        // When
        LFU_Item actual = cache.getLFUitem();

        // Then
        Assert.assertEquals(expected, actual);
    }
}
