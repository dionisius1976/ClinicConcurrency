package ru.lesson.lessons;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by trit on 07.03.2016.
 */
public class MyArrayListTest {

    SimpleArrayList<Date> arr;
    SimpleArrayList<Date> arr2;
    Date date = new Date();
    Date date2 = new Date(1000);
    Date date3 = new Date (2000);
    Date date4 = new Date (4000);
    Date date5 = new Date (5000);

    @Before
    public void setup(){
        arr = new SimpleArrayList<Date>();
        arr.add(date);
        arr.add(null);
        arr.add(date2);
        arr.add(date3);
        arr.add(date4);
        arr2 = new SimpleArrayList<Date>();
        arr2.add(date2);
        arr2.add(date5);
        arr2.add(null);
    }

    @Test
    public void testSize() throws Exception {
        this.setup();
        Assert.assertSame(5, arr.size());
        arr.add(date5);
        Assert.assertSame(6, arr.size());
        arr.clear();
        Assert.assertSame(0, arr.size());
    }

    @Test
    public void testIsEmpty() throws Exception {
        this.setup();
        Assert.assertFalse(arr.isEmpty());
        arr.clear();
        Assert.assertTrue(arr.isEmpty());
    }

    @Test
    public void testClear() throws Exception {
        this.setup();
        arr.clear();
        Assert.assertTrue(arr.isEmpty());
    }

