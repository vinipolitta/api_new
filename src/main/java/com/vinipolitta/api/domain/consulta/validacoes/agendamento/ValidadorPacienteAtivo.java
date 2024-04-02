package com.vinipolitta.api.domain.consulta.validacoes.agendamento;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import com.vinipolitta.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public void validar(AgendamentoConsultaDTO  dados) {
        if (dados.idMedico() == null) {
            return;
        }
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());

        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta nao pode ser agendada com paciente inativo");
        }
    }
}
