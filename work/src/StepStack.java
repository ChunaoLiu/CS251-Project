public class StepStack {
    private Step[] stack;
    private int size;

    public StepStack() {
        //TODO: Implement constructor
        size = 0;
        stack = new Step[1];
    }

    public Step[] resize_step(Step[] step, float ratio) {
        Step[] new_stack = new Step[(int)(size * ratio)];
        System.arraycopy(step, 0, new_stack, 0, step.length);
        return new_stack;
    }

    public void push(Step step) {
        //TODO: Implement push()
        if (size == stack.length) {
            stack = resize_step(stack, 2);
        }
        stack[size] = step;
        size ++;
    }

    public Step peek() throws EmptyStackException {
        //TODO: Implement peek()
        return stack[size];
    }

    public Step pop() throws EmptyStackException {
        //TODO: Implement pop()
        if (size == 0) throw new EmptyStackException();
        Step temp = stack[size];
        stack[size] = null;
        size --;
        if (size <= stack.length * 0.25) {
            resize_step(stack, 0.5f);
        }
        return temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        String path = "";
        for (int i = 0; i < size; i++) {
            if (stack[i] != null) {
                {
                    path += stack[i];
                }
            }
        }
        return path;
    }
}