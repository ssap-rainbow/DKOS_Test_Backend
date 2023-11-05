// package shop.ssap.ssap.service;

// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;
// import org.springframework.web.client.RestTemplate;
// import shop.ssap.ssap.dto.KakaoDTO;

// @Service
// public class KakaoService {
//     @Value("${KAKAO_CLIENT_ID:default_client_id}")
//     private String KAKAO_CLIENT_ID;
    
//     @Value("${KAKAO_CLIENT_SECRET:default_client_secret}")
//     private String KAKAO_CLIENT_SECRET;
    
//     @Value("${KAKAO_REDIRECT_URL:default_redirect_url}")
//     private String KAKAO_REDIRECT_URL;


//     private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
//     private final static String KAKAO_API_URI = "https://kapi.kakao.com";

//     public String getKakaoLogin() {
//         return KAKAO_AUTH_URI + "/oauth/authorize"
//                 + "?client_id=" + KAKAO_CLIENT_ID
//                 + "&redirect_uri=" + KAKAO_REDIRECT_URL
//                 + "&response_type=code";
//     }

//     public KakaoDTO getKakaoInfo(String code) throws Exception {
//         if (code == null) throw new Exception("Failed get authorization code");

//         String accessToken = "";
//         String refreshToken = "";

//         try {
//             HttpHeaders headers = new HttpHeaders();
//             headers.add("Content-type", "application/x-www-form-urlencoded");

//             MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//             params.add("grant_type"   , "authorization_code");
//             params.add("client_id"    , KAKAO_CLIENT_ID);
//             params.add("client_secret", KAKAO_CLIENT_SECRET);
//             params.add("code"         , code);
//             params.add("redirect_uri" , KAKAO_REDIRECT_URL);

//             RestTemplate restTemplate = new RestTemplate();
//             HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);

//             ResponseEntity<String> response = restTemplate.exchange(
//                     KAKAO_AUTH_URI + "/oauth/token",
//                     HttpMethod.POST,
//                     httpEntity,
//                     String.class
//             );

//             JSONObject jsonObj = new JSONObject(response.getBody());

//             accessToken  = (String) jsonObj.get("access_token");
//             System.out.println(">>>>>>>>>>" + accessToken);
//             refreshToken = (String) jsonObj.get("refresh_token");
//         } catch (Exception e) {
//             throw new Exception("API call failed");
//         }

//         return getUserInfoWithToken(accessToken);
//     }

//     private KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
//         //HttpHeader 생성
//         HttpHeaders headers = new HttpHeaders();
//         headers.add("Authorization", "Bearer " + accessToken);
//         headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

//         //HttpHeader 담기
//         RestTemplate rt = new RestTemplate();
//         HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);
//         ResponseEntity<String> response = rt.exchange(
//                 KAKAO_API_URI + "/v2/user/me",
//                 HttpMethod.POST,
//                 httpEntity,
//                 String.class
//         );

//         //Response 데이터 파싱
//         JSONObject jsonObj = new JSONObject(response.getBody());
//         JSONObject account = (JSONObject) jsonObj.get("kakao_account");
//         JSONObject profile = (JSONObject) account.get("profile");

//         long id = (long) jsonObj.get("id");
//         String email = String.valueOf(account.get("email"));
//         String nickname = String.valueOf(profile.get("nickname"));

//         return KakaoDTO.builder()
//                 .id(id)
//                 .email(email)
//                 .nickname(nickname).build();
//     }
// }


package shop.ssap.ssap.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import shop.ssap.ssap.dto.KakaoDTO;

@Service
public class KakaoService {

    // 프록시 설정이 포함된 RestTemplate을 주입받습니다.
    private final RestTemplate restTemplate;

    @Value("${KAKAO_CLIENT_ID:default_client_id}")
    private String KAKAO_CLIENT_ID;

    @Value("${KAKAO_CLIENT_SECRET:default_client_secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${KAKAO_REDIRECT_URL:default_redirect_url}")
    private String KAKAO_REDIRECT_URL;

    private final static String KAKAO_AUTH_URI = "https://kauth.kakao.com";
    private final static String KAKAO_API_URI = "https://kapi.kakao.com";

    // 프록시가 구성된 RestTemplate을 자동 주입합니다.
    @Autowired
    public KakaoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getKakaoLogin() {
        return KAKAO_AUTH_URI + "/oauth/authorize"
                + "?client_id=" + KAKAO_CLIENT_ID
                + "&redirect_uri=" + KAKAO_REDIRECT_URL
                + "&response_type=code";
    }

    public KakaoDTO getKakaoInfo(String code) throws Exception {
        if (code == null) throw new Exception("인증 코드를 가져오지 못했습니다.");

        String accessToken;
        String refreshToken;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("client_secret", KAKAO_CLIENT_SECRET);
        params.add("code", code);
        params.add("redirect_uri", KAKAO_REDIRECT_URL);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_AUTH_URI + "/oauth/token",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        JSONObject jsonObj = new JSONObject(response.getBody());

        accessToken = jsonObj.getString("access_token");
        refreshToken = jsonObj.getString("refresh_token");

        return getUserInfoWithToken(accessToken);
    }

    private KakaoDTO getUserInfoWithToken(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                KAKAO_API_URI + "/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                String.class
        );

        JSONObject jsonObj = new JSONObject(response.getBody());
        JSONObject account = jsonObj.getJSONObject("kakao_account");
        JSONObject profile = account.getJSONObject("profile");

        long id = jsonObj.getLong("id");
        String email = account.optString("email"); // JSONException을 피하기 위해 optString 사용
        String nickname = profile.optString("nickname");

        return KakaoDTO.builder()
                .id(id)
                .email(email)
                .nickname(nickname).build();
    }
}
