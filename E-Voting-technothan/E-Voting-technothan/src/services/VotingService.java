package services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bean.Candidates;
import bean.JsString;
import dao.VotersDAO;

@Path("/")
public class VotingService {
	@GET
	@Path("/validateContact/{uid}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<JsString> validateContact(@PathParam("uid") String uid) {

		String contact = new VotersDAO("govt_hibernate.cfg.xml").validateContact(uid);
  String response_String=contact+","+uid;
  

  JsString js=new JsString();
  js.setResponse(response_String);
 // res.add(js);

List<JsString>list = new ArrayList<JsString>();
list.add(js);
  return list;
  
 // return Response.ok(response_String,MediaType.APPLICATION_JSON).build();
		/*if (contact == null) {
               return item;
			//return Response.ok(item).build();
		} else {

			return Response.ok(item).build();
		}
*/
	}

	@GET
	@Path("/CandidateList/{uid}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<Candidates> getZonalCandidateList(@PathParam("uid") String uid) {

		List<Candidates> candidate = new VotersDAO("govt_hibernate.cfg.xml","election_hibernate.cfg.xml").getCandidates(uid);

		if (candidate.isEmpty()) {

			return null;
			// return new NoNotFound().getMessage();
		} else

			return candidate;
		// }

	}

	@GET
	@Path("/update_votes/{id}/{uid}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("id") int id,@PathParam("uid") String uid) {

		int result = new VotersDAO("election_hibernate.cfg.xml").updateVotes(id,uid);

		if (result == 1) {
			return Response.status(200).entity("SUCCESS").build();

		}

		return Response.status(200).entity("FAIL").build();

	}

	@GET
	@Path("/camplogin/{username}/{password}")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public List<JsString> campLogin(@PathParam("username") String username,
			@PathParam("password") String password) {
		System.out.println("Inside camplogin");
		boolean valid = new VotersDAO("election_hibernate.cfg.xml").validateLogin(username, password);
		
		JsString js=new JsString();
		  
		 // res.add(js);

		if (valid == true)
			js.setResponse("Proceed");
		else
			js.setResponse("Invalid_User");
		
		List<JsString>list = new ArrayList<JsString>();
		list.add(js);
		  return list;
		
	}




@GET
   @Path("/zone/{zone_name}")
   @Produces(value={MediaType.APPLICATION_JSON})
   public Response showResult(@PathParam("zone_name")String zone_name)
   {
	   
	   Candidates c=new VotersDAO().fetchResult(zone_name);
	   if(c!=null)
		   return Response.ok(c).build();
	   else
		   return Response.ok("Zone_not_found").build();
   }
	}

