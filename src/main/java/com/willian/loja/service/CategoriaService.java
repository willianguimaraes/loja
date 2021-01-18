package com.willian.loja.service;

import com.willian.loja.DTO.CategoriaDTO;
import com.willian.loja.Repository.CategoriaRepository;
import com.willian.loja.domain.Categoria;
import com.willian.loja.domain.Categoria;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria findById(Integer id) {
        Optional<Categoria> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Nao Encontrado id "+ id +", do tipo " +Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public Categoria update(Categoria obj){
        Categoria newObj = findById(obj.getId());
        updateData(newObj,obj);
        return repository.save(newObj);
    }

    public void delete(Integer id){
        findById(id);
        try{
            repository.deleteById(id);

        }catch (DataIntegrityViolationException ex){
        throw new DataIntegrityException("Não e possivel excluir categorias com produtos");
        }
    }

    public List<Categoria> findAll(){
        return repository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page,linesPerPage, Sort.Direction.valueOf(direction),orderBy);

        return repository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO objDTO){
        return new Categoria(objDTO.getId(),objDTO.getNome());
    }

    public Categoria updateData(Categoria obj, Categoria objDto){
        obj.setNome(objDto.getNome());
        return obj;
    }

}
