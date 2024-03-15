package com.vinipolitta.api.medico;

import com.vinipolitta.api.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        String email,
        EnderecoDTO endereco
) {
}
