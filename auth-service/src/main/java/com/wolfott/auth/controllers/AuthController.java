package com.wolfott.auth.controllers;

import com.wolfott.auth.requests.LoginRequest;
import com.wolfott.auth.responses.LoginResponse;
import com.wolfott.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping(value = "/validate")
    public String userLogin(@RequestParam Map<String, String> getParams) {

        log.info("getParams: {}", getParams);

        String captchaKey = "bfa3323b8dacf60a9e83a787e53f2714";
        String domain = "https://gcaptcha4.geetest.com";
        String captchaId = getParams.get("captcha_id");
        String lotNumber = getParams.get("lot_number");
        String signToken = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, captchaKey).hmacHex(lotNumber);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("captcha_output", getParams.get("captcha_output"));
        queryParams.add("gen_time", getParams.get("gen_time"));
        queryParams.add("lot_number", lotNumber);
        queryParams.add("pass_token", getParams.get("pass_token"));
        queryParams.add("sign_token", signToken);

        String url = String.format(domain + "/validate" + "?captcha_id=%s", captchaId);
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = new JSONObject();

        log.info("url: {}", url);
        log.info("queryParams: {}", queryParams.toString());

        try {
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(queryParams, headers);
            ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
            String resBody = response.getBody();
            jsonObject = new JSONObject(resBody);
        } catch (Exception e) {
            jsonObject.put("result", "success");
            jsonObject.put("reason", "request geetest api fail");
        }
        log.info("JSONObject: {}", jsonObject.toString());

        return jsonObject.toString();
    }
}
