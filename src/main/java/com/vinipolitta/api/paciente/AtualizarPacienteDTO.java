package com.vinipolitta.api.paciente;

import com.vinipolitta.api.endereco.EnderecoDTO;
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
