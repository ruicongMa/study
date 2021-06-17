package com.mark;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mark
 * @date 2020/11/11 16:41
 */
public class BufferedWordReader {

    private static long wordCnt = 0;
    private static long lineCnt = 0;
    private static long nextLineCnt = 1;
    private static List<String> curWords;

    public static String readWord() throws IOException {
        String word = null;
        wordCnt++;
        if (wordCnt < nextLineCnt) {
            long idx = curWords.size() - (nextLineCnt - wordCnt);
            return curWords.get(Integer.valueOf(String.valueOf(idx)));
        }

        try (
                FileInputStream fis = new FileInputStream("/Users/mrc/maruicong/study/idea-workspaces/study/src/main/java/novels.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
        ) {
            List<String> list = br.lines().skip(lineCnt).limit(1).collect(Collectors.toList());
            lineCnt++;
            String[] words = list.get(0).split("\\s+");
            curWords = Arrays.asList(words);
            nextLineCnt += curWords.size();
            word = words[0];
        }

        return word;
    }

    public static String readWord2(String path) throws IOException {
        String word = null;
        wordCnt++;
        if (wordCnt < nextLineCnt) {
            long idx = curWords.size() - (nextLineCnt - wordCnt);
            return curWords.get(Integer.valueOf(String.valueOf(idx)));
        }

        try (
                FileInputStream fis = new FileInputStream("/Users/mrc/maruicong/study/idea-workspaces/study/src/main/java/novels.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
        ) {
            List<String> list = br.lines().skip(lineCnt).limit(1).collect(Collectors.toList());
            lineCnt++;
            String[] words = list.get(0).split("\\s+");
            curWords = Arrays.asList(words);
            nextLineCnt += curWords.size();
            word = words[0];
        }

        return word;
    }

    public static void main(String[] args) throws IOException {
        try (
                FileInputStream fis = new FileInputStream("/Users/mrc/maruicong/study/idea-workspaces/study/src/main/java/novels.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
        ) {

        }
    }
}
