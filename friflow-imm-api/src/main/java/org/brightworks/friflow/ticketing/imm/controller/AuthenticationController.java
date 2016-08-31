package org.brightworks.friflow.ticketing.imm.controller;

import org.brightworks.friflow.ticketing.imm.dto.UserAccountDTO;
import org.brightworks.friflow.ticketing.imm.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author kdavid
 */
@RestController
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/login",method = POST)
    public String auth(@RequestParam("username")String username,
                       @RequestParam("password")String password) {

        Optional<String> optional = userService.createToken(username,password);
        if(optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("Invalid Username or Password");
    }

    @RequestMapping(value = "/create", method = POST)
    public UserAccountDTO create(@RequestBody UserAccountDTO userAccountDTO) throws Exception {
        return userService.save(userAccountDTO);
    }

    @RequestMapping(value = "/dummy", method = GET)
    public UserAccountDTO create(){
        return new UserAccountDTO();
    }
}
