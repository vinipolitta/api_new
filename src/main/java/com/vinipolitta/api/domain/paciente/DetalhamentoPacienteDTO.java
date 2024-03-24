package com.vinipolitta.api.domain.paciente;

import com.vinipolitta.api.domain.endereco.Endereco;

public record DetalhamentoPacienteDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public DetalhamentoPacienteDTO(Paciente medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCpf(), medico.getTelefone(),medico.getEndereco());
    }
}