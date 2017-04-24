package ecc.cords;

import java.util.List;

public class DaoService{

	private Dao dao = new Dao();

	public <T> List<T> getAllElements(final Class<T> type){
		return dao.getAll(type);
	}

	public <T> T getElement(T t){
      	return dao.get(t);
    }

    public <T> T getElement(final long id, final Class<T> type){
      	return dao.get(id,type);
    }

	public <T> void saveElement(T t) {
		dao.save(t);
	}
}