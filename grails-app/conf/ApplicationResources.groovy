modules = {
    application {
        resource url: 'css/bootstrap-datetimepicker.min.css'
        resource url: 'js/bootstrap-datetimepicker.min.js'
        resource url: 'css/bootstrap-select.min.css'
        resource url: 'js/bootstrap-select.min.js'
        resource url:'js/application.js'

    }

    d3 {
    	resource url: 'js/d3/d3.v3.js'
    	resource url: 'js/d3/nv.d3.js'
    	resource url: 'css/d3/nv.d3.css'
    }

    raphael {
        resource url: 'js/raphael/raphael-min.js'
    }

    graphael {
        dependsOn 'raphael'
        resource url: 'js/raphael/g.raphael-min.js'
        resource url: 'js/raphael/g.pie-min.js'
        resource url: 'js/raphael/g.bar-min.js'
    }

    jqplot {
        resource url: 'css/jqplot/jquery.jqplot.css'
        resource url: 'js/jqplot/jquery.jqplot.min.js'
        resource url: 'js/jqplot/plugins/jqplot.pieRenderer.min.js'
        resource url: 'js/jqplot/plugins/jqplot.barRenderer.min.js'
        resource url: 'js/jqplot/plugins/jqplot.categoryAxisRenderer.min.js'
        resource url: 'js/jqplot/plugins/jqplot.pointLabels.min.js'
    }
}
