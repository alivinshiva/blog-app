package me.alivinshiva.blogapp.caches;

import jakarta.annotation.PostConstruct;

import java.util.Map;

public class AppCache {

    private Map<String, String> cacheMap;

    @PostConstruct
    public void cache() {
        cacheMap = null;
    }
}
