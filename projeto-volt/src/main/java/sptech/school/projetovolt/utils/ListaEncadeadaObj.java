package sptech.school.projetovolt.utils;

import java.util.ArrayList;
import java.util.List;

public class ListaEncadeadaObj<T> {
    private NodeObj<T> head;
    private NodeObj<T> tail;

    public ListaEncadeadaObj() {
        this.head = new NodeObj<>(null);
        this.tail = new NodeObj<>(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    public void addNode(T valor){
        NodeObj<T> newNode = new NodeObj<T>(valor);
        NodeObj<T> aux = head.getNext();
        newNode.setNext(aux);
        head.setNext(newNode);
        newNode.setPrev(head);
        aux.setPrev(newNode);
    }
    public void show(){
        NodeObj<T> nodeAtual = head.getNext();
        while(nodeAtual.getNext() != null){
            System.out.print(nodeAtual.getInfo()+ " ->");
            nodeAtual = nodeAtual.getNext();
        }
        System.out.println("\n");
    }

    public List<T> getAll(){
        List<T> nodes = new ArrayList<>();
        NodeObj<T> actualNode = head.getNext();
        while ((actualNode != head && actualNode != tail) && actualNode != null){
            nodes.add(actualNode.getInfo());
            actualNode = actualNode.getNext();
        }
        return nodes;
    }

    public NodeObj<T> searchNode(T valor){
        NodeObj<T> actualNode = head.getNext();
        NodeObj<T> ant = head;
        while (actualNode != null){
            if(actualNode == valor){
                return actualNode;
            }
                ant = actualNode;
                actualNode = actualNode.getNext();
        }
        return null;
    }

    public Boolean removeNode(T valor){
        NodeObj<T> nodeAtual = head.getNext();
        NodeObj<T> ant = head;
        while (nodeAtual != null){
            if(nodeAtual != tail){
                nodeAtual.getNext().setPrev(ant);
                ant.setNext(nodeAtual.getNext());
                return true;
            }
            ant = nodeAtual;
            nodeAtual = nodeAtual.getNext();
        }
        return false;
    }

    public int getSize(){
        NodeObj<T> actualNode= head.getNext();
        int tam = 0;
        while(actualNode.getInfo() != null){
            tam++;
            actualNode = actualNode.getNext();
        }
        return tam;
    }
}
