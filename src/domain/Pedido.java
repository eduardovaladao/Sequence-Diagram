package domain;

import java.util.List;

public class Pedido {

    private List<LinhaDePedido> itens;

    private Cliente cliente;

    public Double calcularPreco(){
        Double precoFinal = 0.0;
        for(LinhaDePedido linha : itens) {
            Integer quantidade = linha.obterQuantidade();
            Produto produto = linha.obterProduto();
            Double preco = produto.obterDetahesPreco();
            Double precoBase = calcularPrecoBase(quantidade, preco);
            Double precoComDesconto = calcularDescontos(precoBase);
            precoFinal += precoComDesconto;
        }
        return precoFinal;
    }

    private Double calcularDescontos(Double precoBase) {
        Double percentualDesconto = cliente.ObterInformacaoDeDesconto();
        return precoBase - (precoBase * percentualDesconto);
    }

    private Double calcularPrecoBase(Integer quantidade, Double preco) {
        return quantidade * preco;
    }
}