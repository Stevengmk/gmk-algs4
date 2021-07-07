/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * at most 48n + 192 bytes of memory
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private int size;

    private Node<Item> head;
    private Node<Item> tail;

    private class Node<Item>
    {
        private Item item;
        private Node<Item> pre;
        private Node<Item> next;
        public Node(Item val)
        {
            item = val;
            next = null;
            pre = null;
        }
    }

    // construct an empty deque
    public Deque()
    {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // constant worst-case time
    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("item not null !");
        if (isEmpty())
        {
            head = new Node<Item>(item);
            tail = head;
        } else {
            Node<Item> oldHead = head;
            Node<Item> first = new Node<Item>(item);
            head = first;
            first.next = oldHead;
            oldHead.pre = head;
        }
        size++;
    }

    // constant worst-case time
    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
            throw new IllegalArgumentException("item not null !");
        if (isEmpty())
        {
            head = new Node<Item>(item);
            tail = head;
        } else {
            Node<Item> oldTail = tail;
            Node<Item> end = new Node<Item>(item);
            oldTail.next = end;
            tail = end;
            end.pre = oldTail;
        }
        size++;
    }

    // constant worst-case time
    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
            throw new NoSuchElementException("deque is null !");
        Item ret = head.item;
        size--;
        // newHead may be null
        Node<Item> newHead = head.next;
        if (newHead != null)
            newHead.pre = null;
        head = newHead;
        if (isEmpty())
            tail = head;
        return ret;
    }

    // constant worst-case time
    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
            throw new NoSuchElementException("deque is null !");
        Item ret = tail.item;
        // newTail may be null 这种写法是否合适
        Node<Item> newTail = tail.pre;
        if (newTail != null)
            newTail.next = null;
        tail = newTail;
        size--;
        if (isEmpty())
            head = tail;
        return ret;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new DequeIterator<>(head);
    }

    private class DequeIterator<Item> implements Iterator<Item>
    {
        private Node<Item> current;

        // constant worst-case time
        public DequeIterator(Node<Item> head)
        {
            this.current = head;
        }

        // constant worst-case time
        public boolean hasNext() {
            return current != null;
        }

        // constant worst-case time
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException("no more item");
            Item ret = current.item;
            current = current.next;
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException("remove method not supported");
        }
    }

    // unit testing (required), call directly every public constructor and method
    public static void main(String[] args)
    {
        Deque<Integer> deque = new Deque<>();
        // StdOut.println(deque.isEmpty());
        // deque.addFirst(5);
        // deque.addFirst(4);
        // StdOut.println(deque.size());
        // deque.addFirst(3);
        // deque.addLast(6);
        // deque.addLast(7);
        // deque.addFirst(2);
        // deque.removeFirst();
        // StdOut.println(deque.size());
        // deque.removeLast();
        // StdOut.println(deque.isEmpty());
        deque.addLast(1);
        deque.removeLast();
        for (int i : deque)
        {
            StdOut.println(i);
        }
    }

}