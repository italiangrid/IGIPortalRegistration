package portal.registration.dao;

import java.util.List;

import portal.registration.domain.Vo;

public interface VoDAO {

	public List<Vo> getAllVo();

	public Vo findById(Integer id);

	public List<String> getAllDiscipline();

	public List<Vo> getAllVoByName(String search);

}
