package hello.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import hello.model.PreguntaRespuesta;

@Repository
public class PreguntaldosMemoryDAO implements PreguntaldosDAO {

	private HashMap<Integer, PreguntaRespuesta> preguntas = new HashMap<>();
	private AtomicLong counter = new AtomicLong(0);
		

	@Override
	public Integer insertPreguntaRespuesta(PreguntaRespuesta pr) {
		Integer id = (int) counter.getAndIncrement();
		pr.setId(id);
		preguntas.put(id, pr);
		return id;
	}

	@Override
	public Boolean deletePreguntaRespuesta(Integer id) {
		if (preguntas.containsKey(id)) {
			preguntas.remove(id);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean updatePreguntaRespuesta(Integer id, PreguntaRespuesta pr) {
		if (preguntas.containsKey(id)) {
			preguntas.put(id, pr);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public PreguntaRespuesta getPreguntaRespuesta(Integer id) {
        	return preguntas.get(id);			
	}

	@Override
	public List<PreguntaRespuesta> getAll() {
		List<PreguntaRespuesta> lista = new ArrayList<PreguntaRespuesta>();
		Iterator<Integer> it = preguntas.keySet().iterator();
		while (it.hasNext()) {
			Integer key = it.next();
			lista.add(preguntas.get(key));

		}

		return lista;
	}

	public Boolean guardarEnSQL() {
		try {
			Iterator<Integer> it = preguntas.keySet().iterator();
			while (it.hasNext()) {
				ConnectionMySQL pdaoDao = ConnectionMySQL.createInstance();
				Integer key = it.next();
				if (key!=0)
				pdaoDao.guardoPregunta(preguntas.get(key));				
			}
			return Boolean.TRUE;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Boolean.FALSE;
		}
	}

		
}
