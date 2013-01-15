<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
    <head>
        <script src="/resources/js/jquery.min.js"></script>
        <script src="/resources/js/arbor.js" ></script>
        <script src="/resources/js/graphics.js" ></script>
        <script src="/resources/js/renderer.js" ></script>
       
 
        
    </head>
    <body>
     <div id="timeline-embed"></div>
     <script type="text/javascript">
        var timeline_config = {
            width:              '100%',
            height:             '600',
            source:            ${json},
            embed_id:           'timeline-embed',               //OPTIONAL USE A DIFFERENT DIV ID FOR EMBED
            start_at_end:       true,                          //OPTIONAL START AT LATEST DATE
         //   start_at_slide:     '4',                            //OPTIONAL START AT SPECIFIC SLIDE
            start_zoom_adjust:  '0.1',                            //OPTIONAL TWEAK THE DEFAULT ZOOM LEVEL
            hash_bookmark:      true,                           //OPTIONAL LOCATION BAR HASHES
            font:               'Bevan-PotanoSans',             //OPTIONAL FONT
            debug:              true,                           //OPTIONAL DEBUG TO CONSOLE
            lang:               'en',                           //OPTIONAL LANGUAGE
            maptype:            'watercolor',                   //OPTIONAL MAP STYLE
            css:                '/resources/css/timeline.css',     //OPTIONAL PATH TO CSS
            js:                 '/resources/js/timeline-min.js'    //OPTIONAL PATH TO JS
        }
        </script>
    <script type="text/javascript" src="/resources/js/storyjs-embed.js"></script>
    </body>
</html>
    