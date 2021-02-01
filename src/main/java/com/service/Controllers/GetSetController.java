package com.service.Controllers;

import java.util.ArrayList;

import com.service.Items.Data;
import com.service.Service.DeleteData;
import com.service.Service.ReturnData;
import com.service.Service.SetData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class GetSetController {

//    @Value("${url}")
//    String url;
//    @Value("${username}")
//    String username;
//    @Value("${password}")
//    String password;
    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus check()
    {
        return HttpStatus.OK;

    }

    @GetMapping()
    public ArrayList<Data> gets(@RequestParam(value = "dateStart", defaultValue = "0000.00.00") String dateStart,
                                @RequestParam(value = "dateEnd", defaultValue = "9999.12.30") String dateEnd) {

        ReturnData rd = new ReturnData();
        return  rd.get(dateEnd, dateStart);

    }

    @DeleteMapping()
    public  HttpStatus remove(@RequestParam (value = "id") String id) {

        DeleteData dd = new DeleteData();
        return dd.remove(id);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public HttpStatus set(@RequestBody ArrayList<Data> data) {

        SetData sd = new SetData();
        return sd.set(data);

    }

}
