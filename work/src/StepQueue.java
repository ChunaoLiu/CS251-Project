public class StepQueue {

    private int size;
    private Node head;
    private Node tail;

    public StepQueue() {
        //TODO: Implement constructor()
        size = 0;
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Step peek() {
        return tail.step;
    }

    public void enqueue(Step step) {
        //TODO: Implement enqueue()
        StepQueue.Node newNode = new Node();
        newNode.step = step;
        newNode.next = Null;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size += 1;
    }

    public Step dequeue() throws EmptyQueueException {
        //TODO: Implement dequeue()
        Node temp = head;
        head = head.next;
        return temp.step;
    }


    /**
     * If the linked list is: Up->Down->Left->Right,
     * toString should return "UDLR"
     */
    @Override
    public String toString() {
        //TODO: Implement toString
        Node temp = head;
        StringBuilder output = new StringBuilder();
        while (temp != null) {
            output.append(temp.step.letter);
            temp = temp.next;
        }
        return output.toString();
    }

    private static class Node {
        Step step;
        Node next;
    }
}


