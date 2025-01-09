package com.github.michelinman.leetcode.concurrency.htmlParser;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Interface for HTML parser
interface HtmlParser {
    // Method to get URLs from a given URL
    List<String> getUrls(String url);
}

class Solution {
    // Method to start crawling from the given start URL
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        // Extract the hostname from the start URL
        String hostname = getHostname(startUrl);

        // Set to keep track of visited URLs
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);

        // Start crawling and collect the results into a list
        return crawl(startUrl, htmlParser, hostname, visited).collect(Collectors.toList());
    }

    // Recursive method to crawl URLs
    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname, Set<String> visited) {
        // Get URLs from the current URL, filter them by hostname and visited status, and recursively crawl them
        Stream<String> stream = htmlParser.getUrls(startUrl)
                .parallelStream()
                .filter(url -> isSameHostname(url, hostname))
                .filter(visited::add)
                .flatMap(url -> crawl(url, htmlParser, hostname, visited));

        // Concatenate the current URL with the stream of crawled URLs
        return Stream.concat(Stream.of(startUrl), stream);
    }

    // Helper method to extract the hostname from a URL
    private String getHostname(String url) {
        int idx = url.indexOf('/', 7);
        return (idx != -1) ? url.substring(0, idx) : url;
    }

    // Helper method to check if a URL has the same hostname
    private boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }
}