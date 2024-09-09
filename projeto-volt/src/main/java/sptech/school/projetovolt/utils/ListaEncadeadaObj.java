package sptech.school.projetovolt.utils;

public class ListaEncadeadaObj<T> {
    private NodeObj head;

    public ListaEncadeadaObj() {
        this.head = new NodeObj<T>(null);
    }

    public void addNode(T valor){
        NodeObj<T> newNode = new NodeObj<T>(valor);
        newNode.setNext(head.getNext());
        head.setNext(newNode);
    }
    public  void show(){
        NodeObj<T> actualNode = head.getNext();
        while(actualNode != null){
            System.out.print(actualNode.getInfo() +"|");
            actualNode = actualNode.getNext();
        }
    }

    public NodeObj<T> searchNode(T valor){
        NodeObj<T> actualNode = head.getNext();
        while (actualNode != null){
            if(actualNode.getInfo().equals(valor)){
                return actualNode;
            }else{
                actualNode = actualNode.getNext();
            }
        }
        return null;
    }

    public Boolean removeNode(T valor){
        NodeObj<T> ant = head;
        NodeObj<T> actualNode = head.getNext();
        while(actualNode!= null){
            if(actualNode.getInfo().equals(valor)){
                ant.setNext(actualNode.getNext());
                return true;
            }
            ant =actualNode;
            actualNode = actualNode.getNext();
        }
        return false;
    }

    public int getSize(){
        NodeObj<T> actualNode= head.getNext();
        int tam = 0;
        while(actualNode != null){
            tam++;
            actualNode = actualNode.getNext();
        }
        return tam;
    }


}
