import org.junit.Assert;
import org.junit.Test;

public class AccessTests {

    @Test(expected = Exception.class)
    public void accessItemWithInvalidKeyTest() throws Exception {
        LFU_Cache cache = new LFU_Cache();
        cache.accessItem(1);
    }

    @Test
    public <T> void accessItemWhenNodeIsLastFrequencyNodeTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(1000, 1000);

        Integer expectedData = 1000;
        int expectedNodeValue = 2;
        FrequencyNode expectedPreviousNode = cache.getHeadFrequencyNode();
        FrequencyNode expectedNextNode = cache.getHeadFrequencyNode();


        // When
        T actualData = (T) cache.accessItem(1000);
        int actualNodeValue = ((LFU_Item) cache.getByKey().get(1000)).getParent().getValue();
        FrequencyNode actualPreviousNode = ((LFU_Item) cache.getByKey().get(1000)).getParent().getPrevious();
        FrequencyNode actualNextNode = ((LFU_Item) cache.getByKey().get(1000)).getParent().getNext();

        // Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedNodeValue, actualNodeValue);
        Assert.assertEquals(expectedPreviousNode, actualPreviousNode);
        Assert.assertEquals(expectedNextNode, actualNextNode);
    }

    @Test
    public <T> void accessItemWhenNextFrequencyNodesValueIsNot1HigherTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(1000, 1000);
        cache.accessItem(1000);
        cache.accessItem(1000);

        cache.insert(2000, "data");
        cache.insert(3000, 3000);

        T expectedData = (T) "data";
        int expectedNodeValue = 2;
        FrequencyNode expectedPreviousNode = cache.getHeadFrequencyNode().getNext();
        FrequencyNode expectedNextNode = cache.getHeadFrequencyNode().getPrevious();

        // When
        T actualData = (T) cache.accessItem(2000);
        int actualNodeValue = ((LFU_Item) cache.getByKey().get(2000)).getParent().getValue();
        FrequencyNode actualPreviousNode = ((LFU_Item) cache.getByKey().get(2000)).getParent().getPrevious();
        FrequencyNode actualNextNode = ((LFU_Item) cache.getByKey().get(2000)).getParent().getNext();

        // Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedNodeValue, actualNodeValue);
        Assert.assertEquals(expectedNextNode, actualNextNode);
        Assert.assertEquals(expectedPreviousNode, actualPreviousNode);
    }


    @Test
    public <T> void accessItemWhenNextFrequencyNodeIs1Higher() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(1000, 1000);
        cache.accessItem(1000);
        cache.accessItem(1000);
        cache.insert(2000, 2000);
        cache.accessItem(2000);

        int expectedData = 2000;
        int expectedNodeValue = 3;
        FrequencyNode expectedPreviousNode = cache.getHeadFrequencyNode();
        FrequencyNode expectedNextNode = cache.getHeadFrequencyNode();

        // When
        T actualData = (T) cache.accessItem(2000);
        int actualNodeValue = ((LFU_Item)(cache.getByKey().get(2000))).getParent().getValue();
        FrequencyNode actualPreviousNode = ((LFU_Item) cache.getByKey().get(2000)).getParent().getPrevious();
        FrequencyNode actualNextNode = ((LFU_Item) cache.getByKey().get(2000)).getParent().getNext();

        // Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedNodeValue, actualNodeValue);
        Assert.assertEquals(expectedPreviousNode, actualPreviousNode);
        Assert.assertEquals(expectedNextNode, actualNextNode);
    }
}
