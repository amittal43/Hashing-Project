package hopscotch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Map.Entry;
/**
 * A HopScotch hashmap which implements the hopscotch hashtable.
 * @author Raj
 *
 * @param <K>
 * @param <V>
 */
public class Hopscotch<K,V> {
	private static final int INITIAL_TABLE_SIZE =  24;
	private static int numEntries;
	private int size;
	private static final int HOPSCOTCH_CONST = 4;


	public MapEntry<K,V>[] table;

	/**
	 * Constructor with default initial size.
	 */
	public Hopscotch(){
		this(INITIAL_TABLE_SIZE);
	}
	
	/**
	 * Constructor with parameterized constructor.
	 * @param size
	 */
	public Hopscotch(int size){
		numEntries = 0 ;
		this.size = size;
		table = new MapEntry[size];
	}
	
	
	public boolean contains(K key){
		int pos  = hashFunction(key);
		int count=0;
		while(count<HOPSCOTCH_CONST){
				if(pos+count<table.length){
				if(table[pos+count]!=null && table[pos+count].getKey().equals(key))
					return true;
				}
			count++;

		}
		return false;
	}

	
	public V remove(K key){
		if(!contains(key)){
			return null;
		}
		V value=null;
		int pos  =hashFunction(key);
		int count=0;
		while(count<HOPSCOTCH_CONST){
			if (table[pos+count].key.equals(key)){
				value=table[pos+count].value;
				table[pos+count]=null;
				numEntries--;
				break;
			}
			count++;
		}
		return value;
		}

	
	public V put (K key, V value){
		if (contains(key)){

			return update(key,value);
		}
		int pos = hashFunction(key);
		int emptyIndex=-1;
		for (int index=pos; index<table.length;index++){
			if (table[index]==null){
				emptyIndex=index;
				break;
			}				
		}
		if (emptyIndex==-1){
			regrow();
			put(key,value);

		}
		else{
			MapEntry<K,V> entry = new MapEntry<K,V>(key,value,pos);
			int newpos = move(emptyIndex,pos);
			if(newpos==-1){
				regrow();
				put(key,value);
				return null;
			}
			table[newpos]=entry;
			numEntries++;
		}

		return null;


	}
	private int move(int emptyIndex,int targetIndex){
		boolean flag=true;
		while(emptyIndex>targetIndex+(HOPSCOTCH_CONST-1)){
			flag=true;
			for(int j = emptyIndex-(HOPSCOTCH_CONST-1); j<emptyIndex;j++){
				if(emptyIndex<=table[j].base+HOPSCOTCH_CONST-1){
					flag=false;
					table[emptyIndex] = table[j];
					table[j]=null;
					emptyIndex = j;
					break;
				}
			}
			if(flag)
				return -1;
		}
		return emptyIndex;

	}
	private void regrow(){
		int oldCapacity = size;
		int newCapacity = (size*2+1);
		size = newCapacity;
		MapEntry<K,V>[] temp = table.clone();

		table = new MapEntry[size];

		for (int i=0; i<oldCapacity; i++) {
			if(temp[i]!=null ){
				put(temp[i].getKey(), temp[i].getValue());
			}
		}	
	}

	private V update(K key,V value){
		V val=null;
		int pos = hashFunction(key);
		for (int count=0;count<HOPSCOTCH_CONST;count++){
			if(table[pos+count]!=null && table[pos+count].key.equals(key)){
				val = table[pos+count].getValue();
				table[pos+count].value=value;
			}
		}
		return val;
	}
	private int hashFunction(K key){
		return key.hashCode() % table.length;
	}

	public int numEntries(){
		return numEntries;
	}

	public void clear() {
		for (int i=0;i<table.length;i++){
			if (table[i]!=null){
				table[i]=null;
			}
		}
		numEntries=0;
	}

	public Collection<V> values(){
		Collection<V> coll = new ArrayList<V>();
		for(int i=0; i < size; i++){
			if( table[i] != null )
				coll.add(table[i].getValue());

		}	
		return coll;

	}
	public Set<K> keySet(){
		HashSet<K> set = new HashSet<K>();
		for (int i = 0; i < size; i++){
			if( table[i] != null){
				set.add(table[i].getKey());
			}	
		}

		return set;
	}
	
	public boolean isEmpty(){
		if(numEntries==0){
			return true;
		}
		return false;
	}
	
	public V getValue(K key){
		int pos  = hashFunction(key);
		int count=0;
		while(count<HOPSCOTCH_CONST){
				if(table[pos+count]!=null && table[pos+count].getKey().equals(key))
					return table[pos+count].value;

			count++;

		}
		return null;
	}

	public class MapEntry<K,V> implements Entry<K, V> {

		private K key;
		private V value;
		private int base;


		public MapEntry(K key, V value, int base){
			
			this.key=key;
			this.value=value;
			this.base=base;
		}

		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}
		
		public int getBase(){
			return base;
		}
		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value=value;
			return temp;
		}

	}
	
	public int getSize(){
		return size;
	}
	
	public static void main(String[] args){
		Hopscotch<Integer,String> htable = new Hopscotch<Integer, String>();
		htable.put(5, "abc");
		htable.put(5, "bcd");
		System.out.println(htable.getValue(5));
		System.out.println(htable.contains(5));
		System.out.println(htable.numEntries());
		htable.remove(5);
		
		System.out.println(htable.contains(5));
		System.out.println(htable.numEntries());
		
	}
}