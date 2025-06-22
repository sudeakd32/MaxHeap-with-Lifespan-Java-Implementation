import java.util.*;

class Event{
    int priority;
    int lifespan;
    Event(int priority, int lifespan){
        this.priority = priority;
        this.lifespan = lifespan;
    }
}

class MaxHeap {
    ArrayList<Event> heap = new ArrayList<>();
    private int heapSize = 0;

    private int getParentIndex(int i){
        return i/2;
    }

    private int getLeftChildIndex(int i){
        return 2*i;
    }

    private int getRightChildIndex(int i){
        return 2*i+1;
    }

    private void swap(int i, int j){

        Event temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private void heapifyUp(int i) {
        while (i > 0) {
            int parentIndex = getParentIndex(i);
            Event current = heap.get(i);
            Event parent = heap.get(parentIndex);

            if (current.priority > parent.priority) {
                swap(i, parentIndex);
                i = parentIndex;
            } else if (current.priority == parent.priority) {
                if (current.lifespan > parent.lifespan) {
                    swap(i, parentIndex);
                    i = parentIndex;
                } else
                    break;
            } else
                break;
        }
    }

    private void heapifyDown(int i) {
        int maxIndex = i;
        int left = getLeftChildIndex(i);
        int right = getRightChildIndex(i);

        //Compare with left child
        if (left < heapSize) {
            if (heap.get(left).priority > heap.get(maxIndex).priority ||
                    (heap.get(left).priority == heap.get(maxIndex).priority &&
                            heap.get(left).lifespan > heap.get(maxIndex).lifespan)) {
                maxIndex = left;
            }
        }

        //Compare with right child
        if (right < heapSize) {
            if (heap.get(right).priority > heap.get(maxIndex).priority ||
                    (heap.get(right).priority == heap.get(maxIndex).priority &&
                            heap.get(right).lifespan > heap.get(maxIndex).lifespan)) {
                maxIndex = right;
            }
        }
        if (maxIndex != i) {
            swap(i, maxIndex);
            heapifyDown(maxIndex);
        }

    }

    public void insert(int priority, int lifespan) {
        heap.add(new Event(priority, lifespan));
        heapSize++;
        heapifyUp(heapSize-1);
    }

    public Event peekMax(){

        return heapSize == 0 ? null : heap.getFirst();

    }

    public Event extractMax() {
        if (heapSize == 0) return null;

        Event max = peekMax();
        //swap root with last element
        swap(0, heapSize-1);
        heapSize--;
        heapifyDown(0);

        //delete root
        ArrayList<Event> newHeap = new ArrayList<>();
        for (int i=0; i<heapSize; i++) {
            newHeap.add(heap.get(i));
        }
        heap = newHeap;
        heapifyDown(0);
        heapifyUp(heapSize-1);

        return max;
    }

    public void decrementLifespan() {

        ArrayList<Event> newHeapDecrementLifespan = new ArrayList<>();
        for (int i=0; i<heapSize; i++) {
            newHeapDecrementLifespan.add(new Event(heap.get(i).priority, heap.get(i).lifespan-1));
        }
        heap = newHeapDecrementLifespan;
    }

    private void removeExpiredEventsHelper(){

        ArrayList<Event> newHeap = new ArrayList<>();
        int size = 0;
        for (int i=0; i<heapSize; i++) {
            if(heap.get(i).lifespan > 0){
                newHeap.add(heap.get(i));
                size++;
            }

        }
        heap = newHeap;
        heapSize = size;
    }

    public void removeExpiredEvents(){
        removeExpiredEventsHelper();
    }

    public void printHeapTree() {
        System.out.println("\nHeap Tree (priority-lifespan):");

        if (heap.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        int level = 0;
        int itemsOnLevel = 1;
        int index = 0;

        while (index < heap.size()) {
            System.out.print("Level " + level + ": ");

            for (int i = 0; i < itemsOnLevel && index < heap.size(); i++, index++) {
                Event e = heap.get(index);
                System.out.print("(" + e.priority + "," + e.lifespan + ") ");
            }

            System.out.println();
            level++;
            itemsOnLevel *= 2;
        }
    }


}
public class Main2 {
    public static void main(String[] args) {

        MaxHeap maxHeap = new MaxHeap();
        Random rand = new Random();

        for(int i=0; i<20; i++){
            maxHeap.insert(rand.nextInt(100)+1, rand.nextInt(5)+1);
        }

        maxHeap.printHeapTree();

        Event extractedEvent;
        for(int i=0; i<10; i++){
            if(maxHeap.heap.isEmpty()) break;
            extractedEvent = maxHeap.extractMax();
            System.out.println("Extracted event -> Priority: "+extractedEvent.priority+", Original Lifespan: "+(extractedEvent.lifespan+i));

            maxHeap.decrementLifespan();
            maxHeap.removeExpiredEvents();

            maxHeap.printHeapTree();

        }

    }
}