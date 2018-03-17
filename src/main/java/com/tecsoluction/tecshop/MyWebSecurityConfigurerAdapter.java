package com.tecsoluction.tecshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
@ComponentScan("com.tecsoluction.tecshop")
@Import({DataSourceConf.class})
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.roles-query}")
	private String rolesQuery;	
	
	@Autowired
	private DataSourceConf dataSource;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource.dataSource());
//				.passwordEncoder(bCryptPasswordEncoder);
				
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
		http.
			authorizeRequests()				
				.antMatchers("/public/**").permitAll()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/static/**").permitAll()
				.antMatchers("/templates/**").permitAll()
//				.antMatchers("/web/**").permitAll()
//				.antMatchers("/build/**").permitAll()
//				.antMatchers("/vendors/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/produto/produtoporcategoria**").permitAll()
				.antMatchers("/aempresa/**").permitAll()
				.antMatchers("/ofertas/**").permitAll()
				.antMatchers("/novos/**").permitAll()
				.antMatchers("/indicacao/**").permitAll()
				.antMatchers("/catalogo/**").permitAll()
				.antMatchers("/checkout/**").permitAll()
				.antMatchers("/registro/**").permitAll()
				.antMatchers("/produto/produtoporcategoria/**").permitAll()
				.antMatchers("/produto/detalhes/**").permitAll()
				.antMatchers("/ajuda/**").permitAll()
				.antMatchers("/entregas/**").permitAll()
				.antMatchers("/contato/**").permitAll()
				.antMatchers("/carrinhocompra/addproduto/**").permitAll()
				.antMatchers("/carrinhocompra/esvaziarcarrinho/**").permitAll()
				.antMatchers("/erro/**").permitAll()
				.antMatchers("/accessdenied/**").permitAll()
				.antMatchers("/enviaremail/**").permitAll()
				
//				.antMatchers("/bootstrap/**").permitAll()
				.antMatchers("/home").permitAll()
//				.antMatchers("/webjars/**").permitAll()
//				.antMatchers("*/sass/**").permitAll()
				.antMatchers("/private/**").hasAnyRole("MEMBRO","ADM").anyRequest().authenticated()
				.and()
				.csrf().disable().formLogin()
				.loginPage("/login").failureUrl("/erro")
				.defaultSuccessUrl("/home")
				.usernameParameter("username")
				.passwordParameter("senha")
				.and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/home").and().exceptionHandling()
				.accessDeniedPage("/accessdenied");
				
//		http.sessionManagement().maximumSessions(sessaoMax).and().invalidSessionUrl("/sessaoinvalida").and()
//		.sessionManagement().sessionFixation().migrateSession();
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**");
       web
	       .ignoring()
	       .antMatchers("/static/**");
       
       web
       .ignoring()
       .antMatchers("/css/**");
       
       web
       .ignoring()
       .antMatchers("/js/**");
       
       web
       .ignoring()
       .antMatchers("/img/**");
       
//       web
//       .ignoring()
//       .antMatchers("/web/**");
       
//       web
//       .ignoring()
//       .antMatchers("/build/**");
       
//       web
//       .ignoring()
//       .antMatchers("/vendors/**");
       
       web
       .ignoring()
       .antMatchers("/public/**");
       
       web
       .ignoring()
       .antMatchers("/templates/**");
       
//       web
//       .ignoring()
//       .antMatchers("/webjars/**");
       
//       web
//       .ignoring()
//       .antMatchers("*/sass/**");
       
       

	}
	

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("MEMBRO")
                .and()
                .withUser("admin").password("password").roles("ADM");
    }
	
	
	

}
