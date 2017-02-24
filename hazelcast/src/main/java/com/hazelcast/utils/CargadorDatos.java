package com.hazelcast.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.vo.UserVO;

public class CargadorDatos {
	private String rutaFichero = "datos.csv";
	private HazelcastInstance instance = Hazelcast.newHazelcastInstance(new Config());
	
	public HazelcastInstance getInstance() {
		return instance;
	}

	public IMap<Integer, UserVO> cargarCsv() throws IOException{
		IMap<Integer, UserVO> mapUsers = instance.getMap("users");
		InputStreamReader input = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(rutaFichero));
		BufferedReader buffer = new BufferedReader(input);
		String linea = buffer.readLine();
		int contador = 1;
		while ((linea = buffer.readLine()) != null) {
			
			String[] campos = linea.split(";");
			String name = campos[0];
			String email = campos[1];
			String phone = campos[2];
			String surname = campos[3];
			
			UserVO user = new UserVO(name, email, phone, surname);
			mapUsers.put(contador++, user);
		}
		buffer.close();
		input.close();	
		return mapUsers;
	}
	
	public static void main(String[] args){
		try {
			CargadorDatos carga = new CargadorDatos();
			IMap<Integer, UserVO> mapUsers = carga.cargarCsv();
			
			System.out.println("numero de elementos: " + mapUsers.size());
			carga.getInstance().shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
