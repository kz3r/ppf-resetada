package kz3r.ppfresetada.core.servlet

import kz3r.ppfresetada.core.APPLICATION_CONTEXT_PATH
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.stereotype.Component

@Component
class ServletCustomization : WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    override fun customize(factory: ConfigurableServletWebServerFactory?) {
        factory?.setContextPath(APPLICATION_CONTEXT_PATH)
    }
}