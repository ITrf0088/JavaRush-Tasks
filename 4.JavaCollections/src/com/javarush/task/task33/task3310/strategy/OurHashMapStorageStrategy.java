package com.javarush.task.task33.task3310.strategy;

public class OurHashMapStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    Entry[] table  = new Entry[DEFAULT_INITIAL_CAPACITY];

    int size;

    int threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);

    float loadFactor = DEFAULT_LOAD_FACTOR;

    private int hash(Long k){
        return k.hashCode();
    }

    private int indexFor(int hash, int length){
        return hash & (length-1);
    }

    

    private Entry getEntry(Long key){

        if(size == 0) return null;
        int index = indexFor(hash(key),table.length);

        for (Entry e = table[index]; e != null; e = e.next){
            if (e.key.equals(key)){
                return e;
            }
        }
        return null;
    }

    private void resize(int newCapacity){
        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int)(newCapacity * loadFactor);
    }

    private void transfer(Entry[] newTable){
        int newCapacity = newTable.length;

        for (int i = 0; i < table.length; i++) {
            Entry curEntry = table[i];
            table[i] = null;
            while (curEntry != null){
                Entry next = curEntry.next;

                int index = indexFor(hash(curEntry.key),newCapacity);
                curEntry.next = newTable[index];
                newTable[index] = curEntry;

                curEntry = next;
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex){

        if(size >= threshold){
            resize(2* table.length);
            hash = hash(key);
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex){

        Entry entryTable = table[bucketIndex];
        Entry entry = new Entry(hash,key,value,entryTable);
        table[bucketIndex] = entry;

        size++;
    }



    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash,table.length);

        for (Entry e = table[index]; e != null; e = e.next){
            if(e.key.equals(key)){
                e.value = value;
                return;
            }
        }
        addEntry(hash,key,value,index);

    }


    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }



    @Override
    public boolean containsValue(String value) {
        if(value == null) return false;

        for (Entry entry : table) {
            for (Entry e = entry; e !=null; e = e.next){
                if(e.getValue().equals(value)){
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public Long getKey(String value) {
        if(value == null) return null;

        for (Entry entry : table) {
            for (Entry e = entry; e !=null; e = e.next){
                if(e.getValue().equals(value)){
                    return e.getKey();
                }
            }
        }

        return null;
    }

    @Override
    public String getValue(Long key) {

        if(getEntry(key) == null){
            return null;
        }
        return getEntry(key).getValue();
    }
}
