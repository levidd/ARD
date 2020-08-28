package com.mwl.util;

import java.util.ArrayList;
import java.util.List;

public class MenuTrieNode {
    String title;
    String description;
    List<MenuTrieNode> children;
    MenuTrieNode parent;

    public MenuTrieNode(String title, String description) {
        this.title = title;
        this.description = description;
        children = new ArrayList<>();
    }

    public MenuTrieNode(String title, String description, MenuTrieNode parent) {
        this(title, description);
        this.parent = parent;
    }

    public void addChild(MenuTrieNode child) {
        if (child != null) {
            children.add(child);
        }
    }

    public List<MenuTrieNode> getChildren() {
        return children;
    }

    public MenuTrieNode getChild(int child) throws IllegalArgumentException {
        if (child < 0 || child >= children.size()) {
            throw new IllegalArgumentException(); // deal with exception later
        }

        return children.get(child);
    }

    public MenuTrieNode getParent() {
        return parent;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