    @Test
    public void testCheckIndex() throws Exception {
        this.setup();
        Assert.assertTrue(arr.checkIndex(0));
        Assert.assertTrue(arr.checkIndex(4));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testCheckIndexException1() throws Exception {
        this.setup();
        Assert.assertTrue(arr.checkIndex(arr.size()));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testCheckIndexException2() throws Exception {
        this.setup();
        Assert.assertTrue(arr.checkIndex(-1));                      // index < 0;
    }

    @Test
    public void testContains() throws Exception {
        this.setup();
        Assert.assertTrue(arr.contains(date));
        Assert.assertTrue(arr.contains(null));
        Assert.assertFalse(arr.contains(date5));
    }

    @Test
    public void testIndexOf() throws Exception {
        this.setup();
        Assert.assertSame(0, arr.indexOf(date));
        Assert.assertSame(1, arr.indexOf(null));
        Assert.assertSame(-1, arr.indexOf(date5));
    }

    @Test
    public void testLastIndexOf() throws Exception {
        this.setup();
        Assert.assertSame(3, arr.lastIndexOf(date3));
        Assert.assertSame(1, arr.lastIndexOf(null));
        Assert.assertSame(-1, arr.indexOf(date5));
    }

    @Test
    public void testToArray() throws Exception {
        this.setup();
        Object[] obj = new Object[arr.size()];
        obj = arr.toArray();
        for (int i = 0; i < obj.length - 1; i++) {
            assertSame(obj[i], arr.get(i));
        }
    }

    @Test
    public void testAdd() throws Exception {
        this.setup();
        int size = arr.size();
        arr.add(date5);
        arr.add(null);
        Assert.assertSame(date5, arr.get(5));
        Assert.assertSame(null, arr.get(6));
        Assert.assertSame(size + 2, arr.size());
    }

    @Test
    public void testAdd1() throws Exception {
        this.setup();
        int size = arr.size();
        arr.add(1, date5);
        Assert.assertSame(date5, arr.get(1));
        Assert.assertSame(size + 1, arr.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAdd1Exception1() throws Exception {
        this.setup();
        arr.add(6, date5);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAdd1Exception2() throws Exception {
        this.setup();
        arr.add(-1, date5);                                             // index < 0;
    }

    @Test
    public void testAddAll() throws Exception {
        this.setup();
        int size = arr.size();
        arr.addAll(arr2);
        for (int i = size; i < arr.size(); i++) {
            Assert.assertSame(arr.get(i), arr2.get(i - size));
        }
        Assert.assertSame(size + arr2.size(), arr.size());
    }

    @Test
    public void testAddAll1() throws Exception {
        this.setup();
        int index = 1;
        int size = arr.size();
        arr.addAll(index, arr2);
        for (int i = index; i < arr2.size() - 1 ; i++) {
            Assert.assertSame(arr.get(i), arr2.get(i - index));
        }
        Assert.assertSame(size + arr2.size(), arr.size());
    }


    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddAll1Exception1() throws Exception {
        this.setup();
        arr.addAll(6, arr2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddAll1Exception2() throws Exception {
        this.setup();
        arr.addAll(-1, arr2);                                           // index < 0;
    }

    @Test
    public void testRemove() throws Exception {
        this.setup();
        int size = arr.size();
        Assert.assertTrue(arr.remove(date3));
        Assert.assertFalse(arr.contains(date3));
        Assert.assertFalse(arr.remove(date3));
        Assert.assertTrue(arr.remove(null));
        Assert.assertSame(size - 2, arr.size());
    }

    @Test
    public void testRemove1() throws Exception {
        this.setup();
        int size = arr.size();
        arr.remove(3);
        Assert.assertFalse(arr.contains(date3));
        Assert.assertSame(size - 1, arr.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemove1Exception1() throws Exception {
        this.setup();
        arr.remove(6);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemove1Exception2() throws Exception {
        this.setup();
        arr.remove(-1);
    }

    @Test
    public void testRemoveAll() throws Exception {
        this.setup();
        arr.add(date2);
        arr.removeAll(arr2);
        for (int i = 0; i < arr2.size() ; i++) {
            Assert.assertFalse(arr.contains(arr2.get(i)));
        }
    }

    @Test
    public void testRetainAll() throws Exception {
        this.setup();
        arr.retainAll(arr2);
        Assert.assertSame(null, arr.get(0));
        Assert.assertSame(date2, arr.get(1));
        Assert.assertSame(2, arr.size());
    }

    @Test
    public void testContainsAll() throws Exception {
        this.setup();
        SimpleArrayList<Date> arr3 = new SimpleArrayList<Date>();
        for (int i = arr.size() - 1; i >= 0 ; i--) {
            arr3. add(arr.get(i));
        }
        Assert.assertFalse(arr.containsAll(arr2));
        Assert.assertTrue(arr.containsAll(arr3));
    }

    @Test
    public void testGet() throws Exception {
        this.setup();
        Assert.assertSame(date, arr.get(0));
        Assert.assertSame(null, arr.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetException1() throws Exception {
        this.setup();
        arr.get(arr.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetException2() throws Exception {
        this.setup();
        arr.get(-1);
    }

    @Test
    public void testSet() throws Exception {
        this.setup();
        int size = arr.size();
        arr.set(1, date2);
        Assert.assertSame(date2, arr.get(1));
        Assert.assertSame(size, arr.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetException1() throws Exception {
        this.setup();
        arr.set(6, date2);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSetException2() throws Exception {
        this.setup();
        arr.set(-1, date2);
    }

    @Test
    public void testSubList() throws Exception {
        this.setup();
        int fromIndex = 1;
        int toIndex = 3;
        List<Date> sub = arr.subList(fromIndex, toIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            Assert.assertSame(arr.get(i), sub.get(i - fromIndex));
        }
        Assert.assertSame(toIndex - fromIndex + 1, sub.size());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListException1() throws Exception {
        this.setup();
        List<Date> sub = arr.subList(-1, 3);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testSubListException2() throws Exception {
        this.setup();
        List<Date> sub = arr.subList(1, 6);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSubListException3() throws Exception {
        this.setup();
        List<Date> sub = arr.subList(2, 1);
    }

    @Test
    public void testIterator() throws Exception {
        this.setup();
        assertEquals(date, arr.listIterator().next());
        assertTrue(arr.listIterator().hasNext());
    }

    @Test
    public void testListIterator() throws Exception {
        this.setup();
        assertSame(null, arr.listIterator(0).next());
        assertSame(date4, arr.listIterator(3).next());
        assertTrue(arr.listIterator(3).hasNext());
        assertFalse(arr.listIterator(4).hasNext());
        assertFalse(arr.listIterator(0).hasPrevious());
        assertSame(date2, arr.listIterator(3).previous());
        assertSame(4, arr.listIterator(3).nextIndex());
        assertSame(2, arr.listIterator(3).previousIndex());
    }

    @Test
    public void testListIterator1() throws Exception {
        this.setup();
        assertSame(null, arr.listIterator(0).next());
        assertSame(date4, arr.listIterator(3).next());
        assertTrue(arr.listIterator(3).hasNext());
        assertFalse(arr.listIterator(4).hasNext());
        assertFalse(arr.listIterator(0).hasPrevious());
        assertSame(date2, arr.listIterator(3).previous());
        assertSame(4, arr.listIterator(3).nextIndex());
        assertSame(2, arr.listIterator(3).previousIndex());
    }
}