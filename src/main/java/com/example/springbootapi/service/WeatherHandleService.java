package com.example.springbootapi.service;

import com.example.springbootapi.dto.REGION;
import com.example.springbootapi.dto.ResponceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * Handle weather data from API.
 *
 * @author Naoto Wada
 */
@Service
public class WeatherHandleService {

    @Autowired
    private TopService service;

    /**
     * <p>
     * Create pollen affection result from RequestAPI data which is involved input
     * city name.
     *
     * @param cityName if {@code null} then return EMPTY.<br>
     *                 only adopt Japanese region.
     * @return view word as result
     */
    public String getResultByRequest(String cityName) {

        if (cityName == null) {
            return "";
        }

        ResponceDTO dto = service.requestOpenWeatherAPI(cityName);

        List<Boolean> resultList = new ArrayList<>();

        resultList.add(JudgeWeather.isMatch(dto.getWeather()));
        resultList.add(JudgeHumid.isMatch(dto.getHumidity()));
        resultList.add(JudgeWindSpeed.isMatch(dto.getSpeed()));

        return PollenResultCreator.create(resultList);
    }

    /**
     * <p>
     * Create pollen affection result by city name.
     *
     * @param cityName if {@code null} then return EMPTY.<br>
     *                 only adopt Japanese region.
     * @return view word as result
     */
    public String getPollen(String cityName) {

        if (cityName == null) {
            return "";
        }

        List<Boolean> resultList = new ArrayList<>();

        resultList.add(JudgeWeather.isMatch(REGION.getWeather(cityName)));
        resultList.add(JudgeHumid.isMatch(REGION.getHumid(cityName)));
        resultList.add(JudgeWindSpeed.isMatch(REGION.getWindSpeed(cityName)));

        return PollenResultCreator.create(resultList);
    }

    /**
     * <p>
     * Get currentTime as specific format. truncate milliseconds.
     *
     * @return time truncated Millseconds
     */
    public String currentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.systemDefault()).truncatedTo(ChronoUnit.SECONDS);

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(String.valueOf(now.getYear()));
        sb.append("年");
        sb.append(String.valueOf(now.getMonth().getValue()));
        sb.append("月");
        sb.append(String.valueOf(now.getDayOfMonth()));
        sb.append("日 ");
        sb.append(String.valueOf(now.getHour()));
        sb.append("時");
        sb.append(String.valueOf(now.getMinute()));
        sb.append("分");
        sb.append(String.valueOf(now.getSecond()));
        sb.append("秒");
        sb.append("]");

        return sb.toString();
    }

    /**
     * <p>
     * Get city by English name region.
     *
     * @param cityName if {@code null} then return EMPTY.<br>
     *                 only adopt Japanese region.
     * @return Japanese city name
     */
    public String getJapaneseCityName(String cityName) {
        if (cityName == null) {
            return "";
        }
        return REGION.toJpName(cityName);
    }
}