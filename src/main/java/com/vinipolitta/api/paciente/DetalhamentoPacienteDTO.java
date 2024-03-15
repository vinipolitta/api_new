package com.vinipolitta.api.paciente;

import com.vinipolitta.api.endereco.Endereco;
import com.vinipolitta.api.medico.Especialidade;
import com.vinipolitta.api.medico.Medico;

public record DetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPacienteDTO(Paciente medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCpf(), medico.getTelefone(),medico.getEndereco());
    }
}