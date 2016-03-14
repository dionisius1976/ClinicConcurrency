package ru.lesson.lessons;

import java.util.*;

/**
 * Created by trit on 03.03.2016.
 */
public class SimpleArrayList<E> implements List<E> {

       protected Object[] elementData;

    /**
     * Constructor without params
     * @return empty list
     */
    public SimpleArrayList(){ this (0); }

    /**
     * Constructor with params
     * @param i - initial size of new MyArrayList
     */
    public SimpleArrayList(int i){ this.elementData = new Object[i]; }

    /**
     *
     * @return the size of this list
     */
    @Override
    public int size() {
        return this.elementData.length;
    }

    /**
     * @return true if this list is empty and false if this list
     * contains some elements
     */
    @Override
    public boolean isEmpty() {return this.elementData.length == 0;}

    /**
     * This method clears this list.
     * After that this list is empty.
     */
    @Override
    public void clear() {
        elementData = new Object[0];
    }


    /**
     * This method checks if the index is not out of bonds of this list
     * @param index index in elementData array
     * @return true if is not out of bonds
     * @throws IndexOutOfBoundsException
     */
    public boolean checkIndex(int index){
        if (index < 0 || index > this.size() - 1) {
            throw new IndexOutOfBoundsException("Invalid value of index. Index = " + index);
        }
        return true;
    }

    /**
     * This method checks if the object o presents in this list
     * @param o checked object
     * @return if the object o exists in elementData, false otherwise
     */
    private boolean checkObjekt(Object o){
        return o != null;
    }

    /**
     * This method returns the first index of object o in this list
     * @param o equired object
     * @return index of object o in the list or -1 if there is no such object
     * in this list
     */
    @Override
    public int indexOf(Object o) {
            if (o == null) {
                for (int i = 0; i < this.size(); i++) if (this.get(i) == null) return i;
            }
            for (int i = 0; i < this.size(); i++) if (o.equals(this.get(i))) return i;
        return -1;
    }

