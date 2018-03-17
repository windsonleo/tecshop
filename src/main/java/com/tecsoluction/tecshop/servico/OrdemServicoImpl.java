package com.tecsoluction.tecshop.servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.tecshop.dao.IOrdemDAO;
import com.tecsoluction.tecshop.dao.IRoleDAO;
import com.tecsoluction.tecshop.entidade.Ordem;
import com.tecsoluction.tecshop.entidade.Role;
import com.tecsoluction.tecshop.framework.AbstractEntityService;

import java.util.UUID;


@Service("ordemService")
@Transactional
public class OrdemServicoImpl extends AbstractEntityService<Ordem> {


    @Autowired
    private
    IOrdemDAO dao;

//	private Entity entityClass;


    public OrdemServicoImpl() {
        super(Ordem.class, "ordem");
    }

@Override
protected JpaRepository<Ordem, UUID> getDao() {
	// TODO Auto-generated method stub
	return dao;
}

@Override
protected void validateSave(Ordem post) {
	// TODO Auto-generated method stub
	
}

@Override
protected String getIdEntity(Ordem entity) {
	// TODO Auto-generated method stub
	return null;
}

@Override
protected void validateEdit(Ordem post) {
	// TODO Auto-generated method stub
	
}

@Override
protected void validateDelete(UUID id) {
	// TODO Auto-generated method stub
	
}

  

}
