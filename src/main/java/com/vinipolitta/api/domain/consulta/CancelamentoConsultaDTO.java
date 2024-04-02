package com.vinipolitta.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record CancelamentoConsultaDTO(

        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
