package com.coding.basic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;

public class LinkedListTest {
	
	LinkedList linkedList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
	}

	@Test
	public void testAddObject() {
		linkedList.add(null);
		assertEquals(linkedList.size(), 0);
		linkedList.add(new Integer(0));
		assertEquals(linkedList.size(), 1);
		AssertEqualIntValue(0, 0);
		linkedList.add(new Integer(1024));
		linkedList.add(new Integer(1));
		assertEquals(linkedList.size(), 3);
		AssertEqualIntValue(1, 1024);
		AssertEqualIntValue(2, 1);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 2);
		AssertEqualIntValue(0, 1024);
		AssertEqualIntValue(1, 1);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 1);
		AssertEqualIntValue(0, 1);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 0);
	}

	private void AssertEqualIntValue(int index, int expectedValue) {
		assertEquals(expectedValue, ((Integer)linkedList.get(index)).intValue());
	}

	@Test
	public void testAddIntObject() {
		linkedList.add(0, new Integer(0));
		assertEquals(1, linkedList.size());
		AssertEqualIntValue(0, 0);
		linkedList.add(0, new Integer(1024));
		linkedList.add(0, new Integer(1));//1, 1024, 0
		assertEquals(linkedList.size(), 3);
		AssertEqualIntValue(1, 1024);
		AssertEqualIntValue(2, 0);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 2);//1024, 0
		AssertEqualIntValue(0, 1024);
		AssertEqualIntValue(1, 0);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 1);//0
		AssertEqualIntValue(0, 0);
		linkedList.remove(0);
		assertEquals(linkedList.size(), 0);
	}

	@Test
	public void testGet() {
		linkedList.add(new Integer(0));
		AssertEqualIntValue(0, 0);
		linkedList.add(new Integer(1));
		linkedList.add(new Integer(2));
		AssertEqualIntValue(1, 1);
		AssertEqualIntValue(2, 2);
		linkedList.remove(0);
		AssertEqualIntValue(0, 1);
		AssertEqualIntValue(1, 2);
	}

	@Test
	public void testAddFirst() {
		linkedList.add(new Integer(0));
		linkedList.addFirst(new Integer(1024));
		AssertEqualIntValue(0, 1024);
	}

	@Test
	public void testAddLast() {
		linkedList.add(new Integer(0));
		linkedList.addLast(new Integer(1024));
		AssertEqualIntValue(0, 0);
		AssertEqualIntValue(1, 1024);
	}

	@Test
	public void testRemoveFirst() {
		linkedList.add(new Integer(0));
		linkedList.addLast(new Integer(1024));
		linkedList.removeFirst();
		AssertEqualIntValue(0, 1024);
		assertEquals(linkedList.size(), 1);
	}

	@Test
	public void testRemoveLast() {
		linkedList.add(new Integer(0));
		linkedList.addFirst(new Integer(1024));
		linkedList.removeLast();
		AssertEqualIntValue(0, 1024);
		assertEquals(linkedList.size(), 1);
	}

	@Test
	public void testIterator() {
		for(int i=0; i<10; ++i)
		{
			linkedList.add(new Integer(i));
		}
		int i = 0;
		for(Iterator iter = linkedList.iterator(); iter.hasNext();)
		{
			Object o = iter.next();
			assertEquals(i, ((Integer)o).intValue());
			++i;
		}
		assertEquals(10, i);
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	@Test
	public void testReverse()
	{
		linkedList.add(new Integer(3));
		linkedList.add(new Integer(7));
		assertEquals(linkedList.size(), 2);
		linkedList.reverse();
		assertEquals(linkedList.size(), 2);
		AssertEqualIntValue(0, 7);
		AssertEqualIntValue(1, 3);
		linkedList.add(0, new Integer(10));
		linkedList.reverse();
		AssertEqualIntValue(0, 3);
		AssertEqualIntValue(1, 7);
		AssertEqualIntValue(2, 10);
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	@Test
	public void testRemoveFirstHalf()
	{
		linkedList.add(new Integer(2));
		linkedList.add(new Integer(5));
		linkedList.add(new Integer(7));
		linkedList.add(new Integer(8));
		linkedList.add(new Integer(10));
		linkedList.removeFirstHalf();
		assertEquals(3, linkedList.size());
		AssertEqualIntValue(0, 7);
		AssertEqualIntValue(1, 8);
		AssertEqualIntValue(2, 10);
		Integer i = (Integer)linkedList.removeFirst();
		assertEquals(7, i.intValue());
		AssertEqualIntValue(0, 8);
		i = (Integer)linkedList.removeLast();
		assertEquals(10, i.intValue());
		AssertEqualIntValue(0, 8);
		i = (Integer)linkedList.removeFirst();
		assertEquals(8, i.intValue());
		assertEquals(0, linkedList.size());
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	@Test
	public void testRemoveIntInt()
	{
		linkedList.add(new Integer(0));
		assertEquals(1, linkedList.size());
		AssertEqualIntValue(0, 0);
		linkedList.remove(0, 1);
		assertEquals(0, linkedList.size());
		
		for(int i=0; i<10; ++i)
		{
			linkedList.add(new Integer(i));
		}
		linkedList.remove(5, 3);//0,1,2,3,4,-5,-6,-7,8,9
		assertEquals(7,linkedList.size());
		AssertEqualIntValue(0, 0);
		AssertEqualIntValue(1, 1);
		AssertEqualIntValue(2, 2);
		AssertEqualIntValue(3, 3);
		AssertEqualIntValue(4, 4);
		AssertEqualIntValue(5, 8);
		AssertEqualIntValue(6, 9);
	}
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	@Test
	public void testGetElements()
	{
		linkedList.add(new Integer(11));
		linkedList.add(new Integer(101));
		linkedList.add(new Integer(201));
		linkedList.add(new Integer(301));
		linkedList.add(new Integer(401));
		linkedList.add(new Integer(501));
		linkedList.add(new Integer(601));
		linkedList.add(new Integer(701));
		LinkedList l = new LinkedList();
		l.add(new Integer(1));
		l.add(new Integer(3));
		l.add(new Integer(4));
		l.add(new Integer(6));
		int[] result = linkedList.getElements(l);
		assertNotNull(result);
		assertEquals(4, result.length);
		assertEquals(101, result[0]);
		assertEquals(301, result[1]);
		assertEquals(401, result[2]);
		assertEquals(601, result[3]);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	@Test
	public void testSubtract()
	{
		for(int i=0; i<10; ++i)
		{
			linkedList.add(new Integer(i));
		}
		LinkedList listB = new LinkedList();
		for(int i=5; i<8; ++i)
		{
			listB.add(new Integer(i));
		}
		linkedList.subtract(listB);
		assertEquals(7, linkedList.size());
		AssertEqualIntValue(0, 0);
		AssertEqualIntValue(1, 1);
		AssertEqualIntValue(2, 2);
		AssertEqualIntValue(3, 3);
		AssertEqualIntValue(4, 4);
		AssertEqualIntValue(5, 8);
		AssertEqualIntValue(6, 9);
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	@Test
	public void testRemoveDuplicateValues()
	{
		linkedList.add(new Integer(0));
		linkedList.add(new Integer(1));
		linkedList.add(new Integer(1));
		linkedList.add(new Integer(2));
		linkedList.add(new Integer(2));
		linkedList.add(new Integer(2));
		linkedList.removeDuplicateValues();
		assertEquals(3, linkedList.size());
		AssertEqualIntValue(0, 0);
		AssertEqualIntValue(1, 1);
		AssertEqualIntValue(2, 2);
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	@Test
	public void testRemoveRange()
	{
		for(int i=0; i<10; ++i)
		{
			linkedList.add(new Integer(i));
		}
		linkedList.removeRange(3, 8);//should remove 4,5,6,7
		assertEquals(6, linkedList.size());
		AssertEqualIntValue(0, 0);
		AssertEqualIntValue(1, 1);
		AssertEqualIntValue(2, 2);
		AssertEqualIntValue(3, 3);
		AssertEqualIntValue(4, 8);
		AssertEqualIntValue(5, 9);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	@Test
	public void testIntersection()
	{
		for(int i=0; i<10; ++i)
		{
			linkedList.add(new Integer(i));
		}
		LinkedList listB = new LinkedList();
		for(int i=5; i<8; ++i)
		{
			listB.add(new Integer(i));//5,6,7
		}
		listB.add(new Integer(1024));
		LinkedList result = linkedList.intersection(listB);
		assertNotNull(result);
		assertEquals(3, result.size());
		assertEquals(5,((Integer)result.get(0)).intValue());
		assertEquals(6,((Integer)result.get(1)).intValue());
		assertEquals(7,((Integer)result.get(2)).intValue());
	}

}
