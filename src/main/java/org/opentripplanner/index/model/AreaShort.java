/* This program is free software: you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public License
 as published by the Free Software Foundation, either version 3 of
 the License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>. */

package org.opentripplanner.index.model;

import com.vividsolutions.jts.geom.Geometry;
import org.onebusaway.gtfs.model.AgencyAndId;
import org.opentripplanner.util.PolylineEncoder;
import org.opentripplanner.util.model.EncodedPolylineBean;

public class AreaShort {
    public AgencyAndId areaId;
    public EncodedPolylineBean polygon;

    public AreaShort(AgencyAndId areaId, Geometry polygon) {
        this.areaId = areaId;
        this.polygon = PolylineEncoder.createEncodings(polygon.getBoundary());
    }
}
