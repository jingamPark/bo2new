package com.example.bo2.service;


import com.example.bo2.dto.SecUserDTO;
import com.example.bo2.entity.SecUser;
import com.example.bo2.entity.SecUserRole;
import com.example.bo2.repository.SecUserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class SecUserService implements UserDetailsService {


    private final SecUserRepository secUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;


    //회원정보 보기
    public SecUserDTO read(String username) {

        Optional<SecUser> secUser = secUserRepository.findByUsername(username);

        if (secUser.isEmpty()) {

        }

        SecUser user = secUser.get();
        SecUserDTO secUserDTO = modelMapper.map(secUser, SecUserDTO.class);

        return secUserDTO;
    }

    public SecUserDTO findId(String email) {
        Optional<SecUser> secUser = secUserRepository.findByEmail(email);
        if (secUser.isEmpty()){
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }

        SecUser user = secUser.get();

        SecUserDTO secUserDTO = modelMapper.map(user, SecUserDTO.class);

        return secUserDTO ;
    }


//    public SecUserDTO findid(String email) {
//        Optional<SecUser> secUser
//                = secUserRepository.findByEmail(email);
//
//        if(secUser.isEmpty()){
//            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
//        }
//
//
//        SecUserDTO secUserDTO = modelMapper.map(secUser.get().getUsername(), SecUserDTO.class);
//
//        return secUserDTO;
//    }


    public void delete(Long sno) {

        secUserRepository.deleteById(sno);
    }




    public SecUser create(String username, String email, String password) {

        SecUser secUser = new SecUser();
        secUser.setUsername(username);
        secUser.setEmail(email);
        secUser.setPassword(passwordEncoder.encode(password));
        //비밀헌호는 보안을 위해서 암호화
        //스프링시큐리티의 BCryptPasswordEncoder 클래스 사용
        //비크립트 해시함수를 사용
        //사용하는곳마자 new를 생성해서 해도 괜찮지만, 사용하는곳에
        //찾아가서 지우고 수정하기 보다 한곳에 만들어두고 사용(빈등록)
        //security Config에 빈등록

        return secUserRepository.save(secUser);

    }


    // 스프링 시큐리티가 로그인시 사용하는 userDetailsService는 따로 만들어서 구현함
    // userDetailsService는 인터페이스며 구현해야하며, loadUserByUsername 메소드를 구현하도록 강제
    // loadUserByUsername 는 사용자명으로 스프링시큐리티의 사용자 객체를 조회하여 리턴함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<SecUser> secUser
                = secUserRepository.findByUsername(username);     //시큐리티가 아이디를 통해서 객체가 잇는지 확인


        if (secUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        SecUser  user = secUser.get();

        List<GrantedAuthority> authorities
                = new ArrayList<>();

        if ("admin".equals(username)) {
            //권한 지정 role지정
            authorities.add(new SimpleGrantedAuthority(SecUserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(SecUserRole.USER.getValue()));
        }

        log.info("로그인 폼에서 입력한 : " + username);
        log.info(authorities);

        return new  User(username, user.getPassword(), authorities);
        //권한까지 부여된 후 User객체를 생성해 변환
        //스프링시큐리티에서 사용하며 , 아이디 , 패스워드, 권한을 리턴
        //리턴된 User객체의 비밀번호와, 사용자가 폼에 입력한 비밀번호가 일치한는지 검사하는 기능을 내부에
        //가지고 있다.


    }

    //로그인 UserDetailsService 을 사용





}
