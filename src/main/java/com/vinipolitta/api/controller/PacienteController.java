package com.vinipolitta.api.controller;

import com.vinipolitta.api.domain.paciente.*;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPacienteDTO data, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente(data);

        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        var dto = new DetalhamentoPacienteDTO(paciente);

        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<Page<ListagemPacienteDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemPacienteDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhamentoPacienteDTO> listarDetalhepaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarPacienteDTO dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualziarInfos(dados);

        return ResponseEntity.ok(new DetalhamentoPacienteDTO(paciente));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity recuperar(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.reativar();

        return ResponseEntity.noContent().build();
    }
}
