package com.yizijun.utils;


/**
 * 实现前缀树
 * 原理参考: https://leetcode-cn.com/problems/implement-trie-prefix-tree/solution/shi-xian-trie-qian-zhui-shu-by-leetcode/
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-11-27
 */
public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * 插入
     *
     * @param word
     */
    public void insert(String word) {

        TrieNode trieNode = root;

        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!trieNode.contains(c)) {
                trieNode.putTrieNode(c, new TrieNode());
            }
            trieNode = trieNode.getTrieNode(c);
        }

        trieNode.setEnd(true);
    }

    /**
     * 找前缀
     *
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    private TrieNode searchNode(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            if (!trieNode.contains(c)) {
                return null;
            }
            trieNode = trieNode.getTrieNode(c);
        }
        return trieNode;
    }

    /**
     * 找结点
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        TrieNode trieNode = searchNode(word);
        return trieNode != null && trieNode.getIsEnd();
    }


    /**
     * 假定由[a-z] 26个字母组成前缀树
     */
    public static class TrieNode {

        private TrieNode[] trieNodes;

        private final int ROW = 26;

        /**
         * 判断是否为一个词结束
         */
        private boolean isEnd;

        public TrieNode() {
            trieNodes = new TrieNode[ROW];
        }

        public TrieNode getTrieNode(char key) {
            return trieNodes[key - 'a'];
        }

        public void putTrieNode(char key, TrieNode value) {
            trieNodes[key - 'a'] = value;
        }

        public boolean contains(char key) {
            return trieNodes[key - 'a'] != null;
        }

        public void setEnd(boolean isEnd) {
            this.isEnd = isEnd;
        }

        public boolean getIsEnd() {
            return this.isEnd;
        }

    }


}
