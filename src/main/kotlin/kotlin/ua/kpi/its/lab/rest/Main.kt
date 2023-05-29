package ua.kpi.its.lab.rest

import jakarta.servlet.DispatcherType
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.DispatcherServlet
import java.util.*

@SpringBootApplication
class Main

private val logger: Logger
    get() = LogManager.getLogger()

fun main() {
    startJetty()
}

private fun startJetty() {
    logger.info("Starting server at port {}", 8080)
    val server = Server(8080).apply {
        handler = servletContextHandler
        addRuntimeShutdownHook()
    }

    server.start()
    logger.info("Server started at port {}", 8080)
    server.join()
}

private val servletContextHandler: ServletContextHandler
    get() {
        val webAppContext = webApplicationContext
        val dispatcherServlet = DispatcherServlet(webAppContext)
        val springServletHolder = ServletHolder("dispatcherServlet", dispatcherServlet)
        val delegatingFilterProxy = DelegatingFilterProxy("springSecurityFilterChain", webAppContext)
        return ServletContextHandler(ServletContextHandler.SESSIONS).apply {
            errorHandler = null
            contextPath = "/"
            addServlet(springServletHolder, "/*")
            addEventListener(ContextLoaderListener(webAppContext))
            addFilter(FilterHolder(delegatingFilterProxy), "/*", EnumSet.of(DispatcherType.REQUEST))
        }
    }

private val webApplicationContext: WebApplicationContext
    get() = AnnotationConfigWebApplicationContext().apply {
        setConfigLocation("ua.kpi.its.lab.rest.config")
    }
private fun Server.addRuntimeShutdownHook() {
    Runtime.getRuntime().addShutdownHook(Thread {
        if (isStarted) {
            stopAtShutdown = true
            try {
                stop()
            } catch (e: Exception) {
                logger.error("Error while stopping jetty server: ${e.message}", e)
            }
        }
    })
}