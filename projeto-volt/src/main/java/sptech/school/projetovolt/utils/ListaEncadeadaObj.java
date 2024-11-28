package sptech.school.projetovolt.utils;

import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;

import java.util.ArrayList;
import java.util.List;

public class ListaEncadeadaObj {
    private NodeObj<UsuarioConsultaDto> head;
    private NodeObj<UsuarioConsultaDto> tail;

    public ListaEncadeadaObj() {
        this.head = new NodeObj<>(null);
        this.tail = new NodeObj<>(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
    }

    public void addNode(UsuarioConsultaDto valor){
        NodeObj<UsuarioConsultaDto> newNode = new NodeObj<>(valor);
        NodeObj<UsuarioConsultaDto> aux = head.getNext();
        newNode.setNext(aux);
        head.setNext(newNode);
        newNode.setPrev(head);
        aux.setPrev(newNode);
    }
    public void show(){
        NodeObj<UsuarioConsultaDto> nodeAtual = head.getNext();
        while(nodeAtual.getNext() != null){
            System.out.print(nodeAtual.getInfo().getEmail()+ " ->");
            nodeAtual = nodeAtual.getNext();
        }
        System.out.println("\n");
    }

    public List<UsuarioConsultaDto> getAll(){
        List<UsuarioConsultaDto> nodes = new ArrayList<>();
        NodeObj<UsuarioConsultaDto> actualNode = head.getNext();
        while ((actualNode != head && actualNode != tail) && actualNode != null){
            nodes.add(actualNode.getInfo());
            actualNode = actualNode.getNext();
        }
        return nodes;
    }

    public NodeObj<UsuarioConsultaDto> searchNode(UsuarioConsultaDto valor){
        NodeObj<UsuarioConsultaDto> nodeAtual = head.getNext();
        while (nodeAtual != null){
            if(nodeAtual.getInfo().equals(valor)){
                return nodeAtual;
            }
            nodeAtual = nodeAtual.getNext();
        }
        return tail;
    }

    public Boolean removeNode(UsuarioConsultaDto valor){
        NodeObj<UsuarioConsultaDto> nodeAtual = head.getNext();
        NodeObj<UsuarioConsultaDto> ant = head;
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
        NodeObj<UsuarioConsultaDto> actualNode= head.getNext();
        int tam = 0;
        while(actualNode.getInfo() != null){
            tam++;
            actualNode = actualNode.getNext();
        }
        return tam;
    }
}
