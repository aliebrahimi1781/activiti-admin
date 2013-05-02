package adminom

import java.text.DateFormat

import org.codehaus.groovy.grails.web.binding.StructuredDateEditor


public class CalendarEditor extends StructuredDateEditor {
    private def currentDateFormat

    public CalendarEditor(DateFormat dateFormat, boolean allowEmpty) {
        super(dateFormat, allowEmpty)
        currentDateFormat = dateFormat
    }

    @Override
    public String getAsText() {
        Date date = super.getValue()
        return currentDateFormat.format(date)
    }

    @Override
    public Date getValue() {
        Date date = super.getValue()
        return date
    }
}

