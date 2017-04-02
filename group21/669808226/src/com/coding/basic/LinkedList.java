/*
 * @author amazonove
 * @qq 669808226
 * @email darkening@126.com
 */
package com.coding.basic;

public class LinkedList implements List {
	private Node head;//won't hold any data
	private Node tail;//won't hold any data
	
	private int size = 0;
	
	public LinkedList()
	{
		head = new LinkedList.Node();
		tail = new LinkedList.Node();
		head.next = tail;
		tail.prev = head;
	}
	
	
	public void add(Object o)
	{
		if(o == null)
			return;
		
		InsertAfter(this.tail.prev, o);
	}
	
	public void add(int index , Object o)
	{
		if(o == null || index < 0 || index > this.size())
			return;
		
		if(index == this.size())
		{
			InsertAfter(this.tail.prev, o);
			return;
		}
			
		Node n = getNode(index);
		InsertAfter(n.prev, o);
	}
	
	
	public Object get(int index)
	{
		if(!indexValid(index))
			return null;
		
		Node rtn = getNode(index);
		return rtn.data;
	}
	
	public int indexOf(Object o)
	{
		int idx = -1;
		for(Iterator iter = this.iterator(); iter.hasNext();)
		{
			++idx;
			if(iter.next().equals(o))
			{
				return idx;
			}
		}
		return -1;
	}
	
	/*
	 * 移除指定位置的元素
	 */
	public Object remove(int index)
	{
		if(!indexValid(index))
			return null;
		
		Node nodeToRemove = getNode(index);
		nodeToRemove.prev.next = nodeToRemove.next;
		nodeToRemove.next.prev = nodeToRemove.prev;

		--size;
		return nodeToRemove.data;
		
	}
	
	/*
	 * 获取指定位置的元素
	 */
	private Node getNode(int index)
	{
		Node rtn = this.head.next;
		for(int i = 0; i < index; ++i)
		{
			rtn = rtn.next;
		}
		return rtn;
	}

	private boolean indexValid(int index) {
		return index >= 0 && index < this.size;
	}
	
	public int size(){
		return this.size;
	}
	
	public void addFirst(Object o){
		this.add(0, o);;
	}
	
	public void addLast(Object o){
		this.add(o);
	}
	
	public Object removeFirst(){
		return this.remove(0);
	}
	
	public Object removeLast(){
		return this.remove(this.size - 1);
	}
	
	public Iterator iterator(){
		return new LinkedListIterator(this);
	}
	
	/*
	 * 向指定元素后面插入一个元素，包含给定的数据
	 */
	private void InsertAfter(Node node, Object data)
	{
		Node nodeToInsert = new LinkedList.Node(data);
		InsertAfter(node, nodeToInsert);
	}
	
	/*
	 * 向指定元素后面插入一个元素
	 */
	private void InsertAfter(Node node, Node nodeToInsert)
	{
		Node nextNode = node.next;
		node.next = nodeToInsert;
		nodeToInsert.prev = node;
		nodeToInsert.next = nextNode;
		nextNode.prev = nodeToInsert;
		++size;
	}
	
	//static class is not allowed to access it's 
	//outerclass's instance members without an outer class instance
	private static class Node{
		Object data;
		Node next;
		Node prev;
		
		public Node(){}
		
		public Node(Object o)
		{
			data = o;
		}
	}
	
	private class LinkedListIterator implements Iterator
	{
		private LinkedList data;
		
		private int index;
		
		public LinkedListIterator(LinkedList linkedList)
		{
			if(linkedList != null)
			{
				this.data = linkedList;
				this.index = -1;
			}
		}
		
		@Override
		public boolean hasNext() {
			if(this.data != null)
			{
				if(this.data.size() > index + 1)
				{
					return true;
				}
			}
			return false;
		}

		@Override
		public Object next() {
			if(this.hasNext())
			{
				return this.data.get(++index);
			}
			return null;
		}
		
	}
	
	/**
	 * 把该链表逆置
	 * 例如链表为 3->7->10 , 逆置后变为  10->7->3
	 */
	public void reverse()
	{		
		if(this.size() <= 1)
			return;
		Object h = this.removeFirst();
		Object t = this.removeLast();
		this.addFirst(t);
		this.addLast(h);
	}
	
