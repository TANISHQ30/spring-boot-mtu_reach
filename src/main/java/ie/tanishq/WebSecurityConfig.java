package ie.tanishq;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception    {
         /*
          * There are 4 types of user roles defined in the application -
          * 1. Admin - Can see the list of users and their login details, can see all, inactive(0 notes assigned), active(assigned notes>4) and search mentees, can see all notes, can see stats. Add/delete notes & mentees not permitted.
          * 2. Mentor - Can see all notes & mentees, can add/delete mentees and notes, can see inactive mentees(0 notes assigned) and search for mentees.
          * 3. Reviewer - Can see all notes & mentees, can see active mentees(assigned notes>4) and search for mentees, can see stats, add/delete notes & mentees not permitted.
          * 4. API - Access REST endpoints to find all notes for a student and delete a student.
          */


        http.authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/login","/","/h2/**","/403").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/mentee/add", "/mentee/add/**", "/note/add", "/note/add/**", "/mentee/delete/**").hasRole("REACH_MENTOR") //Only for Mentor
                .antMatchers("/mentee","/mentee/**","/mentee/showall","/mentees/inactive","/note/showall","/note/**").hasAnyRole("REACH_MENTOR", "REACH_REVIEWER", "REACH_ADMIN") //Common to reviewer and mentor
                .antMatchers("/stats").hasAnyRole("REACH_ADMIN", "REACH_REVIEWER") //Only for Admin
                .antMatchers("/user/**").hasRole("REACH_ADMIN") //Only for Admin
                .antMatchers("/api","/api/**").hasRole("REACH_API") //Only for API user
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/")
                .and().httpBasic()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().csrf().disable();
        http.headers().frameOptions().disable(); //Added this to render h2 console properly
    }

}
