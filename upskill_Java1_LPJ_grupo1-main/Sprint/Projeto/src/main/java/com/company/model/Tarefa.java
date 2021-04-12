/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

/**
 * Objecto Tarefa.
 * 
 * @author diana
 */
public class Tarefa {
    /**
     * Declaração da referência da Tarefa
     */
    private String ref;

    /**
     * Declaração da designação da Tarefa
     */
    private String designacao;

    /**
     * Declaração da descrição informal da Tarefa
     */
    private String descrInformal;

    /**
     * Declaração da descrição técnica da Tarefa
     */
    private String descrTecnica;

    /**
     * Declaração da duração estimada da Tarefa
     */
    private Integer duracaoEst;

    /**
     * Declaração do custo estimado da Tarefa
     */
    private Double custoEst;

    /**
     * Declaração do objecto categoria de tarefa
     */
    private CategoriaTarefa catTarefa;

    /**
     * Declaração do objecto colaborador
     */
    private Colaborador colab;
    
    /**
     * Atribuição de um valor por omissão a uma constante
     */
    private static final int LIM_MIN_CARATERES = 10;

    /**
     * Constrói uma instância de Tarefa recebendo a referência única por
     * Organização, a designação, a descrição informal, a descrição técnica, a
     * estimativa de duração, a estimativa de custo, categoria de tarefa, e
     * colaborador. Só é criada se todos os parâmetros forem válidos.
     *
     * @param ref referência da Tarefa
     * @param designacao designação da Tarefa
     * @param descrInformal descrição informal da Tarefa
     * @param descrTecnica descrição técnica da Tarefa
     * @param duracaoEst duração estimada da Tarefa
     * @param custoEst custo estimado da Tarefa
     * @param catTarefa categoria de tarefa em que se vai enquadrar a Tarefa
     * @param colab colaborador que faz o registo da Tarefa
     */
    public Tarefa(String ref, String designacao, String descrInformal, String descrTecnica,
            Integer duracaoEst, Double custoEst, CategoriaTarefa catTarefa, Colaborador colab) {
        if ((ref == null) || (designacao == null) || (descrInformal == null) || (descrTecnica == null) || (duracaoEst == null) || (custoEst == null) || (catTarefa == null) || (colab == null)
                || (ref.isEmpty()) || (designacao.isEmpty()) || (descrInformal.isEmpty()) || ((descrTecnica.isEmpty())) || (duracaoEst == 0) || (custoEst == 0)) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }
        
        this.ref = ref;
        setDesignacao(designacao);
        setDescrInformal(descrInformal);
        setDescrTecnica(descrTecnica);
        setDuracaoEst(duracaoEst);
        setCustoEst(custoEst);
        setCatTarefa(catTarefa);
        setColab(colab);

