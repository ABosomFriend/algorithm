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
public class LRUCache<K,V> {

    private int limit;

    private Map<K, Node<K,V>> map;

    private Node head;

    private Node tail;

    public LRUCache(int limit) {
        this.limit = limit;
        map = new ConcurrentHashMap<>(10);
    }


    /**
     * 如果存在对应的key，用新值覆盖原值
     * @param key
     * @param value
     * @return 返回原值， 不存在返回null
     */
    public V put(K key, V value) {
        if (!map.containsKey(key)) {
            if (map.size() >= limit) {
                deleteNode(tail);
            }
            Node<K, V> node = new Node<>(key, value);
            addNode(node);
            map.put(key, node);
            return null;
        }

        Node<K,V> oldNode = map.get(key);
        V oldValue = oldNode.value;
        //赋值新的value
        oldNode.value = value;
        freshNode(oldNode);
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

    private void freshNode(Node node) {
        //先删除节点
        deleteNode(node);
        //再添加节点
        addNode(node);
    }

    /**
     * 删除一个节点
     * @param node
     */
    private void deleteNode(Node node) {
        if (node == tail) {
            tail = tail.next;
            tail.pre.next = null;
            tail.pre = null;
        } else if (node == head) {
            head = head.pre;
            head.next.pre = null;
            head.next = null;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }
    }

    /**
     * 在头添加一个节点
     * @param node
     */
    private void addNode(Node node) {
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            head.next = node;
            node.pre = head;
            head = node;
        }
    }

    private static class Node<K,V> {

        private Node pre;

        private Node next;

        private K key;

        private V value;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache<>(3);
        lruCache.put("a", "aa");
        lruCache.put("b","bb" );
        lruCache.put("c", "cc");
        lruCache.put("a", "aaaaa");
        lruCache.get("b");
    }
}
