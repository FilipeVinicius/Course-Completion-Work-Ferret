package tcc.itb.FerretSystem.control;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import tcc.itb.FerretSystem.model.Projeto;
import tcc.itb.FerretSystem.service.UsuarioService;

public class ProjetoController {
	
	
	private static String Caminhoimg = "*{foto_projeto}";

	public static String getCaminhoimg() {
		return Caminhoimg;
	}

	public static void setCaminhoimg(String caminhoimg) {
		Caminhoimg = caminhoimg;
	}
	
	
	
	
	//@GetMapping("/image/projeto/{id}")
//	@ResponseBody
//	void showImage(@PathVariable("id") Long id, HttpServletResponse response)
//			throws ServletException, IOException {
//		
//		Projeto.setFoto_projeto(Projeto.getFoto_projeto());
//		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		response.getOutputStream().write(imageGallery.get().getImage());
//		response.getOutputStream().close();
//	}

}
