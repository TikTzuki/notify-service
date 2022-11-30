package vn.unicloud.notification.controller

import io.swagger.v3.oas.annotations.Hidden
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Hidden
@Controller
class SpringDocController {

    @GetMapping("redoc")
    @ResponseBody
    fun reDocs(): String {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>Redoc</title>\n" +
                "    <meta charset=\"utf-8\"/>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link href=\"https://fonts.googleapis.com/css?family=Montserrat:300,400,700|Roboto:300,400,700\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<redoc spec-url='/v3/api-docs'></redoc>\n" +
                "<script src=\"https://cdn.redoc.ly/redoc/latest/bundles/redoc.standalone.js\"></script>\n" +
                "</body>\n" +
                "</html>";
    }
}