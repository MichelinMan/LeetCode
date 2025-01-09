package com.github.michelinman.leetcode.concurrency.htmlParser;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

    interface HtmlParser {
        List<String> getUrls(String url);
    }

    class Solution {
        private String getHostName(String url) {
            try {
                return new URI(url).getHost();
            } catch (URISyntaxException e) {
                throw new IllegalArgumentException("Invalid URL: " + url);
            }
        }

        public List<String> crawl(String startUrl, HtmlParser htmlParser) {
            String startHostName = getHostName(startUrl);
            Set<String> visited = ConcurrentHashMap.newKeySet();
            ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
            ExecutorService executor = Executors.newFixedThreadPool(10);

            visited.add(startUrl);
            queue.add(startUrl);

            List<Future<?>> futures = new ArrayList<>();

            while (!queue.isEmpty()) {
                String currentUrl = queue.poll();
                if (currentUrl == null) continue;

                futures.add(executor.submit(() -> {
                    List<String> urls = htmlParser.getUrls(currentUrl);
                    for (String url : urls) {
                        if (getHostName(url).equals(startHostName) && visited.add(url)) {
                            queue.add(url);
                        }
                    }
                }));
            }

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException e) {
                    Thread.currentThread().interrupt();
                }
            }

            executor.shutdown();

            return new ArrayList<>(visited);
        }
    }