package sptech.school.projetovolt.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class NodeObj<T> {
    private T info;
    @JsonIgnore
    private NodeObj<T> next;
    @JsonIgnore
    private NodeObj<T> prev;

    public NodeObj(T info) {
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public NodeObj getNext() {
        return next;
    }

    public void setNext(NodeObj next) {
        this.next = next;
    }

    public NodeObj getPrev() {
        return prev;
    }
    public void setPrev(NodeObj prev) {
        this.prev = prev;
    }
}
