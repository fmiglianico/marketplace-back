package com.formation.jee.ws;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.formation.jee.common.MarketPlaceError;
import com.formation.jee.domain.Armor;
import com.formation.jee.domain.Consumable;
import com.formation.jee.domain.Item;
import com.formation.jee.domain.Player;
import com.formation.jee.domain.PlayerLogin;
import com.formation.jee.domain.Weapon;
import com.formation.jee.service.ItemService;
import com.formation.jee.service.PlayerService;
import com.formation.jee.service.ServiceManager;

@Path("/marketplace")
public class MarketPlaceWS {

	private PlayerService playerService;

	private ItemService itemService;

	public MarketPlaceWS() {
		playerService = ServiceManager.INSTANCE.getPlayerService();
		itemService = ServiceManager.INSTANCE.getItemService();
	}

	@POST
	@Path("/create-player")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPlayer(PlayerLogin playerLogin) throws SQLException {
		playerService.addPlayer(playerLogin);
		return Response.status(200).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(PlayerLogin playerLogin) throws SQLException {
		String token = playerService.login(playerLogin);
		if (token == null)
			return Response.status(403).build();
		return Response.status(200).entity(token).build();
	}

	@GET
	@Path("/get-all-items")
	@Produces(MediaType.APPLICATION_JSON)
	public GenericEntity<Set<Item>> getAllItems() {
		return new GenericEntity<Set<Item>>(itemService.getAll()) {};
	}

	@GET
	@Path("/get-all-armors")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Armor> getAllArmors() {
		return itemService.getAllArmors();
	}

	@GET
	@Path("/get-all-weapons")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Weapon> getAllWeapons() {
		return itemService.getAllWeapons();
	}

	@GET
	@Path("/get-all-consumables")
	@Produces(MediaType.APPLICATION_JSON)
	public Set<Consumable> getAllConsumables() {
		return itemService.getAllConsumables();
	}

	@GET
	@Path("/get-inventory/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getInventory(@PathParam(value = "login") String login) {
		return itemService.getInventoryOfPlayer(login);
	}

	@POST
	@Path("/my-inventory")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> getMyInventory(String token) {
		Player player = playerService.getPlayer(token);
		if (player == null)
			return null;
		return itemService.getInventoryOfPlayer(player.getLogin());
	}

	@POST
	@Path("/my-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Player getMyProfile(String token) {
		return playerService.getPlayer(token);
	}
	
	@POST
	@Path("/buy/{item_id}/{quantity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buy(String token, @PathParam(value="item_id") int itemId, @PathParam(value="quantity") int quantity) throws SQLException {

		Item item = itemService.get(itemId);
		item.setQuantity(quantity);
		MarketPlaceError mke = playerService.buy(token, item);
		if(mke != null)
			return Response.status(418).entity(mke).build();
		return Response.status(200).build();
	}
	
	@POST
	@Path("/sell/{item_id}/{quantity}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sell(String token, @PathParam(value="item_id") int itemId, @PathParam(value="quantity") int quantity) throws SQLException {
		
		Item item = itemService.get(itemId);
		item.setQuantity(quantity);
		MarketPlaceError mke = playerService.sell(token, item);
		if(mke != null)
			return Response.status(418).entity(mke).build();
		return Response.status(200).build();
	}

}
