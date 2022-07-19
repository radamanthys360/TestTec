package com.prueba.tec.conf;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//es responsable de realizar operaciones JWT como creación y validación. Hace uso de io.jsonwebtoken.Jwts
@Component
public class JwtTokenUtil implements Serializable {
	
    @Autowired
    Environment env;

	private static final long serialVersionUID = -2550185165626007488L;

//	public static final long JWT_TOKEN_VALIDITY = 50000;// 50 segundos
	public static final long JWT_TOKEN_VALIDITY = 300000; // 5 minutos


	
	private static final String JWT_SECRET = "2dae84f846e4f4b158a8d26681707f4338495bc7ab68151d7f7679cc5e56202dd3da0d356da007a7c28cb0b780418f4f3246769972d6feaa8f610c7d1e7ecf6a"
			+ "";

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Jws<Claims> claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims.getBody());
	}

	private Jws<Claims> getAllClaimsFromToken(String token) {

		String jwtToken = token.substring("Bearer ".length());
		return Jwts.parserBuilder().setSigningKey(JWT_SECRET.getBytes(Charset.forName("UTF-8"))).build().parseClaimsJws(jwtToken);

	}

	//verifica si el token ya expiro si el token es de antes de la fecha de expiracion 
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// por si necesitamos ignorar la expiracion de un token en especifico
		return false;
	}

	//genera el token 
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	//genera el token y se le asigna un tiempo de  expiracion
	@SuppressWarnings("deprecation")
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(SignatureAlgorithm.HS512, JWT_SECRET.getBytes(Charset.forName("UTF-8"))).compact();
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	//valida si el token es valido para su uso
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
}
