package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{

    Storage storage;

    LRUCache lruCache;
    OriginalRetriever retriever;
    public CachingProxyRetriever(Storage storage) {
        this.storage = storage;
        lruCache = new LRUCache(16);
        retriever = new OriginalRetriever(storage);
    }

    @Override
    public Object retrieve(long id) {
        if(lruCache.find(id) != null){
            return lruCache.find(id);
        }else {
            Object obj = retriever.retrieve(id);
            lruCache.set(id,obj);
            return obj;
        }
    }
}
