package pe.edu.uni.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import pe.edu.uni.app.dto.TransferenciaDto;

@Service
public class TransferenciaService {
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	public TransferenciaDto realizarTransferencia(TransferenciaDto dto, int proceso) {
		//Validar existencia de cuentas y que estén activas
		String sql = "select count(1) chr_cuencodigo from Cuenta where chr_cuencodigo = ?";
		int i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentaorigen());
		if(i == 0) {
			throw new RuntimeException("La cuenta origen no existe.");
		}
		
		sql = "select count(1) chr_cuencodigo from Cuenta where chr_cuencodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentadestino());
		if(i == 0) {
			throw new RuntimeException("La cuenta destino no existe.");
		}
		
		sql = "select case when vch_cuenestado = 'ACTIVO' then 1 else 0 end ";
		sql += "from cuenta where chr_cuencodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentaorigen());
		if(i == 0) {
			throw new RuntimeException("La cuenta origen no está activa.");
		}
		
		sql = "select case when vch_cuenestado = 'ACTIVO' then 1 else 0 end ";
		sql += "from cuenta where chr_cuencodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentadestino());
		if(i == 0) {
			throw new RuntimeException("La cuenta destino no está activa.");
		}
		
		//PARA EVITAR CONTINUAR CON LA TRANSACCION
		if(proceso < 2) {
			return dto;
		}
		
		//Validar que las cuentas sean de la misma moneda
		sql = "SELECT chr_monecodigo ";
		sql += "FROM Cuenta ";
		sql += "WHERE chr_cuencodigo IN (?, ?) ";
		sql += "GROUP BY chr_monecodigo ";
		sql += "HAVING COUNT(*) = 2;";
		try {
			jdbctemplate.queryForObject(sql, String.class, dto.getCodcuentaorigen(), dto.getCodcuentadestino());
		} catch (Exception e) {
			throw new RuntimeException("Las cuentas no poseen la misma moneda.");
		}
		
		if(proceso < 3) {
			return dto;
		}
		
		//Validar el importe positivo
		if(dto.getImporte()<=0) {
			throw new RuntimeException("El importe debe ser positivo.");
		}
		
		if(proceso < 4) {
			return dto;
		}
		
		//Validar que en la cuenta origen haya saldo suficiente
		sql = "select case when (dec_cuensaldo-?)>0 then 1 else 0 end from cuenta where chr_cuencodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getImporte(), dto.getCodcuentaorigen());
		if(i == 0) {
			throw new RuntimeException("El saldo en la cuenta origen es insuficiente.");
		}
		
		if(proceso < 5) {
			return dto;
		}
		
		//TRANSFERENCIA
		//Verificar empleado
		sql = "select count(1) chr_emplcodigo from Empleado where chr_emplcodigo = ?";
		i = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodempleado());
		if(i == 0) {
			throw new RuntimeException("El empleado no existe o ha sido dado de baja.");
		}
		
		//Actualizar saldo de la cuenta origen
		sql = "update Cuenta set dec_cuensaldo = dec_cuensaldo - ? where chr_cuencodigo = ?";
		jdbctemplate.update(sql, dto.getImporte(), dto.getCodcuentaorigen());
		
		//Registrar movimiento para la cuenta origen
			//Obtener la cantidad de movimientos de la cuenta origen
		sql = "select int_cuencontmov from cuenta where chr_cuencodigo = ?";
		int cantmovs = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentaorigen()) + 1;
			
			//INsertar el movimiento
		sql = "insert into Movimiento values(?,?,GETDATE(),?,'009', ?, ?)";
		jdbctemplate.update(sql,dto.getCodcuentaorigen(), cantmovs, dto.getCodempleado(), dto.getImporte(), dto.getCodcuentadestino());
		
			//Cambiar contador de movs en la cuenta
		sql = "update Cuenta set int_cuencontmov = ? where chr_cuencodigo = ?";
		jdbctemplate.update(sql,cantmovs,dto.getCodcuentaorigen());
		
		//Actualizar saldo de la cuenta destino
		sql = "update Cuenta set dec_cuensaldo = dec_cuensaldo + ? where chr_cuencodigo = ?";
		jdbctemplate.update(sql, dto.getImporte(), dto.getCodcuentadestino());
		
		//Registrar movimiento en la cuenta destino
			//Obtener la cantidad de movimientos de la cuenta destino
		sql = "select int_cuencontmov from cuenta where chr_cuencodigo = ?";
		cantmovs = jdbctemplate.queryForObject(sql, Integer.class, dto.getCodcuentadestino()) + 1;
					
			//INsertar el movimiento
		sql = "insert into Movimiento values(?,?,GETDATE(),?,'009', ?, ?)";
		jdbctemplate.update(sql,dto.getCodcuentadestino(), cantmovs, dto.getCodempleado(), dto.getImporte(), dto.getCodcuentaorigen());
		
			//Cambiar contador de movs en la cuenta
		sql = "update Cuenta set int_cuencontmov = ? where chr_cuencodigo = ?";
		jdbctemplate.update(sql,cantmovs,dto.getCodcuentadestino());
		
		return dto;
	}

}