    /**
     * This method returns the last index of object o in this list
     * @param o equired object
     * @return the last index of object o in this list or -1 if there is no such object
     * in this list
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = this.size() - 1; i >= 0; i--) if (elementData[i] == null) return i;
        }
        for (int i = this.size() - 1; i >= 0; i--) if (o.equals(elementData[i])) return i;
        return -1;
    }

     /**
      * Returns an array containing all of the elements in this list
      * in proper sequence (from first to last element).
      * @return an array containing all of the elements in this list in
      *         proper sequence
      */
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * This method appends the specified element to the end of this list.
     * @param e added element
     * @return true
     */
    @Override
    public boolean add(E e) {
        this.increase(1);
        this.set(this.size() - 1, e);
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException
     */
    @Override
    public void add(int index, E element) {
        if (this.checkIndex(index)) {
            Object[] temp = new Object[this.size() + 1];
            System.arraycopy(elementData, 0, temp, 0, index);
            temp[index] = element;
            System.arraycopy(elementData, index, temp, index + 1, this.size() - index);
            elementData = new Object[temp.length];
            elementData = temp;
        }
    }

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the
     * specified collection's Iterator.
     *
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        int lastIndex = this.size();
        this.increase(c.size());
        System.arraycopy(c.toArray(), 0, elementData, lastIndex, c.size());
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.  Shifts the element
     * currently at that position (if any) and any subsequent elements to
     * the right (increases their indices).  The new elements will appear
     * in the list in the order that they are returned by the
     * specified collection's iterator.
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c collection containing elements to be added to this list
     * @return <tt>true</tt> if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (this.checkIndex(index)){
            Object[] temp = new Object[this.size() + c.size()];
            System.arraycopy(elementData, 0, temp, 0, index);
            System.arraycopy(c.toArray(), 0, temp, index, c.size());
            System.arraycopy(elementData, index, temp, index + c.size() ,this.size() - index);
            elementData = temp;
            temp = null;
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contain the element, it is
     * unchanged.
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < this.size(); i++)
                if (this.get(i) == null) {
                    this.remove(i);
                    return true;
                }
            return false;
        }
        for (int i = 0; i < this.size(); i++)
            if (o.equals(this.get(i))) {
                this.remove(i);
                return true;
            }
        return false;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        E returningObject = (E)this.get(index);
        Object[] temp = new Object[this.size() - 1];
        System.arraycopy(elementData, 0, temp, 0, index);
        System.arraycopy(elementData, index + 1, temp, index, this.size() - (index + 1));
        elementData = temp;
        temp = null;
        return returningObject;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection.
     *
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     *
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *         or if the specified collection is null
     * @see Collection#contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (int i = 0; i < this.size(); i++) {
            if (c.contains(this.get(i))) { this.remove(i); i--; }
        }
        return true;
    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection.
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     *
     * @throws NullPointerException if this list contains a null element and the
     *         specified collection does not permit null elements
     *         or if the specified collection is null
     */
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
            for (int i = 0; i < this.size(); i++) {
                if(!c.contains(this.toArray()[i])) {
                    remove(i);
                    i--;
                }
            }
            return true;
    }

    /**
     * Returns true if this list contains the specified element.
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        if (o == null){ for (int i = 0; i < this.size(); i++) if (this.get(i) == null) return true;}
        else for (int i = 0; i < this.size(); i++) if (o.equals(this.get(i))) return true;
        return false;
    }

    /**
     * Returns true if this list contains the specified elements of collection c.
     * @param c - collection with elements whose presence in this list is to be tested
     * @return true if this list contains all the specified elements of collection c
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] temp = c.toArray();
        for (int i = 0; i < c.size(); i++)
            if (!this.contains(c.toArray()[i])) return false;
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E get(int index) {
        return (E)this.elementData[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        E returningObject = (E)elementData[index];
        elementData[index] = element;
        return returningObject;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {

        subListRangeCheck(fromIndex, toIndex, this.size());
        SimpleArrayList<E> returningList = new SimpleArrayList<E>();
        for (int i = fromIndex; i <= toIndex; i++) returningList.add(this.get(i));
        return returningList;
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
    }

    /** Increases the size of this list on the i empty elements. These empty elements
     * appends to the end of this list.
     * @param i the number of adding empty elements
     * @return true
     */
    private boolean increase(int i){
        Object[] temp = new  Object[this.size() + i];
        System.arraycopy(elementData, 0, temp, 0, this.size());
        elementData = temp;
        temp = null;
        return true;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the first position in the list.
     */
    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the first position in the list.
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator(-1);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        return new MyListIterator(index);
    }


    private class MyIterator implements Iterator<E>{

        protected int cursor = -1;

        @Override
        public boolean hasNext() {
            if(cursor >= elementData.length-1)
                return false;
            return true;
        }

        @Override
        public E next() {
            cursor++;
            return (E)elementData[cursor];
        }

        @Override
        public void remove() {
            if(cursor>=0) {
                Object[] newArray = new Object[elementData.length - 1];
                System.arraycopy(elementData, 0, newArray, 0, cursor);
                System.arraycopy(elementData, cursor + 1, newArray, cursor, newArray.length - (cursor));
                elementData = newArray;
            }
        }
    }

    private class MyListIterator extends MyIterator implements ListIterator<E> {

        private int index;

        MyListIterator(int index){
            super();
            this.index = index;
            if((index >= elementData.length)||(index <= -1))
                cursor = -1;
            else
                cursor = index;
        }

        @Override
        public boolean hasNext() {
            if((cursor > elementData.length - 1)||(cursor <- 1)||index == elementData.length - 1)
                return false;
            return true;
        }

        @Override
        public E next() {
            if (cursor >= elementData.length)
                throw new NoSuchElementException();
            cursor++;
            return (E)elementData[cursor];
        }

        @Override
        public boolean hasPrevious() {
            if((cursor >= elementData.length)||(cursor<=0))
                return false;
            return true;
        }

        @Override
        public E previous() {
            cursor--;
            if(cursor<=-1)
                return null;
            return (E)elementData[cursor];
        }

        @Override
        public int nextIndex() {
            return cursor+1;
        }

        @Override
        public int previousIndex() {
            if(cursor<=-1){
                cursor = -1;
                return cursor;}
            return cursor-1;
        }

        @Override
        public void remove() {
            if(cursor>=0) {
                Object[] newArray = new Object[elementData.length - 1];
                System.arraycopy(elementData, 0, newArray, 0, cursor);
                System.arraycopy(elementData, cursor + 1,
                        newArray, cursor,
                        newArray.length - (cursor));
                elementData = newArray;
            }
        }

        @Override
        public void set(E e) {
            if(cursor>-1)
                if(checkObjekt(e))
                    elementData[cursor]=e;
        }

        @Override
        public void add(E e) {
            if(checkObjekt(e)){
                increase(1);
                elementData[elementData.length]=e;
            }
        }
    }




}
