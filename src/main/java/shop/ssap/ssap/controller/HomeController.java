// package shop.ssap.ssap.controller;

// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.*;
// import shop.ssap.ssap.repository.SampleData;
// import shop.ssap.ssap.repository.SampleDataRepository;

// import java.util.Optional;

// @CrossOrigin(origins = "*") 
// @Controller
// @Tag(name = "Home 컨트롤러", description = "HOME API입니다.")
// public class HomeController {

//     private final SampleDataRepository sampleDataRepository;

//     public HomeController(SampleDataRepository sampleDataRepository) {
//         this.sampleDataRepository = sampleDataRepository;
//     }

//     @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
//     @ResponseBody
//     public String hello() {
//         return "hello";
//     }

//     @RequestMapping(value = "/api/greeting", method = RequestMethod.POST)
//     @ResponseBody
//     public String greeting(@RequestBody String name) {
//         return "Hello, " + name + "!";
//     }



//     @RequestMapping(value="/api/home", method= RequestMethod.GET)
//     @ResponseBody
//     public ResponseEntity<String> home() {
//         // "Hello DKOS!" 값을 가진 데이터만 검색
//         Optional<SampleData> helloDkosData = sampleDataRepository.findByDetail("Hello DKOS!");
        
//         if (helloDkosData.isPresent()) {
//             return ResponseEntity.ok(helloDkosData.get().getDetail());
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }
// }


package shop.ssap.ssap.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import shop.ssap.ssap.service.KakaoService;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final KakaoService kakaoService;

    @RequestMapping(value="/api/home", method= RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());

        return "index";
    }


}
