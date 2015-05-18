<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <title>Sad Cycles</title>

        <link type="text/css" rel="stylesheet" href="/main.css"/>
    </head>

    <body>
        <p>
            <a target="_blank" href="https://www.reddit.com/r/dailyprogrammer/comments/36cyxf/20150518_challenge_215_easy_sad_cycles/">https://www.reddit.com/r/dailyprogrammer/comments/36cyxf/20150518_challenge_215_easy_sad_cycles/</a>
        </p>

        <form class="sad-cycles-form" method="GET" action="/sadcycles">
            <label>n: </label>
            <input type="text" name="n" value="" placeholder="n" />
            <br>
            <label>b: </label>
            <input type="text" name="b" value="" placeholder="b" />
            <br>
            <input type="submit" name="Update" value="Update" />
        </form>

        <p class="result"></p>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).on('submit', '.sad-cycles-form', function(e) {
                 $.ajax({
                    url: $(this).attr('action'),
                    type: $(this).attr('method'),
                    data: $(this).serialize(),
                    success: function(response) {
                        $(".result").text(response);
                    }
                });
                e.preventDefault();
            });
        </script>
    </body>
</html>