package br.devnoite.projetodrog.guiadrog.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashUtil {
	
	//método que cria um hash das senhas do BD
	public static String hash(String palavra) {
		//criando uma palavra pra incrementar junto a senha(tempero)
		String salt = "b@n@n@";
		// adcionando o "tempero" à palavra
		palavra = salt + palavra;
		//gera o hash
		String hash = Hashing.sha256().hashString(palavra, StandardCharsets.UTF_8).toString();
		//retorna o hash
		return hash;
	}
}
