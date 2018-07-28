/*
 * CS61B PROJECT 1a:Data Structures, version 1.1
 * Link: http://datastructur.es/sp17/materials/proj/proj1a/proj1a.html
 * @Author:Yuan Ma
 */
public class LinkedListDeque<Item> implements Deque<Item> {
	
	//specific object
	private class Node{
		public Item item;
		public Node previous;
		public Node next;
		
		public Node(Item i, Node p,Node n) {
			item = i;
			previous = p;
			next = n;
		}
	}
	
	//Attribute: global variable
	private Node firstSentinel;
	private Node lastSentinel;
	private int size;
	private Item item;
	
	/************************
	 * Create an empty List.*
	 ************************/
	public LinkedListDeque() {
		firstSentinel = new Node(null,null,null);
		lastSentinel = new Node(null,null,null);
		firstSentinel.next = lastSentinel;
		lastSentinel.previous = firstSentinel;
		size = 0;
	}
	
	Node first;
	Node last;
	/******************************************
	 * addFirst method						  *
	 * Adds an item to the front of the Deque.*
	 ******************************************/
	@Override
	public void addFirst(Item val) {
		first = new Node(val,firstSentinel,firstSentinel.next);		
		firstSentinel.next.previous = first;
		firstSentinel.next = first;
		size+=1;
	}
	/******************************************
	 * addLast method						  *
	 * Adds an item to the back of the Deque. *
	 ******************************************/
	@Override
	public void addLast(Item val) {
		last = new Node(val,lastSentinel.previous,lastSentinel);		
		lastSentinel.previous.next = last;
		lastSentinel.previous = last;
		size+=1;
	}
	/****************************************************
	 * isEmpty method									*
	 * Returns true if deque is empty, false otherwise. *
	 ****************************************************/
	@Override
	public boolean isEmpty() {
		if(firstSentinel.next == lastSentinel && lastSentinel.previous == firstSentinel) {
			return true;
		}
		return false;
	}
	/****************************************************
	 * size method						     			*
	 * Returns the number of items in the Deque.	    *
	 ****************************************************/
	@Override
	public int size() {
		return size;
	}
	/****************************************************************************
	 * printDeque method						     							*
	 * Prints the items in the Deque from first to last, separated by a space.	*
	 ****************************************************************************/
	@Override
	public void printDeque(){
		for(Node p = firstSentinel.next; p!= lastSentinel;p = p.next) {
			System.out.println(p.item+" ");
		}
	}
	
	/****************************************************************************
	 * removeFirst method						     							*
	 * Removes and returns the item at the front of the Deque.					* 
	 * If no such item exists, returns null.									*
	 ****************************************************************************/
	@Override
	public Item removeFirst() {
		Item result = firstSentinel.next.item;
		if(isEmpty()) {
			return null;
		}
		if(size != 1) {//make sure null pointer will not happen
			firstSentinel.next = firstSentinel.next.next;
			firstSentinel.next.next.previous = firstSentinel;
		}
		size--;
		return result;
	}
	/****************************************************************************
	 * removeLast method						     							*
	 * Removes and returns the item at the back of the Deque. 					*
	 * If no such item exists, returns null.									*
	 ****************************************************************************/
	@Override
	public Item removeLast() {
		Item result = lastSentinel.previous.item;
		if(isEmpty()) {
			return null;
		}
		if(size!=1) {
			lastSentinel.previous = lastSentinel.previous.previous;
			lastSentinel.previous.previous.next = lastSentinel;
		}
		size--;
		return result;
	}
	/****************************************************************************
	 * get method						   			  							*
	 * Gets the item at the given index, where 0 is the front, 1 is the			* 
	 * next item, and so forth. If no such item exists, returns null. 			*
	 * Must not alter the deque!												*
	 ****************************************************************************/
	@Override
	public Item get(int index) {
		int i = 0;
		Node p = firstSentinel.next;
		while(i != index) {
			if(p == null || index>size-1) {
				return null;
			}
			p = p.next;
			i++;
		}
		return p.item;
	}
	/****************************************************************************
	 * getRecursive method				   			  							*
	 * Same as get, but uses recursion.											*
	 ****************************************************************************/
	public Item getRecursive(int index) {
			Node node = firstSentinel.next;
			return helper(index, node).item;
	}
	private Node helper(int index, Node node) {
		if(node == null||index<0) {
			return null;
		}
		if(index == 0) {
			return node;
		}
		return helper(index-1,node.next);
	}	
	/****************************************************************************
	 * 																			*
	 * 									MAIN 									*
	 * 																			*
	 ****************************************************************************/
	public static void main(String[] args) {
		LinkedListDeque<Integer> L = new LinkedListDeque<>();
		L.addFirst(300);
		L.addFirst(200);
		L.addFirst(100);
		System.out.println(L.get(-1));
//		System.out.println(L.get(2));
//		System.out.println(L.getRecursive(2));
//		L.addLast(700);
//		L.addLast(600);
//		L.addLast(500);
//		L.printDeque();

	}
}
