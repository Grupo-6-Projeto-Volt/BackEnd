package sptech.school.projetovolt.service.hashtable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import sptech.school.projetovolt.entity.clickProduto.ClickProduto;
import sptech.school.projetovolt.entity.favoritos.Favoritos;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class HashTableService {
    private final HashTableObj hashTable;
    private final UsuarioService usuarioService;
    private final ProdutoRepository produtoRepository;

    public HashTableService(HashTableObj hashTable, UsuarioService usuarioService, ProdutoRepository produtoRepository) {
        this.hashTable = hashTable;
        this.usuarioService = usuarioService;
        this.produtoRepository = produtoRepository;
        try {
            lerArquivoHash();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserir(Usuario usuarioInserido){
        UsuarioConsultaDto usuarioFormatado = UsuarioMapper.toUsuarioConsultaDto(usuarioInserido);
        if(!usuarioFormatado.getCategoria().equals("Admin")){
            hashTable.put(usuarioFormatado);
        }
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
    }
    public void gravarHashTable(){
        List<UsuarioConsultaDto> usuarios = hashTable.getAll();
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
    public List<Produto> listarProdutosUsuario(UsuarioConsultaDto usuarioFormatado,Integer limite){
        List<Produto> produtos = new ArrayList<>();
        NodeObj<UsuarioConsultaDto> node = hashTable.get(usuarioFormatado);
        // usuario sozinho na lista
        if(node.getNext().getInfo() == null && node.getPrev().getInfo() == null){
            Usuario usuarioRecomendado = usuarioService.buscarUsuarioPorId(node.getInfo().getId());
            //validar favoritos
            if(usuarioRecomendado.getFavoritos().isEmpty()){
                List<ClickProduto> aux = usuarioRecomendado.getClickProdutos().stream().toList();
                if(aux.isEmpty()){
                    produtos = produtoRepository.recomendarProdutosParaUsuariosNovos(limite);
                }else{
                    int paramProduto = aux.stream().findAny().get().getProduto().getId();
                    produtos = produtoRepository.recomendarParaUsuariosUnicos(limite,paramProduto);
                }
            }else{
                for (Favoritos favoritos : usuarioRecomendado.getFavoritos().stream().toList()) {
                    produtos.add(favoritos.getProduto());
                }
                for (ClickProduto clickProduto : usuarioRecomendado.getClickProdutos().stream().toList()) {
                    produtos.add(clickProduto.getProduto());
                }
                int paramProduto = produtos.get(produtos.size()-1).getId();
                produtos.addAll(produtoRepository.recomendarParaUsuariosUnicos(limite,paramProduto));
            }
        }
        //validar se possui vizinhos
        if(node.getNext().getInfo() == null && node.getPrev().getInfo() != null){
            UsuarioConsultaDto usuarioVizinho = (UsuarioConsultaDto) node.getPrev().getInfo();
            Usuario usuarioRecomendado = usuarioService.buscarUsuarioPorId(usuarioVizinho.getId());
            Usuario usuarioSelecionado = usuarioService.buscarUsuarioPorId(node.getInfo().getId());
            List<ClickProduto> aux = usuarioRecomendado.getClickProdutos();
            aux.addAll(usuarioSelecionado.getClickProdutos());
            for (ClickProduto clickProduto : aux) {
                produtos.add(clickProduto.getProduto());
            }
            if(produtos.isEmpty()){
                produtos.addAll(produtoRepository.recomendarProdutosParaUsuariosNovos(limite));
            }else{
                //adicionar produtos complementares vindos da query
                int idProduto = produtos.stream().findAny().get().getId();
                produtos.addAll(produtoRepository.recomendarParaUsuarioComVizinhoUnico(usuarioRecomendado.getId(),idProduto));

                //embaralha a lista
                Collections.shuffle(produtos);
            }

        } else if (node.getNext().getInfo() != null && node.getPrev().getInfo() == null) {
            UsuarioConsultaDto usuarioVizinho = (UsuarioConsultaDto) node.getNext().getInfo();
            Usuario usuarioRecomendado = usuarioService.buscarUsuarioPorId(usuarioVizinho.getId());
            Usuario usuarioSelecionado = usuarioService.buscarUsuarioPorId(node.getInfo().getId());
            List<ClickProduto> aux = usuarioRecomendado.getClickProdutos();
            aux.addAll(usuarioSelecionado.getClickProdutos());
            for (ClickProduto clickProduto : aux) {
                produtos.add(clickProduto.getProduto());
            }
            if(produtos.isEmpty()){
                produtos.addAll(produtoRepository.recomendarProdutosParaUsuariosNovos(limite));
            }else{
                int idProduto = produtos.stream().findAny().get().getId();
                produtos.addAll(produtoRepository.recomendarParaUsuarioComVizinhoUnico(usuarioRecomendado.getId(),idProduto));
                Collections.shuffle(produtos);
            }

        }else if(node.getNext().getInfo() != null && node.getPrev().getInfo() != null){
            UsuarioConsultaDto usuarioNext = (UsuarioConsultaDto) node.getNext().getInfo();
           UsuarioConsultaDto usuarioPrev = (UsuarioConsultaDto) node.getPrev().getInfo();

            Usuario usuarioVizinhoDireito = usuarioService.buscarUsuarioPorId(usuarioNext.getId());
            Usuario usuarioVizinhoEsquerdo = usuarioService.buscarUsuarioPorId(usuarioPrev.getId());
            Usuario usuarioAtual = usuarioService.buscarUsuarioPorId(node.getInfo().getId());

            List<ClickProduto> aux = usuarioVizinhoDireito.getClickProdutos();
            aux.addAll(usuarioVizinhoEsquerdo.getClickProdutos());
            aux.addAll(usuarioAtual.getClickProdutos());
            for (ClickProduto clickProduto : aux) {
                produtos.add(clickProduto.getProduto());
            }
            if(produtos.isEmpty()){
                produtos.addAll(produtoRepository.recomendarProdutosParaUsuariosNovos(limite));
            }else{
                int idProduto = produtos.stream().findAny().get().getId();
                produtos.addAll(produtoRepository.recomendarParaUsuarioComVizinhos(usuarioVizinhoEsquerdo.getId(),usuarioVizinhoDireito.getId(),idProduto));
                Collections.shuffle(produtos);
            }
        }
       //se o nodo for nulo
       if(node == null){
            produtos = produtoRepository.recomendarProdutosParaUsuariosNovos(limite);
           Collections.shuffle(produtos);
        }
        return produtos;
    }
}
