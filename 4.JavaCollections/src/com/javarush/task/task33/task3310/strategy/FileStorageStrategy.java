package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy {



    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000L;


    int size;


    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];


    long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;

    long maxBucketSize ;


    private int hash(Long k){
        return k.hashCode();
    }

    private int indexFor(int hash, int length){
        return hash & (length-1);
    }




    private Entry getEntry(Long key){

        if(size == 0) return null;
        int index = indexFor(hash(key),table.length);

        for (Entry e = table[index].getEntry(); e != null; e = e.next){
            if (e.key.equals(key)){
                return e;
            }
        }
        return null;
    }

    private void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    private void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;

        for (int i = 0; i < table.length; i++) {
            Entry curEntry = table[i].getEntry();
            table[i].remove();
            while (curEntry != null){
                Entry next = curEntry.next;

                int index = indexFor(hash(curEntry.key),newCapacity);
                curEntry.next = newTable[index].getEntry();
                newTable[index].putEntry(curEntry);

                curEntry = next;
            }
        }
    }

    private void addEntry(int hash, Long key, String value, int bucketIndex){

        for (FileBucket bucket : table) {
            if(bucket !=null && bucket.getFileSize()>= bucketSizeLimit){
                resize(2* table.length);
                hash = hash(key);
                bucketIndex = indexFor(hash, table.length);
                break;
            }
        }


        createEntry(hash, key, value, bucketIndex);
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex){

        Entry entryTable = (table[bucketIndex] == null) ? null : table[bucketIndex].getEntry();
        Entry entry = new Entry(hash,key,value,entryTable);
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(entry);

        size++;
    }


    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public void put(Long key, String value) {
        int hash = hash(key);
        int index = indexFor(hash,table.length);

        if(table[index] != null) {
            for (Entry e = table[index].getEntry(); e != null; e = e.next) {
                if (e.key.equals(key)) {
                    e.value = value;
                    return;
                }
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

        for (int i = 0; i < table.length ;i++) {
            if(table[i] == null) continue;
            Entry entry = table[i].getEntry();
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

        for (int i = 0; i < table.length ;i++) {
            if(table[i] == null) continue;
            Entry entry = table[i].getEntry();
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
