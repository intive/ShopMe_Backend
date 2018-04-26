package com.intive.shopme.tokens;

import com.intive.shopme.tokens.authentication.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.impl.TextCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class JwtParser {

    private final MessageSource messageSource;
    private final String secret;
    private Claims claimsBody;

    @Autowired
    public JwtParser(MessageSource messageSource, @Value("${jwt.secret-base64}") String secret) {

        this.secret = secret;
        this.messageSource = messageSource;
    }

    public void parse(final String token) {
        try {
            claimsBody = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.decode(secret))
                    .parseClaimsJws(token)
                    .getBody();
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            String message = messageSource.getMessage("invalidToken",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        } catch (SignatureException e) {
            String message = messageSource.getMessage("invalidSignature",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        } catch (ExpiredJwtException e) {
            String message = messageSource.getMessage("tokenExpired",
                    null, LocaleContextHolder.getLocale());
            throw new JwtAuthenticationException(message);
        }
    }

    public UUID getUserId() {
        return UUID.fromString(claimsBody.getSubject());
    }

    public String getEmail() {
        return claimsBody.get("email", String.class);
    }

    public List<String> getScopes() {
        return claimsBody.get("scopes", List.class);
    }

    Date getTokenExpirationDate() {
        return claimsBody.getExpiration();
    }
}
