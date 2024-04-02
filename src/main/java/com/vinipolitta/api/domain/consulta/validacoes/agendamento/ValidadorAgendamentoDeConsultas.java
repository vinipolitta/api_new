package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsultas {

    void validar(AgendamentoConsultaDTO dados);
}
