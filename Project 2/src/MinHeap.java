public class MinHeap {
    private int[] array;

    /**
     * Constructor to initialize the heap
     * @param capacity capacity of the heap - i.e. the maximum elements that can be stored
     */
    public MinHeap(int capacity) {
        array = new int[capacity + 1];
    }

    //

    /**
     * swap score at indexOne with score at indexTwo
     * @param indexOne - to be swapped
     * @param indexTwo - to be swapped
     */
    public void swap(int indexOne, int indexTwo) {
        int temp = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = temp;
    }

    //

    /**
     * score at index swims up in heap
     * If the heap order is violated because a node's key becomes smaller than that node's parent's key,
     * then swap the node with its parents. Keep swimming up the heap until reaching a node with a
     * smaller key or the root. The worst-case time complexity should not be more than O(log(N)).
     * @param index - to swim up
     */
    public void swim(int index) {
        //TODO: Implement swim
        swap(index, ((index - 2) / 2 + 1));
    }


    /**
     * score at index sinks down in heap
     * If the heap order is violated because a node's key becomes larger than one or both of its
     * children's keys, then swap the node with the smaller one of its children. Keep sinking until
     * reaching a node that has two larger children. The worst-case time complexity should not be
     * more than O(log(N)).
     * @param index - to sink
     */
    public void sink(int index) {
        //TODO: Implement sink
        if (array[(index - 1) * 2 + 2] >= array[(index - 1) * 2 + 3]) {
            swap(index, (index - 1) * 2 + 3);
        } else {
            swap(index, (index - 1) * 2 + 2);
        }
    }


    /**
     * insert score in the queue
     * @param score to insert in the heap
     * @return the final index at which the score is stored or
     * return -1 if the score could not be inserted
     */
    public int insert(int score) {
        //TODO: Implement insert
        if (size() == array.length - 1) return -1;
        array[size() + 1] = score;
        int index = size();
        int parent = 0;
        while (true) {
            if (index <= 1) {
                break;
            }
            parent = ((index - 2) / 2) + 1;
            if (array[index] < array[parent]) {
                swim(index);
                index = parent;
                if (parent == 1) {
                    break;
                }
            } else {
                break;
            }
        }
        return parent;
    }

    private int minHeapify(int pos) {

        if (!isLeaf(pos)) {
            int leftChild = 2 * (pos - 1) + 2;
            int rightChild = 2 * (pos - 1) + 3;

            if (array[pos] == 0) {
                return -1;
            }

            if (array[pos] > array[leftChild] || array[pos] > array[rightChild]) {
                if (array[leftChild] <= array[rightChild]) {
                    swap(pos, leftChild);
                    minHeapify(leftChild);
                } else if (array[leftChild] > array[rightChild]) {
                    swap(pos, rightChild);
                    minHeapify(rightChild);
                }
            }
        }
        return 0;
    }

    private boolean isLeaf(int index) {
        if (((index - 1) * 2 + 2) >= size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * remove and return the lowest score from the top of the heap
     * @return the lowest score
     */
    public int delMin() {
        //TODO: Implement delMin
        int output = getMin();
        if (size() == 0) {
            return -1;
        }

        //System.out.println("Before: " + Arrays.toString(array));

        array[1] = array[size()];
        array[size()] = 0;

        int result = minHeapify(1);

        //System.out.println("After: " + Arrays.toString(array));

        return output;
    }




    /**
     * return but do not remove the first score in the queue
     * @return the min score
     */
    public int getMin() {
        if (isEmpty()) {
            return -1;
        }
        return array[1];
    }

    /**
     * return the number of scores currently in the queue
     * note: value of 0 indicates no score at specified index in the queue
     * @return the total elements
     */
    public int size() {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                count++;
            }
        }
        return count;
    }


    /**
     * return a boolean based on whether the queue is empty or not
     * @return true if queue is empty else false
     */
    public boolean isEmpty() {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {
                return false;
            }
        }
        return true;
    }

    //

    /**
     * DO NOT CHANGE
     * used for testing underlying data structure
     * @return the underlying array
     */
    public int[] getArray() {
        return array;
    }
}
