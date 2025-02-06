package com.danilobml.gamestore;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.danilobml.gamestore.dto.GameCreateDTO;
import com.danilobml.gamestore.dto.GameDTO;
import com.danilobml.gamestore.dto.GameListCreateDTO;
import com.danilobml.gamestore.dto.ListMoveDTO;

@AutoConfigureMockMvc
@SpringBootTest
class GamestoreApplicationTests {

	@Autowired
	MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}

	@Test
	public void getGamesTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/games");
		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void getGameByIdTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/games/1");
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void createGameTest() throws Exception {
		GameCreateDTO newGame = new GameCreateDTO("New Game", 2020, "Platform", "PS5", 8.1, "test.image.com",
				"short description", "long description");
		RequestBuilder request = MockMvcRequestBuilders.post("/games")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newGame));

		mockMvc.perform(request)
				.andExpect(status().isCreated());
	}

	@Test
	public void updateGameTest() throws Exception {
		GameDTO updatedGame = new GameDTO(1L, "Updated Game", 2020, "Platform", "PS5", 9.0, "test.image.com",
				"short description", "long description");
		RequestBuilder request = MockMvcRequestBuilders.put("/games/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedGame));

		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void deleteGameTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/games/1");
		mockMvc.perform(request)
				.andExpect(status().isNoContent());
	}

	@Test
	public void getGameListsTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/lists");
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void getGamesInListTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/lists/1/games");
		mockMvc.perform(request)
				.andExpect(status().isOk());
	}

	@Test
	public void createGameListTest() throws Exception {
		GameListCreateDTO newList = new GameListCreateDTO("My Game List");
		RequestBuilder request = MockMvcRequestBuilders.post("/lists")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newList));

		mockMvc.perform(request)
				.andExpect(status().isCreated());
	}

	@Test
	public void deleteGameListTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/lists/1");
		mockMvc.perform(request)
				.andExpect(status().isNoContent());
	}

	@Test
	public void addGameToListTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/lists/1/game/10");
		mockMvc.perform(request)
				.andExpect(status().isCreated());
	}

	@Test
	public void removeGameFromListTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/lists/1/game/10");
		mockMvc.perform(request)
				.andExpect(status().isNoContent());
	}

	@Test
	public void moveGameInListTest() throws Exception {
		ListMoveDTO moveDTO = new ListMoveDTO(0, 1);
		RequestBuilder request = MockMvcRequestBuilders.post("/lists/1/move")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(moveDTO));

		mockMvc.perform(request)
				.andExpect(status().isOk());
	}
}
