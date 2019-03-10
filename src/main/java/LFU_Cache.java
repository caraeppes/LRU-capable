import java.util.HashMap;
import java.util.Map;

public class LFU_Cache<T> {

    private Map<Integer, LFU_Item> byKey;
    private FrequencyNode headFrequencyNode;

    public LFU_Cache() {
        byKey = new HashMap<>();
        headFrequencyNode = new FrequencyNode();
    }

    public FrequencyNode getNewFrequencyNode(int value, FrequencyNode previous, FrequencyNode next) {
        FrequencyNode newNode = new FrequencyNode();
        newNode.setValue(value);
        newNode.setPrevious(previous);
        newNode.setNext(next);
        previous.setNext(newNode);
        next.setPrevious(newNode);
        return newNode;
    }

    public void deleteFrequencyNode(FrequencyNode node) {
        node.getPrevious().setNext(node.getNext());
        node.getNext().setPrevious(node.getPrevious());
    }

    public T accessItem(Integer key) throws Exception {
        LFU_Item temp = byKey.get(key);
        if (temp == null) {
            throw new Exception("No such key!");
        }

        FrequencyNode frequencyNode = temp.getParent();
        FrequencyNode frequencyNodeNext = frequencyNode.getNext();

        if ((frequencyNodeNext.equals(headFrequencyNode)) ||
                (frequencyNodeNext.getValue() != frequencyNode.getValue() + 1)) {
            frequencyNodeNext = getNewFrequencyNode(frequencyNode.getValue() + 1, frequencyNode, frequencyNodeNext);

        }
        frequencyNodeNext.getItems().add(key);
        temp.setParent(frequencyNodeNext);

        frequencyNode.getItems().remove(key);
        if (frequencyNode.getItems().size() == 0) {
            deleteFrequencyNode(frequencyNode);
        }
        return (T) temp.getData();
    }

    public void insert(Integer key, T value) throws Exception {
        if (byKey.keySet().contains(key)) {
            throw new Exception("Key already exists!");
        }
        FrequencyNode frequencyNode = headFrequencyNode.getNext();
        if (frequencyNode.getValue() != 1) {
            frequencyNode = getNewFrequencyNode(1, headFrequencyNode, frequencyNode);
        }

        frequencyNode.getItems().add(key);
        byKey.put(key, new LFU_Item(value, frequencyNode));
    }

    public LFU_Item getLFUitem() throws Exception {
        if (byKey.size() == 0) {
            throw new Exception("The set is empty!");
        }
        return byKey.get(headFrequencyNode.getNext().getItems().toArray()[0]);
    }

    public Map<Integer, LFU_Item> getByKey() {
        return byKey;
    }

    public FrequencyNode getHeadFrequencyNode() {
        return headFrequencyNode;
    }

}
