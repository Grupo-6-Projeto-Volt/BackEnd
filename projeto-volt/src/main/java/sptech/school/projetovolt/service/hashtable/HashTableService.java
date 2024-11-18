package sptech.school.projetovolt.service.hashtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.utils.HashTableObj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTableService<T> {
    private final HashTableObj<T> hashTable;
    public T inserir(T objetoInserido){
        return hashTable.put(objetoInserido);
    }
    public void remover(T objetoInserido){
        hashTable.remove(objetoInserido);
    }
    public T buscar(T objetoBuscado){
        return hashTable.get(objetoBuscado);
    }
    public List<T> listar(){
        return hashTable.getAll();
    }
    public void exibir(){
        if(!hashTable.isEmpty()){
            hashTable.show();
        }else{
            try {
                lerArquivoHash();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public void gravarHashTable(){
        List<T> usuarios = hashTable.getAll();
        usuarios.stream().toList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("./usuarios.json"),usuarios);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao serializar os dados para JSON", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao registrar usuário no arquivo json",e);
        }
    }
    public void lerArquivoHash() throws JsonProcessingException {
        File usuarioFile = new File("./usuarios.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<UsuarioConsultaDto> users = null;
        try {
            users = objectMapper.readValue(usuarioFile, new TypeReference<List<UsuarioConsultaDto>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (UsuarioConsultaDto user : users) {
                hashTable.put((T) user);
            }
    }
//    public void inserirProdutos(){
//        if(!hashTable.isEmpty()){
//            throw new IllegalStateException("Hash table já está preenchida");
//        }
//        produtoRepository.findAll().stream().forEach(produto -> {
//            hashTable.put(produto.getNome().toLowerCase());
//        });
//    }
//
//    public String buscarProdutoPorNome(String nomeProduto){
//        if(hashTable.isEmpty()){
//            throw new IllegalStateException("Tabela Hash vazia!");
//        }
//        return hashTable.get(nomeProduto.toLowerCase());
//    }
//    public Boolean removerProdutoPorNome(String nomeProduto){
//        if(hashTable.isEmpty()){
//            throw new IllegalStateException("Tabela Hash vazia!");
//        }
//        return hashTable.remove(nomeProduto);
//    }
//    public void exibirProdutos(){
//        if(hashTable.isEmpty()){
//            throw new IllegalStateException("Tabela Hash vazia!");
//        }
//        hashTable.show();
//    }

}
