package com.tecsoluction.tecshop.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.tecshop.dao.IEnderecoDAO;
import com.tecsoluction.tecshop.entidade.Endereco;
import com.tecsoluction.tecshop.framework.AbstractEntityService;

import java.util.UUID;

/*  criar validacaoes para que o servico as chamem caso nao haja erros execute a acao  */


@Service("enderecoService")
@Transactional
public class EnderecoServicoImpl extends AbstractEntityService<Endereco> {

    @Autowired
    private IEnderecoDAO dao;


    public EnderecoServicoImpl() {

        super(Endereco.class, "endereco");

    }

    @Override
    protected JpaRepository<Endereco, UUID> getDao() {

        return dao;
    }

	@Override
	protected void validateSave(Endereco post) {
		// TODO Auto-generated method stub
		
	}

    @Override
    protected String getIdEntity(Endereco endereco) {
        return endereco.getId().toString();
    }

    @Override
	protected void validateEdit(Endereco post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}


}
