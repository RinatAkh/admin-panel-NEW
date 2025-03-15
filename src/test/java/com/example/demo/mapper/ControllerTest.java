package com.example.demo.mapper;

import com.example.demo.dao.FilePlayerDao;
import com.example.demo.dao.IdGenerator;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import com.example.demo.filter.PlayerOrder;
import com.example.demo.request.CreatePlayerRequest;
import com.example.demo.dto.FilterDto;
import com.example.demo.dto.PlayerDto;
import com.example.demo.request.PlayerFilterRequest;
import com.example.demo.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
class ControllerTest {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private FilePlayerDao filePlayerDao;
    @Autowired
    private IdGenerator generator;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    // Прверяем правильность возвращаемых данных при контроллере getPlayerList
    @Test
    public void getPlayersListTest() throws Exception {

        PlayerDto newPlayer = new PlayerDto();
        newPlayer.setName("Аркадий(getPlayersListTest)");
        newPlayer.setTitle("snob");
        newPlayer.setExperience(1);
        newPlayer.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer.setProfession(Profession.DRUID);
        newPlayer.setRace(Race.ELF);
        newPlayer.setBanned(false);

        PlayerDto newPlayer1 = new PlayerDto();
        newPlayer1.setName("Кристина (getPlayersListTest)");
        newPlayer1.setTitle("million");
        newPlayer1.setExperience(1);
        newPlayer1.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer1.setProfession(Profession.DRUID);
        newPlayer1.setRace(Race.ORC);
        newPlayer1.setBanned(false);


        PlayerDto newPlayer2 = new PlayerDto();
        newPlayer2.setName("Марго (getPlayersListTest)");
        newPlayer2.setTitle("hfjmco");
        newPlayer2.setExperience(1);
        newPlayer2.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer2.setProfession(Profession.DRUID);
        newPlayer2.setRace(Race.HUMAN);
        newPlayer2.setBanned(false);

        PlayerDto createdPlayer = playerService.createNewPlayer(newPlayer);
        PlayerDto createdPlayer1 = playerService.createNewPlayer(newPlayer1);
        PlayerDto createdPlayer2 = playerService.createNewPlayer(newPlayer2);


        FilterDto filterDto = new FilterDto();
        filterDto.setName("(getPlayersListTest)");
        filterDto.setOrder(PlayerOrder.ID);
        List<PlayerDto> listOfPlayers = playerService.getFilteredPlayers(filterDto);
        System.out.println(listOfPlayers);


        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(listOfPlayers.size()))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value(newPlayer.getName()))
                .andExpect(jsonPath("$[0].title").value(newPlayer.getTitle()))
                .andExpect(jsonPath("$[1].id").isNumber())
                .andExpect(jsonPath("$[1].name").value(newPlayer1.getName()))
                .andExpect(jsonPath("$[1].title").value(newPlayer1.getTitle()))
                .andExpect(jsonPath("$[2].id").isNumber())
                .andExpect(jsonPath("$[2].name").value(newPlayer2.getName()))
                .andExpect(jsonPath("$[2].title").value(newPlayer2.getTitle()));
    }


