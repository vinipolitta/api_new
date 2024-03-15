package com.vinipolitta.api.controller;

import com.vinipolitta.api.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroMedicoDTO data, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(data);

        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        var dto = new DetalhamentoMedicoDTO(medico);

        return ResponseEntity.created(uri).body(dto);

    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicoDTO>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(ListagemMedicoDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<DetalhamentoMedicoDTO> listarDetalheMedico(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));
    }

    @PutMapping()
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarMedicoDTO dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualziarInfos(dados);

        return ResponseEntity.ok(new DetalhamentoMedicoDTO(medico));


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity recuperar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.reativar();

        return ResponseEntity.noContent().build();
    }
}
