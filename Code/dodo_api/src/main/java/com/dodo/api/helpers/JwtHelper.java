package com.dodo.api.helpers;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dodo.api.dtos.UserDto;
import com.dodo.api.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class JwtHelper implements Serializable {
	//this is just a 256bit encrypt string you can find anywhere, go to gg and generate one, it doesnt have to be any specific string, just 256bit encrypt is enough
	@Value("${jwt.authenticate.secretKey}")
	private String secretKey;
	
	@Value("${jwt.authenticate.jwtExpiryTime}")
	private long jwtExpiryTime;
	
	@Value("${jwt.authenticate.refreshTokenExpiryTime}")
	private long refreshTokenExpiryTime;
	
	private Key getSignKey() {
		byte[] keyByte = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyByte);
	}
	
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
        		.parserBuilder()
        		.setSigningKey(getSignKey())
        		.build()
        		.parseClaimsJws(token)
        		.getBody();
    }
	
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
	
	public String getUsernameFromToken(String token) {
        try {
        	return getClaimFromToken(token, Claims::getSubject);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public String getUsernameFromExpiredToken(String token) {
        try {
        	return getClaimFromToken(token, Claims::getSubject);
		} catch (ExpiredJwtException e) {
			e.printStackTrace();
			return e.getClaims().getSubject();
		}
    }
	

    public Date getExpirationDateFromToken(String token) {     
        try {
        	return getClaimFromToken(token, Claims::getExpiration);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
    }
    
    public Boolean isTokenValid(String token, UserDetails user) {
    	try {
            final String username = getUsernameFromToken(token);    
            return (username.equals(user.getUsername()) && !isTokenExpired(token));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    public String generateToken(User user) {
    	return generateToken(new HashMap<>(), user, jwtExpiryTime);
    }
    
    public String generateToken(Map<String, Object> extraClaims, User user, long expiryTime) {
    	return Jwts.builder()
    			.setClaims(extraClaims)
    			.setSubject(user.getUsername())
    			.setIssuedAt(new Date(System.currentTimeMillis()))
    			.setExpiration(new Date(System.currentTimeMillis() + expiryTime))
    			.signWith(getSignKey(), SignatureAlgorithm.HS256)
    			.compact();			
    }
    
    public String generateRefreshToken(User user) {
    	return generateToken(new HashMap<>(), user, refreshTokenExpiryTime);		
    }
}
