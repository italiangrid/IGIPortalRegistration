package portal.registration.dao;

import java.util.List;

import portal.registration.domain.Idp;

public interface IdpDAO {

	public void save(Idp transientInstance);

	public void delete(Idp persistentInstance);

	public Idp findById(Integer id);

	public List<Idp> getAllIdp();
}
