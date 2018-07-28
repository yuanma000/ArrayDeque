//import Test.ArrayDeque;

/*
 * CS61B PROJECT 1a:Data Structures, version 1.1
 * Link: http://datastructur.es/sp17/materials/proj/proj1a/proj1a.html
 * @Author:Yuan Ma
 */
public class ArrayDeque<Item> implements Deque<Item> {
	private int nextFirst;
	private int nextLast;
	private int size;
	private Item[] items;
	private static int FACTOR = 2;
	private int capacity = 8;
	
	//empty Deque
	public ArrayDeque() {
		nextFirst = 0;
		nextLast = 1;
		size = 0;
		items = (Item[]) new Object[capacity];
	}
	//resize stuff
	private void resize(int capacity) {
		Item[] temp = (Item[]) new Object[capacity];
		System.arraycopy(items, 0, temp, 0, size);
		items = temp;
		nextFirst = capacity-1;
		nextLast = size;
	}
	/**********************************************************************
	 * addFirst method													  *
	 * Adds an item to the front of the deque array.     				  *
	 * 1.check if this array is already full.							  *
	 * 2.update new nextFirst and size.									  *
	 * 3.if new nextFirst <0, then nextFirst will relocate to the rear    *
	 *   of this array BUT if the last position is already occupied we    *
	 *   need to expand the capacity of this array.						  *
	 **********************************************************************/
	@Override
	public void addFirst(Item val) {
		if(size == items.length) {
			resize(size*FACTOR);
		}
		items[nextFirst] = val;
		size++;
		nextFirst--;
		if(nextFirst<0) {
			if(items[items.length-1]!=null) {
				resize(size*FACTOR);
			}
			nextFirst = items.length-1;
		}
	}
	/**********************************************************************
	 * addLast method													  *
	 * Adds an item to the end of the deque array.      				  *
	 * 1.check if this array is already empty or empty.					  *
	 * 2.update new nextLast and size.									  *
	 * 3.if new nextLast >capacity, then nextLast will relocate to the    * 
	 * front of this array BUT if the last position is already occupied   *
	 * we need to expand the capacity of this array.					  *
	 **********************************************************************/
	@Override
	public void addLast(Item val) {
		if(isEmpty()) {
			items[0] = val;
			size++;
			nextFirst = items.length - 1;
		}else {
			if(size == items.length) {
				resize(size*FACTOR);
			}
			items[nextLast] = val;
			size++;
			nextLast++;
			if(nextLast>items.length) {
				if(items[0]!= null) {
					resize(size*FACTOR);
				}
				nextLast = size;
			}
		}
	}
	/****************************************************
	 * isEmpty method									*
	 * Returns true if deque is empty, false otherwise. *
	 ****************************************************/
	@Override
	public boolean isEmpty() {
		return size==0;
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
	public void printDeque() {
		for(int i = 0;i<items.length;i++) {
			System.out.print(items[i]+" ");
		}
	} 
	/****************************************************************************
	 * removeFirst method						     							*
	 * Removes and returns the item at the front of the Deque.					* 
	 * 1.Check empty.															*
	 * 2.Check if the current node in the first position,  if yes make nextFirst*
	 * back to -1.																*
	 * 3. update nextFirst index. and return the first position.				*					
	 ****************************************************************************/
	@Override
	public Item removeFirst() {
		if(isEmpty()) {
			return null;
		}
		if(nextFirst == items.length-1) {
			nextFirst = -1;
		}
		nextFirst++;//nextFirst become the current position
		Item temp = items[nextFirst];
		items[nextFirst] = null;
		size--;
		//check shrink
		if(size>16 && size/items.length < 0.25) {
			shrink();
		}
		return temp;
	}
	/****************************************************************************
	 * removeLast method						     							*
	 * Removes and returns the item at the front of the Deque.					* 
	 * 1.Check empty.															*
	 * 2.Check if the current node in the last position,  if yes make nextLast  *
	 * back to array capacity (items.length ).									*
	 * 3. update nextLast index. and return the last position.		     		*					
	 ****************************************************************************/
	@Override
	public Item removeLast() {
		if(isEmpty()) {
			return null;
		}
		if(nextLast == 0) {
			nextLast = items.length;
		}
		nextLast--;//nextLast become the current position
		Item temp = items[nextLast];
		items[nextLast] = null;
		size--;
		//check shrink
		if(size>16 && size/items.length < 0.25) {
			shrink();
		}
		return temp;
	}
	/****************************************************************************
	 * get method						   			  							*												*
	 ****************************************************************************/
	@Override
	public Item get(int index) {
		if(index<size && index>=0) {
			return items[index];
		}
		return null;
	}
	/****************************************************************************
	 * shrink method															*
	 * when the array capacity greater than 16 AND usage factor lower than 25%  *										*
	 * Then we shrink the array capacity.										*
	 * (usage factor = size/capacity)	   										*												*
	 ****************************************************************************/
	private void shrink() {
		int nullStartIndex = 0;
		int nullEndIndex = 0;
		for(int i = 0; i< items.length;i++) {
			if(items[i] == null) {
				nullStartIndex = i;
				break;
			}
		}
		for(int i = items.length-1;i>=0;i--) {
			if(items[i] == null) {
				nullEndIndex = i;
				break;
			}
		}
		int frontSize = nullStartIndex;
		int rearSize = (items.length-1) - nullEndIndex;
		for(int i = 0;i< rearSize;i++) {
			items[nullStartIndex+i] = items[nullEndIndex+i+1];
		}
		resize(frontSize+rearSize);
	}
	
	//WORKING HERE --- WORKING HERE --- WORKING HERE --- WORKING HERE --- WORKING HERE
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayDeque<Integer> L = new ArrayDeque<>();

		L.printDeque();
		System.out.println();
//		array = L.shrink();
//		System.out.println(L.shrink());
//		System.out.println(L.removeLast());
//		System.out.println(L.get(1000000));

//		L.printDeque();
	}

}
