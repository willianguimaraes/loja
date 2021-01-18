package com.willian.loja.service;

import com.willian.loja.DTO.UsuarioDTO;
import com.willian.loja.DTO.UsuarioNewDto;
import com.willian.loja.Repository.UsuarioRepository;
import com.willian.loja.domain.Categoria;
import com.willian.loja.domain.Endereco;
import com.willian.loja.domain.Usuario;
import com.willian.loja.enums.TipoCliente;
import com.willian.loja.exception.DataIntegrityException;
import com.willian.loja.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario findById(Integer id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " + Usuario.class.getName()));
    }

    public Usuario insert(Usuario obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Usuario update(Usuario obj){
        Usuario usuario = findById(obj.getId());
        updateData(usuario, obj);
        return repository.save(usuario);
    }

    public void delete(Integer id){
        findById(id);
        try{
            repository.deleteById(id);

        }catch (DataIntegrityViolationException ex){
            throw new DataIntegrityException("Não e possivel excluir usuarios com outros atributos");
        }
    }

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);

        return repository.findAll(pageRequest);
    }

    public Usuario fromDTO(UsuarioDTO objDTO){
        return Usuario.builder()
                .id(objDTO.getId())
                .nome(objDTO.getNome())
                .email(objDTO.getEmail())
                .build();
//        throw new UnsupportedOperationException("nao Implementado");
    }

    public Usuario fromDTO(UsuarioNewDto objDTO){
         Usuario user = Usuario.builder()
                .nome(objDTO.getNome())
                .email(objDTO.getEmail())
                .cpfCnpj(objDTO.getCpfCnpj())
                .tipoCliente(TipoCliente.toEnum(objDTO.getTipoCliente()))
                .build();
        Endereco.builder()
                .logradouro(objDTO.getLogradouro())
                .cep(objDTO.getCep())
                .numero(objDTO.getNumero())
                .complemento(objDTO.getComplemento())
                .bairro(objDTO.getBairro())
                .usuario(user)
                .cidade(objDTO.getCidade().getId())
                .build();
//        throw new UnsupportedOperationException("nao Implementado");
    }

    public Usuario updateData(Usuario obj, Usuario objDto){
        obj.setNome(objDto.getNome());
        obj.setEmail(objDto.getEmail());
        return obj;
    }
}