import org.junit.Assert;
import org.junit.Test;

public class DeleteTests {

    @Test
    public void deleteFrequencyNodeTest() throws Exception {
        // Given
        LFU_Cache cache = new LFU_Cache();
        cache.insert(1, "item 1");
        cache.insert(2, "item 2");
        cache.insert(3, "item 3");

        cache.accessItem(2);
        cache.accessItem(3);
        cache.accessItem(3);

        FrequencyNode node1 = ((LFU_Item)cache.getByKey().get(1)).getParent();
        FrequencyNode node2 = ((LFU_Item)cache.getByKey().get(2)).getParent();
        FrequencyNode node3 = ((LFU_Item)cache.getByKey().get(3)).getParent();

        FrequencyNode expectedNode1Next = node3;
        FrequencyNode expectedNode3Previous = node1;

        // When
        cache.deleteFrequencyNode(node2);
        FrequencyNode actualNode1Next = node1.getNext();
        FrequencyNode actualNode3Previous = node3.getPrevious();

        // Then
        Assert.assertEquals(expectedNode1Next, actualNode1Next);
        Assert.assertEquals(expectedNode3Previous, actualNode3Previous);
    }
}
