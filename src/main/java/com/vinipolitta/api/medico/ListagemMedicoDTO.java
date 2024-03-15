package com.vinipolitta.api.medico;

import com.vinipolitta.api.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record ListagemMedicoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        boolean ativo,
        Especialidade especialidade

) {

    public ListagemMedicoDTO(Medico medico) {
        this(medico.getId(),medico.getNome(), medico.getNome(), medico.getCrm(),medico.isAtivo(), medico.getEspecialidade());
    }
}