        this.catTarefa = catTarefa;
        this.colab = colab;
    }

    /**
     * Verifica se a referência da Tarefa é igual à referência de outra Tarefa,
     * recebida por parêmetro.
     *
     * @param ref outra referência da Tarefa com a qual se compara a referêcia
     * da Tarefa
     * @return true se a referência da Tarefa for igual à a referência da Tarefa
     * recebida por parâmetro caso contrário devolve false.
     */
    public boolean hasRef(String ref) {
        return this.getRef().equalsIgnoreCase(ref);
    }

    //Refactor get's e set's
    //<editor-fold defaultstate="collapsed">
    
    /**
     * Devolve a referência da Tarefa
     *
     * @return referência da Tarefa
     */
    public String getRef() {
        return ref;
    }

    /**
     * Modifica a referência da Tarefa.
     *
     * @param ref nova referência da Tarefa
     */
    private void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * Devolve a designação da Tarefa.
     *
     * @return designação da Tarefa
     */
    public String getDesignacao() {
        return designacao;
    }

    /**
     * Modifica a designação da Tarefa. Verifica se o tamanho da nova designação
     * está entre 10 e 49 caracteres, caso contrário apresenta mensagem de erro.
     *
     * @param designacao nova designação da Tarefa
     */
    private void setDesignacao(String designacao) {
        if (designacao.length() > LIM_MIN_CARATERES) {
            if(designacao.length() < 49 ){
                this.designacao = designacao;
            }else{
                throw new IllegalArgumentException("Designação deve ser menor do que 50 Caracteres");
            }
        } else {
            throw new IllegalArgumentException("Designação deve ter mais de 10 carateres");
        }
    }

    /**
     * Devolve a descrição informal da Tarefa.
     *
     * @return descrição informal da Tarefa
     */
    public String getDescrInformal() {
        return descrInformal;
    }
    

    /**
     * Modifica a descrição informal da Tarefa. Verifica se o tamanho da
     * descrição informal está entre 10 e 49 caracteres, caso contrário apresenta
     * mensagem de erro.
     *
     * @param descrInformal nova descrição informal da Tarefa
     */
    private void setDescrInformal(String descrInformal) {
        if (descrInformal.length() > LIM_MIN_CARATERES) {
            if(descrInformal.length() < 49 ){
                this.descrInformal = descrInformal;
            }else{
                throw new IllegalArgumentException("Descrição informal deve ser menor do que 50 Caracteres");
            }
        } else {
            throw new IllegalArgumentException("Descrição informal deve ter mais de 10 carateres");
        }

    }

    /**
     * Devolve a descrição técnica da Tarefa.
     *
     * @return descrição técnica da Tarefa
     */
    public String getDescrTecnica() {
        return descrTecnica;
    }

    /**
     * Modifica a descrição técnica da Tarefa. Verifica se o tamanho da
     * descrição técnica está entre 10 e 200 caracteres, caso contrário apresenta
     * mensagem de erro.
     *
     * @param descrTecnica descrição técnica da Tarefa
     */
    private void setDescrTecnica(String descrTecnica) {
        if (descrTecnica.length() > LIM_MIN_CARATERES) {
            if(descrTecnica.length() < 200 ){
            this.descrTecnica = descrTecnica;
            }else{
                throw new IllegalArgumentException("Descrição técnica deve ser menor do que 200 caracteres");
            }
        } else {
            throw new IllegalArgumentException("Descrição técnica deve ter mais de 10 caracteres");
        }
    }

    /**
     * Devolve a estimativa de duração da Tarefa.
     *
     * @return duração estimada da Tarefa
     */
    public Integer getDuracaoEst() {
        return duracaoEst;
    }

    /**
     * Modifica a duração estimada da Tarefa. Verifica se o tamanho da duração
     * estimada é menor que 2147483647 caracteres, caso contrário apresenta
     * mensagem de erro.
     *
     * @param duracaoEst duração estimada da Tarefa
     */
    private void setDuracaoEst(Integer duracaoEst) { // COLOCAR NA DB A VERIFICAÇÃO DO VALOR N PODER SER < 1
        if (duracaoEst < Integer.MAX_VALUE) {
            if(duracaoEst > 0) {                
                this.duracaoEst = duracaoEst;
            } else {
                throw new IllegalArgumentException("Valor de Duração Estimada tem de ser positivo");
            }
        } else {
            throw new IllegalArgumentException("Valor de Duração Estimada grande demais");
        }
    }

    /**
     * Devolve a estimativa de custo da Tarefa.
     *
     * @return custo estimado da Tarefa
     */
    public Double getCustoEst() {
        return custoEst;
    }

    /**
     * Modifica o custo estimado da Tarefa. Verifica se o tamanho do custo
     * estimado é válido, caso contrário apresenta mensagem de erro.
     *
     * @param custoEst custo estimado da Tarefa
     */
    private void setCustoEst(Double custoEst) { // COLOCAR NA DB ESTE CHECK DO CUSTO ESTIMADO SER POSITIVO
        if (custoEst < Double.MAX_VALUE) {
            if(custoEst > 0) {                
                this.custoEst = custoEst;
            } else {
                throw new IllegalArgumentException("Valor do Custo Estimado tem de ser positivo");
            }
        } else {
            throw new IllegalArgumentException("Valor de Custo grande de mais");
        }
    }

    /**
     * Devolve o objecto categoria de tarefa da Tarefa.
     *
     * @return objecto categoria de tarefa da Tarefa
     */
    public CategoriaTarefa getCatTarefa() {
        return catTarefa;
    }

    /**
     * Define a categoria de tarefa da Tarefa.
     * 
     * @param catTarefa nova categoria de tarefa da Tarefa
     */
    private void setCatTarefa(CategoriaTarefa catTarefa) {
        this.catTarefa = catTarefa;
    }

    /**
     * Devolve o colaborador que faz o registo da Tarefa.
     * 
     * @return colaborador que faz o registo da Tarefa
     */
    public Colaborador getColab() {
        return colab;
    }

    /**
     * Define o colaborador da Tarefa.
     * 
     * @param colab colaborador que faz o registo da Tarefa
     */
    private void setColab(Colaborador colab) {
        this.colab = colab;
    }

    // </editor-fold>
    
    /**
     * Compara a Tarefa com o objeto recebido.
     * 
     * @param o objeto a comparar com a Tarefa.
     * @return true se o objeto recebido representar uma Tarefa
     * equivalente à Tarefa. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Tarefa obj = (Tarefa) o;

        return this.ref.equalsIgnoreCase(obj.getRef())
                && this.getDesignacao().equalsIgnoreCase(obj.getDesignacao())
                && this.getDescrInformal().equalsIgnoreCase(obj.getDescrInformal())
                && this.getDescrTecnica().equalsIgnoreCase(obj.getDescrTecnica())
                && this.getDuracaoEst() == (obj.getDuracaoEst())
                && this.getCustoEst() == (obj.getCustoEst())
                && this.getCatTarefa().equals(obj.getCatTarefa())
                && this.getColab().equals(obj.getColab());
    }

    /**
     * Devolve a descrição textual da Tarefa no formato: 
     * Referência: \n Designação: \n Descrição Informal: \n 
     * Descrição Técnica: \n Duração Estimada: \n Custo Estimado: \n 
     * Categoria de Tarefa: \n Colaborador: ".
     *
     * @return caraterísticas da Tarefa
     */
    @Override
    public String toString() {
        return String.format("Referência: %s\n Designação: %s\n Descrição Informal: %s\n Descrição Técnica: %s\n Duração Estimada: %d\n Custo Estimado: %2f\n Categoria de Tarefa: %s\n Colaborador: %s",
                this.getRef(), this.getDesignacao(), this.getDescrInformal(), this.getDescrTecnica(), this.getDuracaoEst(), this.getCustoEst(), this.getCatTarefa().toString(), this.getColab().toString());
    }

}
