package shop.ssap.ssap.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.ssap.ssap.repository.SampleData;
import shop.ssap.ssap.repository.SampleDataRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") 
@Controller
@Tag(name = "Home 컨트롤러", description = "HOME API입니다.")
public class HomeController {

    private final SampleDataRepository sampleDataRepository;

    public HomeController(SampleDataRepository sampleDataRepository) {
        this.sampleDataRepository = sampleDataRepository;
    }

    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/greeting", method = RequestMethod.POST)
    @ResponseBody
    public String greeting(@RequestBody String name) {
        return "Hello, " + name + "!";
    }



    @RequestMapping(value="/api/home", method= RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> home() {
        List<SampleData> sampleDataList = sampleDataRepository.findAll();

        // sampleDataList에서 detail 값을 추출하여 하나의 문자열로 결합
        String result = sampleDataList.stream()
                .map(SampleData::getDetail)
                .collect(Collectors.joining(" "));

        return ResponseEntity.ok(result);
    }
}
