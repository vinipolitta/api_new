package com.vinipolitta.api.domain.consulta.validacoes.cancelamento;

import com.vinipolitta.api.domain.consulta.CancelamentoConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {

    void validar(CancelamentoConsultaDTO dados);

}