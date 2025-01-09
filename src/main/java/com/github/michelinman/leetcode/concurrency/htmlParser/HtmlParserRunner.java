package com.github.michelinman.leetcode.concurrency.htmlParser;

import java.util.*;

public class HtmlParserRunner {

    public static void run(List<String> urls, List<int[]> edges, String startUrl) {
        // Create a map to simulate the HtmlParser behavior
        Map<String, List<String>> urlMap = new HashMap<>();
        for (int[] edge : edges) {
            String fromUrl = urls.get(edge[0]);
            String toUrl = urls.get(edge[1]);
            urlMap.computeIfAbsent(fromUrl, k -> new ArrayList<>()).add(toUrl);
        }

        // Create an instance of HtmlParser
        HtmlParser htmlParser = url -> urlMap.getOrDefault(url, Collections.emptyList());

        // Create an instance of Solution and call the crawl method
        Solution solution = new Solution();
        List<String> result = solution.crawl(startUrl, htmlParser);

        // Print the result
        System.out.print("Output: ");
        for (String url : result) {
            System.out.println(url);
        }
    }

    public static void runHtmlParserExamples() {
        // Example 1
        System.out.println("Example 1:");
        List<String> urls1 = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us"
        );
        List<int[]> edges1 = Arrays.asList(
                new int[]{2, 0},
                new int[]{2, 1},
                new int[]{3, 2},
                new int[]{3, 1},
                new int[]{0, 4}
        );
        String startUrl1 = "http://news.yahoo.com/news/topics/";
        run(urls1, edges1, startUrl1);

        // Example 2
        System.out.println("Example 2:");
        List<String> urls2 = Arrays.asList(
                "http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com"
        );
        List<int[]> edges2 = Arrays.asList(
                new int[]{0, 2},
                new int[]{2, 1},
                new int[]{3, 2},
                new int[]{3, 1},
                new int[]{3, 0}
        );
        String startUrl2 = "http://news.google.com";
        run(urls2, edges2, startUrl2);
    }
}