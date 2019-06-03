package hello.dao;

import java.util.List;

import hello.model.PreguntaRespuesta;

public interface PreguntaldosDAO {
	public Integer insertPreguntaRespuesta(PreguntaRespuesta pr);
	public Boolean deletePreguntaRespuesta(Integer id);
	public Boolean updatePreguntaRespuesta(Integer id,PreguntaRespuesta pr);
	public PreguntaRespuesta getPreguntaRespuesta(Integer id);
	public List<PreguntaRespuesta> getAll();
	public Boolean guardarEnSQL();
}
