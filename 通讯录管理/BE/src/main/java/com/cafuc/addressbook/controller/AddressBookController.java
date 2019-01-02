package com.cafuc.addressbook.controller;

import com.cafuc.addressbook.bean.ResponseBean;
import com.cafuc.addressbook.model.AddressBook;
import com.cafuc.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
@CrossOrigin
public class AddressBookController{
    @Autowired
    private AddressBookService adbService;

    @GetMapping("/search/all")
    public ResponseBean searchInfoAll(){
        List<AddressBook>info;
        try{
            info=adbService.getAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(404,"fault",null);
        }
        return new ResponseBean(200,"success",info);
    }
    @GetMapping("search/one")
    public ResponseBean searchInfoOne(Integer id){
        List<AddressBook>info;
        try{
            info=adbService.getById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseBean(404,"fault",null);
        }
        return new ResponseBean(200,"success",info);
    }
    @GetMapping("/delete/name&tel")
    public int deleteInfoOne(Integer id){
        try{
            adbService.deleteById(id);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 404;
        }
    }
    @GetMapping("/insert/name&tel")
    public int insertInfo(String name,String tel){
        try{
            adbService.insert(name,tel);
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 404;
        }
    }
    @GetMapping("/sort")
    public int sortInfo(){
        try{
            adbService.sort();
            return 200;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 500;
        }

    }

}
