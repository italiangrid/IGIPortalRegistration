package portal.registration.services;

import java.util.List;

import portal.registration.domain.Vo;

public interface VoService {

	public List<Vo> getAllVo();

	public Vo findById(Integer id);

	public String findByVo(Vo vo);

	public List<String> getAllDiscplines();

	public List<Vo> getAllVoByName(String search);

}
