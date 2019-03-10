import java.util.HashSet;
import java.util.Set;

public class FrequencyNode {

    private int frequencyValue;
    private Set<Integer> items;
    private FrequencyNode previous;
    private FrequencyNode next;

    public FrequencyNode() {
        frequencyValue = 0;
        items = new HashSet<>();
        previous = this;
        next = this;
    }

    public int getValue() {
        return frequencyValue;
    }

    public void setValue(int frequencyValue) {
        this.frequencyValue = frequencyValue;
    }

    public Set<Integer> getItems() {
        return items;
    }

    public FrequencyNode getPrevious() {
        return previous;
    }

    public void setPrevious(FrequencyNode previous) {
        this.previous = previous;
    }

    public FrequencyNode getNext() {
        return next;
    }

    public void setNext(FrequencyNode next) {
        this.next = next;
    }
}
