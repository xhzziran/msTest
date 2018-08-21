package cn.free.controller;

import cn.free.pojo.Sale;
import cn.free.pojo.User;
import cn.free.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public ModelAndView login(User user,HttpSession session){
        ModelAndView mv = new ModelAndView("/hello.jsp");
        user=userService.login(user);
        session.setAttribute("user",user);
        if (user!=null){
            mv.addObject("loginUser",user);
            return mv;
        }else {
            mv.setViewName("/index.jsp");
            mv.addObject("islogin","false");

        }
        return mv;

    }


    @RequestMapping("/addSale")
    @ResponseBody
    public Object sale(@RequestParam(name = "productId",required=false) Integer productId, @RequestParam(name = "price",required=false)Double price, @RequestParam(name = "quantity",required=false)Integer quantity, HttpSession session){
        String str = null;

        int num = userService.queryQuantity( productId);
        if(num<quantity){
            str="{\"indicate\":\"库存不足\"}";
        }else{
            double totalPrice = price*quantity;
            Date saleDate = new Date();
            User user = (User) session.getAttribute("user");
            int userId = user.getId();
            Sale sale = new Sale(price, quantity, totalPrice, saleDate, userId, productId);
            int add = userService.addRecord(sale);
            if(add>0){
                str="{\"indicate\":\"添加成功\"}";
            }else {
                str="{\"indicate\":\"添加失败\"}";
            }
        }

        return str;
    }

    @RequestMapping("/saleDetail")
    @ResponseBody
    public Object saleDetail(Integer pageNum,Integer pageSize,String obj){
        System.out.println(obj);
        PageInfo pageInfo = userService.findAll(obj,pageNum,pageSize);

        return pageInfo;
    }


    @RequestMapping("/exit")
    @ResponseBody
    public ModelAndView exit(HttpSession session){
       session.invalidate();
        ModelAndView mv = new ModelAndView("/index.jsp");
        return mv;
    }


    @RequestMapping("/insert")
    public ModelAndView insert(User user){
        ModelAndView mv = new ModelAndView("/hello.jsp");
        System.out.println(user);
        userService.insert(user);
        return mv;
    }

}
