<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <script language="javascript" type="text/javascript" src="/springmvc/resources/js/arbor.js" ></script>
        <script language="javascript" type="text/javascript" src="/springmvc/resources/js/renderer.js" ></script>
        <script language="javascript" type="text/javascript"  src="/springmvc/resources/js/jquery.min.js"></script>
        <script language="javascript" type="text/javascript" src="/springmvc/resources/js/graphics.js" ></script>
    </head>
    <body>
      <canvas id="viewport" width="800" height="600"></canvas>
      <script language="javascript" type="text/javascript">
            var sys = arbor.ParticleSystem(1000, 400,1);
            sys.parameters({gravity:true});
            sys.renderer = Renderer("#viewport") ;
            var data = {
               nodes:{
                 animals:{'color':'red','shape':'dot','label':'Animals'},
                 dog:{'color':'green','shape':'dot','label':'dog'},
                 cat:{'color':'blue','shape':'dot','label':'cat'}
               }, 
               edges:{
                 animals:{ dog:{}, cat:{} }
               }
             };
            sys.graft(data);
            setTimeout(function(){
                        var postLoadData = {
                           nodes:{
                             joe:{'color':'orange','shape':'dot','label':'joe'},
                             fido:{'color':'green','shape':'dot','label':'fido'},
                             fluffy:{'color':'blue','shape':'dot','label':'fluffy'}
                           }, 
                           edges:{
                                dog:{ fido:{} },
                                cat:{ fluffy:{} },
                                joe:{ fluffy:{},fido:{} }
                           }
                         };
                         sys.graft(postLoadData);
                    },10000);
      </script>
    </body>
</html>