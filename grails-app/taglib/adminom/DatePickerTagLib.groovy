package adminom

import org.springframework.web.servlet.support.RequestContextUtils as RCU;
import java.text.DateFormatSymbols
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.i18n.LocaleContextHolder

class DatePickerTagLib {
    static namespace = "bootstrap"

    /**
    * A simple date picker that renders a date as selects.<br/>
    * This is just an initial hack - can be widely improved!
    * e.g. &lt;bs:datePicker name="myDate" value="${new Date()}" /&gt;
    *
    * @emptyTag
    *
    * @attr name REQUIRED The name of the date picker field set
    * @attr value The current value of the date picker; defaults to now if not specified
    * @attr precision The desired granularity of the date to be rendered
    * @attr noSelection A single-entry map detailing the key and value to use for the "no selection made" choice in the select box. If there is no current selection this will be shown as it is first in the list, and if submitted with this selected, the key that you provide will be submitted. Typically this will be blank.
    * @attr years A list or range of years to display, in the order specified. i.e. specify 2007..1900 for a reverse order list going back to 1900. If this attribute is not specified, a range of years from the current year - 100 to current year + 100 will be shown.
    * @attr relativeYears A range of int representing values relative to value. For example, a relativeYears of -2..7 and a value of today will render a list of 10 years starting with 2 years ago through 7 years in the future. This can be useful for things like credit card expiration dates or birthdates which should be bound relative to today.
    * @attr id the DOM element id
    * @attr disabled Makes the resulting inputs and selects to be disabled. Is treated as a Groovy Truth.
    * @attr readonly Makes the resulting inputs and selects to be made read only. Is treated as a Groovy Truth.
    */
    Closure datePicker = { attrs ->
        def out = out // let x = x ?
        def xdefault = attrs['default']
        if (xdefault == null) {
            xdefault = new Date()
        }
        else if (xdefault.toString() != 'none') {
            if (xdefault instanceof String) {
                xdefault = DateFormat.getInstance().parse(xdefault)
            }
            else if (!(xdefault instanceof Date)) {
                throwTagError("Tag [datePicker] requires the default date to be a parseable String or a Date")
            }
        }
        else {
            xdefault = null
        }
        def years = attrs.years
        def relativeYears = attrs.relativeYears
        if (years != null && relativeYears != null) {
            throwTagError 'Tag [datePicker] does not allow both the years and relativeYears attributes to be used together.'
        }

        if (relativeYears != null) {
            if (!(relativeYears instanceof IntRange)) {
                // allow for a syntax like relativeYears="[-2..5]". The value there is a List containing an IntRage.
                if ((!(relativeYears instanceof List)) || (relativeYears.size() != 1) || (!(relativeYears[0] instanceof IntRange))){
                    throwTagError 'The [datePicker] relativeYears attribute must be a range of int.'
                }
                relativeYears = relativeYears[0]
            }
        }
        def value = attrs.value
        if (value.toString() == 'none') {
            value = null
        } else if (!value) {
            value = xdefault
        }
        def name = attrs.name
        def id = attrs.id ?: name

        def noSelection = attrs.noSelection
        if (noSelection != null) {
            noSelection = noSelection.entrySet().iterator().next()
        }

        final PRECISION_RANKINGS = ["year": 0, "month": 10, "day": 20, "hour": 30, "minute": 40]
        def precision = (attrs.precision ? PRECISION_RANKINGS[attrs.precision] :
            (grailsApplication.config.grails.tags.datePicker.default.precision ?
                PRECISION_RANKINGS["${grailsApplication.config.grails.tags.datePicker.default.precision}"] :
                PRECISION_RANKINGS["minute"]))

        def day
        def month
        def year
        def hour
        def minute
        def dfs = new DateFormatSymbols(RCU.getLocale(request))

        def c = null
        if (value instanceof Calendar) {
            c = value
        }
        else if (value != null) {
            c = new GregorianCalendar()
            c.setTime(value)
        }

        if (c != null) {
            day = c.get(GregorianCalendar.DAY_OF_MONTH)
            month = c.get(GregorianCalendar.MONTH) + 1      // add one, as Java stores month from 0..11
            year = c.get(GregorianCalendar.YEAR)
            hour = c.get(GregorianCalendar.HOUR_OF_DAY)
            minute = c.get(GregorianCalendar.MINUTE)
        }

        if (years == null) {
            def tempyear
            if (year == null) {
                // If no year, we need to get current year to setup a default range... ugly
                def tempc = new GregorianCalendar()
                tempc.setTime(new Date())
                tempyear = tempc.get(GregorianCalendar.YEAR)
            }
            else {
                tempyear = year
            }
            if (relativeYears) {
                if (relativeYears.reverse) {
                    years = (tempyear + relativeYears.toInt)..(tempyear + relativeYears.fromInt)
                } else {
                    years = (tempyear + relativeYears.fromInt)..(tempyear + relativeYears.toInt)
                }
            } else {
                years = (tempyear - 100)..(tempyear + 100)
            }
        }

        booleanToAttribute(attrs, 'disabled')
        booleanToAttribute(attrs, 'readonly')

        // get the localized format for dates. NOTE: datepicker only uses Lowercase syntax and does not understand hours, seconds, etc. (it uses: dd, d, mm, m, yyyy, yy)
        def messageSource = ApplicationHolder.application.mainContext.getBean('messageSource')
        String dateFormat = messageSource.getMessage("default.date.format",null,'dd/MM/yyyy HH:mm',LocaleContextHolder.locale )
        String dateFormatJS = dateFormat.tr("Hh","hH")
        String formattedDate = g.formatDate(format: dateFormat, date: c?.getTime())
        out << """
            <input type="hidden" name="${name}" id="${id}" value="date.struct" />
            <input type="hidden" name="${name}_day" id="${id}_day" value="${day}" />
            <input type="hidden" name="${name}_month" id="${id}_month" value="${month}" />
            <input type="hidden" name="${name}_year" id="${id}_year" value="${year}" />
            <input type="hidden" name="${name}_hour" id="${id}_hour" value="${hour}" />
            <input type="hidden" name="${name}_minute" id="${id}_minute" value="${minute}" />
            <div id=\"${id}-container\" class=\"input-append date\">
                <input id=\"${id}-holder\" name=\"${name}-holder\" size=\"16\" type=\"text\" value=\"${formattedDate}\" data-format=\"${dateFormatJS}\" readonly/>
                <span class=\"add-on\"><i data-time-icon="icon-time" data-date-icon="icon-calendar"></i></span>
            </div>
        """
        def sb = new StringBuffer()
        sb << """
            \$('#${id}-container').datetimepicker()
                .on('changeDate', function(ev){
                        try{
                            var date = ev.date
                            \$('#${id}_month').attr("value",date.getMonth() +1); 
                            \$('#${id}_day').attr("value",date.getDate());
                            \$('#${id}_year').attr("value",date.getFullYear());
                            \$('#${id}_hour').attr("value",date.getHours());
                            \$('#${id}_minute').attr("value",date.getMinutes());
                            }
                        catch(error){
                            \$('#${id}_month').attr("value","");
                            \$('#${id}_day').attr("value","");
                            \$('#${id}_year').attr("value","");
                            \$('#${id}_hour').attr("value","");
                            \$('#${id}_minute').attr("value","");
                        }
                });
        """
        out << r.script() { sb.toString() }
    }

    /**
    * Some attributes can be defined as Boolean values, but the html specification
    * mandates the attribute must have the same value as its name. For example,
    * disabled, readonly and checked.
    */
    private void booleanToAttribute(def attrs, String attrName) {
        def attrValue = attrs.remove(attrName)
        // If the value is the same as the name or if it is a boolean value,
        // reintroduce the attribute to the map according to the w3c rules, so it is output later
        if (Boolean.valueOf(attrValue) ||
          (attrValue instanceof String && attrValue?.equalsIgnoreCase(attrName))) {
            attrs.put(attrName, attrName)
        } else if (attrValue instanceof String && !attrValue?.equalsIgnoreCase('false')) {
            // If the value is not the string 'false', then we should just pass it on to
            // keep compatibility with existing code
            attrs.put(attrName, attrValue)
        }
    }

}
