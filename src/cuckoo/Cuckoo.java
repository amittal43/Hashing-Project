package cuckoo;


import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Cuckoo<K,V>{
	
	private final double MAX_LOAD_FACTOR = 0.4;
	
	private final int INITIAL_TABLE_SIZE = 16;
	
	private int sizeTable;
	private int sizeTotal;
	
	private int numEntries;
	
	private SimpleEntry<K,V>[][] table;
	
	
	public Cuckoo(){
		sizeTable = 16;
		sizeTotal = 32;
		table = new SimpleEntry[2][INITIAL_TABLE_SIZE];
		numEntries = 0;
	}

	public boolean contains(K key){
		for(int i=0; i<sizeTable; i++){
			if((table[0][i] != null && table[0][i].getKey().equals(key)) || (table[1][i] !=null && table[1][i].getKey().equals(key)))
				return true;
		}
		return false;
	}

	int currentTable = 0;
	int nextTable = 1;
	
	public int hashFunction1(K key){
		return Math.abs(key.hashCode()) % sizeTable;
	}
	
	public int hashFunction2(K key){
		return (Math.abs(key.hashCode())/sizeTable)%sizeTable;
	}
	
	public int hashFunction(K key, int table){
		if(table == 0)
			return hashFunction1(key);
		
		else
			return hashFunction2(key);
	}
	
	public void put(K key, V value){
		SimpleEntry<K,V> current = new SimpleEntry(key,value);
		int numKicked = 0;
		
		currentTable = 0;
		nextTable = 1;
		
		if(contains(key)){
			if(table[0][hashFunction1(key)].getKey().equals(key)){ // key already in Table 0
				table[0][hashFunction1(key)].setValue(value);
				return;
			}
		
			else if(table[1][hashFunction2(key)].getKey().equals(key)){
				table[1][hashFunction2(key)].setValue(value);
				return;
			}
		}
		
		numEntries++;
		
		if((double)((double)numEntries/(double)sizeTotal)>=MAX_LOAD_FACTOR){
			//System.out.println("Attempted Regrow");
			regrow();
		}
		
		if(table[0][hashFunction1(key)] == null){
			table[0][hashFunction1(key)] = current;
		}
		else if(table[1][hashFunction2(key)] == null){
			table[1][hashFunction2(key)] = current;
		}
		else{
			boolean placeFound = false;
			toggleTables(); // current table is now 1
			
			SimpleEntry<K,V> temp;
			
			while(!placeFound){
				temp = table[currentTable][hashFunction(key,currentTable)]; // save value before kicked out
				table[currentTable][hashFunction(key,currentTable)] = current; // takes place of kicked out element
				
				if(table[nextTable][hashFunction(temp.getKey(),nextTable)]==null){
					table[nextTable][hashFunction(temp.getKey(),nextTable)] = temp;
					placeFound = true;
				}
				else{
					current = table[nextTable][hashFunction(temp.getKey(),nextTable)];
					toggleTables();
				}
				
				if(numKicked>=100){
					regrow();
				}
				
				numKicked++;
			}
		}
	}
	
	private void toggleTables(){
		if(currentTable == 0){
			currentTable = 1;
			nextTable = 0;
		}
		else{
			currentTable = 0;

			nextTable = 1;
		}
	}
	
	public V get(K key){
		if(table[0][hashFunction1(key)].getKey().equals(key))
			return table[0][hashFunction1(key)].getValue();
		else if(table[1][hashFunction2(key)].getKey().equals(key))
			return (table[1][hashFunction2(key)].getValue());
		else
			return null;
	}
	
	public V remove(K key){
		int position1 = hashFunction1(key);
		int position2 = hashFunction2(key);
		
		V temp;
		
		if(table[0][position1].getKey().equals(key)){
			numEntries--;
			temp = table[0][position1].getValue();
			table[0][position1] = null;
			return temp;
		}
		
		if(table[1][position2].getKey().equals(key)){
			numEntries--;
			temp = table[1][position2].getValue();
			table[1][position2] = null;
			return temp;
		}	
		return null;
	}
	
	public Collection<V> values(){
		Collection<V> coll = new ArrayList<V>();
		for(int i=0; i < sizeTable; i++){
			if( table[0][i] != null)
				coll.add(table[0][i].getValue());
		}
		for(int i=0; i < sizeTable; i++){
			if(table[1][i] != null)	
				coll.add(table[1][i].getValue());	
		}	
		return coll;
		
	}
	
	private void regrow(){
		int oldCapacity = sizeTable;
		int newCapacity = (sizeTable*2+1);
		sizeTable = newCapacity;
		sizeTotal = sizeTable*2;
		SimpleEntry<K,V>[][] temp = new SimpleEntry[2][oldCapacity];
		temp = table;
		table = new SimpleEntry[2][sizeTable];
		
		for (int i=0; i<oldCapacity; i++) {
			if(temp[0][i]!=null){
				put(temp[0][i].getKey(), temp[0][i].getValue());
			}
			if(temp[1][i]!=null){
				put(temp[1][i].getKey(), temp[1][i].getValue());
			}
		}	
		
	}
	
	public int numEntries(){
		return numEntries;
	}
	
	public void clear(){
		SimpleEntry[][] temp = table;
		for(int i =0; i< sizeTable; i++){
			temp[0][i] = null;
			temp[1][i] = null;
		}
		numEntries = 0;
	}
	
	public Set<K> keySet(){
		HashSet<K> set = new HashSet<K>();
		for (int i = 0; i < sizeTable; i++){
			if( table[0][i] != null){
				set.add(table[0][i].getKey());
			}
			if(table[1][i] != null){
				set.add(table[1][i].getKey());
			}
			
		}

		return set;
	}
	
	 
	
	public int keyValues(K key){
		int counter = 0;
		for( int i = 0; i <sizeTable; i ++)
		{
			if( table[0][i] != null){
				if( table[0][i].getKey().equals(key))
					counter++;
			}	
			if(table[1][i] != null){
				if( table[1][i].getKey().equals(key))
				counter++;
			}			
		}
		if(counter == 0)
			return -1;
		else return counter;
		
	}	
	
	public int getTotalSize(){
		return sizeTotal;
	}
	
	public int getNumEntries(){
		return numEntries;
	}
}
