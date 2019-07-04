package il.co.sela.security;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Service;

import il.co.sela.dto.LoginInDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	@Autowired
	UDServiceImpl udService;
	@Value("${privateKey}")
	String pKey;
	
	@ManagedAttribute
	public String getPrivateKey() {
		return pKey;
	}
	
	@ManagedAttribute
	public void setPrivateKey(String pKey) {
		this.pKey = pKey;
	}
	
	public String createToken(LoginInDto loginDto) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 7);
		Date nowDate = new Date();
		Date expDate = calendar.getTime();
		Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(pKey), signatureAlgorithm.getJcaName());
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("email", loginDto.getEmail());
		
		return Jwts.builder()
					.setClaims(claims)
					.setNotBefore(nowDate)
					.setExpiration(expDate)
					.signWith(signingKey, signatureAlgorithm)
					.compact();
	}
}