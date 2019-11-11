package ch.puzzle.icedragon.plugin;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import static ch.puzzle.icedragon.plugin.IceDragonParams.getParameter;


@Path("/dragons-nest")
public class DragonsNest {


    public static final String ICE_DRAGON_COOKIE_NAME = "ICE_DRAGON";
    private static final String ICE_DRAGON_ORIGIN_PARAM_NAME = "ICE_DRAGON_ORIGIN";
    private static final String ICE_DRAGON_ORIGIN_DEFAULT = "https://ice-dragon.ch";

    private String iceDragonOrigin;

    public DragonsNest() {
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