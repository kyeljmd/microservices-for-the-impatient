package org.brightworks.friflow.ticketing.imm.service;


import org.brightworks.friflow.ticketing.imm.domain.Role;
import org.brightworks.friflow.ticketing.imm.domain.UserAccount;
import org.brightworks.friflow.ticketing.imm.dto.UserAccountDTO;
import org.brightworks.friflow.ticketing.imm.repo.UserAccountRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author kyel
 */
@Service
public class UserDetailsServiceImpl implements UserService {

    private UserAccountRepo userAccountRepo;

    private PasswordEncoderImpl passwordEncoder;

    private TokenIssuer<String> tokenIssuer;

    public UserDetailsServiceImpl(UserAccountRepo userAccountRepo,
                                  PasswordEncoderImpl passwordEncoder,
                                  TokenIssuer<String> tokenIssuer) {
        this.userAccountRepo = userAccountRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenIssuer = tokenIssuer;
    }

    @Override
    public Optional<String> createToken(String username, String password) {
        UserAccount account = userAccountRepo.findByUsername(username);
        if(account != null) {
            if(passwordEncoder.matches(password, account.getPassword())) {
                return Optional.of(tokenIssuer.issueToken(username));
            }
        }
        return Optional.empty();
    }

    @Override
    public UserAccountDTO findById(Long id) {
        return toDto(userAccountRepo.findOne(id));
    }

    @Override
    public UserAccountDTO save(UserAccountDTO dto) throws Exception {
        UserAccount userAccount = new UserAccount();
        if(userAccountRepo.findByUsername(dto.getUsername()) != null) throw new Exception("Username is already existing");
        userAccount.setUsername(dto.getUsername());
        userAccount.setPassword(passwordEncoder.encode(dto.getPassword()));
        return toDto(userAccountRepo.save(userAccount));
    }

    public UserAccountDTO toDto(UserAccount account) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        userAccountDTO.setUsername(account.getUsername());
        return userAccountDTO;
    }


    private List<SimpleGrantedAuthority> grantedAuthorities(List<Role> roles){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role r: roles){
            authorities.add(new SimpleGrantedAuthority(r.toString()));
        }
        return  authorities;
    }
}
