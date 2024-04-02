package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import com.vinipolitta.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(AgendamentoConsultaDTO  dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com medico inativo");
        }
    }
}
