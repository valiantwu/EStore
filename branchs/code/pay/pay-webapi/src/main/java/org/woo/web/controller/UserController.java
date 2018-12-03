package org.woo.web.controller;


import org.springframework.web.bind.annotation.*;
import org.woo.dataentity.annotation.AccessTokenValidate;
import org.woo.dataentity.model.Page;
import org.woo.mservice.IUserService;
import org.woo.orm.entity.User;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/4/6.
 */
@RestController
@RequestMapping("auth")
public class UserController extends AbstractController<User> {

    private IUserService userService;

    @RequestMapping(value = "/getUsers.do", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getUserList(@RequestParam(value = "pageSize", required = false) String pageSize,
                                    @RequestParam(value = "currentPage", required = false) String currentPage) {
        int ps = Integer.parseInt(pageSize);
        int cp = Integer.parseInt(currentPage);
        return super.resultPageUtil(new Page<>());
    }

    @RequestMapping(value = "/getUser.do", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getUser(@RequestParam(value = "pkId") String pkId) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        if (pkId != null && !"".equals(pkId.trim())) {
            reMap = super.resultEUtil(userService.getUserInfoById(pkId));
        } else {
            reMap.put(KEY_CODE, RESCODE_FAILD);
            reMap.put(KEY_MSG, RESDATA_REQERR);
        }
        return reMap;
    }

    @AccessTokenValidate
    @RequestMapping(value = "/updateUsrInfo.do")
    public @ResponseBody
    Map<String, Object> updateUserInfo(@RequestBody User master) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        return null;
    }
}
