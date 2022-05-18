package com.ajsmdllz.fitomatic.Search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * List of keywords for Activities and Dates (Time) Used at Tokenization stage
 */
public class KeywordList {
    public static ArrayList<String> ActivityList = new ArrayList<>(Arrays.asList(
            "running",
            "walking",
            "weightlifting",
            "rowing",
            "yoga",
            "soccer",
            "hiking",
            "gymnastics",
            "afl",
            "tennis",
            "rugby",
            "surfing",
            "golf",
            "bowling",
            "karate",
            "bouldering",
            "rockclimbing",
            "cycling",
            "mountainbiking",
            "swimming",
            "cricket",
            "judo",
            "taiquandao"
    ));

    public static ArrayList<String> TimeList = new ArrayList<>(Arrays.asList(
            "monday",
            "tuesday",
            "wednesday",
            "thursday",
            "friday",
            "saturday",
            "sunday",
            "evening",
            "morning",
            "afternoon"
    ));

}
