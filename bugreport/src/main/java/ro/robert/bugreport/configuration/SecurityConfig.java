package ro.robert.bugreport.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static ro.robert.bugreport.configuration.ApplicationUserPermission.PROGRAMMER_VIEW_LIST;
import static ro.robert.bugreport.configuration.ApplicationUserPermission.TESTER_VIEW_LIST;
import static ro.robert.bugreport.configuration.Role.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login","/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/logout", "/register","/api/**").permitAll()
                .and()
//                .authorizeRequests()
////                .antMatchers("/register").hasAnyRole(ADMIN.name())
//                //.antMatchers("/api/v1/homepage/tester/**").hasAnyRole(ADMIN.name(), TESTER.name())
//                .antMatchers(HttpMethod.GET, "/api/v1/homepage/tester").hasAuthority(TESTER_VIEW_LIST.getPermission())
//                .antMatchers("/api/v1/homepage/programmer/**").hasAnyRole(ADMIN.name(), PROGRAMMER.name())
//                .antMatchers(HttpMethod.GET, "/api/v1/homepage/programmer").hasAuthority(PROGRAMMER_VIEW_LIST.getPermission())
//                .anyRequest()
//                .authenticated()
//                .and()
                .httpBasic();

    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(ADMIN.getGrantedAuthority())
                .build();

        UserDetails user = User.builder()
                .username("programmer")
                .password(passwordEncoder.encode("programmer"))
                .authorities(PROGRAMMER.getGrantedAuthority())
                .build();

        UserDetails tester = User.builder()
                .username("tester")
                .password(passwordEncoder.encode("tester"))
                .authorities(TESTER.getGrantedAuthority())
                .build();

        return new InMemoryUserDetailsManager(admin, user, tester);
    }
}
