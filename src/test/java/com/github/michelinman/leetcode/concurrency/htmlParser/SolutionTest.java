package com.github.michelinman.leetcode.concurrency.htmlParser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

class SolutionTest {

    @Test
    void testCrawlExample1() {
        List<String> urls = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us"
        );
        List<int[]> edges = Arrays.asList(
                new int[]{2, 0},
                new int[]{2, 1},
                new int[]{3, 2},
                new int[]{3, 1},
                new int[]{0, 4}
        );
        String startUrl = "http://news.yahoo.com/news/topics/";

        Map<String, List<String>> urlMap = new HashMap<>();
        for (int[] edge : edges) {
            String fromUrl = urls.get(edge[0]);
            String toUrl = urls.get(edge[1]);
            urlMap.computeIfAbsent(fromUrl, k -> new ArrayList<>()).add(toUrl);
        }

        HtmlParser htmlParser = url -> urlMap.getOrDefault(url, Collections.emptyList());
        Solution solution = new Solution();
        List<String> result = solution.crawl(startUrl, htmlParser);

        List<String> expected = Arrays.asList(
                "http://news.yahoo.com/news/topics/",
                "http://news.yahoo.com",
                "http://news.yahoo.com/us",
                "http://news.yahoo.com/news"
        );

        assertEquals(expected, result);
    }

    @Test
    void testCrawlExample2() {
        List<String> urls = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com"
        );
        List<int[]> edges = Arrays.asList(
                new int[]{0, 2},
                new int[]{2, 1},
                new int[]{3, 2},
                new int[]{3, 1},
                new int[]{3, 0}
        );
        String startUrl = "http://news.google.com";

        Map<String, List<String>> urlMap = new HashMap<>();
        for (int[] edge : edges) {
            String fromUrl = urls.get(edge[0]);
            String toUrl = urls.get(edge[1]);
            urlMap.computeIfAbsent(fromUrl, k -> new ArrayList<>()).add(toUrl);
        }

        HtmlParser htmlParser = url -> urlMap.getOrDefault(url, Collections.emptyList());
        Solution solution = new Solution();
        List<String> result = solution.crawl(startUrl, htmlParser);

        List<String> expected = Collections.singletonList("http://news.google.com");

        assertEquals(expected, result);
    }
}