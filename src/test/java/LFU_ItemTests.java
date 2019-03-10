import org.junit.Assert;
import org.junit.Test;

public class LFU_ItemTests<T> {

    @Test
    public void LFU_ItemConstructorTest(){
        // Given
        String expectedData = "expected data";
        FrequencyNode expectedParent = new FrequencyNode();

        // When
        LFU_Item lfu_item = new LFU_Item(expectedData, expectedParent);
        T actualData = (T) lfu_item.getData();
        FrequencyNode actualParent = lfu_item.getParent();

        // Then
        Assert.assertEquals(expectedData, actualData);
        Assert.assertEquals(expectedParent, actualParent);
;    }
}
