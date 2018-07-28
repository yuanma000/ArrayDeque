/*
 * CS61B PROJECT 1a:Data Structures, version 1.1
 * Link: http://datastructur.es/sp17/materials/proj/proj1a/proj1a.html
 * @Author:Yuan Ma
 */

public interface Deque<Item> {
	
	public void addFirst(Item val);
	
	public void addLast(Item val);
	
	public boolean isEmpty();
	
	public int size();
	
	public void printDeque();
	
	public Item removeFirst(); 
	
	public Item removeLast();
	
	public Item get(int index);
}