	/**
	 * 删除一个单链表的前半部分
	 * 例如：list = 2->5->7->8 , 删除以后的值为 7->8
	 * 如果list = 2->5->7->8->10 ,删除以后的值为7,8,10

	 */
	public  void removeFirstHalf()
	{
		if(this.size() < 2)
			return;
		int halfSize = this.size() / 2;
		Node n = getNode(halfSize);
		this.head.next = n;
		n.prev = this.head;
		size -= halfSize;
	}
	
	/**
	 * 从第i个元素开始， 删除length 个元素 ， 注意i从0开始
	 * @param i
	 * @param length
	 */
	public void remove(int i, int length)
	{
		if(i < 0 || length <= 0 || (i+length) > this.size())
		{
			return;
		}
		Node firstNode = getNode(i);
		Node lastNode = getNode(i+length-1);
		firstNode.prev.next = lastNode.next;
		lastNode.next.prev  = firstNode.prev;
		size -= length;
	}
	
	/**
	 * 假定当前链表和listB均包含已升序排列的整数
	 * 从当前链表中取出那些listB所指定的元素
	 * 例如当前链表 = 11->101->201->301->401->501->601->701
	 * listB = 1->3->4->6
	 * 返回的结果应该是[101,301,401,601]  
	 * @param list
	 */
	public  int[] getElements(LinkedList list)
	{
		int[] temp = new int[list.size()];
		int count = 0;
		for(Iterator iter = list.iterator(); iter.hasNext();)
		{
			int index = ((Integer)iter.next()).intValue();
			if(index < this.size())
			{
				temp[count++] = ((Integer)get(index)).intValue();
			}
		}
		int[] result = new int[count];
		System.arraycopy(temp, 0, result, 0, count);
		return result;
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 从当前链表中中删除在listB中出现的元素 

	 * @param list
	 */
	public  void subtract(LinkedList list)
	{
		for(Iterator iter = list.iterator(); iter.hasNext();)
		{
			Object o = iter.next();
			int idx = this.indexOf(o);
			if(idx != -1)
			{
				this.remove(idx);
			}
		}
	}
	
	/**
	 * 已知当前链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 删除表中所有值相同的多余元素（使得操作后的线性表中所有元素的值均不相同）
	 */
	public  void removeDuplicateValues()
	{
		if(this.size() <= 1)
			return;
		Object lastObject = get(0);
		for(int i=1; i<this.size(); ++i)
		{
			Object o = get(i);//效率问题，这里可以直接foreach遍历
			if(o.equals(lastObject))
			{
				remove(i);//效率问题，remove需要遍历，这里可以直接修改节点引用，避免遍历
				--i;
				continue;
			}
			lastObject = o;
		}
	}
	
	/**
	 * 已知链表中的元素以值递增有序排列，并以单链表作存储结构。
	 * 试写一高效的算法，删除表中所有值大于min且小于max的元素（若表中存在这样的元素）
	 * @param min
	 * @param max
	 */
	public  void removeRange(int min, int max)
	{
		int i = 0, length = 0;
		for(Iterator iter = this.iterator(); iter.hasNext();)
		{
			int val = ((Integer)iter.next()).intValue();
			if(val <= min)
			{
				++i;
			}
			else
			{
				if(val < max)
				{
					++length;
				}
			}
		}
		this.remove(i, length);
	}
	
	/**
	 * 假设当前链表和参数list指定的链表均以元素依值递增有序排列（同一表中的元素值各不相同）
	 * 现要求生成新链表C，其元素为当前链表和list中元素的交集，且表C中的元素有依值递增有序排列
	 * @param list
	 */
	public LinkedList intersection(LinkedList list){
		LinkedList result = new LinkedList();
		LinkedList smallerList =list.size() < this.size() ? list : this;
		LinkedList largerList =list.size() >= this.size() ? list : this;
		for(int i=0; i<smallerList.size(); ++i)
		{
			Object obj = smallerList.get(i);
			if(largerList.indexOf(obj) != -1)
				result.add(obj);
		}
		return result;
	}
	
}
