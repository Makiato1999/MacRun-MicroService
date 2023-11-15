package com.course.project.gamecenter.adapters;

import com.course.project.gamecenter.adapters.resp.Response;
import com.course.project.gamecenter.business.entity.GamePlayEntity;
import com.course.project.gamecenter.dto.GamePlayReq;
import com.course.project.gamecenter.port.GameCenterService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gameCenter")
@Slf4j
public class GameCenterController {

    @Resource
    private GameCenterService gameCenterService;


    @RequestMapping(value = "register", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<GamePlayEntity> userReact(@RequestParam(required = true) Long userId,
                                              @RequestParam Integer attackId) {

        log.info("【Scenario303 - GameCenter】-【GameCenter】User start play, userId={},attackId={}",
                userId, attackId);

        GamePlayReq gamePlayReq = GamePlayReq.builder()
                .userId(userId)
                .attackId(attackId)
                .build();
        GamePlayEntity gamePlayEntity = gameCenterService.play(gamePlayReq);
        return Response.success(gamePlayEntity);
    }
}
