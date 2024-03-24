package com.vinipolitta.api.domain.paciente;

import com.vinipolitta.api.domain.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record AtualizarPacienteDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        EnderecoDTO endereco
) {
}
