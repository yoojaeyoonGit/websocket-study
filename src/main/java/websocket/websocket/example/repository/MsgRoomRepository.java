package websocket.websocket.example.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;
import websocket.websocket.example.dto.MsgRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Transactional
@RequiredArgsConstructor
public class MsgRoomRepository {
    private final EntityManager em;

    private static Map<String, MsgRoom> msgRoomMap;

    @PostConstruct
    private void init() {
        msgRoomMap = new LinkedHashMap<>();
    }

    public List<MsgRoom> findAllRoom() {
//        List<MsgRoom> msgRooms = new ArrayList<>(msgRoomMap.values());
//        Collections.reverse(msgRooms);

        return em.createQuery("select m from MsgRoom m ", MsgRoom.class)
                .getResultList();
    }


    public MsgRoom findByRoomId(String roomId) {
        return em.createQuery("select m from MsgRoom m where m.roomId = :roomId", MsgRoom.class)
                .setParameter("roomId", roomId)
                .getSingleResult();
//        MsgRoom room = em.find(MsgRoom.class, id);
//        System.out.println(room.getRoomId());
    }

    public MsgRoom createMsgRoom(String name) {
        MsgRoom room = MsgRoom.builder().roomId(name).build();
//        msgRoomMap.put(room.getRoomId(), room);
        em.persist(room);
        return room;
    }
}
