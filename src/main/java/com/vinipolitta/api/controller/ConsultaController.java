package com.vinipolitta.api.controller;

import com.vinipolitta.api.domain.consulta.AgendaDeConsultas;
import com.vinipolitta.api.domain.consulta.AgendamentoConsultaDTO;
import com.vinipolitta.api.domain.consulta.CancelamentoConsultaDTO;

import com.vinipolitta.api.domain.consulta.DetalhamentoConsultaDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas  agendaDeConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoConsultaDTO dados) {
        var dto = agendaDeConsultas.agendar(dados);
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsultaDTO dados) {
        agendaDeConsultas.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


}