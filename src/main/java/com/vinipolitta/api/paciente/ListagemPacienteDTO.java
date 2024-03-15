package com.vinipolitta.api.paciente;

public record ListagemPacienteDTO(
        Long id,
        String nome,
        String email,
        boolean ativo

) {

    public ListagemPacienteDTO(Paciente paciente) {
        this(paciente.getId(),paciente.getNome(), paciente.getEmail(),paciente.isAtivo());
    }
}
