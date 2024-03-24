package com.vinipolitta.api.domain.medico;

import com.vinipolitta.api.domain.endereco.EnderecoDTO;
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
