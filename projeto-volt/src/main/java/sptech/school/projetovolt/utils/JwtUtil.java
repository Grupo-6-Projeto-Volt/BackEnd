package sptech.school.projetovolt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET_KEY = "chave_urubu100";

    public static String generateToken(String email, String categoria) {
        return Jwts.builder()
                .setSubject(email)
                .claim("categoria", categoria)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 *
                        60 * 60 * 10)) // aqui dá 10 horas até expirar
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public static String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    // Recomendação do Copilot ao ver outras funções que montei
// semelhante a essa, então deixie a recomendação dele
    public static String extractCategoria(String token) {
        return extractClaims(token).get("categoria", String.class);
    }

    public static boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public static boolean validateToken(String token, String email) {
        return (extractEmail(token).equals(email) &&
                !isTokenExpired(token));
    }

}
