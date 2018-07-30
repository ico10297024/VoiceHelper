package com.sunruncn.unitdemo;

import com.alibaba.fastjson.JSON;
import com.sunruncn.unitdemo.dto.AccessTokenDTO;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        String json = "{\"access_token\":\"24.2c770cf3e8d86fb6069022f1b1773800.2592000.1534929195.282335-11563705\",\"session_key\":\"9mzdCXV8N0MQKzQ9cy+pR9wKPA241RavPJ2pmXObexR4hzL1wxBycRM6eXGaMbLp2dcrJBSOo53o4jyX2UMkoUGzntzGXQ==\",\"scope\":\"public brain_all_scope brain_unit_utterance audio_voice_assistant_get audio_tts_post wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test\\u6743\\u9650 vis-classify_flower lpq_\\u5f00\\u653e cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar\",\"refresh_token\":\"25.5e2251c5d6d754597b7696f553e1a4d9.315360000.1847697195.282335-11563705\",\"session_secret\":\"bccb03ffde04fcaa16917e6f855045f9\",\"expires_in\":2592000}";
        AccessTokenDTO accessTokenDTO = JSON.parseObject(json, AccessTokenDTO.class);
        System.out.println(json);
    }
}