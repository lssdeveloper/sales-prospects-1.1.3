package br.com.acangasolucoes.erp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public AppUserDetailsService userDetailsService() {
        return new AppUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JsfLoginUrlAuthenticationEntryPoint jsfLoginEntry = new JsfLoginUrlAuthenticationEntryPoint();
        jsfLoginEntry.setLoginFormUrl("/Login.xhtml");
        jsfLoginEntry.setRedirectStrategy(new JsfRedirectStrategy());

        JsfAccessDeniedHandler jsfDeniedEntry = new JsfAccessDeniedHandler();
        jsfDeniedEntry.setLoginPath("/AcessoNegado.xhtml");
        jsfDeniedEntry.setContextRelative(true);

        http
                .csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()

                .authorizeRequests()
                //aqui ficam todas as páginas q não percisam de segurança
                .antMatchers("/Login.xhtml", "/Erro.xhtml", "/javax.faces.resource/**").permitAll()
                
                .antMatchers("/Home.xhtml", "/AcessoNegado.xhtml").authenticated() // Precisa estar autenticado
                .antMatchers("/contato/**").hasAnyRole("COLABORADOR", "ADMINISTRADOR") // Precisa ter permissão de...
                .antMatchers("/empresa/**").hasAnyRole("COLABORADOR", "ADMINISTRADOR")
                .antMatchers("/grupo/**").hasAnyRole("ADMINISTRADOR")
                .antMatchers("/prospecto/**").hasAnyRole("COLABORADOR", "ADMINISTRADOR")
                .antMatchers("/ramoAtividade/**").hasAnyRole("COLABORADOR", "ADMINISTRADOR")
                .antMatchers("/tipoProspecto/**").hasAnyRole("ADMINISTRADOR")
                .antMatchers("/usuarios/**").hasAnyRole("ADMINISTRADOR")
                .and()

                .formLogin()
                	.loginPage("/Login.xhtml")
                	.failureUrl("/Login.xhtml?invalid=true")
                	.defaultSuccessUrl("/Home.xhtml")
                	.and()

                .logout()
                	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                	.and()

                .exceptionHandling()
                	.accessDeniedPage("/AcessoNegado.xhtml")
                	.authenticationEntryPoint(jsfLoginEntry)
                	.accessDeniedHandler(jsfDeniedEntry);
    }
}
