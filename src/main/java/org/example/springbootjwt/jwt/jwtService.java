package org.example.springbootjwt.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class jwtService {
    public  static  final String SECRET_KEY="zRD16vKfwHFMvxwRDClm2eydgq0lGzprCCoAb3C5Tqg=";

//Bu metod giriş yapan kullanıcı için JWT üretir. (jwt icine kullancıı ismi , tarihi , hangi algoritmayla yapıldıgı yazılır )

    public  String generateToken(UserDetails userDetails){

       return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();


    }

    //Bu metod token içindeki bilgileri okumak için kullanılıyor.
   public  <T> T exportToken(String token, Function<Claims,T> claimsFunction){
      Claims claims=  Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claimsFunction.apply(claims);
   }
   //JWT'den username çekmek.
   public  String geyUsernameByToken(String token){
       return exportToken(token,Claims::getSubject);
   }

   //Token'ın süresi geçmiş mi kontrol ediyor.
   public boolean isTokenExpired(String token){
        Date expiredDate=exportToken(token,Claims::getExpiration);
          return  new Date().before(expiredDate);
    }

    public Key getKey(){
   byte[] keyBeyts=     Decoders.BASE64.decode(SECRET_KEY);
 return Keys.hmacShaKeyFor(keyBeyts);
    }

}
