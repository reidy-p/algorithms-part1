import java.util.Iterator;

public class LinkedDeque<Item> implements Iterable<Item>
{
    private Node first, last;
    
    private class Node
    { 
        Item item;
        Node next; 
    }

    public boolean isEmpty()
    { return first == null; }
    
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
     
    }

    public void addLast(Item item)
    {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
    }
    
    public Item removeFirst()
    {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        return item;
    }

    public Item removeLast()
    {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        Node oldlast = last;
        Item item = oldlast.item; 
        oldlast = null;

        // Move last back one
        Node current = first;
        while (current.next != null)
        {
            last = current;
            current = current.next;
        }
        return item;
    }   

    public Iterator<Item> iterator() { return new ListIterator(); }

    private class ListIterator implements Iterator<Item>
    {
 	    private Node current = first;
 	    public boolean hasNext() { return current != null; }
 	    public void remove() { throw new UnsupportedOperationException(); }
 	    public Item next()
        {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

	        Item item = current.item;
	        current = current.next;
	        return item;
	    }
    }

    public int size()
    {
        int count = 1;
        Node current = first;
        while (current.next != null) 
        {
            current = current.next;
            count++; 
        } 
        return count;
    }

    public static void main(String[] args)
    {
        LinkedDeque<String> test = new LinkedDeque<String>();
        String a = "One";
        test.addLast(a);
        String b = "Two";
        test.addLast(b);
        String c = "Three";
        test.addLast(c);
        System.out.println(test.size());

        System.out.println(test.removeFirst());
        test.addFirst(a);
        System.out.println(test.removeLast());
        test.addLast(a);
        test.addLast(a);
        System.out.println(test.size());

        for (String s : test)
            System.out.println(s);

    }
} 
