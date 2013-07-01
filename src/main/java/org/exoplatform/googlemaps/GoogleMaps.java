/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.googlemaps;

import java.io.IOException;

import javax.inject.Inject;

import juzu.Path;
import juzu.Resource;
import juzu.Response;
import juzu.Route;
import juzu.View;
import juzu.io.Stream;
import juzu.plugin.ajax.Ajax;
// Type safe template

import org.exoplatform.googlemaps.templates.googleMaps;
/**
 * @author <a href="mailto:sondn@exoplatform.com">Ngoc Son Dang</a>
 * @since Jun 28, 2013
 * @version 
 * 
 * @tag 
 */
public class GoogleMaps {

	@Inject
	@Path("googleMaps.gtmpl")
	org.exoplatform.googlemaps.templates.googleMaps googleMaps;
	
	
	@View
	public void index() throws IOException {
		index("", "");
	}
	
	@View
	public void index(String location, String mapURL) throws IOException {
		googleMaps.with().location(location).mapURL(mapURL).render();
	}
	
	@Ajax
	@Resource
	public Response.Content<Stream.Char> updateMaps(String location) {
		// Preparing Google Maps API for request
		String mapURL = "https://maps.google.fr/maps?f=q&source=s_q&hl=en&geocode=&q=";
		mapURL += location;
		mapURL += "&aq=&t=m&ie=UTF8&hq=&hnear=";
		mapURL += location;
		mapURL += "&z=12&output=embed";
		
		System.out.println("mapURL: " + mapURL);
		
		// Rendering response
		return Response.ok("{\"mapURL\": \"" + mapURL +"\"}").withMimeType("application/json");
	}
}
