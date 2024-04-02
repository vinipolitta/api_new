package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas {
    public void validar(AgendamentoConsultaDTO dados) {
        var dataConsuta = dados.data();
        var domingo = dataConsuta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsuta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsuta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica");
        }
    }
}
