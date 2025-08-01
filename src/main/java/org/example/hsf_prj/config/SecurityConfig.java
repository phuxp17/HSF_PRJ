    package org.example.hsf_prj.config;

    import lombok.RequiredArgsConstructor;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.http.HttpMethod;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.ProviderManager;
    import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
    import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
    import org.springframework.security.config.http.SessionCreationPolicy;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.security.web.SecurityFilterChain;
    import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
    import org.springframework.web.cors.CorsConfiguration;
    import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

    import java.util.Arrays;
    import java.util.List;


    @Configuration // Đánh dấu đây là lớp cấu hình của Spring
    @RequiredArgsConstructor
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfig {
        private final JwtRequestFilter jwtAuthenticationFilter;
        private final UserDetailsService userDetailsService;

        @Bean// Đánh dấu phương thức này sẽ tạo ra một Spring Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(); // Sử dụng BCryptPasswordEncoder
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder()); // Gọi phương thức @Bean để lấy instance
            return new ProviderManager(List.of(authProvider));
        }

        private static final String[] PUBLIC_ENDPOINTS = {
                "register", "login", "me", "sendOTP", "verifyOTP", "verify-email-code",
                "forget-password", "reset-password"
        };

        private static final String[] GET_PUBLIC_ENDPOINTS = {
                "/blogs/**", "/profiles/**", "/banner/**"
        };



        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/**").permitAll());
            return http.build();
        }


        @Bean
        public UrlBasedCorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();

            // Chỉ cho phép frontend chạy ở các địa chỉ sau
            corsConfiguration.setAllowedOriginPatterns(List.of(
                    "http://localhost:*",
                    "http://127.0.0.1:*",
                    "https://*.vercel.app",
                    "https://*.onrender.com"
            ));

            // vietnam.com ,.vn cho moi duoi truy cap dc
            // Chỉ định các HTTP method được phép
            corsConfiguration.setExposedHeaders(Arrays.asList("*")); // allow bear/ auth token
            corsConfiguration.setAllowedHeaders(Arrays.asList("*")); //  la method option  vdu goi get goi option trc bao trinh duyet mehtod dc thuc hien hay k
            corsConfiguration.setAllowedMethods(Arrays.asList(
                    "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
            ));


            // Chỉ định các header được phép gửi từ client
            corsConfiguration.setAllowedHeaders(Arrays.asList(
                    "Authorization", "Content-Type", "X-Requested-With"
            ));

            // Chỉ định các header client được phép đọc từ response
            corsConfiguration.setExposedHeaders(List.of("Authorization"));

            // Bật allowCredentials để dùng cookie / token trong header
            corsConfiguration.setAllowCredentials(true);

            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return source;
        }

        //    NGHĨA LÀ CSRF TOKEN LÀ CÁI TRÁNH BỊ GỬI REQUEST TỪ 1 TRANG WEB KHÁC KÈM TOKEN ĐĂNG NHAAPH
        //    Ở WEB TỐT CÒN CORS LÀ CHI CHO PHEP NHUNG CAI TRNAG NAO DC GUI REQUEST CHO NHAU


    //    NGHĨA LÀ CSRF TOKEN LÀ CÁI TRÁNH BỊ GỬI REQUEST TỪ 1 TRANG WEB KHÁC KÈM TOKEN ĐĂNG NHAAPH
    //    Ở WEB TỐT CÒN CORS LÀ CHI CHO PHEP NHUNG CAI TRNAG NAO DC GUI REQUEST CHO NHAU
    }



