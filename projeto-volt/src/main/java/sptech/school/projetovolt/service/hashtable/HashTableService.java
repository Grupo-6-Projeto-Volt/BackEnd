package sptech.school.projetovolt.service.hashtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.produto.Produto;
import sptech.school.projetovolt.entity.produto.repository.ProdutoRepository;
import sptech.school.projetovolt.entity.usuario.Usuario;
import sptech.school.projetovolt.service.usuario.UsuarioService;
import sptech.school.projetovolt.service.usuario.dto.UsuarioConsultaDto;
import sptech.school.projetovolt.service.usuario.dto.UsuarioMapper;
import sptech.school.projetovolt.utils.HashTableObj;
import sptech.school.projetovolt.utils.NodeObj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class HashTableService {
    private final HashTableObj hashTable;
    private final UsuarioService usuarioService;

    public HashTableService(HashTableObj hashTable, UsuarioService usuarioService) {
        this.hashTable = hashTable;
        this.usuarioService = usuarioService;
        try {
            lerArquivoHash();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserir(Usuario usuarioInserido){
        UsuarioConsultaDto usuarioFormatado = UsuarioMapper.toUsuarioConsultaDto(usuarioInserido);
        hashTable.put(usuarioFormatado);
    }
    public void remover(Usuario usuarioRemovido){
        hashTable.remove(buscar(usuarioRemovido));
    }
    public UsuarioConsultaDto buscar(Usuario usuarioBuscado){
        UsuarioConsultaDto usuarioFormatado = UsuarioMapper.toUsuarioConsultaDto(usuarioBuscado);
        return hashTable.get(usuarioFormatado).getInfo();
    }
    public List<UsuarioConsultaDto> listar(){
        return hashTable.getAll();
    }
    public void exibir(){
        hashTable.show();
//        if(!hashTable.isEmpty()){
//            hashTable.show();
//        }else{
//            try {
//                lerArquivoHash();
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//                throw new RuntimeException(e);
//            }
//        }
    }
    public void gravarHashTable(){
        List<UsuarioConsultaDto> usuarios = hashTable.getAll();
        usuarios.stream().toList();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(".src/main/resources/usuarios.json"),usuarios);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao serializar os dados para JSON", e);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao registrar usuário no arquivo json",e);
        }
    }
    public void lerArquivoHash() throws JsonProcessingException {
        File usuarioFile = new File(".src/main/resources/usuarios.json");
        if(usuarioFile.exists()){
            ObjectMapper objectMapper = new ObjectMapper();
            List<UsuarioConsultaDto> users = null;
            try {
                users = objectMapper.readValue(usuarioFile, new TypeReference<List<UsuarioConsultaDto>>() {});
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (UsuarioConsultaDto user : users) {
                hashTable.put(user);
            }
        }
    }
    public void temp(){
        List<Usuario> users = usuarioService.listarUsuarios();
        for (Usuario user : users) {
            inserir(user);
        }
    }
    public List<Produto> listarProdutosUsuario(UsuarioConsultaDto usuarioFormatado){
        List<Produto> produtos = new ArrayList<>();
        NodeObj<UsuarioConsultaDto> node = hashTable.get(usuarioFormatado);
        if(node.getNext() != null){
            UsuarioConsultaDto usuarioSeguinte = (UsuarioConsultaDto) node.getNext().getInfo();
            List<ClickProduto> cliques = usuarioService.buscarUsuarioPorId(usuarioSeguinte.getId()).getClickProdutos();
            for (ClickProduto clique : cliques) {
                produtos.add(clique.getProduto());
            }
        }else{
            List<ClickProduto> cliquesDoUsuario = usuarioService.buscarUsuarioPorId(node.getInfo().getId()).getClickProdutos();
            if(!cliquesDoUsuario.isEmpty()){
                for (ClickProduto clickProduto : cliquesDoUsuario) {
                    produtos.add(clickProduto.getProduto());
                }
            }
        }
        return produtos;
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
