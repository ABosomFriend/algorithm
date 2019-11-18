package com.yizijun.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟lru 的 get和put方法
 *
 * @author yizijun
 * @version 1.0.0
 * @since 2019-11-14
 */
public class LRUCache<K, V> {

    private int limit;

    private Map<K, Node<K, V>> map;

    private Node<K, V> head;

    private Node<K, V> tail;

    public LRUCache(int limit) {
        this.limit = limit;
        map = new ConcurrentHashMap<>(10);

        //添加两个初始节点，避免后面过多的判空逻辑
        head = new Node<>();
        tail = new Node<>();
        head.pre = tail;
        tail.next = head;
    }


    /**
     * 如果存在对应的key，用新值覆盖原值
     *
     * @param key
     * @param value
     * @return 返回原值， 不存在返回null
     */
    public V put(K key, V value) {

        V oldValue;

        if (!map.containsKey(key)) {
            if (map.size() >= limit) {
                map.remove(tail.next.key);
                deleteNode(tail.next);
            }
            Node<K, V> node = new Node<>(key, value);
            addNode(node);
            map.put(key, node);
            oldValue = value;
        } else {
            Node<K, V> node = map.get(key);
            oldValue = node.value;
            deleteNode(node);
            node.value = value;
            addNode(node);
        }
        return oldValue;
    }


    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        Node<K, V> node = map.get(key);
        freshNode(node);
        return node.value;
    }

    private void freshNode(Node<K,V> node) {
        //先删除节点
        deleteNode(node);
        //再添加节点
        addNode(node);
    }

    /**
     * 删除一个节点
     *
     * @param node
     */
    private void deleteNode(Node<K,V> node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }

    /**
     * 在头添加一个节点
     *
     * @param node
     */
    private void addNode(Node<K, V> node) {
        node.next = head;
        node.pre = head.pre;
        node.pre.next = node;
        head.pre = node;
    }

    private static class Node<K, V> {

        private Node<K, V> pre;

        private Node<K, V> next;

        private K key;

        private V value;

        public Node() {

        }


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("a", "aa");
        lruCache.put("b", "bb");
        lruCache.put("c", "cc");
        lruCache.put("a", "aaaaa");
        lruCache.get("b");
    }
}
