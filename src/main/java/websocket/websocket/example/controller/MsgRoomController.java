package websocket.websocket.example.controller;


import websocket.websocket.example.dto.MsgRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import websocket.websocket.example.repository.MsgRoomRepository;
import websocket.websocket.example.service.MsgService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comm")
public class MsgRoomController {

    private final MsgService msgService;

    private final MsgRoomRepository msgRoomRepository;

    @GetMapping("/room")
    public String room(Model model) {
        return "/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<MsgRoom> rooms() {
        return msgRoomRepository.findAllRoom();
    }

//    @ApiOperation(value = "방 입장", notes = "room ID를 통해서 방에 입장합니다.")
    @GetMapping("/room/enter/{roomId}")
    public String roomEnter(
            Model model,
//            @ApiParam(value = "방 ID", required = true)
            @PathVariable String roomId) {

        model.addAttribute("roomId", roomId);
        return "/roomdetail";
    }

//    @ApiOperation(value = "방 조회", notes = "room ID를 통해서 방을 조회합니다.")
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public MsgRoom roomInfo(
//            @ApiParam(value = "방 ID", required = true)
            @PathVariable String roomId) {
        return msgRoomRepository.findByRoomId(roomId);
    }

    @PostMapping("/room")
    @ResponseBody
    public MsgRoom createRoom(@RequestParam String name) {
        // TODO : name is Summoner Name
        return msgRoomRepository.createMsgRoom(name);
    }
}