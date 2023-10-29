package shop.ssap.ssap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.ssap.ssap.repository.SampleData;
import shop.ssap.ssap.repository.SampleDataRepository;

import java.util.Optional;

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
        // "Hello DKOS!" 값을 가진 데이터만 검색
        Optional<SampleData> helloDkosData = sampleDataRepository.findByDetail("Hello DKOS!");
        
        if (helloDkosData.isPresent()) {
            return ResponseEntity.ok(helloDkosData.get().getDetail());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
