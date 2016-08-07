package dyploma.auction.system.carriage.goods.mvc.administration.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import dyploma.auction.system.carriage.goods.mvc.administration.model.TypeOfBody;

public interface AdministrationDAOInterface {

	public List<TypeOfBody> getTypeOfBodies() throws DataAccessException; 
}
