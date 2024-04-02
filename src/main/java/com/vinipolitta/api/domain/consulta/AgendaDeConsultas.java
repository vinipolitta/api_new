package com.vinipolitta.api.domain.consulta;

import com.vinipolitta.api.domain.ValidacaoException;
import com.vinipolitta.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsultas;
import com.vinipolitta.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import com.vinipolitta.api.domain.medico.Medico;
import com.vinipolitta.api.domain.medico.MedicoRepository;
import com.vinipolitta.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DetalhamentoConsultaDTO agendar(AgendamentoConsultaDTO dados) {

        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado nao exite");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do medico informado nao exite");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if (medico  ==null ){
            throw new ValidacaoException("Nao existe medico disponivel");
        }

        var paciente = pacienteRepository.findById(dados.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, dados.data(), null);


        consultaRepository.save(consulta);
        return new DetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(AgendamentoConsultaDTO dados) {
        if (dados.idMedico() != null){
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade e  obrigatoria quando o medico  nao for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public void cancelar(CancelamentoConsultaDTO dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }
}
