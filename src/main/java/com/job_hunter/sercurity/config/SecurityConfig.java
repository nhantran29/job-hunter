package com.job_hunter.sercurity.config;

import com.job_hunter.sercurity.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration          // Báo với Spring đây là một class cấu hình (Configuration class)
@EnableWebSecurity      // Kích hoạt Spring Security trong ứng dụng
public class SecurityConfig {

    /**
     * Dịch vụ tùy chỉnh để Spring Security tải thông tin người dùng từ DB.
     * Class này implements UserDetailsService.
     */
    @Autowired private CustomUserDetailsService userDetailsService;

    /**
     * ✅ Định nghĩa SecurityFilterChain — là “bộ lọc an ninh chính”
     *
     * HttpSecurity là nơi bạn cấu hình các quy tắc bảo mật HTTP:
     * - cho phép endpoint nào truy cập công khai (permitAll)
     * - endpoint nào cần xác thực (authenticated)
     * - dùng form login mặc định hay custom
     * - cấu hình CSRF, logout, session, v.v.
     */
    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🚫 Tắt CSRF để test API dễ hơn (vì API REST thường không cần CSRF)
                .csrf(AbstractHttpConfigurer::disable)

                // 🔐 Cấu hình quyền truy cập cho các endpoint
                .authorizeHttpRequests(auth -> auth
                        // Cho phép tất cả người dùng (kể cả chưa login) truy cập API auth (đăng ký, đăng nhập)
                        .requestMatchers("api/auth/**").permitAll() // cho phép public API
                        // Tất cả các request khác bắt buộc phải đăng nhập mới truy cập được
                        .anyRequest().authenticated() // các API khác cần login
                )
                // 🧾 Bật form login mặc định của Spring Security (hiển thị giao diện login mặc định)
                .formLogin(Customizer.withDefaults()) // form login mặc định
                // 🚪 Bật logout mặc định (Spring tự tạo endpoint /logout)
                .logout(Customizer.withDefaults())
                // ⚙️ Cấu hình xác thực (thay thế cho and().userDetailsService(...))
                .userDetailsService(userDetailsService);;
        // Trả về cấu hình đã hoàn chỉnh (build filter chain)
        return http.build();
    }

    /**
     * ✅ Cấu hình AuthenticationManager (trình quản lý xác thực)
     *
     * Đây là nơi Spring Security biết:
     * - Dùng CustomUserDetailsService để tải thông tin người dùng từ DB
     * - Dùng PasswordEncoder nào để kiểm tra mật khẩu
     * dành cho spring dưới 6.1
     */

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        return http
//                .getSharedObject(AuthenticationManagerBuilder.class)
//                // Gán custom service để Spring Security dùng khi xác thực
//                .userDetailsService(userDetailsService)
//                // Dùng BCrypt để mã hóa & so sánh mật khẩu
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//    }
    /**
     * ✅ AuthenticationManager được Spring tự cấu hình qua AuthenticationConfiguration
     * → Không cần dùng getSharedObject / and() như bản cũ nữa.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * ✅ Bean dùng để mã hóa mật khẩu (PasswordEncoder)
     *
     * BCrypt là thuật toán mã hóa phổ biến, mạnh và an toàn trong Spring Security.
     *
     * Khi lưu user mới vào DB, bạn cần:
     *  user.setPassword(passwordEncoder().encode(rawPassword));
     *
     * Khi login, Spring sẽ tự động so sánh mật khẩu mã hóa.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
