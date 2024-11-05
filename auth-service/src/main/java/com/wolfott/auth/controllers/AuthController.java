package com.wolfott.auth.controllers;

import com.wolfott.auth.requests.LoginRequest;
import com.wolfott.auth.responses.LoginResponse;
import com.wolfott.auth.services.AuthService;
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
        String captchaId = "3474b7f80d5f0cdcefcc27778bed5ff2";
        String captchaKey = "071544ae9d0d0dc2b3676dc1d28d932f";
        String domain = "http://gcaptcha4.geetest.com";

        String lotNumber = getParams.get("lot_number");
        String captchaOutput = getParams.get("captcha_output");
        String passToken = getParams.get("pass_token");
        String genTime = getParams.get("gen_time");
        String signToken = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, captchaKey).hmacHex(lotNumber);

        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("lot_number", lotNumber);
        queryParams.add("captcha_output", captchaOutput);
        queryParams.add("pass_token", passToken);
        queryParams.add("gen_time", genTime);
        queryParams.add("sign_token", signToken);

        String url = String.format(domain + "/validate" + "?captcha_id=%s", captchaId);
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject jsonObject = new JSONObject();

        try {
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(queryParams, headers);
            ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
            String resBody = response.getBody();
            jsonObject = new JSONObject(resBody);
        } catch (Exception e) {
            jsonObject.put("result", "success");
            jsonObject.put("reason", "request geetest api fail");
        }

        JSONObject res = new JSONObject();
        if (jsonObject.getString("result").equals("success")) {
            res.put("login", "success");
            res.put("reason", jsonObject.getString("reason"));
        } else {
            res.put("login", "fail");
            res.put("reason", jsonObject.getString("reason"));
        }
        return res.toString();
    }
}
