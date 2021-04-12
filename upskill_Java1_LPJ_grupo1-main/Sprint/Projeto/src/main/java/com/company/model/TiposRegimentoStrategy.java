/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface com métodos da estratégia dos Tipos de Regimento
 * @author joaor
 */
public interface TiposRegimentoStrategy {

    public void RegistaProcessoSeriacao(Anuncio anuncio, List<Colaborador> participantes, ArrayList<Classificacao> listaClass) throws Exception;

    public void RegistaAtribuicao(Classificacao classif) throws Exception;

}
