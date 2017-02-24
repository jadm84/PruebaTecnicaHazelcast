package com.hazelcast.bean;

import java.io.IOException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hazelcast.core.IMap;
import com.hazelcast.query.SqlPredicate;
import com.hazelcast.utils.CargadorDatos;
import com.hazelcast.vo.UserVO;

@ManagedBean(name = "homeBean")
@SessionScoped
public class HomeBean {

	private Collection<UserVO> users;
	private IMap<Integer, UserVO> mapUsers;
	private String inputName;
	private String inputPhone;
	private String inputEmail;
	private String inputSurname;
	private Boolean encontrado;

	@PostConstruct
	private void init() {

		CargadorDatos cargador = new CargadorDatos();
		try {
			setMapUsers(cargador.cargarCsv());
			users = mapUsers.values();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ejecutaFiltro() {
		
		String condicion = "";

		if (!inputName.isEmpty()) {
			condicion = "name like '%" + inputName + "%'";
		}

		if (!inputEmail.isEmpty()) {
			if (!condicion.isEmpty()) {
				condicion += " and ";
			}
			condicion += "email like '%" + inputEmail + "%'";
		}

		if (!inputPhone.isEmpty()) {
			if (!condicion.isEmpty()) {
				condicion += " and ";
			}
			condicion += "phone like '%" + inputPhone + "%'";
		}

		if (!inputSurname.isEmpty()) {
			if (!condicion.isEmpty()) {
				condicion += " and ";
			}
			condicion += "surname like '%" + inputSurname + "%'";
		}

		if (!condicion.isEmpty()) {
			users = mapUsers.values(new SqlPredicate(condicion));
		} else {
			users = mapUsers.values();
		}

	}

	public void registrar() {
		UserVO user = new UserVO(inputName, inputEmail, inputPhone, inputSurname);
		mapUsers.put(mapUsers.size()+1, user);
		ejecutaFiltro();
	}

	public Boolean getHayUsuarios(){
		return !users.isEmpty();
	}
	
	public Collection<UserVO> getUsers() {
		return users;
	}

	public void setUsers(Collection<UserVO> users) {
		this.users = users;
	}

	public IMap<Integer, UserVO> getMapUsers() {
		return mapUsers;
	}

	public void setMapUsers(IMap<Integer, UserVO> mapUsers) {
		this.mapUsers = mapUsers;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getInputPhone() {
		return inputPhone;
	}

	public void setInputPhone(String inputPhone) {
		this.inputPhone = inputPhone;
	}

	public String getInputEmail() {
		return inputEmail;
	}

	public void setInputEmail(String inputEmail) {
		this.inputEmail = inputEmail;
	}

	public String getInputSurname() {
		return inputSurname;
	}

	public void setInputSurname(String inputSurname) {
		this.inputSurname = inputSurname;
	}
}
