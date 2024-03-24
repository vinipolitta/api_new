package com.vinipolitta.api.domain.medico;

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
