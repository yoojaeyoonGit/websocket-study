package websocket.websocket.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import websocket.websocket.example.dto.MsgRoom;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//@Slf4j
@Service
@RequiredArgsConstructor
public class MsgService {
    private final ObjectMapper objectMapper;
    private Map <String, MsgRoom> msgRooms;

    @PostConstruct
    private void init() {
        msgRooms = new LinkedHashMap<>();
    }

    public List<MsgRoom> findAllRoom() {
        return new ArrayList<>(msgRooms.values());
    }

    public MsgRoom findById(String roomId) {
        return msgRooms.get(roomId);
    }

    public MsgRoom createRoom(String name) {
        // TODO : roomId == Summoner MatchId + blue/red Team code + Summoner Name
        String roomId = name;
        return MsgRoom.builder().roomId(roomId).build();
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}