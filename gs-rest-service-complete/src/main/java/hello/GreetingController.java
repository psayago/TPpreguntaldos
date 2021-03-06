package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import hello.model.PreguntaRespuesta;
import hello.service.PreguntaldosServiceImpl;

@Controller
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private PreguntaldosServiceImpl service;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/getPregunta")
    public String pregunta(@RequestParam(value="numero") Integer numero) {
        return service.getPregunta(numero);
    }
    
    @RequestMapping("/getRespuesta")
    public String respuesta(@RequestParam(value="numero") Integer numero) {
        return service.getRespuesta(numero);
    }
    
    @RequestMapping("/getPreguntaRespuesta")
    public PreguntaRespuesta preguntaRespuesta(@RequestParam(value="numero") Integer numero) {
        return service.getPreguntaRespuesta(numero);
    }
    
    
    @RequestMapping(value="/updatePreguntaRespuesta", method=RequestMethod.PUT)
    public @ResponseBody Boolean actualizarPreguntaRespuesta(@RequestBody PreguntaRespuesta pr){
    	return service.updatePreguntaRespuesta(pr.getId(), pr);
    }
    
    @RequestMapping(value="/deletePreguntaRespuesta", method=RequestMethod.DELETE)
    public Boolean borrarPreguntaRespuesta(@RequestParam(value="numero") Integer id){
    	return service.removePreguntaRespuesta(id);
    }
    
    @RequestMapping(value="/insertPreguntaRespuesta", method=RequestMethod.POST)
    public @ResponseBody Boolean agregarPreguntaRespuesta(@RequestBody PreguntaRespuesta pr){
    	return service.addPreguntaRespuesta(pr);
    }
    
    @GetMapping(value = "/getAllPreguntaRespuesta")
	public String getAllPreguntas() {
		Gson respuestas = new Gson();      
		return respuestas.toJson(service.getAll()).toString();
	}
    
    @GetMapping(value = "/guardarPreguntaRespuestaEnBD")
	public Boolean guardarPreguntaRespuestaSQL() {      
		return service.guardarEnSQL();
	}
    
    @RequestMapping("/chequearPregunta")
    public Boolean chequearRespuesta(@RequestParam(value="numero") Integer numero, @RequestParam(value="respuesta") String respuesta ) {
    	return service.getRespuesta(numero).equals(respuesta);
    }
    
    

    //TODO viernes:
    // Procesar archivo de preguntas para guardar en la base de datos
    // Implementar un set de preguntas en memoria
    // Vincular las preguntas en memoria con el codigo del DAO
    // Extra point: integrar DAO con repository usando MySQL
    // https://spring.io/guides/gs/accessing-data-mysql/
    
    
    
}
