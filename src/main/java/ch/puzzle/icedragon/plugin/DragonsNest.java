package ch.puzzle.icedragon.plugin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

import static ch.puzzle.icedragon.plugin.DragonParams.getParameter;


@Path("/dragons-nest")
public class DragonsNest {


    public static final String ICE_DRAGON_COOKIE_NAME = "ICE_DRAGON";
    private static final String ICE_DRAGON_ORIGIN_PARAM_NAME = "ICE_DRAGON_ORIGIN";
    private static final String ICE_DRAGON_ORIGIN_DEFAULT = "https://ice-dragon.ch";

    private String iceDragonOrigin;

    @PostConstruct
    public void init() {
        this.iceDragonOrigin = getParameter(ICE_DRAGON_ORIGIN_PARAM_NAME, ICE_DRAGON_ORIGIN_DEFAULT);

    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    public Response setDragonNest(@QueryParam("voucher") String iceDragon) {
        NewCookie cookie = new NewCookie(ICE_DRAGON_COOKIE_NAME, iceDragon);
        return Response
                .ok()
                .header("Access-Control-Allow-Origin", iceDragonOrigin)
                .header("Access-Control-Allow-Headers",
                        "accept, content-type, origin")
                .header("Access-Control-Allow-Methods","GET")
                .cookie(cookie).build();
    }
}