import java.util.Random;

public class LFU_Item<T> {

    private T data;
    private FrequencyNode parent;
    private Integer key;

    public LFU_Item(T data, FrequencyNode parent) {
        this.data = data;
        this.parent = parent;
        Random random = new Random();
        this.key = random.nextInt(100000);
    }

    public FrequencyNode getParent() {
        return parent;
    }

    public void setParent(FrequencyNode parent) {
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
