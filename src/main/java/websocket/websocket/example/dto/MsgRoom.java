package websocket.websocket.example.dto;

import websocket.websocket.example.entity.Message;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.socket.WebSocketSession;
import websocket.websocket.example.service.MsgService;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MsgRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // TODO : roomId는 MatchId + 진영 코드
    private String roomId;

//    private Set<WebSocketSession> sessions = new HashSet<>();
//
//    public void handleActions(WebSocketSession session, Message message, MsgService msgService) {
//        if (message.getMessageType().equals(Message.MessageType.ENTER)) {
//            sessions.add(session);
//            message.setMessage(message.getSender() + "님이 입장했습니다.");
//        }
//        sendMessage(message, msgService);
//    }
//
//    public <T> void sendMessage(T message, MsgService messageService) {
//        sessions.parallelStream().forEach(session->messageService.sendMessage(session, message));
//    }
}