package shop.ssap.ssap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.ssap.ssap.repository.SampleData;
import shop.ssap.ssap.repository.SampleDataRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*") 
@Controller
public class HomeController {

    private final SampleDataRepository sampleDataRepository;

    public HomeController(SampleDataRepository sampleDataRepository) {
        this.sampleDataRepository = sampleDataRepository;
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
