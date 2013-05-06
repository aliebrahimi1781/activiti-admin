// Place your Spring DSL code here
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

// default language in Config.groovy, if not found always fallback to english
def language = CH.config.app?.language?:"en"
def country = CH.config.app?.country?:"EN"
def appDefaultLocale = new Locale(language,country)

beans = {
    
    localeResolver(org.springframework.web.servlet.i18n.SessionLocaleResolver) {
        defaultLocale = appDefaultLocale
        java.util.Locale.setDefault(defaultLocale)
    }
    
    // customPropertyEditorRegistrar(adminom.CustomPropertyEditorRegistrar) {
    //    messageSource = ref('messageSource')
    // }
}

