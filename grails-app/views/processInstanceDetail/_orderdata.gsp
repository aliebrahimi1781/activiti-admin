<%@ page import="grails.converters.JSON" %>
<div id="orderinfo" class="orderdata"></div>
<r:script>
	function visitObj($container, obj) {
        var $ul = $('<ul>');

        for (var prop in obj) {
            if (obj[prop] == null) continue;
            var $li = $('<li>');
            if (typeof obj[prop] === "object") {
                if ('name' in obj[prop]) {
                    $li.append(obj[prop].name);
                    delete obj[prop].name;
                } else {
                    $li.append(prop);
                }
                visitObj($li, obj[prop]);
            } else {
                $li.append(prop + ' : '+obj[prop]);                   
            }
            $ul.append($li);
        }
        $container.append($ul);
    }
    visitObj($('#orderinfo'), ${it as JSON});
    $("#orderinfo").dynatree({
        // using default options
      });
</r:script>