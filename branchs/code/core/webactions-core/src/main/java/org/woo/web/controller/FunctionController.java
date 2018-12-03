package org.woo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/25.
 */
@RestController
@RequestMapping(value = "fun")
public class FunctionController{
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
   
    public Map<String,Object> getPhotoList(){
        return null;
    }
    public Map<String,Object> getPhotoItemByPkId(){
        return null;
    }
    public Map<String,Object> updatePhotoByPkID(){
        return null;
    }
    public Map<String,Object> movePhotoItem(){
        return null;
    }
    public Map<String,Object> movePhotoItems(){
        return null;
    }
    public Map<String,Object> copyPhotoItem(){
        return null;
    }
    public Map<String,Object> copyPhotoItems(){
        return null;
    }
    public Map<String,Object> deletePhotoItem(){
        return null;
    }
    public Map<String,Object> deletePhotoItems(){
        return null;
    }
    @RequestMapping(value = "get",method = RequestMethod.POST)
    public Map<String,Object> getMenuItemById(String pkid){
        if(null!=pkid&&!"".equals(pkid)){
        }
        return null;
    }
    public Map<String,Object> updateMenuItemById(){
        return null;
    }
    public Map<String,Object> updateMenuItemsById(){
        return null;
    }
    public Map<String,Object> getRoleList(){
        return null;
    }
    public Map<String,Object> getRoleItemById(){
        return null;
    }
    public Map<String,Object> updateRoleItemById(){
        return null;
    }

    public Map<String,Object> updateRoleItems(){
        return null;
    }
    @RequestMapping(value = "getMenuList",method = RequestMethod.POST)
    public Map<String,Object> getGroupChildList(@RequestHeader String pkId){

        return null;
    }
}
