package sptech.school.projetovolt.utils;

import org.springframework.stereotype.Component;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class HashTableObj {
    private ListaEncadeadaObj[] tab;

    public HashTableObj() {
        this.tab = new ListaEncadeadaObj[8];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new ListaEncadeadaObj();
        }
    }
    private int hashFunction(UsuarioConsultaDto object){
        return Math.abs(object.hashCode())%tab.length;
    }
    public void put(UsuarioConsultaDto object){
        int key = hashFunction(object);
        tab[key].addNode(object);
    }
    public NodeObj<UsuarioConsultaDto> get(UsuarioConsultaDto object){
        int key = hashFunction(object);
        NodeObj<UsuarioConsultaDto> nodeFinded = tab[key].searchNode(object);
        if(nodeFinded == null){
            return null;
        }
        return nodeFinded;
    }
    public void show(){
        for (int i = 0; i < tab.length; i++) {
            System.out.print("\nEntrada " + i  +" :");
            if(tab[i].getSize() == 0){
                System.out.print("Lista vazia");
            }else{
                tab[i].show();
            }
        }
        System.out.println("\n");
    }
    public Boolean isEmpty(){
        int aux = 0;
        for (int i = 0; i < tab.length; i++) {
            if(tab[i].getSize() == 0){
                aux++;
            }
        }
        if(aux == tab.length){
            return true;
        }
        return false;
    }
    public Boolean remove(UsuarioConsultaDto object){
        int key = hashFunction(object);
        if(tab[key].removeNode(object)){
            return true;
        }
        return false;
    }
    public int[] size(){
        int[] bucketSizes = new int[8];
        for (int i = 0; i < tab.length; i++) {
            bucketSizes[i] = tab[i].getSize();
        }
        return bucketSizes;
    }
    public List<UsuarioConsultaDto> getAll(){
        List<UsuarioConsultaDto> objects = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            if(tab[i] != null){
                objects.addAll(tab[i].getAll());
            }
        }
        return objects;
    }


}



