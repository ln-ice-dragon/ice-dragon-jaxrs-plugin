package ch.puzzle.icedragon.plugin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import java.util.Date;

import static ch.puzzle.icedragon.plugin.DragonParams.getParameter;

@ApplicationScoped
public class DragonCheck {

    private static final String ICE_DRAGON_SECRET_PARAM_NAME = "ICE_DRAGON_SHARED_SECRET";

    private JWTVerifier verifier;

    @PostConstruct
    public void init() {

        String sharedSecret = getParameter(ICE_DRAGON_SECRET_PARAM_NAME);
        Algorithm algorithm = Algorithm.HMAC512(sharedSecret);
        verifier = JWT.require(algorithm)
                .build();
    }

    public boolean isValid(String iceDragon) {
        try {
            DecodedJWT jwt = verifier.verify(iceDragon);
            return jwt.getExpiresAt().after(new Date());
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}
