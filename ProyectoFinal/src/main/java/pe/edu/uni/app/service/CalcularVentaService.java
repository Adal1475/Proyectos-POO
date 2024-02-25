package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.CalcularDescuentoDto;
import pe.edu.uni.app.dto.CalcularVentaDto;
import pe.edu.uni.app.dto.ValidarIDPubDto;

@Service
public class CalcularVentaService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public CalcularVentaDto calcularVenta(CalcularVentaDto dto) {
		System.out.print(""+dto.getIdpublicacion() + dto.getCantventa());
		ValidarIDPubDto dtov = new ValidarIDPubDto(dto.getIdpublicacion());
		dtov.setIdpublicacion(dto.getIdpublicacion());
		ValidarIDPubService validarIDPubService = new ValidarIDPubService();
		//Validar si existe el id de publicacion
		validarIDPubService.valIDPub(dtov);
		
		CalcularDescuentoDto dtod = new CalcularDescuentoDto(0,dto.getCantventa());
		dtod.setCantventa(dto.getCantventa());
		CalcularDescuentoService calcdescservice = new CalcularDescuentoService();
		dtod = calcdescservice.calcularDescuento(dtod);
		dto.setDescuento(dtod.getDescuento());
		
		String sql = "select valor from CONTROL ";
			sql += "where parametro = 'IGV'";
			float igv = jdbcTemplate.queryForObject(sql,Float.class);
			
		sql = "select precio from PUBLICACION ";
		sql += "where idpublicacion = ?";
		float precioventa = jdbcTemplate.queryForObject(sql,Float.class, dto.getIdpublicacion());
		dto.setPrecioventa(precioventa);
		
		float subtotal = (float) ((dto.getPrecioventa()*dto.getCantventa())*(1-dto.getDescuento())*(1-igv));
		float total = (dto.getPrecioventa()*dto.getCantventa())*(1-dto.getDescuento());
		float impuesto = total - subtotal;
		dto.setImpuesto(impuesto);
		dto.setSubtotal(subtotal);
		dto.setTotal(total);
		
		return dto;
	}
}
