package websocket.websocket.example.controller;

import websocket.websocket.example.dto.MsgRoom;
import websocket.websocket.example.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import websocket.websocket.example.service.MsgService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MsgController {

    @GetMapping("/hello")
    public String hello() {
        // TODO : name is Summoner Name
        return "hello";
    }

    private final SimpMessageSendingOperations sendingOperations;

    @MessageMapping("/comm/message")
    public void message(Message message) {
        if (Message.MessageType.ENTER.equals(message.getMessageType())) {
            message.setMessage(message.getSender() + "이 입장했습니다.");
        }
        sendingOperations.convertAndSend("/sub/comm/room/" + message.getRoomId(), message);
    }


    private final MsgService msgService;

    @PostMapping
    public MsgRoom createRoom(@RequestParam String name) {
        // TODO : name is Summoner Name
        return msgService.createRoom(name);
    }

    @GetMapping
    public List<MsgRoom> findAllRoom() {
        return msgService.findAllRoom();
    }
}