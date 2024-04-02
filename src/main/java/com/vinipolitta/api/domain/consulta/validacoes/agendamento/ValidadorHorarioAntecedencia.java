package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")

public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsultas {

    public void validar(AgendamentoConsultaDTO dados) {
        var dataConsuta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsuta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta Deve ser agendada com antencedencia minima de 30 minutos");
        }

    }
}
