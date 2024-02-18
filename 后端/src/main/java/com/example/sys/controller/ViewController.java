package com.example.sys.controller;

import com.example.common.lang.Result;
import com.example.python.DataView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {
    @PostMapping("/getView")
    public Result getView(@RequestBody String key)
    {

//        Table robberies = null;
//        try
//        {
//            robberies = Table.read().csv("./data/boston-robberies.csv");
//        }
//        catch (IOException  e)
//        {
//
//        }
//        Plot.show(
//                AreaPlot.create(
//                        "Boston Robberies by month: Jan 1966-Oct 1975",
//                        robberies, "Record", "Robberies"));


//        Result result = DataView.excView(url);
        return DataView.excView(key);
    }

}
