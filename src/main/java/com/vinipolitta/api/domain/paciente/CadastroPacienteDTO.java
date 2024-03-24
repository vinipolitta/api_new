package com.vinipolitta.api.domain.paciente;

import com.vinipolitta.api.domain.endereco.EnderecoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroPacienteDTO(

        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String cpf,
        @NotNull @Valid EnderecoDTO endereco) {
}
