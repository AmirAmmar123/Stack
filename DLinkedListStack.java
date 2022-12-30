package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T> {
    private final List<T> dLinkedList;

    public DLinkedListStack(){
        dLinkedList = new DLinkedList<>();

    }
    @Override
    public void push(T t) {
        if(t == null) throw new NullPointerException();
        dLinkedList.insert(t);
    }

    @Override
    public T pop() {
        if(isEmpty()) return null;
        T tail = dLinkedList.getCursor();
        dLinkedList.remove(tail);
        return tail;
    }

    @Override
    public T top() {
        dLinkedList.goToEnd();
        return dLinkedList.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return dLinkedList.isEmpty();
    }

    @Override
    public String toString() {
        if(isEmpty()) return "[]" ;
        return dLinkedList.toString();
    }
}
