package com.github.mitrakumarsujan.springcachedemo.controller;

import com.github.mitrakumarsujan.springcachedemo.dto.CacheInvalidateResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.text.MessageFormat.format;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;

    @PostMapping("/invalidate/{cacheName}")
    public ResponseEntity<CacheInvalidateResponseDto> clearCache(@PathVariable("cacheName") String cacheName) {
        boolean success;
        String message;
        try {
            Cache cache = cacheManager.getCache(cacheName);
            cache.invalidate();
            success = true;
            message = "All entries cleared";
        } catch (NullPointerException e) {
            success = false;
            message = format("No cache found with name [{0}]", cacheName);
        }
        CacheInvalidateResponseDto response = new CacheInvalidateResponseDto(success, message);
        return ResponseEntity.status(success ? OK : INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
