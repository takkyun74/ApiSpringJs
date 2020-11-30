package com.example.springbootapi.service;

import com.example.springbootapi.dto.REGION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;


@Service
public class RegionDataService {

    @Autowired
    private TopService service;

    private static LocalDateTime LAST_REQUEST_TIME = null;

    /** API request cool time to avoid over request. */
    private static final int COOL_TIME_REQUEST_API_MINUTE = 1;

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

    public String getWeather(String cityName) {
        if (cityName == null) {
            return "";
        }
        return REGION.getWeather(cityName);
    }

    public double getTempMax(String cityName) {
        if (cityName == null) {
            return 0.0;
        }
        return REGION.getTempMax(cityName);
    }

    public double getTempMin(String cityName) {
        if (cityName == null) {
            return 0.0;
        }
        return REGION.getTempMin(cityName);
    }

    public int getHumid(String cityName) {
        if (cityName == null) {
            return 0;
        }
        return REGION.getHumid(cityName);
    }

    public double getWindSpeed(String cityName) {
        if (cityName == null) {
            return 0.0;
        }
        return REGION.getWindSpeed(cityName);
    }

    public String getJapaneseCityName(String cityName) {
        if (cityName == null) {
            return "";
        }
        return REGION.getCityName(cityName);
    }

    /**
     * <p>
     * Request API then store result map.
     * <p>
     * If request time under {@value COOL_TIME_REQUEST_API_MINUTE} minute, do
     * nothing.
     *
     * @param requestTime
     * @return result word
     */
    public String requireApi(LocalDateTime requestTime) {

        System.out.println("RegionDataService.requireApi 前回時刻:" + LAST_REQUEST_TIME);
        System.out.println("RegionDataService.requireApi 要求時刻:" + requestTime);

        if (LAST_REQUEST_TIME != null) {

            long durationTime = ChronoUnit.MINUTES.between(LAST_REQUEST_TIME, requestTime);
            if (durationTime < COOL_TIME_REQUEST_API_MINUTE) {

                System.out.println("RegionDataService.requireApi 要求時刻が早いので実行せずに終了:" + durationTime);

                return "もうしばらく時間をおいてから、問い合わせしてください";
            }
        }

        service.requestOpenWeatherAPIthenStoreMap();
        setCurrentTime();
        return "";
    }

    public void setCurrentTime() {
        LAST_REQUEST_TIME = LocalDateTime.now();
    }

}
