package com.vinipolitta.api.paciente;

import com.vinipolitta.api.medico.Especialidade;
import com.vinipolitta.api.medico.Medico;

public record ListagemPacienteDTO(
        Long id,
        String nome,
        String email,
        boolean ativo

) {

    public ListagemPacienteDTO(Paciente medico) {
        this(medico.getId(),medico.getNome(), medico.getNome(),medico.isAtivo());
    }
}
