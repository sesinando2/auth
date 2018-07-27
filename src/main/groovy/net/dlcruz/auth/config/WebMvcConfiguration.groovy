package net.dlcruz.auth.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry)

        registry.addViewController('/').setViewName('home')
        registry.addViewController('/oauth/confirm_access').setViewName('approval')
    }
}
