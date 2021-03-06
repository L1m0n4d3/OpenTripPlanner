package org.opentripplanner.geocoder.reverse;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/*import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Envelope;*/


@Path("/municoder")
public class MunicoderServer {

    /* FIXME Spring config looked like this:
        <bean id="boundaryResolver" class="org.opentripplanner.municoder.ShapefileBoundaryResolver"
    	  init-method="initialize">
    	<property name="shapefile" value="/var/otp/shp/pdx_boundaries.shp" />
    	<property name="nameField" value="CITYNAME" />
    </bean>

     */

    // FIXME inject context
    private BoundaryResolver boundaryResolver;
   
    public void setBoundaryResolver(BoundaryResolver boundaryResolver) {
        this.boundaryResolver = boundaryResolver;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String resolveLocation(
            @QueryParam("location") String location,
            @QueryParam("callback") String callback) {
        if (location == null) {
            throw new BadRequestException("no 'location' to resolve");
        }
        String[] arr = location.split(",");
        //System.out.println("br="+boundaryResolver);
        //return arr[0];
        String result = boundaryResolver.resolve(Double.parseDouble(arr[1]), Double.parseDouble(arr[0]));
        result = (result == null) ? "{}" : "{ \"name\" : \""+result+"\" }";
        if(callback != null) {
            return callback+"("+result+");";
        }
        return result;
    }
}
