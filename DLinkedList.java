package il.ac.telhai.ds.stack;


public class DLinkedList<T> implements List<T> {
    DNode head;
    DNode cursor;
    DNode tail;

    public DLinkedList() {
        head = null;
        cursor = null;
        tail = null;
    }
    //insert function inserts a new element
    //the function checks if the list is empty and acts accordingly
    public void insert(T newElement) {
        if (newElement==null) throw new RuntimeException();
        DNode mynote = new DNode(newElement);
        //if is empty
        if (head == null)
        {
            head = mynote;
            head.next = null;
            head.prev = null;
            cursor = head;
            tail = head;

            return;
        }
        //if not empty
        mynote.setPrev(cursor);
        mynote.setNext(cursor.getNext());
        if (mynote.getNext()!=null) mynote.getNext().setPrev(mynote);
        cursor.setNext(mynote);
        if(mynote.getNext()==null) tail=mynote;
        cursor = mynote;
    }

    @Override
    //removes an item
    //when removing an item i distinguish beetwen 3 option:
    //first option : cursor points to tail and and tail is not head
    //second option : cursor points to head and its the only item
    //third option : cursor point to head and it is not the only item
    //fourth option: cursor points not to head and not to tail
    public T remove() {
        if (isEmpty())  return null;
        T element = cursor.getElement();
        //first
        if (cursor==tail && cursor!=head){
            cursor.getPrev().setNext(null);
            cursor = cursor.getPrev();
            tail = cursor;
            return element;
        }
        //if cursor is on head
        if (cursor==head )
        {
            //second
            if ( cursor.getNext()==null)
            {
                clear();
                return element;
            }
            //third
            cursor.getNext().setPrev(null);
            cursor =cursor.getNext();
            head = cursor;
            return element;
        }
        //fourth option
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        cursor = cursor.getNext();
        return element;




    }

    //second remove finds where the desired item is located and moves the cursor to there and then use the previous remove.
    //if no item is found returns null
    @Override
    public T remove(T element) {
        if (isEmpty()) return null;
        DNode temp = head;
        while(!temp.getElement().equals(element) && temp.getNext()!=null)
        {
            temp = temp.getNext();
        }
        if (temp.getElement().equals(element))
        {
                cursor = temp;
                remove();
                return temp.getElement();


        }
        return null;

    }

    @Override
    //everything is set as null
    public void clear() {
        head = null;
        cursor = null;
        tail = null;

    }
    //next functions are rather simple, so I felt there is no need for documentation


    @Override
    public void replace(T newElement) {
            if (newElement ==null || head==null) throw new RuntimeException();
            cursor.element =newElement;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if(head==null) return false;
        cursor = head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(head==null) return false;
        cursor = tail;
        return true;
    }

    @Override
    public T getNext() {
        if (head==null) return null;
        if(cursor.getNext()==null) return null;

        cursor = cursor.getNext();
        return cursor.getElement();
    }

    @Override
    public T getPrev() {
        if (isEmpty()) return null;
        if(cursor.getPrev()==null) return null;

        cursor = cursor.getPrev();
        return cursor.getElement();
    }

    @Override
    public T getCursor() {
        if (head==null) return null;
        return cursor.getElement();
    }

    @Override
    public boolean hasNext() {
        if (head==null) return false;
        return cursor.getNext() != null ;
    }

    @Override
    public boolean hasPrev() {
        if (head==null) return false;
        return cursor.getPrev() != null;
    }

    public String toString()
    {
        StringBuilder str= new StringBuilder("[");
        DNode tmp = tail;
        if (tmp==null) return null;
        while(tmp.getPrev()!=null)
        {
            str.append((tmp.getElement().toString()));
            str.append(", ");
            tmp = tmp.getPrev();
        }
        str.append(tmp.getElement());
        str.append("]");
        return String.valueOf(str);
    }



    //node class
    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;

        }

    }
}