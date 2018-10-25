package org.woo.login.controller;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.woo.orm.entity.TokenHistory;
import org.woo.orm.entity.User;
import org.woo.web.Constants;
import org.woo.web.controller.AbstractController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "login")
public final class LoginController extends AbstractController<User> {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * @param @return
     * @return Map<String,Object>
     * @throws
     * @Title: loginRequest
     * @Description: TODO
     */
    @RequestMapping(value = "/loginRequest.do", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> loginRequest() {
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("code", 200);
        logger.info("热委托人" + reMap);
        return reMap;
    }

    /**
     * @param @return
     * @return Map<String,Object>
     * @throws
     * @Title: indexRequest
     */
    @RequestMapping(value = "/indexRequest.do", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> indexRequest() {
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("code", 200);
        return reMap;
    }

    /**
     * @param @return
     * @return Map<String,Object>
     * @throws
     * @Title: userLogin
     * @Description: TODO
     */
    @RequestMapping(value = "/userLogin.do", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> userLogin(
            @RequestBody User master) throws Exception {
        Map<String, Object> reMap = new HashMap<String, Object>();
        User reUser = null;
        TokenHistory serverTokenHistory = new TokenHistory();
        reUser = userService.queryByUserName(master.getLoginName());
        if (reUser != null && master.getLoginName().equalsIgnoreCase(reUser.getLoginName())) {
            if (master.getLoginPassWord().equals(reUser.getLoginPassWord())) {
                reMap.put(KEY_CODE, RESCODE_SUCCESS);
                reMap.put(KEY_DATA, reUser);
                reMap.put(KEY_MSG, LOGIN_SUCCESS);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pkId", reUser.getPkId());
                String subject = jwtUtil.generalSubject(map);
                String loginTokenHistory = jwtUtil.createJWT(Constants.JWT_ID, subject, Constants.JWT_TTL);
//                if (iTokenService.updateToken(reUser.getPkId(), loginTokenHistory)) {
//                    serverTokenHistory.setPkId(iTokenHistoryService.getSeq());
//                    serverTokenHistory.setLoginIp(super.getIpV4());
//                    serverTokenHistory.setAccessToken(loginTokenHistory);
//                    serverTokenHistory.setFkUserId(reUser.getPkId());
//                    iTokenHistoryService.insert(serverTokenHistory);
//                }
                reMap.put(KEY_AUTH, loginTokenHistory);
            } else {
                reMap.put(KEY_CODE, RESCODE_NOEXIST);
                reMap.put(KEY_MSG, LOGIN_FAILD);
            }
        } else {
            reMap.put(KEY_CODE, RESCODE_NOEXIST);
            reMap.put(KEY_MSG, LOGIN_USEERNAMENOTEXIT);
        }
        logger.info("" + reMap);
        return reMap;
    }

    @RequestMapping(value = "/tokenLogin.do", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> loginTokenLogin(@RequestParam(value = "authorization") String loginTokenHistory) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        TokenHistory serverTokenHistory = new TokenHistory();
        Claims claims = null;
        String refushToken = null;
        try {
            claims = jwtUtil.parseJWT(loginTokenHistory);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            reMap.put(KEY_EXCEPTION, e.getMessage());
            reMap.put(KEY_CODE, RESCODE_EXCEPTION);
            reMap.put(KEY_MSG, AUTH_FAILD_EXP);
            return reMap;
        }
        if (claims != null) {
            String json = claims.getSubject();
            User master = JSONObject.parseObject(json, User.class);
            String accessToken = (String) iTokenService.getTokenByMaterId(master.getPkId());
            TokenHistory tokenHistory = null;
//            tokenHistory = iTokenHistoryService.getTokenByAccessToken(accessToken);
            if (loginTokenHistory.equals(accessToken)) {
                if (tokenHistory != null && tokenHistory.getLoginIp() != null && tokenHistory.getLoginIp().length() > 0 && super.getIpV4().equals(tokenHistory.getLoginIp())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("pkId", master.getPkId());
                    String subject = jwtUtil.generalSubject(map);
                    try {
                        refushToken = jwtUtil.createJWT(Constants.JWT_ID, subject, Constants.JWT_TTL);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    if (iTokenService.updateToken(master.getPkId(), refushToken)) {
//                        serverTokenHistory.setPkId(iTokenHistoryService.getSeq());
//                        serverTokenHistory.setFkUserId(master.getPkId());
//                        serverTokenHistory.setLoginIp(super.getIpV4());
//                        serverTokenHistory.setAccessToken(refushToken);
//                        iTokenHistoryService.insert(serverTokenHistory);
//                    }
                    reMap.put(KEY_CODE, RESCODE_SUCCESS);
                    reMap.put(KEY_AUTH, refushToken);
                    reMap.put(KEY_MSG, AUTH_SUCCESS_MSG);
                } else {
                    reMap.put(KEY_CODE, RESCODE_FAILD);
                    reMap.put(KEY_MSG, AUTH_FAILD_IP);
                }
            } else {
                reMap.put(KEY_CODE, RESCODE_FAILD);
                reMap.put(KEY_MSG, LOGIN_OTHERDEC);
            }
        }
        return reMap;
    }

    /**
     * @param @return
     * @return Map<String,Object>
     * @throws
     * @Title: registerRequest
     * @Description: TODO
     */
    @ResponseBody
    @RequestMapping(value = "/registerRequest.do", method = RequestMethod.POST)
    public Map<String, Object> registerRequest() {
        Map<String, Object> reMap = new HashMap<String, Object>();
        reMap.put("code", 200);
        logger.info("" + reMap);
        return reMap;
    }

    /**
     * @param
     * @return void
     * @throws
     */
    @ResponseBody
    @RequestMapping(value = "/password.do", method = RequestMethod.POST)
    public void modifyPassWord(String userName) {
    }

    @RequestMapping(value = "/loginOut.do", method = RequestMethod.GET)
    public Map<String, Object> loginOut() {
        return null;
    }
}
