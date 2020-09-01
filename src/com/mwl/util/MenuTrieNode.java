package com.mwl.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void setParent(MenuTrieNode parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuTrieNode that = (MenuTrieNode) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
             //   &&
              //  Objects.equals(children, that.children);
             //   &&
             //   Objects.equals(parent, that.parent);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(title, description, children, parent);
//    }

}
