package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.ConsultaRepository;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraCOnsultaNoMesmoHorario implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(AgendamentoConsultaDTO dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse mesmo horario");
        }
    }
}
