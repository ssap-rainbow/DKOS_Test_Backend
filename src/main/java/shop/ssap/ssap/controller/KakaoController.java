package shop.ssap.ssap.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import shop.ssap.ssap.common.MsgEntity;
import shop.ssap.ssap.dto.KakaoDTO;
import shop.ssap.ssap.service.KakaoService;

@CrossOrigin(origins = "*") 
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kakao")
public class KakaoController {

    private final RestTemplate restTemplate;

    private final KakaoService kakaoService;

    // @GetMapping("/callback")
    // public ResponseEntity<MsgEntity> callback(HttpServletRequest request) throws Exception {
    //     KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));

    //     return ResponseEntity.ok()
    //             .body(new MsgEntity("Success", kakaoInfo));
    // }
    @GetMapping("/callback")
    public ResponseEntity<KakaoDTO> callback(HttpServletRequest request) throws Exception {
        KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"));
        return ResponseEntity.ok(kakaoInfo); // 바로 kakaoInfo를 반환
    }    
}
