package com.daycare_manager.daycare_manager;

import com.daycare_manager.daycare_manager.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
<<<<<<< HEAD
=======
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
>>>>>>> e37a316083091398fa7a61aa12e663e811b5f425
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
<<<<<<< HEAD
=======
@EnableWebSecurity
>>>>>>> e37a316083091398fa7a61aa12e663e811b5f425
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader userDetailsLoader;

    public SecurityConfiguration(UserDetailsLoader userDetailsLoader) {
        this.userDetailsLoader = userDetailsLoader;
    }


    @Bean
    // telling that this object should be built automatically
    public PasswordEncoder passwordEncoder() {
        //Hashing passwords:
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /* Login configuration */
                .formLogin()
                .loginPage("/login")
<<<<<<< HEAD
                .defaultSuccessUrl("/home") // user's home page, it can be any URL
=======
                .defaultSuccessUrl("/user/profile") // user's home page, it can be any URL
>>>>>>> e37a316083091398fa7a61aa12e663e811b5f425
                .permitAll() // Anyone can go to the login page
            /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
            /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home") // anyone can see the home and the posts pages
                .permitAll()
//            /* Pages that require athentication */
//                .and()
//                .authorizeRequests()
//                .antMatchers(
//                        "/posts/create", // only authenticated users can create posts
//                        "/posts/{id}/edit", // only authenticated users can edit posts
//                        "/posts/new",
//                        "posts/create"
//                )
//                .authenticated()
        ;
    }
}
<<<<<<< HEAD
=======


//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    /* ... */
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/dahsboard") // We'll determine the home page based on the role here
//                .permitAll()
//                .and()
//            /* ... */
//        ;
//    }
//}

//@Controller
//public class UsersController {
//    @GetMapping("/dashboard")
//    public String dashboard(HttpServletRequest request) {
//        if (request.isUserInRole("ROLE_STUDENT")) {
//            return "redirect:/dashboard/student"; // Suppose we already have an action for this one
//        }
//        return "redirect:/dashboard/teacher"; // And another for this one
//    }
//}
>>>>>>> e37a316083091398fa7a61aa12e663e811b5f425