//     Прверяем правильность возвращаемых данных при контроллере getPlayerListCount
    @Test
    public void getPlayersListCountTest() throws Exception {

        List<Player> listOfPlayers = filePlayerDao.getAllPlayers(null);
//                playerService.getFilteredPlayers(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(listOfPlayers.size()));
    }


   // Прверяем правильность возвращаемых данных при контроллере createNewPlayer
    @Test
    public void createNewPlayerTest() throws Exception {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setName("David");
        createPlayerRequest.setTitle("cheap");
        createPlayerRequest.setRace(Race.HUMAN);
        createPlayerRequest.setExperience(5);
        createPlayerRequest.setProfession(Profession.DRUID);
        createPlayerRequest.setBirthday(new Date(124, Calendar.DECEMBER,5));

        String requestBody = mapper.writeValueAsString(createPlayerRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("David"))
                .andExpect(jsonPath("$.title").value("cheap"))
                .andExpect(jsonPath("$.race").value("HUMAN"))
                .andExpect(jsonPath("$.experience").value(5))
                .andExpect(jsonPath("$.profession").value("DRUID"))
                .andExpect(jsonPath("$.birthday").value("2024-12-04T21:00:00.000+00:00"));
    }


    //Test на то чтобы проверить выдается ли ошибка при некорректных данных. Если тест пройден значит выбрасывается исключение
    @Test
    public void testInvalidRequestForCreatePlayerRequest() throws Exception {
        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setName("helloy");
        createPlayerRequest.setTitle("djdkdls");
        createPlayerRequest.setRace(Race.HUMAN);
        createPlayerRequest.setExperience(10000000);
        createPlayerRequest.setProfession(null);
        createPlayerRequest.setBirthday(new Date(124, Calendar.DECEMBER,5));

        String requestBody = mapper.writeValueAsString(createPlayerRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void getPlayerByIdTest() throws Exception {
        PlayerDto newPlayer = new PlayerDto();
        newPlayer.setName("Bob");
        newPlayer.setTitle("djhbjhsbdcbd");
        newPlayer.setExperience(1);
        newPlayer.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer.setProfession(Profession.DRUID);
        newPlayer.setRace(Race.HUMAN);
        newPlayer.setBanned(false);

        PlayerDto createdPlayer = playerService.createNewPlayer(newPlayer);


        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players/{id}", createdPlayer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdPlayer.getId()))
                .andExpect(jsonPath("$.name").value(newPlayer.getName()))
                .andExpect(jsonPath("$.title").value(newPlayer.getTitle()))
                .andExpect(jsonPath("$.race").value("HUMAN"))
                .andExpect(jsonPath("$.experience").value(newPlayer.getExperience()))
                .andExpect(jsonPath("$.profession").value("DRUID"));

    }


    @Test
    public void deletePlayerTest() throws Exception {
        PlayerDto newPlayer = new PlayerDto();
        newPlayer.setName("Svetlana");
        newPlayer.setTitle("Who's that chick");
        newPlayer.setExperience(1);
        newPlayer.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer.setProfession(Profession.DRUID);
        newPlayer.setRace(Race.HUMAN);
        newPlayer.setBanned(false);

        PlayerDto createdPlayer = playerService.createNewPlayer(newPlayer);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/players/{id}", createdPlayer.getId()))
                .andExpect(status().isOk());


        PlayerDto playerToFind = playerService.getPlayerById(createdPlayer.getId());

        Assertions.assertNull(playerToFind);
    }


    @Test
    public void updatePlayerTest() throws Exception {

        PlayerDto newPlayer = new PlayerDto();
        newPlayer.setName("Svetlana");
        newPlayer.setTitle("Who's that chick");
        newPlayer.setExperience(1);
        newPlayer.setBirthday(new Date(124, Calendar.DECEMBER,5));
        newPlayer.setProfession(Profession.DRUID);
        newPlayer.setRace(Race.HUMAN);
        newPlayer.setBanned(false);

        PlayerDto createdPlayer = playerService.createNewPlayer(newPlayer);
        System.out.println(createdPlayer);


        PlayerFilterRequest playerFilterRequest = new PlayerFilterRequest();
        playerFilterRequest.setName("BOBBY");

        // Кстати по моему у requestBody не должно быть столько переменных
        String requestBody = mapper.writeValueAsString(playerFilterRequest);
        System.out.println(requestBody);


        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/{id}", createdPlayer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(createdPlayer.getId()))
                .andExpect(jsonPath("$.name").value(playerFilterRequest.getName()))
                .andExpect(jsonPath("$.title").value(createdPlayer.getTitle()))
                .andExpect(jsonPath("$.race").value("HUMAN"))
                .andExpect(jsonPath("$.experience").value(createdPlayer.getExperience()))
                .andExpect(jsonPath("$.profession").value("DRUID"));
    }

}
