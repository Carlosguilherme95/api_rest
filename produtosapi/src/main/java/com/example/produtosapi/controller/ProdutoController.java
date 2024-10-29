package com.example.produtosapi.controller;

import com.example.produtosapi.ProdutoRecordDto;
import com.example.produtosapi.model.Produto;
import com.example.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.ConstructorProperties;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("produtos")
public class ProdutoController {


    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    private ProdutoRecordDto toDto(Produto produto) {
        return new ProdutoRecordDto(produto.getNome(), produto.getDescricao(), produto.getPreco());
    }

    @PostMapping
    public ResponseEntity<ProdutoRecordDto> salvaProduto(@RequestBody ProdutoRecordDto produtoRecordDto) {
        Produto novoProduto = new Produto();
        novoProduto.setNome(produtoRecordDto.nome());
        novoProduto.setDescricao(produtoRecordDto.descricao());
        novoProduto.setPreco(produtoRecordDto.preco());

        Produto produtoSalvo = produtoRepository.save(novoProduto);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(produtoSalvo));
    }

    @GetMapping("/busca")
    public List<Produto> buscaTodosProdutos(){
        return produtoRepository.findAll();
    }


    @GetMapping("/{id}")
    public Produto produtoPorId(@PathVariable("id") Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deletaProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void atualizaProduto(@PathVariable Long id,
                                @RequestBody Produto produto) {
        produto.setId(id);
        produtoRepository.save(produto);
    }

    @GetMapping
    public List<Produto> pesquisa(@RequestParam("nome") String nome) {
        return produtoRepository.findByNome(nome);
    }
}




