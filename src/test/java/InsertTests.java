import org.junit.Assert;
import org.junit.Test;

public class InsertTests {

    @Test(expected = Exception.class)
    public void insertWhenKeyAlreadyExistsTest() throws Exception{
        // Given
        LFU_Cache cache = new LFU_Cache();
        FrequencyNode frequencyNode = cache.getNewFrequencyNode(1, cache.getHeadFrequencyNode(), cache.getHeadFrequencyNode());
        LFU_Item item = new LFU_Item("data", frequencyNode);
        item.setKey(1000);
        cache.getByKey().put(item.getKey(), item);

        // When
        cache.insert(item.getKey(), "data");
    }

    @Test
    public <T> void insertWhenFrequencyNodeWithValue1DoesNotExistTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        Integer key = 1000;
        String expectedData = "expected data";
        int expectedNodeItemsSize = 1;

        // When
        cache.insert(key, expectedData);
        FrequencyNode expectedParentNode = cache.getHeadFrequencyNode().getNext();
        FrequencyNode actualParentNode = ((LFU_Item)(cache.getByKey().get(key))).getParent();
        T actualData = (T) ((LFU_Item)(cache.getByKey().get(key))).getData();
        int actualNodeItemsSize = cache.getHeadFrequencyNode().getNext().getItems().size();

        // Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedParentNode, actualParentNode);
        Assert.assertEquals(expectedNodeItemsSize, actualNodeItemsSize);
    }

    @Test
    public <T> void insertWhenFrequencyNodeWithValue1Exists() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(2000, "data");
        cache.insert(3000, "more data");
        int expectedNodeItemsSize = 3;

        // When
        cache.insert(1000, "even more data");
        int actualNodeItemsSize = cache.getHeadFrequencyNode().getNext().getItems().size();

        // Then
        Assert.assertEquals(expectedNodeItemsSize, actualNodeItemsSize);
    }
}
