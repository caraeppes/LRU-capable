import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FrequencyNodeTests {

    @Test
    public void frequencyNodeConstructorTest(){
        // Given
        FrequencyNode frequencyNode = new FrequencyNode();
        int expectedValue = 0;
        Set<LFU_Item> expectedItems = new HashSet<>();
        FrequencyNode expectedPrevious = frequencyNode;
        FrequencyNode expectedNext = frequencyNode;

        // When
        int actualValue = frequencyNode.getValue();
        Set<Integer> actualItems = frequencyNode.getItems();
        FrequencyNode actualPrevious = frequencyNode.getPrevious();
        FrequencyNode actualNext = frequencyNode.getNext();

        // Then
        Assert.assertEquals(expectedValue, actualValue);
        Assert.assertEquals(expectedItems, actualItems);
        Assert.assertEquals(expectedNext, actualNext);
        Assert.assertEquals(expectedPrevious, actualPrevious);
    }
}
