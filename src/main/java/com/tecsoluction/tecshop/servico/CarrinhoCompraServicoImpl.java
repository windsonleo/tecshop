package com.tecsoluction.tecshop.servico;


import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tecsoluction.tecshop.dao.ICarrinhoCompraDAO;
import com.tecsoluction.tecshop.entidade.CarrinhoCompra;
import com.tecsoluction.tecshop.framework.AbstractEntityService;




@Service("carrinhoService")
@Transactional
public class CarrinhoCompraServicoImpl extends AbstractEntityService<CarrinhoCompra>  {

	
    @Autowired
    private ICarrinhoCompraDAO dao;
	
	
	public CarrinhoCompraServicoImpl() {
		   super(CarrinhoCompra.class, "carrinhocompra");
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected JpaRepository<CarrinhoCompra, UUID> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	protected void validateSave(CarrinhoCompra post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getIdEntity(CarrinhoCompra entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void validateEdit(CarrinhoCompra post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void validateDelete(UUID id) {
		// TODO Auto-generated method stub
		
	}

	
	



}
