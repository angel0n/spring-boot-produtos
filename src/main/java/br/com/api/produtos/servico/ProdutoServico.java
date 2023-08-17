package br.com.api.produtos.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.api.produtos.modelo.ProdutoModelo;
import br.com.api.produtos.modelo.RespostaModelo;
import br.com.api.produtos.repositorio.ProdutoRepositorio;

@Service
public class ProdutoServico {
    @Autowired
    private ProdutoRepositorio pr;

    @Autowired
    private RespostaModelo rm;

    public Iterable<ProdutoModelo> listar() {
        return pr.findAll();
    }

    public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao) {
        if (pm.getNome().equals("")) {
            rm.setMensagem("O nome do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }

        if (pm.getMarca().equals("")) {
            rm.setMensagem("A marca do produto é obrigatório!");
            return new ResponseEntity<RespostaModelo>(rm, HttpStatus.BAD_REQUEST);
        }

        HttpStatus status = HttpStatus.CREATED;

        if(acao.equals("alterar")){
            status = HttpStatus.OK;
        }

        return new ResponseEntity<ProdutoModelo>(pr.save(pm),status);

    }

    public ResponseEntity<RespostaModelo> remover(Long codigo){
        pr.deleteById(codigo);

        rm.setMensagem("O produto foi removido!");

        return new ResponseEntity<RespostaModelo>(rm, HttpStatus.OK);
    }

}
