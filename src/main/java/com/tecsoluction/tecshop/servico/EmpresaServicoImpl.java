package com.tecsoluction.tecshop.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tecsoluction.tecshop.dao.IEmpresaDAO;
import com.tecsoluction.tecshop.entidade.Empresa;
import com.tecsoluction.tecshop.framework.AbstractEntityService;

import java.util.UUID;

/*  criar validacaoes para que o servico as chamem caso nao haja erros execute a acao  */


@Service("empresaService")
@Transactional
public class EmpresaServicoImpl extends AbstractEntityService<Empresa> {

    @Autowired
    private IEmpresaDAO dao;


    public EmpresaServicoImpl() {

        super(Empresa.class, "empresa");

    }

    @Override
    protected JpaRepository<Empresa, UUID> getDao() {

        return dao;
    }

    @Override
    protected void validateSave(Empresa post) {
        // TODO Auto-generated method stub

    }

    @Override
    protected String getIdEntity(Empresa empresa) {
        return empresa.getId().toString();
    }

    @Override
    protected void validateEdit(Empresa post) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void validateDelete(UUID id) {
        // TODO Auto-generated method stub

    }


}
