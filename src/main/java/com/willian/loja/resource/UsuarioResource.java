package com.willian.loja.resource;

import com.willian.loja.DTO.CategoriaDTO;
import com.willian.loja.DTO.UsuarioDTO;
import com.willian.loja.DTO.UsuarioNewDto;
import com.willian.loja.domain.Categoria;
import com.willian.loja.domain.Usuario;
import com.willian.loja.domain.Usuario;
import com.willian.loja.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> buscaPorId(@PathVariable Integer id){
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateUsuario(@Valid @RequestBody UsuarioDTO objDTO, @PathVariable Integer id){
        Usuario obj = service.fromDTO(objDTO);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<Usuario> objs = service.findAll();
        List<UsuarioDTO> listaDTO = objs.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    public ResponseEntity<Page<UsuarioDTO>> findPage(
            @RequestParam(value="page", defaultValue = "0") Integer page,
            @RequestParam(value="linesOfPage", defaultValue = "25") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value="direction", defaultValue = "ASC") String direction){
        Page<Usuario> objs = service.findPage(page,linesPerPage,orderBy,direction);
        Page<UsuarioDTO> listaDTO = objs.map(obj -> new UsuarioDTO(obj));
        return ResponseEntity.ok().body(listaDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertNewCategory(@Valid @RequestBody UsuarioNewDto objDTO){
        Usuario obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}