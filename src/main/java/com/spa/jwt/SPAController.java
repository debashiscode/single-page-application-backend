package com.spa.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.spa.jwt.models.SPAAuthRequest;
import com.spa.jwt.models.SPAAuthResponse;
import com.spa.jwt.models.SPAPostsResponse;
import com.spa.jwt.repo.SPAPostsService;
import com.spa.jwt.services.SPAUserDetailsService;
import com.spa.jwt.util.JwtUtil;


@RestController
public class SPAController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private SPAUserDetailsService spaUserDetailsService;

    @Autowired
    private SPAPostsService spaPostsService;

    @GetMapping(value = "/v1/fetchPosts")
    public ResponseEntity<SPAPostsResponse> getPosts(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "100") Integer pageSize, @RequestParam(defaultValue = "") String title) {
        return new ResponseEntity<SPAPostsResponse>(spaPostsService.getAllPosts(pageNo, pageSize, title), new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/v1/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody SPAAuthRequest spaAuthRequesr) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(spaAuthRequesr.getUsername(), spaAuthRequesr.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = spaUserDetailsService
                .loadUserByUsername(spaAuthRequesr.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new SPAAuthResponse(jwt));
    }

}

